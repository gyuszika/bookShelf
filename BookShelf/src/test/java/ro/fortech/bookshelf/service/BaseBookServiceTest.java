package ro.fortech.bookshelf.service;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import ro.fortech.bookshelf.domain.Book;
import ro.fortech.bookshelf.service.BookService;
import ro.fortech.bookshelf.service.ValidationException;

public abstract class BaseBookServiceTest {

	protected abstract BookService getBookService();

	@After
	public void tearDown() {
		Collection<Book> books = new LinkedList<Book>(getBookService().listAll());

		for (Book book : books) {
			getBookService().delete(book.getId());
		}
	}

	@Test
	public void test_empty_get_all() {
		Collection<Book> books = getBookService().listAll();
		Assert.assertTrue(books.isEmpty());
	}

	@Test(expected = ValidationException.class)
	public void test_add_no_booktitle() throws ValidationException {
		Book book = new Book();
		book.setAuthor("Ion");
		book.setNumberOfPages(150);
		book.setPublicationYear(2005);
		book.setBookLanguage("Romanian");
		book.setBookLocation("Cluj");
		book.setDonatorEmail("abc@mail.com");
		getBookService().save(book);

	}

	@Test(expected = ValidationException.class)
	public void test_add_no_author() throws ValidationException {
		Book book = new Book();
		book.setBookTitle("Ion");
		book.setNumberOfPages(150);
		book.setPublicationYear(2005);
		book.setBookLanguage("Romanian");
		book.setBookLocation("Cluj");
		book.setDonatorEmail("abc@mail.com");
		getBookService().save(book);

	}

	@Test(expected = ValidationException.class)
	public void test_add_no_pages() throws ValidationException {
		Book book = new Book();
		book.setBookTitle("Ion");
		book.setAuthor("Ion Ion");
		book.setPublicationYear(2005);
		book.setBookLanguage("Romanian");
		book.setBookLocation("Cluj");
		book.setDonatorEmail("abc@mail.com");
		getBookService().save(book);

	}

	@Test(expected = ValidationException.class)
	public void test_add_no_language() throws ValidationException {
		Book book = new Book();
		book.setBookTitle("Ion");
		book.setAuthor("Ion Ion");
		book.setNumberOfPages(150);
		book.setPublicationYear(2005);
		book.setBookLocation("Cluj");
		book.setDonatorEmail("abc@mail.com");
		getBookService().save(book);

	}

	@Test(expected = ValidationException.class)
	public void test_add_no_location() throws ValidationException {
		Book book = new Book();
		book.setBookTitle("Ion");
		book.setAuthor("Ion Ion");
		book.setNumberOfPages(150);
		book.setPublicationYear(2005);
		book.setBookLanguage("Romanian");
		book.setDonatorEmail("abc@mail.com");
		getBookService().save(book);

	}

	@Test(expected = ValidationException.class)
	public void test_add_no_donator_email() throws ValidationException {
		Book book = new Book();
		book.setBookTitle("Ion");
		book.setAuthor("Ion Ion");
		book.setNumberOfPages(150);
		book.setPublicationYear(2005);
		book.setBookLanguage("Romanian");
		book.setBookLocation("Cluj");
		getBookService().save(book);

	}


	@Test(expected = ValidationException.class)
	public void test_add_empty() throws ValidationException {
		Book book = new Book();

		getBookService().save(book);

	}

	@Test
	public void test_add_valid_book() throws ValidationException {
		Book book = new Book();
		book.setBookTitle("Ion");
		book.setAuthor("Ion Ion");
		book.setNumberOfPages(150);
		book.setPublicationYear(2005);
		book.setBookLanguage("Romanian");
		book.setBookLocation("Cluj");
		book.setDonatorEmail("abc@mail.com");
		getBookService().save(book);
		
		Assert.assertEquals(1, getBookService().listAll().size());
		Book fromDB = getBookService().listAll().iterator().next();
		
		Assert.assertNotNull(fromDB);
		Assert.assertTrue(fromDB.getId() > 0);
		book.setId(fromDB.getId());
		Assert.assertEquals(book, fromDB);
	}

	@Test
	public void test_delete_inexistent() throws ValidationException {

		Assert.assertFalse(getBookService().delete(-10l));

	}

