package org.example.theschedulerv2;

import java.util.ArrayList;

public class SmartSearch {
    private BKTree courseIDs;
    private BKTree courseNames;

    public SmartSearch() {

        try{
            courseIDs = new BKTree("src/course_id_dict.txt",
                    2);
            courseNames = new BKTree("src/course_name_dict.txt",
                    2);
        }catch(Exception e){
            System.out.println("Something is severely wrong ");
        }
    }

    public ArrayList<String> idResults(String word){
        return this.courseIDs.search(word);
    }

    public ArrayList<String> nameResults(String word){
        return this.courseNames.search(word);
    }
}
