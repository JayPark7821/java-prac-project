package kr.jay.apigatewayprac.common

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Log
 *
 * @author jaypark
 * @version 1.0.0
 * @since 9/6/24
 */
interface Log {
    val log: Logger get() = LoggerFactory.getLogger(this.javaClass)
}