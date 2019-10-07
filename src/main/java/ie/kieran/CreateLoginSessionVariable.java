package ie.kieran;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class CreateLoginSessionVariable 
	implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	private HttpSession session;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		session.setAttribute("loggedin", true);
	}
}