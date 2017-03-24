I ran around the user login by simply using basic auth for every request and having default user = user and default password = password.
Each request, I simply pass in the person ID instead.

### Endpoints:

* GET / => gets homepage (not completed, see note 1 below)
* GET /tweets => gets tweets by user and by people user follows
  * @param String userId
  * @param String userId
* GET /followers => gets followers for a user
  * @param String userId
* GET /following => gets people a user follows
  * @param String userId
* POST /follow => for when a user follows someone
  * @param String userId
  * @param String personToFollowId
* POST /unfollow => for when a user unfollows someone
  * @param String userId
  * @param String followedPersonId

**note 1**: I decided to do bonus 2 instead of bonus 3
SQL is as follows to generate top 5 list of most popular followers for user 1. From here we can adapt for any/all users:

select top 5 person_id, count(*)
from (SELECT * FROM followers WHERE person_id IN (SELECT follower_person_id FROM followers WHERE person_id = 1))
GROUP BY person_id
ORDER BY count(*) DESC;

All users:

SELECT * FROM person INNER JOIN (
    select top 5 person_id, count(*)
    from (SELECT * FROM followers WHERE person_id IN (SELECT follower_person_id FROM followers WHERE person_id = 1))
    GROUP BY person_id
    ORDER BY count(*) DESC;
) table_2 ON person.id = table_2.or

**note 2**: I am aware I only scaffolded the unit tests and they are still bare. I am a little short on time so I opted to show you what could have been with a skeleton :p
see more on testing: https://spring.io/guides/gs/testing-web/
