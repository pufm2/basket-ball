package puf.m2.basket.db.entity;

import java.sql.SQLException;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;
import puf.m2.basket.model.entity.Club;
import puf.m2.basket.model.entity.ref.OfficeRef;
import puf.m2.basket.model.entity.ref.PresidentRef;
import puf.m2.basket.model.entity.ref.SecretaryRef;
import puf.m2.basket.model.entity.ref.TreasurerRef;
import puf.m2.basket.model.entity.ref.VicePresidentRef;
import puf.m2.basket.model.support.DbProp;

public class DbClub extends Entity implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "BASKET_USER.T_CLUB";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    protected static int[] _sqlType = { 4, 12, 2006, 2006, 2006, 2006, 2006,
            2003, 4 };
    protected static ORADataFactory[] _factory = new ORADataFactory[9];
    static {
        _factory[2] = OfficeRef.getORADataFactory();
        _factory[3] = PresidentRef.getORADataFactory();
        _factory[4] = VicePresidentRef.getORADataFactory();
        _factory[5] = TreasurerRef.getORADataFactory();
        _factory[6] = SecretaryRef.getORADataFactory();
        _factory[7] = DbCategories.getORADataFactory();
    }
    protected static final DbClub _DbClubFactory = new DbClub();

    public static ORADataFactory getORADataFactory() {
        return _DbClubFactory;
    }

    /* constructors */
    protected void _init_struct(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[9], _sqlType, _factory);
    }

    public DbClub() {
        _init_struct(true);
    }

    public DbClub(Integer id, String clubName, OfficeRef clubOffice,
            PresidentRef clubPresident, VicePresidentRef clubVicePresident,
            TreasurerRef clubTreasurer, SecretaryRef clubSecretary,
            DbCategories listcategory, Integer deleted) throws SQLException {
        _init_struct(true);
        setId(id);
        setClubName(clubName);
        setClubOffice(clubOffice);
        setClubPresident(clubPresident);
        setClubVicePresident(clubVicePresident);
        setClubTreasurer(clubTreasurer);
        setClubSecretary(clubSecretary);
        setListcategory(listcategory);
        setDeleted(deleted);
    }

    /* ORAData interface */
    @Override
    public Datum toDatum(Connection c) throws SQLException {
        return _struct.toDatum(c, _SQL_NAME);
    }

    /* ORADataFactory interface */
    @Override
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    protected ORAData create(DbClub o, Datum d, int sqlType)
            throws SQLException {
        if (d == null)
            return null;
        if (o == null)
            o = new Club();
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    /* accessor methods */
    @Override
    @DbProp
    public Integer getId() throws SQLException {
        return (Integer) _struct.getAttribute(0);
    }

    public void setId(Integer id) throws SQLException {
        _struct.setAttribute(0, id);
    }

    @DbProp
    public String getClubName() throws SQLException {
        return (String) _struct.getAttribute(1);
    }

    public void setClubName(String clubName) throws SQLException {
        _struct.setAttribute(1, clubName);
    }

    @DbProp
    public OfficeRef getClubOffice() throws SQLException {
        return (OfficeRef) _struct.getAttribute(2);
    }

    public void setClubOffice(OfficeRef clubOffice) throws SQLException {
        _struct.setAttribute(2, clubOffice);
    }

    @DbProp
    public PresidentRef getClubPresident() throws SQLException {
        return (PresidentRef) _struct.getAttribute(3);
    }

    public void setClubPresident(PresidentRef clubPresident)
            throws SQLException {
        _struct.setAttribute(3, clubPresident);
    }

    @DbProp
    public VicePresidentRef getClubVicePresident() throws SQLException {
        return (VicePresidentRef) _struct.getAttribute(4);
    }

    public void setClubVicePresident(VicePresidentRef clubVicePresident)
            throws SQLException {
        _struct.setAttribute(4, clubVicePresident);
    }

    @DbProp
    public TreasurerRef getClubTreasurer() throws SQLException {
        return (TreasurerRef) _struct.getAttribute(5);
    }

    public void setClubTreasurer(TreasurerRef clubTreasurer)
            throws SQLException {
        _struct.setAttribute(5, clubTreasurer);
    }

    @DbProp
    public SecretaryRef getClubSecretary() throws SQLException {
        return (SecretaryRef) _struct.getAttribute(6);
    }

    public void setClubSecretary(SecretaryRef clubSecretary)
            throws SQLException {
        _struct.setAttribute(6, clubSecretary);
    }

    @DbProp
    public DbCategories getListcategory() throws SQLException {
        return (DbCategories) _struct.getAttribute(7);
    }

    public void setListcategory(DbCategories listcategory) throws SQLException {
        _struct.setAttribute(7, listcategory);
    }

    @DbProp
    public Integer getDeleted() throws SQLException {
        return (Integer) _struct.getAttribute(8);
    }

    public void setDeleted(Integer deleted) throws SQLException {
        _struct.setAttribute(8, deleted);
    }

}
