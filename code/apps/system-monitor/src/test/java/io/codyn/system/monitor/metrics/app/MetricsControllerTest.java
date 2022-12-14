package io.codyn.system.monitor.metrics.app;

import io.codyn.system.monitor.IntegrationTest;
import io.codyn.system.monitor._shared.Metrics;
import io.codyn.system.monitor.test.TestMetric;
import io.codyn.system.monitor.test.TestMetrics;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AutoConfigureObservability
@Import(MetricsControllerTest.TestConfig.class)
public class MetricsControllerTest extends IntegrationTest {

    private static final Clock FIXED_CLOCK = Clock.fixed(Instant.parse("2022-12-24T22:11:22Z"), ZoneId.of("UTC"));

    @Test
    void shouldAddMetricsToPrometheus() {
        var testCase = prepareAddMetricsTestCase();

        testHttpClient.test()
                .path("/metrics")
                .POST()
                .body(testCase.metricsToSend)
                .execute();

        var txtMetrics = testHttpClient.test()
                .path("/actuator/prometheus")
                .GET()
                .execute();

        var actualMetrics = TestMetrics.parseMetrics(txtMetrics);
        Assertions.assertThat(actualMetrics)
                .containsAll(testCase.expectedMetrics);
    }

    private AddMetricsTestCase prepareAddMetricsTestCase() {
        var containersMetrics = List.of(
                new ContainerMetrics("nginx", "nginx-default",
                        LocalDateTime.parse("2022-10-10T10:00:00"), System.currentTimeMillis(),
                        100_000, 100_000_000, 0.2),
                new ContainerMetrics("java-app", "java-app-II",
                        LocalDateTime.parse("2022-12-10T13:00:00"), System.currentTimeMillis() - 1000,
                        250_000_000, 100_000_000_000L, 0.1),
                new ContainerMetrics("postgres", "postgres-1",
                        LocalDateTime.parse("2023-01-01T10:00:00"), FIXED_CLOCK.millis(),
                        99_000_000, 500_000_000, 0.01));

        var source = "some-machine";

        var expectedMetrics = new ArrayList<TestMetric>();

        expectedMetrics.add(TestMetrics.metric("monitoring_collector_up_timestamp_seconds", Map.of("source", source),
                toSecondsTimestampString(FIXED_CLOCK.instant().toEpochMilli())));

        containersMetrics.forEach(c -> expectedMetrics.addAll(toExpectedContainerMetrics(source, c)));

        return new AddMetricsTestCase(new ContainersMetrics(source, containersMetrics), expectedMetrics);
    }

    private String toSecondsTimestampString(LocalDateTime dateTime) {
        return toSecondsTimestampString(dateTime.toInstant(ZoneOffset.UTC).toEpochMilli());
    }

    private String toSecondsTimestampString(long timestamp) {
        return String.valueOf(timestamp / 1000.0);
    }

    private List<TestMetric> toExpectedContainerMetrics(String source, ContainerMetrics metrics) {
        var containerLabels = Map.of(Metrics.SOURCE_LABEL, source,
                Metrics.APPLICATION_LABEL, metrics.containerName(),
                Metrics.INSTANCE_ID_LABEL, metrics.instanceId());

        var expectedMetrics = new ArrayList<TestMetric>();

        expectedMetrics.add(TestMetrics.metric("monitoring_application_started_at_timestamp_seconds",
                containerLabels, toSecondsTimestampString(metrics.startedAt())));

        expectedMetrics.add(TestMetrics.metric("monitoring_application_up_timestamp_seconds",
                containerLabels, toSecondsTimestampString(metrics.timestamp())));

        expectedMetrics.add(TestMetrics.metric("monitoring_application_used_memory_bytes",
                containerLabels, String.valueOf((double) metrics.usedMemory())));

        expectedMetrics.add(TestMetrics.metric("monitoring_application_max_memory_bytes",
                containerLabels, String.valueOf((double) metrics.maxMemory())));

        expectedMetrics.add(TestMetrics.metric("monitoring_application_cpu_usage_percent",
                containerLabels, String.valueOf(metrics.cpuUsage())));

        return expectedMetrics;
    }


    private record AddMetricsTestCase(ContainersMetrics metricsToSend,
                                      List<TestMetric> expectedMetrics) {
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        @Primary
        Clock fixedClock() {
            return FIXED_CLOCK;
        }
    }
}
