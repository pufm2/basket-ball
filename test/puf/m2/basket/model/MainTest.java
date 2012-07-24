package puf.m2.basket.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import oracle.jdbc.OracleResultSet;
import oracle.sql.ORADataFactory;
import puf.m2.basket.db.JDBCUtil;
import puf.m2.basket.db.entity.DbTeam;
import puf.m2.basket.db.entity.DbTeamMany;
import puf.m2.basket.db.entity.DbTeamWithPlayer;
import puf.m2.basket.db.entity.TeamRef;
import puf.m2.basket.model.entity.Address;
import puf.m2.basket.model.entity.Player;
import puf.m2.basket.model.entity.Team;

public class MainTest {
    private Connection conn;
    
    @Before
    public void setUp() throws SQLException {

        conn = JDBCUtil.getConnection();

    }
    
    @Test
    public void testSelect() throws SQLException {
        String stmtString = "select value(p) from player p";
        PreparedStatement pstmt = null;
        OracleResultSet orset = null;
        
        try {
            pstmt = conn.prepareStatement(stmtString);
            orset = (OracleResultSet) pstmt.executeQuery();
            while (orset.next()) {
                Player p = (Player) orset.getORAData(1, Player.getORADataFactory());
                
                System.out.println("player id : " + p.getId());
                System.out.println("player name : " + p.getPersonName());
                
                DbTeamWithPlayer twp = p.getTeamWithPlayers();
                twp.getArray()[0].getManyTeam().getValue().getTeamName();
                System.out.print("playing for: ");
                for (DbTeamMany tm : twp.getArray()) {
                    Team t = tm.getManyTeam().getValue();
                    System.out.print(t.getTeamName() + ", ");

                }
                System.out.println();
            }
        } finally {
            JDBCUtil.close(orset);
            JDBCUtil.close(pstmt);
        }
    }
    
    @Test
    public void testUpdate() throws SQLException {

    }
    
    private TeamRef getTeamRef() throws SQLException {
        PreparedStatement pstmt = null;
        try {
            String stmtString = "SELECT ref(t) FROM team t where id = 1";
            pstmt = conn.prepareStatement(stmtString);
            OracleResultSet orset = (OracleResultSet) pstmt.executeQuery();
            while (orset.next()) {
                return (TeamRef) orset.getORAData(1, TeamRef.getORADataFactory());
            }

        } finally {
            JDBCUtil.close(pstmt);
        }
        return null;
    }
    
    @Test
    public void testInsert() throws SQLException {

        PreparedStatement pstmt = null;
        try {
            String stmtString =  "insert into player values(?)";

            TeamRef tr = getTeamRef();
            
            DbTeamMany[] tmArr = {new DbTeamMany(tr, Timestamp.valueOf("2012-01-01 01:01:01"),
                    Timestamp.valueOf("2012-01-01 01:01:01"))};
            DbTeamWithPlayer twp = new DbTeamWithPlayer(tmArr);
            Player p = new Player(99, "a", "123", new Timestamp(345345345), new Address(), twp);
            pstmt = conn.prepareStatement(stmtString);
            pstmt.setObject(1, p);
            pstmt.executeUpdate();
            conn.commit();

        } finally {
            JDBCUtil.close(pstmt);
        }
    }
    
    /*private void insertTeam() throws SQLException {
        
        String stmtString = "insert into team values(?)";
        PreparedStatement pstmt = null;
        OracleResultSet orset = null;
        
        try {
            Team team = new Team(9, "team 9");
            pstmt = conn.prepareStatement(stmtString);
            pstmt.setObject(1, team);
            pstmt.executeUpdate();
            conn.commit();
            ORADataFactory f = TeamRef.getORADataFactory();
            //f.create(team.toDatum(conn), team.)

        } finally {
            JDBCUtil.close(pstmt);
        }
    }*/

}
