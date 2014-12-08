Todo App
===============

This is a pre-work app for Android Bootcamp preparation.

Time spent: 2 hours spent in coding

Completed user stories:

 * [x] Required: User can add item
 * [x] Required: User can remove item by long click
 * [x] Required: App can save item into file

Walkthrough of all user stories:

<img src="TodoApp.gif" height="350" />

Tip Calculator App
===============

This is Week-1 homework app for Android Bootcamp.

Time spent: 5 hours spent in total (2 hours for required; 3 hours for optional stories)

Completed user stories:

 * [x] User is displayed the tip of specified percentage for specified entered amount
 * [x] User enters the total amount of the transaction
 * [x] User can select between tip amounts (i.e 10%, 15%, 20%)
 * [x] Upon selecting tip amount, formatted tip value is displayed
 * [x] Optional: User changes the total amount and updated tip is reflected automatically
 * [x] Optional: User can select custom tip percentage if desired
 * [x] Optional: User can select how many ways to split the tip
 * [x] Optional: User can edit preset tip percentages and have them persist across launches
 * [x] Optional: Experiment with trying input widgets to replace the buttons and/or textviews
 * [x] Optional: Improve the user interface and experience by using images and/or colors

Walkthrough of all user stories:

<img src="TipCalculator.gif" height="350" /> <img src="TipCalculator2.gif" height="350" />

Instagram Client App
===============

This is Week-2 homework app for Android Bootcamp.

Time spent: 5 hours spent in total

Completed user stories:

 * [x] User can scroll through current popular photos from Instagram
 * [x] For each photo displayed, user can see the following details:
  * Graphic, Caption, Username
  * (Optional) relative timestamp, like count, user profile image
 * [ ] Advanced: Add pull-to-refresh for popular stream with SwipeRefreshLayout
 * [ ] Advanced: Show latest comment for each photo (bonus: show last 2 comments)
 * [ ] Advanced: Display each photo with the same style and proportions as the real Instagram
 * [ ] Advanced: Display each user profile image using a CircularImageView
 * [ ] Advanced: Display a nice default placeholder graphic for each image during loading (read more about Picasso)
 * [ ] Advanced: Improve the user interface through styling and coloring
 * [ ] Bonus: Allow user to view all comments for an image within a separate screen or a dialog fragment

Walkthrough of all user stories:

<img src="InstagramClient.gif" height="350" />

Grid Image Search App
===============

Time spent: 6 hours spent in coding

Completed user stories:

* [x] User can enter a search query that will display a grid of image results from the Google Image API.
* [x] User can click on "settings" which allows selection of advanced search options to filter results
* [x] User can configure advanced search filters such as:
* [x] Size (small, medium, large, extra-large)
* [x] Color filter (black, blue, brown, gray, green, etc...)
* [x] Type (faces, photo, clip art, line art)
* [x] Site (espn.com)
* [x] Subsequent searches will have any filters applied to the search results
* [x] User can tap on any image in results to see the image full-screen
* [x] User can scroll down “infinitely” to continue loading more image results (up to 8 pages)
* [ ] Optional: Use the ActionBar SearchView or custom layout as the query box instead of an EditText
* [ ] Optional: User can share an image to their friends or email it to themselves
* [ ] Optional: Robust error handling, check if internet is available, handle error cases, network failures
* [ ] Optional: Improve the user interface and experiment with image assets and/or styling and coloring
* [ ] Stretch: Replace Filter Settings Activity with a lightweight modal overlay
* [ ] Stretch: User can zoom or pan images displayed in full-screen detail view
* [ ] Stretch: Use the StaggeredGridView to display visually interesting image results

Walkthrough of all user stories:

<img src="GridImageSearch.gif" height="350" />

Twitter Client App
===============

Time spent: 10 hours spent in coding

Completed user stories:

* [x] User can sign in using OAuth login flow
* [ ] User can view last 25 tweets from their home timeline
 * [x] User should be able to see the user, body and timestamp for tweet
 * [x] User should be displayed the relative timestamp for a tweet "8m", "7h"
 * [ ] Optional: Links in tweets are clickable and viewable
* [x] User can load more tweets once they reach the bottom of the list using "infinite scroll" pagination
* [x] User can compose a new tweet
 * [x] User can click a “Compose” icon in the Action Bar on the top right
 * [x] User will have a Compose view opened
 * [x] User can enter a message and hit a button to post to twitter
 * [x] User should be taken back to home timeline with new tweet visible
 * [x] Optional: User can see a counter with total number of characters left for tweet
* [x] Optional: User can refresh tweets timeline by pulling down to refresh (i.e pull-to-refresh)
* [ ] Optional: User can open the twitter app offline and see last loaded tweets
  * Tweets are persisted into sqlite and can be displayed from the local DB
* [ ] Optional: User can tap a tweet to display a "detailed" view of that tweet
* [ ] Optional: User can select "reply" from detail view to respond to a tweet
* [ ] Optional: Improve the user interface and theme the app to feel twitter branded
* [ ] Stretch: User can see an embedded media (image) within tweet detail view
* [ ] Stretch: Compose View activity is replaced with a modal overlay

Walkthrough of all user stories:
<img src="TwitterClient/TwitterClient.gif" height="350" />

===============
Time spent: 12 hours spent in coding

User Stories:
* [x] Includes all required user stories from Week 3 Twitter Client
* [x] User can switch between Timeline and Mention views using tabs.
 * [x] User can view their home timeline tweets.
 * [x] User can view the recent mentions of their username.
 * [x] User can scroll to bottom of either of these lists and new tweets will load ("infinite scroll")
 * [x] Optional: Implement this in a gingerbread-compatible approach
* [x] User can navigate to view their own profile
 * [x] User can see picture, tagline, # of followers, # of following, and tweets on their profile.
* [x] User can click on the profile image in any tweet to see another user's profile.
 * [x] User can see picture, tagline, # of followers, # of following, and tweets of clicked user.
 * [x] Profile view should include that user's timeline
 * [ ] Optional: User can view following / followers list through the profile
* [ ] Optional: When a network request goes out, user sees an indeterminate progress indicator
* [ ] Optional: User can "reply" to any tweet on their home timeline
 * [ ] The user that wrote the original tweet is automatically "@" replied in compose
* [ ] Optional: User can click on a tweet to be taken to a "detail view" of that tweet
 * [ ] Optional: User can take favorite (and unfavorite) or reweet actions on a tweet
* [ ] Optional: Improve the user interface and theme the app to feel twitter branded
* [ ] Optional: User can search for tweets matching a particular query and see results
* [ ] Stretch: User can view their direct messages (or send new ones)

<img src="TwitterClient/TwitterClient2.gif" height="350" />

GIF created with [LiceCap](http://www.cockos.com/licecap/).
