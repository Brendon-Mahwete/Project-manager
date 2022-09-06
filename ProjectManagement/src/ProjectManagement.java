import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class ProjectManagement {
    public static void main(String[] args) throws ParseException {

        Scanner input = new Scanner(System.in);
        menu(); // Calling the menu function
        char choice = input.next().charAt(0);

        /*---------------------------------------SQL Setup section---------------------------------------*/
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/projects?useSSL=false",
                    "otheruser",
                    "swordfish"
            );

            // Create a direct line to the database for running our queries
            Statement statement = connection.createStatement();
            /*---------------------------------------While loop section---------------------------------------*/

            while (true) {

                /*-------------------------------------Creating new Project section-------------------------------------*/

                if (choice == '1') {
                    //Creating the Project objects and getting inputs by calling the getInputs function
                    Project poised = new Project();
                    poised.getInputs();

                    System.out.println("**************************************");

                    // Declaring the Person class object and getting inputs by calling the getInputs function
                    Person client = new Person("Client", poised.getProjectNum());
                    client.getInputs();

                    System.out.println("**************************************");

                    //Declaring the Person class object 'architects' to get info about the architect
                    Person architect = new Person("Architect", poised.getProjectNum());
                    architect.getInputs();

                    System.out.println("**************************************");

                    //Declaring the Person class object 'projectManager' to get info about the Manager
                    Person projectManager = new Person("Project Manager", poised.getProjectNum());
                    projectManager.getInputs();

                    System.out.println("**************************************");

                    //Declaring the Person class object 'structural_engineer' to get info about the engineer
                    Person structural_engineer = new Person("Structural Engineer", poised.getProjectNum());
                    structural_engineer.getInputs();

                    //Adding the details from the user to the database
                    statement.executeUpdate("insert into projects values ("+poised.getFileInput()+")");
                    statement.executeUpdate("insert into clients values ("+client.getFileInput()+")");
                    statement.executeUpdate("insert into architects values ("+architect.getFileInput()+")");
                    statement.executeUpdate(
                            "insert into project_managers values ("+projectManager.getFileInput()+")");
                    statement.executeUpdate(
                            "insert into structural_engineers values ("+structural_engineer.getFileInput()+")");



                    //Displays the Person class details
                    System.out.println(poised);
                    System.out.println(client);
                    System.out.println(architect);
                    System.out.println(projectManager);
                    System.out.println(structural_engineer);

                }

                /*-----------------------------------View Projects and edit section-----------------------------------*/

                else if (choice == '2') {
                    System.out.println("You chose to view projects");

                    //Displays the list of Projects and their project name
                    System.out.println("The list of projects: ");
                    printProjectsList(statement);

                    //Displays the project
                    System.out.println("Which Project do you want to edit.\nEnter the project number only: ");
                    int editProjectNum = input.nextInt();
                    input.nextLine();

                    if (!searchProject(statement ,editProjectNum)) {
                        System.out.println("Entered a wrong number");
                    }

                    else {
                        String userInput;
                        int updateNum; // For updating the int value in the class
                        float updateAmount; // For updating the amounts in the projects

                        /*--------------------Choosing which section of a project to edit-----------------*/
                        while (true) {
                            //Displays the project, Client, Architect and Contractor
                            displayProject(statement, editProjectNum);

                            System.out.println("1 : Edit Project Name");
                            System.out.println("2 : Edit Building Type");
                            System.out.println("3 : Edit Project Address");
                            System.out.println("4 : Edit Project Deadline");
                            System.out.println("5 : Edit Project Number");
                            System.out.println("6 : Edit Project erfNumber");
                            System.out.println("7 : Edit Project Total Fee");
                            System.out.println("8 : Edit Amount paid to date");
                            System.out.println("9 : if the project is complete");
                            System.out.println("0 : To return to the previous menu");
                            char editProjectSection = input.next().charAt(0);
                            input.nextLine();

                            if (editProjectSection == '1') {
                                System.out.println("project name");
                                System.out.println("Enter the new project name: ");
                                userInput = input.nextLine();
                                statement.executeUpdate(
                                        "update projects set project_name = '"+userInput+"' " +
                                                "where project_num = "+editProjectNum);

                            } else if (editProjectSection == '2') {
                                System.out.println("Building type");
                                System.out.println("Enter the new building type: ");
                                userInput = input.nextLine();
                                statement.executeUpdate(
                                        "update projects set build_type = '"+userInput+"' " +
                                                "where project_num = "+editProjectNum);

                            } else if (editProjectSection == '3') {
                                System.out.println("Project address");
                                System.out.println("Enter the new project address: ");
                                userInput = input.nextLine();
                                statement.executeUpdate(
                                        "update projects set address = '"+userInput+"' " +
                                                "where project_num = "+editProjectNum);

                            } else if (editProjectSection == '4') {
                                System.out.println("Project deadline");
                                System.out.println("Enter the new project deadline (date must be in this format dd/mm/yyyy): ");
                                userInput = input.nextLine();
                                statement.executeUpdate(
                                        "update projects set deadLine = '"+userInput+"' " +
                                                "where project_num = "+editProjectNum);

                            } else if (editProjectSection == '5') {
                                /*A try statement nested in a while loop to catch errors and request the user
                                to enter again*/
                                while(true) {
                                    try {
                                        System.out.println("Project number");
                                        System.out.println("Enter the new Project Number: ");
                                        updateNum = input.nextInt();
                                        input.nextLine();
                                        statement.executeUpdate(
                                                "update projects set project_num = " + updateNum + " " +
                                                        "where project_num = " + editProjectNum);
                                        break;
                                    }catch (SQLException e){
                                        System.out.println("incorrect input please enter again: ");
                                    }
                                }
                            } else if (editProjectSection == '6') {
                                /*A try statement nested in a while loop to catch errors and request the user
                                to enter again*/
                                while(true) {
                                    try {
                                        System.out.println("Project ERF Number");
                                        System.out.println("Enter the ERF Number: ");
                                        updateNum = input.nextInt();
                                        input.nextLine();
                                        statement.executeUpdate(
                                                "update projects set erf_num = " + updateNum + " " +
                                                        "where project_num = " + editProjectNum);
                                        break;
                                    } catch (SQLException e) {
                                        System.out.println("incorrect input please enter again: ");
                                    }
                                }
                            } else if (editProjectSection == '7') {
                                /*A try statement nested in a while loop to catch errors and request the user
                                to enter again*/
                                while(true) {
                                    try {
                                        System.out.println("Total Fee");
                                        System.out.println("Enter the new Total fee: ");
                                        updateAmount = input.nextInt();
                                        input.nextLine();
                                        statement.executeUpdate(
                                                "update projects set total_fee = " + updateAmount + " " +
                                                        "where project_num = " + editProjectNum);
                                        break;
                                    } catch (SQLException e) {
                                        System.out.println("incorrect input please enter again: ");
                                    }
                                }
                            } else if (editProjectSection == '8') {
                                /*A try statement nested in a while loop to catch errors and request the user
                                to enter again*/
                                while(true) {
                                    try {
                                        System.out.println("Amount paid to date");
                                        System.out.println("Enter the amount the client paid: ");
                                        updateAmount = input.nextFloat();
                                        input.nextLine();

                                        //Adding the updateAmount with the amount from the database
                                        statement.executeUpdate(
                                                "update projects set paid_amount = paid_amount + " + updateAmount + " " +
                                                        "where project_num = " + editProjectNum);
                                        break;
                                    }catch (Exception e){
                                        System.out.println("Wrong Input try again");
                                    }
                                }
                            } else if (editProjectSection == '9') {
                                System.out.println("Complete project");

                                //finalizing the project
                                ResultSet result = statement.executeQuery(
                                        "SELECT * FROM projects where project_num = "+editProjectNum);
                                System.out.println("\n==============================================\n");
                                result.next();

                                //Getting the total fee and paid amount from the database
                                float totalFee = result.getFloat("total_fee");
                                float paidAmount = result.getFloat("paid_amount");

                                // Comparing the total feel and the paid amount
                                if (totalFee <= paidAmount){
                                    System.out.println("Project fully paid");
                                }else {
                                    ResultSet client_details = statement.executeQuery(
                                            "select * from clients  where project_num = "+editProjectNum
                                    );
                                    while (client_details.next()) {
                                        System.out.println("Amount not fully paid ");
                                        System.out.println("Contact " + client_details.getString("client_name"));
                                        System.out.println("Email: " + client_details.getString("client_email"));
                                        System.out.println("Phone number: " + client_details.getString("client_num"));
                                        System.out.println("Out standing amount R " + (totalFee-paidAmount));
                                    }
                                }

                                //Making the value of project_complete table to true
                                statement.executeUpdate(
                                        "update projects set project_complete = 'true' where project_num = "+editProjectNum);
                                System.out.println("\n==============================================\n");
                                break;
                            } else if (editProjectSection == '0') {
                                break;
                            } else {
                                System.out.println("You entered a wrong input enter again");
                            }
                        }
                    }

                }

                /*---------------------------------------View Incomplete Projects---------------------------------------*/

                else if (choice == '3') {
                    //Displays the list of Incomplete Projects and their project name
                    printIncompleteProjects(statement);
                }

                /*---------------------------------------View Passed deadline Projects---------------------------------------*/

                else if (choice == '4') {
                    //Displays the list of Incomplete Projects that are passed their due date
                    System.out.println("The list of Projects that are past the due date: ");
                    printPastDueDateProjects(statement);
                }
                /*---------------------------------------search project---------------------------------------*/
                else if (choice =='5'){
                    // Searching for a project based on project number
                    System.out.println("Enter the project number: ");
                    int proj_num = input.nextInt();
                    if(searchProject(statement, proj_num)){
                        displayProject(statement, proj_num);
                    }else {
                        System.out.println("\n==================================================\n");
                        System.out.println("Project not found");
                        System.out.println("\n==================================================\n");
                    }
                }

                /*---------------------------------------Exit from while loop---------------------------------------*/

                else if (choice == '6') {
                    break;
                }

                /*------------------------------------Getting user input in While loop------------------------------------*/
                menu();
                choice = input.next().charAt(0);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // A Menu function to display the menu
    public static void menu() {
        System.out.println("\n************************************************");
        System.out.println("Hello choose your option: ");
        System.out.println("1 :Create a new project");
        System.out.println("2 :View existing projects and Edit projects");
        System.out.println("3 :To view incomplete projects");
        System.out.println("4 :Projects past the dead line");
        System.out.println("5 :To search for a project");
        System.out.println("6 :Exit");
    }

    /***
     * Compares two dates
     * compares today and the project's deadline date to see if it has passed nor not
     *
     * @param theDate
     * @return
     * @throws ParseException
     */
    public static boolean isDatePassed(String theDate) throws ParseException {
        // A function to compare dates
        LocalDate date = LocalDate.now();
        DateTimeFormatter myFormattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dueDate = date.format(myFormattedDate);

        SimpleDateFormat myDate = new SimpleDateFormat("dd/MM/yyyy");
        Date today = myDate.parse(theDate);
        Date newDueDate = myDate.parse(dueDate);


        return today.compareTo(newDueDate) >= 0;
    }

    /**
     * Method printing all values in all rows.
     * Takes a statement to try to avoid spreading DB access too far.
     *
     * @param statement on an existing connection
     * @throws SQLException
     */
    public static void printProjectsList(Statement statement) throws SQLException{

        ResultSet results = statement.executeQuery("SELECT * FROM projects");
        String displayProject = "";
        System.out.println("\n==================================================\n");
        System.out.println("Project_NUM \t Project Name");
        displayProject += "--------------------------------------------------------\n";
        while (results.next()) {

            displayProject += results.getInt("project_num") + "\t\t\t\t\t";
            displayProject += results.getString("project_name") + "\n";
            displayProject += "--------------------------------------------------------\n";
        }
        System.out.println(displayProject);
        System.out.println("\n==================================================\n");
    }

    /***
     * Method that displays the details of a single project
     *
     * @param statement on an existing connection
     * @param number the value of the project number
     * @throws SQLException
     */
    public static void displayProject(Statement statement, int number) throws SQLException{

        ResultSet result = statement.executeQuery(
                "SELECT * FROM projects where project_num = "+ number);

        String outPutProject = "";
        while (result.next()) {

            outPutProject += "\n==============================================\n";
            outPutProject += "Project number: " + result.getInt("project_num") + "\n";
            outPutProject += "Project name: " + result.getString("project_name") + "\n";
            outPutProject += "Building type: " + result.getString("build_type") + "\n";
            outPutProject += "Address : " + result.getString("address") + "\n";
            outPutProject += "Deadline: " + result.getString("deadline") + "\n";
            outPutProject += "ERF number: " + result.getString("erf_num") + "\n";
            outPutProject += "The total fee: R" + result.getInt("total_fee") + "\n";
            outPutProject += "Amount paid so far: R" + result.getInt("paid_amount");
            outPutProject += "\n==============================================\n";
        }
        System.out.println(outPutProject);
    }

    /**
     * Method prints all projects that are incomplete
     * Takes a statement to try to avoid spreading DB access too far.
     *
     * @param statement on an existing connection
     * @throws SQLException
     */
    public static void printIncompleteProjects(Statement statement) throws SQLException{
        ResultSet results = statement.executeQuery("SELECT * FROM projects where project_complete = 'false'");
        System.out.println("\n==================================================\n");
        while (results.next()) {
            System.out.println(
                    results.getInt("project_num") + ", "
                            + results.getString("project_name") + ", "
                            + results.getString("build_type") + ", "
                            + results.getString("address") + ", "
                            + results.getInt("erf_num") + ", "
                            + results.getString("deadline") + ", "
                            + results.getInt("total_fee") + ", "
                            + results.getInt("paid_amount") + ", "
                            + results.getString("project_complete")
            );
        }
        System.out.println("\n==================================================\n");
    }

    /**
     * Method prints projects that are passed their due date
     * Takes a statement to try to avoid spreading DB access too far.
     *
     * @param statement on an existing connection
     * @throws SQLException
     * @throws ParseException
     */
    public static void printPastDueDateProjects(Statement statement) throws SQLException, ParseException {
        ResultSet results = statement.executeQuery("SELECT * FROM projects where project_complete = 'false'");
        System.out.println("\n==================================================\n");
        while (results.next()) {
            if (!isDatePassed(results.getString("deadline"))) {
                System.out.println(
                        results.getInt("project_num") + ", "
                                + results.getString("project_name") + ", "
                                + results.getString("build_type") + ", "
                                + results.getString("address") + ", "
                                + results.getInt("erf_num") + ", "
                                + results.getString("deadline") + ", "
                                + results.getInt("total_fee") + ", "
                                + results.getInt("paid_amount") + ", "
                                + results.getString("project_complete")
                );
            }
        }
        System.out.println("\n==================================================\n");
    }

    /**
     * Method checks if the number is in the column project_num of the table projects
     * returns true if the number is in the column and false if it's not
     *
     * @param statement on an existing connection
     * @param number this represents the projects num entered by the user
     * @return
     * @throws SQLException
     */
    public static boolean searchProject(Statement statement, int number) throws SQLException {
        ResultSet results = statement.executeQuery("SELECT * FROM projects where project_num = "+number);
        return results.next();
    }
}