package com.wdg.common.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * 用来生成代码
 */
public class GenerateCode {

    public static void main(String[] args) {

        String xmlPath = System.getProperty("user.dir")+"\\src\\main\\resources\\mapper\\system";
        String outputDir=System.getProperty("user.dir")+"\\src\\main\\java";
        FastAutoGenerator.create("jdbc:mysql://81.70.190.102:50307/admin?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8", "root", "TXroot123.")
                .globalConfig(builder -> {
                    builder.author("wdg") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.wdg.system") // 设置父包名
                            //.moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, xmlPath)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_user") // 设置需要生成的表名
                            //.addTablePrefix("app_", "c_") // 设置过滤表前缀
                            .entityBuilder()  //开启Entity 策略配置
                            .enableLombok() //开启 lombok
                            .enableTableFieldAnnotation() //生成字段注解
                            .mapperBuilder()
                            .enableBaseColumnList()
                            .enableBaseResultMap();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
