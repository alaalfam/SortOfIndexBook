package com.company;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class DB {
    private ArrayList<Person> people = new ArrayList<>();
    DB(){

    }
    DB(String [][] arrSheet){
        for (int i = 0; i < arrSheet.length; i++) {
            // arrSheet[i][0] = personName
            // arrSheet[i][1] = journalName
            // arrSheet[i][2] = page
            if (arrSheet[i][0].equals("") && arrSheet[i][1].equals("") && arrSheet[i][2].equals("")){
                continue;
            }
            else if (arrSheet[i][0].equals("")){
                String journalName = arrSheet[i][1];
                String page = arrSheet[i][2];
                people.get(people.size()-1).addJournal(journalName , page);
            }
            else {
                String name = arrSheet[i][0];
                String journalName = arrSheet[i][1];
                String page = arrSheet[i][2];
                StringTokenizer tokenizer = new StringTokenizer(name, "،", false);
                String lastName = tokenizer.nextToken();
                String firstName = "";
                if (tokenizer.hasMoreTokens()) {
                    firstName = tokenizer.nextToken();
                }
                Person person = new Person(firstName, lastName);
                person.addJournal(journalName, page);
                people.add(person);
            }
        }
    }
    public void setPeople(){

    }

    public ArrayList<Person> getPeople() {
        return people;
    }
    private void swapPeople(int i , int j){
        Person person = people.get(j);
        people.set(j , people.get(i));
        people.set(i , person);
    }
    public void sortPeople(){
        for (int i = 0; i < people.size(); i++) {
            for (int j = i+1; j < people.size(); j++) {
                String lastName1 = people.get(i).getLastName();
                String firstName1 = people.get(i).getFirstName();
                String lastName2 = people.get(j).getLastName();
                String firstName2 = people.get(j).getFirstName();
                String compareLastname = Main.stringCompare(lastName1 , lastName2);
                if (compareLastname.equals(lastName2)){
                    swapPeople(i , j);
                }
                else if (compareLastname.equals("=")){
                    String compareFirstName = Main.stringCompare(firstName1 , firstName2);
                    if (compareFirstName.equals(firstName2)){
                        swapPeople(i , j);
                    }
                }
            }
        }
    }
}
