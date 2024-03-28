package kr.jay.springcassandraapp.cassandra.entity;

import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * Employee
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/28/24
 */
@Table
public class Employee {

    @PrimaryKey
    public final EmployeePrimaryKey key;

    @Column
    public String phoneNumber;

    public Employee(EmployeePrimaryKey key, String phoneNumber) {
        this.key = key;
        this.phoneNumber = phoneNumber;
    }
}
