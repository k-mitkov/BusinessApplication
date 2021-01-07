package javaproject.BusinessApplication.util;

import javaproject.BusinessApplication.exeptions.CustomTwitterException;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TweetSenderImpl implements TweetSender{

    private static final String EXCEPTION_MESSAGE=
            "Something went wrong! Please check your internet connection and try again.";

    private static String consumerKeyStr = "?????";
    private static String consumerSecretStr = "??????";
    private static String accessTokenStr = "???????";
    private static String accessTokenSecretStr = "????????";


    @Override
    public void tweet(String message) {
        try {
            Twitter twitter = new TwitterFactory().getInstance();

            twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);
            AccessToken accessToken = new AccessToken(accessTokenStr,
                    accessTokenSecretStr);
            twitter.setOAuthAccessToken(accessToken);
            twitter.updateStatus(message);

        } catch (TwitterException te) {
            throw new CustomTwitterException(EXCEPTION_MESSAGE);
        }

    }
}
