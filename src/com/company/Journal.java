package com.company;

public class Journal {
    private String name;
    private String page;
    Journal(String name , String page){
        this.name = name;
        this.page = page;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }
}
