package ro.fortech.bookshelf.dao;

import java.util.Collection;

import ro.fortech.bookshelf.domain.AbstractModel;

public interface BaseDao<T extends AbstractModel> {

	Collection<T> getAll();
	
	T findById(Long id);
	
	T update(T model);
	
	boolean delete(T model);

}
