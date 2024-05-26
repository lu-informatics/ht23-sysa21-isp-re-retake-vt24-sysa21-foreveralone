public class Role {
  
  //Initializing instance variables--
  private String roleName;
  private String department;
  private String roleDescription;

  //Constructors for Role class, both empty and complete--
  public Role(){
    
  }

  public Role(String roleName, String roleDescription, String department){
    this.roleName = roleName;
    this.roleDescription = roleDescription;
    this.department = department;
  }

  //Getters and Setters for Role class--
  public String getRoleName(){
    return roleName;
  }

  public void setRoleName(String roleName){
    this.roleName = roleName;
  }

  public String getRoleDescription(){
    return roleDescription;
  }

  public void setRoleDescription(String roleDescription){
    this.roleDescription = roleDescription;
  }

  public String getDepartment(){
    return department;
  }

  public void setDepartment(String department){
    this.department = department;
  }

  //To make Main's addRole() & updateRoleDescription() simpler
  public void displayRoleInfo(){
    System.out.println("Role Name: " + roleName + "\nDepartment: " + department + "\nRole Description: " + roleDescription);
  }

}