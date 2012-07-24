package puf.m2.basket.model.entity;

import java.sql.SQLException;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbSeason;
import puf.m2.basket.db.entity.SeasonRef;
import puf.m2.basket.model.support.BasketException;

public class Season extends DbSeason implements ORAData, ORADataFactory {
    
    public static final String TABLE_NAME = "season";
    private static final Season _SeasonFactory = new Season();
    
    private SeasonRef ref;

    public static ORADataFactory getORADataFactory() {
        return _SeasonFactory;
    }

    public Season() {
        super();
    }

    public Season(Integer id, java.sql.Timestamp seasonStartdate,
            java.sql.Timestamp seasonEnddate) throws SQLException {
        setId(id);
        setSeasonStartdate(seasonStartdate);
        setSeasonEnddate(seasonEnddate);
    }

    /* ORAData interface */
    @Override
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(new Season(), d, sqlType);
    }

    public SeasonRef getRef() throws BasketException {
        if (ref == null) {
            ref = getRef(SeasonRef.class);
        }
        return ref;
    }
}
