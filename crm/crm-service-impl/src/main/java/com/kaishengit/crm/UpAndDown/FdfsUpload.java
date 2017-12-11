package com.kaishengit.crm.UpAndDown;

import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class FdfsUpload implements BaseUpload {
    @Override
    public Map<String, Object> upload(InputStream inputStream, String fileName) throws IOException{
        try {
            String offName = fileName.substring(fileName.lastIndexOf("."));
            StorageClient storageClient = getStorageClient();
            String[] results = storageClient.upload_appender_file(IOUtils.toByteArray(inputStream),offName,null);
            Map<String,Object> map = new HashMap<>();
            map.put("group",results[0]);
            map.put("fileName",results[1]);
            return map;
        } catch (MyException e) {
           throw new RuntimeException("存储文件到FDFS出错：",e);
        }
    }

    @Override
    public byte[] getFile(String fileName) throws IOException {
        String[] array = fileName.split("#");
        String groupName = array[0];
        String filePath = array[1];
        try {
            StorageClient storageClient = getStorageClient();
            return storageClient.download_file(groupName,filePath);
        }catch (MyException e){
            throw new RuntimeException("从FastDFS中获取文件异常",e);
        }

    }

    public StorageClient getStorageClient() throws IOException, MyException {
        Properties properties = new Properties();
        properties.setProperty(ClientGlobal.PROP_KEY_TRACKER_SERVERS,"192.168.174.129:22122");
        ClientGlobal.initByProperties(properties);

        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();

        StorageClient storageClient = new StorageClient(trackerServer,null);
        return storageClient;
    }
}
