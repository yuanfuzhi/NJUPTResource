package com.stitp.recommendation.result;

public class OrderData {
    private String id;

    private String name;

    private String singer;

    public OrderData(Order order) {
        this.id = order.getID();
        this.name = order.getSongName();
        this.singer = order.getAuthor();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
