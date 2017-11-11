package com.lanou3g.crm.staff.dao;

import com.lanou3g.crm.staff.domain.Staff;

import java.util.List;

/**
 * Created by dllo on 17/11/9.
 */
public interface StaffDao {
    void save(Staff staff);
    List<Staff> find();


    List<Staff> queryStaff(Staff staff);
    //更新
    public void update();
    //删除
    public void delete(Staff staff);
    //保存或更新
    public void  saveOrUpdate(Staff staff);
    //查询所有
    public List<Staff> findAll(Staff staff);
    //条件查询
    public List<Staff> findAll(String sql,Object... params);

}
