package puf.m2.basket.model.entity;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbCoach;
import puf.m2.basket.db.entity.DbTeamMany;
import puf.m2.basket.db.entity.DbTeamWithCoach;
import puf.m2.basket.db.entity.DbTeamWithPlayer;
import puf.m2.basket.model.support.BasketException;

public class Coach extends DbCoach implements ORAData, ORADataFactory {

    public static final String TABLE_NAME = "coach";
    private static final Coach _CoachFactory = new Coach();

    private List<Team> teams = new ArrayList<Team>();

    public static ORADataFactory getORADataFactory() {
        return _CoachFactory;
    }

    public Coach() {
        super();
    }

    public Coach(Integer id, String personName, DbTeamWithCoach teamWithCoach)
            throws SQLException {
        setId(id);
        setPersonName(personName);
        setTeamWithCoach(teamWithCoach);
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    /* ORAData interface */
    @Override
    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        return create(new Coach(), d, sqlType);
    }

    public void save() throws BasketException {

        List<DbTeamMany> tmList = new ArrayList<DbTeamMany>();
        try {

            for (Team team : teams) {
                tmList.add(new DbTeamMany(team.getRef(), new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis())));
            }
            setTeamWithCoach(new DbTeamWithCoach(tmList.toArray(new DbTeamMany[]{})));

        } catch (Exception e) {
            throw new BasketException(e);

        }
        super.save();
    }
}
