package com.dpc.splitcamel;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

@Component("discardAggregationStrategy")
public class DiscardAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        newExchange.getIn().setBody(null);
        return null;
    }
}
