package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 * Utility class for generic tests in the system.
 *
 * @author BGS Team
 */
public class GenericTests {

    /**
     * This test verifies there are 52 cards when creating new deck.
     *
     * @return
     */
    public int simpleCardsTest() {

        int errorCode = 0;
        PrintWriter writer = null;

        try {
            writer = new PrintWriter("52 Cards Check Report.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            if (Constants.DEBUG) {
                System.out.println(ex.getMessage());
            }
        }
        writer.println(new Date());
        writer.println("----------------------------------------------------------");
        model.Deck myDeck = new model.Deck();
        writer.print(myDeck.toString());
        writer.close();

        File file = new File("52 Cards Check Report.txt");
        int lineCount = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException ex) {
            if (Constants.DEBUG) {
                System.out.println(ex.getMessage());
            }
        }
        String line;
        try {
            while ((line = br.readLine()) != null) {
                lineCount++;

            }
        } catch (IOException ex) {
            if (Constants.DEBUG) {
                System.out.println(ex.getMessage());
            }
        }
        if (lineCount != 54) {
            errorCode = 1;                       //lineCount should be 54. 2 lines for file header + 52 lines for cards.
        }
        if (myDeck.getCardDeck().length != 52) {
            errorCode = 2;   //myDeck.length needs to be 52
        }
        if (myDeck.getNumOfCard() != 52) {
            errorCode = 3;         //myDeck.NumOfCard needs to be 52
        }
        return errorCode;
    }

    /**
     * This test the UI by referring question to the user.
     *
     * @return
     */
    public int simpleUIVisabilityTest() {

        int errorCode = 0;
        String[] questions = new String[8];
        String[] userAnswers = new String[8];
        String options = " (Yes/No/Quit)";
        String errMsg = "Please answer Y/N/Q only.\n";
        String userName;
        int quesNum = 0;
        Scanner sc = new Scanner(System.in);

        questions[0] = "Do you see the main game window?";
        questions[1] = "Do you see the \"SUBMIT\" button?";
        questions[2] = "After pressing the \"SUBMIT\" button, did you got tranfered to the dealer? (pretty girl, can't miss it)";
        questions[3] = "Can you see the \"SIT DOWN\" button in the dealer window?";
        questions[4] = "After pressing the \"SIT DOWN\" button, did you got tranfered to the table?";
        questions[5] = "Do you see the \"DEAL\" button?";
        questions[6] = "Do you see the \"QUIT\" button?";
        questions[7] = "After pressing the \"DEAL\" button, the dealer gave you 2 cards?";

        System.out.print("Please enter your username: ");
        userName = sc.next();
        for (String question : questions) { //ask questions
            System.out.println(questions[quesNum] + options);
            userAnswers[quesNum] = sc.next();
            if ("Q".equals(userAnswers[quesNum])) {
                return errorCode = 1;
            }
            while (!"Y".equals(userAnswers[quesNum]) && !"N".equals(userAnswers[quesNum]) && !"Q".equals(userAnswers[quesNum])) {

                System.out.println(errMsg + questions[quesNum] + options);
                userAnswers[quesNum] = sc.next();
                if ("Q".equals(userAnswers[quesNum])) {
                    return errorCode = 1;
                }
            }
            quesNum++;
        }

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("UI Visibility Check Report.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            if (Constants.DEBUG) {
                System.out.println(ex.getMessage());
            }
        }
        writer.println(new Date());
        writer.println("----------------------------------------------------------");
        writer.println("User " + userName + " answered as followed:");
        writer.println(Arrays.toString(userAnswers));
        writer.close();

        if (quesNum < 8) {
            errorCode = 1;                                     //user didn't answer all the questions
        }
        if (Arrays.asList(userAnswers).contains("N")) {
            errorCode = 2;      //user answered "No" to one of the questions
        }
        return errorCode;
    }
}
