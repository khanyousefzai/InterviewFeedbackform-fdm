package com.fdmgroup.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.dao.AccountManagerRepo;
import com.fdmgroup.dao.ClientRepo;
import com.fdmgroup.dao.FormRepo;
import com.fdmgroup.dao.QuestionRepo;
import com.fdmgroup.dao.TagRepo;
import com.fdmgroup.dao.TraineeRepo;
import com.fdmgroup.dao.UserRepo;
import com.fdmgroup.model.AccountManager;
import com.fdmgroup.model.Client;
import com.fdmgroup.model.Form;
import com.fdmgroup.model.Question;
import com.fdmgroup.model.Tag;
import com.fdmgroup.model.Trainee;
import com.fdmgroup.model.User;

/**
 * Handles User Form Submission and used to View forms
 * @author Simon D, Daniel P, Ibtisam C
 *
 */
@Controller
public class FormController {

	@Autowired
	QuestionRepo questionRepo;

	@Autowired
	FormRepo formRepo;

	@Autowired
	ClientRepo clientRepo;

	@Autowired
	TagRepo tagRepo;
	
	@Autowired
	UserRepo userRepo;

	@Autowired
	TraineeRepo traineeRepo;

	@Autowired
	AccountManagerRepo accountManagerRepo;
	
	
	/**
	 * Used to display a new form to the trainee
	 * @param session current session
	 * @return redirect to form page and return an empty form
	 */
	@RequestMapping(value = "/newForm", method = RequestMethod.POST)
	public ModelAndView Home(HttpSession session) {

		Form newForm = new Form();
		List<String> amList = new ArrayList<>();
		List<Client> cList = new ArrayList<>();
		Iterable<AccountManager> tmp = accountManagerRepo.findAll();
		Iterable<Client> tmpc= clientRepo.findAll();
		tmp.forEach((x)-> amList.add(x.getFirstname()));
		tmpc.forEach((x) -> cList.add(x));

		ModelAndView formView = new ModelAndView("form.jsp");
		formView.addObject("form", newForm);
		formView.addObject("amList", amList);
		formView.addObject("cList", cList);
		return formView;

	}

	//Post/Redirect/Get pattern
	@RequestMapping(value = "/submitForm", method = RequestMethod.GET)
	public String handler() {
		return "home.jsp";
	}

	//Like/dislike functionality
	@RequestMapping(value= "likeQuestion", method = RequestMethod.POST)
	public String submit(@ModelAttribute("currentForm")Form form, @RequestParam String formId,@RequestParam String[] status, HttpSession session) {
		List<Question> question = form.getQuestionList();
		System.out.println(question);
		Form currentForm = formRepo.findById(Integer.parseInt(formId));
		
		User curr = (User)session.getAttribute("user");
		
		User current = userRepo.findById(curr.getUserId()).get();
		
		/*
		 * status:
			1:User hasn't liked nor disliked the question before but liked the question
			2:User liked the question before, unliked the question
			3:User disliked the question before, liked the question
			4:User hasn't liked nor disliked the question before but disliked the question
			5:User disliked the question before, un-disliked the question
			6:User liked the question before, disliked the question
			*/
		for(int i = 0; i < question.size();i++) {
			Question updatedQuestion = questionRepo.findById(question.get(i).getQuestionId());
			
			System.out.println(status[i]);
			switch(status[i]) {
			  case "1":
				  List<User> newLikeList = new ArrayList<User>(); 
				  newLikeList.add(current);
				  updatedQuestion.setUserLiked(newLikeList);
				  updatedQuestion.setLikes(question.get(i).getLikes());
			    // code block
			    break;
			  case "2":
				  List<User> likeList = updatedQuestion.getUserLiked(); 
				  likeList.remove(current);
				  updatedQuestion.setUserLiked(likeList);
				  updatedQuestion.setLikes(question.get(i).getLikes());
			    // code block
			    break;
			  case "3":
				  if(question != null && question.get(i).getUserLiked() != null) {
					  List<User> likeList3 = question.get(i).getUserLiked(); 
					  likeList3.add(current);
					  if(question.get(i).getUserDisliked()!= null) {
						  List<User> dislikeList = updatedQuestion.getUserDisliked();
						  dislikeList.remove(current);
						  updatedQuestion.setUserDisliked(dislikeList);
						  updatedQuestion.setDislikes(question.get(i).getLikes());
					  }
					  updatedQuestion.setUserLiked(likeList3);
					  updatedQuestion.setLikes(question.get(i).getLikes());
				  }
				    // code block
				    break;
			  case "4":
				  List<User> newDislikeList = new ArrayList<User>(); 
				  newDislikeList.add(current);
				  updatedQuestion.setUserDisliked(newDislikeList);
				  System.out.println(question.get(i).getDislikes());
				  updatedQuestion.setDislikes(question.get(i).getDislikes());
				    // code block
				  break;
			  case "5":
				  List<User> dislikeList4 = updatedQuestion.getUserDisliked(); 
				  dislikeList4.remove(current);
				  updatedQuestion.setUserDisliked(dislikeList4);
				  updatedQuestion.setDislikes(question.get(i).getDislikes());
				    // code block
				    break;
			  case "6":
				  List<User> dislikeList2 = question.get(i).getUserDisliked(); 
				  dislikeList2.add(current);
				  if(question.get(i).getUserLiked()!= null) {
					  List<User> likeList2 = updatedQuestion.getUserLiked();
					  likeList2.remove(current);
					  updatedQuestion.setUserDisliked(likeList2);
					  updatedQuestion.setDislikes(question.get(i).getDislikes());
				  }
				  updatedQuestion.setUserLiked(dislikeList2);
				  updatedQuestion.setLikes(question.get(i).getDislikes());
				    // code block
				    break;
			  case "7":
				  break;
			  default:
				  if(question.get(i).getUserLiked()!= null) {
					  List<User> likeList4 = updatedQuestion.getUserLiked();
					  likeList4.remove(current);
					  updatedQuestion.setUserDisliked(likeList4);
					  updatedQuestion.setDislikes(question.get(i).getDislikes());
				  }
				  if(question.get(i).getUserDisliked()!= null) {
					  List<User> dislikeList3 = updatedQuestion.getUserDisliked();
					  dislikeList3.remove(current);
					  updatedQuestion.setUserDisliked(dislikeList3);
					  updatedQuestion.setDislikes(question.get(i).getLikes());
				  }
			    // code block
			}
			  updatedQuestion.setForm(currentForm); 
			  questionRepo.save(updatedQuestion);
		}
		  currentForm.setQuestionList(question); 
		  formRepo.save(currentForm);

		session.setAttribute("user", curr);
		
		switch ((String)session.getAttribute("type")) {
		case "trainee":
			return "home.jsp";
		case "trainer":
			return "trainerHome.jsp";
		case "accountManager":
			return "accountManagerHome.jsp";
		default:
			return "index.jsp";
		}
		
	}


