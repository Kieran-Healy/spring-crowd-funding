package ie.kieran.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role {
	
	@Id
	String userEmail;
	
	@Column
	String roleDescription;
	
	public Role() {
		
	}
	
	public Role(String userEmail, String roleDescription) {
		this.userEmail = userEmail;
		this.roleDescription = roleDescription;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@Override
	public String toString() {
		return "Role [userEmail=" + userEmail + ", roleDescription=" + roleDescription + "]";
	}

}
