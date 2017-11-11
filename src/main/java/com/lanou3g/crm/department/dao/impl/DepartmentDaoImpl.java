package com.lanou3g.crm.department.dao.impl;

import com.lanou3g.crm.department.dao.DepartmentDao;
import com.lanou3g.crm.department.domain.Department;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by dllo on 17/11/9.
 */
public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao {
    @Override
    public void addDept(Department department) {
        getHibernateTemplate().save(department);
    }

    @Override
    public List<Department> query() {
        List<Department> list = (List<Department>) getHibernateTemplate().find("from Department T_DEPT");
        return list;
    }

    @Override
    public List<Department> saveOrUpdate(Department department) {
        if (department.getDepId().isEmpty()){
            getHibernateTemplate().save(department);
        }else {
            getHibernateTemplate().saveOrUpdate(department);
        }
        return null;
    }
}
