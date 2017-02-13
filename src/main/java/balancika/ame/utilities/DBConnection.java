package balancika.ame.utilities;

import java.sql.SQLException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.mysql.jdbc.Connection;
import balancika.ame.entities.MeDataSource;

public class DBConnection {
	public static Connection getConnection(MeDataSource dataSource){
		try{
			DriverManagerDataSource source = new DriverManagerDataSource();
			source.setDriverClassName("com.mysql.jdbc.Driver");
			source.setUrl("jdbc:mysql://"+dataSource.getIp()+":"+dataSource.getPort()+"/"+dataSource.getDb());
			source.setUsername(dataSource.getUn());
			source.setPassword(dataSource.getPw());
			return (Connection) source.getConnection();
		}catch(SQLException e){
			e.printStackTrace();
		}	
		return null;
	}
}
