package com.unity3d.start;

import android.app.Dialog;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.njsr.mnhtfj.nearme.gamecenter.R;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    private View mContentView;
    private Dialog mLoadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layout = setContentViewId();
        if (mContentView == null)
        mContentView = inflater.inflate(layout, container, false);
        initBaseView();
        initView();
        initData();
        return mContentView;
    }

    /**
     * 通过控件ID查找控件
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T findView(int id){
        return (T)mContentView.findViewById(id);
    }

    protected abstract void initView();
    protected abstract void initData();
    /**
     * 设置内容布局ID
     * @return
     */
    protected abstract int setContentViewId();

    private void initBaseView(){
        mLoadingDialog = new Dialog(getActivity(), R.style.DownloadAppDialog);
      /*  CircularProgress cp = new CircularProgress(this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = DimenUtils.dip2px(this,50);
        lp.height = DimenUtils.dip2px(this,50);
        lp.gravity = Gravity.CENTER;
        cp.setLayoutParams(lp);*/
        mLoadingDialog.setContentView(R.layout.layout_loading_dialog);
//        mLoadingDialog.setMessage(getString(R.string.loading));
    }

    protected void showLoadingDialog(){
        mLoadingDialog.show();
    }

    protected void dismissLoadingDialog(){
        mLoadingDialog.dismiss();
    }
 }