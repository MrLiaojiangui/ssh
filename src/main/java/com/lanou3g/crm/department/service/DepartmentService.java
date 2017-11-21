package com.lanou3g.crm.department.service;

import com.lanou3g.crm.department.domain.Department;
import com.sun.org.apache.bcel.internal.generic.DDIV;

import java.util.List;

/**
 * Created by dllo on 17/11/11.
 */
public interface DepartmentService {

    void addDept(Department department);

    List<Department> query();

    void saveOrQuery(Department department);

    List<Department> getListByPage(int offSet,int length);
}
