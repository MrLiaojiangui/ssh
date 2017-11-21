package com.lanou3g.crm.department.dao.impl;

import com.lanou3g.crm.department.dao.DepartmentDao;
import com.lanou3g.crm.department.domain.Department;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by dllo on 17/11/9.
 */
public class DepartmentDaoImpl extends HibernateDaoSupport implements DepartmentDao {

    //添加部门
    @Override
    public void addDept(Department department) {
        getHibernateTemplate().save(department);
    }

    //查询所有部门
    @Override
    public List<Department> query() {
        List<Department> list = (List<Department>) getHibernateTemplate().find("from Department T_DEPT");
        return list;
    }



    //添加或更新
    //如果depId为空添加,不为空更新
    @Override
    public void saveOrUpdate(Department department) {
        if (department.getDepId().isEmpty()){
            getHibernateTemplate().save(department);
        }else {
            getHibernateTemplate().saveOrUpdate(department);
        }
    }

    //分页
    //用getHibernateTemplate().execute方法,
    @Override
    public List<Department> getListByPage(int offSet, int length) {
        final String sql = "from Department T_DEPT";
        List<Department> list = getHibernateTemplate().execute(new HibernateCallback<List<Department>>() {
            @Override
            public List<Department> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery(sql);
                query.setFirstResult(offSet);
                query.setMaxResults(length);
                List<Department> list1 = query.list();
                return list1;
            }
        });
        return list;
        
    }
}
