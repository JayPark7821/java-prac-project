package kr.jay.consumer

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

/**
 * Consumer
 *
 * @author jaypark
 * @version 1.0.0
 * @since 9/5/24
 */
@Component
class Consumer {

    @RabbitListener(queues = ["example.queue"])
    fun consume(message: String) {
        println("Received message: $message")
    }
}