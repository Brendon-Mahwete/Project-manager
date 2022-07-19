import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class ProjectManagement {
    public static void main(String[] args) throws ParseException {

        Scanner input = new Scanner(System.in);
        menu(); // Calling the menu function
        char choice = input.next().charAt(0);

        /*---------------------------------------While loop section---------------------------------------*/

        while (true) {

            /*-------------------------------------Creating new Project section-------------------------------------*/

            if (choice == 'c' || choice == 'C') {
                String personDetails;
                //Creating the Project objects and getting inputs by calling the getInputs function
                Project poised = new Project();
                poised.getInputs();

                //Displays the Project class details
                System.out.println(poised);

                // Declaring an object for the Person class and getting inputs by calling the getInputs function
                Person individual = new Person("Client");
                individual.getInputs();

                System.out.println("**************************************");

                Person architect = new Person("Architect");
                architect.getInputs();
                System.out.println("**************************************");

                Person contractor = new Person("Contractor");
                contractor.getInputs();

                personDetails = individual.getFileInput() + architect.getFileInput() + contractor.getFileInput();
                poised.writeToFile(personDetails);

                //Displays the Person class details
                System.out.println(individual);
                System.out.println("**************************************");
                System.out.println(architect);
                System.out.println("**************************************");
                System.out.println(contractor);

            }

            /*-----------------------------------View Projects and edit section-----------------------------------*/

            else if (choice == 'v' || choice == 'V') {
                System.out.println("You chose to view projects");
                String[][] projectList = listOfProjects();

                //Displays the list of Projects and their project name
                System.out.println("The list of projects: ");
                for (int i = 0; i < projectList.length; i++) {
                    System.out.println((i + 1) + ": " + projectList[i][0]);
                }
                //Displays the project
                System.out.println("Which Project do you want to edit.\nEnter the number only: ");
                int editProjectNum = input.nextInt();
                input.nextLine();

                if (editProjectNum > projectList.length) {
                    System.out.println("Entered a wrong number");
                }

                else {

                    //Creates a new Projects instance of the project that the user chose to view and displays it
                    Project newPoised = new Project(projectList[editProjectNum - 1]);
                    Person newIndividual = new Person(personInfo(projectList[editProjectNum - 1], "client"));
                    Person newArchitect = new Person(personInfo(projectList[editProjectNum - 1], "architect"));
                    Person newContractor = new Person(personInfo(projectList[editProjectNum - 1], "contractor"));
                    String userInput;
                    int updateNum; // For updating the int value in the class
                    double updateAmount; // For updating the amounts in the projects

                    /*--------------------Choosing which section of a project to edit-----------------*/
                    while (true) {
                        //Displays the project, Client, Architect and Contractor
                        System.out.println(newPoised);
                        System.out.println(newIndividual);
                        System.out.println(newArchitect);
                        System.out.println(newContractor);

                        System.out.println("n : Edit Project Name");
                        System.out.println("b : Edit Building Type");
                        System.out.println("a : Edit Project Address");
                        System.out.println("d : Edit Project Deadline");
                        System.out.println("m : Edit Project Number");
                        System.out.println("e : Edit Project erfNumber");
                        System.out.println("f : Edit Project Total Fee");
                        System.out.println("p : Edit Amount paid to date");
                        System.out.println("x : if the project is complete");
                        System.out.println("z : To return to the previous menu");
                        char editProjectSection = input.next().charAt(0);
                        input.nextLine();

                        if (editProjectSection == 'n' || editProjectSection == 'N') {
                            System.out.println("project name");
                            System.out.println("Enter the new project name: ");
                            userInput = input.nextLine();
                            newPoised.updateProjectName(userInput);
                            projectList[editProjectNum - 1][0] = userInput;

                        } else if (editProjectSection == 'b' || editProjectSection == 'B') {
                            System.out.println("Building type");
                            System.out.println("Enter the new building type: ");
                            userInput = input.nextLine();
                            newPoised.updateBuildingType(userInput);
                            projectList[editProjectNum - 1][1] = userInput;

                        } else if (editProjectSection == 'a' || editProjectSection == 'A') {
                            System.out.println("Project address");
                            System.out.println("Enter the new project address: ");
                            userInput = input.nextLine();
                            newPoised.updateProjectAddress(userInput);
                            projectList[editProjectNum - 1][2] = userInput;

                        } else if (editProjectSection == 'd' || editProjectSection == 'D') {
                            System.out.println("Project deadline");
                            System.out.println("Enter the new project deadline (date must be in this format dd/mm/yyyy): ");
                            userInput = input.nextLine();
                            newPoised.updateProjectDeadline(userInput);
                            projectList[editProjectNum - 1][3] = userInput;

                        } else if (editProjectSection == 'm' || editProjectSection == 'M') {
                            System.out.println("Project number");
                            System.out.println("Enter the new Project Number: ");
                            updateNum = input.nextInt();
                            newPoised.updateProjectNumber(updateNum);
                            input.nextLine();
                            projectList[editProjectNum - 1][4] = String.valueOf(updateNum);

                        } else if (editProjectSection == 'e' || editProjectSection == 'E') {
                            System.out.println("project erf number");
                            System.out.println("Enter the new ERF Number: ");
                            updateNum = input.nextInt();
                            newPoised.updateERF_Number(updateNum);
                            input.nextLine();
                            projectList[editProjectNum - 1][5] = String.valueOf(updateNum);

                        } else if (editProjectSection == 'f' || editProjectSection == 'F') {
                            System.out.println("Total fee");
                            System.out.println("Enter the new total fee: ");
                            updateAmount = input.nextDouble();
                            newPoised.updateProjectTotalFee(updateAmount);
                            input.nextLine();
                            projectList[editProjectNum - 1][6] = String.valueOf(updateAmount);

                        } else if (editProjectSection == 'p' || editProjectSection == 'P') {
                            System.out.println("Amount paid to date");
                            System.out.println("Enter the amount the client paid. ");
                            updateAmount = input.nextDouble();
                            newPoised.updateAmountPaid(updateAmount);
                            input.nextLine();
                            projectList[editProjectNum - 1][7] = String.valueOf(newPoised.getAmountPaidToDate());

                        } else if (editProjectSection == 'x' || editProjectSection == 'X') {
                            System.out.println("Complete project");

                            //finalizing the project
                            System.out.println("\n==============================================\n");
                            newPoised.updateIsProjectFinished(true);
                            projectList[editProjectNum - 1][8] = String.valueOf(newPoised.getIsProjectFinished());

                            if (newPoised.getIsProjectFinished()) {
                                if (newPoised.getAmountPaidToDate() >= newPoised.getProjectTotalFee()) {
                                    System.out.println("Payment in full");
                                } else {
                                    System.out.println("\nThe amount is in arrears!");
                                    System.out.println("Please contacts" + newIndividual.getName() +
                                            " at " + newIndividual.getPhoneNumber());
                                    System.out.println("The outstanding amount is R" + (newPoised.getProjectTotalFee() - newPoised.getAmountPaidToDate()));
                                }
                            }
                            System.out.println("\n==============================================\n");
                            writeToFile(projectList);
                            break;
                        } else if (editProjectSection == 'z' || editProjectSection == 'Z') {
                            writeToFile(projectList);
                            break;
                        } else {
                            System.out.println("You entered a wrong input enter again");
                        }
                    }
                }

            }

            /*---------------------------------------View Incomplete Projects---------------------------------------*/

            else if (choice == 'i' || choice == 'I') {
                String[][] projectList = listOfProjects();

                //Displays the list of Incomplete Projects and their project name
                System.out.println("The list of Incomplete projects: ");
                int j = 1;
                for (String[] strings : projectList) {
                    if (Objects.equals(strings[8], "false")) {
                        System.out.println((j) + ": " + strings[0]);
                        j++;
                    }
                }
            }

            /*---------------------------------------View Incomplete Projects---------------------------------------*/

            else if (choice == 'd' || choice == 'D') {
                String[][] projectList = listOfProjects();

                //Displays the list of Incomplete Projects and their project name
                System.out.println("The list of Projects that are past the due date: ");
                int j = 1;
                for (String[] strings : projectList) {
                    if (!isDatePassed(strings[3])) {
                        System.out.println((j) + ": " + strings[0]);
                        j++;
                    }
                }
            }

            /*---------------------------------------Exit from while loop---------------------------------------*/

            else if (choice == 'e' || choice == 'E') {
                break;
            }

            /*------------------------------------Getting user input in While loop------------------------------------*/
            menu();
            choice = input.next().charAt(0);
        }
    }

    public static void menu() {
        System.out.println("\n************************************************");
        System.out.println("Hello choose your option: ");
        System.out.println("c :Create a new project");
        System.out.println("v :View existing projects and Edit projects");
        System.out.println("i :To view incomplete projects");
        System.out.println("d :Projects past the dead line");
        System.out.println("e :To exit");
    }

    public static int numOfLines() {
        //This function counts the number of lines in the file Projects.txt and returns the value
        int lineCount = 0;

        //A Try statement to catch errors
        try {
            File myObj = new File("Projects.txt");
            Scanner reader = new Scanner(myObj);

            // A while loop to count the lines
            while (reader.hasNextLine()) {
                reader.nextLine();
                lineCount++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return lineCount;
    }

    public static String[][] listOfProjects() {
        // This function takes the projects in the file Projects.txt and puts them in a 2d array and returns it


        //Creating a 2d array with the number of lines representing the row
        int linesInFile = numOfLines();
        String[][] myList = new String[linesInFile][];

        //A Try statement to catch errors
        try {

            File projectFile = new File("Projects.txt");
            Scanner reader = new Scanner(projectFile);
            int row = 0;

            //Reads data in the file and stores them in the Array
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                myList[row] = data.split(",");
                row++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return myList;
    }

    //This functions takes a 2d array of projects then writes them to the file
    public static void writeToFile(String[][] myList) {
        StringBuilder myWords = new StringBuilder();
        try {
            FileWriter myFile = new FileWriter("Projects.txt");
            for (String[] strings : myList) {
                for (int j = 0; j < 24; j++) {
                    myWords.append(strings[j]).append(",");
                }
                myWords.append("\n");
            }
            myFile.write(String.valueOf(myWords));
            myFile.close();
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    public static String[] personInfo(String[] list, String choice) {
        String[] newList = new String[5];
        int j = 0;
        if(Objects.equals(choice, "client")){
            for (int i = 9; i < 14; i++) {
                newList[j] = list[i];
                j++;
            }
        }else if(Objects.equals(choice, "architect")){
            for (int i = 14; i < 19; i++) {
                newList[j] = list[i];
                j++;
            }
        }else if(Objects.equals(choice, "contractor")){
            for (int i = 19; i < 24; i++) {
                newList[j] = list[i];
                j++;
            }
        }
        return newList;
    }

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
}