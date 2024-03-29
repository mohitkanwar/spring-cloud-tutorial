package com.mohitkanwar.tutorial.springcloud;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.time.Duration;


@Configuration
public class RouteConfigurations {

    @Bean
    public RouteLocator resilientRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder
                .routes()
                .route(
                        route-> route
                                .path("*/resilient/*")
                                .filters(f ->
                                        {
                                            f.circuitBreaker(
                                                    config ->
                                                            config.setName("resilientCircuitBreaker")
                                                                    .setFallbackUri("forward:/fallback/catchall")
                                            );
                                            f.requestRateLimiter().configure(
                                                    c -> c.setRateLimiter(getRateLimiter())
                                            );
                                            return f;
                                        }

                                ).uri("lb://resilient-gateway")
                )

                .build();
    }

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .timeLimiterConfig(TimeLimiterConfig.custom()
                        .timeoutDuration(Duration.ofSeconds(2))
                        .build()).build());
    }

    @Bean
    public RedisRateLimiter getRateLimiter() {
        return new RedisRateLimiter(1, 2);
    }

    @Bean
    public KeyResolver getKeyResolver() {
        return exchange -> Mono.just("defaultKeyForAllServices");
    }
}
