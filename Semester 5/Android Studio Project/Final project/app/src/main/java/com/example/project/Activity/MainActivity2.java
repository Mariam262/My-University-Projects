package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.project.Adapter.CategoryAdapter;
import com.example.project.Adapter.RecommendedAdapter;
import com.example.project.Domain.CategoryDomain;
import com.example.project.Domain.FoodDomain;
import com.example.project.Editprofile;
import com.example.project.Help;
import com.example.project.LocationActivity;
import com.example.project.R;
import com.example.project.SearchActivity;
import com.example.project.Settings;
import com.example.project.Support;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    TextView tvloc;
    private RecyclerView.Adapter adapter, adapter2;
    TextView p, seem;
    LinearLayout homebtn, cartBtn,support, set, prof;
    EditText search;
    private RecyclerView recyclerViewCategotyList, recyclerViewPopularList;

    ViewFlipper v_flipper;
    int[] images = {
            R.drawable.icecream1,
            R.drawable.icecream2,
            R.drawable.ice3,
            R.drawable.icecream4,
            R.drawable.icecream5
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvloc = (TextView) findViewById(R.id.textView7);
        homebtn = (LinearLayout) findViewById(R.id.homeBtn);
        cartBtn = (LinearLayout) findViewById(R.id.cartbtn);
        support=(LinearLayout) findViewById(R.id.message);
        set=(LinearLayout) findViewById(R.id.settings);
        prof=(LinearLayout) findViewById(R.id.profile);
        p=(TextView)findViewById(R.id.textView7);
        search=(EditText) findViewById(R.id.editTextTextPersonName);


        v_flipper = findViewById(R.id.v_flipper);
        //Slider
        for (int i = 0; i < images.length; i++) {
            flipImage(images[i]);
        }
        recyclerViewCategory();
        recyclerViewPopular();
        recyclerViewPopular1();
        bottomNavigation();

    }

    private void bottomNavigation() {

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity2.class));
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, CartActivity.class));
            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, Help.class));
            }
        });
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, Settings.class));
            }
        });
        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, Editprofile.class));
            }
        });

        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, LocationActivity.class));
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, SearchActivity.class));
            }
        });

    }


    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.view2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);


        ArrayList<FoodDomain> foodlist = new ArrayList<>();
        foodlist.add(new FoodDomain("Choco Brownie", "rec1", "eggs,flavour, fresh cream, milk, chocolate serum", 13.0, 5, 20,  1000));
        foodlist.add(new FoodDomain("Green kulfi", "rec2", "eggs,flavour, fresh cream, milk, chocolate serum", 15.0, 4, 18,  1500));
        foodlist.add(new FoodDomain("Rainbow Cream", "rec3", "eggs,flavour, fresh cream, milk, chocolate serum", 10.0, 5, 20,  1000));
        foodlist.add(new FoodDomain("Choco Cone", "rec4", "eggs,flavour, fresh cream, milk, chocolate serum", 13.0, 5, 20,  1200));
        foodlist.add(new FoodDomain("Cornetto", "rec5", "eggs,flavour, fresh cream, milk, chocolate serum", 14.0, 5, 20,  1000));
        foodlist.add(new FoodDomain("Jet Sport", "rec6", "eggs,flavour, fresh cream, milk, chocolate serum", 13.0, 5, 20,  1000));
        foodlist.add(new FoodDomain("Frozen Yoghurt", "rec7", "eggs,flavour, fresh cream, milk, chocolate serum", 21.0, 5, 20,  1000));
        adapter2 = new RecommendedAdapter(foodlist);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    private void recyclerViewPopular1() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.view3);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);


        ArrayList<FoodDomain> foodlist = new ArrayList<>();
        foodlist.add(new FoodDomain("Crust Cones", "pop1", "eggs,flavour, fresh cream, milk, chocolate serum", 20.0, 5, 20, 1000));
        foodlist.add(new FoodDomain("Chocolious", "pop2", "eggs,flavour, fresh cream, milk, chocolate serum", 25.0, 4, 18, 1500));
        foodlist.add(new FoodDomain("Rainbow Scoop", "pop3", "eggs,flavour, fresh cream, milk, chocolate serum", 30.0, 5, 20, 1000));
        foodlist.add(new FoodDomain("Oreo Cream", "pop4", "eggs,flavour, fresh cream, milk, chocolate serum", 15.0, 5, 20, 1000));
        foodlist.add(new FoodDomain("Cream Stick", "pop5", "eggs,flavour, fresh cream, milk, chocolate serum", 22.0, 5, 20, 1000));

        adapter2 = new RecommendedAdapter(foodlist);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    public void flipImage(int i) {
        ImageView view = new ImageView(this);
        view.setBackgroundResource(i);//which image will be portraited
        v_flipper.addView(view);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);
        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);

    }

    private void recyclerViewCategory() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategotyList = findViewById(R.id.view1);
        recyclerViewCategotyList.setLayoutManager(linearLayoutManager);


        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("Rainbow", "pic8"));
        categoryList.add(new CategoryDomain("Vanilla", "pic2"));
        categoryList.add(new CategoryDomain("Colorly", "pic3"));
        categoryList.add(new CategoryDomain("Cone", "pic4"));
        categoryList.add(new CategoryDomain("Swirl", "pic5"));
        categoryList.add(new CategoryDomain("Bites", "pic6"));
        categoryList.add(new CategoryDomain("Scoop", "pic7"));


        adapter = new CategoryAdapter(categoryList);
        recyclerViewCategotyList.setAdapter(adapter);
    }


    public void Location(View view) {
        //Intent intent=new Intent(MainActivity2.this, Location.class );
        //startActivity(intent);
    }
}
