package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.Example.FileExample;
import com.kaishengit.crm.UpAndDown.BaseUpload;
import com.kaishengit.crm.entity.File;
import com.kaishengit.crm.entity.Staff;
import com.kaishengit.crm.exception.StaffException;
import com.kaishengit.crm.mapper.FileMapper;
import com.kaishengit.crm.service.FileService;
import com.sun.javafx.collections.MappingChange;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    @Qualifier("localUpload")
    private BaseUpload baseUpload;

    @Value("${filePath}")
    private String filePath;
    @Override
    public List<File> findAllByPid(Integer id) {
        FileExample fileExample = new FileExample();
        fileExample.createCriteria().andPidEqualTo(id);
        return fileMapper.selectByExample(fileExample);
    }

    @Override
    public File findById(Integer id) {
        return fileMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addFolder(Staff staff, Integer pid, String name) {
        File file = new File();
        file.setName(name);
        file.setUpdatetime(new Date());
        file.setPid(pid);
        file.setStaffId(staff.getId());
        file.setType("dir");
        fileMapper.insert(file);
    }

    /**
     * 传入FDFS服务器时的上传文件的
      * @param inputStream
     * @param fileSize
     * @param fileName
     * @param staff
     * @param pid
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveNewFile(InputStream inputStream, long fileSize, String fileName,Staff staff,Integer pid) {
        File file = new File();
        file.setType(File.FILE_TYPE_FLODER);
        file.setUpdatetime(new Date());
        file.setStaffId(staff.getId());
        file.setPid(pid);
        file.setDownloadNum(0);
        file.setName(fileName);
        file.setSize(FileUtils.byteCountToDisplaySize(fileSize));

        try{
          Map<String,Object> map = baseUpload.upload(inputStream,fileName);
          fileName = map.get("group")+"#"+map.get("fileName");
        }catch (IOException e){
            throw new StaffException(e,"上传文件时出错");
        }
        file.setSaveName(fileName);
        fileMapper.insert(file);
    }

    /**
     * 上传到本地文件夹时的上传
     * @param inputStream
     * @param fileSize
     * @param fileName
     * @param staff
     * @param pid
     */
    @Override
    public void localNewFile(InputStream inputStream, long fileSize, String fileName, Staff staff, Integer pid) {
        File file = new File();
        file.setType(File.FILE_TYPE_FLODER);
        file.setUpdatetime(new Date());
        file.setStaffId(staff.getId());
        file.setPid(pid);
        file.setDownloadNum(0);
        file.setName(fileName);
        file.setSize(FileUtils.byteCountToDisplaySize(fileSize));
        try {
            Map<String,Object> map = baseUpload.upload(inputStream,fileName);
            file.setSaveName((String) map.get("fileName"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileMapper.insert(file);
    }

    @Override
    public InputStream downFileById(Integer id) throws IOException{
        File file = fileMapper.selectByPrimaryKey(id);
        if (file == null || file.getType().equals(File.FILE_TYPE_PACKAGE)){
            throw new StaffException(id+"对应文件不存在");
        }
        file.setDownloadNum(file.getDownloadNum()+1);
        fileMapper.updateByPrimaryKey(file);
        byte[] bytes = baseUpload.getFile(file.getSaveName());
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public void deleFileById(Integer id) {
        File file = fileMapper.selectByPrimaryKey(id);
        if ("file".equals(file.getType())){
            java.io.File file1 = new java.io.File("D:/test"+file.getSaveName());
            file1.delete();
        }
        fileMapper.deleteByPrimaryKey(id);
    }
}
