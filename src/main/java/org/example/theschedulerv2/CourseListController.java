package org.example.theschedulerv2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CourseListController {

    // Close Button on courseWindow should close that window and go back to mainWindow
    @FXML
    public void closeCourseList(ActionEvent event) {
        //TODO: send arraylist of classes back to main to put on shcedule here
        Stage stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
