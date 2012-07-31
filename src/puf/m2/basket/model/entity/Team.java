package puf.m2.basket.model.entity;

import java.sql.SQLException;
import java.util.List;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbTeam;
import puf.m2.basket.model.entity.ref.TeamRef;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.EntityUtils;

public class Team extends DbTeam {
    public static final String TABLE_NAME = "TEAM";

    private static final Team _TeamFactory = new Team();

    private TeamRef ref;

    public static ORADataFactory getORADataFactory() {
        return _TeamFactory;
    }

    public Team() {

    }

    public Team(Integer id, String teamName, Integer deleted)
            throws SQLException {
        super(id, teamName, deleted);
    }

    /* ORAData interface */
    @Override
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(new Team(), d, sqlType);
    }

    public TeamRef getRef() throws BasketException {
        if (ref == null) {
            ref = getRef(TeamRef.class);
        }
        return ref;
    }

    public static Team loadById(int id) throws BasketException {

        return EntityUtils.loadById(id, Team.class);

    }
        
    public String toString(){
    	String result = "";
    	try {
			result = getTeamName();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return result;
    }

}
