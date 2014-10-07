package com.criteo.kafka;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yammer.metrics.core.MetricName;
import com.yammer.metrics.core.MetricPredicate;

public class RegexMetricExclusionPredicateTest {

    @Test
    public void testMatches() {
        MetricPredicate predicate = new RegexMetricExclusionPredicate(".*kafka\\.(.*cluster.*|.*log\\.Log\\..*(-LogEndOffset|NumLogSegments).*)");
        assertTrue(predicate.matches(new MetricName(MetricPredicate.class, "a.random.string"), null));
        assertFalse(predicate.matches(new MetricName(MetricPredicate.class, "kafka.kafka.log.Log.android_events-0-LogEndOffset"), null));
        assertFalse(predicate.matches(new MetricName(MetricPredicate.class, "kafka.kafka.cluster"), null));
    }
}
