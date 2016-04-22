package dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import annotations.Column;
import annotations.Table;


public class BaseDaoImpl<T> implements IBaseDao<T>{
	private Connection conn;
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/demo-db?user=root&password=password_pinglian";
			conn = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int insert(T entity) {
		try {
			String sql = getModifySql(entity);
			System.out.println(sql);
			Statement sts = conn.createStatement();
			sts.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = sts.getGeneratedKeys();  
			if(rs.next()){  
				return rs.getInt(1);  
			}  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(T entity) {
		try {
			String sql = getModifySql(entity);
			Statement sts = conn.createStatement();
			return sts.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<T> select() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	private String getModifySql(T entity) throws Exception {
		StringBuffer sb = null;
		Class clz = entity.getClass();
		if(clz.isAnnotationPresent(Table.class)) {
			Table table = (Table)clz.getAnnotation(Table.class);
			if (null == table) {
				return  sb.toString();
			}else {
				sb = new StringBuffer("insert into "+table.name() +"(");
			}
		}else {
			sb = new StringBuffer("insert into "+clz.getSimpleName().toLowerCase() +"(");
		}
		Field[] fds = clz.getDeclaredFields();
		List<String> columnList = null;	//存储所有列名信息
		List<String> valueList = null;	//存储所有值信息
		if(null != fds && fds.length > 0) {
			Field fd = null;
			Column column = null;
			columnList = new ArrayList<String>();
			valueList = new ArrayList<String>();
			for(int i = 0;i< fds.length;i++) {
				fd = fds[i];
				if(fd.isAnnotationPresent(Column.class)) {
					column = fd.getAnnotation(Column.class);
					fd.setAccessible(true);
					Object obj = fd.get(entity);
					if(!column.isPremaryKey()) {
						columnList.add(column.fieldName());
						if(obj != null) {
							valueList.add(obj+"");
						}else {
							valueList.add("null");
						}
						
					} else {
						if(obj != null) {
							columnList.add(column.fieldName());
							valueList.add(obj+"");
						}
					}
				}
			}
		}
		
		StringBuffer valueBuffer = new StringBuffer(" values(");
		if(columnList !=null && valueList != null) {
			for(int i = 0;i<columnList.size();i++) {
				if(i == 0) {
					sb.append(columnList.get(i));
				}else {
					sb.append(","+columnList.get(i));
				}
			}
			sb.append(")");
			for(int i = 0;i<valueList.size();i++) {
				if(i == 0) {
					valueBuffer.append(valueList.get(i));
				}else {
					valueBuffer.append(","+valueList.get(i));
				}
			}
			valueBuffer.append(")");
			sb.append(valueBuffer);
			
		}
		return sb.toString();
		
	}
	
}
