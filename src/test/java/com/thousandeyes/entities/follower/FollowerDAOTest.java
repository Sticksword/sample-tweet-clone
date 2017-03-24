package com.thousandeyes.entities.follower;

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
public class FollowerDAOTest {
    @Autowired
    private FollowerDAO followerDAO;


    @Before
    public void setUp() throws Exception {
        // TODO insert DAO tests
    }

    @Test
    public void contexLoads() throws Exception {
        assertThat(followerDAO).isNotNull();
    }

    @Test
    public void getFollowers() throws Exception {

    }

    @Test
    public void getFollowing() throws Exception {

    }

    @Test
    public void follow() throws Exception {

    }

    @Test
    public void unfollow() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }
}