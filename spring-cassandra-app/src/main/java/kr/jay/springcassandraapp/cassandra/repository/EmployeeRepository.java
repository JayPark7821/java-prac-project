package kr.jay.springcassandraapp.cassandra.repository;

import java.util.List;
import kr.jay.springcassandraapp.cassandra.entity.Employee;
import kr.jay.springcassandraapp.cassandra.entity.EmployeePrimaryKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 * EmployeeRepository
 *
 * @author jaypark
 * @version 1.0.0
 * @since 3/28/24
 */
@Repository
public interface EmployeeRepository extends CassandraRepository<Employee, EmployeePrimaryKey> {

    List<Employee> findByKeyLocationAndKeyDepartment(String location, String department);

}
