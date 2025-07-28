package com.ysj.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ysj.practice")
@MapperScan("com.ysj.practice") // 이 아래 모든 패키지 안의 Mapper 인터페이스를 자동 스캔
public class MemberProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberProjectApplication.class, args);
	}

}
