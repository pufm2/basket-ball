package puf.m2.basket.model.entity;

import java.sql.SQLException;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbUser;
import puf.m2.basket.model.entity.ref.UserRef;

public class User extends DbUser implements ORAData, ORADataFactory {

    public static final String TABLE_NAME = "USERBASKET";

    private static final User _UserFactory = new User();
    
    private UserRef ref;

    public static ORADataFactory getORADataFactory() {
        return _UserFactory;
    }

    public User() {
        super();
    }

    public User(Integer id, String username, String password, Integer deleted)
            throws SQLException {
        setId(id);
        setUsername(username);
        setPassword(password);
        setDeleted(deleted);
    }

    /* ORAData interface */
    @Override
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(new User(), d, sqlType);
    }
    

    
    public String toString(){
    	String result = "";
    	try {
			result = getUsername();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return result;
    }

}
