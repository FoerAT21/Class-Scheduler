package org.example.theschedulerv2;

import java.util.ArrayList;

public class SmartSearch {
    private ArrayList<String> correctWords;
    private String idQueryWord;
    private String nameQueryWord;
    private BKTree courseIDs;
    private BKTree courseNames;



    public SmartSearch() {
        this.idQueryWord = "";
        this.nameQueryWord = "";

        correctWords = new ArrayList<>();
        try{
            courseIDs = new BKTree("C:\\Users\\FOERSTAT21\\OneDrive - Grove City College\\Semester 6\\Software Engineering\\TheSchedulerV2\\src\\course_id_dict.txt",
                    2);
            courseNames = new BKTree("C:\\Users\\FOERSTAT21\\OneDrive - Grove City College\\Semester 6\\Software Engineering\\TheSchedulerV2\\src\\course_name_dict.txt",
                    2);
        }catch(Exception e){
            System.out.println("Something is severely wrong ");
        }
    }

    public BKTree getCourseNames(){
        return this.courseNames;
    }

    public BKTree getCourseIDs(){
        return this.courseIDs;
    }

    public void setIdQueryWord(String queryWord){
        this.idQueryWord = queryWord;
    }

    public void setNameQueryWord(String queryWord){
        this.nameQueryWord = queryWord;
    }

    public String getIdQueryWord(){
        return this.idQueryWord;
    }

    public String getNameQueryWord(){
        return this.nameQueryWord;
    }



}
