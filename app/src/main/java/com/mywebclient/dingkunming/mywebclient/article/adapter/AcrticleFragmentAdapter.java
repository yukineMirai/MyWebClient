package com.mywebclient.dingkunming.mywebclient.article.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mywebclient.dingkunming.mywebclient.R;
import com.mywebclient.dingkunming.mywebclient.article.bean.ResultBeanData;
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

public class AcrticleFragmentAdapter extends RecyclerView.Adapter {

    public static final int TEST_ONE = 0;
    public static final int TEST_TWO = 1;
    public static final int TEST_THERE = 2;
    public static final int TEST_FOR = 3;
    public static final int TEST_FIVE = 4;
    private  LayoutInflater layout;
    private  Context mContext;
    private  List<ResultBeanData.AcrticlesBean> acrticles;

    private int currentType = TEST_ONE;

    public AcrticleFragmentAdapter(Context mContext, List<ResultBeanData.AcrticlesBean> acrticles) {
               this.mContext= mContext;
               this.acrticles = acrticles;
        layout = LayoutInflater.from(mContext);
    }

    class BunnerViewHolder extends RecyclerView.ViewHolder{

        private  Context mContext;

        private Banner banner;

        public BunnerViewHolder(Context mContext, View itemView) {
            super(itemView);

            this.mContext = mContext;
            this.banner = (Banner) itemView.findViewById(R.id.banner);

        }

        public void setData() {

            List<String> imagesUrl = new ArrayList<>();
            for (int i=1;i<=4;i++){
                String imageUrl = "bg("+i+").jpg";
                imagesUrl.add(imageUrl);
            }
            //设置循环指示
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {

                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE+url).into(view);
                }
            });
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext,position+"被点击了",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ViewPager act_viewpager;
        private List<String> act_info;

        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            act_viewpager = (ViewPager) itemView.findViewById(R.id.act_viewpager);

            act_info = new ArrayList<>();
            for( int i= 1;i < 5; i++){
                String imageUrl = "bg("+(i+100)+").jpg";
                act_info.add(imageUrl);
            }
        }

        public void setData() {
            act_viewpager.setPageMargin(20);//设置页与页之间的间距
            act_viewpager.setOffscreenPageLimit(3);//>=3


            //act_viewpager.setPageTransformer();
            //setPageTransformer 决定动画效果
            /*act_viewpager.setPageTransformer(true, new
                    ScaleInTransformer());*/
            //1.有数据了
            //2.设置适配器
            act_viewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return act_info.size();
                }
                /**
                 *
                 * @param view 页面
                 * @param object instantiateItem方法返回的值
                 * @return
                 */
                @Override
                public boolean isViewFromObject(View view, Object object) {
                    return view == object;
                }

                /**
                 *
                 * @param container ViewPager
                 * @param position 对应页面的位置
                 * @return
                 */
                @Override
                public Object instantiateItem(ViewGroup container, final int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    //Glide.with(mContext).load(Constants.BASE_URL_IMAGE + act_info.get(position).getIcon_url()).into(imageView);
                    //添加到容器中
                    container.addView(imageView);

                    //设置点击事件
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                        }
                    });


                    return imageView;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                    container.removeView((View) object);
                }
            });
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == TEST_ONE){
            return new BunnerViewHolder(mContext,layout.inflate(R.layout.banner_viewpager,null));
        }else if(viewType == TEST_TWO){
            return  new ChannelViewHolder(mContext,layout.inflate(R.layout.channel_item,null));
        }else if(viewType == TEST_THERE){
            return new ActViewHolder(mContext, layout.inflate(R.layout.act_item, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(getItemViewType(position) == TEST_ONE){
            BunnerViewHolder bunnerViewHolder = (BunnerViewHolder) holder;
            bunnerViewHolder.setData();
        }else if (getItemViewType(position) == TEST_TWO){
            ChannelViewHolder channelView = (ChannelViewHolder) holder;
            channelView.setData();

        }else if (getItemViewType(position) == TEST_THERE){
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            ((ActViewHolder) holder).setData();
        }

    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case TEST_ONE:
                currentType = TEST_ONE;
                break;
            case TEST_TWO:
                currentType = TEST_TWO;
                break;
            case TEST_THERE:
                currentType= TEST_THERE;
                break;
            case TEST_FIVE:
                currentType = TEST_FIVE;
                break;
            case TEST_FOR:
                currentType = TEST_FOR;
                break;
        }

        return currentType;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
