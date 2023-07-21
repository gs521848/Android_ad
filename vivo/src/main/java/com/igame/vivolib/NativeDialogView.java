package com.igame.vivolib;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class NativeDialogView
        extends RelativeLayout
{
    public static final int TEXTVIEW_ID_LOGO = 20011;
    public static final int IMAGE_ID = 19989;
    public static final int IMAGE_ID_LOGO = 19999;
    public static final int DIALOG_ID = 19988;
    Context context;
    private RelativeLayout closeLayout;
    private ImageView closeIcon,adLogo;
    private TextView tvTitle,tvAdLogo;
    private ImageView adImage;
    private ImageView icon;
    private boolean isInstall;
    private ImageView adIcon;

    public NativeDialogView(Context context, boolean isInstall)
    {
        super(context);
        this.context = context;
        this.isInstall = isInstall;
        init(null);
    }

    public NativeDialogView(Context context, AttributeSet attr)
    {
        super(context, attr);
        this.context = context;
        init(attr);
    }

    private void init(AttributeSet attributeSet)
    {
        LayoutParams lp = new LayoutParams(-2, -2);
        int padding = DimenUtils.dip2px(context,10);
//        setPadding((int) (padding ),padding, (int) (padding),padding);

//        setBackgroundColor(0xffffffff);
        setLayoutParams(lp);
        initAdImage();
        initCloseIcon();
        initAdLogo();
        initTitle();
        resizeOnAddWindow();
    }
    private void initAdLogo(){
        tvAdLogo = new TextView(context);
        tvAdLogo.setId(TEXTVIEW_ID_LOGO);
        tvAdLogo.setGravity(Gravity.CENTER);
        tvAdLogo.setBackgroundColor(0x22000000);
        tvAdLogo.setTextColor(0xffffffff);

        adLogo = new ImageView(context);

        adLogo.setScaleType(ImageView.ScaleType.FIT_XY);
        adLogo.setId(IMAGE_ID_LOGO);

        LayoutParams lp = new LayoutParams(DimenUtils.dip2px(context
                ,50)
                , DimenUtils.dip2px(context,20));
        lp.addRule(ALIGN_RIGHT, IMAGE_ID);
        lp.addRule(ALIGN_BOTTOM, IMAGE_ID);
        addView(adLogo,lp);
        addView(tvAdLogo,lp);
    }

    private void initCloseIcon()
    {
        closeLayout = new RelativeLayout(this.context);
        this.closeIcon = new ImageView(this.context);

        LayoutParams lp = new LayoutParams(DimenUtils.dip2px(context,isInstall ? 40 : 60)
                , DimenUtils.dip2px(context,isInstall ? 40 : 60));

        lp.addRule(ALIGN_RIGHT, IMAGE_ID);
        lp.addRule(ALIGN_TOP, IMAGE_ID);
        closeLayout.setLayoutParams(lp);
//        closeLayout.setBackgroundColor(0xff000000);
//        lp.rightMargin = DimenUtils.dip2px(context,5);
//        lp.topMargin = DimenUtils.dip2px(context,5);

        LayoutParams llp = new LayoutParams(DimenUtils.dip2px(context,35)
                , DimenUtils.dip2px(context,35));
        this.closeIcon.setLayoutParams(llp);
        llp.addRule(ALIGN_PARENT_RIGHT);
        llp.addRule(ALIGN_PARENT_TOP);
//        llp.rightMargin = DimenUtils.dip2px(context,5);
//        llp.topMargin = DimenUtils.dip2px(context,5);
//        closeIcon.setBackgroundColor(0xff000000);
        this.closeIcon.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_menu_close_clear_cancel));
