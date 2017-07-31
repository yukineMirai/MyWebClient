package com.mywebclient.dingkunming.mywebclient.utils;

import android.os.Environment;

/**
 *
 * 作用：配置各个页面联网地址
 */
public class Constants {

    public static String BASE_URL = "http://dingkunming.cn/";

    /**
     * 主页面的路径
     */
    public static String HOME_URL  = BASE_URL+"acrticle";
    /**
     * MoonNotePage_URL
     */
    public static String MOONNOTE_URL  = BASE_URL+"moonNote";
    /**
     * 图片的基本路径
     */
    public static String BASE_URL_IMAGE  = BASE_URL+"/bg/images/";
    /**
     * 头像的基本路径
     */
    public static String BASE_URL_IMAGE_ICON  = BASE_URL+"/images/logo/image.jpg";
    public static String USER_LOGIN = BASE_URL +" m";

    public static String SAVE_IMAGE = Environment.getExternalStorageDirectory() + "/a_myWeb/";


}
