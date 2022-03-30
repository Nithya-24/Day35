package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmpPayrollServiceDB {

	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}

	/**
	 * To get the list of employee payroll from the database
	 */
	public List<EmployeePayrollData> employeePayrollList;

	public EmpPayrollServiceDB() {
	}

	public EmpPayrollServiceDB(List<EmployeePayrollData> employeePayrollList) {
		this.employeePayrollList = employeePayrollList;
	}

	/**
	 * Main method for manipulation of JDBC
	 * 
	 * @param args - Default Java param (Not used)
	 */
	public static void main(String[] args) {
		List<EmployeePayrollData> employeePayrollList = new ArrayList<EmployeePayrollData>();
		EmpPayrollServiceDB employeePayrollService = new EmpPayrollServiceDB(employeePayrollList);
		Scanner consoleInputReader = new Scanner(System.in);
		employeePayrollService.readEmployeeData(consoleInputReader);
		employeePayrollService.writeEmployeeData(IOService.CONSOLE_IO);
	}

	/**
	 * @param consoleInputReader Read employee data
	 */
	public void readEmployeeData(Scanner consoleInputReader) {
		System.out.println("Enter employee ID : ");
		int id = Integer.parseInt(consoleInputReader.nextLine());
		System.out.println("Enter employee name : ");
		String name = consoleInputReader.nextLine();
		System.out.println("Enter employee salary : ");
		double salary = Double.parseDouble(consoleInputReader.nextLine());
		employeePayrollList.add(new EmployeePayrollData(id, name, salary));
	}

	/**
	 * Write payroll data to console
	 */
	public void writeEmployeeData(IOService ioService) {
		if (ioService.equals(IOService.CONSOLE_IO))
			System.out.println("Writing Employee Payroll Data to Console\n" + employeePayrollList);
		else if (ioService.equals(IOService.FILE_IO))
			new EmpFileIOService().writeData(employeePayrollList);
	}

	/**
	 * @param ioService Print Data
	 */
	public void printData(IOService ioService) {
		new EmpFileIOService().printData();
	}

	/**
	 * @param ioService
	 * @return number of entries
	 */
	public long countEntries(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			return new EmpFileIOService().countEntries();
		return 0;
	}

	/**
	 * @param ioService
	 * @return Employee Payroll Data List
	 */
	public List<EmployeePayrollData> readData(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			return new EmpFileIOService().readData();
		else if (ioService.equals(IOService.DB_IO))
			return new EmpPayrollService().readData();
		else
			return null;
	}
}