package com.mh3yad.mylib;

public class Book {
    private int id;
    private int pages;
    private String name;
    private String imageUrl;
    private String author;
    private String shortDec;
    private String longtDec;
    private boolean isExpanded;

    public Book(int id, int pages, String name, String imageUrl, String author, String shortDec, String longDec) {
        this.id = id;
        this.pages = pages;
        this.name = name;
        this.imageUrl = imageUrl;
        this.author = author;
        this.shortDec = shortDec;
        this.longtDec = longDec;
        isExpanded= false;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getShortDec() {
        return shortDec;
    }

    public void setShortDec(String shortDec) {
        this.shortDec = shortDec;
    }

    public String getLongDec() {
        return longtDec;
    }

    public void setLongDec(String longtDec) {
        this.longtDec = longtDec;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", pages=" + pages +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", author='" + author + '\'' +
                ", shortDec='" + shortDec + '\'' +
                ", longtDec='" + longtDec + '\'' +
                '}';
    }
}
