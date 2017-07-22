package net.lll0.bus.database.bean;

import java.io.Serializable;

/**
 * Created by liang on 2017/1/1.
 */
public class SearchLineBean implements Serializable{
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
