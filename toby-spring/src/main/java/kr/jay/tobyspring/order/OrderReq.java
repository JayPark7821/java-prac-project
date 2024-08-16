package kr.jay.tobyspring.order;

import java.math.BigDecimal;

/**
 * OrderReq
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/16/24
 */
public record OrderReq(String no, BigDecimal total) {

}
