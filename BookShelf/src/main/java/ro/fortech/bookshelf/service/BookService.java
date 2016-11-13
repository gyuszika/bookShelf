package ro.fortech.bookshelf.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ro.fortech.bookshelf.dao.BookDao;
import ro.fortech.bookshelf.domain.Book;
import ro.fortech.bookshelf.service.ValidationException;

@Service
public class BookService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);

	@Autowired
	private BookDao dao;

	public Collection<Book> listAll() {
		return dao.getAll();
	}

	public Collection<Book> search(String query) {
		LOGGER.debug("Searching for " + query);
		return dao.searchByName(query);
	}

	public boolean delete(Long id) {
		LOGGER.debug("Deleting book for id: " + id);
		Book book = dao.findById(id);
		if (book != null) {
			dao.delete(book);
			return true;
		}

		return false;
	}

	public Book get(Long id) {
		LOGGER.debug("Getting book for id: " + id);
		return dao.findById(id);

	}

	public void save(Book book) throws ValidationException {
		LOGGER.debug("Saving: " + book);
		validate(book);

		dao.update(book);
	}

	private void validate(Book book) throws ValidationException {
		List<String> errors = new LinkedList<String>();

		if (StringUtils.isEmpty(book.getBookTitle())) {
			errors.add("Book Title is empty");
		}

		if (StringUtils.isEmpty(book.getAuthor())) {
			errors.add("Author name is empty");
		}

		if (StringUtils.isEmpty(book.getBookLanguage())) {
			errors.add("Book language is empty!");
		}

		if (book.getNumberOfPages() <= 0) {
			errors.add("Number of pages must be greater than 0!");
		}

		if (StringUtils.isEmpty(book.getBookLocation())) {
			errors.add("Book location is empty");
		}
		if (StringUtils.isEmpty(book.getDonatorEmail())) {
			errors.add("Donator email is empty");
		}

		if (!errors.isEmpty()) {
			throw new ValidationException(errors.toArray(new String[] {}));
		}
	}

	public BookDao getDao() {
		return dao;
	}

	public void setDao(BookDao dao) {
		this.dao = dao;
	}

}
