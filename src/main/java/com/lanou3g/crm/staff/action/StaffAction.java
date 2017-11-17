package com.lanou3g.crm.staff.action;

import com.lanou3g.crm.base.BaseAction;
import com.lanou3g.crm.department.domain.Department;
import com.lanou3g.crm.department.service.DepartmentService;
import com.lanou3g.crm.post.domain.Post;
import com.lanou3g.crm.staff.domain.Staff;
import com.lanou3g.crm.staff.service.StaffService;
import com.lanou3g.crm.staff.service.impl.StaffServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;
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
    private List<Staff> staffs;

    private String depId;
    
    @Resource
    private DepartmentService departmentService;

    private Department department;

    private List<Department> allDept;

    private String postId;

    private List<Post> posts;


    private String staffId;
    private Staff staffById;


    //添加员工
    public String addStaff() {
        Staff staff = getModel();
        staff.setPost(new Post(postId));
        staffService.save(staff);
        return SUCCESS;
    }
    //查询所有员工
    public String queryStaff(){
        //查出所有部门
        allDept = departmentService.query();
        //如果名字不为空,通过员工名字找员工
        if (!StringUtils.isBlank(staff.getStaffName())){
            staffs =  staffService.findAllStaffByStaffName(staff.getStaffName());
        //如果职务不为空,通过职务ID找员工
        }else if (!StringUtils.isBlank(postId)&&!postId.equals("-1")){
            staffs =  staffService.findAllStaffByPostId(postId);
        //通过部门ID查找员工
        }else if (!StringUtils.isBlank(depId)&&!depId.equals("-1")){
            postId =null;
            staffs = staffService.getStaffByDeptId(depId);
        }else {
            postId = null;
            depId = null;
            staffs = staffService.queryStaff();
        }

        return SUCCESS;
    }

    //根据部门id查询对应的职位
    public String getPostByDepId(){
        posts = staffService.getPostByDeptId(depId);
        return SUCCESS;
    }


    public String login() {
        Staff staff1 = staffService.login(getModel());
        if (staff1!= null){
            sessionPut("login",getModel().getLoginName());
            return SUCCESS;
        }
        addFieldError("msg","用户不存在");
        return INPUT;

    }
    //查询所有部门

    public String findAllDept(){
        allDept = departmentService.query();
        System.out.println("部门:" + allDept);
        return SUCCESS;
    }
    //编辑员工

    public String edieStaff(){
        Staff staff = getModel();
        staff.setPost(new Post(postId, new Department(depId)));
        staffService.saveOrUpdate(staff);
        return SUCCESS;
    }

    //编辑员工是查出一个员工的所有信息

    public String editStaffPre(){
        allDept = departmentService.query();
        staffById = staffService.findStaffById(getModel().getStaffId());
        ActionContext.getContext().put("setDeptId",staffById.getPost().getDepartment().getDepId());
        ActionContext.getContext().getSession().put("setPostId",staffById.getPost().getPostId());
        ActionContext.getContext().getSession().put("setPostName",staffById.getPost().getPostName());
        return SUCCESS;
    }



    public List<Staff> getStaffs() {
        return staffs;
    }

    public Staff getStaff() {
        return staff;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Department> getAllDept() {
        return allDept;
    }

    public void setAllDept(List<Department> allDept) {
        this.allDept = allDept;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public Staff getStaffById() {
        return staffById;
    }

    public void setStaffById(Staff staffById) {
        this.staffById = staffById;
    }
}
