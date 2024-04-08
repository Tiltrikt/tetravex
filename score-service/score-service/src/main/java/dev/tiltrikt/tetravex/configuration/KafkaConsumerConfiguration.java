package dev.tiltrikt.tetravex.configuration;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KafkaConsumerConfiguration {

  @Bean
  public KafkaListenerConfigurer kafkaListenerConfigurer(@NotNull LocalValidatorFactoryBean validatorFactory) {
    return r -> r.setValidator(validatorFactory);
  }
}