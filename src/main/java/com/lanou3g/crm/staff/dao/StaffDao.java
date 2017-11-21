package com.lanou3g.crm.staff.dao;

import com.lanou3g.crm.department.domain.Department;
import com.lanou3g.crm.post.domain.Post;
import com.lanou3g.crm.staff.domain.Staff;

import java.util.List;

/**
 * Created by dllo on 17/11/9.
 */
public interface StaffDao {
    void save(Staff staff);

    //查询所有员工
    List<Staff> queryStaff();

    public void  saveOrUpdate(Staff staff);

   //查询所有部门信息
    List<Department> getDepartments();

    /**
     * 根据部门ID,查询出所对应的职位
     * @param deptId 部门的ID
     * @return 职位的集合
     */
    List<Post> getPostByDeptId(String deptId);

    /**
     * 根据部门和职位ID,查询员工
     * @param depId
     * @param postId
     * @return 员工的集合
     */
    List<Staff> getStaffByDeptIdAndPostId(String depId,String postId);

    /**
     * 查询某个部门的所有员工
     * @param deptId
     * @return
     */
    List<Staff> getStaffByDeptId(String deptId);

    //通过员工ID查找员工
    Staff findStaffById(String staffId);

    //通过员工名字查找所有员工
    List<Staff> findAllStaffByStaffName(String staffName);

    //通过职务ID查找所有员工
    List<Staff> finAllStaffByPostId(String postId);

   //通过部门ID,职务ID,姓名
   List<Staff> findBy_depId_postId_staffName(String depId, String postId, String staffName);

    //部门ID,员工名字
    List<Staff> findBy_depId_staffName(String depId, String staffName);




    List<Staff> find(Staff staff);

    List< Staff>findStaffByLoginNameAndLoginPwd(Staff staff);

    //按名查员工
    Staff findStaffByLoginName(String loginName);

}
