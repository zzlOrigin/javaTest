package test;


import com.sun.org.apache.xpath.internal.operations.String;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.*;
import java.util.Date;
import java.util.Properties;


public class test {


    @Test
    public void show1(){
        System.out.println(new Md5Hash("123456").toString());
    }

/*


    @Test
    public void uploadFile() throws IOException, MyException {
        Properties properties = new Properties();
        properties.setProperty(ClientGlobal.PROP_KEY_TRACKER_SERVERS,"192.168.174.129:22122");
        ClientGlobal.initByProperties(properties);

        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();

        InputStream inputStream = new FileInputStream(new File("d:/test/1.jpg"));
        StorageClient storageClient = new StorageClient(trackerServer,null);
        String[] strings = storageClient.upload_file(IOUtils.toByteArray(inputStream),"jpg",null);

        inputStream.close();
    }

    */
/*group1
     M00/00/00/wKiugVoOWvyAVkFkAAKr6YDYtmI674.jpg
     *//*

    @Test
    public void downLoad() throws IOException, MyException {
         Properties properties = new Properties();
         properties.setProperty(ClientGlobal.PROP_KEY_TRACKER_SERVERS,"192.168.174.129:22122");
         ClientGlobal.initByProperties(properties);
         TrackerClient trackerClient = new TrackerClient();
         TrackerServer trackerServer = trackerClient.getConnection();

         StorageClient storageClient = new StorageClient(trackerServer,null);
         byte[] bytes = storageClient.download_file("group1","M00/00/00/wKiugVoOWvyAVkFkAAKr6YDYtmI674.jpg");
         FileOutputStream outputStream = new FileOutputStream("D:/text/1.jpg");
         outputStream.write(bytes,0,bytes.length);
         outputStream.flush();
         outputStream.close();
    }
*/
}
