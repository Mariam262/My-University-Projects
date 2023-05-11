package com.example.project.Activity;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project.Domain.FoodDomain;
import com.example.project.Helper.ManagementCart;
import com.example.project.R;

public class ShowDetailActivity extends AppCompatActivity {

    private TextView addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt, totalPriceTxt, starTxt, caloryTxt, timeTxt;
    private ImageView plusBtn, minusBtn, picFood;
    private FoodDomain object;
    private int numberOder = 1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart=new ManagementCart(this);

        iniView();
        getBundle();
    }

    private void getBundle() {
        object=(FoodDomain)getIntent().getSerializableExtra("object");

        int drawableResourceId=this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("$"+object.getFee());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOder));
        caloryTxt.setText(object.getCalories()+"Calories");
        starTxt.setText(object.getStar()+"");
        titleTxt.setText(object.getTime()+" minutes");
        totalPriceTxt.setText("$"+Math.round(numberOder * object.getFee()));

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOder = numberOder + 1;
                numberOrderTxt.setText(String.valueOf(numberOder));
                totalPriceTxt.setText("$"+Math.round(numberOder * object.getFee()));
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOder > 1){
                    numberOder = numberOder - 1;
                }
                numberOrderTxt.setText(String.valueOf(numberOder));
                totalPriceTxt.setText("$"+Math.round(numberOder * object.getFee()));
            }
        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numberOder);
                managementCart.insertFood(object);
            }
        });
    }

    private void iniView()
    {
        addToCartBtn=(TextView) findViewById(R.id.addToCratBtn);
        titleTxt=(TextView) findViewById(R.id.titleTxt);
        feeTxt=(TextView) findViewById(R.id.priceTxt);
        descriptionTxt=(TextView) findViewById(R.id.descriptionTxt);
        numberOrderTxt=(TextView) findViewById(R.id.numberItemTxt);
        plusBtn=(ImageView) findViewById(R.id.plusCardBtn);
        minusBtn= (ImageView)  findViewById(R.id.minusCardBtn);
        picFood=(ImageView)  findViewById(R.id.foodPic);
        totalPriceTxt=(TextView) findViewById(R.id.totalPriceTxt);
        starTxt=(TextView) findViewById(R.id.startTxt);
        caloryTxt=(TextView) findViewById(R.id.caloTxt);
        timeTxt=(TextView) findViewById(R.id.timeTxt);
    }
}