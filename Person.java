import java.util.Scanner;

public class Person {
    ///Attributes
    private String position;
    private String name;
    private String email;
    private String address;
    private String phoneNumber; // Sting is used instead of int or long since java doesn't accept numbers like "0963"

    // Constructors;

    public Person(String title){
        this.position = title;
    }

    public Person(String[] myList){
        this.position = myList[0];
        this.name = myList[1];
        this.email = myList[2];
        this.address = myList[3];
        this.phoneNumber = myList[4];
    }

//    public Person(){
//
//    }
    //Methods
    public void updatePhoneNumber(String newPhoneNumber){
        phoneNumber = newPhoneNumber;
    }

    //Method getters
    public String getPosition(){
        return position;
    }
    public  String getPhoneNumber(){
        return phoneNumber;
    }
    public String getName(){
        return name;
    }
    /***
     *
     * @return a string containing the details of the project
     */
    public String toString(){
        String displayPerson = "";

        displayPerson += "\n==============================================\n";
        displayPerson += position + "'s name: " + name + "\n";
        displayPerson += position + "'s email: " + email + "\n";
        displayPerson += position + "'s address: " + address + "\n";
        displayPerson += position + "'s phone number: " + phoneNumber;
        displayPerson += "\n==============================================\n";

        return displayPerson;
    }
    //A function for getting inputs from the user
    public void getInputs(){

        // Getting Person details from the user
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the " + position + ": ");
        this.name = input.nextLine();
        System.out.println("Enter the email address of the " + position + ": ");
        this.email = input.nextLine();
        System.out.println("Enter the Physical address of the " + position + ": ");
        this.address = input.nextLine();
        System.out.println("Enter the phone number of the " + position + ": ");
        this.phoneNumber = input.nextLine();

    }
    public String getFileInput(){
        return position + "," +
                name + "," +
                email + "," +
                address + "," +
                phoneNumber + ",";
    }
}
