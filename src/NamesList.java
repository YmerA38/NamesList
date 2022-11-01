import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class NamesList {

    private ArrayList<String> names;
    private final String FILE_EXTENSION = "data/names.txt";

    public NamesList() {
        names = new ArrayList<>();
    }

    public void startUserInterface() throws FileNotFoundException {
        System.out.println("""
                Welcome to the NamesList - enterprise edition.
                ----------------------------------------------
                """);

        Scanner sc = new Scanner(System.in);
        int choice = 99;
        while( choice != 0) {
            showMenu();
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> displayListOfNames();
                case 2 -> loadListOfNames();
                case 3 -> saveListOfNames();
                case 4 -> enterNames();
                case 0 -> exit();
                default -> System.out.println("Unknown command - please use 0-4");
            }

        }
    }

    private void showMenu() {
        System.out.println("""
                1) Display list of names
                2) Load list of names (not implemented)
                3) Save list of names (not implemented)
                4) Enter names
                0) Exit
                
                """);
    }

    private void enterNames() throws FileNotFoundException {
        System.out.println("""
                Enter names
                -----------
                Enter each name you want to add to the list. End by entering an empty name.
                """);
        Scanner sc = new Scanner(System.in);
        String name = "-nothing yet-";
        while(!name.isBlank() && sc.hasNextLine()) {
            name = sc.nextLine();
            if(!name.isBlank()) {
                names.add(name);
                System.out.println(name + " added to the list, enter another, or empty to quit");
            }
        }
        System.out.println("Done");
    }

    private void saveListOfNames() throws FileNotFoundException {

        PrintStream toFile = new PrintStream(FILE_EXTENSION);// brug hellere "/" ikke"\\" da de virker på alle platforme.
        for (String name : names){
            toFile.println(name);
        }


    }

    private void loadListOfNames() throws FileNotFoundException {

        Scanner sx = new Scanner(new File(FILE_EXTENSION));
        String name = "-nothing yet-";
        while (!name.isBlank() && sx.hasNextLine()) {
            name = sx.nextLine();
            if (!name.isBlank()) {
                names.add(name);
            }
        }
        System.out.println(" File loaded to the list, enter another, or empty to quit");
    }

    private void displayListOfNames() {
        for(String name : names) {
            System.out.println(name);
        }
        String isAre = "are";
        String s = "s";
        if(names.size() == 1) {
            isAre = "is";
            s = "";
        }
        System.out.println("There " + isAre + " " + names.size() + " name"+s+" in the system");
    }

    private void exit() {
        System.out.println("""
                ...
                Thank you for using NamesList - enterprise edition.
                Don't forget to subscribe to our newsletter!""");

    }


    public static void main(String[] args) throws FileNotFoundException {
        NamesList app = new NamesList();
        app.startUserInterface();
    }
}
