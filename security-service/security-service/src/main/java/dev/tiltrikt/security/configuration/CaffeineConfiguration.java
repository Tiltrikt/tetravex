package dev.tiltrikt.security.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.gson.GsonBuilder;
import io.jsonwebtoken.gson.io.GsonSupplierSerializer;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CaffeineConfiguration {

  @Bean
  Caffeine<Object, Object> caffeineConfig() {
    return Caffeine.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES);
  }

  @Bean
  CacheManager cacheManager(@NotNull Caffeine<Object, Object> caffeine) {
    CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
    caffeineCacheManager.setCaffeine(caffeine);
    return caffeineCacheManager;
  }

}