package com.bitirmeodev.halisahambe;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@EnableJpaAuditing
@EnableCaching
@SpringBootApplication
public class HalisahamBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HalisahamBeApplication.class, args);
    }

    @Bean
    public Config hazelCastConfig() {
        Config config = new Config();
        config.setInstanceName("hazelcast-instance")
                .addMapConfig(
                        new MapConfig("cities")
                                .setEvictionConfig(new EvictionConfig()
                                        .setEvictionPolicy(EvictionPolicy.LRU)
                                        .setSize(200)
                                        .setMaxSizePolicy(MaxSizePolicy.USED_HEAP_PERCENTAGE))
                                .setTimeToLiveSeconds(120))
                .addMapConfig(
                        new MapConfig("users")
                                .setEvictionConfig(new EvictionConfig()
                                        .setEvictionPolicy(EvictionPolicy.LRU)
                                        .setSize(100)
                                        .setMaxSizePolicy(MaxSizePolicy.USED_HEAP_PERCENTAGE))
                                .setTimeToLiveSeconds(120))
                .addMapConfig(
                        new MapConfig("events")
                                .setEvictionConfig(new EvictionConfig()
                                        .setEvictionPolicy(EvictionPolicy.LRU)
                                        .setSize(200)
                                        .setMaxSizePolicy(MaxSizePolicy.USED_HEAP_PERCENTAGE))
                                .setTimeToLiveSeconds(120));
        return config;
    }

    @Bean
    public HazelcastInstance hazelcastInstance(Config hazelCastConfig) {
        return Hazelcast.newHazelcastInstance(hazelCastConfig);
    }
}
