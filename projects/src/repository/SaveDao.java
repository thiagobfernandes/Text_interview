package repository;

import models.Objects;
import models.Save;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaveDao {
    public void saveScene(int idScene) throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "INSERT INTO save(idScene) VALUES(?)";
        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setInt(1, idScene);
        ps.executeUpdate();

    }


    public boolean isSceneSaved(int idScene) throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String checkSql = "SELECT COUNT(*) FROM save WHERE idScene = ?";
        PreparedStatement checkPs = connect.prepareStatement(checkSql);
        checkPs.setInt(1, idScene);
        ResultSet rs = checkPs.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) > 0; // Retorna true se a cena estiver salva
        }
        return false; // Retorna false se não estiver salva
    }



    public Save findIdSave(int idScene) throws SQLException {
        Save save = new Save();
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "SELECT * FROM save WHERE idSave = ?";
        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setInt(1, idScene);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            save.setIdSave(rs.getInt("idSave"));
            save.setIdScene(rs.getInt("idScene"));// Obtém o valor da coluna 'idSave'
        }
        return save;
    }

    public int getMaxIdSave() throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "SELECT MAX(idSave) AS maxId FROM save";
        PreparedStatement ps = connect.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("maxId");
        }
        return -1;  // Retorna -1 se não encontrar nenhum valor
    }

    public void updateSaveCurrentWhereNull(int currentScene, int saveCurrent) throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "UPDATE interaction SET Currentsave = ? WHERE IdSceneCurrent = ? AND Currentsave IS NULL";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, saveCurrent);
            ps.setInt(2, currentScene);

            ps.executeUpdate();
        } catch (SQLException e) {

            System.err.println("Erro ao atualizar Currentsave: " + e.getMessage());
            throw e;
        }
    }

    public void updateSaveCurrentWithValue(int currentScene, int saveCurrent) throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "UPDATE interaction SET Currentsave = ? WHERE IdSceneCurrent = ?";

        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, saveCurrent);
            ps.setInt(2, currentScene);

            ps.executeUpdate();
        } catch (SQLException e) {
            // Log de erro ou tratamento de exceção
            System.err.println("Erro ao atualizar Currentsave: " + e.getMessage());
            throw e;
        }
    }








}






