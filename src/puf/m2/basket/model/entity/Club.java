package puf.m2.basket.model.entity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbCategories;
import puf.m2.basket.db.entity.DbClub;
import puf.m2.basket.model.entity.ref.ClubRef;
import puf.m2.basket.model.entity.ref.OfficeRef;
import puf.m2.basket.model.entity.ref.PresidentRef;
import puf.m2.basket.model.entity.ref.SecretaryRef;
import puf.m2.basket.model.entity.ref.TreasurerRef;
import puf.m2.basket.model.entity.ref.VicePresidentRef;
import puf.m2.basket.model.support.BasketException;

public class Club extends DbClub implements ORAData, ORADataFactory {

    public static final String TABLE_NAME = "club";

    private static final Club _ClubFactory = new Club();
    
   // private ClubRef ref;

    List<Category> categories = new ArrayList<Category>();

    public static ORADataFactory getORADataFactory() {
        return _ClubFactory;
    }

    public Club() {
        super();
    }

    public Club(Integer id, String clubName, OfficeRef clubOffice,
            PresidentRef clubPresident, VicePresidentRef clubVicePresident,
            TreasurerRef clubTreasurer, SecretaryRef clubSecretary,
            DbCategories listcategory, Integer deleted) throws SQLException {
        super(id, clubName, clubOffice, clubPresident, clubVicePresident,
                clubTreasurer, clubSecretary, listcategory, deleted);
    }

    /* ORAData interface */
    @Override
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(new Club(), d, sqlType);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    @Override
    public void save() throws BasketException {
        DbCategories dbCategories = new DbCategories(
                categories.toArray(new Category[] {}));
        try {
            setListcategory(dbCategories);
        } catch (SQLException e) {
            new BasketException(e);
        }

        super.save();
    }
    
    public String toString(){
    	String result = "";
    	try {
			result = getClubName();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return result;
    }
}
