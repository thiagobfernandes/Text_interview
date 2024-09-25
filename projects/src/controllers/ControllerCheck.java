package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.ComandsEnum;
import models.Interaction;
import models.Objects;
import repository.ComandoDAO;
import repository.ObjectsDAO;
import repository.SceneDAO;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class ControllerCheck implements Route {
    SceneDAO sceneDAO = new SceneDAO();
    Gson gson = new Gson();
    ComandoDAO comandoDAO = new ComandoDAO();

    ObjectsDAO objectsDAO = new ObjectsDAO();
    @Override
    public Object handle(Request request, Response response) throws Exception {
        String body = request.body();
        JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
        String comando = jsonObject.get("comando").getAsString();
        String[] valor = comando.split(" ");
        Integer maxid = sceneDAO.getMaxIdInteraction();
        Interaction interaction = sceneDAO.findIdInteraction(maxid);
        Integer ceneAtual = interaction.getIdSceneCurrent();


        if(valor.length ==1){
            comandoDAO.updateCommand(ceneAtual,"Qual objeto esta querendo checar?");
        return gson.toJson("Quer saber sobre qual objete?, ta vazio po");

        } else if(valor.length > 2){
            comandoDAO.updateCommand(ceneAtual,"Eita eita um objeto por vez meu compadre");
            return gson.toJson("Eita eita um objeto por vez meu compadre");
        }
        if(valor[0].equals(ComandsEnum.check.name().toLowerCase())){
            Objects object = objectsDAO.findObjectsByName(valor[1].toLowerCase());
            if(object.getDescObject() != null){
                comandoDAO.updateCommand(ceneAtual,object.getDescObject());
                return gson.toJson(object.getDescObject());
            } else {
                return gson.toJson("Objeto nao encontrado");
            }



        } else {
            return gson.toJson("??????");
        }





    }
}
