package controller;

/**
 * Main class to run the BGS system.
 *
 * @author BGS Team
 */
public class Main implements java.io.Serializable {

    /**
     * @param args the command line arguments
     */
    //private static Controller controller;

    public static void main(String[] args) {
        Controller.getInstance();
        //controller = Controller.getInstance();

        /**
         * Backend tests Block
         */
        /*
         Player player = new Player("elad", "123");
         Game game = controller.deal(player); 
         System.out.println("player value: " + player.getCurrentHand().playerHandValue());
         System.out.println("Dealer value: " + game.getDealer().dealerHandValue());
         while(player.getCurrentHand().playerHandValue()<17)
         {
         controller.hit(game);
         System.out.println("player value: " + player.getCurrentHand().playerHandValue());
         }
         if(!controller.isBusted(game))
         {
         controller.stand(game);
         controller.whoWon(game);
         }
        
         /**
         * Tests Block
         */
        /*
         GenericTests test = new GenericTests();
         int errorCode1 = test.SimpleCardsTest();    //run SimpleCardsTest
         if (errorCode1 != 0) try {
         throw new exceptions.CardDeckTestException("Error code "+errorCode1);
         } catch (CardDeckTestException ex) {
         if (Constants.DEBUG){
         System.out.print(ex.getMessage());
         }
         }
            
         int errorCode2 = test.SimpleUIVisabilityTest();     //run SimpleUIVisabilityTest
         if (errorCode2 != 0) try {
         throw new exceptions.UITestException("Error code "+errorCode2);
         } catch (UITestException ex) {
         Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
         }*/
    }

}
