package com.lanou3g.crm.department.service.impl;

import com.lanou3g.crm.department.dao.DepartmentDao;
import com.lanou3g.crm.department.domain.Department;
import com.lanou3g.crm.department.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/11/11.
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentDao departmentDao;

    @Override
    public void addDept(Department department) {
        departmentDao.saveOrUpdate(department);
    }

    @Override
    public List<Department> query() {
        return departmentDao.query();
    }

    @Override
    public void saveOrQuery(Department department) {
        departmentDao.saveOrUpdate(department);
    }

    @Override
    public List<Department> getListByPage(int offSet, int length) {
        return departmentDao.getListByPage(offSet,length);
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }
}
