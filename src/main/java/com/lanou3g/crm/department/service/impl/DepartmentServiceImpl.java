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
        departmentDao.addDept(department);
    }

    @Override
    public List<Department> query(Department department) {
        return departmentDao.query();
    }

    @Override
    public List<Department> saveOrQuery(Department department) {
        return departmentDao.saveOrUpdate(department);
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }
}
