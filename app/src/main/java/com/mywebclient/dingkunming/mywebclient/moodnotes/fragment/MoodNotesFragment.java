package com.mywebclient.dingkunming.mywebclient.moodnotes.fragment;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.mywebclient.dingkunming.mywebclient.R;
import com.mywebclient.dingkunming.mywebclient.article.adapter.AcrticleAdapter;
import com.mywebclient.dingkunming.mywebclient.article.bean.ResultBeanData;
import com.mywebclient.dingkunming.mywebclient.article.fragment.AcrticleFragment;
import com.mywebclient.dingkunming.mywebclient.base.BaseFragment;
import com.mywebclient.dingkunming.mywebclient.moodnotes.adapter.MoonNoteAdapter;
import com.mywebclient.dingkunming.mywebclient.moodnotes.bean.ResultMoonNote;
import com.mywebclient.dingkunming.mywebclient.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/2/16.
 */

public class MoodNotesFragment extends BaseFragment {
    private static final String TAG = MoodNotesFragment.class.getSimpleName();
     private  TextView textView ;
    private RecyclerView rvAcrticle;
    private List<ResultMoonNote.MoonNotesBean> moonNotes;
    private MoonNoteAdapter adapter;
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_acrticle, null);
        textView = (TextView) view.findViewById(R.id.action_bar_title);
        rvAcrticle = (RecyclerView) view.findViewById(R.id.rv_acrticle);
        initListener();

        return view;
    }

    private void initListener() {

        //暂时无监听时间

    }

    @Override
    public void initDate() {
        super.initDate();
        //textView.setText("MoodNotes Fragment");

        //联网请求主页的数据
        getDataFromNet();
    }

    private void getDataFromNet() {

        String url = Constants.MOONNOTE_URL;
        OkHttpUtils
                .get()
                .url(url)
                //.addParams("","")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"MoonNotes请求失败=="+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,"MoonNotes请求成功=="+response);
                        //解析数据
                        processData(response);
                    }


                });

    }

    private void processData(String response) {


        ResultMoonNote moonNote = JSON.parseObject(response,ResultMoonNote.class);
        moonNotes = moonNote.getMoonNotes();

        if(moonNotes != null){
            //有数据
            //设置适配器
            adapter = new MoonNoteAdapter(mContext,moonNotes);
            rvAcrticle.setAdapter(adapter);
            rvAcrticle.setLayoutManager(new GridLayoutManager(mContext,1));
        }else{
            //无数据
            Log.e(TAG,"MoonNotes无数据---------==");
        }

        for(ResultMoonNote.MoonNotesBean moonNote1:moonNotes){
            Log.e(TAG,"MoonNotes解析成功==="+moonNote1.getMnDate()+"-----"+moonNote1.getMnContent());
        }

    }


}
