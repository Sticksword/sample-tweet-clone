package com.thousandeyes.entities.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * Created by Michael on 3/21/2017.
 */
@Repository
public class PersonDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String sql = "SELECT TOP 1 follower_person_id where person";

    @Autowired
    public PersonDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void filler() {

    }
}
