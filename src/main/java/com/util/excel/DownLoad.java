package com.util.excel;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * 在线导出
 *
 * @author 潘根山
 * @create 2018-03-25 13:12
 */
public class DownLoad {
    public static void download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("GBK");
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + new String(filename.getBytes(), "iso8859-1"));
            response.setHeader("Content-Disposition",
                    "inline" + "; filename=\"" + new String(filename.getBytes(), "iso8859-1") + "\"");
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=GBK");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            if (file.exists()) {
                file.delete();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}