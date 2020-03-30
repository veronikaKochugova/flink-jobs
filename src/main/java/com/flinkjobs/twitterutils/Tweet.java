package com.flinkjobs.twitterutils;

public class Tweet {
    private String language;
    private String text;

    public Tweet(String language, String text) {
        this.language = language;
        this.text = text;
    }

    public String language() {
        return language;
    }

    public String text() {
        return text;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "language='" + language + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
