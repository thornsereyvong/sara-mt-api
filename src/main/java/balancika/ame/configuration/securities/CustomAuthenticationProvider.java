package balancika.ame.configuration.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import balancika.ame.entities.CrmUserLogin;
import balancika.ame.service.impl.UserServiceImpl;
import balancika.ame.utilities.PasswordEncrypt;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserServiceImpl userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName().trim();
		String password = authentication.getCredentials().toString().trim();
		CrmUserLogin user = userService.findUserByUsername(username);
		if (user == null || (!user.getUserID().equalsIgnoreCase(username)) || user.getHas() == 0 || !password.equals(new PasswordEncrypt().BalDecrypt(user.getPassword()))) {
			throw new BadCredentialsException("Invalid Username and password!");
		}

		if (user.getPermission() == 0) {
			throw new BadCredentialsException("You have no permission! Please contact your administrator!");
		}
		return new UsernamePasswordAuthenticationToken(user.getUserID(), password, null);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
