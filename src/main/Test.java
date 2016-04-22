package main;

import dao.BaseDaoImpl;
import dao.IBaseDao;
import entity.User;

public class Test {

	public static void main(String[] args) {
		User user = new User();
		user.setPassword("3453");
		
		try {
			IBaseDao<User> dao = new BaseDaoImpl<User>();
			System.out.println(dao.insert(user));;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
