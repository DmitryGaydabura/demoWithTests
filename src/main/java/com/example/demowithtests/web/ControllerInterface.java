package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.EmployeeCreateDto;
import com.example.demowithtests.dto.EmployeeReadDto;
import com.example.demowithtests.dto.EmployeeUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface ControllerInterface {
    /**
     * This is endpoint to add a new employee
     *
     * @param requestForSave The request body of the request.
     * @return EmployeeCreateDto
     */
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This is endpoint to add a new employee.", description = "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    EmployeeCreateDto saveEmployee(@RequestBody @Valid Employee requestForSave);

    /**
     * "This is endpoint to get all employees."
     *
     * The description is a bit more verbose: "Create request to add a new employee."
     *
     * The tags are used to group the operations together
     *
     * @return List of all employees
     */
    @GetMapping("/users/getAll")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    @Operation(summary = "This is endpoint to get all employees.", description = "Create request to add a new employee.", tags = {"Employee"})
    @ResponseStatus(HttpStatus.OK)
    List<Employee> getAllUsers();

    /**
     * This is endpoint returned a employee by his id
     *
     * @param id The id of the employee you want to get.
     * @return EmployeeReadDto
     */
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint returned a employee by his id.", description = "Create request to get all employees", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    EmployeeReadDto getEmployeeById(@PathVariable Integer id);

    /**
     * This is endpoint to update employee by ID
     *
     * @param id The id of the employee to update.
     * @param employee The object that will be updated.
     * @return EmployeeUpdateDto
     */
    @PutMapping("/users/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "User was updated!"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    @Operation(summary = "This is endpoint to update employee by ID.", description = "Update (Name,Country,Email only!) by ID", tags = {"Employee"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    EmployeeUpdateDto refreshEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee);

    /**
     * "This is endpoint to remove employee by ID."
     *
     * The description is a little more verbose: "Create request to remove employee by ID."
     *
     * The tags are used to group the endpoints together
     *
     * @param id The id of the employee to be removed.
     */
    @PatchMapping("/users/{id}")
    @Operation(summary = "This is endpoint to remove employee by ID.", description = "Create request to remove employee by ID.", tags = {"Employee"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeEmployeeById(@PathVariable Integer id);

    /**
     * "This is endpoint to remove all employees."
     *
     * The description is a little more verbose: "Create request to remove all employees."
     *
     * The tags are used to group the operations together
     */
    @DeleteMapping("/users")
    @Operation(summary = "This is endpoint to remove all employees.", description = "Create request to remove all employees.", tags = {"Employee"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeAllUsers();

    /**
     * "This is endpoint to read all employees by name."
     *
     * The description is a bit more verbose: "Create request to get all employees by name."
     *
     * The tags are used to group operations together. In this case, we're grouping all of our employee operations together
     *
     * @param name The name of the parameter.
     * @return List of employees
     */
    @GetMapping(value = "/users", params = {"name"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "User was updated!"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    @Operation(summary = "This is endpoint to read all employees by name.", description = "Create request to get all employees by name.", tags = {"Employee"})
    @ResponseStatus(HttpStatus.OK)
    List<Employee> getAllByName(@RequestParam(value = "name") String name);

    /**
     * This is endpoint to get all employees by value isFull
     *
     * @return List of employees
     */
    @GetMapping(value = "/users/isFull")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "User was updated!"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    @Operation(summary = "This is endpoint to get all employees by value isFull.", description = "Create request to get all employees by isFull.", tags = {"Employee"})
    @ResponseStatus(HttpStatus.OK)
     List<Employee> getAllByIsFullTrue();

    /**
     * This is endpoint to generate password for employee by ID
     *
     * @param id The ID of the employee to generate a password for.
     * @return Employee
     */
    @PutMapping(value = "/users/password", params = {"id"})
    @Operation(summary = "This is endpoint to generate password for employee by ID.", description = "Create request to generate new Password for employee by ID.", tags = {"Employee"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    Employee generateRandomPassword(@RequestParam(value = "id") int id);

    /**
     * "This is endpoint to update password for employee by ID."
     *
     * @param employee The object that will be updated.
     * @return Employee
     */
    @PatchMapping("/users/password")
    @Operation(summary = "This is endpoint to update password for employee by ID.", description = "Create request to update password for employee by ID.", tags = {"Employee"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    Employee updatePassword(@RequestBody Employee employee);
}
