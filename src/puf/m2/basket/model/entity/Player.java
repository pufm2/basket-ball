package puf.m2.basket.model.entity;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbPlayer;
import puf.m2.basket.db.entity.DbTeamMany;
import puf.m2.basket.db.entity.DbTeamWithPlayer;
import puf.m2.basket.model.entity.ref.PlayerRef;
import puf.m2.basket.model.support.BasketException;
import puf.m2.basket.model.support.EntityUtils;

public class Player extends DbPlayer {

    public static final String TABLE_NAME = "PLAYER";

    private static final Player _PlayerFactory = new Player();
    private List<Team> teams = new ArrayList<Team>();

    private PlayerRef ref;

    public static ORADataFactory getORADataFactory() {
        return _PlayerFactory;
    }

    public Player() {

    }

    public Player(Integer id, String personName, Integer deleted,
            String playerLicenceNumber, java.sql.Timestamp playerBirthday,
            Address playerAddress, DbTeamWithPlayer teamWithPlayers)
            throws SQLException {
        super(id, personName, deleted, playerLicenceNumber, playerBirthday,
                playerAddress, teamWithPlayers);
    }

    /* ORAData interface */
    @Override
    protected ORAData createExact(Datum d, int sqlType) throws SQLException {
        return create(new Player(), d, sqlType);
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public PlayerRef getRef() throws BasketException {
        if (ref == null) {
            ref = getRef(PlayerRef.class);
        }
        return ref;
    }

    public static Player loadById(int id) throws BasketException {

        return EntityUtils.loadById(id, Player.class);
    }

    @Override
    public void save() throws BasketException {

        List<DbTeamMany> tmList = new ArrayList<DbTeamMany>();

        try {

            for (Team team : teams) {
                tmList.add(new DbTeamMany(team.getRef(), new Timestamp(System
                        .currentTimeMillis()), new Timestamp(System
                        .currentTimeMillis())));
            }
            setTeamWithPlayers(new DbTeamWithPlayer(
                    tmList.toArray(new DbTeamMany[] {})));

        } catch (Exception e) {
            throw new BasketException(e);

        }

        super.save();
    }

    @Override
    public void update() throws BasketException {

        List<DbTeamMany> tmList = new ArrayList<DbTeamMany>();

        try {

            for (Team team : teams) {
                tmList.add(new DbTeamMany(team.getRef(), new Timestamp(System
                        .currentTimeMillis()), new Timestamp(System
                        .currentTimeMillis())));
            }
            setTeamWithPlayers(new DbTeamWithPlayer(
                    tmList.toArray(new DbTeamMany[] {})));

        } catch (Exception e) {
            throw new BasketException(e);

        }

        super.update();
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
