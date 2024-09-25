package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.*;
import repository.*;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class ControllerGet implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String body = request.body();
        JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
        String comando = jsonObject.get("comando").getAsString();
        //json body requisicao

        SceneDAO sceneDAO = new SceneDAO();
        Integer maxValue = sceneDAO.getMaxIdInteraction();
        Interaction interaction = new SceneDAO().findIdInteraction(maxValue);
        int cenaAtual = interaction.getIdSceneCurrent();
        Integer saveAtual = interaction.getIdSave();


        Gson gson = new Gson();
        ObjectsDAO objectsDAO = new ObjectsDAO();
        GetDAO getDAO = new GetDAO();
        InventoryDao inventoryDao = new InventoryDao();
        Inventory inventory;
        ComandoDAO comandoDAO = new ComandoDAO();
        String[] valor = comando.split(" ");


        if (cenaAtual <= 0) {
            cenaAtual = 1;
            sceneDAO.insertInteraction(cenaAtual, " ", saveAtual);
        }


        if (valor.length == 1) {
            comandoDAO.updateCommand(cenaAtual, "O que voce quis dizer mano?");
        }
        Objects validation = objectsDAO.findObjectsByName(valor[1]);
        if (validation.getNameObject() == null || validation.getIdObject() <= 0 || validation.getIdObject() == null) {
            comandoDAO.updateCommand(cenaAtual, "Que objeto e esse?");
            return gson.toJson("o que e isso?");
        }

        if (cenaAtual == 1) {
            if (valor[0].equals(ComandsEnum.get.name().toLowerCase()) && (valor[1]).equals("mochila")) {
                Objects objects = objectsDAO.findObjectsByid(3);
                if (objects != null) {
                    if (objects.getPossibleToCarry() == 1) {
                        if (inventoryDao.isObjectInInventory(objects.getIdObject(), saveAtual)) {
                            comandoDAO.updateCommand(cenaAtual, "Ja pegou esse objeto, nao?");
                        } else {
                            getDAO.AddToInventory(objects.getIdObject(), saveAtual);
                        }
                    }
                    Objects mochila = objectsDAO.findObjectsByName("mochila");
                    comandoDAO.updateCommand(cenaAtual, mochila.getResultPositive());
                    sceneDAO.insertInteraction(mochila.getIdSceneCurrent(), " ", saveAtual);
                    return gson.toJson("Deu certo amigo" + objects.getResultPositive());
                } else {
                    comandoDAO.updateCommand(cenaAtual, "Erro ao procurar mochila");
                }
            } else {
                comandoDAO.updateCommand(cenaAtual, "voce digitou algo errado amigo");
            }


        } else {


            if (valor[0].equals(ComandsEnum.get.name().toLowerCase())) { //verificacao de comandos
                Objects objects = objectsDAO.findObjectsByName(valor[1]);

                if (objects != null && objects.getIdScene().equals(cenaAtual)) { // validacao do objeto da cena
                    if (inventoryDao.isObjectInInventory(objects.getIdObject(), saveAtual)) {
                        comandoDAO.updateCommand(cenaAtual, "Voce ja tem " + objects.getNameObject() + " no seu inventario Irmao");
                          return gson.toJson("Voce ja tem " + objects.getNameObject() + "no seu inventario Irmao");
                    } else {

                        if(objects.getCommandCorrect() == null) {
                            comandoDAO.updateCommand(cenaAtual,objects.getResultNegative());
                            return gson.toJson("Objeto errado meu colega");
                        }

                        if (objects.getCommandCorrect().equals(comando)) {
                            comandoDAO.updateCommand(cenaAtual, objects.getResultPositive());
                            getDAO.AddToInventory(objects.getIdObject(), saveAtual);
                            sceneDAO.insertInteraction(objects.getIdSceneCurrent(), " ", saveAtual);
                            return gson.toJson("Deu certo amigo" + objects.getResultPositive());
//                        return gson.toJson(objects.getResultPositive());
                        }

                    }
                }  else {
                    comandoDAO.updateCommand(cenaAtual, "que objeto e esse?");
                }
            } else {
                comandoDAO.updateCommand(cenaAtual, "O que voce ta tentando inserir mano");

            }

        }
        return null;
    }
}












