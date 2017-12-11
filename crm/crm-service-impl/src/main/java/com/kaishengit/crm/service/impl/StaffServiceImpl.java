package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.Example.StaffDepartmentExample;
import com.kaishengit.crm.Example.StaffExample;
import com.kaishengit.crm.entity.Staff;
import com.kaishengit.crm.entity.StaffDepartment;
import com.kaishengit.crm.exception.DepartmentException;
import com.kaishengit.crm.exception.StaffException;
import com.kaishengit.crm.mapper.StaffDepartmentMapper;
import com.kaishengit.crm.mapper.StaffMapper;
import com.kaishengit.crm.service.StaffService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StaffServiceImpl implements StaffService {

    private static final Integer COMPANY_ID = 1;
    @Autowired
    private StaffMapper staffMapper;
     @Autowired
    private StaffDepartmentMapper staffDepartmentMapper;
    @Override
    public Staff login(String mobile, String password) throws StaffException{
        StaffExample staffExample = new StaffExample();
        staffExample.createCriteria().andMobileEqualTo(mobile);

        List<Staff> staffList = staffMapper.selectByExample(staffExample);
        Staff staff = null;

        if (staffList != null && !staffList.isEmpty()){
            staff = staffList.get(0);
        }
        if (staff != null && staff.getPassword().equals(DigestUtils.md5Hex(password))){
            return staff;
        }else {
            throw new StaffException("账号或者密码错误");
        }
    }

    @Override
    public List<Staff> selectByMap(Map<String, Object> map) {
        if (map.get("deptId") == null || COMPANY_ID.equals(map.get("deptId"))){
            map.put("deptId",null);
        }
        return staffMapper.selectByMap(map);
    }

    @Override
    @Transactional
    public void addNewStaff(String userName, String password, String mobile, Integer[] daptId) throws DepartmentException {
        StaffExample staffExample = new StaffExample();
        staffExample.createCriteria().andMobileEqualTo(mobile);
        List<Staff> staffList = staffMapper.selectByExample(staffExample);
        if (staffList != null && !staffList.isEmpty()) {
            throw new DepartmentException("已存在用户");
        }

        Staff staff = new Staff();
        staff = new Staff();
        staff.setUserName(userName);
        staff.setMobile(mobile);
        staff.setPassword(DigestUtils.md5Hex(password));
        staff.setUpdateTime(new Date());
        staff.setCreateTime(new Date());
        staffMapper.insertSelective(staff);
        for (Integer departId : daptId) {
            StaffDepartment staffDepartment = new StaffDepartment();
            staffDepartment.setStaffId(staff.getId());
            staffDepartment.setDepartmentId(departId);
            staffDepartmentMapper.insert(staffDepartment);


        }
    }

    @Override
    public Long CountByDeptId(Integer deptId) {
        if (deptId == null || COMPANY_ID.equals(deptId)){
            deptId = null;
        }
         return staffMapper.CountByDeptId(deptId);
    }

    @Override
    public void deleByid(Integer id) {
        staffMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Staff> findAllStaff() {
        return staffMapper.selectByExample(new StaffExample());
    }

    @Override
    public Staff findById(Integer sid) {
        return staffMapper.selectByPrimaryKey(sid);
    }

    @Override
    public Staff findByMobile(String userName) {
        StaffExample staffExample = new StaffExample();
        staffExample.createCriteria().andMobileEqualTo(userName);
        List<Staff> staffList = staffMapper.selectByExample(staffExample);
        if (staffList == null && staffList.isEmpty()){
            return null;
        }
        return staffList.get(0);
    }

}
