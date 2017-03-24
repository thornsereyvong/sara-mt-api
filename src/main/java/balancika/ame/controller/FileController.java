package balancika.ame.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import balancika.ame.entities.MeDataSource;
import balancika.ame.service.FileService;

@RestController
@RequestMapping("/rest/file/")
public class FileController {
	
	@Autowired
	private MeDataSource dataSource;
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping(value = {"/get/{fileName:.*}"}, method = RequestMethod.GET)
	public void get(@PathVariable("fileName") String FileName, HttpServletRequest req, HttpServletResponse response) throws SQLException{
		try {
			dataSource = dataSource.getMeDataSourceByHttpServlet(req);
			
			String filePath = fileService.getPath(dataSource);
			
			File f;
			
			if(filePath != null){
				File Tempf = new File(filePath);
				if(Tempf.exists()){
					f = new File(filePath + "/", FileName);
				}else{
					f = new File(req.getServletContext().getRealPath("/"), FileName);
				}
			}else{
				f = new File(req.getServletContext().getRealPath("/"), FileName);
			}
			
			if(f != null){
				InputStream is = new FileInputStream(f);
				org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
			    response.flushBuffer();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = {"/getfile/{fileName:.*}"}, method = RequestMethod.GET)
	public byte[] getfile(@PathVariable("fileName") String FileName, HttpServletRequest req, HttpServletResponse response) throws SQLException{
		try {
			dataSource = dataSource.getMeDataSourceByHttpServlet(req);
			
			String filePath = fileService.getPath(dataSource);
			
			File f;
			
			if(filePath != null){
				File Tempf = new File(filePath);
				if(Tempf.exists()){
					f = new File(filePath + "/", FileName);
				}else{
					f = new File(req.getServletContext().getRealPath("/"), FileName);
				}
			}else{
				f = new File(req.getServletContext().getRealPath("/"), FileName);
			}
			
			if(f != null){
				InputStream is = new FileInputStream(f);
				return IOUtils.toByteArray(is);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
