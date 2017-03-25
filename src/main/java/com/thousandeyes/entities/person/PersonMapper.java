package com.thousandeyes.entities.person;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Michael on 3/21/2017.
 */
public class PersonMapper implements RowMapper {
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setPersonName(rs.getString("name"));
        person.setPersonId(rs.getInt("id"));

        if (isThere(rs, "follower_person_id")) {
            person.setMostPopularFollowerId(rs.getInt("follower_person_id"));
        }
        if (isThere(rs, "num_followers")) {
            person.setMostPopularFollowerId(rs.getInt("num_followers"));
        }

        return person;
    }

    private boolean isThere(ResultSet rs, String column)
    {
        try
        {
            rs.findColumn(column);
            return true;
        } catch (SQLException e)
        {
            System.out.println("Column doesn't exist");
        }
        return false;
    }
}