package com.flinkjobs;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.twitter.TwitterSource;

import java.util.Properties;

public class FilteringEnglishTweets {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        var props = new Properties();
        props.setProperty(TwitterSource.CONSUMER_KEY, "");
        props.setProperty(TwitterSource.CONSUMER_SECRET, "");
        props.setProperty(TwitterSource.TOKEN, "");
        props.setProperty(TwitterSource.TOKEN_SECRET, "");

        env.addSource(new TwitterSource(props))
                .map(new MapToTweet())
                .filter((FilterFunction<Tweet>) tweet -> tweet.language().equals("en"))
                .print();

        env.execute();

    }
}


