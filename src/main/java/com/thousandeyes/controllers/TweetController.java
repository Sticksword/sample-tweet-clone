package com.thousandeyes.controllers;

import com.thousandeyes.entities.follower.Follower;
import com.thousandeyes.entities.person.Person;
import com.thousandeyes.entities.tweet.Tweet;
import com.thousandeyes.services.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Michael on 3/14/2017.
 */
@RestController
public class TweetController {

    private TwitterService twitterService;

    @Autowired
    public TweetController(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    @RequestMapping(value = "/example", method = RequestMethod.GET)
    public String index(@AuthenticationPrincipal final UserDetails user) {
        return "Greetings " + user.getUsername() + " from Spring Boot!";
    }

    @RequestMapping(value = "/tweets", method = RequestMethod.GET)
    @ResponseBody
    public List<Tweet> getTweets(@RequestParam(defaultValue = "1") final String userId, @RequestParam(defaultValue = "") final String filter) {
        return twitterService.getRelatedTweets(Integer.valueOf(userId), filter);
    }

    @RequestMapping(value = "/followers", method = RequestMethod.GET)
    @ResponseBody
    public List<Follower> getFollowers(@RequestParam(defaultValue = "1") final String userId) {
        return twitterService.getFollowers(Integer.valueOf(userId));
    }

    @RequestMapping(value = "/following", method = RequestMethod.GET)
    @ResponseBody
    public  List<Follower> getFollowing(@RequestParam(defaultValue = "1") final String userId) {
        return twitterService.getFollowing(Integer.valueOf(userId));
    }

    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    @ResponseBody
    public boolean follow(@RequestParam(defaultValue = "1") final String userId, @RequestParam final int personToFollowId) {
        return twitterService.follow(Integer.valueOf(userId), personToFollowId);
    }

    @RequestMapping(value = "/unfollow", method = RequestMethod.POST)
    @ResponseBody
    public boolean unfollow(@RequestParam(defaultValue = "1") final String userId, @RequestParam final int followedPersonId) {
        return twitterService.unfollow(Integer.valueOf(userId), followedPersonId);
    }

}
