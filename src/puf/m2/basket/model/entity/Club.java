package puf.m2.basket.model.entity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import puf.m2.basket.db.entity.DbCategories;
import puf.m2.basket.db.entity.DbClub;
import puf.m2.basket.db.entity.OfficeRef;
import puf.m2.basket.db.entity.PresidentRef;
import puf.m2.basket.db.entity.SecretaryRef;
import puf.m2.basket.db.entity.TreasurerRef;
import puf.m2.basket.db.entity.VicePresidentRef;
import puf.m2.basket.model.support.BasketException;

public class Club extends DbClub implements ORAData, ORADataFactory {
    
    public static final String TABLE_NAME = "club";

    private static final Club _ClubFactory = new Club();
    
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
            DbCategories listcategory) throws SQLException {
        setId(id);
        setClubName(clubName);
        setClubOffice(clubOffice);
        setClubPresident(clubPresident);
        setClubVicePresident(clubVicePresident);
        setClubTreasurer(clubTreasurer);
        setClubSecretary(clubSecretary);
        setListcategory(listcategory);
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
    
    public void save() throws BasketException {
        DbCategories dbCategories = new DbCategories(categories.toArray(new Category[] {}));
        try {
            setListcategory(dbCategories);
        } catch (SQLException e) {
            new BasketException(e);
        }
        
        super.save();
    }
}
