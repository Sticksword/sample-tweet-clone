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

    private static final String getListOfUserPairings = "SELECT * \n" +
            "FROM   person \n" +
            "       INNER JOIN (SELECT t1.person_id, \n" +
            "                          t1.follower_person_id, \n" +
            "                          t1.num_followers \n" +
            "                   FROM   (SELECT f.person_id, \n" +
            "                                  f.follower_person_id, \n" +
            "                                  num_followers \n" +
            "                           FROM   followers f \n" +
            "                                  INNER JOIN (SELECT person_id, \n" +
            "                                                     Count(*) AS num_followers \n" +
            "                                              FROM   followers \n" +
            "                                              GROUP  BY person_id) AS T \n" +
            "                                          ON f.follower_person_id = T.person_id) \n" +
            "                          t1 \n" +
            "                          INNER JOIN (SELECT person_id, \n" +
            "                                             Max(num_followers) AS num_followers \n" +
            "                                      FROM   (SELECT f.person_id, \n" +
            "                                                     f.follower_person_id, \n" +
            "                                                     num_followers \n" +
            "                                              FROM   followers f \n" +
            "                                     INNER JOIN (SELECT \n" +
            "                                     person_id, \n" +
            "                                     Count(*) AS num_followers \n" +
            "                                                 FROM \n" +
            "                                     followers \n" +
            "                                     GROUP  BY person_id) \n" +
            "                                                AS T \n" +
            "                                             ON f.follower_person_id = \n" +
            "                                                T.person_id \n" +
            "                                             ) \n" +
            "                                      GROUP  BY person_id) t2 \n" +
            "                                  ON t1.person_id = t2.person_id \n" +
            "                                     AND t1.num_followers = t2.num_followers) t3 \n" +
            "               ON person.id = t3.person_id; "; // should clean this but then again, should really not try to query in only one query...

    @Autowired
    public PersonDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Person> getUsersWithMostPopularFollower() {
        return namedParameterJdbcTemplate.query(getListOfUserPairings, new PersonMapper());
    }

    /* actual sql in less annoying, more legible formatting if you guys are interested

SELECT *
FROM   person
       INNER JOIN (SELECT t1.person_id,
                          t1.follower_person_id,
                          t1.num_followers
                   FROM   (SELECT f.person_id,
                                  f.follower_person_id,
                                  num_followers
                           FROM   followers f
                                  INNER JOIN (SELECT person_id,
                                                     Count(*) AS num_followers
                                              FROM   followers
                                              GROUP  BY person_id) AS T
                                          ON f.follower_person_id = T.person_id)
                          t1
                          INNER JOIN (SELECT person_id,
                                             Max(num_followers) AS num_followers
                                      FROM   (SELECT f.person_id,
                                                     f.follower_person_id,
                                                     num_followers
                                              FROM   followers f
                                     INNER JOIN (SELECT
                                     person_id,
                                     Count(*) AS num_followers
                                                 FROM
                                     followers
                                     GROUP  BY person_id)
                                                AS T
                                             ON f.follower_person_id =
                                                T.person_id
                                             )
                                      GROUP  BY person_id) t2
                                  ON t1.person_id = t2.person_id
                                     AND t1.num_followers = t2.num_followers) t3
               ON person.id = t3.person_id;

see also: http://www.dpriver.com/pp/sqlformat.htm
     */
}
