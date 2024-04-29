package org.example.theschedulerv2;
import java.util.Arrays;
import java.util.Scanner;

public class Class {
    private String courseID; // eg COMP350A -- will be unique
    private String courseName;
    private int numCredits;
    private String daysOfWeek;
    private int beginTime; // Military Time
    private int endTime; // Military Time
    private String instructor;

    private String department;
    private int indexInDB;

//    private String description;

    public Class(String classToAdd){
        Scanner scan = new Scanner(classToAdd);
        this.department = scan.next();
        String number = scan.next();
        String section = scan.next();

        String className = "";
        while(!scan.hasNextInt()){
            String temp = scan.next();
            if(scan.hasNextInt()) className += temp;
            else className += temp + " ";
        }

        this.courseName = className;

        this.numCredits = scan.nextInt();
        this.daysOfWeek = scan.next();


        for(int i = 0; i<2; i++){
            String time = scan.next();
            if(i == 0) scan.useDelimiter("-");
            String amPM = scan.next();
            scan.useDelimiter(" ");
            System.out.println("HERE " +amPM);
            if(time.startsWith("-")) time = time.substring(1);

            Scanner overTime = new Scanner(time);
            overTime.useDelimiter(":");

            int hour = overTime.nextInt();
            System.out.println("Hour: " + hour);
            int minute = overTime.nextInt();
            System.out.println("Minute: " + minute);

            if(amPM.equals("PM") || amPM.equals(" PM")){
                if(hour != 12){
                    hour += 12;
                }
            }
            hour*=100;

            if(i == 0) this.beginTime = hour+minute;
            else this.endTime = hour+minute;
        }

        String instructorName = "";

        while(scan.hasNext()) instructorName += scan.next() + " ";

        this.instructor = instructorName.stripTrailing();
        this.courseID = department + " " + number + " " + section;
        this.indexInDB = -1;
    }

    public Class(String courseID, String courseName, int numCredits,
                 String daysOfWeek, int beginTime, int endTime,
                 String instructor, String department, int indexInDB) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.numCredits = numCredits;
        this.daysOfWeek = daysOfWeek;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.instructor = instructor;
        this.department = department;
        this.indexInDB = indexInDB;
    }

    public int getIndexInDB() {
        return indexInDB;
    }

    public void setIndexInDB(int n){
        indexInDB = n;
    }

    public int getNumCredits() {
        return numCredits;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getDaysOfWeek(){
        return daysOfWeek;
    }

    public String getDepartment() {
        return department;
    }

    public String getCourseID() {
        return courseID;
    }

    @Override
    public String toString() {

        return courseID + " " + courseName + " " + numCredits + " "+ daysOfWeek + " " +
                convertTime(beginTime) + "-" + convertTime(endTime) + " " + instructor;
    }

    public String convertTime(int time){
        StringBuilder returnable = new StringBuilder();
        if(time<1200){
            returnable.append(time / 100);
            returnable.append(":");

            if(time % 100 < 10) returnable.append("0");

            returnable.append(time % 100);
            returnable.append(" AM");
            return returnable.toString();
        }else if(time < 1300){
            returnable.append(time/100);
            returnable.append(":");

            if(time % 100 < 10) returnable.append("0");

            returnable.append(time%100);
            returnable.append(" PM");
            return returnable.toString();
        }

        time -= 1200;
        returnable.append(time / 100);
        returnable.append(":");

        if(time % 100 < 10) returnable.append("0");

        returnable.append(time % 100);
        returnable.append(" PM");

        return returnable.toString();
    }

    public String getCourseName() {
        return courseName;
    }


    public boolean hasConflict(Class other){
        boolean sameDay = false;
        for(int i = 0; i < other.daysOfWeek.length(); i++){
            for(int j = 0; j < this.daysOfWeek.length(); j++){
                if(daysOfWeek.charAt(i) == other.daysOfWeek.charAt(i)){
                    sameDay = true;
                    break;
                }
            }

        }

        boolean timeOverlap = false;

        if(this.beginTime == other.beginTime) timeOverlap = true;
        else if(this.beginTime > other.beginTime && this.endTime <= other.endTime){
            timeOverlap = true;
        }else if(other.beginTime > this.beginTime && other.endTime <= this.endTime) {
            timeOverlap = true;
        }

        return sameDay && timeOverlap;
    }

    public boolean isSameClass(Class other){
        return this.courseID.equals(other.courseID) && this.instructor.equals(other.instructor);
    }
}

