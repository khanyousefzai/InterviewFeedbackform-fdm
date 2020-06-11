package com.fdmgroup.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.dao.AccountManagerRepo;
import com.fdmgroup.dao.AdminRepo;
import com.fdmgroup.dao.ClientRepo;
import com.fdmgroup.dao.TraineeRepo;
import com.fdmgroup.dao.TrainerRepo;
import com.fdmgroup.dao.UserRepo;
import com.fdmgroup.model.AccountManager;
import com.fdmgroup.model.Admin;
import com.fdmgroup.model.Client;
import com.fdmgroup.model.Expertise;
import com.fdmgroup.model.Form;
import com.fdmgroup.model.Trainee;
import com.fdmgroup.model.Trainer;
import com.fdmgroup.model.User;

/**
 * handle admin functions
 * @author Simon Duan
 *
 */
@Controller
public class AdminController {
	@Autowired
	ClientRepo clientRepo;
	
	@Autowired
	TraineeRepo traineeRepo;
	
	@Autowired
	TrainerRepo trainerRepo;
	
	@Autowired
	AccountManagerRepo amRepo;
	
	@Autowired
	UserRepo uRepo;
	
	@Autowired
	AdminRepo adminRepo;
	
	@RequestMapping("/removePage")
	public ModelAndView removePage(HttpSession session) {
		session.setAttribute("err", "");
		session.setAttribute("succ", "");
		return new ModelAndView("adminRemove.jsp");
	}

	@RequestMapping("/resetPage")
	public ModelAndView resetPage(HttpSession session) {
		session.setAttribute("err", "");
		session.setAttribute("succ", "");
		return new ModelAndView("adminResetPassword.jsp");
	}
	
	@RequestMapping("/createUserPage")
	public ModelAndView createUserPage(HttpSession session) {
		session.setAttribute("err", "");
		session.setAttribute("succ", "");
		return new ModelAndView("adminCreateUser.jsp");
	}
	@RequestMapping("/createClientPage")
	public ModelAndView createClientPage(HttpSession session) {
		session.setAttribute("err", "");
		session.setAttribute("succ", "");
		List<AccountManager> am = new ArrayList<>();
		Iterable<AccountManager> amList = amRepo.findAll();
		amList.forEach((x) -> am.add(x));
		session.setAttribute("accountManagerList", am);
		
		return  new ModelAndView("adminCreateClient.jsp");
	}
	
	@RequestMapping("/removeuser")
	public ModelAndView removeClient(HttpSession session,@RequestParam String username) {
		ModelAndView m = new ModelAndView("adminRemove.jsp");
		Admin cur  = (Admin)session.getAttribute("user");
		
		
		String err = "";
		String succ = "";
		
		if(username.equals(cur.getUsername())) {
			err="You Cannot Remove Yourself!";
			succ = "";
			session.setAttribute("err", err);
			return m;
		}
		if(uRepo.findByUsername(username).size() == 0) {
			err = "User Not Found";
			succ = "";
		}else {
			User tmp = uRepo.findByUsername(username).get(0);
			String deletedUser = "";
			switch (tmp.getPermission()) {
			case 1:
				Trainee foundTrainee = traineeRepo.findByUsername(username).get(0);
				Trainee removed = traineeRepo.findByUsername("removed").get(0);
				List<Form> removedList = removed.getFormList();
				List<Form> fList = foundTrainee.getFormList();
				fList.forEach((x) -> {
					removedList.add(x);
					x.setAuthor(removed);
				});
				removed.setFormList(removedList);
				deletedUser = foundTrainee.getUsername();
				traineeRepo.delete(foundTrainee);
				err = "";
				succ = "The Trainee (" + deletedUser + ") Has Been Removed";
				break;
			case 2:
				Trainer foundTrainer = trainerRepo.findByUsername(username).get(0);
				foundTrainer.setExpertiseList(new ArrayList<>());
				deletedUser = foundTrainer.getUsername();
				trainerRepo.delete(foundTrainer);
				err = "";
				succ = "The Trainer (" + deletedUser + ") Has Been Removed";
				break;
			case 3:
				AccountManager foundAM = amRepo.findByUsername(username).get(0);
				List<Client> cList = foundAM.getClientList();
				cList.forEach((x) ->{
					List<AccountManager> amList = x.getAccountManagerList();
					for(int i=0;i<amList.size();i++) {
						if(amList.get(i).getUserId() == foundAM.getUserId()) {
							amList.remove(i);
							break;
						}
					}
				});
				foundAM.setClientList(cList);
				deletedUser = foundAM.getUsername();
				amRepo.delete(foundAM);
				err = "";
				succ = "An Account Manager (" + deletedUser + ") Has Been Removed";
				break;
			default:
				break;
			}
		}
		
		session.setAttribute("err", err);
		session.setAttribute("succ", succ);
		return m;
	}

	@RequestMapping("/resetuser")
	public ModelAndView resetUser(HttpSession session,@RequestParam String username) { 
		ModelAndView m = new ModelAndView("adminResetPassword.jsp");
		
		String err = "";
		String succ = "";
		
		session.setAttribute("err", err);
		session.setAttribute("succ", succ);
		return m;
		
	}

