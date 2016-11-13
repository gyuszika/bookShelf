package ro.fortech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ro.fortech.bookshelf")
public class BookShelfApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookShelfApplication.class, args);

	}

}
