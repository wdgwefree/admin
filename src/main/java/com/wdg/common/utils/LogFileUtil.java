package com.wdg.common.utils;

import org.springframework.beans.factory.annotation.Value;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 日志文件记录
 * @author: wdg
 * @create: 2023-11-20 18:05
 */
public class LogFileUtil {

    @Value("${log.path}")
    private String path;


    public static void debugLog(String message) {
        message = "[" + Thread.currentThread().getName() + "]" + "[DEBUG]" + message + "\n";
        log(message);
    }

    private synchronized static void log(String message) {
        try {
            System.out.println("Thread: "+Thread.currentThread().getName()+"执行中");
            Thread.sleep(5000);
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");
            String dateStr = df.format(date);
            String FileName = "D:\\dcsm/" + "zm_Log" + "-" + dateStr + ".log";
            FileOutputStream debugFile = new FileOutputStream(FileName, true);
            SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss  ");
            debugFile.write(dff.format(new Date()).getBytes());
            debugFile.write(message.getBytes());
            debugFile.close();
            System.out.println("Thread: "+Thread.currentThread().getName()+"执行结束");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
