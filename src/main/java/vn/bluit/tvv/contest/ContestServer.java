package vn.bluit.tvv.contest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class ContestServer extends Application {
    public static final Logger LOGGER = Logger.getLogger("GLOBAL");

    public static void main(String[] args) {
        System.out.printf("Hello world!!!");
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vn/bluit/tvv/contest/fxml/uiuserlogin.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("CMS Login");

        // Khoi tao the hien ket noi CSDL
       /* new Thread(()->{
            DatabaseHandler.getInstance();
        }).start();*/
    }
}