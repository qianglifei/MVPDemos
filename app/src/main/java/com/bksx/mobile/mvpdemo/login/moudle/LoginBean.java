package com.bksx.mobile.mvpdemo.login.moudle;

/**
 * @author :qlf
 */
public class LoginBean {
    private String yhzh;
    private String yhmm;

    public String getYhzh() {
        return yhzh;
    }

    public void setYhzh(String yhzh) {
        this.yhzh = yhzh;
    }

    public String getYhmm() {
        return yhmm;
    }

    public void setYhmm(String yhmm) {
        this.yhmm = yhmm;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "yhzh='" + yhzh + '\'' +
                ", yhmm='" + yhmm + '\'' +
                '}';
    }
}
