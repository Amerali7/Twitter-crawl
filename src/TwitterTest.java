import twitter4j.conf.ConfigurationBuilder;
import twitter4j.*;
import java.util.List;
public class TwitterTest
{
		
	public static void main(String[] args)
	{
		try
		{
			// set up the library and enter the keys
			ConfigurationBuilder cf = new ConfigurationBuilder();
			cf.setOAuthConsumerKey("PqDVln0WRo1FHgJMsX856nijI");
			cf.setOAuthConsumerSecret("NcDLYWCUtYzqBqbF9ixHkl74HLsBkrODMUlWamxyP8k7nUiMC8");
			cf.setOAuthAccessToken("960584451238563843-nqxRKqxPgL2g8i7oUeDLc2RJl448z5M");
			cf.setOAuthAccessTokenSecret("INBZkrD9dlz85gRFIaHnRnVVZ8NkoyPjER6na3G9Prspe");
			Twitter twitter = new TwitterFactory(cf.build()).getInstance();
			User user = twitter.verifyCredentials(); // Get main user
			
			//tester code
			// Print user Data
			System.out.println("----Profile Data----");
			System.out.println("USERNAME: @" + user.getScreenName());
			System.out.println("ID: " + user.getId());
			System.out.println("NAME: " + user.getName());
			System.out.println("URL: " + user.getProfileImageURL());
			System.out.println("FRIENDS: "+ user.getFriendsCount() + "friends.");
			System.out.println("-----------");
			//tester code
			
			// Print Timeline
			List<Status> statuses = twitter.getHomeTimeline();
			System.out.println("Showing @" + user.getScreenName() + "'s hometimeline.");
			for (Status status : statuses)
			{
				System.out.println("@" + status.getUser().getScreenName() + " - " +
				status.getText());
			}
			System.out.println("-----------");
			
			
			//followers data
			System.out.println("Showing Follower Information.");
			long followerCursor = -1;
			IDs followerIDs;
			do{
				followerIDs = twitter.getFollowersIDs( user.getId(),
				followerCursor);
				ResponseList<User> followers =
				twitter.lookupUsers(followerIDs.getIDs());
				for(User follower : followers)
				{
				System.out.println("FOLLOWER USERNAME: "+
				follower.getScreenName());
				System.out.println("FOLLOWER ID: " + follower.getId());
				System.out.println("FOLLOWER NAME: " + follower.getName());
				System.out.println("FOLLOWER FOLLOWERS COUNT: " +
				follower.getFollowersCount());
				System.out.println("FOLLOWER FRIENDS COUNT: " +
				follower.getFriendsCount());
				System.out.println("-----------");
				}
			}while((followerCursor = followerIDs.getNextCursor())!=0);
			
			
			// Get friends
			// System.out.println("Showing Friend Information.");
			
			
			System.out.println("Showing Friend Information.");
			long friendCursor = -1;
			IDs friendIDs;
			do{
				friendIDs = twitter.getFriendsIDs(user.getId(), friendCursor);
				ResponseList<User> friends =
				twitter.lookupUsers(friendIDs.getIDs());
				for(User friend : friends)
				{
				System.out.println("FRIEND SCREEN NAME: " +
				friend.getScreenName());
				System.out.println("FRIEND ID: " + friend.getId());
				System.out.println("FRIEND NAME: " + friend.getName());
				System.out.println("FRIEND FOLLOWER COUNT: " +
				friend.getFollowersCount());
				System.out.println("FRIEND FRIENDS COUNT: " +
				friend.getFriendsCount());
				System.out.println("-----------");
				}
			}
			
			while((friendCursor = friendIDs.getNextCursor())!=0);
		}
		catch (TwitterException te)
		{
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
			System.exit(-1);
	}
}
}
