package com.flinkjobs;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

public class MapToTweet implements MapFunction<String, Tweet> {

    static private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Tweet map(final String tweetJsonStr) throws Exception {
        var tweetJson = mapper.readTree(tweetJsonStr);
        var textNode = tweetJson.get("text");
        var langNode = tweetJson.get("lang");

        var text = textNode == null ? "" : textNode.textValue();
        var lang = langNode == null ? "" : langNode.textValue();
        return null;
    }
}
