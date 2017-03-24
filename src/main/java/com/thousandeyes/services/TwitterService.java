package com.thousandeyes.services;

import com.thousandeyes.entities.follower.FollowerDAO;
import com.thousandeyes.entities.person.Person;
import com.thousandeyes.entities.person.PersonDAO;
import com.thousandeyes.entities.tweet.Tweet;
import com.thousandeyes.entities.tweet.TweetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Michael on 3/21/2017.
 */
@Service
public class TwitterService {

    private PersonDAO personDAO;
    private TweetDAO tweetDAO;
    private FollowerDAO followerDAO;

    @Autowired
    public TwitterService(PersonDAO personDAO, TweetDAO tweetDAO, FollowerDAO followerDAO) {
        this.personDAO = personDAO;
        this.tweetDAO = tweetDAO;
        this.followerDAO = followerDAO;
    }

    public List<Tweet> getRelatedTweets(int personId, String filter) {
        return Stream.concat(
                this.tweetDAO.fetchSelfTweets(personId, filter).stream(),
                this.tweetDAO.fetchIdolTweets(personId, filter).stream()
        ).collect(Collectors.toList());
    }

    public List<Person> getFollowers(int personId) {
        return followerDAO.getFollowers(personId); // it may seem that this extra abstraction is useless but it's for future proofing
    }

    public List<Person> getFollowing(int personId) {
        return followerDAO.getFollowing(personId); // same with this
    }

    public boolean follow(int userId, int personToFollowId) {
        return followerDAO.follow(userId, personToFollowId);
    }

    public boolean unfollow(int userId, int followedPersonId) {
        return followerDAO.unfollow(userId, followedPersonId);
    }
}
