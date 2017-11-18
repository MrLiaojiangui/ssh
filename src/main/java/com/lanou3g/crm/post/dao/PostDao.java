package com.lanou3g.crm.post.dao;

import com.lanou3g.crm.department.domain.Department;
import com.lanou3g.crm.post.domain.Post;

import java.util.List;

/**
 * Created by dllo on 17/11/13.
 */
public interface PostDao {
    void save(Post post);
    List<Post> query();
    List<Department> findAllDept(Department department);
    void saveOrUpdate(Post post);
}
