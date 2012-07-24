package puf.m2.basket.model.entity;

import java.sql.SQLException;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbScoreDetail;
import puf.m2.basket.db.entity.PlayerRef;
import puf.m2.basket.db.entity.TeamRef;

public class ScoreDetail extends DbScoreDetail implements ORAData,
        ORADataFactory {
    private static final ScoreDetail _ScoreDetailFactory = new ScoreDetail();

    public static ORADataFactory getORADataFactory() {
        return _ScoreDetailFactory;
    }

    public ScoreDetail() {
        super();
    }

    public ScoreDetail(Integer id, TeamRef team, PlayerRef player, Integer value)
            throws SQLException {
        setId(id);
        setTeam(team);
        setPlayer(player);
        setValue(value);
    }

    /* ORAData interface */
    @Override
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(new ScoreDetail(), d, sqlType);
    }

}
