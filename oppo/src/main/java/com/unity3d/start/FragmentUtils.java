package com.unity3d.start;

//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentUtils {
	

    /**
     * 将一个Fragment加入到activity中
     * @param fm
     * @param fragment
     * @param resId
     */
    public static void add(FragmentManager fm, Fragment fragment, int resId){
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(resId, fragment);
        ft.commitAllowingStateLoss();
    }

    /**
     * 将一个Fragment从一个activity中移除
     * @param fm
     * @param fragment
     */
    public static void removePage(FragmentManager fm, Fragment fragment){
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(fragment);
        ft.commitAllowingStateLoss();
    }

    /**
     * 显示一个已载入activity的fragment
     * @param fm
     * @param fragment
     */
    public static void show(FragmentManager fm, Fragment fragment){
        FragmentTransaction ft = fm.beginTransaction();
        if (fragment.isAdded()){
            ft.show(fragment);
        }
        ft.commitAllowingStateLoss();
    }

    /**
     * 隐藏一个已载入activity的fragment
     * @param fm
     * @param fragment
     */
    public static void hide(FragmentManager fm, Fragment fragment){
        FragmentTransaction ft = fm.beginTransaction();
        if (fragment.isAdded()){
            ft.hide(fragment);
        }
        ft.commitAllowingStateLoss();
    }
    
    public static void replace(FragmentManager fm ,Fragment fragment,int resId){
    	
    	FragmentTransaction ft = fm.beginTransaction();
        //ft.setCustomAnimations(R.anim.push_right_in, R.anim.push_right_out,R.anim.push_right_in,R.anim.push_right_out);
    	ft.replace(resId, fragment);
    	ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    	ft.addToBackStack(null);
    	ft.commitAllowingStateLoss();
  }
    public static void removeReplaceFragment(FragmentManager fm,Fragment fragment){

        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(fragment);
        ft.commitAllowingStateLoss();

    }


}
