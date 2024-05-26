import java.util.ArrayList;

public class Recruitment {

  //Initializing instance variables--
  private Role role;
  private String endDate;
  private ArrayList <Applicant> applicants;
  // Strings roleName, roleDescription, roleDepartment stored in role

  //Constructor for Recruitment class, both empty and complete--
  public Recruitment(){

  }

  public Recruitment(Role role, String endDate, ArrayList<Applicant> applicants){
    if(!isDateValid(endDate)){
      endDate = java.time.LocalDate.now().toString();
      System.out.println("Invalid date. Recruitment date set to today. (" + endDate + ")");
    }
    this.role = role;
    this.endDate = endDate;
    this.applicants = applicants;
  }

  private boolean isDateValid(String endDate){
    endDate = endDate.replaceAll("/|-", "");
    String  date = java.time.LocalDate.now().toString();
    date = date.replaceAll("-", "");
    if(Integer.parseInt(date) < Integer.parseInt(endDate)){
      return true;
    }else{
      return false;
    }
  }

  //Getters and Setters for Recruitment class--
  public Role getRole(){
    return role;
  }
  public void setRole(Role role){
    this.role = role;
  }

  public String getEndDate(){
    return endDate;
  }

  public void setEndDate(String endDate){
    this.endDate = endDate;
  }

  public ArrayList<Applicant> getApplicants(){
    return applicants;
  }

  public void setApplicants(ArrayList<Applicant> applicants){
    this.applicants = applicants;
  }

    //If the recruitment class needs to obtain the roleName, roleDescription, and roleDepartment of it's role, then it can use the following methods:
  public String getRoleName(){
    return role.getRoleName();
  }
  
  public String getRoleDescription(){
    return role.getRoleDescription();
  }
  
  public String getRoleDepartment(){
    return role.getDepartment();
  }
    //As of right now, I do not see a reason to have the recruiment class able to set any of these values. RETURN TO THIS LATER*****
  
  //To make Main's displayRecruitmentApplicants() simpler
  public void displayApplicantsList(){
    for(int i = 0; i < applicants.size(); i++){
      System.out.println(applicants.get(i).getName());
    }
  }
  

      
}