package repository;

import models.Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckDAO {
    public Object findByDescObjects(String name) throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "SELECT idObject, nameObject, descObject FROM objects WHERE nameObject = ?";
        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        Objects objects = new Objects();

        if (rs.next()) {
            objects.setIdObject(rs.getInt("idObject"));
            objects.setNameObject(rs.getString("nameObject"));
            objects.setDescObject(rs.getString("descObject"));
        }
        return objects;
    }

}
