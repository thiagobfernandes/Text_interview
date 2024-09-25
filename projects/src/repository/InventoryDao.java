package repository;

import models.Inventory;
import models.Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDao {
    public List<Inventory> listObjects(Integer idSave) throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "SELECT * FROM inventory WHERE idSave = ?";
        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setInt(1, idSave);
        ResultSet rs = ps.executeQuery();

        List<Inventory> objectsList = new ArrayList<>();
        while (rs.next()) {
            Inventory inventory = new Inventory();
            inventory.setIdObject(rs.getInt("idObject"));
            inventory.setIdSave(rs.getInt("idSave"));

            objectsList.add(inventory);
        }
        return objectsList;

    }


    public Inventory findObjectNull(Integer idObject) throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();

        String sql = "SELECT * FROM inventory WHERE idObject = ? AND idSave IS NULL";

        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setInt(1, idObject);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Inventory inventory = new Inventory();
            inventory.setIdObject(rs.getInt("idObject"));
            inventory.setIdSave(null); // idSave é NULL
            return inventory;
        }

        return null;
    }





    public Inventory findObjectByIdInventory(Integer idSave, Integer idObject) throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();


        String sql = "SELECT * FROM inventory WHERE idObject = ? AND idSave = ?";

        PreparedStatement ps = connect.prepareStatement(sql);


        ps.setInt(1, idObject);
        ps.setObject(2, idSave);

        ResultSet rs = ps.executeQuery();


        if (rs.next()) {
            Inventory inventory = new Inventory();
            inventory.setIdObject(rs.getInt("idObject"));
            inventory.setIdSave(rs.getInt("idSave"));
            return inventory;
        }

        return null;
    }





    public List<Inventory> listObjectsNull() throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "SELECT * FROM inventory WHERE idSave IS NULL";
        PreparedStatement ps = connect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Inventory> objectsList = new ArrayList<>();
        while (rs.next()) {
            Inventory inventory = new Inventory();
            inventory.setIdObject(rs.getInt("idObject"));
            inventory.setIdSave(rs.getInt("idSave"));

            objectsList.add(inventory);
        }
        return objectsList;
    }
    // para pegar os nao nulos
    public List<Inventory> listObjectsWithSave(Integer idSave) throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "SELECT o.idObject, o.nameObject, i.idSave " +
                "FROM objects o " +
                "INNER JOIN inventory i ON o.idObject = i.idObject " +
                "WHERE i.idSave = ?";

        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setInt(1, idSave); // Define o idSave fornecido

        ResultSet rs = ps.executeQuery();

        List<Inventory> objectList = new ArrayList<>();
        while (rs.next()) {
            Inventory inventory = new Inventory();

            inventory.setName(rs.getString("nameObject"));

            objectList.add(inventory);
        }
        return objectList;
    }
    // para pegar os nullos
    public List<Inventory> listObjectsNullSave() throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "SELECT o.idObject, o.nameObject, i.idSave " +
                "FROM objects o " +
                "INNER JOIN inventory i ON o.idObject = i.idObject " +
                "WHERE i.idSave IS NULL";

        PreparedStatement ps = connect.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        List<Inventory> objectList = new ArrayList<>();
        while (rs.next()) {
            Inventory inventory = new Inventory();
            inventory.setName(rs.getString("nameObject"));
            objectList.add(inventory);
        }
        return objectList;
    }



    public boolean isObjectInInventory(Integer idObject, Integer idSave) throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "SELECT COUNT(*) FROM inventory WHERE idObject = ? AND (idSave = ? OR (idSave IS NULL AND ? IS NULL))";
         PreparedStatement ps = connect.prepareStatement(sql) ;
            ps.setInt(1, idObject);
            if (idSave != null) {
                ps.setInt(2, idSave);
                ps.setObject(3, idSave);
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
                ps.setNull(3, java.sql.Types.INTEGER);
            }
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                //se for maior que 0 o item ja esta no inventario
                return count > 0;
            }

        return false;
    }

    public void deleteNullsFromInventory() throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sqlDelete = "DELETE FROM inventory WHERE idSave IS NULL";
        String sqlResetAutoIncrement = "ALTER TABLE inventory AUTO_INCREMENT = 1";

      PreparedStatement psDelete = connect.prepareStatement(sqlDelete);
             PreparedStatement psReset = connect.prepareStatement(sqlResetAutoIncrement); {


            int rowsAffected = psDelete.executeUpdate();
            System.out.println(rowsAffected + " registros deletados com idSave nulo.");

            psReset.executeUpdate();
            System.out.println("AUTO_INCREMENT reiniciado para 1.");
        }
    }








}
