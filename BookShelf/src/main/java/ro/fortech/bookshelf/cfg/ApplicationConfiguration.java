package ro.fortech.bookshelf.cfg;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ro.fortech.bookshelf.controller.SecurityFilter;
import ro.fortech.bookshelf.dao.BookDao;
import ro.fortech.bookshelf.dao.db.JdbcTemplateBookDao;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public FilterRegistrationBean securityFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(createSecurityFilter());
		registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
		registration.addUrlPatterns("/*");
		return registration;
	}

	@Bean
	public SecurityFilter createSecurityFilter() {
		return new SecurityFilter();
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");

		String url = new StringBuilder()
				.append("jdbc:")
				.append("postgresql")
				.append("://")
				.append("localhost")
				.append(":")
				.append("5432")
				.append("/")
				.append("bookshelf")
				.toString();

		dataSource.setUrl(url);
		dataSource.setUsername("postgres");
		dataSource.setPassword("gyuszika1991");
		return dataSource;

	}

	@Bean
	public BookDao bookDao() {
		// return new IMEmployeeDAO();

		return new JdbcTemplateBookDao(dataSource());

	}

}