package com.mywebclient.dingkunming.mywebclient.main.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mywebclient.dingkunming.mywebclient.R;
import com.mywebclient.dingkunming.mywebclient.utils.Constants;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class ShowActivity extends AppCompatActivity {

    private Bitmap mBitmap;
    private String mFileName;
    private String mSaveMessage;

    private Drawable drawable;
    private String url;

    private static final String TAG = "TAG";

    private static final String htmlFor03 = "网络图片测试1："
            + "<img src='http://dingkunming.cn/bg/images/bg(1).jpg'><h2>12345</h2>";
    TextView textView;
    String mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Bundle extras = getIntent().getExtras();
        mText = extras.getString("content");
        textView = (TextView) findViewById(R.id.tv_show_content);
        TextView tv_title = (TextView) findViewById(R.id.action_bar_title1);

        tv_title.setText(extras.getString("title"));
        //textView.setText(Html.fromHtml(mText, new MyImageGetter(), null));
        textView.setText(Html.fromHtml(mText, new NetWorkImageGetter(), null));
        // textView.setText(Html.fromHtml(htmlFor03, imageGetter, null));
        //setData();

    }

    class NetWorkImageGetter implements Html.ImageGetter {
        /*
         * (non-Javadoc)
         * @see android.text.Html.ImageGetter#getDrawable(java.lang.String)
         */
        @Override
        public Drawable getDrawable(String source) {
            Drawable drawable = null;

            mFileName = Constants.BASE_URL + source;
            String[] array = mFileName.split("/");

            Log.e("TEST", "文件存在不存在:" + array[array.length - 1]);
            File file = new File(Constants.SAVE_IMAGE + array[array.length - 1]);
            //File file = new File(Environment.getExternalStorageDirectory(), NET_PIC_NAME);
            if (file.exists()) {

                Log.e("TEST", "文件存在");
                drawable = Drawable.createFromPath(file.getAbsolutePath());
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth() * 2,
                        drawable.getIntrinsicHeight() * 2);
                /*Bitmap bitmap = BitmapFactory.decodeFile(Constants.SAVE_IMAGE + array[array.length - 1]);
                drawable = new BitmapDrawable(bitmap);*/

            } else {
                new Thread(saveFileRunnable).start();

            }
            return drawable;
        }
    }


    public class URLDrawable extends BitmapDrawable {
        public Bitmap bitmap;

        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0, 0, getPaint());
            }
        }
    }

    /*private class MyImageGetter implements Html.ImageGetter {
        private URLDrawable urlDrawable = null;

        @Override
        public Drawable getDrawable(final String source) {


            mFileName = Constants.BASE_URL + source;
            String[] array = mFileName.split("/");
            //Log.e("TEST","文件存在不存在:"+array[array.length-1]);
            new Thread(saveFileRunnable).start();
            // Log.e("TEST","出线程");

            Bitmap bitmap = BitmapFactory.decodeFile(Constants.SAVE_IMAGE + array[array.length - 1]);
            Drawable drawable = new BitmapDrawable(bitmap);
            urlDrawable = new URLDrawable();
            Picasso.with(ShowActivity.this).load(Constants.BASE_URL + source).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    urlDrawable.bitmap = bitmap;
                    urlDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                    //urlDrawable.setBounds(0, 0, 500, 500);
                    textView.setText(textView.getText());//不加这句显示不出来图片，原因不详
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                }
            });
            return urlDrawable;
        }
    }*/

    public InputStream getImageStream(String path) {

        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return conn.getInputStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Runnable saveFileRunnable = new Runnable() {
        @Override
        public void run() {

                String[] array = mFileName.split("/");
                Log.e("TEST", "文件不存在");

                try {
                    //  Log.e("TEST","start");
                    mBitmap = BitmapFactory.decodeStream(getImageStream(mFileName));
                    // Log.e("TEST","mBitmap OK");
                    saveFile(mBitmap, array[array.length - 1]);
                    Log.e("TEST", "saveFile OK");
                    mSaveMessage = "图片保存成功！";

                    //-----
                    Log.e("TEST", "图片保存成功");
                } catch (IOException e) {
                    mSaveMessage = "图片保存失败！";
                    Log.e("TEST", "图片保存失败");
                    e.printStackTrace();
                }

            textView.setText(Html.fromHtml(mText, new NetWorkImageGetter(), null));
            //messageHandler.sendMessage(messageHandler.obtainMessage());
        }

    };

    /**
     * 保存文件
     *
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public void saveFile(Bitmap bm, String fileName) throws IOException {
        Log.e("TEST", "saveFile start");
        File dirFile = new File(Constants.SAVE_IMAGE);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        //String[] array = fileName.split("/");
        Log.e("TEST", "1 " + fileName);
        File myCaptureFile = new File(Constants.SAVE_IMAGE + fileName);
        //Log.e("TEST","2 ");
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        //Log.e("TEST","3 ");
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        //Log.e("TEST","4 ");
        bos.flush();
        bos.close();
    }

}

    /*Runnable runnable = new Runnable() {
        @Override
        public void run() {

            Log.e("TEST","ok");

            // InputStream is = null;
            try {
                //is = (InputStream) new URL(url).getContent();
                //String s = String.valueOf(is);
                // Toast.makeText(ShowActivity.this,"ok",Toast.LENGTH_SHORT).show();
                drawable = Drawable.createFromStream(is, "src");
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight());
                Log.e("TEST","ok"+url+"|");
                is.close();
                //return d;
            } catch (Exception e) {
                String s = String.valueOf(e);
                Log.e("TEST","error");
                //Toast.makeText(ShowActivity.this,"error",Toast.LENGTH_SHORT).show();
                // return null;
            }
        }
    };


}
*/
