package com.fdmgroup.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fdmgroup.dao.AccountManagerRepo;
import com.fdmgroup.dao.AdminRepo;
import com.fdmgroup.dao.ExpertiseRepo;
import com.fdmgroup.dao.FormRepo;
import com.fdmgroup.dao.ReportRepo;
import com.fdmgroup.dao.SecurityAnswerRepo;
import com.fdmgroup.dao.SecurityQuestionRepo;
import com.fdmgroup.dao.TraineeRepo;
import com.fdmgroup.dao.TrainerRepo;
import com.fdmgroup.dao.UserRepo;
import com.fdmgroup.model.AccountManager;
import com.fdmgroup.model.Admin;
import com.fdmgroup.model.Client;
import com.fdmgroup.model.Expertise;
import com.fdmgroup.model.Form;
import com.fdmgroup.model.Question;
import com.fdmgroup.model.Report;
import com.fdmgroup.model.SecurityAnswer;
import com.fdmgroup.model.SecurityQuestion;
import com.fdmgroup.model.Tag;
import com.fdmgroup.model.Trainee;
import com.fdmgroup.model.Trainer;
import com.fdmgroup.model.User;

/**
 * Index page Controller, responsible for handing the user login and
 * registration
 * 
 * @author Ibtisam C, Simon D, Daniel P
 */
@Controller
public class IndexController {
	
	@Autowired
	private SMTPMailSender smtpmailsender;
	
	@Autowired
	TraineeRepo traineeRepo;

	@Autowired
	AccountManagerRepo amRepo;

	@Autowired
	TrainerRepo trainerRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	FormRepo formRepo;

	@Autowired
	AdminRepo adminRepo;

	@Autowired
	ExpertiseRepo exRepo;

	@Autowired
	ReportRepo rRepo;

	@Autowired
	SecurityQuestionRepo securityQuestionRepo;

	@Autowired
	SecurityAnswerRepo securityAnswerRepo;

	/**
	 * Landing Page, maps to index.jsp
	 * 
	 * @return ModelAndView A reference to the index page
	 */
	@RequestMapping("/")
	public ModelAndView Home(HttpSession session) {
		if (session.getAttribute("errMsg") != null) {
			session.setAttribute("errMsg", "");
		}
		if (session.getAttribute("succMsg") != null) {
			session.setAttribute("succMsg", "");
		}
		if (session.getAttribute("type") == null) {
			ModelAndView index = new ModelAndView("index.jsp");
			return index;
		} else {
			String t = (String) session.getAttribute("type");
			switch (t) {
			case "trainee":
				ModelAndView traineehome = new ModelAndView("home.jsp");
				return traineehome;
			case "trainer":
				ModelAndView trainerhome = new ModelAndView("trainerHome.jsp");
				return trainerhome;
			case "admin":
				ModelAndView adminhome = new ModelAndView("adminHome.jsp");
				return adminhome;
			default:
				ModelAndView error = new ModelAndView("errorPage.jsp");
				return error;
			}
		}

	}

