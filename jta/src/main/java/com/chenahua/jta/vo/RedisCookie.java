package com.chenahua.jta.vo;

public class RedisCookie {
    private String tokenkey;
    private String userInfo;

    public String getTokenkey() {
        return tokenkey;
    }

    public void setTokenkey(String tokenkey) {
        this.tokenkey = tokenkey;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "RedisCookie{" +
                "tokenkey='" + tokenkey + '\'' +
                ", userInfo='" + userInfo + '\'' +
                '}';
    }
}
