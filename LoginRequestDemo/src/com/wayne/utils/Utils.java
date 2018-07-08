package com.wayne.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public static String getTextFromStream(InputStream is){

        try {
            int len = 0;
            byte[] b = new byte[1024];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while((len = is.read(b)) != -1){
                bos.write(b, 0, len);
            }

            String text = new String(bos.toByteArray());
            bos.close();
            return text;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}