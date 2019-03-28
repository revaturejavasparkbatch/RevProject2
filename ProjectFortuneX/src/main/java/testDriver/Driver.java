package testDriver;

import java.util.List;

import org.hibernate.Session;

import com.revature.bean.Fortune;
import com.revature.bean.User;
import com.revature.dao.FortuneDao;
import com.revature.dao.FortuneDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.util.HibernateUtil;

public class Driver {
	public static void main(String[] args) {
		Session s = HibernateUtil.getSession();
		UserDao ud = new UserDaoImpl();
		FortuneDao fd = new FortuneDaoImpl();
		
		//create users
		User u1 = new User("wconstanza0@bbb.org", "Wilie", "Constanza", "guest1");
		User u2 = new User("tkemp1@bing.com", "Tina", "Kemp", "guest2");
		User u3 = new User("daronin2@buzzfeed.com", "Dal", "Aronin", "guest3");
		User u4 = new User("rduckwith3@deliciousdays.com", "Rossie", "Duckwith", "guest4");
		User u5 = new User("deleteme@email.com", "delete", "me", "guest5");
		
		//store users into DB
		ud.createUser(u1);
		ud.createUser(u2);
		ud.createUser(u3);
		ud.createUser(u4);
		ud.createUser(u5);
		
		//list all users in DB
//		List<User> users = ud.getAllUsers();
//		for(User u : users) {
//			System.out.println(u);
//		}
		
		//list specific user by email
//		User storedUser = ud.getUserByEmail("wconstanza0@bbb.org");
//		System.out.println(storedUser);
		
		//update user in DB
//		User change = storedUser;
//		change.setfName("George");
//		change.setPassword("p4ssw0rd");
//		ud.updateUser(change);
		
		//delete user with no fortunes in DB 
//		User storedUser = ud.getUserByEmail("rduckwith3@deliciousdays.com");
//		ud.deleteUser(storedUser);
		
		//delete user with fortunes in DB
//		User storedUser = ud.getUserByEmail("wconstanza0@bbb.org");
//		ud.deleteUser(storedUser);
		
		
		//create fortunes
		Fortune f1 = new Fortune("c07b32", u1, 7);
		Fortune f2 = new Fortune("a327ca", u1, 16);
		Fortune f3 = new Fortune("d432a9", u1, 83);
		Fortune f4 = new Fortune("17b4aa", u3, 91);
		Fortune f5 = new Fortune("9177c2", u4, 2);
		
		//store fortunes into DB
		fd.createFortune(f1);
		fd.createFortune(f2);
		fd.createFortune(f3);
		fd.createFortune(f4);
		fd.createFortune(f5);
		
		//list all fortunes of specified user
//		User storedUser = ud.getUserByEmail("rduckwith3@deliciousdays.com");
//		List<Fortune> usersFortunes = fd.getAllFortunesByUser(storedUser.getId());
//		
//		for(Fortune uf : usersFortunes) {
//			System.out.println(uf);
//		}
		
		//delete fortune
//		User storedUser = ud.getUserByEmail("rduckwith3@deliciousdays.com");
//		fd.deleteFortune("9177c2", storedUser.getId());
		
		HibernateUtil.closeSessionFactory();
	}
}
