package vn.bluit.tvv.contest;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vn.bluit.tvv.contest.function.Navigation;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class ContestServer extends Application {
    public static final Logger LOGGER = Logger.getLogger("GLOBAL");

    private double x;
    private double y;
    Navigation nav = new Navigation();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Parent root = FXMLLoader.load(getClass().getResource("/vn/bluit/tvv/contest/fxml/uiuserlogin.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("CMS Login");
        primaryStage.setResizable(false);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        primaryStage.show();*/

        try {
            Parent root = FXMLLoader.load(getClass().getResource(nav.getLogin()));
            primaryStage.setTitle("Login");
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            root.setOnMousePressed(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event){
                    x = event.getSceneX();
                    y = event.getSceneY();
                }
            });

            root.setOnMouseDragged(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event){
                    primaryStage.setX(event.getScreenX()-x);
                    primaryStage.setY(event.getScreenY()-y);
                }
            });

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.getIcons().add(nav.applicationIcon);
            primaryStage.centerOnScreen();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            showExceptionDialog(e);
        }

        // Khoi tao the hien ket noi CSDL
       /* new Thread(()->{
            DatabaseHandler.getInstance();
        }).start();*/
    }
    public static void showExceptionDialog(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("An error occurred:");
        String content = "Error: ";

        if (null != e) {
            content += e.toString() + "\n\n";
        }
        alert.setContentText(content);
        Exception ex = new Exception(e);
        //Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();
        //Set up TextArea
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefHeight(600);
        textArea.setPrefWidth(800);

        //Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(textArea);
        alert.showAndWait();
    }
}