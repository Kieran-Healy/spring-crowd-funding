package ie.kieran.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue
    int userId;
	
	@Column
	@NotNull
	@Email
	String userEmail;
	
	@Column
	@Size(min=8)
	String userPassword;
	
	@OneToMany(mappedBy="founder", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Project> projects; 
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Pledge> pledges;
	
	@OneToOne
	@JoinColumn(name = "roleEmail", nullable = false)
	Role userRole;
	
	@Column
	boolean userEnabled;

	public User() {
		
	}
	
	public User(String userEmail, String userPassword, Role userRole, boolean userEnabled) {
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userRole = userRole;
		this.userEnabled = userEnabled;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public List<Pledge> getPledges() {
		return pledges;
	}
	
	public void setRole(Role role) {
		this.userRole = role;
	}

	public void setPledges(List<Pledge> pledged) {
		this.pledges = pledged;
	}

	public String getUserEmail() {
		return userEmail;
	}
	

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public void setUserEnabled(boolean userEnabled) {
		this.userEnabled = userEnabled;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userEmail=" + userEmail + ", userPassword=" + userPassword + ", userRole="
				+ userRole + ", userEnabled=" + userEnabled + "]";
	}

	

}
