package org.example.theschedulerv2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

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
    private TextField startField;
    @FXML
    private TextField endField;
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
    private ChoiceBox<String> yearField;
    @FXML
    private ChoiceBox<String> majorField;

    private String[] yearList = {"Freshmen", "Sophomore", "Junior", "Senior"};

    private String[] departmentList = {"ACCT", "ART", "ASTR", "BIOL", "CHEM",
            "CMIN", "COMM", "COMP", "DESI", "ECON", "EDUC", "ELEE", "ENGL", "ENGR",
            "ENTR", "EXER", "FNCE", "FREN", "GEOL", "GREK", "HEBR", "HIST", "HUMA",
            "INBS", "LATN", "MARK", "MATH", "MECE", "MNGT", "MUSI", "NURS", "PHIL",
            "PHYE", "PHYS", "POLS", "PSYC", "RELI", "ROBO", "SCIC", "SEDU", "SOCI",
            "SOCW", "SPAN", "SSFT", "THEA", "WRIT"};

    private String[] instructorList = {"Agnew, Rochelle", "Al Moakar, Lory", "Allison, Blair", "Anderson, Erik",
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

    String name;
    String department;
    String instructor;
    String code;
    int start;
    int end;
    String day;
    String major;
    String year;
    private Stage stage;

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

            if (!Objects.equals(nameField.getText(), "")) {
                name = nameField.getText();
            } else {
                name = "";
            }
            if (!Objects.equals(departmentField.getValue(), "")) {
                department = departmentField.getValue();
            } else {
                department = "";
            }
            if (!Objects.equals(instructorField.getValue(), "")) {
                instructor = instructorField.getValue();
            } else {
                instructor = "";
            }
            if (!Objects.equals(codeField.getText(), "")) {
                code = codeField.getText();
            } else {
                code = "";
            }
            if (!Objects.equals(startField.getText(), "")) {
                start = Integer.parseInt(startField.getText());
            } else {
                start = 0;
            }
            if (!Objects.equals(endField.getText(), "")) {
                end = Integer.parseInt(endField.getText());
            } else {
                end = 2359;
            }

            System.out.println(name);
            System.out.println(department);
            System.out.println(instructor);
            System.out.println(code);
            System.out.println(start);
            System.out.println(end);
            System.out.println(day);

            try {
                Parent child = FXMLLoader.load(getClass().getResource("courseWindow.fxml"));
                Scene scene = new Scene(child, 320, 240);
                stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Second Window!");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);

                stage.showAndWait();
            } catch (Exception e) {
                System.out.println("Search Error: " + e);
            }

        } catch (Exception e) {
            System.out.println("Exception Message: " + e);
        }
    }

    public void closeList(Stage stage) {
        stage.close();
    }

    //Initializes all dropdown options in mainWindow with their expected options
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        departmentField.getItems().addAll(departmentList);
        instructorField.getItems().addAll(instructorList);
        majorField.getItems().addAll(departmentList);
        yearField.getItems().addAll(yearList);
    }
}
