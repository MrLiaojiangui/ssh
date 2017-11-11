package com.lanou3g.crm.staff.service.impl;

import com.lanou3g.crm.staff.dao.StaffDao;
import com.lanou3g.crm.staff.domain.Staff;
import com.lanou3g.crm.staff.service.StaffService;
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

    @Override
    public void add(Staff staff) {
        staffDao.save(staff);

    }

    @Override
    public Staff login(Staff staff) {
        List<Staff> staffs = staffDao.queryStaff(staff);
        if (staffs.size() != 0){
            return staff;
        }else {
            return null;
        }
    }

}

