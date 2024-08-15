package kr.jay.tobyspring.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

/**
 * Order
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/10/24
 */
public class Order {
    private Long id;

    private String no;

    private BigDecimal total;

    public Order() {
    }

    public Order(String no, BigDecimal total) {
        this.no = no;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public String getNo() {
        return no;
    }

    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", no='" + no + '\'' +
            ", total=" + total +
            '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
