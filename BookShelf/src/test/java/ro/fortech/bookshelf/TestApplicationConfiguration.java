package ro.fortech.bookshelf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ro.fortech.bookshelf.dao.BookDao;
import ro.fortech.bookshelf.dao.inmemorydao.IMBookDao;
import ro.fortech.bookshelf.service.BookService;

@Configuration
public class TestApplicationConfiguration {

	@Bean
	public BookService employeeService() {
		return new BookService();
	}
	
	@Bean
	public BookDao bookDao() {
		return new IMBookDao();
	
		
	}
}
