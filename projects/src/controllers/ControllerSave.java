package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.Interaction;
import models.Inventory;
import models.Save;
import repository.*;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.sql.SQLException;
import java.util.List;

public class ControllerSave implements Route {
    SaveDao saveDao = new SaveDao();
        Gson gson = new Gson();
    InventoryDao inventoryDao = new InventoryDao();
        GetDAO getDAO = new GetDAO();
        SceneDAO sceneDAO = new SceneDAO();
        ComandoDAO comandoDAO = new ComandoDAO();




    @Override
    public Object handle(Request request, Response response) throws Exception {
        String body = request.body();
        JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
        String comando = jsonObject.get("comando").getAsString();
        String[] valor = comando.split(" ");
        Integer maxID = sceneDAO.getMaxIdInteraction();
        Interaction interaction = sceneDAO.findIdInteraction(maxID);
        Integer currentScene = interaction.getIdSceneCurrent();


        if(valor.length > 1) {
            comandoDAO.updateCommand(currentScene,"Para salvar digite apenas (- save)");
        }


        if (currentScene == 1) {
            return gson.toJson("Uai nem jogou e ja quer salvar? safado");
        } else {
            if (!saveDao.isSceneSaved(currentScene)) {


                saveDao.saveScene(currentScene);
                Integer newIdsave = saveDao.getMaxIdSave();
                Integer loadSave = interaction.getIdSave();

                List<Inventory> inventory;


                if (loadSave != null) {
                    inventory = inventoryDao.listObjects(loadSave);
                } else {
                    inventory = inventoryDao.listObjectsNull();
                }


                if (inventory.isEmpty()) {
                    System.out.println("nenhum objeto encontrado.");
                } else {
                    System.out.println("objetos encontrados: " + inventory.size());
                }


                for (Inventory item : inventory) {
                    try {
                        System.out.println("Adicionando ao inventário: idObject=" + item.getIdObject() + ", idSave=" + newIdsave);
                        getDAO.AddToInventory(item.getIdObject(), newIdsave);
                    } catch (SQLException e) {
                        System.err.println("Erro ao adicionar ao inventário: " + e.getMessage());
                    }
                }
                if (loadSave == null) {
                    saveDao.updateSaveCurrentWhereNull(currentScene, newIdsave);
                } else {
                    saveDao.updateSaveCurrentWithValue(currentScene, newIdsave);
                }

                System.out.println(newIdsave);
                comandoDAO.updateCommand(currentScene,"Salvo com sucesso");
                return gson.toJson("salvo com sucesso");
            }else {
                comandoDAO.updateCommand(currentScene,"Voce ja salvou essa cena nao e mesmo?");

            return gson.toJson("Deu ruim :(");
        }
        }



    }
}
