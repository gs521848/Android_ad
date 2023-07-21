package com.unity3d.start;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import androidx.annotation.Nullable;

import com.njsr.mnhtfj.nearme.gamecenter.R;


public class UserPrivacyFragment extends BaseFragment {
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater
            , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView() {

        textView = findView(R.id.tv_user_privacy);
        findView(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    @Override
    protected void initData() {
        Bundle arg = getArguments();
        if (arg != null){
            int strId = arg.getInt("res_id");
            textView.setText(strId);
        }
    }

    @Override
    protected int setContentViewId() {
        return R.layout.frg_privacy_user;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
