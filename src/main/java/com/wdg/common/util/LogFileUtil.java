package com.wdg.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 日志文件记录
 * @author: wdg
 * @create: 2023-11-20 18:05
 */
@Component
public class LogFileUtil {

    @Value("${log.path}")
    private String path;


    public void debug(String message) {
        message = "[" + Thread.currentThread().getName() + "]" + "[DEBUG] " + message + "\n";
        log(message);
    }

    public void info(String message) {
        message = "[" + Thread.currentThread().getName() + "]" + "[info]" + message + "\n";
        log(message);
    }

    private synchronized void log(String message) {
        try {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");
            String dateStr = df.format(date);
            String fileName = path + "log" + "_" + dateStr + ".log";
            FileOutputStream fileOS = new FileOutputStream(fileName, true);
            SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss  ");
            fileOS.write(dff.format(new Date()).getBytes());
            fileOS.write(message.getBytes());
            fileOS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
