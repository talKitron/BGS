package model;
import java.io.IOException;
import java.util.*;

/**
 * Model Logic class
 */
public final class Model implements I_Model, java.io.Serializable {
    //***************************************** Variables *********************************************
    /**
     * Singleton instance of this class, loaded on the first execution of ModelLogic.getInstance()
     */
    private static Model instance;
    /**
     * Boolean flag for class instance existence (singleton)
     */
    private static boolean exists = false;
    /**
     * SysData reference pointer
     */
    private static DataBase db;
    //***************************************** Constructors ******************************************
    private Model() {
       
    }
    //***************************************** Methods ***********************************************

    /**
     * The method creates this class's instance & provides access to it, by returning a reference (singleton).
     * @return reference to this class's only instance, or null if reference was already returned (singleton).
     * @throws GeneralException
     */
    public static Model getInstance() throws IOException {
        if (!exists) {
           
            db = DataBase.getInstance();
            if (db != null) {
                db.executeInput();
                exists = true;
                instance = new Model();
                return instance;
            }
        }
        return null;
    }

    public HashMap<String, Contact> getPhoneBook() {
        return db.getIPhoneBook();
    }

        @Override
    public boolean addTuple(String startPhoneNumber, String endPhoneNumber, String first, String last) {
            Contact contact = new Contact(first,last);
            if (!db.getIPhoneBook().containsKey(startPhoneNumber+""+endPhoneNumber)) {
                db.getIPhoneBook().put(startPhoneNumber+""+endPhoneNumber, contact);
                return true;
            }        
        return false;
    }

        @Override
    public boolean modifyTuple(String startPhoneNumber, String endPhoneNumber, String first, String last, String phoneBeforeChanging) {
            Contact contact = new Contact(first,last);
            if (!db.getIPhoneBook().containsKey(startPhoneNumber+""+endPhoneNumber)) {
                db.getIPhoneBook().put(startPhoneNumber+""+endPhoneNumber, contact);
                return true;
            }
            if (db.getIPhoneBook().containsKey(startPhoneNumber+""+endPhoneNumber)){ // if the number is the same and already exists check if the name was modify
                if(!(startPhoneNumber+"-"+endPhoneNumber).equals(phoneBeforeChanging)){
                    return false;
                }
                else{
                    db.getIPhoneBook().put(startPhoneNumber+""+endPhoneNumber, contact);
                    return true;
                }
            }
            return false;
    }

        @Override
    public boolean deleteTuple(String startPhoneNumber, String endPhoneNumber) {
        if (db.getIPhoneBook().containsKey(startPhoneNumber+""+endPhoneNumber)) {
            db.getIPhoneBook().remove(startPhoneNumber+""+endPhoneNumber);
            return true;
        }
        return false;
    }

    /**
     * The method handle the exit from system.
     * @param logOut
     * @throws java.io.IOException
     */
    public void executeSysExit(boolean logOut) throws IOException {
        db.executeOutput(logOut);
    }


}
