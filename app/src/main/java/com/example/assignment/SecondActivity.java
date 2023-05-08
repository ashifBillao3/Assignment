package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.assignment.databinding.ActivitySecondBinding;
import com.example.assignment.fragment.BufferBottomSheetFragment;
import com.example.assignment.fragment.CalenderBottomSheeetFragment;
import com.example.assignment.fragment.DoneBottomSeetFragment;
import com.example.assignment.fragment.SeatsBottomSheetFragment;
import com.example.assignment.interFace.OnClickFragmentButton;
import com.google.android.material.bottomsheet.BottomSheetBehavior;


import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener, OnClickFragmentButton {
    private ActivitySecondBinding binding;
    private BufferBottomSheetFragment bufferBottomSheetFragment;
    private CalenderBottomSheeetFragment calenderBottomSheeetFragment;
    private SeatsBottomSheetFragment seatsBottomSheetFragment;
    private List<String> ageList=new ArrayList<>();
    private List<String> childrenList=new ArrayList<>();
    private DoneBottomSeetFragment doneBottomSeetFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(SecondActivity.this,R.layout.activity_second);

        ageList.add("1");
        ageList.add("2");
        ageList.add("3");
        ageList.add("4");
        ageList.add("5");
        ageList.add("6");
        ageList.add("7");
        ageList.add("8");
        ageList.add("9");
        ageList.add("10");
        childrenList.add("1");
        childrenList.add("2");
        childrenList.add("3");
        childrenList.add("4");
        childrenList.add("5");
        childrenList.add("6");
        childrenList.add("7");
        childrenList.add("8");
        childrenList.add("9");
        childrenList.add("10");

        String imageString=getIntent().getStringExtra("drawable");
        Glide.with(this).load(imageString).apply(RequestOptions.bitmapTransform(new BlurTransformation())).into(binding.backgroundImage);

        bufferBottomSheetFragment=new BufferBottomSheetFragment();
        RelativeLayout relativeLayout=findViewById(R.id.buffer_bottom_sheet);
        BottomSheetBehavior<RelativeLayout> bottomSheetBehavior=BottomSheetBehavior.from(relativeLayout);
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);

        calenderBottomSheeetFragment=new CalenderBottomSheeetFragment(SecondActivity.this);
        RelativeLayout relativeLayout1=findViewById(R.id.calenderbottom_sheet_background);
        BottomSheetBehavior<RelativeLayout> behavior=BottomSheetBehavior.from(relativeLayout1);
        behavior.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);

        seatsBottomSheetFragment=new SeatsBottomSheetFragment(ageList,childrenList,SecondActivity.this);
        RelativeLayout relativeLayout2=findViewById(R.id.calenderbottom_sheet_background);
        BottomSheetBehavior<RelativeLayout> behavior2=BottomSheetBehavior.from(relativeLayout2);
        behavior2.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);

        doneBottomSeetFragment=new DoneBottomSeetFragment(SecondActivity.this);
        RelativeLayout relativeLayout3=findViewById(R.id.buffer_bottom_sheet);
        BottomSheetBehavior<RelativeLayout> bottomSheetBehavior1=BottomSheetBehavior.from(relativeLayout3);
        bottomSheetBehavior1.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);

        binding.backButton.setOnClickListener(this);
        binding.tripButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button:
                finish();
                break;
            case R.id.trip_button:
                bufferBottomSheetFragment.show(getSupportFragmentManager(),bufferBottomSheetFragment.getTag());
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bufferBottomSheetFragment.dismiss();
                        calenderBottomSheeetFragment.show(getSupportFragmentManager(),calenderBottomSheeetFragment.getTag());
                    }
                },3000);
                break;
        }
    }

    @Override
    public void onClickSeatsButton(boolean click) {
        if (click){
            calenderBottomSheeetFragment.dismiss();
            seatsBottomSheetFragment.show(getSupportFragmentManager(),seatsBottomSheetFragment.getTag());
            click=false;
        }
    }

    @Override
    public void onClickPayButton(boolean click) {
        if (click){
            seatsBottomSheetFragment.dismiss();
            doneBottomSeetFragment.show(getSupportFragmentManager(),doneBottomSeetFragment.getTag());
            click=false;
        }

    }

    @Override
    public void onCLickDoneButton(boolean click) {
        doneBottomSeetFragment.dismiss();
    }
}