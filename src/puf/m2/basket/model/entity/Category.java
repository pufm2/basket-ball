package puf.m2.basket.model.entity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbCategory;
import puf.m2.basket.db.entity.DbTeams;
import puf.m2.basket.model.support.BasketException;

public class Category extends DbCategory implements ORAData, ORADataFactory {
    public static final String TABLE_NAME = "category";

    private static final Category _CategoryFactory = new Category();
    
   // private CategoryRef ref;

    private List<Team> teams = new ArrayList<Team>();

    public static ORADataFactory getORADataFactory() {
        return _CategoryFactory;
    }

    public Category() {
        super();
    }

    public Category(Integer id, String categoryName, DbTeams listteam,
            Integer deleted) throws SQLException {
        super(id, categoryName, listteam, deleted);
    }

    /* ORAData interface */
    @Override
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(new Category(), d, sqlType);
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    @Override
    public void save() throws BasketException {
        DbTeams dbTeams = new DbTeams(teams.toArray(new Team[] {}));
        try {
            setListteam(dbTeams);
        } catch (SQLException e) {
            throw new BasketException(e);
        }

        super.save();
    }
    
    public String toString(){
    	String result = "";
    	try {
			result = getCategoryName();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return result;
    }

}
