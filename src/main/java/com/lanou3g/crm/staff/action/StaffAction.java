package com.lanou3g.crm.staff.action;

import com.lanou3g.crm.base.BaseAction;
import com.lanou3g.crm.staff.domain.Staff;
import com.lanou3g.crm.staff.service.StaffService;
import com.lanou3g.crm.staff.service.impl.StaffServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by dllo on 17/11/10.
 */
@Controller("staffAction")
@Scope("prototype")
public class StaffAction extends BaseAction<Staff, StaffServiceImpl> {
    @Resource
    private StaffService staffService;
    private Staff staff = getModel();

    public String addStaff() {

        staffService.add(staff);
        return "add";
    }

    public Staff getStaff() {
        return staff;
    }

    public String login() {
        Staff staff1 = staffService.login(staff);
        if (staff1!= null){
            sessionPut("login",staff.getLoginName());
            return SUCCESS;
        }
        addFieldError("msg","用户不存在");
        return INPUT;


    }
}
