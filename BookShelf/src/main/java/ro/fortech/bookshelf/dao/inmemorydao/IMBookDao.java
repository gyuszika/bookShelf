package ro.fortech.bookshelf.dao.inmemorydao;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.util.StringUtils;

import ro.fortech.bookshelf.dao.BookDao;
import ro.fortech.bookshelf.domain.Book;

//@Component
public class IMBookDao extends IMBaseDao<Book> implements BookDao {

	@Override
	public Collection<Book> searchByName(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAll();
		}

		Collection<Book> all = new LinkedList<Book>(getAll());
		for (Iterator<Book> it = all.iterator(); it.hasNext();) {
			Book book = it.next();
			String ss = book.getBookTitle() + " " + book.getAuthor();
			if (!ss.toLowerCase().contains(query.toLowerCase())) {
				it.remove();
			}
		}
		return all;
	}

}
