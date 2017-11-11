package com.lanou3g.crm.staff.dao.impl;

import com.lanou3g.crm.staff.dao.StaffDao;
import com.lanou3g.crm.staff.domain.Staff;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dllo on 17/11/9.
 */
@Repository("staffDao")
public class StaffDaoImpl extends HibernateDaoSupport implements StaffDao {
    @Override
    public void save(Staff staff) {
        getHibernateTemplate().save(staff);

    }

    @Override
    public List<Staff> find() {
        List<Staff> staffs = (List<Staff>) getHibernateTemplate().find("from Staff T_STAFF");
        return staffs;
    }

    @Override
    public List<Staff> queryStaff(Staff staff) {
        return getHibernateTemplate().findByExample(staff);
    }


    @Override
    public void update() {

    }

    @Override
    public void delete(Staff staff) {

    }

    @Override
    public void saveOrUpdate(Staff staff) {

    }

    @Override
    public List<Staff> findAll(Staff staff) {
        return null;

    }

    @Override
    public List<Staff> findAll(String sql, Object... params) {
        return null;
    }
}
