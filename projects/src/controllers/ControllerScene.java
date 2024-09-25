package controllers;

import com.google.gson.Gson;
import com.sun.tools.jconsole.JConsoleContext;
import models.Interaction;
import models.Inventory;
import models.Save;
import models.Scene;
import repository.InventoryDao;
import repository.SceneDAO;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.List;

public class ControllerScene  implements Route {

    @Override

    public Object handle(Request request, Response response) throws Exception {

//        Session session = request.session();
//
   SceneDAO sceneDAO = new SceneDAO();
        Gson gson = new Gson();
        Integer current = sceneDAO.getMaxIdInteraction();
        Interaction interaction  = sceneDAO.findIdInteraction(sceneDAO.getMaxIdInteraction());
        System.out.println(interaction.getIdInteraction());
        InventoryDao inventoryDao = new InventoryDao();

        System.out.println(interaction.getIdSceneCurrent());
        if(interaction.getIdSceneCurrent() == 1) {
            inventoryDao.deleteNullsFromInventory();
        }


//
   List<Scene> scenes = sceneDAO.findScenesUpToCurrent(interaction.getIdSceneCurrent());




        return gson.toJson(scenes);// Retorne as cenas como JSON
    }
}