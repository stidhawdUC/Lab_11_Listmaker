import com.sun.xml.internal.ws.addressing.WsaActionUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {
    static ArrayList<String> list = new ArrayList<>();
    static Scanner in = new Scanner(System.in);
    static boolean printMode = false;
    public static void main(String[] args)
    {
        //three steps to begin
        //display the list
        //display the menu
        //menu input

        final String mainMenu = "A - add | D - delete | I - insert | P - print | Q - quit";
        String menuCmd = "";
        boolean done = false;
        do{
            displayList();
            printMode = false;
            menuCmd = SafeInput.getRegExString(in, mainMenu, "^[AaDdIiPpQq]$");
            menuCmd = menuCmd.toUpperCase();
            // prompt given and input taken, now to execute the choice
            switch(menuCmd)
            {
                case "A":
                    addToList();
                    break;
                    // prompt user for the item
                    //use nonzerostring etc
                    // maybe additional check to make sure it's not over x characters?
                    //add to list
                case "D":
                    //prompt user for item number
                    //translate to index by subtracting 1
                    //remove item (review the video)
                    deleteFromList();
                    break;
                case "I":
                    insertInList();
                    break;
                case "P":
                    //use a boolean to find a way to display the list without numbers
                    if(list.size() != 0)
                    {
                        printMode = true;
                        System.out.printf("\n%61s", "Print Mode\n");
                    }
                    else System.out.println("\nThere's no list yet to print!\n");
                    break;
                case "Q":
                    done = SafeInput.getYNConfirm(in, "List will be lost.  Are you sure?");
                    break;  // I don't need this
            }

        }while(!done);
        System.out.println("\nGoodbye!\n");

    }

    private static void displayList()
    {
        for(int b = 0; b < 31; b++) System.out.print("*+");
            System.out.println("");

        if(list.size() != 0)
        {
            if(printMode == true) // "print mode"
            {
                for (int i = 0; i < list.size(); i++) System.out.printf("%60s\n", list.get(i));
                String printMessage = "Total Items: ";
                System.out.printf("%50s %9d\n", printMessage, list.size());
            }
            else // regular list mode
            {
                for (int i = 0; i < list.size(); i++) System.out.printf("%3d | %55s\n", i + 1, list.get(i));
            }

        }else
        {
            System.out.println("\n++++++ Welcome to the ListMaker! Use the menu to begin. ++++++\n");
        }

        for(int b = 0; b < 31; b++) System.out.print("+*"); // reversed order for style points
            System.out.println("");
    }

    private static void addToList() {
        boolean readyToAdd = false;
        String newItem = "";
        do{
        newItem = SafeInput.getNonZeroLenString(in, "Enter item");
        if (newItem.length() <= 55) {
            list.add(newItem);
            readyToAdd = true;
            System.out.println("Adding item...\n");
        } else System.out.println("Length of item is too long! Keep under 55 characters.");
    }while(!readyToAdd);

    }

    private static void deleteFromList()
    {
            int itemToDelete = SafeInput.getRangedInt(in, "Enter the number of the item " +
                    "to delete", 1, list.size());
            itemToDelete = itemToDelete - 1; // could just say itemToDelete++ but this is easier to read for me
            list.remove(itemToDelete);
        System.out.println("Deleting item...\n");
    }

    private static void insertInList()
    {
        boolean readyToInsert = false;
        String insertedItem = "";
            int insertLocation = SafeInput.getRangedInt(in, "Enter the number " +
                    "at which to insert", 1, list.size());
            insertLocation = insertLocation - 1;
        do
        {
            insertedItem = SafeInput.getNonZeroLenString(in, "Enter item");
            if (insertedItem.length() <= 55) {
                list.add(insertLocation, insertedItem);
                readyToInsert = true;
                System.out.println("Inserting item...\n");
            } else System.out.println("Length of item is too long! Keep under 55 characters.");
        }while(!readyToInsert);


    }
}