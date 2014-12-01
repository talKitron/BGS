
package view;

//*************************************************** Imports *****************************************************//

import controller.Controller;
import exceptions.CheckIntException;
import exceptions.CheckLettersException;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Contact;
import utilities.InputChecksClass;

/**
 * @author Rawan
 */
public final class View {

//*************************************************** Variables *****************************************************// 
    /**
     * Singleton instance of this class, loaded on the first execution of ViewLogic.getInstance()
     */
    private static View instance;
    /**
     * the loginFrame
     */
    private static frm_IPhoneBook frm_IPhoneBook;
    /**
     * Boolean flag for class instance existence (singleton)
     */
    private static boolean exist = false;
    /**
     * ControllerLogic reference pointer
     */
    private static Controller controller;

//*************************************************** Constructors *************************************************
    /**
     * empty constructor.
     */
    public View() {
    }
//*************************************************** Methods *************************************************

    /**
     * The method creates this class's instance & provides access to it, by returning a reference (singleton).
     * @param ins
     * @return reference to this class's only instance, or null if reference was already returned (singleton).
     */
    public static View getInstance(Controller ins) {
        if (!exist) {
            controller = ins;
        }
        if (controller != null) {     // if controller is null create new viewLogic object
            exist = true;
            instance = new View();
            return instance;
        }
        return null;
    }
    /**
     * create new frm_IPhoneBook
     */
    public void executeLoginView() {
        frm_IPhoneBook = new frm_IPhoneBook(instance);      // create new login frame
    }
     public void addTuple(JTextField startPhoneNumber, JTextField endPhoneNumber, JTextField first, JTextField last, DefaultTableModel model) {
       
         try{
             InputChecksClass.integerTest(first.getText(), "First Name");
             InputChecksClass.integerTest(last.getText(), "Last Name");
             InputChecksClass.lettersTest(startPhoneNumber.getText(), "Start Phone Number");
             InputChecksClass.lettersTest(endPhoneNumber.getText(), "End Phone Number");
             
               if (controller.addTuple(startPhoneNumber.getText(), endPhoneNumber.getText(), first.getText(), last.getText())) {
                    model.addRow(new Object[]{startPhoneNumber.getText()+ "-" +endPhoneNumber.getText(),first.getText(),last.getText()});
                    sound("/Sounds/ok.wav");
                    JOptionPane.showMessageDialog(null, first.getText()+ " " + last.getText() + " was added succesfully to PhoneBook", "Added Message", JOptionPane.INFORMATION_MESSAGE);
                    clearFields(startPhoneNumber, endPhoneNumber, first, last);
               }
               else{
                   sound("/Sounds/error.wav");
                   JOptionPane.showMessageDialog(null,"Contact with the number: " + startPhoneNumber.getText()+ "-" + endPhoneNumber.getText() + " already exists in PhoneBook!", "Error Message", JOptionPane.ERROR_MESSAGE);
               }
         } catch (CheckIntException | CheckLettersException ex) {
            ex.getStackTrace();
        }
     }
         
    

    public void modifyTuple(JTextField startPhoneNumber, JTextField endPhoneNumber, JTextField first, JTextField last,String phoneBeforeChanging, DefaultTableModel model, JTable tblPhoneBook) {
        try{
             InputChecksClass.integerTest(first.getText(), "First Name");
             InputChecksClass.integerTest(last.getText(), "Last Name");
             InputChecksClass.lettersTest(startPhoneNumber.getText(), "Start Phone Number");
             InputChecksClass.lettersTest(endPhoneNumber.getText(), "End Phone Number");
             
               if (controller.modifyTuple(startPhoneNumber.getText(), endPhoneNumber.getText(), first.getText(), last.getText(),phoneBeforeChanging)) {
                    model.setValueAt(startPhoneNumber.getText()+"-"+endPhoneNumber.getText(), tblPhoneBook.getSelectedRow(), 0);
                    model.setValueAt(first.getText(), tblPhoneBook.getSelectedRow(), 1);
                    model.setValueAt(last.getText(), tblPhoneBook.getSelectedRow(), 2);
                    sound("/Sounds/ok.wav");
                    JOptionPane.showMessageDialog(null, first.getText()+ " " + last.getText() + " was Modified succesfully!", "Modified Message", JOptionPane.INFORMATION_MESSAGE);
                    clearFields(startPhoneNumber, endPhoneNumber, first, last);
               }
               else{
                   sound("/Sounds/error.wav");
                   JOptionPane.showMessageDialog(null,"Contact " + first.getText()+ " " + last.getText() + " can't be modified!", "Error Message", JOptionPane.ERROR_MESSAGE);
               }
         } catch (CheckIntException | CheckLettersException ex) {
            ex.getStackTrace();
        }
    }

    public void deleteTuple(JTextField startPhoneNumber, JTextField endPhoneNumber,DefaultTableModel model, JTable tblPhoneBook) {
       
        if (controller.deleteTuple(startPhoneNumber.getText(), endPhoneNumber.getText())) {
                    sound("/Sounds/ok.wav");
                    model.removeRow(tblPhoneBook.getSelectedRow());
                    JOptionPane.showMessageDialog(null, "Contact with phone number: " + startPhoneNumber.getText()+ "-" + endPhoneNumber.getText() + " was Deleted succesfully!", "Deleted Message", JOptionPane.INFORMATION_MESSAGE);
               }
               else{
                   sound("/Sounds/error.wav");
                   JOptionPane.showMessageDialog(null,"Contact with phone number: " + startPhoneNumber.getText()+ "-" + endPhoneNumber.getText() + " can't be deleted!", "Error Message", JOptionPane.ERROR_MESSAGE);
               }
    }

    /**
     * @return the IPhoneBook
     */
    public HashMap<String, Contact> getPhoneBook() {
        return controller.getPhoneBook();
    }

        /**
     * the method gets address of sound file and return the sound for play.
     * @param soundAddress
     * @return 
     */
    public SoundClass sound(String soundAddress) {          ///////// sound method
        SoundClass sound = new SoundClass(soundAddress);
        return sound;
    }
    /**
     * the methods clear all fields that she get
     * using ellipsis
     * @param text 
     */
    public void clearFields(JTextField... text) {            // ellipsis method to clear fields
        for (JTextField t : text) {
            t.setText("");
        }
    }
    /**
     * method executes the system's exit
     * @param logOut
     * @throws IOException 
     */
    public void executeSysExit(boolean logOut) throws IOException {
        controller.executeSysExit(logOut);
    }

    }
