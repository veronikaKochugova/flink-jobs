package com.flinkjobs;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.twitter.TwitterSource;
import twitter4j.TweetEntity;

import java.util.Properties;

public class FilteringEnglishTweets {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties props = new Properties();
        props.setProperty(TwitterSource.CONSUMER_KEY, "");
        props.setProperty(TwitterSource.CONSUMER_SECRET, "");
        props.setProperty(TwitterSource.TOKEN, "");
        props.setProperty(TwitterSource.TOKEN_SECRET, "");

        env.addSource(new TwitterSource(props)).print();

        env.execute();

    }
}


