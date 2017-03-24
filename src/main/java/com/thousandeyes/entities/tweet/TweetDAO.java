package com.thousandeyes.entities.tweet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Michael on 3/21/2017.
 */
@Repository
public class TweetDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String selfTweets = "SELECT * FROM tweet WHERE person_id = :person_id";

    private static final String idolTweets = "SELECT * FROM followers f INNER JOIN tweet t ON f.follower_person_id = t.person_id WHERE f.follower_person_id = :person_id";

    @Autowired
    public TweetDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Tweet> fetchSelfTweets(int personId, String filter) {
        String sql = selfTweets;
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("person_id", personId);
        if (!filter.isEmpty()) {
            sql += " AND content LIKE :filter";
            namedParameters.addValue("filter", "%" + filter + "%");
        }

        return namedParameterJdbcTemplate.query(sql, namedParameters, new TweetMapper());
    }

    public List<Tweet> fetchIdolTweets(int personId, String filter) {
        String sql = idolTweets;
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("person_id", personId);
        if (!filter.isEmpty()) {
            sql += " AND t.content LIKE :filter";
            namedParameters.addValue("filter", "%" + filter + "%");
        }

        return namedParameterJdbcTemplate.query(sql, namedParameters, new TweetMapper());
    }
}
