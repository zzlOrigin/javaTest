package com.kaishengit.crm.UpAndDown;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class LocalUpload implements BaseUpload {
    @Value("${filePath}")
    private String saveFilePath;
    @Override
    public Map<String, Object> upload(InputStream inputStream,String fileName) throws IOException {
        String newFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        OutputStream outputStream = new FileOutputStream(new File(saveFilePath,newFileName));
        IOUtils.copy(inputStream,outputStream);
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        Map<String,Object> map = new HashMap<>();
        map.put("fileName",newFileName);
    return map;
    }

    @Override
    public byte[] getFile(String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(new File(saveFilePath,fileName));
        return IOUtils.toByteArray(inputStream);
    }
}
