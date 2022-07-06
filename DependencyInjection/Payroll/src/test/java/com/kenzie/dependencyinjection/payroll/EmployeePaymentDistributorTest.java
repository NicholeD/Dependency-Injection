package com.kenzie.dependencyinjection.payroll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmployeePaymentDistributorTest {
    private EmployeePaymentDistributor employeePaymentDistributor;

    private PayrollTracker payrollTracker;

    private BankClient bankClient;

    @BeforeEach
    public void setup() {
        bankClient = new BankClient();
        payrollTracker = new PayrollTracker(bankClient);
        employeePaymentDistributor = new EmployeePaymentDistributor(payrollTracker);
    }

    @Test
    public void employeeHasBeenPaid_withPaidEmployee_checksThePayrollTrackersSetOfPaidEmployees() {
        // GIVEN a paid employee
        BigDecimal salary = BigDecimal.valueOf(500);
        Employee employee = new Employee(salary);

        employeePaymentDistributor.payEmployee(employee);

        // WHEN we check if an employee has been paid
        boolean hasBeenPaid = employeePaymentDistributor.employeeHasBeenPaid(employee);

        // THEN assert it is return true
        assertTrue(hasBeenPaid,
                "Expected hasBeenPaid to be true for an employee who has been paid");
    }
}
