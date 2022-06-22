package com.example.triple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TripleApplication {

	public static void main(String[] args) {

		try {
			SpringApplication.run(TripleApplication.class, args);
		}catch(Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

}
