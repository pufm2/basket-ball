package puf.m2.basket.model.entity;

import java.sql.SQLException;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbPerson;

public class Person extends DbPerson implements ORAData, ORADataFactory {
    private static final Person _PersonFactory = new Person();

    public static ORADataFactory getORADataFactory() {
        return _PersonFactory;
    }

    protected Person() {
        super();
    }

    protected Person(Integer id, String personName, Integer deleted)
            throws SQLException {
        super(id, personName, deleted);
    }

    /* ORAData interface */
    @Override
    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        return create(new Person(), d, sqlType);
    }
    
    public String toString(){
    	String result = "";
    	try {
			result = getPersonName();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return result;
    }

}
