package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Chance;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Staff;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public interface CustomerService {
    PageInfo<Customer> findAllCust(Staff staff, Integer pageNo);

    void saveCust(Customer customer);

    Customer findById(Integer id);

    PageInfo<Customer> findAllPublic(Integer pageNo);


    void updateCust(Customer customer1);

    void deleCust(Customer customer);

    void tranCust(Customer customer, Integer sid);

    List<Customer> findAllCust(Integer id);


    void exportCsvFileToOutputStream(OutputStream outputStream, Staff staff) throws IOException;

    void exportXlsFileToOutputStream(OutputStream outputStream, Staff staff) throws IOException;

    List<Map<String,Object>> findAllCustForEch(Staff staff);
}