	@RequestMapping("/removeclient")
	public ModelAndView removeUser(HttpSession session, @RequestParam String clientName) {
		
		ModelAndView m = new ModelAndView("adminRemove.jsp");
		List<Client> foundClient = clientRepo.findByClientNameIgnoreCase(clientName);
	
		String err = "";
		String succ = "";
		
		if(foundClient.size() == 0) {
			System.out.println("Client Not Found!");
			err = "Client Not Found!";
			succ = "";
		} else {
			Client found = foundClient.get(0);
			List<AccountManager> amList = found.getAccountManagerList();
			amList.forEach((x) -> {
				List<Client> cList = x.getClientList();
				for (int i = 0; i < cList.size(); i++) {
					if(cList.get(i).getClientId() == found.getClientId()) {
						cList.remove(i);
						break;
					}
				}
			});
			
			found.setAccountManagerList(new ArrayList<AccountManager>());
			String deletedClient = found.getClientName();
			clientRepo.delete(found);
			System.out.println("removed");
			err = "";
			succ = "The Client (" + deletedClient + ") Has Been Removed";
		}
		session.setAttribute("err", err);
		session.setAttribute("succ", succ);
		return m;
	}

	@RequestMapping("/adminCreateUser")
	public ModelAndView adminCreateUser(HttpSession session, @RequestParam String username, @RequestParam String password, @RequestParam String password_c, 
			@RequestParam String firstname, @RequestParam String lastname, @RequestParam String email, @RequestParam String type) {
		ModelAndView m = new ModelAndView("adminCreateUser.jsp");
		
		String err = "";
		String succ = "";
		
		if(uRepo.findByUsername(username).size() !=0 ) {
			err = "This Username Already Exists!";
			succ = "";
			session.setAttribute("err", err);
			session.setAttribute("succ", succ);
			return m;
		}
		if(uRepo.findByEmail(email).size() !=0 ) {
			err = "This Email Has Already Been Used!";
			succ = "";
			session.setAttribute("err", err);
			session.setAttribute("succ", succ);
			return m;
		}
		if(!password.equals(password_c)) {
			err = "The Two Passwords Do NOT Match!";
			succ = "";
			session.setAttribute("err", err);
			session.setAttribute("succ", succ);
			return m;
		}
		
		if(!(password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$") && password.length() >= 6)) {
			err = "The Password Must Be Longer Than Or Equal To 6 Characters And Include An Uppercase, A Lower Case And A Number";
			succ = "";
			session.setAttribute("err", err);
			session.setAttribute("succ", succ);
			return m;
		}
		
		if(!emailValidation(email, firstname, lastname)) {
			err = "This Is NOT A Valid FDM Email Address!";
			succ = "";
			session.setAttribute("err", err);
			session.setAttribute("succ", succ);
			return m;
		}
		
		
		switch (type) {
		case "Trainee":
			Trainee newTrainee = new Trainee(username, password, email, firstname, lastname, 1, "");
			String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
			newTrainee.setPassword(generatedSecuredPasswordHash);
			traineeRepo.save(newTrainee);
			succ = "A Trainee (" + username + ") Has Been Created";
			err = "";
			break;
			
		case "Trainer":
			Trainer newTrainer = new Trainer(username, password, email, firstname, lastname, 2);
			generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
			newTrainer.setPassword(generatedSecuredPasswordHash);
			trainerRepo.save(newTrainer);
			succ = "A Trainer (" + username + ") Has Been Created";
			err = "";
			break;
			
		case "AccountManager":
			AccountManager newAM = new AccountManager(username, password, email, firstname, lastname, 3);
			generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
			newAM.setPassword(generatedSecuredPasswordHash);
			amRepo.save(newAM);
			succ = "An Account Manager (" + username + ") Has Been Created";
			err = "";
			break;
		case "Admin":
			Admin newAdmin = new Admin(username, password, email, firstname, lastname, 9);
			generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
			newAdmin.setPassword(generatedSecuredPasswordHash);
			adminRepo.save(newAdmin);
			succ = "An Admin (" + username + ") Account Has Been Created";
			err = "";
			break;
		default:
			break;
		}
	
		session.setAttribute("err", err);
		session.setAttribute("succ", succ);

		return m;

	}
	
	@RequestMapping("/adminCreateClient")
	
	public ModelAndView createClient(HttpSession session, @RequestParam String clientName, @RequestParam List<String> accountManagerList) {
		
		ModelAndView m = new ModelAndView("adminCreateClient.jsp");
		String err = "";
		String succ = "";
		if(clientName.equals("")) {
			err = "Please Enter A Name!";
			succ = "";
			session.setAttribute("err", err);
			session.setAttribute("succ", succ);
			return m;
		}
		
		Client newClient = new Client(clientName);
		
		if(clientRepo.findByClientName(clientName).size() !=0 ) {
			err = "This Client Name Already Exists!";
		} else {
			
			List<AccountManager> tmpList = new ArrayList<AccountManager>();
			accountManagerList.forEach((x) -> {
				AccountManager tmpAm = amRepo.findById(Integer.valueOf(x)).get();
				List<Client> cl = tmpAm.getClientList();
				cl.add(newClient);
				tmpAm.setClientList(cl);
				tmpList.add(tmpAm);
			});
			
			
			newClient.setAccountManagerList(tmpList);
			clientRepo.save(newClient);
			succ = "The Client (" + newClient.getClientName()  + ") Has Been Created";
			err = "";
		}
		
		session.setAttribute("succ", succ);
		session.setAttribute("err", err);
		
		return m;
		
	}
	
	public boolean emailValidation(String email, String firstname, String lastname) {
		
		String[] temp = email.split("@");
		
		if(temp.length != 2 ) {
			return false;
		}else if (!temp[1].equalsIgnoreCase("fdmgroup.com")) {
			return false;
		}else {
			String name = firstname + "." + lastname;
			if(!temp[0].toLowerCase().startsWith(name.toLowerCase())) {
				return false;
			}
		}
		
		
		return true;
	}
}
