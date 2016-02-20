package com.jokelib;

import java.util.ArrayList;

public class MyJokeData {
    private ArrayList<String> jokeList;

    public MyJokeData(int length) {
        jokeList = new ArrayList<String>();
        init(length);
    }

    private void init(int length){
        for (int i=0;i<length;i++){
            jokeList.add("sample Joke "+i);
        }
    }

    public ArrayList<String> getJokeList() {
        return jokeList;
    }

    public String getRandomJoke(){
        int rand = ((Double)(Math.random() * jokeList.size())).intValue();
        return jokeList.get(rand);
    }

}
