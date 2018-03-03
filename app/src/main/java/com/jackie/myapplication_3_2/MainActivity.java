package com.jackie.myapplication_3_2;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager floors;
    private LinearLayout dotsContainer;

    private int[] images = { R.drawable.floor1, R.drawable.floor2 };
    private List<MyImageView> myImageViews;
    private int currentFloorIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floors = (ViewPager) findViewById(R.id.floors);
        dotsContainer = (LinearLayout) findViewById(R.id.dots_container);

        myImageViews = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            final MyImageView myImageView = new MyImageView(this);
            myImageView.setImageDrawable(getDrawable(images[i]));
            final int currentIndex = i;
            myImageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        myImageView.update(event.getX(), event.getY());
                        updateOtherImageViews(currentIndex);
                    }

                    return true;
                }
            });
            myImageViews.add(myImageView);

            ImageView dot = new ImageView(this);
            LinearLayout.LayoutParams dotParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i == currentFloorIndex) {
                dot.setImageDrawable(getResources().getDrawable(R.drawable.dot_selected));
            } else {
                dot.setImageDrawable(getResources().getDrawable(R.drawable.dot_unselected));
            }

            if (i > 0) {
                dotParams.leftMargin = 38;
            }

            dot.setLayoutParams(dotParams);
            dot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floors.setCurrentItem(currentIndex);
                }
            });
            dotsContainer.addView(dot);
        }

        MyPagerAdapter adapter = new MyPagerAdapter(myImageViews);
        floors.setAdapter(adapter);

        floors.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentFloorIndex = position;
                for (int i = 0; i < dotsContainer.getChildCount(); i++) {
                    ImageView dot = (ImageView) dotsContainer.getChildAt(i);
                    if (i == position) {
                        dot.setImageDrawable(getDrawable(R.drawable.dot_selected));
                    } else {
                        dot.setImageDrawable(getDrawable(R.drawable.dot_unselected));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void updateOtherImageViews(int floorIndex) {
        for (int i = 0; i < myImageViews.size(); i++) {
            if (i == floorIndex) {
                continue;
            }

            MyImageView imageView = myImageViews.get(i);
            imageView.update(0, 0);
        }
    }
}
