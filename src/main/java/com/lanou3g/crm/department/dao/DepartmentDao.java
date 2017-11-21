package com.lanou3g.crm.department.dao;

import com.lanou3g.crm.department.domain.Department;
import com.sun.tools.javac.code.Scope;

import java.util.List;

/**
 * Created by dllo on 17/11/9.
 */
public interface DepartmentDao {

    //添加部门
    void addDept(Department department);
    //查询所有部门
    List<Department> query();
    //添加或更新
    void saveOrUpdate(Department department);
    //分页
    List<Department> getListByPage(int offSet,int length);
}
