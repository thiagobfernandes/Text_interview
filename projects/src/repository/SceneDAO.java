package repository;

import com.google.gson.Gson;
import models.Interaction;
import models.Scene;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SceneDAO {

   public List<Scene> findScenesUpToCurrent(Integer currentScene) throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "SELECT DISTINCT s.idScene, s.descScene, i.IdSceneCurrent, i.resposta " +
                "FROM scene s " +
                "INNER JOIN interaction i ON s.idScene = i.IdSceneCurrent " +
                "WHERE s.idScene <= ? ";

        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setInt(1, currentScene);

        ResultSet rs = ps.executeQuery();
        List<Scene> scenes = new ArrayList<>();

        while (rs.next()) {
            System.out.println("Entrou");
            Scene scene = new Scene();
            scene.setIdScene(rs.getInt("idScene"));
            System.out.println(scene.getIdScene());
            scene.setDescScene(rs.getString("descScene"));
            System.out.println(scene.getDescScene());
            scene.setIdSceneCurrent(rs.getInt("IdSceneCurrent"));
            System.out.println(scene.getIdSceneCurrent());
            scene.setResposta(rs.getString("resposta"));
            System.out.println(scene.getResposta()+1);
            System.out.println("saiu");
            scenes.add(scene);

        }

        return scenes;
    }





//

    public void updateCurrentScene(Integer sceneId) throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "UPDATE interaction SET IdSceneCurrent = ? WHERE id = 1";
        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setInt(1, sceneId);
        ps.executeUpdate();
    }


    public void insertInteraction(int sceneIdCurrent, String resposta, Integer idSave) throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "INSERT INTO interaction (IdSceneCurrent, resposta, Currentsave) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, sceneIdCurrent);
            ps.setString(2, resposta);

            // Se idSave for null, insere NULL no banco
            if (idSave == null) {
                ps.setNull(3, java.sql.Types.INTEGER);
            } else {
                ps.setInt(3, idSave);
            }

            ps.executeUpdate(); // Executa a inserção
        } catch (SQLException e) {
            System.err.println("Erro ao inserir interação: " + e.getMessage());
            throw e; // Rethrow se necessário
        }
    }




    public Integer getMaxIdInteraction() throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "SELECT MAX(idInteraction) AS maxId FROM interaction"; // SQL para obter o máximo idInteraction
        PreparedStatement ps = connect.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getObject("maxId", Integer.class); // Retorna o máximo idInteraction encontrado
        }
        return null;
    }


    public Interaction findIdInteraction(Integer maxId) throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();
        String sql = "SELECT * FROM interaction WHERE idInteraction = ?";
        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setInt(1, maxId);

        ResultSet rs = ps.executeQuery();
        Interaction interaction = null; // Inicializa como null

        if (rs.next()) {
            interaction = new Interaction(); // Cria uma nova instância apenas se encontrar um resultado
            interaction.setIdInteraction(rs.getInt("idInteraction"));
            interaction.setIdSceneCurrent(rs.getInt("IdSceneCurrent"));
            interaction.setResposta(rs.getString("resposta"));
            interaction.setIdSave(rs.getInt("Currentsave")); // Corrigido para idSave
        }

        return interaction; // Retorna null se não encontrou resultados
    }




    public void deleteAllExceptFirstRow() throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();

        String sqlDelete = "DELETE FROM interaction WHERE idInteraction != 1";

        String sqlResetAutoIncrement = "ALTER TABLE interaction AUTO_INCREMENT = 2";

        PreparedStatement psDelete = connect.prepareStatement(sqlDelete);
        PreparedStatement psReset = connect.prepareStatement(sqlResetAutoIncrement);

        try {

            int rowsAffected = psDelete.executeUpdate();
            System.out.println(rowsAffected + " registros deletados exceto a linha com idInteraction = 1.");


            psReset.executeUpdate();
            System.out.println("AUTO_INCREMENT reiniciado para 2.");
        } finally {

            psDelete.close();
            psReset.close();
        }
    }

    public void deleteAll() throws SQLException {
        Connection connect = ConnectionDatabase.getConnection();

        String sqlDelete = "DELETE FROM interaction";

        String sqlResetAutoIncrement = "ALTER TABLE interaction AUTO_INCREMENT = 1";

        PreparedStatement psDelete = connect.prepareStatement(sqlDelete);
        PreparedStatement psReset = connect.prepareStatement(sqlResetAutoIncrement);

        try {

            int rowsAffected = psDelete.executeUpdate();
            System.out.println(rowsAffected + " registros deletados exceto a linha com idInteraction = 1.");


            psReset.executeUpdate();
            System.out.println("AUTO_INCREMENT reiniciado para 2.");
        } finally {

            psDelete.close();
            psReset.close();
        }
    }

}



