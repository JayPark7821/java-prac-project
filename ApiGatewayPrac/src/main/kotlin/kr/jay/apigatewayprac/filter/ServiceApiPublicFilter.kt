package kr.jay.apigatewayprac.filter

import kr.jay.apigatewayprac.common.Log
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component

/**
 * ServiceApiFilter
 *
 * @author jaypark
 * @version 1.0.0
 * @since 9/6/24
 */
@Component
class ServiceApiPublicFilter : AbstractGatewayFilterFactory<ServiceApiPublicFilter.Config>(Config::class.java) {
    companion object : Log
    class Config

    override fun apply(config: ServiceApiPublicFilter.Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val uri = exchange.request.uri
            log.info("service api public filter route uri: {}", uri)
            val mono = chain.filter(exchange)
            mono
        }
    }

}