package com.mywebclient.dingkunming.mywebclient.article.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.mywebclient.dingkunming.mywebclient.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/2/16.
 */
public class ChannelViewHolder extends RecyclerView.ViewHolder {

    private  Context mContext;
    private GridView mGridView;
    private ChannelAdapter  adapter;

    public ChannelViewHolder(final Context mContext, View itemView) {
        super(itemView);
        this.mContext = mContext;
        mGridView = (GridView) itemView.findViewById(R.id.gv_channel);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,"position:"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setData() {
        List<String> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add("test"+i);
        }
        adapter = new ChannelAdapter(mContext,list);
        mGridView.setAdapter(adapter);

    }



    /*public static class ChannelAdapter extends BaseAdapter{

        private  List<String> list;
        private  Context mContext;

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
             convertView = View.inflate(mContext,R.layout.item_channel,null);
             viewHolder = new ViewHolder();
             viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_channel);
             viewHolder.textView = (TextView) convertView.findViewById(tv_channel);
         }else {
             convertView = (View) convertView.getTag();
         }
            String imageUrl = "bg("+(position+100)+").jpg";
            Glide.with(mContext).load(Constants.BASE_URL_IMAGE+imageUrl).into(viewHolder.imageView);
            viewHolder.textView.setText(list.get(position));


            return convertView;
        }
         static class ViewHolder{
            ImageView imageView;
            TextView textView;
        }
    }*/
}
