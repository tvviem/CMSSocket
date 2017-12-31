package vn.bluit.tvv.contest.controller;

import com.jfoenix.controls.JFXPasswordField;
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

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    Navigation nav = new Navigation();
    @FXML
    private JFXTextField tfusername;

    @FXML
    private JFXPasswordField pfpassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    void handleSettingDbConnect(ActionEvent event) {
        try {
            Parent database_parent = FXMLLoader.load(getClass().getResource(nav.getDatabase()));
            Scene database_scene = new Scene(database_parent);
            Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(database_scene);
            app_stage.setTitle("Database Source");
            MouseDrag md = new MouseDrag();
            md.setDragged(database_parent, app_stage);
            app_stage.getIcons().add(nav.applicationIcon);
            app_stage.show();

        } catch (Exception e) {
            nav.showAlert(Alert.AlertType.ERROR, "Error", null, String.valueOf(e));
        }
    }
    @FXML
    void handleLoginButtonAction(ActionEvent event) {

    }

}
