package com.mywebclient.dingkunming.mywebclient.article.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/16.
 */

public class ResultBeanData {

    private List<AcrticlesBean> acrticles;

    public List<AcrticlesBean> getAcrticles() {
        return acrticles;
    }

    public void setAcrticles(List<AcrticlesBean> acrticles) {
        this.acrticles = acrticles;
    }

    public static class AcrticlesBean {
        /**
         * id : 15
         * title : 名字怎么都无所谓
         * content : dssdkl;dsf
         * jointime : 2017-2-11 18:18:55
         * articletypeId : 1
         * userId : 5
         */

        private int id;
        private String title;
        private String content;
        private String jointime;
        private int articletypeId;
        private int userId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getJointime() {
            return jointime;
        }

        public void setJointime(String jointime) {
            this.jointime = jointime;
        }

        public int getArticletypeId() {
            return articletypeId;
        }

        public void setArticletypeId(int articletypeId) {
            this.articletypeId = articletypeId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
