package eu.ace_design.yourcast.sources

import eu.ace_design.yourcast.model._

package twitter {


  case class Tweet(user: User, message: String, date: java.util.Date) extends ScalarInformation
  case class User(id: Long, screenName: String, profileImageUrl: String)


  class Search extends Source {

    // Unhandled as it should be policies: result_type and rpp
    val needed = Set("authorization", "query")

    protected def call(ctx: CallContext) = ctx get "query" match {
      case "anonymous" => CompositeInformation(TwitterMockDatabase.searchResults:_*)
      case _ => CompositeInformation()
    }

  }

  class TimeLine extends Source {
    // Unhandled as it should be policies: count
    val needed = Set("authorization", "screen_name")

    protected def call(ctx: CallContext) = ctx get "screen_name" match {
      case "myUserName" => CompositeInformation(TwitterMockDatabase.myTweets:_*)
      case _ => CompositeInformation()
    }
  }


  class SearchImage extends Source {
    // Unhandled as it should be policies: result_type and rpp
    val needed = Set("authorization", "key")

    protected def call(ctx: CallContext) = ???
  }

  class UserImage extends Source {

    // Unhandled as it should be policies: count
    val needed = Set("authorization", "screen_name")

    protected def call(ctx: CallContext) = ???
  }

  /**
   *  A Mock database to fake connection to real Twitter Service
   */
  object TwitterMockDatabase {

    val me = User(id=123456789, screenName = "My User Name", profileImageUrl = "http://.../myUserName.jpg")
    val myTweets = Seq(
      Tweet(user = me, message = "This is my first tweet",  date =  "1970-01-01 00:01"),
      Tweet(user = me, message = "This is my second tweet", date =  "1971-01-01 00:01"),
      Tweet(user = me, message = "This is my third tweet",  date =  "1972-01-01 00:01"),
      Tweet(user = me, message = "This is my fourth tweet", date =  "1973-01-01 00:01"),
      Tweet(user = me, message = "and so on",               date =  "1974-01-01 00:01")
    )

    val searchResults = Seq(
      Tweet(user = User(id = 123, screenName = "Anonymous #1", profileImageUrl = "http://.../anonymous1.jpg"),
            message = "an #anonymous message from anonymous guy", date = "2012-08-01 12:40"),
      Tweet(user = User(id = 456, screenName = "Anonymous #2", profileImageUrl = "http://.../anonymous2.jpg"),
        message = "I'm nobody #anonymous", date = "2012-08-01 12:45"),
      Tweet(user = User(id = 789, screenName = "Anonymous #3", profileImageUrl = "http://.../anonymous3.jpg"),
        message = "BE #ANONYMOUS!", date = "2012-08-01 12:50")
    )

    import java.util.Date
    import java.text.SimpleDateFormat
    implicit private def toDate(str: String): Date = new SimpleDateFormat("yyy-MM-dd HH:mm").parse(str)
  }


}
