package com.fdmgroup.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "FDMUSER")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "UserType", discriminatorType = DiscriminatorType.STRING)
public class User {

	@Id
	@Column(name = "U_ID")
	@SequenceGenerator(name = "U_Seq", sequenceName = "SEQ_U",  initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "U_Seq")
	private int userId;
	
	@Column(name = "U_USERNAME")
	private String username;
	
	@Column(name = "U_PASSWORD")
	private String password;
	
	@Column(name="U_EMAIL")
	private String email;
	
	@Column(name = "U_FIRSTNAME")
	private String firstname;
	
	@Column(name = "U_LASTNAME")
	private String lastname;

	@Column(name = "U_PERMISSION")
	private int permission;
	
	@OneToOne(mappedBy="user")
	private QuestionsGroup questionsgroup;

	@ManyToMany(mappedBy="userLiked")
	private List<Question> likedQuestionList;
	
	@ManyToMany(mappedBy="userDisliked")
	private List<Question> dislikedQuestionList;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String email, String firstname, String lastname, int permission) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.permission = permission;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getPermission() {
		return permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public int getUserId() {
		return userId;
	}

	public List<Question> getLikedQuestionList() {
		return likedQuestionList;
	}

	public void setLikedQuestionList(List<Question> likedQuestionList) {
		this.likedQuestionList = likedQuestionList;
	}

	public List<Question> getDislikedQuestionList() {
		return dislikedQuestionList;
	}

	public void setDislikedQuestionList(List<Question> dislikedQuestionList) {
		this.dislikedQuestionList = dislikedQuestionList;
	}

	public QuestionsGroup getQuestiongroup() {
		return questionsgroup;
	}

	public void setQuestiongroup(QuestionsGroup questiongroup) {
		this.questionsgroup = questiongroup;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", firstname=" + firstname + ", lastname=" + lastname + ", permission=" + permission + "]";
	}

	
	
	
	
}
