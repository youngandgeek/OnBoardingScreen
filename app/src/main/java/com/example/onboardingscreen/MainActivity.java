package com.example.onboardingscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ViewPager slideViewPager;
    private LinearLayout linearLayout;
    private AppCompatButton skipBtn, nextBtn, backBtn;
    ViewPagerAdapter viewPagerAdapter;
    //to indicate page number
    TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**call the views initialization method**/
        init();


        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginInten = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginInten);
                finish();

            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getItem(0) < 4) {
                    slideViewPager.setCurrentItem(1, true);
                } else {
                    Intent loginInten = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(loginInten);
                    finish();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getItem(0) > 0) {
                    slideViewPager.setCurrentItem(-1, true);
                }

            }
        });
        slideViewPager = findViewById(R.id.slideViewPager);
        linearLayout = findViewById(R.id.indicator_layout);
        viewPagerAdapter = new ViewPagerAdapter(this);
        slideViewPager.setAdapter(viewPagerAdapter);
        setUpIndicator(0);
        slideViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setUpIndicator(position);
                if (position > 0) {
                    backBtn.setVisibility(View.VISIBLE);

                } else backBtn.setVisibility(View.INVISIBLE);
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    //set 2 dots indicator to the slider
    public void setUpIndicator(int position) {
        dots = new TextView[3];
        linearLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                dots[i].setTextColor(getResources().getColor(R.color.md_theme_dark_onErrorContainer, getApplicationContext().getTheme()));
            }
            linearLayout.addView(dots[i]);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dots[position].setTextColor(getResources().getColor(R.color.light_green, getApplicationContext().getTheme()));
        }


    }

    //return the current item displayed in viewpager
    private int getItem(int i) {
        return slideViewPager.getCurrentItem() + i;
    }

    //views initialization method
    private void init() {
        skipBtn = findViewById(R.id.skip_btn);
        nextBtn = findViewById(R.id.next_btn);
        backBtn = findViewById(R.id.back_btn);

    }
}