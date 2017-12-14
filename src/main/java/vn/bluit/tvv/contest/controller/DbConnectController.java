package vn.bluit.tvv.contest.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import vn.bluit.tvv.contest.model.ConnectionPoolManager;

import java.net.URL;
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

    @FXML
    void handleCheckDbConnect(ActionEvent event) {

    }

    @FXML
    void handleSaveDbConnectInfo(ActionEvent event) {

    }

    @FXML
    void handleUserLogin(ActionEvent event) {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    // TAO DOI TUONG LUU TRU THONG TIN KET NOI THANH CONG DEN DATABASE TAI DAY
    public static ConnectionPoolManager conPool = new ConnectionPoolManager();

}
