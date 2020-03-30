package com.flinkjobs;


import com.flinkjobs.twitterutils.MapToTweet;
import com.flinkjobs.twitterutils.Tweet;
import com.flinkjobs.twitterutils.TwitterPropertiesHandler;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.twitter.TwitterSource;
import org.apache.flink.util.Collector;

import java.util.Date;

public class NumberOfTweetsPerLanguage {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        var props = TwitterPropertiesHandler.properties();

        env.setStreamTimeCharacteristic(TimeCharacteristic.IngestionTime);

        env.addSource(new TwitterSource(props))
                .map(new MapToTweet())
                .keyBy((KeySelector<Tweet, String>) Tweet::language)
                .timeWindow(Time.minutes(10))
                .apply((WindowFunction<Tweet, Tuple3<String, Long, Date>, String, TimeWindow>) (l, window, iterable, collector) -> {
                    long count = 0;
                    for (Tweet t : iterable) {
                        ++count;
                    }
                    collector.collect(new Tuple3<>(l, count, new Date(window.getEnd())));
                })
                .print();

        env.execute();

    }
}
