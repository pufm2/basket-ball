package puf.m2.basket.model.entity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbMatch;
import puf.m2.basket.db.entity.DbScoreDetails;
import puf.m2.basket.db.entity.SeasonRef;
import puf.m2.basket.model.support.BasketException;

public class Match extends DbMatch implements ORAData, ORADataFactory {
    
    public static final String TABLE_NAME = "match";
    private static final Match _MatchFactory = new Match();

    List<ScoreDetail> scoreDetails = new ArrayList<ScoreDetail>();
    
    public static ORADataFactory getORADataFactory() {
        return _MatchFactory;
    }

    public Match() {
        super();
    }

    public Match(Integer id, java.sql.Timestamp matchDate, SeasonRef season,
            DbScoreDetails details) throws SQLException {
        setId(id);
        setMatchDate(matchDate);
        setSeason(season);
        setDetails(details);
    }

    /* ORAData interface */
    @Override
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(new Match(), d, sqlType);
    }


    public List<ScoreDetail> getScoreDetails() {
        return scoreDetails;
    }
    
    public void addScoreDetail(ScoreDetail scoreDetail) {
        scoreDetails.add(scoreDetail);
    }
    
    public void save() throws BasketException {
        DbScoreDetails dbScoreDetails = new DbScoreDetails(scoreDetails.toArray(new ScoreDetail[]{}));
        try {
            setDetails(dbScoreDetails);
        } catch (SQLException e) {
            throw new BasketException(e);
        }
        
        super.save();
    }
}
