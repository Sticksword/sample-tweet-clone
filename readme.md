I ran around the user login by simply using basic auth for every request and having default user = user and default password = password.
Each request, I simply pass in the person ID instead.

### Endpoints:

* GET / => gets homepage
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

*note* I am aware I only scaffolded the unit tests and they are still bare. I am a little short on time so I opted to show you what could have been with a skeleton :p