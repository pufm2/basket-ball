package puf.m2.basket.model.entity;

import java.sql.SQLException;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbSeason;
import puf.m2.basket.model.entity.ref.SeasonRef;

public class Season extends DbSeason implements ORAData, ORADataFactory {
    
    public static final String TABLE_NAME = "season";
    
   // private SeasonRef ref;
    
    private static final Season _SeasonFactory = new Season();

    public static ORADataFactory getORADataFactory() {
        return _SeasonFactory;
    }

    public Season() {
        super();
    }

    public Season(Integer id, String seasonName,
            java.sql.Timestamp seasonStartdate,
            java.sql.Timestamp seasonEnddate, Integer deleted)
            throws SQLException {
        setId(id);
        setSeasonName(seasonName);
        setSeasonStartdate(seasonStartdate);
        setSeasonEnddate(seasonEnddate);
        setDeleted(deleted);
    }

    /* ORAData interface */
    @Override
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(new Season(), d, sqlType);
    }

    public String toString(){
    	String result = "";
    	try {
			result = getSeasonName();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return result;
    }
}
