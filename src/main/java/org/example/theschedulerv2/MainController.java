package org.example.theschedulerv2;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private ChoiceBox<String> departmentField;
    @FXML
    private ChoiceBox<String> instructorField;
    @FXML
    private TextField codeField;
    @FXML
    private ChoiceBox<String> startField;
    @FXML
    private ChoiceBox<String> endField;
    @FXML
    private CheckBox MondayBox;
    @FXML
    private CheckBox TuesdayBox;
    @FXML
    private CheckBox WednesdayBox;
    @FXML
    private CheckBox ThursdayBox;
    @FXML
    private CheckBox FridayBox;
    @FXML
    private ChoiceBox<String> majorField;
    @FXML
    private ListView<String> scheduleList;
    @FXML
    private ListView<String> searchResults;
    private Schedule currSchedule = new Schedule(); // TODO: WHEN CHANGE SCHED_NAME currSchedule.setName(to_update_value)
    //private ArrayList<String> schedules;
    private User curUser = new User();
    private String currentSchedule = "";
    @FXML
    private TextField scheduleName;
    private String nameOfSchedule;
    @FXML
    private TabPane tab;
    @FXML
    private Tab scheduleTab;
    @FXML
    private Tab coursesTab;
    @FXML
    private Tab recTab;
    @FXML
    private TextArea recText;

    private String[] times = {"none", "800", "900", "1000", "1100", "1200", "1300", "1400", "1500", "1600",
                                "1700", "1800", "1900", "2000", "2100", "2200"};

    private String[] yearList = {"Freshmen", "Sophomore", "Junior", "Senior"};

    private String[] departmentList = {"none", "ACCT", "ART", "ASTR", "BIOL", "CHEM",
            "CMIN", "COMM", "COMP", "DESI", "ECON", "EDUC", "ELEE", "ENGL", "ENGR",
            "ENTR", "EXER", "FNCE", "FREN", "GEOL", "GREK", "HEBR", "HIST", "HUMA",
            "INBS", "LATN", "MARK", "MATH", "MECE", "MNGT", "MUSI", "NURS", "PHIL",
            "PHYE", "PHYS", "POLS", "PSYC", "RELI", "ROBO", "SCIC", "SEDU", "SOCI",
            "SOCW", "SPAN", "SSFT", "THEA", "WRIT"};

    private String[] instructorList = {"none", "Agnew, Rochelle", "Al Moakar, Lory", "Allison, Blair", "Anderson, Erik",
            "Antoszewski, Lisa", "Archibald, C", "Augspurger, Joseph", "Ault, Dana",
            "Ayers, David", "Baker, Elizabeth", "Bancroft, Eric", "Bandy, Gregory",
            "Barbour, Kristin", "Bardy, Erik", "Bellassai, Anthony", "Berry, Matthew",
            "Bibza, James", "Biddle, Wayne", "Billock, Rebecca", "Blackburn, Gina",
            "Bonomo, Kelleen", "Boyle, Henry", "Bridger, Daniel", "Bright, Martin",
            "Bright, Myron", "Brooks, James", "Brower, Shane", "Brown, Janice",
            "Brutscher, Chandler", "Butler, David", "Buxton, Jeffrey", "Byo, Donald",
            "Byun, Seulgi", "Campbell, George", "Carruth, Melissa", "Carson, Kenneth",
            "Carter, Beverly", "Catanese, Alexander", "Chapman, Jarrett", "Christman, Alan",
            "Churm, George", "Clauss, Michelle", "Clem, James", "Clemm, Robert",
            "Cook, Adam", "Cost, Jay", "Coulter, Michael", "Coyne, John",
            "Craig, Betsy", "Cramer, Kristen", "Cramer, Susan", "Crute, Christy",
            "Culbertson, Linda", "Davidek, Jeffrey", "Dellinger, Brian", "Dickinson, Brian",
            "DiDonato, Andrea", "DiDonato, Andrew", "DiStasi, Vincent", "Dixon, John",
            "Dolan, Brenda", "Drai, Remi", "Dreves, Susan", "Dudt, Jan",
            "Edwards, Jason", "English, Kyle", "English, Yvonne", "Erb, Andrew",
            "Fair, Mark", "Falcetta, Michael", "Farone, Tracy", "Fecich, Samantha",
            "Fennell, Drew", "Fitch, Bradley", "Flanders, Steven", "Fleske, William",
            "Forteza, Deborah", "Franklin, Christopher", "Fugate, Luke", "Fuller, Caleb",
            "Fuss, Leslie", "Garvey, Kevin", "Genareo, Phyllis", "Gibson, Todd",
            "Gordon, Thomas", "Graham, Mark", "Gregg, Jane", "Grimm, Richard",
            "Guevara, Holly", "Hammond, Ryan", "Harmon, Rebecca", "Harp, Gillis",
            "Harvey, Andrew", "Hasper, Joseph", "Havrilla, Laura", "Heasley, Daniel",
            "Heid, Ronald", "Heisey, Natalie", "Henley, Karastin", "Herald, Sandra",
            "Herbener, Jeffrey", "Hogsette, David", "Hollenberger, Jennifer", "Homan, Kristin",
            "Homan, Timothy", "Horton, Joseph", "Hosack, Lisa", "Houk, Suzanne",
            "Huebert, Melva", "Hutchins, Jonathan", "Jackson, Michael", "Jackson, Tracy",
            "Jenkins, Stephen", "Kemeny, Paul", "Kengor, Paul", "Kinman, David",
            "Kocur, Richard", "Kohanski, Elisa", "Kriley, Charles", "Kubik, Paula",
            "Lamie, Melissa", "Lewis, Cedric", "Ligo, Carla", "Lingwall, James",
            "Lipson, Hilary", "Loretto, Adam", "Lytle, Laurie", "Madsen, Kelsey",
            "Markley, Andrew", "Marsch, Glenn", "Martin, Tammi", "May, Douglas",
            "Mayo, Joshua", "McCullough, Jacqueline", "McFeaters, Michelle", "McIntyre, Dale",
            "McNulty, Paul", "Messer, H", "Miller, Jennifer", "Miller, Kimberly",
            "Miller, Ryan", "Mitchell, Andrew", "Moeller, Julie", "Mohr, Timothy",
            "Mucha, Nathanael", "Mueller, Katherine", "Munson, Jolene", "Munson, Paul",
            "Nemeth, Daniel", "Nichols, Constance", "Omasits, Christopher", "Paparone, Stacy",
            "Patterson, Richard", "Pazehoski, Kristina", "Philson, Cynthia", "Piastro-Tedford, Sasha",
            "Potter, Eric", "Powell, Scott", "Prins, Philip", "Pritchard, Patricia",
            "Rhoades, Kathy", "Richards, George", "Rider, Robert", "Rine, C",
            "Ritenour, Shawn", "Roach, Janey", "Robbins, David", "Rumbaugh, Luke",
            "Rupnik, Elizabeth", "Sanders, Glen", "Savage, Richard", "Sayles, Leo",
            "Scanga, James", "Schaefer, Paul", "Scott, Julia", "Senita, Karyn",
            "Severson, Sean", "Seybold, Kevin", "Shaw, Kevin", "Shepson, Donald",
            "Shidemantle, Wendy", "Smith, Ethan", "Smith, John", "Smith, Kenneth",
            "Snyder, Richard", "Stanton, Samuel", "Stauff, Devin", "Stephens, John",
            "Stone, Jennifer", "Strain, Joy", "Sullivan, Logan", "Sweet, Timothy",
            "Tedford, Jeffrey", "Tessmer, David", "Thompson, Gary", "Thrasher, Caleb",
            "Thrasher, James", "Throckmorton, E", "Timko, Elaine", "Trueman, Carl",
            "Ulrich, Vernon", "Valentine, David", "Venesky, Jason", "Verbois, Caleb",
            "Wagner, Doris", "Waha, Kristen", "Wasilko, Mark", "Weber, Alana",
            "Weber, Brent", "Welton, Gary", "West, Ryan", "Wise, Scott",
            "Wolfe, Britton", "Wolfe, Daniel", "Wolinski, Jeffrey", "Wong, Ven Ney",
            "Wood, Darren", "Young, M", "Yowler, Brian", "Zhang, Youhui"};

    private String[] majorList = {"Accounting"};

    String name;
    String department;
    String instructor;
    String code;
    String start;
    String end;
    String day;
    String major;
    String year;
    private Stage stage;
    private ArrayList<Class> courseList;
    private String classToAdd;

    // MainWindow Search Button: Collects all the filter variables and opens courseWindow
    @FXML
    protected void search(ActionEvent event) {
        try {
            day = "";
            if (MondayBox.isSelected()) {
                day += "M";
            }
            if (TuesdayBox.isSelected()) {
                day += "T";
            }
            if (WednesdayBox.isSelected()) {
                day += "W";
            }
            if (ThursdayBox.isSelected()) {
                day += "R";
            }
            if (FridayBox.isSelected()) {
                day += "F";
            }

            if(day.isEmpty()) day = null;

            if (!Objects.equals(nameField.getText(), "")) {
                name = nameField.getText();
            } else {
                name = null;
            }

            if (!Objects.equals(departmentField.getValue(), "")) {
                department = departmentField.getValue();

                // Allows user to take out value
                if(department != null && department.equals("none")) {
                    departmentField.setValue("");
                    department = null;
                }

            } else {
                department = null;
            }

            if (!Objects.equals(instructorField.getValue(), "")) {
                instructor = instructorField.getValue();

                // Allows user to take out value
                if(instructor != null && instructor.equals("none")){
                    instructorField.setValue("");
                    instructor = null;
                }

                // Formats name of instructor properly for database
                if(instructor != null){
                    Scanner temp = new Scanner(instructor);
                    temp.useDelimiter(", ");
                    String lastName = temp.next();
                    String firstName = temp.next();
                    instructor = firstName + " " + lastName;
                }


            } else {
                instructor = null;
            }
            if (!Objects.equals(codeField.getText(), "")) {
                code = codeField.getText();
            } else {
                code = null;
            }
            if (startField.getValue() != null && !startField.getValue().isEmpty()) {
                start = startField.getValue();

                // Allows user to take out value
                if(start != null && start.equals("none")){
                    startField.setValue("");
                    start = null;
                }
            } else {
                start = null;
            }

            if (endField.getValue() != null && !endField.getValue().isEmpty()) {
                end = endField.getValue();

                // Allows user to take out value
                if(end != null && end.equals("none")){
                    endField.setValue("");
                    end = null;
                }
            } else {
                end = null;
            }

            courseList = Search.search(
                    department, code, null, name, null,
                    day, start, end, instructor
            );

            tab.getSelectionModel().select(coursesTab);
            for(Class c : courseList){
                searchResults.getItems().add(c.toString());
            }


//            try {
//                Parent child = FXMLLoader.load(getClass().getResource("courseWindow.fxml"));
//                Scene scene = new Scene(child, 320, 240);
//                stage = new Stage(StageStyle.DECORATED);
//                stage.setTitle("Second Window!");
//                stage.initModality(Modality.APPLICATION_MODAL);
//                stage.setScene(scene);
//
//                stage.showAndWait();
//            } catch (Exception e) {
//                System.out.println("Search Error: " + e);
//            }

        } catch (Exception e) {
            System.out.println("Exception Message: " + e);
        }
    }

    @FXML
    public void saveSchedule(ActionEvent action) {
        if (nameOfSchedule.equals("")) {
            //TODO: please enter name for your schedule and try again
        } else {
            // Set Schedule name
            currSchedule.setScheduleName(nameOfSchedule);
            // Saves the schedule in the txt
            currSchedule.saveSchedule(curUser);
            // Clear scheduleList
            scheduleList.getItems().clear();
            // Repopulate scheduleList
            for (Schedule s : curUser.getSavedSchedules()){
                scheduleList.getItems().addAll(s.getScheduleName());
            }
        }
    }

    @FXML
    public void openSchedule(ActionEvent action) {
        //TODO: open schedule
        scheduleName.setText(currentSchedule);

        //TODO: get schedule with that name from txt file and view it

    }

    @FXML
    public void openRecSchedule(ActionEvent action) throws IOException {
        System.out.println("called openRecSchedule");
        String major;
        if (!Objects.equals(majorField.getValue(), "")) {
            major = majorField.getValue();

            // Allows user to take out value
            if(major != null && major.equals("none")) {
                majorField.setValue("");
                major = null;
            }

        } else {
            major = null;
        }
        System.out.println("Major: " + major);
        String recSchedule = Schedule.retRecSchedule(major);

        tab.getSelectionModel().select(recTab);
        System.out.println(recSchedule);
        recText.setText(recSchedule);
    }



    @FXML
    public void nameTheSchedule(ActionEvent action) {
        nameOfSchedule = scheduleName.getText();
//        System.out.println(nameOfSchedule);
    }




    //Initializes all dropdown options in mainWindow with their expected options
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        departmentField.getItems().addAll(departmentList);
        instructorField.getItems().addAll(instructorList);
        majorField.getItems().addAll(majorList);
        startField.getItems().addAll(times);
        endField.getItems().addAll(times);

        curUser.loadSavedSchedules();

