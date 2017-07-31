package com.mywebclient.dingkunming.mywebclient.main.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.mywebclient.dingkunming.mywebclient.R;
import com.mywebclient.dingkunming.mywebclient.acrticletype.fragment.AcrticleTypeFragment;
import com.mywebclient.dingkunming.mywebclient.article.fragment.AcrticleFragment;
import com.mywebclient.dingkunming.mywebclient.base.BaseFragment;
import com.mywebclient.dingkunming.mywebclient.message.fragment.MessageFragment;
import com.mywebclient.dingkunming.mywebclient.moodnotes.fragment.MoodNotesFragment;
import com.mywebclient.dingkunming.mywebclient.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.framelayout)
    FrameLayout framelayout;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;

    private ArrayList<BaseFragment> fragments;
    private int position = 0;
    private Fragment tempFragemnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initFragment();
        initListener();
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;
                        break;
                    case R.id.rb_cart:
                        position = 3;
                        break;
                    case R.id.rb_user:
                        position = 4;
                        break;

                }
                //根据位置取不同的Fragment
                BaseFragment baseFragment = getFragment(position);
                switchFragment(tempFragemnt, baseFragment);
            }
        });
        rgMain.check(R.id.rb_home);
    }

    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {

        if (tempFragemnt != nextFragment) {
            tempFragemnt = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    //添加Fragment
                    transaction.add(R.id.framelayout, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }


            }
        }
    }


    private BaseFragment getFragment(int position) {

        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;


    }


    private void initFragment() {

        fragments = new ArrayList<>();
        fragments.add(new AcrticleFragment());
        fragments.add(new AcrticleTypeFragment());
        fragments.add(new MoodNotesFragment());
        fragments.add(new MessageFragment());
        fragments.add(new UserFragment());
    }
}
