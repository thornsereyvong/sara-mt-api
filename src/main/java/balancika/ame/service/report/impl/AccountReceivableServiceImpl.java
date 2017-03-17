package balancika.ame.service.report.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
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
		try (Connection con = DBConnection.getConnection(dataSource)){			
			String sql = "SELECT DATE_FORMAT(s.SalDate, '%d-%b-%Y') as SalDate, sd.LocationID ,s.SalID,c.CustName,SUM(COALESCE(sd.NetTotalAmt,0) + COALESCE(sd.DisDol,0)) AS 'TotalAmt',sum(COALESCE(sd.DisDol,0) + COALESCE(sd.DisInv,0)) as DisInvDol,e.EmpName, "+
					             "COALESCE(s.PmtToDate,0) AS 'PmtToDate',s.CustID,s.EmpID,s.PostStatus,s.SalReference,COALESCE(s.ClassID) as 'ClassID',COALESCE((SELECT C.Des FROM tblclass C WHERE C.ClassID = s.ClassID),'') AS ClassName "+
					             ",(SUM(COALESCE(sd.NetTotalAmt,0) + COALESCE(sd.DisDol,0)) - sum(COALESCE(sd.DisDol,0) + COALESCE(sd.DisInv,0))) as 'NetTotalAmt' "+
					             ",((SUM(COALESCE(sd.NetTotalAmt,0) + COALESCE(sd.DisDol,0)) - sum(COALESCE(sd.DisDol,0) + COALESCE(sd.DisInv,0))) - COALESCE(s.PmtToDate,0)) as 'AmtDue' "+
					             ",COALESCE((SELECT SUM(COALESCE(RcpAmount,0)) FROM tblreceiptdetails WHERE SalID=s.SalID),0) 'rcpToDate' "+
					     "FROM tblsales s LEFT JOIN tblsalesdetails sd on s.SalID = sd.SalID "+
					     "LEFT JOIN tblemployee e on s.EmpID = e.EmpID LEFT JOIN tblcustomer c on s.CustID = c.CustID "+
					     "WHERE (s.SalDate BETWEEN ? AND ?) "+
					     "AND IF(LOWER(?) = 'all',TRUE,s.PostStatus = ?) "+
					     "AND IF(LOWER(?) = 'all',TRUE,sd.LocationID >= ?) "+
					     "AND IF(LOWER(?) = 'all',TRUE,sd.LocationID <= ?) "+
					     "AND IF(LOWER(?) = 'all',TRUE,s.EmpID >= ?) "+
					     "AND IF(LOWER(?) = 'all',TRUE,s.EmpID <= ?) "+
					     "GROUP BY s.SalID";
			PreparedStatement p = con.prepareStatement(sql);
			p.setString(1, criteria.get("fDate"));
			p.setString(2, criteria.get("tDate"));
			
			p.setString(3, criteria.get("postStatus"));
			p.setString(4, criteria.get("postStatus"));
			
			p.setString(5, criteria.get("fLocation"));
			p.setString(6, criteria.get("fLocation"));
			
			p.setString(7, criteria.get("tLocation"));
			p.setString(8, criteria.get("tLocation"));
			
			p.setString(9, criteria.get("fEmployee"));
			p.setString(10, criteria.get("fEmployee"));
			
			p.setString(11, criteria.get("tEmployee"));
			p.setString(12, criteria.get("tEmployee"));
			
			ResultSet rs = p.executeQuery();
			return SQLUtil.aliasRSToMap(rs);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
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
