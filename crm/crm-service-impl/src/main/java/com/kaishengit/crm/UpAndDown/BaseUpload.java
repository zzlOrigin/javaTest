package com.kaishengit.crm.UpAndDown;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface BaseUpload {


    Map<String,Object> upload(InputStream inputStream,String fileName) throws IOException;


    byte[] getFile(String fileName) throws IOException;

}
