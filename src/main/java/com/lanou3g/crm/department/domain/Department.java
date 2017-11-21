package com.lanou3g.crm.department.domain;

import com.lanou3g.crm.post.domain.Post;
import com.lanou3g.crm.staff.domain.Staff;

import java.util.Set;

public class Department {
    private String depId;
    private String depName;
    private Set<Staff> staffs;
    private Set<Post> posts;

    public Department() {
    }

    public String getDepId() {
        return depId;
    }

    public Department(String depId) {
        this.depId = depId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "depId='" + depId + '\'' +
                ", depName='" + depName + '\'' +
                '}';
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Set<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(Set<Staff> staffs) {
        this.staffs = staffs;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
