package net.lll0.bus.database.bean;

import java.io.Serializable;

/**
 * Created by liang on 2017/1/1.
 */
public class SearchLineBean implements Serializable{
    /**
     * 公交线路地理名称第名称
     */
    public String lineName;

    /**
     * 公交线路线路名称
     */
    public String pathName;
    /**
     * 公交详情的的查询url
     */
    public String link;
    /**
     * 开始站点的id
     */
    public String startLineID;
    /**
     * 结束站点的id
     */
    public String endLineID;

    @Override
    public String toString() {
        return "LineBean{" +
                "lineName='" + lineName + '\'' +
                ", pathName='" + pathName + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
