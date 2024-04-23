package org.example.theschedulerv2;

import java.util.ArrayList;

public class SmartSearch {
    private ArrayList<String> correctWords;
    private String queryWord;
    private BKTree courseIDs;
    private BKTree courseNames;

    public SmartSearch() {
        this.queryWord = "";
        correctWords = new ArrayList<>();
        try{
            courseIDs = new BKTree("course_id_dict.txt");
            courseNames = new BKTree("course_name_dict.txt");
        }catch(Exception e){
            System.out.println("Something is severely wrong ");
        }
    }

    public void setQueryWord(String queryWord){
        this.queryWord = queryWord;
    }



}
