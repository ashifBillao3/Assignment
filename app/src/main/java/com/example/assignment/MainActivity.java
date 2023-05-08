package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.example.assignment.adapter.ViewPagerAdapter;
import com.example.assignment.databinding.ActivityMainBinding;
import com.example.assignment.interFace.OnClickViewPagerButtons;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  OnClickViewPagerButtons {
    private ViewPagerAdapter viewPagerAdapter;
    private List<String> images=new ArrayList<>();
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);

        images.add("https://images.pexels.com/photos/2892012/pexels-photo-2892012.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
        images.add("https://images.pexels.com/photos/441596/pexels-photo-441596.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
        images.add("https://images.pexels.com/photos/3750759/pexels-photo-3750759.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
        images.add("https://images.pexels.com/photos/3415629/pexels-photo-3415629.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");


        viewPagerAdapter=new ViewPagerAdapter(images,MainActivity.this,MainActivity.this);
        binding.viewPagerImage.setAdapter(viewPagerAdapter);

        binding.viewPagerImage.setClipToPadding(false);
        binding.viewPagerImage.setClipChildren(false);
        binding.viewPagerImage.setOffscreenPageLimit(3);
        binding.viewPagerImage.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.85f + r *0.15f);
            }
        });
        binding.viewPagerImage.setPageTransformer(compositePageTransformer);

    }



    @Override
    public void onClickViePageAndBtcButton(String image) {
        Intent intent=new Intent(MainActivity.this,SecondActivity.class);
        intent.putExtra("drawable",image);
        startActivity(intent);
        overridePendingTransition(R.anim.zoom_in,R.anim.zoom_out);

    }
}