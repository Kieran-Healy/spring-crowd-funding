package ie.kieran.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int projectId;
	
	@Column(nullable=false, unique=true)
	private String projectName;
	
	@Column(nullable=false, unique=true)
	private String projectDesc;
	
	@Column(nullable=false)
	private double projectTarget;
	
	@Column
	private double currentAmount = 0.0;
	
	@Column
	private boolean enabled;
	
	@Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private Date startDate;
	
	@ManyToOne
	private User founder;

	@OneToMany(mappedBy="project", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Pledge> pledge;

	public Project() {
		
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public User getFounder() {
		return founder;
	}

	public void setFounder(User founder) {
		this.founder = founder;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean getEnabled() {
		return this.enabled;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public double getProjectTarget() {
		return projectTarget;
	}

	public void setProjectTarget(double projectTarget) {
		this.projectTarget = projectTarget;
	}

	public double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}
	
	public List<Pledge> getPledge() {
		return pledge;
	}

	public void setPledge(List<Pledge> pledges) {
		this.pledge = pledges;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", projectDesc=" + projectDesc
				+ ", projectTarget=" + projectTarget + ", currentAmount=" + currentAmount + ", enabled=" + enabled
				+ ", startDate=" + startDate + ", founder=" + founder + "]";
	}


}
