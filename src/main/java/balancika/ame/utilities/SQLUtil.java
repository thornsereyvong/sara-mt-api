package balancika.ame.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.ResultSetMetaData;

public class SQLUtil {
	
	public static List<Map<String, Object>> aliasRSToMap(ResultSet rs){
		try {
			ArrayList<Map<String, Object>> arr = new ArrayList<>();
			ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();
			Map<String, Object> map = null; 
			while(rs.next()){
				 map = new HashMap<>();
				 for (int i = 1; i <= meta.getColumnCount(); i++) {
	                String key = meta.getColumnLabel(i);
	                Object value = rs.getObject(i);
	                map.put(key, value);
	            }
				arr.add(map);
			}
			return arr;			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
