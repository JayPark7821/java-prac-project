package kr.jay.producer

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

/**
 * Producer
 *
 * @author jaypark
 * @version 1.0.0
 * @since 9/5/24
 */
@Component
class Producer(
    private val rabbitTemplate: RabbitTemplate,
) {

    fun produce(exchange: String, routingKey: String, message: Any){
        rabbitTemplate.convertAndSend(exchange, routingKey, message)
    }

}