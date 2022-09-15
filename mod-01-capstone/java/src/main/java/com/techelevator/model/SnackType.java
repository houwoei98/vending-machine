package com.techelevator.model;

public enum SnackType {
    CHIP( "Crunch Crunch, Yum!"),
    CANDY("Munch Munch, Yum!"),
    DRINK("Glug Glug, Yum!"),
    GUM("Chew Chew, Yum!"),
    DEFAULT("");

    public String sound;

    SnackType(String s) {
        this.sound = s;
    }
}
