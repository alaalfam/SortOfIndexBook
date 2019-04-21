package com.company;

import java.util.ArrayList;

public class Person {
    private String firstName;
    private String lastName;
    private ArrayList<Journal> journals = new ArrayList<>();
    Person(String firstName , String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getJornalsCount(){
        return journals.size();
    }
    public void addJournal(String journalName , String page){
        Journal journal = new Journal(journalName , page);
        journals.add(journal);
    }

    public Journal getJournal(int i){
        if (i < journals.size() && i>=0){
            return journals.get(i);
        }
        else
            return null;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

}
