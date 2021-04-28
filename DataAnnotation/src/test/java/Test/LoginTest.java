package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import View.LoginView;

public class LoginTest {
	private LoginView login;

	@Before
	public void setUp() {
		login = new LoginView();
	}
	
	@Test
	public void testName() {
		login.setName("2018054541");
		String userName = login.getLoginUser();
		assertEquals("2018054541", userName);

	}
	
	@Test
	public void testPwd() {
		login.setPwd("123456");
		String userPwd = login.getLoginPwd();
		assertEquals("123456", userPwd);
	}
	
	@Test
	public void testLogin() {
		login.setIsCorrect();
		boolean isCorrect = login.getIsCorrect();
		assertEquals(false, isCorrect);
	}

}
