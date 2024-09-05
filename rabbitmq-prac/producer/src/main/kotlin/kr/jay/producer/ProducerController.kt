package kr.jay.producer

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * ProducerController
 *
 * @author jaypark
 * @version 1.0.0
 * @since 9/5/24
 */
@RestController
class ProducerController(
    private val producer: Producer,
) {

    @GetMapping("/produce")
    fun produce() {
        producer.produce("example.exchange", "example.routingKey", "Hello, RabbitMQ!")
    }
}