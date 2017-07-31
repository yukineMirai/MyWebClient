package com.mywebclient.dingkunming.mywebclient.article.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mywebclient.dingkunming.mywebclient.R;
import com.mywebclient.dingkunming.mywebclient.article.bean.ResultBeanData;
import com.mywebclient.dingkunming.mywebclient.main.activity.ShowActivity;
import com.mywebclient.dingkunming.mywebclient.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/16.
 */

public class AcrticleAdapter extends RecyclerView.Adapter<AcrticleAdapter.ActicleHolder> {


    private final Context mContext;
    private final List<ResultBeanData.AcrticlesBean> resultBean;

    public AcrticleAdapter(Context mContext, List<ResultBeanData.AcrticlesBean> resultBean) {
        this.mContext= mContext;
        this.resultBean = resultBean;
    }

    @Override
    public ActicleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext,R.layout.activity_item,null);

        return new ActicleHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ActicleHolder holder, int position) {

        ResultBeanData.AcrticlesBean acrticlesBean = resultBean.get(position);
        holder.tv_title.setText(acrticlesBean.getTitle());
        holder.tv_content.setText(Html.fromHtml(acrticlesBean.getContent()));
        holder.tv_time.setText(acrticlesBean.getJointime());

    }

    @Override
    public int getItemCount() {
        return resultBean.size();
    }

    class ActicleHolder extends RecyclerView.ViewHolder{
        private ImageView iv_icon;
        private TextView tv_title;
        private TextView tv_content;
        private TextView tv_time;


      public ActicleHolder(View itemView) {
          super(itemView);
          iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
          tv_title = (TextView) itemView.findViewById(R.id.tv_title);
          tv_content = (TextView) itemView.findViewById(R.id.tv_content);
          tv_time = (TextView) itemView.findViewById(R.id.tv_time);

          itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                 // Toast.makeText(mContext,"postion"+getLayoutPosition(),Toast.LENGTH_SHORT).show();
                  Intent intent = new Intent(mContext, ShowActivity.class);
                  intent.putExtra("content",resultBean.get(getLayoutPosition()).getContent());
                  intent.putExtra("title",resultBean.get(getLayoutPosition()).getTitle());
                  mContext.startActivity(intent);
              }
          });

      }
  }
}
