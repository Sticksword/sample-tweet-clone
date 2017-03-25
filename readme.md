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
* GET /listPairings => get a list of all users paired with their most popular follower (dupes allowed)

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

OK had to completely rework, could not "just adapt". (I am very shocked at how lengthy this query is... it's been fun though re-learning in depth SQL):

```SQL
SELECT * FROM person inner join

(
SELECT t1.person_id, t1.follower_person_id, t1.num_followers FROM

(SELECT f.person_id, f.follower_person_id, num_followers FROM followers f
INNER JOIN
(
SELECT person_id, count(*) as num_followers FROM followers
GROUP BY person_id
) as T
ON f.follower_person_id = T.person_id) t1

INNER JOIN

(SELECT person_id, max(num_followers) as num_followers FROM
(
SELECT f.person_id, f.follower_person_id, num_followers FROM followers f
INNER JOIN
(
SELECT person_id, count(*) as num_followers FROM followers
GROUP BY person_id
) as T
ON f.follower_person_id = T.person_id
)
GROUP BY person_id) t2

ON t1.person_id = t2.person_id AND t1.num_followers = t2.num_followers
) t3

ON person.id = t3.person_id;
```

**note 2**: I purposely chose to return the ties from the most popular followers because I wasn't sure what to do to pick one of the two.
There will be duplicate entries for certain individuals such as Judith Woodard having 2 most popular followers each with 6 followers, person with id=1 and person with id=4.

**note 3**: I am aware I only scaffolded the unit tests and they are still bare. I am a little short on time so I opted to show you what could have been with a skeleton :p

see more on testing: https://spring.io/guides/gs/testing-web/

see more on greatest-n-per-group: http://stackoverflow.com/questions/7745609/sql-select-only-rows-with-max-value-on-a-column

see more on joining 2 selects: http://stackoverflow.com/questions/10538539/join-two-select-statement-results