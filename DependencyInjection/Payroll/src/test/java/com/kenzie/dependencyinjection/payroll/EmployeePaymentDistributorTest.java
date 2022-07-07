package com.kenzie.dependencyinjection.payroll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoSettings;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmployeePaymentDistributorTest {
    private EmployeePaymentDistributor employeePaymentDistributor;

    @Mock
    private PayrollTracker payrollTracker;

    private BankClient bankClient;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        bankClient = new BankClient();
        employeePaymentDistributor = new EmployeePaymentDistributor(payrollTracker);
    }

    @Test
    public void employeeHasBeenPaid_withPaidEmployee_checksThePayrollTrackersSetOfPaidEmployees() {
        // GIVEN a paid employee
        BigDecimal salary = BigDecimal.valueOf(500);
        Employee employee = new Employee(salary);

        employeePaymentDistributor.payEmployee(employee);
        Set<Employee> paidEmployees = new HashSet<>();
        paidEmployees.add(employee);

        when(payrollTracker.getPaidEmployees()).thenReturn(paidEmployees);

        // WHEN we check if an employee has been paid
        boolean hasBeenPaid = employeePaymentDistributor.employeeHasBeenPaid(employee);

        // THEN assert it is return true
        assertTrue(hasBeenPaid,
                "Expected hasBeenPaid to be true for an employee who has been paid");
    }
}
