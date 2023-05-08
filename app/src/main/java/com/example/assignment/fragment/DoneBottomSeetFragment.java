package com.example.assignment.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment.R;
import com.example.assignment.databinding.FragmentDoneBottomSeetBinding;
import com.example.assignment.interFace.OnClickFragmentButton;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class DoneBottomSeetFragment extends BottomSheetDialogFragment {
    private FragmentDoneBottomSeetBinding binding;
    private OnClickFragmentButton onClickFragmentButton;

    public DoneBottomSeetFragment(OnClickFragmentButton onClickFragmentButton) {
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
        View view= inflater.inflate(R.layout.fragment_done_bottom_seet, container, false);
        binding= DataBindingUtil.bind(view);
        binding.doneButton.setOnClickListener(v -> {
            onClickFragmentButton.onCLickDoneButton(true);
        });
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog=getDialog();
        if (dialog!=null){
            View bottomSheet=dialog.findViewById(R.id.done_fragment);
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