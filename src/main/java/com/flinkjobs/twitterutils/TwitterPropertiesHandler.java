package com.flinkjobs.twitterutils;

import org.apache.flink.streaming.connectors.twitter.TwitterSource;

import java.util.Properties;

final public class TwitterPropertiesHandler {

    private static Properties props = new Properties();

    static {
        props.setProperty(TwitterSource.CONSUMER_KEY, "");
        props.setProperty(TwitterSource.CONSUMER_SECRET, "");
        props.setProperty(TwitterSource.TOKEN, "");
        props.setProperty(TwitterSource.TOKEN_SECRET, "");
    }

    public static Properties properties() {
        return props;
    }
}
