package kr.jay.springcassandraapp.service;

import kr.jay.springcassandraapp.cassandra.entity.Employee;
import kr.jay.springcassandraapp.cassandra.entity.EmployeePrimaryKey;
import kr.jay.springcassandraapp.cassandra.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

/**
 * CassandraService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/28/24
 */
@Service
public class CassandraService {

    private final EmployeeRepository employeeRepository;

    public CassandraService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void test() {
        Employee employee = new Employee(
            new EmployeePrimaryKey("Seoul", "IT", "Jay"),
            "010-1234-5678"
        );

        employeeRepository.save(employee);

        Employee employee1 = new Employee(
            new EmployeePrimaryKey("Suwon", "QA", "Park"),
            "010-1234-9999"
        );
        employeeRepository.save(employee1);

        employeeRepository.findByKeyLocationAndKeyDepartment("Seoul", "IT")
            .stream().map( e-> "location: %s, department: %s, name: %s, phoneNumber: %s".formatted(
                e.key.location, e.key.department, e.key.name, e.phoneNumber
            )).forEach(System.out::println);
    }
}
