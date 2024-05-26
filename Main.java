import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.Scanner;
import java.util.ArrayList;

public class Main extends Application
{ 
  //Initializing instance variables---------
  private static ArrayList<Interview> interviewSchedule = new ArrayList<Interview>();
  private static ArrayList<Recruitment> currentRecruitments = new ArrayList<Recruitment>();
  private static ArrayList<Role> currentJobRoles = new ArrayList<Role>();
  private static Scanner sc = new Scanner(System.in);

  //Initiating the GUI---------------------
  @Override
    public void start(Stage primaryStage) {
        Label label; 
          TextField tf;
          Button buttonInterviewScene;
          Button buttonRecruitmentScene;
          Button buttonRoleScene;
          VBox vbox;
          Scene scene;

          TableView<String> tableView = new TableView<>();


          tf = new TextField("Text Field!");
          tf.setMaxWidth(200);

          label = new Label("Type text and click the button");
          buttonInterviewScene = new Button("Interview Scene"); 

          buttonRecruitmentScene = new Button("Recruitment Scene"); 

          buttonRoleScene = new Button("Role Scene"); 

          //INTERVIEW SCENE-----when button is clicked, open to Interview scene
          buttonInterviewScene.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent e) {
                  Stage newStage = new Stage();
                  Label newLabel = new Label("New Scene!");
                  VBox newVBox = new VBox(newLabel);
                  newVBox.setAlignment(Pos.CENTER);
                  Scene newScene = new Scene(newVBox, 300, 200);
                  newStage.setTitle("Interview Scene");


                  TableView<Interview> tableView = new TableView<>();
                  ObservableList<Interview> data = FXCollections.observableArrayList(interviewSchedule);

                  TableColumn<Interview, String> roleNameColumn = new TableColumn<>("Role Name");
                  roleNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRecruitment().getRole().getRoleName()));
                  roleNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                  roleNameColumn.setOnEditCommit((CellEditEvent<Interview, String> event) -> {
                      TableColumn<Interview, String> col = event.getTableColumn();
                      Interview interview = event.getRowValue();
                      interview.getRecruitment().getRole().setRoleName(event.getNewValue());
                  });

                  TableColumn<Interview, String> applicantNameColumn = new TableColumn<>("Applicant Name");
                  applicantNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApplicant().getName()));
                  applicantNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                  applicantNameColumn.setOnEditCommit(event -> {
                      Interview interview = event.getRowValue();
                      interview.getApplicant().setName(event.getNewValue());
                  });
                  
                  

                  TableColumn<Interview, String> dateColumn = new TableColumn<>("Date");
                  dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));

                  TableColumn<Interview, String> locationColumn = new TableColumn<>("Location");
                  locationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocation()));

                  // Add columns to the TableView
                  tableView.getColumns().addAll(roleNameColumn, applicantNameColumn, dateColumn, locationColumn);

                  //Create Buttons to Add & Delete Roles in Table View
                  Button addButton = new Button("Add");
                  addButton.setOnAction(new EventHandler<ActionEvent>() {
                      @Override
                      public void handle(ActionEvent e) {
                          // Add code to handle the Add button click event
                          // For example, you can open a new window or perform an action
                         //Create "empty" objects for the new Interview object to contain
                          Role newRole = new Role("Insert Role", "Insert Role Description Here", "Insert Department Here");
                          Recruitment newRecruitment = new Recruitment(newRole, java.time.LocalDate.now().toString(), new ArrayList<Applicant>());
                          Applicant newApplicant = new Applicant("John Doe", new ArrayList<>());
                          Interview newInterview = new Interview( newRecruitment, newApplicant, "New Date", "New Location");
                          interviewSchedule.add(newInterview);
                          tableView.getItems().add(newInterview);
                      }
                  });

                  Button delButton = new Button("Delete");
                  delButton.setOnAction(new EventHandler<ActionEvent>() {
                      @Override
                      public void handle(ActionEvent e) {
                          // Add code to handle the Delete button click event
                          // For example, you can open a new window or perform an action
                          Interview selectedInterview = tableView.getSelectionModel().getSelectedItem();
                          if (selectedInterview != null) {
                              interviewSchedule.remove(selectedInterview);
                              tableView.setItems(FXCollections.observableArrayList(interviewSchedule));
                          }
                      }
                  });

                  HBox hbox = new HBox();
                  hbox.getChildren().addAll(addButton, delButton);
                
                  tableView.setItems(data);

                newStage.setScene(newScene);
                newVBox.getChildren().add(tableView);
                newVBox.getChildren().add(hbox);


                newStage.show();
              }
          });

          //RECRUITMENT SCENE----------when button is clicked, open to Recruitment scene
          buttonRecruitmentScene.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent e) {
                  Stage newStage = new Stage();
                  Label newLabel = new Label("New Scene!");
                  VBox newVBox = new VBox(newLabel);
                  Scene newScene = new Scene(newVBox, 300, 200);
                  newVBox.setAlignment(Pos.CENTER);
                  newStage.setTitle("Recruitment Scene");

                  TableView<String> tableView = new TableView<>();
                  //ObservableList<Recruitment> data = FXCollections.observableArrayList(currentRecruitments);
                  TableColumn<String, String> roleNameColumn = new TableColumn<>("Role Name");
                  //roleNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRole().getRoleName()));
                  TableColumn<String, String> endDateColumn = new TableColumn<>("End Date");
                  //endDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEndDate()));
                  TableColumn<String, String> applicantsColumn = new TableColumn<>("Applicants");
                  //applicantsColumn.setCellValueFactory(cellData -> {
                  //    StringBuilder applicants = new StringBuilder();
                  //    for (Applicant applicant : cellData.getValue().getApplicants()) {
                  //        applicants.append(applicant.getName()).append(", ");
                  //    }
                  //    return new SimpleStringProperty(applicants.toString());
                  //});
                  // Add columns to the TableView
                  tableView.getColumns().addAll(roleNameColumn, endDateColumn, applicantsColumn);
                  //tableView.setItems(data);

                  Button button1 = new Button("Button 1");
                  Button button2 = new Button("Button 2");
                  Button button3 = new Button("Button 3");



                  newStage.setScene(newScene);

                  newVBox.getChildren().add(tableView);
                  newVBox.getChildren().add(button1);
                  newVBox.getChildren().add(button2);
                  newVBox.getChildren().add(button3);

                  newStage.show();
              }
          });

          //ROLE SCENE---------when button is clicked, open to Role scene
          buttonRoleScene.setOnAction(new EventHandler<ActionEvent>() {
              @Override
              public void handle(ActionEvent e) {
                  Stage newStage = new Stage();
                  Label newLabel = new Label("New Scene!");
                  VBox newVBox = new VBox(newLabel);
                  newVBox.setAlignment(Pos.CENTER);
                  Scene newScene = new Scene(newVBox, 500, 400);


                  // Create TableView
                  TableView<Role> tableView = new TableView<>();

                  // Enable editing for TableView
                  tableView.setEditable(true);

                  // Create columns
                  TableColumn<Role, String> roleNameColumn = new TableColumn<>("Role Name");
                  roleNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRoleName()));
                  roleNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                  roleNameColumn.setOnEditCommit(event -> {
                      Role role = event.getRowValue(); // Get the role associated with the edited row
                      role.setRoleName(event.getNewValue()); // Update the role name with the new value   
                  });

                  TableColumn<Role, String> departmentColumn = new TableColumn<>("Department");
                  departmentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartment()));
                  departmentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                  departmentColumn.setOnEditCommit(event -> {
                      Role role = event.getRowValue(); // Get the role associated with the edited row
                      role.setDepartment(event.getNewValue()); // Update the department with the new value
                  });

                  TableColumn<Role, String> descriptionColumn = new TableColumn<>("Description");
                  descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRoleDescription()));
                  descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                  descriptionColumn.setOnEditCommit(event -> {
                      Role role = event.getRowValue(); // Get the role associated with the edited row
                      role.setRoleDescription(event.getNewValue()); // Update the role description with the new value
                  });

                  // Add columns to the TableView
                  tableView.getColumns().addAll(roleNameColumn, departmentColumn, descriptionColumn);

                  // Create data
                  ObservableList<Role> data = FXCollections.observableArrayList(currentJobRoles);

                  // Set the data to be displayed in the TableView
                  tableView.setItems(data);



                  //Create Buttons to Add & Delete Roles in Table View
                  Button addButton = new Button("Add");
                  addButton.setOnAction(new EventHandler<ActionEvent>() {
                      @Override
                      public void handle(ActionEvent e) {
                          // Add code to handle the Add button click event
                          // For example, you can open a new window or perform an action
                          Role newRole = new Role("New Role", "New Department", "New Description");
                          currentJobRoles.add(newRole);
                          tableView.getItems().add(newRole);
                      }
                  });

                  Button delButton = new Button("Delete");
                  delButton.setOnAction(new EventHandler<ActionEvent>() {
                      @Override
                      public void handle(ActionEvent e) {
                          // Add code to handle the Delete button click event
                          // For example, you can open a new window or perform an action
                          Role selectedRole = tableView.getSelectionModel().getSelectedItem();
                          if (selectedRole != null) {
                              currentJobRoles.remove(selectedRole);
                              tableView.setItems(FXCollections.observableArrayList(currentJobRoles));
                          }
                      }
                  });

                  HBox hbox = new HBox();
                  hbox.getChildren().addAll(addButton, delButton);
                 tableView.getColumns().addAll(roleNameColumn, departmentColumn, descriptionColumn);
                 tableView.setItems(data);


                  newStage.setTitle("Role Scene");
                  newStage.setScene(newScene);
                  newVBox.getChildren().addAll(tableView,hbox);
                  newStage.show();
              }
          });

          vbox = new VBox(label, tf, buttonInterviewScene, buttonRecruitmentScene, buttonRoleScene);
          vbox.setSpacing(20);
          vbox.setAlignment(Pos.CENTER);
          scene = new Scene(vbox, 500, 500);

          // Create an array of items
          String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};
          // Create a ListView and set the items
          ListView<String> listView = new ListView<>(FXCollections.observableArrayList(items));
          // Add the ListView to the VBox
          vbox.getChildren().add(listView);

          primaryStage.setTitle("A Simple Scene!");
          primaryStage.setScene(scene);
          primaryStage.show();
        
        
    }

   
  public static void main(String[] args) {

   // Print the current date in format yymmdd
    String  date = java.time.LocalDate.now().toString();
    date = date.replaceAll("-", "");
    System.out.println("Current date: " + date);
    String testDate = "2025/03/01";
    testDate = testDate.replaceAll("/|-", "");
    System.out.println("Test date: " + testDate);
    if(Integer.parseInt(date) < Integer.parseInt(testDate)){
      System.out.println("date is smaller");
    }else{
      System.out.println("date is bigger");
    }

    //Creating test data for 4 roles:
    Role truckDriver = new Role("Truck Driver", "Logistics", "Pickup deliveries, drive them to next location, offload deliveries");
    Role warehouseGrunt = new Role("Warehouse Grunt", "Logistics","Unpack and pack delivery trucks as they arrive");
    Role logisticsOperator = new Role("Logistics Operator", "Logistics", "Coordinate drivers, warehouses, and deliveries");
    Role payrollAdmin = new Role("Payroll Admin", "HR", "Manage employee income payments");
    currentJobRoles.add(truckDriver);
    currentJobRoles.add(warehouseGrunt);
    currentJobRoles.add(logisticsOperator);
    currentJobRoles.add(payrollAdmin);

    //Creating test data for 6 applicants:
    Applicant app1 = new Applicant("Joseph", new ArrayList<Role>());
    app1.getRoleAppHistory().add(truckDriver);
    app1.getRoleAppHistory().add(warehouseGrunt);
    Applicant app2 = new Applicant("Eric", new ArrayList<Role>());
    Applicant app3 = new Applicant("Mark", new ArrayList<Role>());
    app3.getRoleAppHistory().add(logisticsOperator);
    app3.getRoleAppHistory().add(payrollAdmin);
    Applicant app4 = new Applicant("Henry", new ArrayList<Role>());
    app4.getRoleAppHistory().add(truckDriver);
    Applicant app5 = new Applicant("Bella", new ArrayList<Role>());
    app5.getRoleAppHistory().add(truckDriver);
    Applicant app6 = new Applicant("Marie", new ArrayList<Role>());
    app6.getRoleAppHistory().add(logisticsOperator);

    //Creating test data for 3 recruitments
    Recruitment rec01 = new Recruitment(truckDriver, "2023/06/06", new ArrayList<Applicant>());
    rec01.getApplicants().add(app1);
    rec01.getApplicants().add(app4);
    rec01.getApplicants().add(app5);
    Recruitment rec02 = new Recruitment(warehouseGrunt, "2024/07/07", new ArrayList<Applicant>());
    rec02.getApplicants().add(app1);
    rec02.getApplicants().add(app2);
    Recruitment rec03 = new Recruitment(logisticsOperator, "2024-08-08", new ArrayList<Applicant>());
    rec03.getApplicants().add(app3);
    rec03.getApplicants().add(app6);
    currentRecruitments.add(rec01);
    currentRecruitments.add(rec02);
    currentRecruitments.add(rec03);

    //Creating test data for 6 interviews
    Interview interview01 = new Interview(rec01, app1, "May 27th at 10.00", "Lund");
    Interview interview02 = new Interview(rec01, app4, "May 25th at 13.00", "Stockholm");
    Interview interview03 = new Interview(rec01, app5, "June 1st at 14.00", "Stockholm");
    Interview interview04 = new Interview(rec02, app2, "June 1st at 10.00", "Stockholm");
    Interview interview05 = new Interview(rec03, app3, "May 26th at 11.00", "Lund");
    Interview interview06 = new Interview(rec03, app6, "May 26th at 15.00", "Lund");
    interviewSchedule.add(interview01);
    interviewSchedule.add(interview02);
    interviewSchedule.add(interview03);
    interviewSchedule.add(interview04);
    interviewSchedule.add(interview05);
    interviewSchedule.add(interview06);
    
    launch(args);
  }

  //Main Methods

  public static void displayInterviewSchedule(){
    for(int i = 0; i < interviewSchedule.size(); i++){
      interviewSchedule.get(i).displayInterviewInfo();
    }
  }


  
  public static void bookInterview(){
    //Ask user for interview date, location, and applicant name, create new Applicant. 
    System.out.println("Enter the date of the interview: ");
    String newInterviewDate = sc.nextLine();
    System.out.println("Enter the location of the interview: ");
    String newInterviewLocation = sc.nextLine();
    System.out.println("Enter the name of the applicant: ");
    Applicant newApplicant = new Applicant();
    newApplicant.setName(sc.nextLine());
    
    //Ask user for role name, check if it matches an ongoing recruitment. If yes, add the new applicant to the currentrecruitments list, create a new interview object with the user inputted values, add the interview to the interview schedule, and send a confirmation message. If not, then prompt the user to enter a new role name. 
    System.out.println("Enter the name of the role they are applying for: ");
    String roleName = sc.nextLine();
    boolean roleFound = false;
    
    for(int i = 0; i < currentRecruitments.size(); i++){
      if(currentRecruitments.get(i).getRoleName().contains(roleName)){
        currentRecruitments.get(i).getApplicants().add(newApplicant);
        Interview newInterview = new Interview(currentRecruitments.get(i), newApplicant, newInterviewDate, newInterviewLocation);
        interviewSchedule.add(newInterview);
        System.out.println("Interview booked! Here is the interview information:");
        newInterview.displayInterviewInfo();
        roleFound = true;
        break;
      }
    }
    
    if (roleFound == false){
      System.out.println("There is not a recruitment for that role. Please enter a new role name: ");
    }
    
  }


  
  public static void removeInterview(){
    System.out.println("Enter the name of the applicant whose interview you would like to remove: ");
    String applicantName = sc.nextLine();
    boolean interviewFound = false;
    
    for (int i = 0; i < interviewSchedule.size(); i++){
      if (interviewSchedule.get(i).getApplicant().getName().equals(applicantName)){
        interviewSchedule.remove(i);
        System.out.println("Interview has been removed!");
        interviewFound = true;
        break;
      }           
    }
    if (interviewFound == false){
      System.out.println("There is no interview scheduled for this applicant. Please restart the removeInterview function.");
    }  
  }


  
  public static void addRole(){
    System.out.println("Enter the name of the role you would like to add: ");
    String newRoleName = sc.nextLine();
    System.out.println("Enter the name of which department this role would work in: ");
    String newRoleDepartment = sc.nextLine();
    System.out.println("Enter a description of the role: ");
    String newRoleDescription = sc.nextLine();
    Role newRole = new Role(newRoleName, newRoleDescription, newRoleDepartment);
    currentJobRoles.add(newRole);
    System.out.println("Role has been added! Here is the information for this role:");
    newRole.displayRoleInfo();
  }


  
  public static void updateRoleDescription(){
    System.out.println("Enter the name of the role whose description you would like to update: ");
    String roleName = sc.nextLine();
    boolean roleFound = false;
    
    for (int i = 0; i < currentJobRoles.size(); i++){
      if (currentJobRoles.get(i).getRoleName().equals(roleName)){
        System.out.println("This is the role's current information:");
        currentJobRoles.get(i).displayRoleInfo();
        System.out.println("Enter the new description of the role: ");
        String newRoleDescription = sc.nextLine();
        currentJobRoles.get(i).setRoleDescription(newRoleDescription);
        System.out.println("Role description has been updated! Here is the information for this role:");
        currentJobRoles.get(i).displayRoleInfo();
        roleFound= true;
        break;
      }       
    }

    if (roleFound == false){
      System.out.println("There is no role instance with this name. Please restart the updateRoleDescription function.");
    }
  }


  
  public static void addApplicant(){
    System.out.println("Enter the name of the applicant you would like to add: ");
    Applicant newApplicant = new Applicant(sc.nextLine(), new ArrayList<Role>());
    
    
    System.out.println("Enter the name of the role this applicant is applying for:");
    String roleName = sc.nextLine();
    boolean roleRecruitmentFound = false;
    
    for (int i = 0; i < currentRecruitments.size(); i++){
      if (currentRecruitments.get(i).getRoleName().equals(roleName)){
        newApplicant.getRoleAppHistory().add(currentRecruitments.get(i).getRole());
        currentRecruitments.get(i).getApplicants().add(newApplicant);
        System.out.println("Applicant has been added to the applicant list of the recruitment for " + currentRecruitments.get(i).getRoleName() + " role.");
        System.out.println("Here is the current list of applicants for the "+ currentRecruitments.get(i).getRoleName() + " role:");
        currentRecruitments.get(i).displayApplicantsList();
        roleRecruitmentFound = true;
        break;
      } 
    }

    if (roleRecruitmentFound == false){
      System.out.println("There is no recruitment ongoing for this role. Please restart the addApplicant function.");
    }
    //OBS NEED TO TEST. Uncertain about the for loop's process. Should check if applicant had already applied to another role/have functionality to add another role to the applicant's role application history.
  }



  
  public static void displayApplicantsAppHistory(){
    System.out.println("Enter the name of the applicant whose application history you would like to display: ");
    String applicantName = sc.nextLine();
    boolean applicantFound = false;

    for (int i = 0; i < currentRecruitments.size(); i++){
      for (int j = 0; j < currentRecruitments.get(i).getApplicants().size(); j++){
        if (currentRecruitments.get(i).getApplicants().get(j).getName().equals(applicantName)){
          System.out.println("Here is the applicant's application history for " + currentRecruitments.get(i).getRoleName() + " role:");
          currentRecruitments.get(i).getApplicants().get(j).displayRoleAppHistory();
          applicantFound = true;
        } 
      }
    }
    if (applicantFound == false){
      System.out.println("There is no applicant instance with this name. Please restart the displayApplicantsAppHistory function.");
    }
    //OBS NEED TO TEST. Uncertain about the for loop's process.
  }


  
  public static void displayRecruitmentApplicants(){
    System.out.println("Enter the name of the currently recruiting role whose applicants you would like to display: ");
    String recruitmentName = sc.nextLine();
    boolean recruitmentFound = false;
    
    for (int i = 0; i < currentRecruitments.size(); i++){
      if (currentRecruitments.get(i).getRoleName().equals(recruitmentName)){
        System.out.println("Here is the list of applicants for " + currentRecruitments.get(i).getRoleName() + " role:");
        currentRecruitments.get(i).displayApplicantsList();
        recruitmentFound = true;
        break;
      } 
      
    }
    if (recruitmentFound == false){
      System.out.println("There is no recruitment instance with this name. Please restart the displayRecruitmentApplicants function.");
    }
  }

} 
