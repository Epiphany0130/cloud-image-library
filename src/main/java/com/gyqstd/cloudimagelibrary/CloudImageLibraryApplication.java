package com.gyqstd.cloudimagelibrary;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.gyqstd.cloudimagelibrary.mapper") // 扫描 Mapper 接口所在的包
@EnableAspectJAutoProxy(exposeProxy = true) // 启用 AOP 代理并暴露代理对象
public class CloudImageLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudImageLibraryApplication.class, args);
    }
}
