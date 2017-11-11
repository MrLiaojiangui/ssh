package com.lanou3g.crm.staff.service;

import com.lanou3g.crm.base.BaseDao;
import com.lanou3g.crm.staff.domain.Staff;

import java.util.List;

/**
 * Created by dllo on 17/11/9.
 */
public interface StaffService {
    void add(Staff staff);
    Staff login(Staff staff);
}
