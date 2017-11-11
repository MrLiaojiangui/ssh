package com.lanou3g.crm.department.action;



import com.lanou3g.crm.base.BaseAction;
import com.lanou3g.crm.department.domain.Department;
import com.lanou3g.crm.department.service.DepartmentService;
import com.lanou3g.crm.department.service.impl.DepartmentServiceImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/11/11.
 */
@Controller("departmentAction")
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department,DepartmentServiceImpl> {
   @Resource
    private DepartmentService departmentService;
    private Department department = getModel();
    public String addDepartment(){
        departmentService.addDept(department);
        return "addDept";
    }
}