//        closeIcon.setBackgroundDrawable(AssetsUtil.getInstance(context).getDrawable("btn_back_hemeng_sdk.png"));
        //17301560
        closeLayout.addView(closeIcon);
        addView(this.closeLayout);
    }

    private void initAdImage()
    {
        this.adImage = new ImageView(this.context);
        adImage.setBackgroundColor(0xffffffff);
        LayoutParams ivLp = new LayoutParams(DimenUtils.dip2px(context,350)
                , DimenUtils.dip2px(context,220));
        ivLp.addRule(CENTER_HORIZONTAL);
        this.adImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.adImage.setLayoutParams(ivLp);
        this.adImage.setId(IMAGE_ID);
        addView(this.adImage);
    }

    private void initTitle()
    {

//        if (this.adIcon == null)
//        {
//            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(DimenUtils.dip2px(context,30)
//                    , DimenUtils.dip2px(context,15));
//            this.adIcon = new ImageView(this.context);
//            this.adIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);
////            this.adIcon.setBackgroundDrawable(AssetsUtil.getInstance(context).getDrawable("pic_hemeng_guanggao.png"));
//            lp.addRule(ALIGN_PARENT_RIGHT);
//            lp.addRule(ALIGN_PARENT_BOTTOM);
//            addView(this.adIcon, lp);
//        }

        tvTitle = new TextView(this.context);
        tvTitle.setText("");
        tvTitle.setBackgroundColor(0xffffffff);
        tvTitle.setTextColor(0xff575757);
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
        int padding = DimenUtils.dip2px(context,8);
        tvTitle.setPadding(padding,padding,padding,padding);
        LayoutParams lp = new LayoutParams(DimenUtils.dip2px(context,350)
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(BELOW,IMAGE_ID);
        lp.addRule(CENTER_HORIZONTAL);

        addView(this.tvTitle,lp);
    }
    public void resizeOnAddWindow(){
//        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//
//                getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                if (getLayoutParams() instanceof FrameLayout.LayoutParams){
//                    ((FrameLayout.LayoutParams) getLayoutParams()).gravity = Gravity.CENTER;
//                }
//                if (getLayoutParams() instanceof RelativeLayout.LayoutParams){
//                    ((LayoutParams) getLayoutParams()).addRule(CENTER_IN_PARENT);
//                }
//                getLayoutParams().width = ScreenUtils.getScreenWidth(context);
//                getLayoutParams().height = (int) (adImage.getHeight() * 1.1f);
//                requestLayout();
//            }
//        });
    }
    public ImageView getAdIcon()
    {
//        try
//        {
//            if (this.adIcon == null)
//            {
//                RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(75, 39);
//                this.adIcon = new ImageView(this.context);
//                this.adIcon.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                this.adIcon.setImageDrawable(AssetsUtil.getInstance(context).getDrawable("pic_hemeng_guanggao.png"));
//                localLayoutParams.addRule(9);
//                addView(this.adIcon, localLayoutParams);
//            }
//            return this.adIcon;
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
        return null;
    }

    public ImageView getIcon()
    {
        try
        {
            if (this.icon == null)
            {
                LayoutParams localLayoutParams = new LayoutParams(50, 50);
                this.icon = new ImageView(this.context);
                this.icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
                localLayoutParams.addRule(9);
                localLayoutParams.leftMargin = 80;
                addView(this.icon, localLayoutParams);
            }
            return this.icon;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public View getCloseLayout()
    {
        return this.closeLayout;
    }

    public TextView getTextView()
    {
        return this.tvTitle;
    }

    public ImageView getAdimageView()
    {
        return this.adImage;
    }

    public void resizeAdImageHeight(final int width,final int height) {
//        if (ScreenUtils.getScreenWidth(context) > ScreenUtils.getScreenHeight(context)){
//            resizeOnAddWindow();
//            return;
//        }
//        float scale = (float)width / height;
//        if (adImage.getLayoutParams().width < 0){
//            if (ScreenUtils.getScreenWidth(context) > ScreenUtils.getScreenHeight(context)){
//                adImage.getLayoutParams().width = (int) (ScreenUtils.getScreenHeight(context));
//            }else{
//                float horizontalScale = height > width ? 0.85f : 0.97f;
//                adImage.getLayoutParams().width = (int) (ScreenUtils.getScreenWidth(context)*horizontalScale) ;
//            }
//
//        }
//        int w = adImage.getLayoutParams().width ;
//        int newH = (int) (w / scale)  ;
//        adImage.getLayoutParams().height = newH;
//        resizeOnAddWindow();


    }

}
