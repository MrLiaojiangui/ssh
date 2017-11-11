package com.lanou3g.crm.department.dao;

import com.lanou3g.crm.department.domain.Department;

import java.util.List;

/**
 * Created by dllo on 17/11/9.
 */
public interface DepartmentDao {
    void addDept(Department department);
    List<Department> query();
    List<Department> saveOrUpdate(Department department);
}
