package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.File;
import com.kaishengit.crm.entity.Staff;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileService {

    List<File> findAllByPid(Integer id);

    File findById(Integer id);

    void addFolder(Staff staff, Integer pid, String name);

    void saveNewFile(InputStream inputStream, long fileSize, String fileName,Staff staff,Integer pid);

    void localNewFile(InputStream inputStream, long fileSize, String fileName,Staff staff,Integer pid);

    InputStream downFileById(Integer id) throws IOException;

    void deleFileById(Integer id);
}
