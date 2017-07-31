package com.mywebclient.dingkunming.mywebclient.article.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mywebclient.dingkunming.mywebclient.R;
import com.mywebclient.dingkunming.mywebclient.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2017/2/16.
 */

public class ChannelAdapter extends BaseAdapter {
    private final   List<String> list;
    private final Context mContext;

    public ChannelAdapter(Context mContext, List<String> list) {

        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = View.inflate(mContext, R.layout.item_channel,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_channel);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_channel);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        String imageUrl = "bg("+(position+100)+").jpg";
        //Glide.with(mContext).load(Constants.BASE_URL_IMAGE+channelInfoBean.getImage()).into(viewHolder.iv_icon );
         Glide.with(mContext).load(Constants.BASE_URL_IMAGE+imageUrl).into(viewHolder.iv_icon);
        //viewHolder.iv_icon.setImageBitmap(Bitmap.createBitmap(R.mipmap.ic_launcher));
        viewHolder.tv_title.setText(list.get(position));


        return convertView;
    }
    static class ViewHolder {
        ImageView iv_icon;
        TextView tv_title;
    }
}
