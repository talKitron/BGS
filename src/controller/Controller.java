package controller;
import java.io.IOException;
import java.util.HashMap;
import model.Model;
import view.View;

/**
 * Controller Logic class
 */
public final class Controller {

    /**
     * Singleton instance of this class, loaded on the first execution of ControllerLogic.getInstance()
     */
    private static Controller instance;
    /**
     * Boolean flag for class instance existence (singleton)
     */
    private static boolean exists = false;
    /**
     * ViewLogic reference pointer
     */
    private static View view; 
    /**
     * ModelLogic reference pointer
     */
    private static Model model; //assuming we've only one.
    //***************************************** Constructors ******************************************
    private Controller() {
       
    }
    //***************************************** Methods ***********************************************

    /**
     * The method creates this class's instance & provides access to it, by returning a reference (singleton).
     * @return reference to this class's only instance, or null if reference was already returned (singleton).
     */
    public static Controller getInstance() throws IOException {       
            if (!exists) {
                exists = true;
                model = Model.getInstance();
                instance = new Controller();
                view = View.getInstance(instance);
                view.executeLoginView();
                return instance;
            }     
        return instance;
    }													//all methods Will be overridden from Interface in 2nd HW!!
    //***************************************** CRUD Methods ******************************************
//    /**
//     * @return the PhoneBook
//     */
//    public HashMap<String, Contact> getPhoneBook() {
//        return model.getPhoneBook();
//    }
//    /**
//     * add contact
//     * @param startPhoneNumber
//     * @param endPhoneNumber
//     * @param first
//     * @param last
//     * @return 
//     */
//    public boolean addTuple(String startPhoneNumber,String endPhoneNumber, String first, String last) {
//        return model.addTuple(startPhoneNumber,endPhoneNumber ,first,last);
//    }
//    /**
//     * modify contact
//     * @param startPhoneNumber
//     * @param endPhoneNumber
//     * @param first
//     * @param last
//     * @param phoneBeforeChanging
//     * @return 
//     */
//    public boolean modifyTuple(String startPhoneNumber,String endPhoneNumber, String first, String last, String phoneBeforeChanging) {
//        return model.modifyTuple(startPhoneNumber,endPhoneNumber ,first,last,phoneBeforeChanging);
//    }
//    /**
//     * delete contact
//     * @param startPhoneNumber
//     * @param endPhoneNumber
//     * @return 
//     */
//    public boolean deleteTuple(String startPhoneNumber,String endPhoneNumber) {
//        return model.deleteTuple(startPhoneNumber,endPhoneNumber);
//    }
//     /**
//     * The method handle the exit from system.
//     * @param logOut
//     */
//    public void executeSysExit(boolean logOut) throws IOException {
//        model.executeSysExit(logOut);
//    }
}