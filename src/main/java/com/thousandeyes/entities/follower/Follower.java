package com.thousandeyes.entities.follower;

/**
 * Created by Michael on 3/21/2017.
 */
public class Follower {

    private int mappingId;
    private int followerId;
    private int personId;

    public int getMappingId() {
        return mappingId;
    }

    public void setMappingId(int mappingId) {
        this.mappingId = mappingId;
    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
