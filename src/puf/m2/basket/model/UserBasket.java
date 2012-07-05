package puf.m2.basket.model;

import java.util.List;
import java.util.Map;

import puf.m2.basket.db.DbProp;
import puf.m2.basket.exception.BasketException;
import puf.m2.basket.exception.UserException;
import puf.m2.basket.model.support.Condition;

public class UserBasket extends BasketEntity {

	protected static final Map<Integer, UserBasket> MAP = new CacheAwareMap<Integer, UserBasket>();

	@DbProp
	private String username;
	@DbProp
	private String password;

	public UserBasket() {
		super();
	}

	public UserBasket(String name, String password) {
		this.username = name;
		this.password = password;
	}

	public static UserBasket requestLogin(String username, String password) {

		Condition c = new Condition("username", username.toUpperCase());
		c.and(new Condition("password", password.toUpperCase())).and(
				new Condition("deleted", "0"));

		UserBasket user = null;
		try {
			List<UserBasket> userList = getByCondition(c, UserBasket.class);
			if (userList.size() == 1) {
				user = userList.get(0);
			}
		} catch (BasketException e) {

		}

		return user;

	}

	public static UserBasket getUserByName(String username) {

		Condition c = new Condition("name", username);
		c.and(new Condition("deleted", "0"));

		UserBasket user = null;
		try {
			List<UserBasket> userList = getByCondition(c, UserBasket.class);
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
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
