package com.example.assignment.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.assignment.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class BufferBottomSheetFragment extends BottomSheetDialogFragment {
    public BufferBottomSheetFragment() {
        // Required empty public constructor
    }

    public static BufferBottomSheetFragment newInstance() {
        BufferBottomSheetFragment fragment = new BufferBottomSheetFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_buffer_bottom_sheet, container, false);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog=getDialog();
        if (dialog!=null){
            View bottomSheet=dialog.findViewById(R.id.buffer_bottom_sheet);
            bottomSheet.getLayoutParams().height=ViewGroup.LayoutParams.MATCH_PARENT;
        }
        View view=getView();
        view.post(()->{
           View parent= (View) view.getParent();
           CoordinatorLayout.LayoutParams params=(CoordinatorLayout.LayoutParams)(parent).getLayoutParams();
           CoordinatorLayout.Behavior behavior=params.getBehavior();
           BottomSheetBehavior bottomSheetBehavior= (BottomSheetBehavior) behavior;
           bottomSheetBehavior.setPeekHeight(view.getMeasuredHeight());
        });
    }
}