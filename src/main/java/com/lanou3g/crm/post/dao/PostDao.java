package com.lanou3g.crm.post.dao;

import com.lanou3g.crm.department.domain.Department;
import com.lanou3g.crm.post.domain.Post;

import java.util.List;

/**
 * Created by dllo on 17/11/13.
 */
public interface PostDao {

    //添加职务
    void save(Post post);

    //查询所有职务
    List<Post> query();

    //查询所有部门
    List<Department> findAllDept(Department department);

    //更新保存职务
    void saveOrUpdate(Post post);
}
