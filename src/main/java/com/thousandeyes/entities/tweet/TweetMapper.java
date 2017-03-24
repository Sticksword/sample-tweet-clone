package com.thousandeyes.entities.tweet;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Michael on 3/21/2017.
 */
public class TweetMapper implements RowMapper {
    public Tweet mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tweet tweet = new Tweet();
        tweet.setTweetId(rs.getInt("id"));
        tweet.setMessage(rs.getString("content"));
        tweet.setPersonId(rs.getInt("person_id"));
        return tweet;
    }
}
