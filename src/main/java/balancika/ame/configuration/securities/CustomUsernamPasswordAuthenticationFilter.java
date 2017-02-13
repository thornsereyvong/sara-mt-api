package balancika.ame.configuration.securities;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import balancika.ame.entities.MeDataSource;

public class CustomUsernamPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	@Autowired
	private MeDataSource dataSource;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		final String str = request.getParameter("company");
		String[] data = str.split("12345");
		String databaseName = data[0];
		request.getSession().setAttribute("databaseName", databaseName);
		request.getSession().setAttribute("company", data[1]);
		request.getSession().setAttribute("userActivity", request.getParameter("ame_username"));
		dataSource.setDb(request.getSession().getAttribute("databaseName").toString());
		dataSource.setIp(request.getSession().getAttribute("ip").toString());
		dataSource.setPort(request.getSession().getAttribute("port").toString());
		dataSource.setUn(request.getSession().getAttribute("usernamedb").toString());
		dataSource.setPw(request.getSession().getAttribute("passworddb").toString());
		return super.attemptAuthentication(request, response);
	}
}
