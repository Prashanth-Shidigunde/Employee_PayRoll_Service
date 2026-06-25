package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EmployeePayrollService {
    public enum IOService{
        CONSOLE_IO,
        DB_IO,
        REST_IO
    }
    private static final String PAYROLL_FILE = "EmployeePayroll.txt";
    private List<EmployeePayroll> employeePayrollList;

    public EmployeePayrollService(){}

    public EmployeePayrollService(List<EmployeePayroll> employeePayrollList) {
        this.employeePayrollList = employeePayrollList;
    }

    public static void main(String[] args) {
        ArrayList<EmployeePayroll> employeePayrollList =new ArrayList<>();
        EmployeePayrollService employeePayrollService=new EmployeePayrollService(employeePayrollList);
        Scanner consoleInputReader=new Scanner(System.in);
        employeePayrollService.readEmployeePayrollData(consoleInputReader);
        employeePayrollService.writeEmployeePayrollDat();
    }

    private void writeEmployeePayrollDat() {
        System.out.println("\n writing employee Payroll Roaster to Console\n"+employeePayrollList);
    }

    private void readEmployeePayrollData(Scanner consoleInputReader) {
        System.out.println("Enter Employee Id");
        int id=consoleInputReader.nextInt();
        System.out.println("Enter Employee Name");
        String name=consoleInputReader.next();
        System.out.println("Enter salary of Employee");
        double salary=consoleInputReader.nextDouble();
        employeePayrollList.add(new EmployeePayroll(id,name,salary));
    }
    //UC4
    // Write employee data to file
    public void writeEmployeeData(List<EmployeePayroll> employeeList) throws IOException {

        Path path = Path.of(PAYROLL_FILE);

        List<String> employeeData = employeeList.stream()
                .map(EmployeePayroll::toString)
                .collect(Collectors.toList());

        Files.write(path, employeeData);

        System.out.println("Employee Payroll Written Successfully.");
    }
    public long countEntries() throws IOException {

        Path path = Path.of(PAYROLL_FILE);

        return Files.lines(path).count();
    }

}
