package com.thousandeyes.controllers;

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
    public List<Tweet> getTweets(@AuthenticationPrincipal final UserDetails user, @RequestParam(defaultValue = "") final String filter) {
        return twitterService.getRelatedTweets(1, filter);
    }

    @RequestMapping(value = "/followers", method = RequestMethod.GET)
    @ResponseBody
    public List<Person> getFollowers(@AuthenticationPrincipal final UserDetails user) {
        return twitterService.getFollowers(1);
    }

    @RequestMapping(value = "/following", method = RequestMethod.GET)
    @ResponseBody
    public  List<Person> getFollowing(@AuthenticationPrincipal final UserDetails user) {
        return twitterService.getFollowing(1);
    }

    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    @ResponseBody
    public boolean follow(@AuthenticationPrincipal final UserDetails user, @RequestParam final int personToFollowId) {
        return twitterService.follow(1, personToFollowId);
    }

    @RequestMapping(value = "/unfollow", method = RequestMethod.POST)
    @ResponseBody
    public boolean unfollow(@AuthenticationPrincipal final UserDetails user, @RequestParam final int followedPersonId) {
        return twitterService.unfollow(1, followedPersonId);
    }

}
