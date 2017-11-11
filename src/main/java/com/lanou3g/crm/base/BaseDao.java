package com.lanou3g.crm.base;

import java.util.List;

/**
 * Created by dllo on 17/11/9.
 */
public interface BaseDao<T> {
    // 保存
    public void save(T t);
    //更新
    public void update(T t);
    //删除
    public void delete(T t);
    //保存或更新
    public void  saveOrUpdate(T t);
    //查询所有
    public List<T> findAll();
    //条件查询
    public List<T> findAll(String sql,Object... params);


}
