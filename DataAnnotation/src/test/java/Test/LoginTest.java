package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import View.LoginView;

public class LoginTest {

	@Test
	public void testLogin() {
		LoginView login = new LoginView();
		login.setText("2018054541", "123456");
		login.setIsCorrect();
		String userName = login.getLoginUser();
		String userPwd = login.getLoginPwd();
		boolean isCorrect = login.getIsCorrect();
		assertEquals("2018054541", userName);
		assertEquals("123456", userPwd);
		assertEquals(false, isCorrect);
	}

}
