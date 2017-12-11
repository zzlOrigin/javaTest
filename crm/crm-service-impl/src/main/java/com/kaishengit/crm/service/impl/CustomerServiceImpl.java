package com.kaishengit.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.Example.CustomerExample;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Staff;
import com.kaishengit.crm.mapper.CustomerMapper;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.crm.service.StaffService;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private StaffService staffService;
    @Override
    public PageInfo<Customer> findAllCust(Staff staff, Integer pageNo) {
        CustomerExample customerExample = new CustomerExample();
        customerExample.createCriteria().andStaffIdEqualTo(staff.getId());
        PageHelper.startPage(pageNo,15);
        List<Customer> customers = customerMapper.selectByExample(customerExample);

        return new PageInfo<>(customers);
    }

    @Override
    public void saveCust(Customer customer) {
        customer.setSetTime(new Date());
        customerMapper.insert(customer);
    }

    @Override
    public Customer findById(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Customer> findAllPublic(Integer pageNo) {
        CustomerExample customerExample = new CustomerExample();
        customerExample.createCriteria().andStaffIdEqualTo(0);
        PageHelper.startPage(pageNo,15);
        List<Customer> customers = customerMapper.selectByExample(customerExample);
        return new PageInfo<>(customers);
    }

    @Override
    public void updateCust(Customer customer1) {
        customer1.setUpdateTime(new Date());
        customerMapper.updateByPrimaryKey(customer1);
    }

    @Override
    public void deleCust(Customer customer) {
        customerMapper.deleteByPrimaryKey(customer.getId());
    }

    @Override
    public void tranCust(Customer customer, Integer sid) {
        Staff staff = staffService.findById(sid);
        Staff staff1 = staffService.findById(customer.getStaffId());
        customer.setReminder(new Date() + "时"+staff1.getUserName()+"把"+customer.getCustName()+"客户,转交给"+staff.getUserName());
        customer.setStaffId(staff.getId());
        customerMapper.updateByPrimaryKey(customer);
    }

    @Override
    public List<Customer> findAllCust(Integer id) {
        CustomerExample customerExample = new CustomerExample();
        customerExample.createCriteria().andStaffIdEqualTo(id);
        return customerMapper.selectByExample(customerExample);
    }

    @Override
    public void exportCsvFileToOutputStream(OutputStream outputStream, Staff staff) throws IOException {
        List<Customer> customers = findAllCust(staff.getId());

        StringBuilder sb = new StringBuilder();
        sb.append("姓名,联系电话,职位,地址\r\n");
        for (Customer customer:customers){
            sb.append(customer.getCustName())
                    .append(",")
                    .append(customer.getMobile())
                    .append(",")
                    .append(customer.getJobTitle())
                    .append(",")
                    .append(customer.getAddress())
                    .append("\r\n");
        }
        IOUtils.write(sb.toString(),outputStream,"GBK");
        outputStream.flush();
        outputStream.close();
    }

    @Override
    public void exportXlsFileToOutputStream(OutputStream outputStream, Staff staff)throws IOException {
        List<Customer> customers = findAllCust(staff.getId());
        Workbook workbook = new HSSFWorkbook();
        //创建sheet
        Sheet sheet = workbook.createSheet("我的客户");
        //创建行
        Row titleRow = sheet.createRow(0);
        //创建单元格
        Cell naemCell = titleRow.createCell(0);
        naemCell.setCellValue("姓名");
        titleRow.createCell(1).setCellValue("电话");
        titleRow.createCell(2).setCellValue("职位");
        titleRow.createCell(3).setCellValue("地址");
        for(int i = 0; i < customers.size();i++){
            Customer customer = customers.get(i);
            Row dataRow = sheet.createRow(i+1);
            dataRow.createCell(0).setCellValue(customer.getCustName());
            dataRow.createCell(1).setCellValue(customer.getMobile());
            dataRow.createCell(2).setCellValue(customer.getJobTitle());
            dataRow.createCell(3).setCellValue(customer.getAddress());
        }
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    @Override
    public List<Map<String, Object>> findAllCustForEch(Staff staff) {
        return customerMapper.monthForNumber();


    }

}
