package dev.tiltrikt.tetravex.producer;

import dev.tiltrikt.tetravex.dto.ScoreAddRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static dev.tiltrikt.tetravex.configuration.ScoreKafkaTopicConfiguration.SCORE_TOPIC;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ScoreProducer {

  @NotNull KafkaTemplate<String, ScoreAddRequest> kafkaTemplate;

  public void addScore(@NotNull ScoreAddRequest scoreAddRequest, @NotNull String player) {

    Map<String, Object> headersMap = new HashMap<>();
    headersMap.put("Player", player);
    headersMap.put(KafkaHeaders.TOPIC, SCORE_TOPIC);

    MessageHeaders messageHeaders = new MessageHeaders(headersMap);
    Message<ScoreAddRequest> message = new GenericMessage<>(scoreAddRequest, messageHeaders);
    kafkaTemplate.send(message);
    log.info("Sent {}", message);
  }
}
