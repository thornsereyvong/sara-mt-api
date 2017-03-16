package balancika.ame.service.report.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.MeDataSource;
import balancika.ame.service.report.AccountReceivableService;
import balancika.ame.utilities.DBConnection;
import balancika.ame.utilities.SQLUtil;

@Repository
public class AccountReceivableServiceImpl implements AccountReceivableService{

	@Override
	public List<Map<String, Object>> invoiceRegisterSummary(Map<String, String> criteria, MeDataSource dataSource) {
		return null;
	}

	@Override
	public Map<String, Object> startupInvoiceRegister(MeDataSource dataSource) {
		try (Connection con = DBConnection.getConnection(dataSource)){
			String statement = SQLUtil.LOCATION_SQL+SQLUtil.EMPLOYEE_SQL+SQLUtil.CLASS_SQL+SQLUtil.CUSTOMER_SQL+SQLUtil.MMDSALE_SQL;			
			String[] str = {"location","employee","class","customer","saleDate"};			
			String sql = "{ call ame_multiple_sql_statement(?,?)}";
			CallableStatement c = con.prepareCall(sql);
			c.setInt(1, str.length);
			c.setString(2, statement);
			boolean r = c.execute();
			int i=0;
			Map<String, Object> map = null;
			while(r){
				if(i==0){
					map = new HashMap<String, Object>();
				}				
				ResultSet rs = c.getResultSet();
				map.put(str[i], SQLUtil.aliasRSToMap(rs));
				rs.close();
				r = c.getMoreResults();
				i++;
			}		
			return map;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