//        String filePath = "src/SavedSchedules.txt";
//
//        schedules = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split("\\s+");
//                schedules.add(parts[0]);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        for (Schedule s : curUser.getSavedSchedules()){
            scheduleList.getItems().addAll(s.getScheduleName());
        }

        //scheduleList.getItems().addAll(schedules);
        scheduleList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentSchedule = scheduleList.getSelectionModel().getSelectedItem();
                System.out.println(currentSchedule);
            }
        });


        searchResults.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Listen for double click events on the selected item
                searchResults.setOnMouseClicked(mouseEvent -> {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {

                        // Handle double-click action here
                        classToAdd = newValue; // Assuming classToAdd is a field in your class
                        System.out.println(classToAdd);

                        Class c = new Class(classToAdd);

                        boolean hasConflict = false;
                        for(Class cTemp : currSchedule.getClassesInSchedule()){
                            if(c.hasConflict(cTemp)) hasConflict = true;
                        }

                        if(hasConflict){
                            // TODO JADEN DEAL WITH IT
                            // TODO Give text message saying has conflict
                        }else{
                            // TODO JADEN DEAL WITH IT
                            // TODO ARE YOU SURE YOU WANT TO ADD? Y/N

                        }

                        // Add your logic to add the selected class to the schedule
                    }
                });
            }
        });
    }
}
