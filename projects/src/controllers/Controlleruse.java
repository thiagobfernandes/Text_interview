package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.*;
import repository.ComandoDAO;
import repository.InventoryDao;
import repository.ObjectsDAO;
import repository.SceneDAO;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class Controlleruse implements Route {
    SceneDAO sceneDAO = new SceneDAO();
    Gson gson = new Gson();
    ObjectsDAO objectsDAO = new ObjectsDAO();

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String body = request.body();
        JsonObject jsonBody = JsonParser.parseString(body).getAsJsonObject();
        String comando = jsonBody.get("comando").getAsString();
        String[] valor = comando.split(" ");
        //body requisicao

        Integer maxId = sceneDAO.getMaxIdInteraction();
        Interaction interaction = sceneDAO.findIdInteraction(maxId);
        Integer cenaAtual = interaction.getIdSceneCurrent();
        Integer SaveAtual = interaction.getIdSave();

        //controle de cenas e save

        Objects objects;
        Objects objectsCena;
        Inventory inventory;

        InventoryDao inventoryDao = new InventoryDao();
        ComandoDAO comandoDAO = new ComandoDAO();
        SceneDAO sceneDAO1 = new SceneDAO();


        if (cenaAtual <= 0) {
            sceneDAO1.insertInteraction(1, " ", null);
        }


        if (valor.length == 1) {
            comandoDAO.updateCommand(cenaAtual, "Uai meu mano oque voce quis dizer?? objeto nulo meu comparça digita algo ai");
        }

        Objects validation = objectsDAO.findObjectsByName(valor[1]);


        if (valor.length == 2) {
            if (validation.getNameObject() == null || validation.getIdObject() == null) {
                comandoDAO.updateCommand(cenaAtual, "Objeto nao reconhecido :(");
                return gson.toJson("objeto irreconhecivel");
            }


            if (ComandsEnum.use.name().toLowerCase().equals(valor[0])) {
                objects = objectsDAO.findObjectsByName(valor[1]);
                if (objects.getNameObject() == null || objects.getIdObject() <= 0) {
                    comandoDAO.updateCommand(cenaAtual, "Que Objeto e esse?");
                }

                if (objects.getIdScene().equals(cenaAtual)) {
                    if (comando.equals(objects.getCommandCorrect())) {  //novo update
                        comandoDAO.updateCommand(cenaAtual, objects.getResultPositive());
                        sceneDAO1.insertInteraction(objects.getIdSceneCurrent(), " ", SaveAtual);
                        return gson.toJson("deu certo meu amigo fique tranquilo po");

                    } else {
                        comandoDAO.updateCommand(cenaAtual, objects.getResultNegative()); // novo update
                    }
                } else {
                    comandoDAO.updateCommand(cenaAtual, "Objeto selecionado nao condiz com a sua cena atual"); // novo update
                }
            }


        } else if (valor.length == 4) { //use with
            {
               try {
                    if (valor[0].equals(ComandsEnum.use.name().toLowerCase()) && valor[2].equalsIgnoreCase("with")) { //validando as strings recebidas
                        objects = objectsDAO.findObjectsByName(valor[1]);

                        if (objects.getNameObject() == null || objects.getIdObject() <= 0 || objects.getIdObject() == null) {
                            comandoDAO.updateCommand(cenaAtual, "Objeto invalido");
                            return gson.toJson("errado");
                        }

                        System.out.println("id objeto" + objects.getIdObject());
                        if (SaveAtual == null) {
                            inventory = inventoryDao.findObjectNull(objects.getIdObject()); // achei
                        } else {
                            inventory = inventoryDao.findObjectByIdInventory(SaveAtual, objects.getIdObject());
                        }

                        System.out.println(inventory.toString());
                        System.out.println(SaveAtual);
                        System.out.println(inventory.getIdSave());

                        objectsCena = objectsDAO.findObjectsByName(valor[3]);
                        System.out.println(objectsCena.getNameObject());

                        if (objectsCena.getNameObject().length() <= 4 || objectsCena.getNameObject() == null || objectsCena.getIdObject() <= 0 || objectsCena.getIdObject() == null) {
                            comandoDAO.updateCommand(cenaAtual, "Objeto invalido :(");
                            return gson.toJson("deu ruim");
                        } else {
                            if (objectsCena.getIdScene().equals(cenaAtual)) {
                                if (comando.equals(objectsCena.getCommandCorrect())) {
                                    comandoDAO.updateCommand(cenaAtual, objects.getResultPositive());
                                    sceneDAO1.insertInteraction(objectsCena.getIdSceneCurrent(), " ", SaveAtual);
                                    return gson.toJson("Deu certo mestrao");// novo update
                                } else {
                                    comandoDAO.updateCommand(cenaAtual, "Comando Invalido");
                                }

                            } else {
                                comandoDAO.updateCommand(cenaAtual, "Objeto nao correspondente a cena");
                            }


                        }
                    }

                } catch (Exception e){
                   comandoDAO.updateCommand(cenaAtual, "Humm, tem algo errado nesse comandinho :(");
                   return gson.toJson("catherr");
               }
                // busco o objeto no meu inventario


                ;

            }
        }

        return null;

    }
}


