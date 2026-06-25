package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EmployeePayrollServiceTest {

    @Test
    public void given3Employees_WhenWrittenToFile_ShouldReturn3Entries()
            throws IOException {

        EmployeePayroll emp1 =
                new EmployeePayroll(101, "Prashanth", 50000);

        EmployeePayroll emp2 =
                new EmployeePayroll(102, "Rahul", 60000);

        EmployeePayroll emp3 =
                new EmployeePayroll(103, "Anjali", 70000);

        List<EmployeePayroll> employees =
                Arrays.asList(emp1, emp2, emp3);

        EmployeePayrollService service =
                new EmployeePayrollService();

        service.writeEmployeeData(employees);

        long entries = service.countEntries();

        Assertions.assertEquals(3, entries);
    }
        @Test
        public void givenEmployeePayrollFile_WhenPrinted_ShouldReturnThreeEntries()
                throws IOException {

            EmployeePayrollService service = new EmployeePayrollService();

            System.out.println("Employee Payroll Data");

            service.printData();

            long entries = service.countEntries();

            Assertions.assertEquals(3, entries);
        }
    @Test
    public void givenEmployeePayrollFile_WhenCounted_ShouldReturnThreeEntries()
            throws IOException {

        EmployeePayrollService service = new EmployeePayrollService();

        long entries = service.countEntries();

        System.out.println("Number of Entries : " + entries);

        Assertions.assertEquals(3, entries);
    }
    @Test
    public void givenEmployeePayrollFile_WhenRead_ShouldReturnThreeEmployees() throws IOException {

        EmployeePayrollService service = new EmployeePayrollService();

        List<EmployeePayroll> employeeList = List.of(
                new EmployeePayroll(101, "Prashanth", 50000),
                new EmployeePayroll(102, "Rahul", 60000),
                new EmployeePayroll(103, "Anjali", 70000)
        );

        // First create the file
        service.writeEmployeeData(employeeList);

        // Then read it
        List<EmployeePayroll> result = service.readEmployeePayrollData();

        // Print data
        result.forEach(System.out::println);

        // Verify
        Assertions.assertEquals(3, result.size());
    }
 }