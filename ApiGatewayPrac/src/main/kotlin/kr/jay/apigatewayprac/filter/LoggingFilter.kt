package kr.jay.apigatewayprac.filter

import kr.jay.apigatewayprac.common.Log
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.Ordered
import reactor.core.publisher.Mono

/**
 * LoggingFilter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 9/7/24
 */
class LoggingFilter : AbstractGatewayFilterFactory<LoggingFilter.Config>(Config::class.java) {
    companion object : Log

    data class Config(
        val baseMassage: String,
        val preLogger: Boolean,
        val postLogger: Boolean,
    )

    override fun apply(config: Config): GatewayFilter {
        return OrderedGatewayFilter({ exchange, chain ->
            val request = exchange.request
            val response = exchange.response
            log.info("Logging Filter baseMessage: {}", config.baseMassage)

            chain.filter(exchange).then(Mono.fromRunnable {
                if (config.postLogger) {
                    log.info("Logging Filter Response status: {}", response.statusCode)
                }
            })
        }, Ordered.HIGHEST_PRECEDENCE)
    }
}