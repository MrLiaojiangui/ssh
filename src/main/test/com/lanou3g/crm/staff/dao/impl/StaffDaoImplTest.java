package com.lanou3g.crm.staff.dao.impl;

import com.lanou3g.crm.department.domain.Department;
import com.lanou3g.crm.post.domain.Post;
import com.lanou3g.crm.staff.dao.StaffDao;
import com.lanou3g.crm.staff.domain.Staff;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by dllo on 17/11/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/*.xml")
public class StaffDaoImplTest {
    @Resource
    private StaffDao staffDao;
    private Department department = new Department();
    private Post post = new Post();
    @Test
    public void save(){
        Staff staff = new Staff();
        staff.setLoginName("5555");
        staff.setLoginPwd("123456");
        staff.setStaffName("垃圾");


        department.setDepName("教学部");
//        department.setDepId(1);
        post.setPostName("讲师");
//        post.setPostId(5);
//        staffDao.save(staff);

        staff.setPost(post);
        post.setDepartment(department);
        staffDao.save(staff);
    }


}