package ro.fortech.bookshelf.dao.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ro.fortech.bookshelf.dao.BookDao;
import ro.fortech.bookshelf.domain.Book;

public class JdbcTemplateBookDao implements BookDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplateBookDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Collection<Book> getAll() {
		return jdbcTemplate.query("select * from book", new BookMapper());
	}

	@Override
	public Book findById(Long id) {
		return jdbcTemplate.queryForObject("select * from book where id = ?", new Long[] { id }, new BookMapper());
	}

	@Override
	public Book update(Book model) {

		String sql = "";
		Long newId = null;
		if (model.getId() > 0) {
			sql = "update book set book_title=?, author=?, book_language=?, number_of_pages=?, publication_year=?, book_location=?, donator_email=? "
					+ "where id = ? returning id";
			newId = jdbcTemplate.queryForObject(sql,
					new Object[] { 
							model.getBookTitle(), 
							model.getAuthor(), 
							model.getBookLanguage(),
							model.getNumberOfPages(), 
							model.getPublicationYear(), 
							model.getBookLocation(),
							model.getDonatorEmail(), 
							model.getId()

					}, new RowMapper<Long>() {
						public Long mapRow(ResultSet rs, int arg1) throws SQLException {
							return rs.getLong(1);
						}
					});
		} else {
			sql = "insert into book (book_title, author, book_language, number_of_pages, publication_year, book_location, donator_email) "
					+ "values (?, ?, ?, ?, ?, ?, ?) returning id";

			newId = jdbcTemplate.queryForObject(sql,
					new Object[] { 
							model.getBookTitle(),
							model.getAuthor(),
							model.getBookLanguage(),
							model.getNumberOfPages(),
							model.getPublicationYear(), 
							model.getBookLocation(),
							model.getDonatorEmail()

					}, new RowMapper<Long>() {
						public Long mapRow(ResultSet rs, int arg1) throws SQLException {
							return rs.getLong(1);
						}
					});
		}
		model.setId(newId);

		return model;
	}

	@Override
	public boolean delete(Book model) {

		jdbcTemplate.update("DELETE FROM book WHERE id = ?",  new Object[] { model.getId()});
		return true;
	}

	@Override
	public Collection<Book> searchByName(String query) {
		return null;
	}

	private static class BookMapper implements RowMapper<Book> {

		@Override
		public Book mapRow(ResultSet rs, int arg1) throws SQLException {
			Book book = new Book();
			book.setId(rs.getLong("id"));
			book.setBookTitle(rs.getString("book_title"));
			book.setAuthor(rs.getString("author"));
			book.setBookLanguage(rs.getString("book_language"));
			book.setNumberOfPages(rs.getInt("number_of_pages"));
			book.setPublicationYear(rs.getInt("publication_year"));
			book.setBookLocation(rs.getString("book_location"));
			book.setDonatorEmail(rs.getString("donator_email"));
			return book;
		}

	}

}
