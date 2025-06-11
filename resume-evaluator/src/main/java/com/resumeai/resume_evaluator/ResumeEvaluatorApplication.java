package com.resumeai.resume_evaluator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
})
public class ResumeEvaluatorApplication {


	public static void main(String[] args) {
		SpringApplication.run(ResumeEvaluatorApplication.class, args);
	}


}
