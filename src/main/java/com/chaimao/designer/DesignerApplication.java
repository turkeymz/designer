package com.chaimao.designer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan   //扫描Servlet
@MapperScan("mapper")
public class DesignerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesignerApplication.class, args);
	}



}
