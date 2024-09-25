package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.ComandsEnum;
import models.Interaction;
import models.Save;
import repository.ComandoDAO;
import repository.SaveDao;
import repository.SceneDAO;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class ControllerLoad implements Route {
    SaveDao saveDao = new SaveDao();
    Save save = new Save();
    Gson gson = new Gson();
    SceneDAO sceneDAO = new SceneDAO();
    ComandoDAO comandoDAO = new ComandoDAO();



    @Override
    public Object handle(Request request, Response response) throws Exception {

        String body = request.body();
        Integer idCena = sceneDAO.getMaxIdInteraction();
        Interaction interaction = sceneDAO.findIdInteraction(idCena);
       Integer cenaAtual = interaction.getIdSceneCurrent();
       Integer saveAtual = interaction.getIdSave();

        JsonObject jsonbody = JsonParser.parseString(body).getAsJsonObject();
        String comando = jsonbody.get("comando").getAsString();
        String[] valor = comando.split(" ");


    if(valor.length ==2){


        if(ComandsEnum.load.name().toLowerCase().equals(valor[0])){
            Integer idsave = Integer.valueOf(valor[1]);
            System.out.println(save.getIdSave());
            System.out.println(save.getIdSave());
            save = saveDao.findIdSave(idsave);
            if (save.getIdSave() == null) {
                comandoDAO.updateCommand(cenaAtual,"esse save nao existe amigo");
                 return gson.toJson("Digite direito po");

            }
            if(save.getIdSave() != null ){
                sceneDAO.deleteAll();
                sceneDAO.insertInteraction(save.getIdScene(), "Save " + 1 + "carregado com sucesso", 1);
                comandoDAO.updateCommand(save.getIdScene(),"Ola amigo, que bom que voltei aproveite seu jogo a partir da cena " + save.getIdSave());
                return gson.toJson("Deu certo");
            } else {
                comandoDAO.updateCommand(save.getIdScene(),"Save nao encontrado");
                return gson.toJson("Save nao encontrado");
            }





        }
    }else  {
        return gson.toJson("Tem parada errada ai  meu irmao ");

    }

        return null;
    }
}
