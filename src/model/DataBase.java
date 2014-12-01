package model;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public final class DataBase implements java.io.Serializable {
    //***************************************** Variables *********************************************

    /**
     * Singleton instance of this class, loaded on the first execution of SysData.getInstance()
     */
    private static DataBase instance;
    /**
     * Boolean flag for class instance existence (singleton)
     */
    private static boolean exists = false;
    /**
     * Stores all the terminals
     */
    private HashMap<String, Contact> IPhoneBooks;
    /**
     * Stores all the outgoing & arriving flights (departures & arrivals)
     */

    //***************************************** Constructors ******************************************
    private DataBase() {
        
        IPhoneBooks = new HashMap<String, Contact>();
        executeInput();
    }
    //***************************************** Getters & Setters *************************************

    /**
     * @return the IPhoneBook
     */
    protected HashMap<String, Contact> getIPhoneBook() {
        return IPhoneBooks;
    }

    //***************************************** Methods ***********************************************

    protected static void setInstance(DataBase sysData) {
        instance = sysData;
    }

    /**
     * The method creates this class's instance & provides access to it, by returning a reference (singleton).
     *
     * @return reference to this class's only instance, or null if reference was already returned (singleton).
     */
    protected static DataBase getInstance() {
           executeInput();
        if (!exists) {
            exists = true;
            instance = new DataBase();
            return instance;
        }
        return instance;
    }
    /**
     * the methods execute the input from file.
     */
    public static void executeInput() {
        try {
            FileInputStream fileIn = new FileInputStream("IPhoneBookDB.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            setInstance(((DataBase) in.readObject()));
            exists = true;
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.getStackTrace();
        }

    }
    /**
     * the methods execute the output to file.
     * @param logOut
     */
    protected void executeOutput(boolean logOut) {

        try {
            instance.IPhoneBooks = getIPhoneBook();
            FileOutputStream fileOut = new FileOutputStream("IPhoneBookDB.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(instance);
            out.close();
            fileOut.close();
            if (!logOut) {
                System.exit(0);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
