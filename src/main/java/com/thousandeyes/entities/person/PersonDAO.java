package com.thousandeyes.entities.person;

import com.thousandeyes.entities.follower.FollowerMapper;
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
public class PersonDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String sql = "SELECT * FROM person inner join \n" +
            "\n" +
            "(\n" +
            "SELECT t1.person_id, t1.follower_person_id, t1.num_followers FROM\n" +
            "\n" +
            "(SELECT f.person_id, f.follower_person_id, num_followers FROM followers f\n" +
            "INNER JOIN\n" +
            "(\n" +
            "SELECT person_id, count(*) as num_followers FROM followers\n" +
            "GROUP BY person_id\n" +
            ") as T\n" +
            "ON f.follower_person_id = T.person_id) t1\n" +
            "\n" +
            "INNER JOIN\n" +
            "\n" +
            "(SELECT person_id, max(num_followers) as num_followers FROM\n" +
            "(\n" +
            "SELECT f.person_id, f.follower_person_id, num_followers FROM followers f\n" +
            "INNER JOIN\n" +
            "(\n" +
            "SELECT person_id, count(*) as num_followers FROM followers\n" +
            "GROUP BY person_id\n" +
            ") as T\n" +
            "ON f.follower_person_id = T.person_id\n" +
            ")\n" +
            "GROUP BY person_id) t2\n" +
            "\n" +
            "ON t1.person_id = t2.person_id AND t1.num_followers = t2.num_followers\n" +
            ") t3\n" +
            "\n" +
            "ON person.id = t3.person_id;"; // should clean this but then again, should really not try to query in only one query...

    @Autowired
    public PersonDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Person> getUsersWithMostPopularFollower() {
        return namedParameterJdbcTemplate.query(sql, new PersonMapper());
    }
}
