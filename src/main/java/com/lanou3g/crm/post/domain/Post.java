package com.lanou3g.crm.post.domain;

import com.lanou3g.crm.department.domain.Department;
import com.lanou3g.crm.staff.domain.Staff;

import java.util.Set;

public class Post {
    private String postId;
    private String postName;
    private Department department;
    private Set<Staff> staffs;

    public Post() {
    }

    public Post(String postId) {
        this.postId = postId;
    }

    public Post(String postId, Department department) {
        this.postId = postId;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postName='" + postName + '\'' +
                '}';
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(Set<Staff> staffs) {
        this.staffs = staffs;
    }
}
