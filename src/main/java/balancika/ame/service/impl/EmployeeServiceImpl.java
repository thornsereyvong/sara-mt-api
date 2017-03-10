package balancika.ame.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.CallableStatement;

import balancika.ame.entities.MeDataSource;
import balancika.ame.entities.maintain.Employee;
import balancika.ame.service.EmployeeService;
import balancika.ame.utilities.DBConnection;

@Repository
public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public List<Employee> listEmployee(MeDataSource dataSource) throws SQLException {
		Employee employee = null;
		CallableStatement cbs = null;
		try(Connection con = DBConnection.getConnection(dataSource)){
			
			String sql = "{call ame_list_employee()}";
			cbs = (CallableStatement) con.prepareCall(sql);		
			ResultSet rs = cbs.executeQuery();
			
			ArrayList<Employee> arrEmp = new ArrayList<Employee>();
			while(rs.next()){
				employee = new Employee();
				employee.setEmpId(rs.getString("empId"));
				employee.setEmpName(rs.getString("empName"));
				employee.setEmpEmail(rs.getString("empEmail"));
				employee.setEmpPostion(rs.getString("empPosition"));
				arrEmp.add(employee);
			}
			rs.close();			
			return arrEmp;
		} catch (Exception ex){
			ex.printStackTrace();
		}finally{
			cbs.close();
		}
		
		return null;
	}

	
}
