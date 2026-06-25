package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {
    public enum IOService{
        CONSOLE_IO,
        DB_IO,
        REST_IO
    }
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

}
