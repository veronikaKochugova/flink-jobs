package com.flinkjobs;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.twitter.TwitterSource;

import java.util.Properties;

public class FilteringEnglishTweets {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        var props = TwitterPropertiesHandler.properties();

        env.addSource(new TwitterSource(props))
                .map(new MapToTweet())
                .filter((FilterFunction<Tweet>) tweet -> tweet.language().equals("en"))
                .print();

        env.execute();

    }
}


