package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeCreateDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.dto.EmployeeUpdateDto;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.util.config.mapstruct.EmployeeToDtoMapper;
import com.example.demowithtests.util.exceptions.ResourceWasDeletedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class ServiceBean implements Service {

    private final Repository repository;
    private EmployeeToDtoMapper mapper
            = Mappers.getMapper(EmployeeToDtoMapper.class);

    /**
     * > The function creates an employee and sets the isFull property to true if the employee has a country, name, and
     * email
     *
     * @param employee The employee object that is being created.
     * @return The employee object is being returned.
     */
    @Override
    public EmployeeCreateDto create(Employee employee) {
        employee.setIsFull(employee.getCountry() != null && employee.getName() != null && employee.getEmail() != null);

        return mapper.employeeToCreateDto(repository.save(employee));
    }

    /**
     * > The function returns a list of all employees in the database
     *
     * @return A list of all employees
     */
    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    /**
     * If the employee with the given id exists, return it, otherwise throw an exception
     *
     * @param id The id of the employee to be retrieved.
     * @return An Employee object
     */
    @Override
    public EmployeeReadDto getById(Integer id) {
        return mapper.employeeToReadDto(repository.findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }

    /**
     * If the employee exists, update the employee with the new values and return the updated employee
     *
     * @param id       The id of the employee to update.
     * @param employee The employee object that contains the new values for the employee.
     * @return The employee object is being returned.
     */
    @Override
    public EmployeeUpdateDto updateById(Integer id, Employee employee) {
        return mapper.employeeToUpdateDto(repository.findById(id)
                .map(entity -> {
                    entity.setName(employee.getName());
                    entity.setEmail(employee.getEmail());
                    entity.setCountry(employee.getCountry());
                    entity.setIsUpdated(true);
                    return repository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id)));
    }

    /**
     * If the employee exists, delete it. If it doesn't exist, throw an exception
     *
     * @param id The id of the employee to be deleted.
     */
    @Override
    public void removeById(Integer id) {
        Employee employee = repository.findById(id)
                .orElseThrow(ResourceWasDeletedException::new);
        repository.delete(employee);
    }

    /**
     * It removes all the data from the database.
     */
    @Override
    public void removeAll() {
        repository.deleteAll();

    }

    /**
     * Get all employees with the given name.
     *
     * @param name The name of the method.
     * @return A list of employees with the name specified.
     */
    public List<Employee> getListAllByName(String name) {
        return repository.getAllByName(name);
    }

    /**
     * Get all employees where isFull is true.
     *
     * @return A list of all employees who are full time.
     */
    public List<Employee> getAllByIsFullTrue() {
        return repository.getAllByIsFullTrue();
    }

    /**
     * > Generate a random password for the employee with the given id and save it to the database
     *
     * @param id The id of the employee whose password is to be generated.
     * @return Employee
     */
    @Override
    public Employee generatePassword(int id) {
        Employee employee = repository.getById(id);
        String p = PasswordGenerator.generate();
        employee.setPassword(p);
        repository.save(employee);
        return employee;
    }

    /**
     * It updates the password of an employee by id.
     *
     * @param employee The employee object that is passed in from the front end.
     * @return The updated employee.
     */
    @Override
    public Employee updatePasswordById(Employee employee) {
        Integer id = employee.getId();
        String password = employee.getPassword();
        Employee updatedEmployee = repository.getById(id);
        updatedEmployee.setPassword(password);
        repository.save(updatedEmployee);
        return updatedEmployee;
    }



}
