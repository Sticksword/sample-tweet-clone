package com.thousandeyes.entities.person;

/**
 * Created by Michael on 3/21/2017.
 */
public class Person {

    private int personId;
    private String personName;
    private int mostPopularFollowerId;
    private int mostPopularFollowerFollowerCount; // technically don't need this

    public Person() {

    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getMostPopularFollowerId() {
        return mostPopularFollowerId;
    }

    public void setMostPopularFollowerId(int mostPopularFollowerId) {
        this.mostPopularFollowerId = mostPopularFollowerId;
    }

    public int getMostPopularFollowerFollowerCount() {
        return mostPopularFollowerFollowerCount;
    }

    public void setMostPopularFollowerFollowerCount(int mostPopularFollowerFollowerCount) {
        this.mostPopularFollowerFollowerCount = mostPopularFollowerFollowerCount;
    }
}
