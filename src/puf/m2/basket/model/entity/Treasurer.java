package puf.m2.basket.model.entity;

import java.sql.SQLException;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbTreasurer;
import puf.m2.basket.model.entity.ref.TreasurerRef;

public class Treasurer extends DbTreasurer implements ORAData, ORADataFactory {

    public static final String TABLE_NAME = "Treasurer";
    private static final Treasurer _TreasurerFactory = new Treasurer();
    
    private TreasurerRef ref;

    public static ORADataFactory getORADataFactory() {
        return _TreasurerFactory;
    }

    public Treasurer() {
        super();
    }

    public Treasurer(Integer id, String personName, Integer deleted)
            throws SQLException {
        super(id, personName, deleted);
    }

    /* ORAData interface */
    @Override
    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        return create(new Treasurer(), d, sqlType);
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
