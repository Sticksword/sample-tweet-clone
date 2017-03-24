package com.thousandeyes.entities.follower;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Michael on 3/21/2017.
 */
public class FollowerMapper implements RowMapper {
    public Follower mapRow(ResultSet rs, int rowNum) throws SQLException {
        Follower follower = new Follower();
        follower.setMappingId(rs.getInt("id"));
        follower.setFollowerId(rs.getInt("follower_person_id"));
        follower.setPersonId(rs.getInt("person_id"));
        return follower;
    }
}