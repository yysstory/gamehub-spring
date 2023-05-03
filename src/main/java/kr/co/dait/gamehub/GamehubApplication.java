package kr.co.dait.gamehub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GamehubApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamehubApplication.class, args);
	}

}
