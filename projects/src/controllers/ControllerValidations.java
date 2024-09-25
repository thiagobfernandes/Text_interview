package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import repository.SceneDAO;
import spark.Request;
import spark.Response;
import spark.Route;

public class ControllerValidations implements Route {
    Controlleruse controllerUse = new Controlleruse();
    ControllerGet controllerGet = new ControllerGet();
    ControllerSave controllerSave = new ControllerSave();
    ControllerLoad controllerLoad = new ControllerLoad();
    ControllerInventory controllerInventory = new ControllerInventory();
    ControllerCheck controllerCheck = new ControllerCheck();
    ControllerStart controllerStart = new ControllerStart();


    @Override
    public Object handle(Request request, Response response) throws Exception {
        String body = request.body();

        response.type("application/json");
        System.out.println("corpo pilantra: " + body);
        JsonObject jsonBody = JsonParser.parseString(body).getAsJsonObject();
        String comando = jsonBody.get("comando").getAsString();
        response.type("application/json");
        if (!jsonBody.has("comando")) {
            response.status(400); // Bad Request
            return "Campo 'comando' não encontrado";
        }

        SceneDAO sceneDAO = new SceneDAO();
        String[] valor = comando.split(" ");


        switch (valor[0].toLowerCase()) {
            case "use":
          return controllerUse.handle(request, response);
            case "get":
              return  controllerGet.handle(request,response);
            case "save":
          return  controllerSave.handle(request,response);
            case "load":
                return  controllerLoad.handle(request,response);
            case "inventory":
                return controllerInventory.handle(request,response);
            case "check":
                return controllerCheck.handle(request,response);
            case "start":

                return controllerStart.handle(request,response);
            default:
                return "Comando não reconhecido";
        }
    }
}
