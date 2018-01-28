package net.lll0.bus.database.manager;

import net.lll0.bus.database.dao.CollectionBusLineEntityDao;
import net.lll0.bus.database.entity.CollectionBusLineEntity;
import net.lll0.bus.mgr.ApplicationManager;
import net.lll0.bus.ui.APP;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by liang on 2017/9/8.
 */

public class CollectionManager {

    private static CollectionManager collectionManager;
    private CollectionBusLineEntityDao busLineEntityDao;

    private CollectionManager() {

    }

    public static CollectionManager getInstance() {
        if (collectionManager == null) {
            collectionManager = new CollectionManager();
            collectionManager.busLineEntityDao = ApplicationManager.getDaoSession(ApplicationManager.getContext()).getCollectionBusLineEntityDao();
        }
        return collectionManager;
    }


    public Long insert(CollectionBusLineEntity collectionBusLineEntity) {
        return busLineEntityDao.insert(collectionBusLineEntity);
    }

    /**
     * 通过id唯一查询一条记录
     *
     * @param id
     * @return
     */
    public CollectionBusLineEntity findById(String id) {
        List<CollectionBusLineEntity> list = busLineEntityDao.queryBuilder().where(CollectionBusLineEntityDao.Properties.Id.eq(id)).list();
        if (list!=null&&list.size()>0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过id的集合查询相关的收藏数据
     *
     * @param ids
     * @return
     */
    public List<CollectionBusLineEntity> findByIds(List<String> ids) {
        return busLineEntityDao.queryBuilder().where(CollectionBusLineEntityDao.Properties.Id.in(ids)).list();
    }
    /**
     * 通过id的集合查询相关的收藏数据
     *
     * @return
     */
    public List<CollectionBusLineEntity> select() {
        return busLineEntityDao.loadAll();
    }


    public void delete (  CollectionBusLineEntity c){
        busLineEntityDao.delete(c);

    }


}
