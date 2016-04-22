package dao;

import java.util.List;

public interface IBaseDao<T> {
	int insert(T entity);
	
	int deleteById(Long id);
	
	int update(T entity);
	
	List<T> select();
}
