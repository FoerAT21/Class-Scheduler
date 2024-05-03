package org.example.theschedulerv2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.collections.FXCollections;

public class MainController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private ListView<String> nameSuggestions = new ListView<>();
    @FXML
    private ChoiceBox<String> departmentField;
    @FXML
    private ChoiceBox<String> instructorField;
    @FXML
    private TextField codeField;
    @FXML
    private ListView<String> idSuggestions = new ListView<>();
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
    private Schedule currSchedule = new Schedule(); // WHEN CHANGE SCHED_NAME currSchedule.setName(to_update_value)
    private User curUser = new User();
    private String currentSchedule = "";
    @FXML
    private TextField scheduleName;
    private String nameOfSchedule = "";
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
    @FXML
    private GridPane scheduleGridPane;
    @FXML
    private Button modeSwitch;
    private Boolean isLight = true;
    @FXML
    private Label creditsLabel;
    private int totalCredits = 0;
    private SmartSearch autoFill = new SmartSearch();


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

    private String[] majorList = {"Accounting", "ComputerScienceBS", "Economics", "English", "Management"};

    String name;
    String department;
    String instructor;
    String code;
    String start;
    String end;
    String day;
    private ArrayList<Class> courseList;

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
            searchResults.getItems().clear();
            for(Class c : courseList){
                searchResults.getItems().add(c.toString());
                //Store indexInDB value with the item in the ListView
                searchResults.getProperties().put(c.toString(), c.getIndexInDB());
            }

        } catch (Exception e) {
            System.out.println("Exception Message: " + e);
        }
    }

    @FXML
    public void saveSchedule(ActionEvent action) {
        if (nameOfSchedule.equals("")) {
            // please enter name for your schedule and try again
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Please Enter A Name For Your Schedule");
            alert.setHeaderText("Warning Alert");
            alert.showAndWait();
        } else {
            // Set Schedule name
            currSchedule.setScheduleName(nameOfSchedule);
            // Saves the schedule in the txt
            currSchedule.saveSchedule(curUser);
            // Repopulate scheduleList
            if (!scheduleList.getItems().contains(currSchedule.getScheduleName())){
                scheduleList.getItems().add(currSchedule.getScheduleName());
            }
        }
    }

    @FXML
    public void openSchedule(ActionEvent action) {
        // open schedule
        scheduleName.setText(currentSchedule);

        // get schedule with that name from txt file and view it

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
    protected void onModeSwitch(ActionEvent action) {
        System.out.println("Attempting to Switch Modes");
        isLight = !isLight;

        // Get the scene of the button
        Scene scene = modeSwitch.getScene();

        // Remove the previous stylesheet
        scene.getStylesheets().clear();

        // Add the new stylesheet based on the state
        String newStylesheet = isLight ? "lightmode.css" : "darkmode.css";

        if (isLight) {
            modeSwitch.setText("Dark Mode");
        } else {
            modeSwitch.setText("Light Mode");
        }

        String resourcePath = "/org/example/theschedulerv2/" + newStylesheet;
        String externalForm = getClass().getResource(resourcePath).toExternalForm();
        scene.getStylesheets().add(externalForm);

        System.out.println("New Stylesheet Added: " + newStylesheet);
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

        for (Schedule s : curUser.getSavedSchedules()){
            scheduleList.getItems().addAll(s.getScheduleName());
        }

        //scheduleList.getItems().addAll(schedules);
        scheduleList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentSchedule = scheduleList.getSelectionModel().getSelectedItem();
                totalCredits = 0;
                scheduleName.setText(currentSchedule);
                currSchedule = curUser.getSchedule(currentSchedule);
                // Create an iterator to safely remove elements
                Iterator<Node> iterator = scheduleGridPane.getChildren().iterator();

                // Iterate through the children of the GridPane
                while (iterator.hasNext()) {
                    Node node = iterator.next();
                    // Check if the child is a label representing a class (based on ID or style class)
                    if (node instanceof Label && ((Label) node).getStyleClass().contains("class-label")) {
                        // Remove the class label from the GridPane
                        iterator.remove(); // Use the iterator's remove method
                    }
                }
                for (Class c : currSchedule.getClassesInSchedule()){
                    addToGridPane(c);
                    addCredits(c.getNumCredits());
                }
            }
        });

        searchResults.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // listen for double click events on the selected item
                searchResults.setOnMouseClicked(mouseEvent -> {
                    if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
                        String s = searchResults.getSelectionModel().getSelectedItem();
                        Object indexInDBObject = searchResults.getProperties().get(s);
                        int index = (int) indexInDBObject;
                        Class c = Search.getClassByID(index);

                        boolean hasConflict = false;
                        for(Class cTemp : currSchedule.getClassesInSchedule()){
                            if(c.hasConflict(cTemp)){
                                hasConflict = true;
                                break;
                            }
                        }

                        if(hasConflict)
                        {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Class Conflict!");
                            alert.setHeaderText("This class cannot be added due to a conflict");
                            alert.showAndWait();
                        }
                        else{
                            currSchedule.addCourse(c);
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirm Class to Add");
                            alert.setHeaderText("Are you sure you want to add this Class?");
                            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                            alert.getButtonTypes().setAll(yes,no);
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == yes) {
                                addToGridPane(c);
                                addCredits(c.getNumCredits());
                            }
                        }
                    }
                });
            }
        });

        scheduleName.textProperty().addListener((observable, oldValue, newValue) -> {
            // update the nameOfSchedule variable whenever the text changes
            nameOfSchedule = newValue;
        });

        updateCreditsLabel();
      
        nameField.textProperty().addListener((observable, oldValue, newValue)->{
            this.nameSuggestions.setVisible(false);
            this.nameSuggestions.getItems().clear();

            if(newValue.length() > oldValue.length()){
                // Finding the word they are currently searching for
                int index = 0;
                for(int i = newValue.length()-1; i>=0; i--){
                    if(newValue.charAt(i) == ' ') {
                        index = i+1;
                        break;
                    }
                }

                String currWord = newValue.substring(index);
                ArrayList<String> temp = new ArrayList<>();
                if(!currWord.isEmpty()) {
                    ArrayList<String> allSuggests = autoFill.nameResults(currWord);

                    for (int i = 0; i < 3; i++) {
                        if (i == allSuggests.size()) break;
                        if (allSuggests.get(i).charAt(0) == currWord.charAt(0)) {
                            temp.add(allSuggests.get(i));
                        }
                    }

                    ObservableList<String> searchSuggests = FXCollections.observableList(temp);

                    this.nameSuggestions.setItems(searchSuggests);
                    this.nameSuggestions.setVisible(true);
                }
            }
        });

        nameSuggestions.setOnMouseClicked(event -> {
            if(nameField.getText().contains(" ")){
                int index = 0;
                int length = nameField.getText().length();
                if(length != 0){
                    for(int i = length-1; i>=0; i--){
                        if(nameField.getText().charAt(i) == ' '){
                            index = i;
                            break;
                        }
                    }
                    nameField.setText(nameField.getText().substring(0,index)+
                            " " + nameSuggestions.getSelectionModel().getSelectedItem());
                }


            }else
                nameField.setText(nameSuggestions.getSelectionModel().getSelectedItem());


            nameSuggestions.setVisible(false);
        });

        codeField.textProperty().addListener((observable, oldValue, newValue)->{
            this.idSuggestions.setVisible(false);
            this.idSuggestions.getItems().clear();

            if(newValue.length() > oldValue.length()){

                // Finding the word they are currently searching for
                int index = 0;
                for(int i = newValue.length()-1; i>=0; i--){
                    if(newValue.charAt(i) == ' ') {
                        index = i+1;
                        break;
                    }
                }

                String currWord = newValue.substring(index);
                ArrayList<String> temp = new ArrayList<>();
                ArrayList<String> allSuggests = autoFill.idResults(currWord);

                for(int i = 0; i < 3; i++){
                    if(i == allSuggests.size()) break;
                    if(allSuggests.get(i).charAt(0) == currWord.charAt(0)){
                        temp.add(allSuggests.get(i));
                    }
                }

                ObservableList<String> searchSuggests = FXCollections.observableList(temp);

                this.idSuggestions.setItems(searchSuggests);
                if(!searchSuggests.isEmpty()){
                    this.idSuggestions.setVisible(true);
                }
            }
        });

        idSuggestions.setOnMouseClicked(event -> {
            // Replace text in the TextField with the selected suggestion
            codeField.setText(idSuggestions.getSelectionModel().getSelectedItem());
            idSuggestions.setVisible(false);
        });
    }

    private void addToGridPane(Class c) {
        // parse the daysOfWeek string
        String daysOfWeek = c.getDaysOfWeek();
        // get start and end times of the class
        int startTime = c.getBeginTime();
        int endTime = c.getEndTime();

        // map days to their corresponding column index in the grid pane
        Map<Character, Integer> dayToColumn = new HashMap<>();
        dayToColumn.put('M', 1);
        dayToColumn.put('T', 2);
        dayToColumn.put('W', 3);
        dayToColumn.put('R', 4);
        dayToColumn.put('F', 5);

        // iterate over daysOfWeek string
        for (int i = 0; i < daysOfWeek.length(); i++) {
            char day = daysOfWeek.charAt(i);
            // get corresponding column index for day
            int columnIndex = dayToColumn.get(day);

            // calculate start and end row index based on start and end times
            int startRow = (startTime - 800) / 100 + 1;
            int endRow = (endTime - 800) / 100 + 1;

            // calculate rowspan based on duration
            int rowspan = endRow - startRow + 1;

            // calculate the portion of the last row the class occupies
            double endFraction = (endTime % 100) / 60.0;

            // add class to grid pane
            String classInfo = c.getCourseName();
            Label classLabel = new Label(classInfo);
            classLabel.getStyleClass().add("class-label");
            classLabel.setStyle("-fx-background-color: #8B0000; -fx-text-fill: #FFFFFF; -fx-padding: 5px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
            GridPane.setColumnIndex(classLabel, columnIndex);
            GridPane.setRowIndex(classLabel, startRow);
            GridPane.setRowSpan(classLabel, rowspan);

            // calculate preferred height based on duration
            double preferredHeight = 40.0 * (rowspan - 1 + endFraction); // Assuming each row corresponds to 40 pixels in height
            classLabel.setPrefHeight(preferredHeight);
            classLabel.setMinHeight(Region.USE_PREF_SIZE);
            classLabel.setMaxHeight(Region.USE_PREF_SIZE);

            classLabel.getProperties().put(classInfo, c.getIndexInDB());

            // listen for double click events on the selected item
            classLabel.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
                    String labelText = classLabel.textProperty().get();
                    Object indexObj = classLabel.getProperties().get(labelText);
                    int index = (int) indexObj;
                    System.out.println(index);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirm Class Removal");
                    alert.setHeaderText("Are you sure you want to remove this class?");
                    ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(yes, no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == yes) {
                        currSchedule.removeCourse(index);
                        // Create an iterator to safely remove elements
                        Iterator<Node> iterator = scheduleGridPane.getChildren().iterator();
                        // Iterate through the children of the GridPane
                        while (iterator.hasNext()) {
                            Node node = iterator.next();
                            Object o = node.getProperties().getOrDefault(labelText, 0);
                            int in = (int) o;
                            // Check if the child is a label representing a class (based on ID or style class)
                            if (node instanceof Label && in == index) {
                                // Remove the class label from the GridPane
                                iterator.remove(); // Use iterators remove method
                            }
                        }
                        removeCredits(c.getNumCredits());
                    }
                }
            });

            scheduleGridPane.getChildren().add(classLabel);

            // Tooltip to display class details
            Tooltip tooltip = new Tooltip("Course: " + c.getCourseName() + "\n" +
                    "Instructor: " + c.getInstructor() + "\n" +
                    "Time: " + startTime + " - " + endTime);
            Tooltip.install(classLabel, tooltip);
        }
        tab.getSelectionModel().select(scheduleTab);
    }

    // Method to add credits
    public void addCredits(int credits) {
        totalCredits += credits;
        updateCreditsLabel();
    }

    // Method to remove credits
    public void removeCredits(int credits) {
        totalCredits -= credits;
        updateCreditsLabel();
    }

    // Method to update the credits label text
    private void updateCreditsLabel() {
        creditsLabel.setText("Total Credits: " + totalCredits);
    }
}
