package com.company;
import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static char [] character = {'آ','ا','ب','پ','ت','ث','ج','چ','ح','خ','د','ذ','ر','ز','ژ','س','ش','ص','ض','ط','ظ','ع','غ','ف','ق','ک','گ','ل','م','ن','و','ه','ی','.','-','_','+','/'};

    public static char convertChar(char c){
        // أ = ا
        // ؤ = و
        // ء =
        // ئ = ی

        if (c == 'أ'){
            c = 'ا';
        }
        else if (c == 'ؤ'){
            c = 'و';
        }
        else if (c == 'ئ'){
            c = 'ی';
        }
        else if (c == 'ء'){
            c = 'ا';
        }
        return c;
    }

    public static char charCompare(char c1 , char c2){

        c1 = convertChar(c1);
        c2 = convertChar(c2);
        for (int i = 0; i < character.length; i++) {
            if (character[i] == c1 && character[i] == c2){
                return '=';
            }
            else if (character[i] == c1){
                return c1;
            }
            else if (character[i] == c2){
                return c2;
            }
        }
        return 'n';
    }

    public static String stringCompare(String str1 , String str2){
        int l1 = str1.length();
        int l2 = str2.length();
        for (int i = 0 , j=0 ; i < l1 && j<l2 ; i++ , j++) {
            while ( i<l1 && str1.charAt(i) == ' '){
                i++;
            }
            while ( j<l2 && str2.charAt(j) == ' '){
                j++;
            }
            if (i>=l1 || j>=l2){
                break;
            }
            boolean ok = false ;
            if (str1.charAt(i) == '(' || str1.charAt(i) == ')'){
                l1 = i+1;
                ok = true;
            }
            if (str2.charAt(j) == '(' || str2.charAt(j) == ')'){
                l2 = j+1;
                ok = true ;
            }
            if (ok){
                break;
            }

            if (charCompare(str1.charAt(i) , str2.charAt(j)) == str1.charAt(i)){
                return str1;
            }
            else if (charCompare(str1.charAt(i),str2.charAt(j)) == str2.charAt(j)){
                return str2;
            }
        }
        if (l1<l2){
            return str1;
        }
        else if (l1>l2){
            return str2;
        }
        return "=";
    }
    public static void printArr(String [][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]+"\t\t\t");
            }
            System.out.println();
        }
    }
    public static String [][] createArrSheet(ArrayList<Person> people){
        int rows = 0 ;
        for (int i = 0; i < people.size(); i++) {
            int countJournal = people.get(i).getJornalsCount();
            rows += countJournal;
        }
        String [][] arrSheet = new String[rows][3];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < 3; j++) {
                arrSheet[i][j] = "" ;
            }
        }
        for (int i = 0  , j = 0; i < people.size() && j<rows; i++ , j++) {
            String firstName = people.get(i).getFirstName();
            String lastName = people.get(i).getLastName();
            String name = lastName+"،"+firstName;
            arrSheet[j][0] = name;

            int countJournal = people.get(i).getJornalsCount();
            for ( int k=0 ; k < countJournal; k++) {
                arrSheet[j+k][1] = people.get(i).getJournal(k).getName();
                arrSheet[j+k][2] = people.get(i).getJournal(k).getPage();
            }
            j = j+countJournal-1;
        }
        return arrSheet;
    }
    public static void cleanPeople(ArrayList<Person> people){
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getLastName().equals("نام اشخاص") || ( people.get(i).getLastName().equals("")&&people.get(i).getFirstName().equals("")&&people.get(i).getJournal(0).getName().equals("")&&people.get(i).getJournal(0).getPage().equals("") )){
                people.remove(i);
                i--;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        ReadExcel readExcel = new ReadExcel("./src/com/company/Book1.xls");
        readExcel.read();
        String [][] arrSheet = readExcel.getArrSheet();
        DB db = new DB(arrSheet);
        db.sortPeople();
        ArrayList<Person> people = db.getPeople();
        cleanPeople(people);
        String [][] arrSheetSorted = createArrSheet(people);
        WriteExcel writeExcel = new WriteExcel("./src/com/company/WriteBook1.xls");
        writeExcel.write(arrSheetSorted);
    }
}
