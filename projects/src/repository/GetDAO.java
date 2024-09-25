package repository;

import models.Inventory;
import models.Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetDAO {
    public Inventory AddToInventory(Integer idObject, Integer idSave) throws SQLException {
        try {
            Connection connect = ConnectionDatabase.getConnection();
            String sql = "INSERT INTO inventory(idObject, idSave) VALUES (?, ?)";
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, idObject);
            ps.setObject(2, idSave, java.sql.Types.INTEGER); // Alterado para setObject
            ps.executeUpdate();

            return null;
        } catch (SQLException e) {
            System.out.println(e);
            throw e;
        }
    }

}
