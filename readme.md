I ran around the user login by simply using basic auth for every request and having default user = user and default password = password.
Each request, I simply pass in the person ID instead.

### Endpoints:

* GET / => gets homepage (not completed, see note 1 below)
* GET /tweets => gets tweets by user and by people user follows
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

```SQL
SELECT TOP 5 person_id, count(*)
FROM (
    SELECT * FROM followers WHERE person_id IN (SELECT follower_person_id FROM followers WHERE person_id = 1)
)
GROUP BY person_id
ORDER BY count(*) DESC;
```

All users (tentative, need to test at home):

```SQL
SELECT * FROM person INNER JOIN (
    SELECT orig.person_id as original_id, orig.follower_person_id
    FROM followers orig INNER JOIN followers followersOfFollowers
    ON orig.follower_person_id = followersOfFollowers.person_id
    GROUP BY orig.follower_person_id
    ORDER BY count(*) DESC
) as popular_followers ON person.id = popular_followers.original_id;
```

**note 2**: I am aware I only scaffolded the unit tests and they are still bare. I am a little short on time so I opted to show you what could have been with a skeleton :p
see more on testing: https://spring.io/guides/gs/testing-web/
