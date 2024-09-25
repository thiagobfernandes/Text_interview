import com.google.gson.Gson;
import controllers.ControllerScene;
import controllers.ControllerValidations;
import repository.CheckDAO;
import repository.ObjectsDAO;
import repository.SceneDAO;
import spark.Spark;

import java.sql.SQLException;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        Gson gson = new Gson();
        SceneDAO sceneDAO = new SceneDAO();
        ObjectsDAO objectsDAO = new ObjectsDAO();
        CheckDAO checkDAO = new CheckDAO();

        // Define a porta antes das rotas
        port(3200);
/// a fazer, restart e help...
        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });



        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        Spark.get("/", new ControllerScene()); // rota cena
        Spark.post("/", new ControllerValidations());
       // validations
    }




    }


