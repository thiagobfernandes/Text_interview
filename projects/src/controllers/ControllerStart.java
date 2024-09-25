package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.Interaction;
import repository.ComandoDAO;
import repository.SceneDAO;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class ControllerStart implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String body = request.body();
        JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
        String comando = jsonObject.get("comando").getAsString();
        String[] valor = comando.split(" ");
        SceneDAO sceneDAO = new SceneDAO();
        Gson gson = new Gson();
        ComandoDAO comandoDAO = new ComandoDAO();
        Integer maxid = sceneDAO.getMaxIdInteraction();
        Interaction interaction = sceneDAO.findIdInteraction(maxid);
        Integer cenaAtual = interaction.getIdSceneCurrent();



        if (valor.length == 1) {
            if (valor[0].equalsIgnoreCase("start")) {
                sceneDAO.deleteAll();
                sceneDAO.insertInteraction(1, " Bem vindo ao jogo", null);
                if (cenaAtual == 1) {
                    comandoDAO.updateCommand(1, "Jogo iniciado");
                    return gson.toJson("O jogo comecou com sucesso");

                } else if( cenaAtual > 1){
                    sceneDAO.deleteAllExceptFirstRow();
                    comandoDAO.updateCommand(cenaAtual, "voce voltou para primeira cena, que triste");
                }
            } else {
                return gson.toJson("O que vc quer digitar irmaozinho?");
            }


//    } else {
//                comandoDAO.updateCommand(cenaAtual, "Quer comecar o jogo agora?  nao seu safado");
//            }

        }
        return null;
    }
}

