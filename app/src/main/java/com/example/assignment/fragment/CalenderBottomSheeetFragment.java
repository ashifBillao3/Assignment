package com.example.assignment.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.assignment.R;
import com.example.assignment.databinding.FragmentCalenderBottomSheeetBinding;
import com.example.assignment.interFace.OnClickFragmentButton;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class CalenderBottomSheeetFragment extends BottomSheetDialogFragment {
    private FragmentCalenderBottomSheeetBinding binding;
    private SeatsBottomSheetFragment seatsBottomSheetFragment;
    private OnClickFragmentButton onClickFragmentButton;

    public CalenderBottomSheeetFragment(OnClickFragmentButton onClickFragmentButton) {
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

         View view=inflater.inflate(R.layout.fragment_calender_bottom_sheeet, container, false);
        binding= DataBindingUtil.bind(view);
        binding.seatsButton.setOnClickListener(v -> {
           onClickFragmentButton.onClickSeatsButton(true);
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog=getDialog();
        if (dialog!=null){
            View bottomSheet=dialog.findViewById(R.id.calenderbottom_sheet_background);
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