package com.lanou3g.crm.staff.action;

import com.lanou3g.crm.base.BaseAction;
import com.lanou3g.crm.department.domain.Department;
import com.lanou3g.crm.department.service.DepartmentService;
import com.lanou3g.crm.post.domain.Post;
import com.lanou3g.crm.staff.domain.Staff;
import com.lanou3g.crm.staff.service.StaffService;
import com.lanou3g.crm.staff.service.impl.StaffServiceImpl;
import com.lanou3g.crm.utils.MD5Utils;
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

public class StaffAction extends BaseAction<Staff, StaffService> {



    private List<Staff> staffs;

    private String depId;

    @Resource
    private DepartmentService departmentService;


    private List<Department> allDept;

    private String postId;

    private List<Post> posts;


    private String staffId;
    private Staff staffById;

    private String oldPassword;
    private String newPassword;
    private String reNewPassword;




    //登录
    public String login() {
        Staff staff1 = service.login(getModel());
        if (staff1!= null){
            sessionPut("login",getModel().getLoginName());
            return SUCCESS;
        }
        addFieldError("msg","用户不存在");
        return INPUT;
    }

    @SkipValidation
    //重新登录
    public String staffAction_logout(){
        ActionContext.getContext().getSession().clear();
        return INPUT;
    }

    // 修改密码
    @SkipValidation
    public String modifyPassword() {

        if (!StringUtils.isBlank(reNewPassword) &&
                !StringUtils.isBlank(newPassword)&&reNewPassword.equals(newPassword)) {
            //查找出登陆的员工信息
            Staff staff = service.findStaffByLoginName(ActionContext.getContext().getSession().
                    get("login").toString());
            //将员工密码更新保存
            staff.setLoginPwd(MD5Utils.getMD5Value(newPassword));
            service.saveOrUpdate(staff);
            return SUCCESS;
        } else {
            addFieldError("msg", "请输入正确的密码");

            return ERROR;
        }
    }

    //添加员工
    @SkipValidation
    public String addStaff() {
        //对添加员工的密码进行加密
        Staff staff = getModel();
        String md5Value = MD5Utils.getMD5Value(staff.getLoginPwd());
        staff.setLoginPwd(md5Value);
        staff.setPost(new Post(postId));
        service.save(staff);
        return SUCCESS;
    }



    //查询所有员工
    @SkipValidation
    public String queryStaff(){
        //查出所有部门
        allDept = departmentService.query();

        staffs = service.queryStaff();

        return SUCCESS;
    }

    //高级查询
    @SkipValidation
    public String findAllStaffs(){
        staffs = service.getAllResult(getModel());
        return SUCCESS;
    }

    //根据部门id查询对应的职位
    @SkipValidation
    public String getPostByDepId(){
        posts = service.getPostByDeptId(depId);
        return SUCCESS;
    }

    //查询所有部门

    @SkipValidation
    public String findAllDept(){
        allDept = departmentService.query();
        System.out.println("部门:" + allDept);
        return SUCCESS;
    }

    //编辑员工
    @SkipValidation
    public String edieStaff(){
        Staff staff = getModel();
        staff.setPost(new Post(postId, new Department(depId)));
        service.saveOrUpdate(staff);
        return SUCCESS;
    }

    //编辑员工时查出一个员工的所有信息

    @SkipValidation
    public String editStaffPre(){
        allDept = departmentService.query();
        staffById = service.findStaffById(getModel().getStaffId());
        ActionContext.getContext().put("setDeptId",staffById.getPost().getDepartment().getDepId());
        ActionContext.getContext().getSession().put("setPostId",staffById.getPost().getPostId());
        ActionContext.getContext().getSession().put("setPostName",staffById.getPost().getPostName());
        return SUCCESS;
    }



    public List<Staff> getStaffs() {
        return staffs;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
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


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReNewPassword() {
        return reNewPassword;
    }

    public void setReNewPassword(String reNewPassword) {
        this.reNewPassword = reNewPassword;
    }
}
