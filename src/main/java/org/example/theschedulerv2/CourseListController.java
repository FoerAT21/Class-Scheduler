package org.example.theschedulerv2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CourseListController {

//    ArrayList<Class> courseList;

    // Close Button on courseWindow should close that window and go back to mainWindow
    @FXML
    public void closeCourseList(ActionEvent event) {
        //TODO: send arraylist of classes back to main to put on shcedule here
        Stage stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        stage.close();
    }


    public void courseList() {
        // TODO: put courseList up onto window with buttons
//        MainController.courseList.get(0);
    }

}
