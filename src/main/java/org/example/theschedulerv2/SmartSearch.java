package org.example.theschedulerv2;

import java.util.ArrayList;

public class SmartSearch {
    private String idQueryWord;
    private String nameQueryWord;
    private BKTree courseIDs;
    private BKTree courseNames;



    public SmartSearch() {
        this.idQueryWord = "";
        this.nameQueryWord = "";

        try{
            courseIDs = new BKTree("src/course_id_dict.txt",
                    2);
            courseNames = new BKTree("src/course_name_dict.txt",
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

    public ArrayList<String> idResults(){
        return this.courseIDs.search(this.idQueryWord);
    }

    public ArrayList<String> nameResults(){
        return this.courseNames.search(this.nameQueryWord);
    }


}
