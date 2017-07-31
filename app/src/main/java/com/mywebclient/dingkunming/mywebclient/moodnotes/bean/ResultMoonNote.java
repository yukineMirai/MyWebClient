package com.mywebclient.dingkunming.mywebclient.moodnotes.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class ResultMoonNote
{


    private List<MoonNotesBean> moonNotes;

    public List<MoonNotesBean> getMoonNotes() {
        return moonNotes;
    }

    public void setMoonNotes(List<MoonNotesBean> moonNotes) {
        this.moonNotes = moonNotes;
    }

    public static class MoonNotesBean {
        /**
         * mnid : 2
         * mnContent : <p>撒阿萨德科技洒洒水库按时付款了撒娇看房了解啊斯洛伐克</p>
         * mnDate : 2017-2-23 17:38:54
         * mnUserId : 1
         */

        private int mnid;
        private String mnContent;
        private String mnDate;
        private int mnUserId;

        public int getMnid() {
            return mnid;
        }

        public void setMnid(int mnid) {
            this.mnid = mnid;
        }

        public String getMnContent() {
            return mnContent;
        }

        public void setMnContent(String mnContent) {
            this.mnContent = mnContent;
        }

        public String getMnDate() {
            return mnDate;
        }

        public void setMnDate(String mnDate) {
            this.mnDate = mnDate;
        }

        public int getMnUserId() {
            return mnUserId;
        }

        public void setMnUserId(int mnUserId) {
            this.mnUserId = mnUserId;
        }
    }
}
