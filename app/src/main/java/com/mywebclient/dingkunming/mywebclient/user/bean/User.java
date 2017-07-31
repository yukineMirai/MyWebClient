package com.mywebclient.dingkunming.mywebclient.user.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/16.
 */

public class User {

    /**
     * users : [{"uid":5,"email":"974685276@qq.com","password":"317697800","state":0,"code":"5157600146544db6826a90eeaca74687","imagepath":"/myWeb//images/logo/image.jpg","root":1}]
     * status : 1
     */

    private String status;
    private List<UsersBean> users;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UsersBean> getUsers() {
        return users;
    }

    public void setUsers(List<UsersBean> users) {
        this.users = users;
    }

    public static class UsersBean {
        /**
         * uid : 5
         * email : 974685276@qq.com
         * password : 317697800
         * state : 0
         * code : 5157600146544db6826a90eeaca74687
         * imagepath : /myWeb//images/logo/image.jpg
         * root : 1
         */

        private int uid;
        private String email;
        private String password;
        private int state;
        private String code;
        private String imagepath;
        private int root;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getImagepath() {
            return imagepath;
        }

        public void setImagepath(String imagepath) {
            this.imagepath = imagepath;
        }

        public int getRoot() {
            return root;
        }

        public void setRoot(int root) {
            this.root = root;
        }
    }
}
