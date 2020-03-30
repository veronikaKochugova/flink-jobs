package com.flinkjobs;


import com.flinkjobs.twitterutils.TwitterPropertiesHandler;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.twitter.TwitterSource;

public class NumberOfTweetsPerLanguage {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        var props = TwitterPropertiesHandler.properties();

        env.addSource(new TwitterSource(props)).print();

        env.execute();

    }
}
