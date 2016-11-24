package com.spatil32.a20367073_homework4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Shreyas on 10/6/2016.
 */
public class SecondActivity extends AppCompatActivity {
    //private data members
    private TextView foodName;
    private TextView foodDescription;
    private ImageView foodImage;

    //String array to store food menus
    private String[] food = new String[]
            {
                "Garlic Chicken", "Adobo Chicken", "Pizza", "Kabab",
                "MeatLoaf", "Shrimp", "Lasagna", "Roll", "Nacho Dip", "Cupcake", "Cookies"
            };
    //String array to store food item description
    private String[] descriptions = new String[]
            {
                "Goes with Pasta & Salad",
                "Traditionally cooked with Bok Choy",
                "Excellent Cauliflower Pizza",
                "Greek Island Chicken Shish Kabab",
                "Turkey and Quinoa Meatloaf",
                "Spicey Grilled Shrimp to hit any barbeque",
                "World's best Lasagna with onion and garlic",
                "Chicken Roll Enchiladas",
                "Outrageous Warm Chicken Nacho Dip",
                "Candy Corn Cupcake",
                "Pumpkin Chocolate Chip Cookies"
            };
    //Integer array to store food images of drawable type
    private Integer[] images = new Integer[]
            {
                    R.drawable.chikengarlic, R.drawable.adobochicken, R.drawable.pizzacrust, R.drawable.kabab,
                    R.drawable.meatloaf, R.drawable.shrimp, R.drawable.lasagna, R.drawable.roll,
                    R.drawable.nachodip, R.drawable.cupcake, R.drawable.cookies
            };
    private int timer;
    public Handler threadHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        foodName = (TextView) findViewById(R.id.foodname);
        foodDescription = (TextView)findViewById(R.id.foodDescription);
        foodImage = (ImageView)findViewById(R.id.foodImage);
        //get seekbar value passed through intent from main activity
        Intent intentView = this.getIntent();
        timer = intentView.getIntExtra("seekbarValue", 0);
        //calls runnable thread explicitly
        threadHandler.postDelayed(myRunnable, 0);
    }

    private final Runnable myRunnable = new Runnable() {
        int index = 0;
        @Override
        public void run()
        {
            foodName.setText(food[index]);
            foodImage.setImageResource(images[index]);
            foodDescription.setText(descriptions[index]);
            Toast.makeText(getApplicationContext(), "Favourite Food Item Number = " + (index + 1), Toast.LENGTH_SHORT).show();
            index++;
            if(index >= food.length) {
                index=0;
            }
            //run method is executed according to seekbar value * 1000 milliseconds until back button is pressed
            threadHandler.postDelayed(this, timer*1000);
        }
    };

    //Set message to pass to main activity
    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("message","Set the timer again to start the slide show.!!");
        this.setResult(Activity.RESULT_OK,data);
        super.finish();
    }

    //This method is executed when back button is pressed
    public void backClicked(View view)
    {
        //removes callbacks to threadHandler
        threadHandler.removeCallbacks(myRunnable);
        super.onBackPressed();
    }
}