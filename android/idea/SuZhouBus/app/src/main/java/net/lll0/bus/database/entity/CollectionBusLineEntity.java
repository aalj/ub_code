package net.lll0.bus.database.entity;

import net.lll0.bus.adapter.BaseBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liang on 2017/9/8.
 */
@Entity
public class CollectionBusLineEntity extends BaseBean {
    @Id
    private String id;
    private String url;
    private String lineName;
    private String endLine;

    @Generated(hash = 2123094958)
    public CollectionBusLineEntity(String id, String url, String lineName,
            String endLine) {
        this.id = id;
        this.url = url;
        this.lineName = lineName;
        this.endLine = endLine;
    }

    @Generated(hash = 386149917)
    public CollectionBusLineEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getEndLine() {
        return endLine;
    }

    public void setEndLine(String endLine) {
        this.endLine = endLine;
    }
}
