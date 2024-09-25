package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.ComandsEnum;
import models.Interaction;
import models.Inventory;
import netscape.javascript.JSObject;
import repository.ComandoDAO;
import repository.InventoryDao;
import repository.SceneDAO;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ControllerInventory implements Route {
    InventoryDao inventoryDao = new InventoryDao();
    Gson gson = new Gson();
    SceneDAO sceneDAO = new SceneDAO();
    ComandoDAO comandoDAO = new ComandoDAO();

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();
        String body = request.body();
        JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
        String comando = jsonObject.get("comando").getAsString();

        String valor[] = comando.split(" ");

        Integer maxId = sceneDAO.getMaxIdInteraction();
        Interaction interaction = sceneDAO.findIdInteraction(maxId);
        Integer cenaAtual = interaction.getIdSceneCurrent();
        Integer saveAtual = interaction.getIdSave();
        Inventory inventory;


List<Inventory> objectList;

        if(valor.length == 1){

        if(valor[0].equals(ComandsEnum.inventory.name().toLowerCase())) {
            if(saveAtual == null){
                    objectList = inventoryDao.listObjectsNullSave();

            } else {
                objectList = inventoryDao.listObjectsWithSave(saveAtual);
            }


            if(objectList != null && !objectList.isEmpty()) {
                List<String> nameObjects= objectList.stream()
                        .map(Inventory::getName) // Substitua 'getNomeObjeto' pelo nome do getter correto
                        .collect(Collectors.toList());



                comandoDAO.updateCommand(cenaAtual, gson.toJson(nameObjects));
                return gson.toJson("Deu certo mano seu inventory");
            } else {
                comandoDAO.updateCommand(cenaAtual, "Voce nao contem nenhum objeto, thiago lindo");
                return gson.toJson("Voce nao contem nenhum objeto, thiago lindo");
            }


        }
        } else {
            comandoDAO.updateCommand(cenaAtual, "Uai mano digita certo ai po, digite (--inventory--) irmao e esse (" + valor[1] + ") ai");
            return gson.toJson("Uai mano digita certo ai po, digite (--inventory--) irmao e esse (" + valor[1] + ") ai");
        }
        return null;
    }
}
