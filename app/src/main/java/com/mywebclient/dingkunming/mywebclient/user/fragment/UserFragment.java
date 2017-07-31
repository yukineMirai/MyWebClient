package com.mywebclient.dingkunming.mywebclient.user.fragment;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.mywebclient.dingkunming.mywebclient.R;
import com.mywebclient.dingkunming.mywebclient.article.adapter.AcrticleFragmentAdapter;
import com.mywebclient.dingkunming.mywebclient.article.bean.ResultBeanData;
import com.mywebclient.dingkunming.mywebclient.article.fragment.AcrticleFragment;
import com.mywebclient.dingkunming.mywebclient.base.BaseFragment;
import com.mywebclient.dingkunming.mywebclient.user.bean.User;
import com.mywebclient.dingkunming.mywebclient.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/2/16.
 */

public class UserFragment extends BaseFragment {
    private static final String TAG = AcrticleFragment.class.getSimpleName();
    private LinearLayout ll_login;
     private  TextView textView ;
    private  TextView tv_title ;
    private EditText et_email;
    private EditText et_password;
    private Button bn_login;
    private boolean  login_static = false;
    User.UsersBean uUser;
    List<User.UsersBean> users ;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_user, null);

        et_email = (EditText) view.findViewById(R.id.et_email);
        et_password = (EditText) view.findViewById(R.id.et_password);
        textView = (TextView) view.findViewById(R.id.tv_login_success);
        tv_title = (TextView) view.findViewById(R.id.action_bar_title1);
        bn_login = (Button) view.findViewById(R.id.bn_logon);
        ll_login = (LinearLayout) view.findViewById(R.id.ll_login);

        initListener();



        return view;
    }

    private void initListener() {

        bn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDataFromNet();
                initDate();
            }
        });
    }

    private void getDataFromNet() {

        String url = Constants.USER_LOGIN;
        OkHttpUtils
                .get()
                .url(url)
                .addParams("email",et_email.getText().toString())
                .addParams("password",et_password.getText().toString())
                .build()
                .execute(new StringCallback() {



                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"首页请求失败=="+e.getMessage());
                        login_static = false;
                        Toast.makeText(mContext,"请检查网络",Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,"首页请求成功=="+response);
                        //解析数据
                        processData(response);
                        initDate();

                    }


                });

    }
    private void processData(String response) {

        User user = JSON.parseObject(response,User.class);

        if (user.getStatus()!=null){
            Log.e(TAG,"user.getStatus()!=null");
        }
        if(user.getStatus() == null){
            Log.e(TAG,"user.getStatus()==null");
        }

        Log.e(TAG,user.getStatus());
        if(user.getStatus().equals("0")||user.getStatus().equals("null")){
            Log.e(TAG,"密码错误");
            Toast.makeText(mContext,"账号或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
            login_static = false;

        }else{

            Log.e(TAG,"OK");
            users = (List<User.UsersBean>) user.getUsers();
            for (User.UsersBean user1 :users){
                uUser = user1;
            }
            login_static = true;
        }
       /* ResultBeanData resultBeanData = JSON.parseObject(response,ResultBeanData.class);
        resultBean = resultBeanData.getAcrticles();


        if(resultBean != null){
            //有数据
            //设置适配器
            adapter = new AcrticleFragmentAdapter(mContext,resultBean);
            rvAcrticle.setAdapter(adapter);
            rvAcrticle.setLayoutManager(new GridLayoutManager(mContext,1));
        }else{
            //无数据
            Log.e(TAG,"无数据---------==");
        }

        for(ResultBeanData.AcrticlesBean acrticle:resultBean){
            Log.e(TAG,"解析成功=="+acrticle.getTitle()+"-----"+acrticle.getJointime());
        }*/


    }

    @Override
    public void initDate() {
        super.initDate();
        tv_title.setText("用户页面");
        Log.e(TAG,login_static+"------------");
        if(login_static){
            ll_login.setVisibility(View.INVISIBLE);
            textView.setText("欢迎你，"+uUser.getEmail());
            textView.setVisibility(View.VISIBLE);
        }else{

        }
    }
}
