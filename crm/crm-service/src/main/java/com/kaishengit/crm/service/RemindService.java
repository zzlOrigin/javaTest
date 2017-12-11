package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Remind;
import com.kaishengit.crm.entity.Staff;

import java.util.List;

public interface RemindService {


    PageInfo<Remind> findAllByStaffId(Staff staff, Integer pageNo);

    void saveNewRemind(Remind remind);

    void deleById(Integer id);

    List<Remind> findAllByChanceId(Integer id);

    List<Remind> findAllByCustId(Integer id);

    void doneRemind(Integer id);
}
