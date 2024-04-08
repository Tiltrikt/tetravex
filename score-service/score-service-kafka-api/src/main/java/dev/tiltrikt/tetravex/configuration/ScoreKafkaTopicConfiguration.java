package dev.tiltrikt.tetravex.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class ScoreKafkaTopicConfiguration {

  public static final String SCORE_TOPIC = "score-topic";
  public static final String SCORE_TOPIC_DLT = "score-topic-dlt";

  @Bean
  public NewTopic scoreTopic() {
    return TopicBuilder.name(SCORE_TOPIC)
        .replicas(2)
        .partitions(1)
        .build();
  }

  @Bean
  public NewTopic scoreTopicDLT() {
    return TopicBuilder.name(SCORE_TOPIC_DLT)
        .replicas(2)
        .partitions(1)
        .build();
  }
}