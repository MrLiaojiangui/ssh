package com.lanou3g.crm.staff.service;

import com.lanou3g.crm.department.domain.Department;
import com.lanou3g.crm.page.PageBase;
import com.lanou3g.crm.post.domain.Post;
import com.lanou3g.crm.staff.domain.Staff;

import java.util.List;

/**
 * Created by dllo on 17/11/9.
 */
public interface StaffService {
    void save(Staff staff);

    List<Staff> queryStaff();

    void saveOrUpdate(Staff staff);


    Staff login(Staff staff);


    //查询所有部门信息
    List<Department> getDepartments();

    /**
     * 根据部门ID,查询出所对应的职位
     *
     * @param deptId 部门的ID
     * @return 职位的集合
     */
    List<Post> getPostByDeptId(String deptId);


    Staff findStaffById(String staffId);


    //高级查询
    List<Staff> getAllResult(Staff staff);


    //
    List<Staff> find(Staff staff);

    Staff findStaffByLoginName(String loginName);


//    /**
//     * 根据部门和职位ID,查询员工
//     * @param depId
//     * @param postId
//     * @return 员工的集合
//     */
//    List<Staff> getStaffByDeptIdAndPostId(String depId,String postId);

//    /**
//     * 查询某个部门的所有员工
//     * @param deptId
//     * @return
//     */
//    List<Staff> getStaffByDeptId(String deptId);

//    List<Staff> findAllStaffByStaffName(String staffName);
//
//    List<Staff> findAllStaffByPostId(String postId);

//    Staff findStaffByLoginNameAndLoginPwd(Staff staff);




}
