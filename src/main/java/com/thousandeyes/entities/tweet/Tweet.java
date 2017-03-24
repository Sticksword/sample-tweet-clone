package com.thousandeyes.entities.tweet;

/**
 * Created by Michael on 3/21/2017.
 */
public class Tweet {

    private int tweetId;
    private String message;
    private int personId;

    public Tweet() {

    }

    public int getTweetId() {
        return tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
