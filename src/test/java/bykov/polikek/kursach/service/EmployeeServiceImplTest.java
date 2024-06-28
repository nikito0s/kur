package baranow.polikek.kursach.service;

import baranow.polikek.kursach.exceptions.ExceptionHandler;
import baranow.polikek.kursach.model.Employee;
import baranow.polikek.kursach.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ExceptionHandler exceptionHandler;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addEmployee() {
        Employee employee = new Employee();
        employee.setIdEmployee(1L);
        employee.setNameEmployee("John");
        employee.setSurnameEmployee("Doe");
        employee.setNumTelephoneEmployee("123456789");

        // Mock the save method
        when(employeeRepository.save(employee)).thenReturn(employee);

        employeeService.addEmployee(employee);

        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void getAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setIdEmployee(1L);
        employee1.setNameEmployee("John");
        employee1.setSurnameEmployee("Doe");

        Employee employee2 = new Employee();
        employee2.setIdEmployee(2L);
        employee2.setNameEmployee("Jane");
        employee2.setSurnameEmployee("Smith");

        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void getEmployeeById() {
        Employee employee = new Employee();
        employee.setIdEmployee(1L);
        employee.setNameEmployee("John");
        employee.setSurnameEmployee("Doe");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.getEmployeeById(1L);

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getNameEmployee());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void putEmployeeById() {
        Employee existingEmployee = new Employee();
        existingEmployee.setIdEmployee(1L);
        existingEmployee.setNameEmployee("John");
        existingEmployee.setSurnameEmployee("Doe");

        Employee updatedEmployee = new Employee();
        updatedEmployee.setNameEmployee("Jane");
        updatedEmployee.setSurnameEmployee("Smith");
        updatedEmployee.setNumTelephoneEmployee("987654321");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(existingEmployee)).thenReturn(existingEmployee);

        Optional<Employee> result = employeeService.putEmployeeById(1L, updatedEmployee);

        assertTrue(result.isPresent());
        assertEquals("Jane", result.get().getNameEmployee());
        assertEquals("Smith", result.get().getSurnameEmployee());
        assertEquals("987654321", result.get().getNumTelephoneEmployee());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(existingEmployee);
    }

    @Test
    void deleteEmployeeById() {
        // Mock the deleteById method
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteEmployeeById(1L);

        verify(employeeRepository, times(1)).deleteById(1L);
    }
}
