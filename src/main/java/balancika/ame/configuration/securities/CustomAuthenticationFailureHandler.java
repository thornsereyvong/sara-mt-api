package balancika.ame.configuration.securities;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		super.setDefaultFailureUrl("/login?error");
		super.onAuthenticationFailure(request, response, exception);
		 if(exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
			 System.out.println("User Not Found!");
		  } else if (exception.getClass().isAssignableFrom(DisabledException.class)) {
			  System.out.println("User was Disable!");
		  }
	}
}
