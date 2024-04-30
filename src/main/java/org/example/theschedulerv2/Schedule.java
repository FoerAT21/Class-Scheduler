package org.example.theschedulerv2;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Schedule {
    private ArrayList<ArrayList<Class>> database;
    private String scheduleName;
    private ArrayList<Class> classesInSchedule;
    private int numCredits;


    public Schedule(){
        this.scheduleName = "";
        this.numCredits = 0;
        this.classesInSchedule = new ArrayList<>();
    }
    public Schedule(ArrayList<ArrayList<Class>> db){
        this.database = db;
        this.scheduleName = "";
        this.numCredits = 0;
        this.classesInSchedule = new ArrayList<>();
    }

    public Schedule(ArrayList<ArrayList<Class>> db, String scheduleName){
        this.database = db;
        this.scheduleName = scheduleName;
        numCredits = 0;
        classesInSchedule = new ArrayList<>();
    }

    public Schedule(String scheduleName){
        this.scheduleName = scheduleName;
        numCredits = 0;
        classesInSchedule = new ArrayList<>();
    }

    // Getter for scheduleName
    public String getScheduleName() {
        return scheduleName;
    }

    public int getNumCredits(){
        return numCredits;
    }

    public void setScheduleName(String name){
        this.scheduleName = name;
    }

    public void printForRemove(){
        for(int i = 0; i < classesInSchedule.size(); i++){
            System.out.println((i+1) + " " + classesInSchedule.get(i).toString());
        }
    }

    public boolean addCourse(Class courseToAdd){
        if (courseToAdd != null) {
            int classCredits = courseToAdd.getNumCredits();
            numCredits += classCredits;

            // Add the course to the list of classes in the schedule
            classesInSchedule.add(courseToAdd);
            return true;
        } else {
            return false;
        }
    }

    // this should look up the class in the db - if not found - exception
    // if found, create class object and check for conflicts in current using the Class.hasConflict
    // Returns false if there is a conflict, true if not.
    public boolean addCourse(int index){
        //ArrayList<Class> courseToAdd = database.get(index);
        Class courseToAdd = Search.getClassByID(index);

//        for(Class c : this.classesInSchedule){
//            if(c.hasConflict(courseToAdd)) {
//                System.out.println(courseToAdd.getCourseName() + " cannot be added because it conflicts with " + c.getCourseName() + ".");
//                return false;
//            }
//        }
        if (courseToAdd != null) {
            int classCredits = courseToAdd.getNumCredits();
            numCredits += classCredits;

            // Add the course to the list of classes in the schedule
            classesInSchedule.add(courseToAdd);
            return true;
        } else {
            return false;
        }
    }

    public void removeCourse(int indexInDB){
        for (Class c : classesInSchedule) {
            if (c.getIndexInDB() == indexInDB){
                classesInSchedule.remove(c);
            }
        }

    }

    /**
     * This method saves a Schedule by passing itself into the addSchedule method of User
     *
     * addSchedule will take care of adding this Schedule to its savedSchedules ArrayList
     *
     * @param s : An instance of the User class
     */
    public void saveSchedule(User s)
    {
        try
        {
            // Add schedule to User's SavedSchedules ArrayList
            ArrayList<Schedule> temp = s.getSavedSchedules();
            for(int i = 0; i<s.getSavedSchedules().size(); i++){
                if(temp.get(i).scheduleName.equals(this.scheduleName)){
                    temp.remove(i);
                    i--;
                }
            }
            s.addSchedule(this);

            // Load SavedSchedules.txt
            File oldFile = new File("src/SavedSchedules.txt");

            // Create a new file to write to
            File newFile = new File("src/newSavedSchedules.txt");

            // BufferedReader to read through oldFile
            BufferedReader br = new BufferedReader(new FileReader(oldFile));

            // PrintWriter to write to newFile
            PrintWriter pw = new PrintWriter(newFile);

            // Declaring a string variable to hold each line
            String line;

            // Loop until end of file is reached
            while((line = br.readLine()) != null)
            {
                // Just grab the first thing before comma to check if equal
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");

                if(!lineScanner.next().equals(this.scheduleName))
                {
                    pw.write(line + "\n");
                    pw.flush();
                }
            }

            // Close br
            br.close();

            //Delete oldFile
            if(!oldFile.delete())
            {
                System.err.println("Failed to delete the old schedule file.");
                return;
            }

            // Saving the schedule in newFile
            pw.write(this.scheduleName + ",");
            for (Class c : classesInSchedule) {
                pw.write(c.getIndexInDB() + ",");
                pw.flush();
            }

            // Write a new line
            pw.write("\n");
            pw.flush();

            // Close pw
            pw.close();

            //Rename newFile to SavedSchedules.txt
            if(!newFile.renameTo(oldFile))
            {
                System.err.println("Failed to rename the new schedule file.");
            }
        }
        catch(IOException e)
        {
            System.err.println("An error occurred while saving the schedule: " + e.getMessage());
        }
    }

    public void downloadSchedule(){

    }

    /**
     * This method takes a GCC major as a String input and prints the recommended schedule
     * for that major to the console. It uses preformatted txt files in the Recommended_Schedules folder.
     * @param major : major of the recommended schedule to display
     * @throws IOException
     */
    public static String retRecSchedule(String major) throws IOException {
        // Formats the pathname to the necessary PDF file based on major
        String pathName = "src/Recommended_Schedules/" + major + ".txt";

        // Loading an existing document
        File file = new File(pathName);

        // Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String st;
        StringBuilder toRet = new StringBuilder();

        // Loop until end of file is reached
        while ((st = br.readLine()) != null)
            // Append to toRet
            toRet.append(st).append("\n");

        return toRet.toString();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n---------------------------------------------------------------------------------------------\n");
        sb.append(this.scheduleName + "\n");
        sb.append("Number of Credits: " + numCredits + "\n-----------------------\n");

        for(int i = 0; i < classesInSchedule.size(); i++){
            sb.append(classesInSchedule.get(i).toString() + "\n");
        }

        sb.append("---------------------------------------------------------------------------------------------\n");

        return sb.toString();
    }

    public ArrayList<Class> getClassesInSchedule(){
        return classesInSchedule;
    }

    // for Console purposes -- just to see what is in Schedule for Sprint 1
    public void printSchedule(){
        int i = 1;
//        for (java.lang.Class c : classesInSchedule){
//            StringBuilder s = new StringBuilder();
//            s.append("Course: ").append(i).append(": ").append(c.getCourseID()).append(" | ").append(c.getCourseName()).append(" | ").append(c.getInstructor()).append(" | ").append(c.getNumCredits()).append(" | ").append(c.getBeginTime()).append(" - ").append(c.getEndTime());
//            System.out.println(s);
//            i++;
//        }
    }
}
