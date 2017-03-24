package com.thousandeyes.entities.tweet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Michael on 3/24/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TweetDAOTest {
    @Autowired
    private TweetDAO tweetDAO;


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void contexLoads() throws Exception {
        assertThat(tweetDAO).isNotNull();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void fetchSelfTweets() throws Exception {

    }

    @Test
    public void fetchIdolTweets() throws Exception {

    }

}