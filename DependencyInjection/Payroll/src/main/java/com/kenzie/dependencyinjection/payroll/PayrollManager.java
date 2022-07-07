package com.kenzie.dependencyinjection.payroll;

import java.math.BigDecimal;
import java.util.List;

public class PayrollManager {
    /**
     * Main method of project.
     * @param args Main method parameter
     */
    public static void main(String[] args) {
        runPayroll();
    }

    private static void runPayroll() {
        PayrollManagerComponent dagger = DaggerPayrollManagerComponent.create();
        EmployeePaymentDistributor employeePaymentDistributor = dagger.provideEmployeePaymentDistributor();
        HumanResourcesClient humanResourcesClient = dagger.provideHumanResourcesClient();

        List<Employee> payroll = humanResourcesClient.getNextPayrollEmployees();

        for (Employee employee : payroll) {
            BigDecimal updatedBalance = employeePaymentDistributor.payEmployee(employee);
            System.out.println("Updated balance: $" + updatedBalance.setScale(2, BigDecimal.ROUND_HALF_UP));
        }
    }
}
