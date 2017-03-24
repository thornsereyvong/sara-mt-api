package balancika.ame.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

import balancika.ame.entities.MeDataSource;
import balancika.ame.service.FileService;
import balancika.ame.utilities.DBConnection;

@Repository
public class FileServiceImpl implements FileService {

	@Override
	public String getPath(MeDataSource meDataSource) {
		try (Connection con = DBConnection.getConnection(meDataSource)){
			String sql = "SELECT ImagePath FROM tblconfiguration;";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				return rs.getString("ImagePath");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
