package com.bksx.mobile.common.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.bksx.mobile.common.R;

/**
 * @author :qlf
 */
public class CustomToolbar extends Toolbar {
    private View view;
    private ImageView mTvMainTitleLeft;
    private TextView mTvMainTitle;

    public CustomToolbar(@NonNull Context context) {
        super(context);
    }

    public CustomToolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomToolbar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        if (view == null) {
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            view = mInflater.inflate(R.layout.custom_toolbar, null);

            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            //如果没有这行代码，title不会居中显示

            addView(view,layoutParams);
        }
    }




    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTvMainTitleLeft = findViewById(R.id.imageView_back);
        mTvMainTitle = findViewById(R.id.textView_title);
    }

    //设置主title的内容
    public void setMainTitle(String text) {
        this.setTitle(" ");
        mTvMainTitle.setVisibility(View.VISIBLE);
        mTvMainTitle.setText(text);
    }

    //设置主title的内容文字的颜色
    public void setMainTitleColor(int color) {
        mTvMainTitle.setTextColor(color);
    }

    //设置title左边文字
    public void setMainTitleLeftText(String text) {
        mTvMainTitleLeft.setVisibility(View.VISIBLE);
       // mTvMainTitleLeft.setText(text);
    }

    //设置title左边文字颜色
    public void setMainTitleLeftColor(int color) {
      //  mTvMainTitleLeft.setTextColor(color);
    }

    //设置title左边图标
    public void setMainTitleLeftDrawable(int res) {
        Drawable dwLeft = ContextCompat.getDrawable(getContext(), res);
        dwLeft.setBounds(0, 0, dwLeft.getMinimumWidth(), dwLeft.getMinimumHeight());
       // mTvMainTitleLeft.setCompoundDrawables(dwLeft, null, null, null);
    }

    //设置title右边文字
    public void setMainTitleRightText(String text) {
       // mTvMainTitleRight.setVisibility(View.VISIBLE);
       // mTvMainTitleRight.setText(text);
    }

    //设置title右边文字颜色
    public void setMainTitleRightColor(int color) {
      //  mTvMainTitleRight.setTextColor(color);
    }

    //设置title右边图标
    public void setMainTitleRightDrawable(int res) {
        Drawable dwRight = ContextCompat.getDrawable(getContext(), res);
        dwRight.setBounds(0, 0, dwRight.getMinimumWidth(), dwRight.getMinimumHeight());
       // mTvMainTitleRight.setCompoundDrawables(null, null, dwRight, null);
    }

    //设置toolbar状态栏颜色
    public void setToolbarBackground(int res) {
        this.setBackgroundResource(res);
    }

    //设置toolbar左边图标
    public void setToolbarLeftBackImageRes(int res) {
        this.setNavigationIcon(res);
    }

    //设置toolbar左边文字
    public void setToolbarLeftText(String text) {
        this.setNavigationContentDescription(text);
    }

    //设置toolbar的标题
    public void setToolbarTitle(String text) {
        this.setTitle(text);
    }

    //设置toolbar标题的颜色
    public void setToolbarTitleColor(int color) {
        this.setTitleTextColor(color);
    }

    //设置toolbar子标题
    public void setToolbarSubTitleText(String text) {
        this.setSubtitle(text);
    }

    //设置toolbar子标题颜色
    public void setToolbarSubTitleTextColor(int color) {
        this.setSubtitleTextColor(color);
    }
}
