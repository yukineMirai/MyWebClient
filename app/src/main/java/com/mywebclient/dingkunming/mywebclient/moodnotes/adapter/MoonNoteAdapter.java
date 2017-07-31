package com.mywebclient.dingkunming.mywebclient.moodnotes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mywebclient.dingkunming.mywebclient.R;
import com.mywebclient.dingkunming.mywebclient.article.adapter.AcrticleAdapter;
import com.mywebclient.dingkunming.mywebclient.main.activity.ShowActivity;
import com.mywebclient.dingkunming.mywebclient.moodnotes.bean.ResultMoonNote;
import com.mywebclient.dingkunming.mywebclient.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class MoonNoteAdapter extends RecyclerView.Adapter<MoonNoteAdapter.MoonNoteHolder> {


    private final Context mContext;
    private final List<ResultMoonNote.MoonNotesBean> moonNotes;

    public MoonNoteAdapter(Context mContext, List<ResultMoonNote.MoonNotesBean> moonNotes) {
        this.mContext = mContext;
        this.moonNotes = moonNotes;
    }



    @Override
    public MoonNoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext,R.layout.activity_item,null);

        return new MoonNoteHolder(itemView);
    }



    @Override
    public void onBindViewHolder(MoonNoteHolder holder, int position) {

        //取得当前位置数据
        ResultMoonNote.MoonNotesBean moonNotesBean = moonNotes.get(position);
        holder.tv_content.setText(moonNotesBean.getMnContent());
        holder.tv_time.setText(moonNotesBean.getMnDate());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE).into(holder.iv_icon);
    }

    @Override
    public int getItemCount() {
        return moonNotes.size();
    }

    class MoonNoteHolder extends RecyclerView.ViewHolder {
        private ImageView iv_icon;
        private TextView tv_title;
        private TextView tv_content;
        private TextView tv_time;


        public MoonNoteHolder(View itemView) {
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
                    intent.putExtra("content",moonNotes.get(getLayoutPosition()).getMnContent());
                    intent.putExtra("title","MoonNote");
                    mContext.startActivity(intent);
                }
            });

        }
    }

}
