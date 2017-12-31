package vn.bluit.tvv.contest.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import vn.bluit.tvv.contest.function.MouseDrag;
import vn.bluit.tvv.contest.function.Navigation;
import vn.bluit.tvv.contest.model.ConnectionPoolManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class DbConnectController implements Initializable {
    @FXML
    private JFXTextField tfservername;

    @FXML
    private JFXTextField tfcongphucvu;

    @FXML
    private JFXTextField tfdbuser;

    @FXML
    private JFXTextField pfdbpassword;

    Navigation nav = new Navigation();

    @FXML
    void handleCheckDbConnect(ActionEvent event) {

    }

    @FXML
    void handleSaveDbConnectInfo(ActionEvent event) {

    }

    @FXML
    void handleUserLogin(ActionEvent event) {
        try {
            Parent database_parent = FXMLLoader.load(getClass().getResource(nav.getLogin()));
            Scene database_scene = new Scene(database_parent);
            Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(database_scene);
            app_stage.setTitle("Login");
            MouseDrag md = new MouseDrag();
            md.setDragged(database_parent, app_stage);
            app_stage.getIcons().add(nav.applicationIcon);
            app_stage.show();

        } catch (Exception e) {
            nav.showAlert(Alert.AlertType.ERROR, "Error", null, String.valueOf(e));
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Read Properties file and fill in control
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("app.properties"));
            String username_prop = properties.getProperty("DB_USER");
            String password_prop = properties.getProperty("DB_PASSWORD");
            String server_prop = properties.getProperty("DB_SERVERNAME");
            String database_prop = properties.getProperty("DB_NAME");
            String port_prop = properties.getProperty("DB_PORT");
            tfservername.setText(server_prop);
            tfcongphucvu.setText(port_prop);
            tfdbuser.setText(username_prop);
        }
        catch (IOException e) {
            nav.showAlert(Alert.AlertType.ERROR, "Error", null, "Setting File Not Found!");
        }
    }
    // TAO DOI TUONG LUU TRU THONG TIN KET NOI THANH CONG DEN DATABASE TAI DAY
    public static ConnectionPoolManager conPool = new ConnectionPoolManager();

}
