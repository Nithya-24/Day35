import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import java.util.List;

import com.EmpPayrollServiceDB;
import com.EmpPayrollServiceDB.IOService;
import com.EmployeePayrollData;
import com.EmployeePayrollException;

public class Emp_PayrollTest {

	@Test
	public void shouldAnswerWithTrue() {
		assertTrue(true);
	}
	public void given3Employees_WhenWrittenToFile_ShouldMatchEmployeeEntries() {
		EmployeePayrollData[] arrayOfEmp = { new EmployeePayrollData(1, "Nithya R", 400000),
				new EmployeePayrollData(2, "XYZ", 500000),
				new EmployeePayrollData(3, "ABC", 600000) };
		EmpPayrollServiceDB employeePayrollService;
		employeePayrollService = new EmpPayrollServiceDB(Arrays.asList(arrayOfEmp));
		employeePayrollService.writeEmployeeData(IOService.FILE_IO);
		long entries = employeePayrollService.countEntries(IOService.FILE_IO);
		employeePayrollService.printData(IOService.FILE_IO);
		List<EmployeePayrollData> employeeList = employeePayrollService.readData(IOService.FILE_IO);
		System.out.println(employeeList);
		assertEquals(3, entries);
	}

	@Test
	/**
	 * created test method to match the employeeCount
	 */
	public void givenEmployeePayrollInDB_WhenRetrieved_ShouldMatchEmployeeCount() {

		/**
		 * Creating object for EmployeePayRollService Class
		 */
		EmpPayrollServiceDB employeePayrollService = new EmpPayrollServiceDB();
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readData(IOService.DB_IO);
		assertEquals(3, employeePayrollData.size());
	}
	@Test
	/**
	 * To check whether the salary is updated in the database and is synced with the
	 * DB
	 * 
	 * @throws EmployeePayrollException
	 */
	public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDatabase() throws EmployeePayrollException {
		EmpPayrollServiceDB employeePayrollService = new EmpPayrollServiceDB();
		List<EmployeePayrollData> employeePayrollData = employeePayrollService.readData(IOService.DB_IO);
		employeePayrollService.updateEmployeeSalary("ABC", 3000000);
		boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("ABC");
		assertTrue(result);
		System.out.println(employeePayrollData);
	}
}