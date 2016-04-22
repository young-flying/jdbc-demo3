package utils;

import java.lang.reflect.Field;

import annotations.Column;
import annotations.Table;

public class ConvertUtils {
	public static String createSql(Class clz) {
		StringBuffer sb = null;
		if(null == clz)
			return sb.toString();
		Field[] fds = clz.getDeclaredFields();
		Column column = null;
		Table table = null;
		if(clz.isAnnotationPresent(Table.class)) {
			table = (Table)clz.getAnnotation(Table.class);
			if (null == table) {
				return  sb.toString();
			}else {
				sb = new StringBuffer("create table "+table.name() +"(\r\n");
			}
		}else {
			sb = new StringBuffer("create table "+clz.getSimpleName().toLowerCase() +"(\r\n");
		}
		if(null != fds && fds.length > 0) {
			Field fd = null;
			for(int i = 0;i< fds.length;i++) {
				fd = fds[i];
				if(fd != null) {
					if(fd.isAnnotationPresent(Column.class)) {
						column = fd.getAnnotation(Column.class);
						sb.append(""+column.fieldName()+" "+column.type().toDbType()+"("+column.length()+") ");
						if(column.isPremaryKey()) {
							sb.append("not null auto_increment primary key");
						}else {
							sb.append("default "+column.defaultValue());
						}
						if(i < fds.length-1) {
							sb.append(",\r\n");
						}else {
							sb.append(")");
						}
					}
				}
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
	}
}
