package com.stitp.recommendation.result;

import java.io.Serializable;

public class Order implements Serializable {

    private  static  final long serialVersionUID = 1L;

    private String ID;
    private String SongName;
    private String Author;
    private String Album;
    private Integer Type;
    private Integer Number;
    private Integer Popularity;

    public Order(){}

    public Order(Integer type, Integer number) {
        this.Type = type;
        this.Number = number;
    }

    public Order(String id, String songname, String author, String album, Integer type, Integer number, Integer popularity){
        this.ID = id;
        this.SongName = songname;
        this.Author = author;
        this.Album = album;
        this.Type = type;
        this.Number = number;
        this.Popularity = popularity;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setAblum(String ablum) {
        Album = ablum;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public void setNumber(Integer number) {
        Number = number;
    }

    public void setPopularity(Integer popularity) {
        Popularity = popularity;
    }

    public String getID() {

        return ID;
    }

    public String getSongName() {
        return SongName;
    }

    public String getAuthor() {
        return Author;
    }

    public String getAblum() {
        return Album;
    }

    public Integer getType() {
        return Type;
    }

    public Integer getNumber() {
        return Number;
    }

    public Integer getPopularity() {
        return Popularity;
    }

    @Override
    public String toString() {
        return this.ID + "  " +this.SongName;
    }
}