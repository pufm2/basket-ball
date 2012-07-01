package puf.m2.basket.model;

import java.util.List;
import java.util.Map;

import puf.m2.basket.db.DbProp;
import puf.m2.basket.exception.BasketException;
import puf.m2.basket.exception.UserException;
import puf.m2.basket.model.support.Condition;

public class User extends BasketEntity {

	protected static final Map<Integer, User> MAP = new CacheAwareMap<Integer, User>();

	@DbProp
	private String name;
	@DbProp
	private String password;

	public User() {
		super();
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public static User login(String username, String password) {

		Condition c = new Condition("name", username);
		c.and(new Condition("password", password)).and(
				new Condition("deleted", "0"));

		User user = null;
		try {
			List<User> userList = getByCondition(c, User.class);
			if (userList.size() == 1) {
				user = userList.get(0);
			}
		} catch (BasketException e) {

		}

		return user;

	}

	public static User getUserByName(String username) {

		Condition c = new Condition("name", username);
		c.and(new Condition("deleted", "0"));

		User user = null;
		try {
			List<User> userList = getByCondition(c, User.class);
			if (userList.size() == 1) {
				user = userList.get(0);
			}
		} catch (BasketException e) {

		}

		return user;

	}

	public void save() throws UserException {
		try {
			super.save();
			MAP.put(id, this);
		} catch (BasketException e) {
			throw new UserException(e);
		}

	}

	public void update() throws UserException {

		try {
			super.update();
		} catch (BasketException e) {
			throw new UserException(e);
		}
	}

	public String getUsername() {
		return name;
	}

	public void setUsername(String username) {
		this.name = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
