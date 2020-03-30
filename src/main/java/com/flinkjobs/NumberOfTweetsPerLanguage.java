package com.flinkjobs;


import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.twitter.TwitterSource;
import twitter4j.Twitter;

import java.util.Properties;

public class NumberOfTweetsPerLanguage {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        var props = TwitterPropertiesHandler.properties();

        env.addSource(new TwitterSource(props)).print();

        env.execute();

    }
}
