import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Project {
    //Attributes
    private String projectName;
    private String buildingType;
    private String projectAddress;
    private String projectDeadline;
    private int projectNumber;
    private int erfNumber;
    private double projectTotalFee;
    private double amountPaidToDate;
    private boolean isProjectFinished = false;

    //Constructors
    public Project(){

    }
    public Project(String[] myList){
        this.projectName = myList[0];
        this.buildingType = myList[1];
        this.projectAddress = myList[2];
        this.projectDeadline = myList[3];
        this.projectNumber = Integer.parseInt(myList[4]);
        this.erfNumber = Integer.parseInt(myList[5]);
        this.projectTotalFee = Double.parseDouble(myList[6]);
        this.amountPaidToDate = Double.parseDouble(myList[7]);
        this.isProjectFinished = Boolean.parseBoolean(myList[8]);
    }
    //Methods to update/change class variable values
    public void updateProjectDeadline(String newProjectDeadline){
        projectDeadline = newProjectDeadline;
    }
    public void updateIsProjectFinished(boolean newProjectStatus){
        isProjectFinished = newProjectStatus;
    }
    public void updateProjectName(String newProjectName){
        projectName = newProjectName;
    }
    public void updateBuildingType(String newBuildingType){
        buildingType = newBuildingType;
    }
    public void updateProjectAddress(String newProjectAddress){
        projectAddress = newProjectAddress;
    }
    public void updateProjectNumber(int newProjectNumber){
        projectNumber = newProjectNumber;
    }
    public void updateERF_Number(int newErfNumber){
        erfNumber = newErfNumber;
    }
    public void updateProjectTotalFee(double newProjectTotalFee){
        projectTotalFee = newProjectTotalFee;
    }
    public void updateAmountPaid(double newAmountPaid){
        amountPaidToDate += newAmountPaid; //Adding the new amount to the already existing amount
    }

    // Method getters
    public String getProjectDeadline(){
        return projectDeadline;
    }
    public double getProjectTotalFee(){
        return projectTotalFee;
    }
    public double getAmountPaidToDate(){
        return amountPaidToDate;
    }
    public boolean getIsProjectFinished(){
        return isProjectFinished;
    }
    public int getProjectNum(){
        return projectNumber;
    }

    /***
     *
     * @return a string containing the details of the project
     */
    public String toString(){

        String displayProject = "";

        //A function to display the details of the project
        displayProject += "\n==============================================\n";
        displayProject += "Project number: " + projectNumber + "\n";
        displayProject += "Project name: " + projectName + "\n";
        displayProject += "Building type: " + buildingType + "\n";
        displayProject += "Address : " + projectAddress + "\n";
        displayProject += "Deadline: " + projectDeadline + "\n";
        displayProject += "ERF number: " + erfNumber + "\n";
        displayProject += "The total fee: R" + projectTotalFee + "\n";
        displayProject += "Amount paid so far: R" + amountPaidToDate;
        displayProject += "\n==============================================\n";

        return displayProject;
    }
    //A function for getting inputs from the user
    public void getInputs(){
        Scanner input = new Scanner(System.in);

        //A set of while loops with a try statement to catch input errors, like a string entered instead of an int
        while(true){
            try {
                System.out.println("Enter the project number: ");
                this.projectNumber = input.nextInt();
                input.nextLine();
                break;

            }catch (InputMismatchException e){
                System.out.println("Input incorrect!");
                input.nextLine();
                input.nextLine();
            }

        }
        System.out.println("Enter the project name: ");
        projectName = input.nextLine();
        System.out.println("Enter the building type: ");
        this.buildingType = input.nextLine();
        System.out.println("Enter the address: ");
        this.projectAddress = input.nextLine();
        System.out.println("Enter the deadline date(date must be in this format dd/mm/yyyy)");
        this.projectDeadline = input.nextLine();

        while(true){
            try {
                System.out.println("Enter the ERF number: ");
                this.erfNumber = input.nextInt();
                break;

            }catch (InputMismatchException e){
                System.out.println("Input incorrect!");
                input.nextLine();
            }

        }

        while(true){
            try {
                System.out.println("Enter the total fee: ");
                this.projectTotalFee = input.nextDouble();
                break;

            }catch (InputMismatchException e){
                System.out.println("Input incorrect!");
                input.nextLine();
            }

        }

        while(true){
            try {
                System.out.println("Enter the amount paid to date: ");
                this.amountPaidToDate = input.nextDouble();
                input.nextLine();
                break;

            }catch (InputMismatchException e){
                System.out.println("Input incorrect!");
                input.nextLine();
            }

        }
    }

    public String getFileInput(){
        //A variable used to store a boolean as a string
        String projectFinished = Boolean.toString(isProjectFinished);
        return projectNumber + ", '" +
                projectName + "', '" +
                buildingType + "', '" +
                projectAddress + "', " +
                erfNumber + ", '" +
                projectDeadline + "', " +
                projectTotalFee + ", " +
                amountPaidToDate + ",'" +
                projectFinished + "'";
    }

    public void writeToFile(String person){
        String fileInput = getFileInput();
        try{
            FileWriter myFile = new FileWriter("Projects.txt", true);
            BufferedWriter writer = new BufferedWriter(myFile);
            writer.write(fileInput + "," + person +  "\n");
            writer.close();
        }
        catch (IOException e){
            System.out.println("Something went wrong");
        }
    }

}
