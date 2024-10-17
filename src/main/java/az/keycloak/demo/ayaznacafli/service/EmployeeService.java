package az.keycloak.demo.ayaznacafli.service;

import az.keycloak.demo.ayaznacafli.entity.Employee;
import az.keycloak.demo.ayaznacafli.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee insert(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee, Long id) {
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}
