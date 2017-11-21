package com.lanou3g.crm.staff.dao.impl;

import com.lanou3g.crm.department.domain.Department;
import com.lanou3g.crm.page.base.PageHibernateCallback;
import com.lanou3g.crm.post.domain.Post;
import com.lanou3g.crm.staff.dao.StaffDao;
import com.lanou3g.crm.staff.domain.Staff;
import com.lanou3g.crm.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
//import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;


import java.util.List;

/**
 * Created by dllo on 17/11/9.
 */
public class StaffDaoImpl extends HibernateDaoSupport implements StaffDao {
    @Override
    public void save(Staff staff) {
        getHibernateTemplate().save(staff);

    }

    @Override
    public List<Staff> queryStaff() {
        List<Staff> staffs = (List<Staff>) getHibernateTemplate().find("from Staff T_STAFF");
        return staffs;
    }

    @Override
    public void saveOrUpdate(Staff staff) {
//        if (StringUtils.isBlank(staff.getStaffId())){
//            //staff.setStaffId(null);
//            getHibernateTemplate().save(staff);
//        }else {
        getHibernateTemplate().saveOrUpdate(staff);
//       }

    }

    @Override
    public List<Department> getDepartments() {
        List<Department> departments =
                (List<Department>) getHibernateTemplate().find("from Department T_DEPT");
        return departments;
    }

    @Override
    public List<Post> getPostByDeptId(String deptId) {
        String sql = "from Post T_POST where department.depId = ?";

        return (List<Post>) getHibernateTemplate().find(sql, deptId);

    }

    @Override
    public List<Staff> getStaffByDeptIdAndPostId(String depId, String postId) {
        String sql = "from Staff where post.department.depId=? and post.postId = ?";
        Object[] value = {depId, postId};
        return (List<Staff>) getHibernateTemplate().find(sql, value);
    }

    @Override
    public List<Staff> getStaffByDeptId(String deptId) {
        String sql = "from Staff T_STAFF where post.department.depId = ?";
        return (List<Staff>) getHibernateTemplate().find(sql, deptId);

    }

    @Override
    public Staff findStaffById(String staffId) {
        Staff staff = getHibernateTemplate().get(Staff.class, staffId);
        return staff;
    }

    //0  0  1
    @Override
    public List<Staff> findAllStaffByStaffName(String staffName) {
        String sql = "from Staff T_STAFF where staffName like ?";
        return (List<Staff>) getHibernateTemplate().find(sql, "%" + staffName + "%");

    }

    //1 1 0
    @Override
    public List<Staff> finAllStaffByPostId(String postId) {
        String sql = "from Staff T_STAFF where post.postId = ?";
        return (List<Staff>) getHibernateTemplate().find(sql, postId);
    }

    //三个条件 1 1 1
    @Override
    public List<Staff> findBy_depId_postId_staffName(String depId, String postId, String staffName) {
        String hql = "from Staff T_STAFF where post.department.depId = ? and post.postId = ? and staffName like ?";
        return (List<Staff>) getHibernateTemplate().find(hql,depId,postId,"%"+staffName + "%");
    }

    //1  0  1
    @Override
    public List<Staff> findBy_depId_staffName(String depId, String staffName) {
        String hql = "from Staff T_STAFF where post.department.depId = ? and staffName like ?";
        return (List<Staff>) getHibernateTemplate().find(hql,depId,"%"+staffName+"%");
    }


    @Override
    public List<Staff> find(Staff staff) {
        return getHibernateTemplate().findByExample(staff);
    }

    //通过登录名和密码查找员工
    @Override
    public List<Staff> findStaffByLoginNameAndLoginPwd(Staff staff) {
        String md5Value = MD5Utils.getMD5Value(staff.getLoginPwd());
        staff.setLoginPwd(md5Value);
        String hql = "from Staff T_STAFF where loginName = ? and loginPwd = ?";
        List<Staff> list = (List<Staff>) getHibernateTemplate().find(hql, staff.getLoginName(), staff.getLoginPwd());
        return list;
    }

    @Override
    public Staff findStaffByLoginName(String loginName) {
        String sql = "from Staff T_STAFF where loginName = ?";
        List<Staff> staff1 = (List<Staff>) getHibernateTemplate().find(sql, loginName);
        if (staff1.size() == 0) {
            return null;
        }
        return staff1.get(0);
    }

}



