package com.thousandeyes.entities.follower;

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
public class FollowerDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String getFollowersQuery = "SELECT * FROM followers f WHERE f.person_id = :person_id";
    private static final String getFollowingQuery = "SELECT * FROM followers f WHERE f.follower_person_id = :person_id";

    private static final String followQuery = "INSERT INTO followers (person_id, follower_person_id) VALUES (:to_be_followed, :user_id)";
    private static final String unfollowQuery = "DELETE FROM followers WHERE follower_person_id = :user_id AND person_id = :followed_person_id";

    @Autowired
    public FollowerDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Follower> getFollowers(int personId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("person_id", personId);

        return namedParameterJdbcTemplate.query(getFollowersQuery, namedParameters, new FollowerMapper());
    }

    public List<Follower> getFollowing(int personId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("person_id", personId);

        return namedParameterJdbcTemplate.query(getFollowingQuery, namedParameters, new FollowerMapper());
    }

    public boolean follow(int userId, int personToFollowId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("to_be_followed", personToFollowId)
                .addValue("user_id", userId);

        return namedParameterJdbcTemplate.update(followQuery, namedParameters) > 0;
    }

    public boolean unfollow(int userId, int followedPersonId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("user_id", userId)
                .addValue("followed_person_id", followedPersonId);

        return namedParameterJdbcTemplate.update(unfollowQuery, namedParameters) > 0;
    }
}
