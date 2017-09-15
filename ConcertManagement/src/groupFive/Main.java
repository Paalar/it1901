package groupFive;

import util.Constants;
import Json.JsonDecode;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    Constants constants = new Constants();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        if(Constants.getHome() == null){
            constants.setHome("main");
            constants.setCurrent("main");
        }
        primaryStage.setTitle("Concert Management");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();

        JSONArray jsonArray = JsonDecode.parseJSON();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void changeView(AnchorPane rootPane, String fxmlFile) {
        // Denne burde også legge til hva du har endret til i en stack så vi kan lett lage fram og tilbake knapper.
        fxmlFile = fxmlFile.replace("ø","o");
        constants.setPrev(constants.getCurrent());
        constants.setCurrent(fxmlFile);
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlFile + ".fxml"));
            rootPane.getChildren().setAll(pane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeViewBack(AnchorPane rootPane, String fxmlFile){
        constants.setForw(fxmlFile);
        constants.setCurrent(fxmlFile);
        changeView(rootPane, constants.getPrev());
    }

    public static ArrayList<String> filterList(ArrayList<String> wholeList, String split) {
        ArrayList<String> listToAdd = new ArrayList<>();
        for (int x  = 0; x < wholeList.size(); x++) {
            if (wholeList.get(x).startsWith(split)) {
                listToAdd.add(wholeList.get(x).replace(split, ""));
            }
        }
        return listToAdd;
    }
}
