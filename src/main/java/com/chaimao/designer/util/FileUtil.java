package com.chaimao.designer.util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @Author: cmxu
 * @Description: 文件操作类
 * @Date： create in 21:52 2018/3/11
 * @Modified By:
 */
public class FileUtil {
    /**
     * 上传文件
     * @param file 文件2进制
     * @param filePath 文件路径
     * @param fileName 文件名
     * @throws Exception
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
