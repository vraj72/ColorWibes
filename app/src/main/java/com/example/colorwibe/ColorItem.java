package com.example.colorwibe;

public class ColorItem {
    private String hash;
    private String extra1;
    private String extra2;

    public ColorItem(String hash, String extra1, String extra2) {
        this.hash = hash;
        this.extra1 = extra1;
        this.extra2 = extra2;
    }



    public String getHash() {
        return hash;
    }

    public String getExtra1() {
        return extra1;
    }

    public String getExtra2() {
        return extra2;
    }
}
