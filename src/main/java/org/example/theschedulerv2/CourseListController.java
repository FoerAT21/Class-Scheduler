//package org.example.theschedulerv2;
//
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.ListView;
//import javafx.stage.Stage;
//
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//
//public class CourseListController {
//    @FXML
//    private ListView<String> courseList;
//    private ArrayList<Class> list;
//
////    ArrayList<Class> courseList;
//
//    // Close Button on courseWindow should close that window and go back to mainWindow
//    @FXML
//    public void closeCourseList(ActionEvent event) {
//        //TODO: send arraylist of classes back to main to put on shcedule here
//        Stage stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
//        stage.close();
//    }
//
//
//    public void courseList() {
//        // TODO: put courseList up onto window with buttons
//    }
//
//    public void initialize(URL arg0, ResourceBundle arg1){
//
//        list = MainController.courseList;
//        for(Class c : list)
//            System.out.println(c);
//        courseList.getItems().addAll(String.valueOf(list));
//        courseList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
//
//            }
//        });
//    }
//
//}
