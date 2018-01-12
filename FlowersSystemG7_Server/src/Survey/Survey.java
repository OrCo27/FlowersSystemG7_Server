package Survey;

import java.io.Serializable;
import java.util.ArrayList;

import Branches.CustomerService;

public class Survey implements Serializable {
	private int id;
	private String subject;
	private int creatorId;
	private boolean isActive;
	private ArrayList<SurveyBranch> surveyBranchList;
	private ArrayList<SurveyQuestion> surveyQuestionList;

	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	public ArrayList<SurveyBranch> getSurveyBranchList() {
		return surveyBranchList;
	}
	public void setSurveyBranchList(ArrayList<SurveyBranch> surveyBranchList) {
		this.surveyBranchList = surveyBranchList;
	}
	public ArrayList<SurveyQuestion> getSurveyQuestionList() {
		return surveyQuestionList;
	}
	public void setSurveyQuestionList(ArrayList<SurveyQuestion> surveyQuestionList) {
		this.surveyQuestionList = surveyQuestionList;
	}
	public Survey(int id, String subject, int creatorId,boolean isActive) {
	
		this.id = id;
		this.subject = subject;
		this.creatorId = creatorId;
	} 
	
	public Survey(String subject, int creatorId,boolean isActive) {
		this.subject = subject;
		this.creatorId = creatorId;
	} 
	
}
