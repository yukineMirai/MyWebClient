package com.mywebclient.dingkunming.mywebclient.message.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mywebclient.dingkunming.mywebclient.base.BaseFragment;

/**
 * Created by Administrator on 2017/2/16.
 */

public class MessageFragment extends BaseFragment {
     private  TextView textView ;
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextSize(30);
        textView.setTextColor(Color.BLUE);
        textView.setGravity(Gravity.CENTER);

        return textView;
    }

    @Override
    public void initDate() {
        super.initDate();
        textView.setText("message Fragment");
    }
}
