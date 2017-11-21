package com.lanou3g.crm.department.action;



import com.lanou3g.crm.base.BaseAction;
import com.lanou3g.crm.department.domain.Department;
import com.lanou3g.crm.department.service.DepartmentService;
import com.lanou3g.crm.department.service.impl.DepartmentServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/11/11.
 */
public class DepartmentAction extends BaseAction<Department,DepartmentService> {

    private List<Department> departments;

    private int totalSize;          //页数总数
    private int length;             //长度
    private int pageSize;           //每页有多少天数据
    private int offSet;             //起始位置
    private List<Department> listByPage;

    //添加部门
    public String addDepartment(){
        if (getModel().getDepName().isEmpty()){
            addFieldError("","部门不能为空");
            return ERROR;
        }else {
            service.addDept(getModel());
            departments = service.query();
            return "addDept";

        }

    }

    //查询所有部门
    public String query(){
        departments = service.query();
        return SUCCESS;
    }

    //添加或者更新
   public String saveOrUpdate(){
       service.saveOrQuery(getModel());
       return SUCCESS;
   }

   // 分页
   public String page(){
       List<Department> list = service.query();
       totalSize = list.size();
       length = 5;
       pageSize = totalSize/length+1;
       int overPlus = totalSize - totalSize%5;
       System.out.println(overPlus);
       if (offSet < 0){
           offSet = 0;

       }else if (offSet > totalSize){
           offSet = overPlus;
       }
       ActionContext.getContext().getSession().put("overPlus",overPlus);
       ActionContext.getContext().getSession().put("offSet",offSet);
       ActionContext.getContext().getSession().put("pageSize",pageSize);
       listByPage = service.getListByPage(offSet,length);
       ActionContext.getContext().getSession().put("list_by_page",listByPage);
       return SUCCESS;


   }


    public List<Department> getDepartments() {
        return departments;
    }


    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getOffSet() {
        return offSet;
    }

    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }
}
