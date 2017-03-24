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
        return person;
    }
}