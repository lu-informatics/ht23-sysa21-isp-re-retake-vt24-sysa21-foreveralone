public class Interview{

  //Initializing instance variables--
  private Recruitment recruitment;
  // Role role stored in recruitment
  private Applicant applicant;
  private String date;
  private String location;

  //Constructor for Interview class, both empty and complete--
  public Interview(){

  }

  public Interview(Recruitment recruitment, Applicant applicant, String date, String location){
    this.recruitment = recruitment;
    this.applicant = applicant;
    this.date = date;
    this.location = location;
  }

  //Getters and Setters for Interview class--
  public Recruitment getRecruitment(){
    return recruitment;
  }

  public void setRecruitment(Recruitment recruitment){
    this.recruitment = recruitment;
  }

  public Applicant getApplicant(){
    return applicant;
  }

  public void setApplicant(Applicant applicant){
    this.applicant = applicant;
  }

  public String getDate(){
    return date;
  }

  public void setDate(String date){
    this.date = date;
  }

  public String getLocation(){
    return location;
  }

  public void setLocation(String location){
    this.location = location;
  }

    //If the interview class needs to obtain the role it's recruitment refers to, it can use the following method:
  public Role getRole(){
    if(recruitment != null) {
      return recruitment.getRole();
    } else {
      return null;  // or handle this case as needed in your application
    }
  }
  //As of right now, I do not see a reason to have the interview class able to set the role of the recuitment. RETURN TO THIS LATER*****

  //To make Main's displayInterviewSchedule() simpler
  public void displayInterviewInfo(){
    System.out.println("Interview for " + recruitment.getRole().getRoleName() + "role with applicant " + applicant.getName() + " is scheduled for " + date + " at " + location + "." );
  }
  
}