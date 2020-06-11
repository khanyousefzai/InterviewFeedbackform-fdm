package com.fdmgroup;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fdmgroup.dao.SecurityAnswerRepo;
import com.fdmgroup.dao.SecurityQuestionRepo;
import com.fdmgroup.dao.UserRepo;
import com.fdmgroup.model.SecurityAnswer;
import com.fdmgroup.model.User;
import com.fdmgroup.controller.IndexController;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class IndexControllerTest {
	
	@Autowired
	SecurityQuestionRepo securityQuestionRepo;
	@Autowired
	SecurityAnswerRepo securityAnswerRepo;
	@Autowired
	UserRepo userRepo;
	@Autowired
	IndexController indexController;
	
	//Refer to Persona	
	User testUser = new User("bwayne","Bw1234","bruce.wayne@fdmgroup.com","Bruce","Wayne",1);
	String email = testUser.getEmail();
	int userId = testUser.getUserId();
	String password = "Bw1234";
	String type = "Trainee";
	String accountType ="Java";
	String invalidEmail = "fake@fdmgroup.com";
	int qOneId = 2; 
	String qOneAnswer = "Question one answer";
	int qTwoId = 6;
	String qTwoAnswer = "Question two answer";
	int qThreeId = 7;
	String qThreeAnswer = "Question three answer";
	SecurityAnswer  testSecurityAnswer = new SecurityAnswer(userId,qOneId,qOneAnswer,qTwoId, qTwoAnswer, qThreeId,qThreeAnswer);
	
	//IndexController indexController = new IndexController();
	//User testUser = mock(User.class);
	
	@Before
	public void init() {
        System.out.println("test start-----------------");
	}
	
//askSequrityQuestion

	@Test
	public void test_GetUser_From_DatabaseByEmail() {
		List<User> foundUser = userRepo.findByEmail("admin@fdmgroup.com");
		String foundEmail = foundUser.get(0).getEmail();
		assertEquals("admin@fdmgroup.com",foundEmail);
	}
	
	@Test
	public void test_InsertUser_Into_Database() throws MessagingException {
		ModelAndView mv = indexController.RegisterAttempt(testUser, testSecurityAnswer, password, type, accountType); 
		//return to registration.jsp if user already  in the database
		assertEquals(mv.getViewName(),"index.jsp");
	}
	
	@Test
	public void test_GetSecurityQuestion_From_DatabaseByUserId() {
		
		ModelAndView mv = indexController.askSecurityQuestion(email);
		//Valid email
		assertEquals(mv.getViewName(),"securityQuestion.jsp");
		//Invalid email
		ModelAndView mv2 = indexController.askSecurityQuestion(invalidEmail);
		assertEquals(mv2.getViewName(),"retrievePassword.jsp");
		assertEquals(mv2.getModel().get("errMsg"), "User not exist!");
	}



}
