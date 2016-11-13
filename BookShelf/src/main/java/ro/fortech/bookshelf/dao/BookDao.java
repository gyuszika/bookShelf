package ro.fortech.bookshelf.dao;

import java.util.Collection;

import ro.fortech.bookshelf.domain.Book;

public interface BookDao extends BaseDao<Book>{

	Collection<Book> searchByName(String query);

}
