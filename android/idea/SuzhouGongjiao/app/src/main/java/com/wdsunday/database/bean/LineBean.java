package com.wdsunday.database.bean;

/**
 * Created by liang on 2017/1/1.
 */
public class LineBean {
    public String lineName;
    public String pathName;
    public String link;

    @Override
    public String toString() {
        return "LineBean{" +
                "lineName='" + lineName + '\'' +
                ", pathName='" + pathName + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
