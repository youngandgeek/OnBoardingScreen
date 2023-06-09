package com.example.onboardingscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    //array of images from drawable folder that'll display in view pager
    int images[]={
    R.drawable.image_one,
    R.drawable.image_two,
    R.drawable.img_three

    };
    //array of headings from @strings folder that'll display in the view pager
    int heading[]={

      R.string.heading_one,
      R.string.heading_two,
      R.string.heading_three
    };

    int desc[]={
       R.string.desc_one,
       R.string.desc_two,
       R.string.desc_three

    };
    //constructor with context

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(LinearLayout)object;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slider_layout,container,false);
        //declare views from slider_layout.xml
        ImageView slideTitleImg=view.findViewById(R.id.title_image);
        TextView slideTitleText=view.findViewById(R.id.text_title);
        TextView slideDescText=view.findViewById(R.id.text_desc);
        //set layout from arrayName[position]
        slideTitleImg.setImageResource(images[position]);
        slideTitleText.setText(heading[position]);
        slideDescText.setText(desc[position]);

        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);

    }
}
