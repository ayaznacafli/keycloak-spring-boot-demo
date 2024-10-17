package az.keycloak.demo.ayaznacafli.repository;

import az.keycloak.demo.ayaznacafli.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
