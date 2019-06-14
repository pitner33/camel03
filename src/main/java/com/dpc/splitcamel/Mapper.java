package com.dpc.splitcamel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component("Mapper")
public class Mapper implements Processor {
    @Override
    public void process(Exchange exchange) {
        String body = exchange.getIn().getBody(String.class);
        JSONObject input = new JSONObject(body);
        Map<String, String> output = processor(input);
        exchange.getIn().setBody(output);
    }

    private Map<String, String> processor(JSONObject input) {
        Map<String, String > result = new HashMap<>();

        JSONObject values = input.getJSONObject("values");
        if (!values.has("is_enable")) {
            return  null;
        }

        JSONArray array = values.getJSONArray("is_enable");
        for (int i = 0; i < array.length(); i++) {
            JSONObject attribute = (JSONObject) array.get(i);
            result.put("scope" + i, attribute.getString("scope"));
        }

        return result;
    }
}
