package com.example.assignment.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment.R;
import com.example.assignment.adapter.AgeAdaptor;
import com.example.assignment.adapter.ChildrenAdaptor;
import com.example.assignment.databinding.FragmentSeatsBottomSheetBinding;
import com.example.assignment.interFace.OnClickFragmentButton;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;


public class SeatsBottomSheetFragment extends BottomSheetDialogFragment {
    private AgeAdaptor ageAdaptor;
    private ChildrenAdaptor childrenAdaptor;
    private List<String> ageList;
    private List<String> childrenList;
    private FragmentSeatsBottomSheetBinding binding;
    private OnClickFragmentButton onClickFragmentButton;

    public SeatsBottomSheetFragment(List<String> ageList, List<String> childrenList, OnClickFragmentButton onClickFragmentButton) {
        this.ageList = ageList;
        this.childrenList = childrenList;
        this.onClickFragmentButton = onClickFragmentButton;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_seats_bottom_sheet, container, false);
         binding= DataBindingUtil.bind(view);
         ageAdaptor=new AgeAdaptor(ageList,requireActivity());
         binding.ageViewPager.setAdapter(ageAdaptor);
         childrenAdaptor=new ChildrenAdaptor(childrenList,requireActivity());
         binding.childrenViewPager.setAdapter(childrenAdaptor);

        binding.ageViewPager.setClipToPadding(false);
        binding.ageViewPager.setClipChildren(false);
        binding.ageViewPager.setOffscreenPageLimit(3);
        binding.ageViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        binding.childrenViewPager.setClipToPadding(false);
        binding.childrenViewPager.setClipChildren(false);
        binding.childrenViewPager.setOffscreenPageLimit(3);
        binding.childrenViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.85f + r *0.15f);
            }
        });
        binding.ageViewPager.setPageTransformer(compositePageTransformer);
        binding.childrenViewPager.setPageTransformer(compositePageTransformer);

        binding.payButton.setOnClickListener(v -> {
            onClickFragmentButton.onClickPayButton(true);
        });
         return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog=getDialog();
        if (dialog!=null){
            View bottomSheet=dialog.findViewById(R.id.seats_bottom_sheet);
            bottomSheet.getLayoutParams().height=ViewGroup.LayoutParams.MATCH_PARENT;
        }
        View view=getView();
        view.post(()->{
            View parent= (View) view.getParent();
            CoordinatorLayout.LayoutParams params=(CoordinatorLayout.LayoutParams)(parent).getLayoutParams();
            CoordinatorLayout.Behavior behavior=params.getBehavior();
            BottomSheetBehavior bottomSheetBehavior= (BottomSheetBehavior) behavior;
            bottomSheetBehavior.setPeekHeight(view.getMeasuredHeight()-100);
        });
    }
}