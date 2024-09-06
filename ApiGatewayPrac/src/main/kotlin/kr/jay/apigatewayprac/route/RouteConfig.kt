package kr.jay.apigatewayprac.route

import kr.jay.apigatewayprac.filter.ServiceApiPrivateFilter
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * RouteConfig
 *
 * @author jaypark
 * @version 1.0.0
 * @since 9/6/24
 */
@Configuration
class RouteConfig(
    private val serviceApiPrivateFilter: ServiceApiPrivateFilter,
) {

    @Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route { spec ->
                spec.order(-1)
                spec.path(
                    "/service-api/api/**"
                ).filters{ filterSpec ->
                    filterSpec.filter(serviceApiPrivateFilter.apply(ServiceApiPrivateFilter.Config()))
                    filterSpec.rewritePath("RewritePath=/server-api(?<segment>/?.*)", "\${segment}")
                }.uri(
                    "http://localhost:8080"
                )
            }
            .build()
    }
}