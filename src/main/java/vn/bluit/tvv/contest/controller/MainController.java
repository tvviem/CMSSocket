package vn.bluit.tvv.contest.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController implements Initializable {

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initDrawer();
    }
    private void initDrawer() {
        try{
            VBox toolbar = FXMLLoader.load(getClass().getResource("vn/bluit/tvv/contest/fxml/toolbar.fxml"));
            drawer.setSidePane(toolbar);
        } catch (IOException ioe) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ioe);
        }
        HamburgerBasicCloseTransition task = new HamburgerBasicCloseTransition(hamburger);
        task.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (EventHandler<Event>) event -> {
            task.setRate(task.getRate()*-1);
            task.play();
            if(drawer.isHidden()) {
                drawer.open();
            } else {
                drawer.close();
            }
        });
    }
}