	/**
	 * Used to map the user login attempt to access the site, checks to see if the
	 * password matches the username entered
	 * 
	 * @param username The username input by the user
	 * @param password The password input by the user
	 * @return index with an error message or appropriate landing page dependent on
	 *         input from user
	 */
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public ModelAndView LoginAttempt(@RequestParam String username, @RequestParam String password,
			HttpSession session) {
		if (userRepo.findByUsername(username).size() > 0) {
			User foundUser = userRepo.findByUsername(username).get(0);
			boolean matched = BCrypt.checkpw(password, foundUser.getPassword());
			// if(foundUser.getPassword().equals(password)) {
			if (matched == true) {
				ModelAndView home;
				if (foundUser.getPermission() == 1) {
					List<Form> foundUserForm = formRepo.findByAuthorOrderByInterviewDateDesc(foundUser);
					Trainee foundTrainee = traineeRepo.findByUsername(username).get(0);
					session.setAttribute("userForms", foundUserForm);
					session.setAttribute("user", foundTrainee);
					session.setAttribute("type", "trainee");
					String succMsg = "";
					String errMsg = "";
					home = new ModelAndView("home.jsp");
					home.addObject("succMsg", succMsg);
					home.addObject("errMsg", errMsg);
					return home;
				} else if (foundUser.getPermission() == 2) {
					List<Form> foundUserForm = formRepo.findAllByOrderByInterviewDateDesc();
					Trainer foundTrainer = trainerRepo.findByUsername(username).get(0);
					List<Report> rList = rRepo.findByrStatus("new");
					session.setAttribute("user", foundTrainer);
					session.setAttribute("type", "trainer");
					session.setAttribute("report", rList.get(0));
					home = new ModelAndView("trainerHome.jsp");
					session.setAttribute("allForms", foundUserForm);
					home.addObject("question", new Question());
					home.addObject("client", new Client());
					home.addObject("tag", new Tag());
					String succMsg = "";
					String errMsg = "";
					home.addObject("succMsg", succMsg);
					home.addObject("errMsg", errMsg);
					return home;
				} else if (foundUser.getPermission() == 3) {
					List<Form> foundUserForm = formRepo.findAllByOrderByInterviewDateDesc();
					AccountManager foundAM = amRepo.findByUsername(username).get(0);
					List<Report> rList = rRepo.findByrStatus("new");
					session.setAttribute("user", foundAM);
					session.setAttribute("type", "accountManager");
					session.setAttribute("report", rList.get(0));
					home = new ModelAndView("accountManagerHome.jsp");
					session.setAttribute("allForms", foundUserForm);
					home.addObject("question", new Question());
					home.addObject("client", new Client());
					home.addObject("tag", new Tag());
					String succMsg = "";
					String errMsg = "";
					home.addObject("succMsg", succMsg);
					home.addObject("errMsg", errMsg);
					return home;
				} else if (foundUser.getPermission() == 9) {
					Admin foundAdmin = adminRepo.findByUsername(username).get(0);
					session.setAttribute("user", foundAdmin);
					session.setAttribute("type", "admin");
					home = new ModelAndView("adminHome.jsp");
					String succMsg = "";
					String errMsg = "";
					home.addObject("succMsg", succMsg);
					home.addObject("errMsg", errMsg);
					return home;
				}
			}
		}
		String errMsg = "Incorrect Username or Password!";
		String succMsg = "";
		ModelAndView index = new ModelAndView("index.jsp");
		index.addObject("succMsg", succMsg);
		index.addObject("errMsg", errMsg);
		return index;
	}
	/**
	 * Maps the Register link to an empty registration view
	 * 
	 * @param <SecurityQuestion>
	 * @return The view for registration.jsp
	 */
	@RequestMapping("/registerView")
	public ModelAndView RegisterReq() {
		ModelAndView registrationView = new ModelAndView("registration.jsp");
		List<SecurityQuestion> questionArray = (List<SecurityQuestion>) securityQuestionRepo.findAll();
		registrationView.addObject("securityQuestion", questionArray);
		return registrationView;
	}

