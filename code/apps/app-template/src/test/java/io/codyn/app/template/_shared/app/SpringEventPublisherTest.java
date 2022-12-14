package io.codyn.app.template._shared.app;

import io.codyn.app.template.SpringIntegrationTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.EventListener;

@Import(SpringEventPublisherTest.TestConfig.class)
public class SpringEventPublisherTest extends SpringIntegrationTest {

    @Autowired
    private SpringEventPublisher publisher;
    @Autowired
    private CustomEventListener firstEventListener;
    @Autowired
    private CustomEventListener secondEventListener;

    @Test
    void shouldPublishEventToSpringContext() {
        Assertions.assertThat(firstEventListener.capturedEvent)
                .isNull();
        Assertions.assertThat(secondEventListener.capturedEvent)
                .isNull();

        var event = new CustomEvent("some-id", "some-data");

        publisher.publish(event);

        Assertions.assertThat(firstEventListener.capturedEvent)
                .isEqualTo(event);
        Assertions.assertThat(secondEventListener.capturedEvent)
                .isEqualTo(event);
    }


    record CustomEvent(String id, String data) {
    }

    static class CustomEventListener {

        private CustomEvent capturedEvent;

        @EventListener
        public void onEvent(CustomEvent event) {
            capturedEvent = event;
        }
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        public CustomEventListener firstEventListener() {
            return new CustomEventListener();
        }

        @Bean
        public CustomEventListener secondEventListener() {
            return new CustomEventListener();
        }
    }
}
