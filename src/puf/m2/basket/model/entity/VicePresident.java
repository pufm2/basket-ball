package puf.m2.basket.model.entity;

import java.sql.SQLException;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbVicePresident;
import puf.m2.basket.model.entity.ref.VicePresidentRef;

public class VicePresident extends DbVicePresident implements ORAData,
        ORADataFactory {

    public static final String TABLE_NAME = "vice_president";

    private static final VicePresident _VicePresidentFactory = new VicePresident();
    
    private VicePresidentRef ref;

    public static ORADataFactory getORADataFactory() {
        return _VicePresidentFactory;
    }

    public VicePresident() {
        super();
    }

    public VicePresident(Integer id, String personName, Integer deleted)
            throws SQLException {
        super(id, personName, deleted);
    }

    /* ORAData interface */
    @Override
    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        return create(new VicePresident(), d, sqlType);
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
