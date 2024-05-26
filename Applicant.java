import java.util.ArrayList;

public class Applicant {

  //Initializing instance variables--
  private String name;
  private ArrayList<Role> roleAppHistory; 

  //Constructors for the Applicant class, both empty and complete
  public Applicant(){

  }

  public Applicant(String name, ArrayList<Role> roleAppHistory){
    this.name = name;
    this.roleAppHistory = roleAppHistory;
  }

  //Getter and Setters for the Applicant class--
  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name = name;
  }

  public ArrayList<Role> getRoleAppHistory(){
    return roleAppHistory;
  }

  public void setRoleAppHistory(ArrayList<Role> roleAppHistory){
    this.roleAppHistory = roleAppHistory;
  }

  //to make Main's displayApplicantInfo() simpler
  public void displayRoleAppHistory(){
    for (int i = 0; i < roleAppHistory.size(); i++){
      roleAppHistory.get(i).displayRoleInfo();
    }
  }
  
}