	/**
	 * Used to submit the form and add those Details to the database
	 * @param trainee current trainee
	 * @param form form created
	 * @param session current session
	 * @param qbody array of string of question body
	 * @param insideTag array of tags for different question separated by integer
	 * @param clientName client name
	 * @return
	 */
	@RequestMapping(value = "/submitForm", method = RequestMethod.POST)
	public String submitForm(@Valid@ModelAttribute("user")Trainee trainee, @Valid@ModelAttribute("form")Form form, HttpSession session,
			@RequestParam String[] qbody, @RequestParam String[] insideTag , @RequestParam String clientName) {
		List<Question> questions = new ArrayList<Question>();
		//setup tags and questions.
		List<List<String>> tagName = parseStringArr(insideTag);

		for(int i = 0; i<qbody.length;i++) {
			Question q = new Question(qbody[i]);
			List<Tag> tagList = new ArrayList<>();
			for(int j = 0; j<tagName.get(i).size();j++) {
				Tag tmp = new Tag(tagName.get(i).get(j));
				tagList.add(tmp);
			}
			q.setTagList(tagList);

			questions.add(q);

		}
		//Setting the Form for each question answered
		for(Question q : questions) {
			q.setForm(form);
		}

		//set up client.
		List<Client> cFound = clientRepo.findByClientNameIgnoreCase(clientName);
		Client c = new Client(clientName);
		List<Form> formList = new ArrayList<>();
		if(cFound.size() != 0) {
			c = cFound.get(0);
			formList = c.getFormList();
			formList.add(form);
			c.setFormList(formList);
		}else {
				
			formList.add(form);
			c.setFormList(formList);
		
			clientRepo.save(c);
		}
		form.setClient(c);
		
		
		
		
				
		Trainee curr = (Trainee)session.getAttribute("user");
		form.setFdmStream(curr.getStream());
		form.setQuestionList(questions);
		//submit
		if(curr!=null) {
			//set up author with a managed instance
			Trainee found = traineeRepo.findById(curr.getUserId()).get();
			form.setAuthor(found);

			//Mananged tags
			for(int i=0;i<form.getQuestionList().size();i++) {
				for(int j=0;j<form.getQuestionList().get(i).getTagList().size();j++) {
					String tempTag = form.getQuestionList().get(i).getTagList().get(j).getTagName();

					if(tagRepo.findByTagNameIgnoreCase(tempTag).size() !=0 ) {
						Tag foundTag = tagRepo.findByTagNameIgnoreCase(tempTag).get(0);
						form.getQuestionList().get(i).getTagList().set(j, foundTag);
						form.getQuestionList().get(i).getTagList().get(j).getQuestionList().add(form.getQuestionList().get(i));
					}else {
						Tag newTag = new Tag(tempTag);
						newTag.setQuestionList(new ArrayList<Question>());
						tagRepo.save(newTag);
						Tag foundTag = tagRepo.findByTagNameIgnoreCase(tempTag).get(0);
						form.getQuestionList().get(i).getTagList().set(j, foundTag);
						form.getQuestionList().get(i).getTagList().get(j).getQuestionList().add(form.getQuestionList().get(i));
					}
				}
			}
			//save form
			java.util.Date d1 = new java.util.Date();
			form.setFormCreated(new java.sql.Date(d1.getTime()));
			formRepo.save(form);
			for(Question q : questions) {
				questionRepo.save(q);
			}
			//update user in session
			List<Form> foundUserForm = formRepo.findByAuthorOrderByInterviewDateDesc(curr);
			session.setAttribute("userForms", foundUserForm);
			session.setAttribute("user", curr);

		}
		return "redirect:/submitForm";
	}
	
	//utility method to parse tag list to 2d array.
	public List<List<String>> parseStringArr(String[] strArr){
		boolean newTagList = true;
		List<List<String>> res = new ArrayList<>();
		List<String> temp = new ArrayList<>();
		for(String s : strArr) {
			if(newTagList) {
				if(isInt(s)) {
					continue;
				}
				newTagList = false;
				temp = new ArrayList<>();
				temp.add(s);
			}else {
				if(isInt(s)) {
					res.add(temp);
					newTagList = true;
					continue;
				}else {
					temp.add(s);
				}
			}

		}
		return res;
	}
	//utility method check if a string is integer.
	public boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch(Exception e) {
			return false;
		}
		return true;
	}

}