	@Test
	public void test_add_delete() throws Exception {
		Book book = new Book();
		book.setBookTitle("Ion");
		book.setAuthor("Ion Ion");
		book.setNumberOfPages(150);
		book.setPublicationYear(2005);
		book.setBookLanguage("Romanian");
		book.setBookLocation("Cluj");
		book.setDonatorEmail("abc@mail.com");
		getBookService().save(book);
		
		Assert.assertEquals(1, getBookService().listAll().size());
		Book fromDB = getBookService().listAll().iterator().next();

		Assert.assertTrue(getBookService().delete(fromDB.getId()));
		Assert.assertFalse(getBookService().delete(fromDB.getId()));
		Assert.assertEquals(0, getBookService().listAll().size());

	}

	@Test
	public void test_search_by_title_no_result() throws ValidationException {
		Book book = new Book();
		book.setBookTitle("Ion");
		book.setAuthor("Ion Ion");
		book.setNumberOfPages(150);
		book.setPublicationYear(2005);
		book.setBookLanguage("Romanian");
		book.setBookLocation("Cluj");
		book.setDonatorEmail("abc@mail.com");
		getBookService().save(book);
		Assert.assertEquals(0, getBookService().search("cucu").size());

	}

	@Test
	public void test_search_by_title_multiple_results() throws ValidationException {
		Book book = new Book();
		book.setBookTitle("Ion");
		book.setAuthor("Ion Ion");
		book.setNumberOfPages(150);
		book.setPublicationYear(2005);
		book.setBookLanguage("Romanian");
		book.setBookLocation("Cluj");
		book.setDonatorEmail("abc@mail.com");
		getBookService().save(book);

		book = new Book();
		book.setBookTitle("Ion");
		book.setAuthor("Carturescu");
		book.setNumberOfPages(150);
		book.setPublicationYear(2005);
		book.setBookLanguage("Romanian");
		book.setBookLocation("Cluj");
		book.setDonatorEmail("abc@mail.com");
		getBookService().save(book);
		Assert.assertEquals(2, getBookService().search("Ion").size());

	}

	@Test
	public void test_search_by_title_multiple_results_partial_search() throws ValidationException {
		Book book = new Book();
		book.setBookTitle("Ion");
		book.setAuthor("Ion Ion");
		book.setNumberOfPages(150);
		book.setPublicationYear(2005);
		book.setBookLanguage("Romanian");
		book.setBookLocation("Cluj");
		book.setDonatorEmail("abc@mail.com");
		getBookService().save(book);

		book = new Book();
		book.setBookTitle("Ion");
		book.setAuthor("Carturescu");
		book.setNumberOfPages(150);
		book.setPublicationYear(2005);
		book.setBookLanguage("Romanian");
		book.setBookLocation("Cluj");
		book.setDonatorEmail("abc@mail.com");
		getBookService().save(book);
		Assert.assertEquals(2, getBookService().search("Io").size());

	}

	@Test
	public void test_search_by_title_multiple_results_partial_case_insensitive() throws ValidationException {
		Book book = new Book();
		book.setBookTitle("Ion");
		book.setAuthor("Ion Ion");
		book.setNumberOfPages(150);
		book.setPublicationYear(2005);
		book.setBookLanguage("Romanian");
		book.setBookLocation("Cluj");
		book.setDonatorEmail("abc@mail.com");
		getBookService().save(book);
		
		book = new Book();
		book.setBookTitle("Ion");
		book.setAuthor("Florian");
		book.setNumberOfPages(1501);
		book.setPublicationYear(2005);
		book.setBookLanguage("Romanian");
		book.setBookLocation("Cluj");
		book.setDonatorEmail("abc@mail.com");
		getBookService().save(book);

		book = new Book();
		book.setBookTitle("Ion");
		book.setAuthor("Carturescu");
		book.setNumberOfPages(150);
		book.setPublicationYear(2005);
		book.setBookLanguage("Romanian");
		book.setBookLocation("Cluj");
		book.setDonatorEmail("abc@mail.com");
		getBookService().save(book);
		Assert.assertEquals(2, getBookService().search("iOn").size());

	}

}
