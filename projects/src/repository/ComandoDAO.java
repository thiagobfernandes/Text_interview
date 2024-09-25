package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComandoDAO {
    public void updateCommand(int currentScene, String command) throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "UPDATE interaction SET resposta = ? WHERE IdSceneCurrent = ?";
        PreparedStatement ps = connect.prepareStatement(sql);

        ps.setString(1, command);
        ps.setInt(2, currentScene);

        ps.executeUpdate();
    }

    public String getCommand(int currentScene) throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "SELECT resposta FROM interaction WHERE current_scene = ?";
        PreparedStatement ps = connect.prepareStatement(sql);

        ps.setInt(1, currentScene);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getString("resposta");
        }

        return null;  // Retorna null se não encontrar nenhum valor
    }

}
