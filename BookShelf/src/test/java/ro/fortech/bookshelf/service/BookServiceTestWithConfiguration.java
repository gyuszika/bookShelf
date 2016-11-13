package ro.fortech.bookshelf.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ro.fortech.bookshelf.TestApplicationConfiguration;
import ro.fortech.bookshelf.service.BaseBookServiceTest;
import ro.fortech.bookshelf.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestApplicationConfiguration.class})
public class BookServiceTestWithConfiguration extends BaseBookServiceTest {

	@Autowired
	private BookService service;
	


	@Override
	protected BookService getBookService() {
		return service;
	}
	
	
	
}