	/**
	 * Used to verify the user's attempt at trying to make a new account. It checks
	 * for whether the passwords match, the user already exists, whether the email
	 * is already registered to an account, or if a correct account type is selected
	 * by the user
	 * 
	 * @param user        An object representing the user information input by the
	 *                    user, used to verify if the user already exists, etc.
	 * @param confirm_pw  A string that represents the second input of the password
	 *                    input by the user, used to verify that the password
	 *                    matches the original password input by the user
	 * @param type        The type of account the user wishes to create
	 * @param session     The current HttpSession, used to set an error message
	 *                    based on input by user
	 * @param accountType trainee-stream/trainer-expertise
	 * @return Index.jsp or Registration.jsp based on input received on the form
	 * @throws MessagingException 
	 */
	@RequestMapping(value = { "/RegisterAttempt" }, method = RequestMethod.POST)
	public ModelAndView RegisterAttempt(User user, SecurityAnswer securityAnswer, @RequestParam String confirm_pw,
			@RequestParam String type, @RequestParam String accountType) throws MessagingException {
		String errMsg = "";
		String succMsg = "";
		ModelAndView registrationView = new ModelAndView("registration.jsp");
		ModelAndView indexView = new ModelAndView("index.jsp");
		List<SecurityQuestion> questionArray = (List<SecurityQuestion>) securityQuestionRepo.findAll();
		registrationView.addObject("securityQuestion", questionArray);		
		registrationView.addObject("user", user);
		registrationView.addObject("accountType", accountType);
		registrationView.addObject("confirm_pw", confirm_pw);
		registrationView.addObject("securityAnswer", securityAnswer);
		
		//1.check password match
		if (user.getPassword().equals(confirm_pw)) {
			//2. check password match requirement
			if (!(user.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")
					&& user.getPassword().length() >= 6)) {
				errMsg = "Password must be longer or equals to 6 digits and include uppercase, lower case and number!";
				registrationView.addObject("errMsg", errMsg);
				return registrationView;
			}
			//3.check if email valid
			if (!emailValidation(user.getEmail(), user)) {
				errMsg = "Not a valid FDM email address!";
				registrationView.addObject("errMsg", errMsg);
				return registrationView;
			}
			
			List<User> foundUser = userRepo.findByUsername(user.getUsername());
			List<User> foundUserByEmail = userRepo.findByEmail(user.getEmail());
		    //This is successful registered 
			if (foundUser.size() == 0 && foundUserByEmail.size() == 0) {			
					Trainee newTrainee = new Trainee(user.getUsername(), user.getPassword(), user.getEmail(),
							user.getFirstname(), user.getLastname(), 1, accountType.toLowerCase());
					String generatedSecuredPasswordHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
					newTrainee.setPassword(generatedSecuredPasswordHash);
					
					traineeRepo.save(newTrainee);			
					smtpmailsender.send(user.getEmail(), "Testing", "Hey!!");
					securityAnswer.setUserId(newTrainee.getUserId());
					securityAnswerRepo.save(securityAnswer);			
					
					succMsg = "Registration Successful!";
					indexView.addObject("succMsg", succMsg);

			} else {
				//4.check if username/email already registered
				if (foundUser.size() > 0) {
					errMsg = "This username is already taken!";
				} else if (foundUserByEmail.size() > 0) {
					errMsg = "This email is already registered!";
				}
				registrationView.addObject("errMsg", errMsg);
				return registrationView;
			}
		} else {
			// 1. check if the password match
			errMsg = "The passwords don't match!";
			registrationView.addObject("errMsg", errMsg);
			return registrationView;
		}
		return indexView;
	}
	// email validation
	public boolean emailValidation(String email, User user) {
		String[] temp = email.split("@");
		if (temp.length != 2) {
			return false;
		} else if (!temp[1].equalsIgnoreCase("fdmgroup.com")) {
			return false;
		} else {
			String name = user.getFirstname() + "." + user.getLastname();
			if (!temp[0].toLowerCase().startsWith(name.toLowerCase())) {
				return false;
			}
		}
		return true;
	}
	@RequestMapping("/home")
	public ModelAndView home() {
		return new ModelAndView("home.jsp");
	}
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession currentSession) {
		currentSession.invalidate();
		ModelAndView mv = new ModelAndView("index.jsp");
		return mv;
	}
	@RequestMapping("/retrievePassword")
	public ModelAndView retrievePassword() {
		return new ModelAndView("retrievePassword.jsp");
	}
	@RequestMapping(value = { "/securityQuestion" }, method = RequestMethod.POST)
	public ModelAndView askSecurityQuestion(@RequestParam String email) {
		List<User> foundUser = userRepo.findByEmail(email);
		String errMsg = "";
		ModelAndView securityQuestionView = new ModelAndView("securityQuestion.jsp");
		ModelAndView retrievePasswordView =new ModelAndView("retrievePassword.jsp");
		if (foundUser.size() == 0) {
			errMsg = "User not exist!";
			retrievePasswordView.addObject("errMsg", errMsg);
			retrievePasswordView.addObject("email", email);
			return retrievePasswordView;
		} else {
			int userId = foundUser.get(0).getUserId();
			SecurityAnswer userSecurityAnswer = securityAnswerRepo.findById(userId).get();
			List<SecurityQuestion> userQuestions = new ArrayList<SecurityQuestion>();
			userQuestions.add(securityQuestionRepo.findById(userSecurityAnswer.getFirstSecurityQuestionId()).get());
			userQuestions.add(securityQuestionRepo.findById(userSecurityAnswer.getSecondSecurityQuestionId()).get());
			userQuestions.add(securityQuestionRepo.findById(userSecurityAnswer.getThirdSecurityQuestionId()).get());
			securityQuestionView.addObject("userQuestions", userQuestions);
			securityQuestionView.addObject("userId", userId);
		}
		return securityQuestionView;
	}
	@RequestMapping(value = { "/resetPasswordAttempt" }, method = RequestMethod.POST)
	public ModelAndView checkSecurityQuestion(@RequestParam String userId, @RequestParam String firstSecurityAnswer,
			@RequestParam String secondSecurityAnswer, @RequestParam String thirdSecurityAnswer) {
		
		SecurityAnswer userSecurityAnswer = securityAnswerRepo.findById(Integer.parseInt(userId)).get();
		User foundUser = userRepo.findById(Integer.parseInt(userId)).get();
		ModelAndView resetPasswordView = new ModelAndView("resetPassword.jsp");
		ModelAndView securityQuestionView = new ModelAndView("securityQuestion.jsp");

		if (userSecurityAnswer.getFirstSecurityAnswer().equalsIgnoreCase(firstSecurityAnswer)
				&& userSecurityAnswer.getSecondSecurityAnswer().equalsIgnoreCase(secondSecurityAnswer)
				&& userSecurityAnswer.getThirdSecurityAnswer().equalsIgnoreCase(thirdSecurityAnswer) ){			
			resetPasswordView.addObject("foundUser", foundUser);
			return resetPasswordView;
		} else {
			String errMsg = "Answers are not correct!";
			List<SecurityQuestion> userQuestions = new ArrayList<SecurityQuestion>();
			userQuestions.add(securityQuestionRepo.findById(userSecurityAnswer.getFirstSecurityQuestionId()).get());
			userQuestions.add(securityQuestionRepo.findById(userSecurityAnswer.getSecondSecurityQuestionId()).get());
			userQuestions.add(securityQuestionRepo.findById(userSecurityAnswer.getThirdSecurityQuestionId()).get());			
			securityQuestionView.addObject("userQuestions", userQuestions);
			securityQuestionView.addObject("firstSecurityAnswer", firstSecurityAnswer);
			securityQuestionView.addObject("secondSecurityAnswer", secondSecurityAnswer);
			securityQuestionView.addObject("thirdSecurityAnswer", thirdSecurityAnswer);
			securityQuestionView.addObject("userId", userId);
			securityQuestionView.addObject("errMsg", errMsg);
			return securityQuestionView;
		}
	}

	@RequestMapping(value = { "/resetPassword" }, method = RequestMethod.POST)
	public ModelAndView checkSecurityQuestion(@RequestParam String userId, @RequestParam String password,
			@RequestParam String confirm_pw) {
		String errMsg = "";
		User actionUser = userRepo.findById(Integer.parseInt(userId)).get();
		ModelAndView resetPasswordView = new ModelAndView("resetPassword.jsp");
		ModelAndView indexView = new ModelAndView("index.jsp");
		
		if (password.equals(confirm_pw)) {
			if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$") && password.length() >= 6) {
				
				String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
				actionUser.setPassword(generatedSecuredPasswordHash);
				userRepo.save(actionUser);
				String succMsg = "Password modified Secussfully!";			
				indexView.addObject("succMsg", succMsg);
				return indexView;
			}else {
				errMsg = "Password length must be longer than or equals to 6 and must include upper-case letters, lower-case letters and numbers!";
				resetPasswordView.addObject("errMsg", errMsg);
				resetPasswordView.addObject("foundUser", actionUser);
				return resetPasswordView;
			}
		}
		errMsg = "Two passwords do not match!";
		resetPasswordView.addObject("errMsg", errMsg);	
		resetPasswordView.addObject("foundUser", actionUser);
		return resetPasswordView;
	}
}
