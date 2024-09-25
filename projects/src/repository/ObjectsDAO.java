package repository;

import models.Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectsDAO {
    public Objects findObjectsByName(String name) throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "SELECT * FROM objects WHERE nameObject = ?";
        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setString(1,name);
        ResultSet rs = ps.executeQuery();
        Objects objects = new Objects();

        if(rs.next()) {
            objects.setIdObject(rs.getInt("idObject"));
            objects.setNameObject(rs.getString("nameObject"));
            objects.setDescObject(rs.getString("descObject"));
            objects.setCommandCorrect(rs.getString("commandCorrect"));
            objects.setResultPositive(rs.getString("resultPositive"));
            objects.setResultNegative(rs.getString("resultNegative"));
            objects.setIdScene(rs.getInt("idScene"));
            objects.setIdSceneCurrent(rs.getInt("idSceneCurrent"));
            objects.setPossibleToCarry(rs.getInt("possibleToCarry"));
        }
        return objects;
    }
    public Objects findObjectsByid(int idObject) throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "SELECT * FROM objects WHERE idObject = ?";
        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setInt(1,idObject);
        ResultSet rs = ps.executeQuery();
        Objects objects = new Objects();

        if(rs.next()) {
            objects.setIdObject(rs.getInt("idObject"));
            objects.setNameObject(rs.getString("nameObject"));
            objects.setDescObject(rs.getString("descObject"));
            objects.setCommandCorrect(rs.getString("commandCorrect"));
            objects.setResultPositive(rs.getString("resultPositive"));
            objects.setResultNegative(rs.getString("resultNegative"));
            objects.setIdScene(rs.getInt("idScene"));
            objects.setIdSceneCurrent(rs.getInt("idSceneCurrent"));
            objects.setPossibleToCarry(rs.getInt("possibleToCarry"));
        }
        return objects;
    }
}