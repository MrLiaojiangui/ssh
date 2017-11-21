package com.lanou3g.crm.staff.service.impl;

import com.lanou3g.crm.department.domain.Department;
import com.lanou3g.crm.page.PageBase;
import com.lanou3g.crm.post.domain.Post;
import com.lanou3g.crm.staff.dao.StaffDao;
import com.lanou3g.crm.staff.domain.Staff;
import com.lanou3g.crm.staff.service.StaffService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/11/9.
 */
@Service("staffService")
public class StaffServiceImpl implements StaffService {
    @Resource
    private StaffDao staffDao;

    //添加员工
    @Override
    public void save(Staff staff) {
        staffDao.save(staff);

    }

    //查询所有员工
    @Override
    public List<Staff> queryStaff() {
        return staffDao.queryStaff();
    }

    //保存或者更新
    @Override
    public void saveOrUpdate(Staff staff) {
        staffDao.saveOrUpdate(staff);
    }

    //登录
    @Override
    public Staff login(Staff staff) {
        List<Staff> staffs = staffDao.findStaffByLoginNameAndLoginPwd(staff);
        if (staffs.size() != 0){
            return staff;
        }else {
            return null;
        }
    }

    @Override
    public List<Department> getDepartments() {
        return staffDao.getDepartments();
    }

    @Override
    public List<Post> getPostByDeptId(String deptId) {
        return staffDao.getPostByDeptId(deptId);
    }



    @Override
    public Staff findStaffById(String staffId) {
        return staffDao.findStaffById(staffId);
    }

    //高级查询的六个条件
    @Override
    public List<Staff> getAllResult(Staff staff) {

        //部门 1  职务 1  名字 1
        if (!StringUtils.isBlank(staff.getPost().getDepartment().getDepId()) &&
                !staff.getPost().getDepartment().getDepId().equals("-1") &&
                !StringUtils.isBlank(staff.getPost().getPostId()) &&
                !staff.getPost().getPostId().equals("-1") &&
                !StringUtils.isBlank(staff.getStaffName())) {
            return staffDao.findBy_depId_postId_staffName(staff.getPost().getDepartment().getDepId(),
                    staff.getPost().getPostId(), staff.getStaffName());
            //部门 1 职位 1 名字 0
        } else if (!StringUtils.isBlank(staff.getPost().getDepartment().getDepId()) &&
                !staff.getPost().getDepartment().getDepId().equals("-1") &&
                !StringUtils.isBlank(staff.getPost().getPostId()) &&
                !staff.getPost().getPostId().equals("-1") &&
                StringUtils.isBlank(staff.getStaffName())) {
            return staffDao.finAllStaffByPostId(staff.getPost().getPostId());
            //部门 1 职位 0 名字 1
        } else if (!StringUtils.isBlank(staff.getPost().getDepartment().getDepId()) &&
                staff.getPost().getPostId().equals("-1") &&
                !StringUtils.isBlank(staff.getStaffName())) {
            staff.getPost().setPostId(null);
            return staffDao.findBy_depId_staffName(staff.getPost().getDepartment().getDepId(), staff.getStaffName());
            //部门 1 职位 0 名字 0
        } else if (!StringUtils.isBlank(staff.getPost().getDepartment().getDepId()) &&
                staff.getPost().getPostId().equals("-1") &&
                StringUtils.isBlank(staff.getStaffName())) {
            staff.getPost().setPostId(null);
            return staffDao.getStaffByDeptId(staff.getPost().getDepartment().getDepId());
            //部门 0 职位 0 名字 1
        } else if (StringUtils.isBlank(staff.getPost().getDepartment().getDepId())&&
                StringUtils.isBlank(staff.getPost().getPostId()) &&
                !StringUtils.isBlank(staff.getStaffName())) {
            staff.getPost().setPostId(null);
            staff.getPost().getDepartment().setDepId(null);
            return staffDao.findAllStaffByStaffName(staff.getStaffName());
            //部门 0 职位 0 名字 0
        } else {
            staff.getPost().setPostId(null);
            staff.getPost().getDepartment().setDepId(null);
            return staffDao.queryStaff();
        }


    }


    @Override
    public List<Staff> find(Staff staff) {
        return staffDao.find(staff);
    }

    @Override
    public Staff findStaffByLoginName(String loginName) {
        return staffDao.findStaffByLoginName(loginName);
    }

    public void setStaffDao(StaffDao staffDao) {
        this.staffDao = staffDao;
    }





    //    @Override
//    public List<Staff> getStaffByDeptIdAndPostId(String depId, String postId) {
//        return staffDao.getStaffByDeptIdAndPostId(depId,postId);
//    }
//
//    @Override
//    public List<Staff> getStaffByDeptId(String deptId) {
//        return staffDao.getStaffByDeptId(deptId);
//    }


    //    @Override
//    public Staff findStaffByLoginNameAndLoginPwd(Staff staff) {
//        List<Staff> staffs = staffDao.findStaffByLoginNameAndLoginPwd(staff);
//        for (Staff staff1 : staffs) {
//            if (staff1.getLoginName().equals(staff.getLoginName()) && staff1.getLoginPwd().equals(staff.getLoginPwd()));
//            return staff1;
//        }
//        return null;
//    }

    //    @Override
//    public List<Staff> findAllStaffByStaffName(String staffName) {
//        return staffDao.findAllStaffByStaffName(staffName);
//    }
//
//    @Override
//    public List<Staff> findAllStaffByPostId(String postId) {
//        return staffDao.finAllStaffByPostId(postId);
//    }
}

