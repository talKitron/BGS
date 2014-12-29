package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import model.Card;
import model.Game;
import model.Hand;
import utilities.Constants;
import AppPackage.AnimationClass;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * TableFrame frame for the game.
 * @author BGS Team
 */
public class TableFrame extends javax.swing.JFrame{
    private Image image;
    /**ViewLogic field*/
    private final View view;
    /**Current game object*/
    private static Game currentGame;
    /**Current Dealer Hand JLabels*/
    private static ArrayList<JLabel> dealerHand;
    /**Current Player Hand JLabels*/
    private static ArrayList<JLabel> playerHand;
    /**Current Deck of Cards JLabels*/
    private static ArrayList<JLabel> deckOfCards;
    /**Animation manager object*/
    public static AnimationClass ac;
    
    /**
     * Creates new form TableFrame
     * @param view
     * @param game
     */
    public TableFrame(View view, Game game) {
        this.view = view;
        TableFrame.currentGame = game;
        dealerHand = new ArrayList<>();
        playerHand = new ArrayList<>();
        deckOfCards = new ArrayList<>();
        ac = new AnimationClass();
        initComponents();
        setLocationRelativeTo(null);
        pnlMenuInGame.setVisible(false);
        pnlMenu.setOpaque(false);
        pnlShowWinner.setVisible(false);
        updateScoreBoard();
        
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btnStand){
                    btnStandActionPerformed(e);
                } else if (e.getSource() == btnHit){
                    btnHitActionPerformed(e);
                } else if (e.getSource() == btnSurrender){
                    btnSurrenderActionPerformed(e);
                } else if (e.getSource() == btnDeal){
                    btnDealActionPerformed(e);
                } else if (e.getSource() == btnQuit){
                    btnQuitActionPerformed(e);
                }
            }
        };
        
        KeyStroke keystroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false);
        btnStand.registerKeyboardAction(actionListener, keystroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        keystroke = KeyStroke.getKeyStroke(KeyEvent.VK_H, 0, false);
        btnHit.registerKeyboardAction(actionListener, keystroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        keystroke = KeyStroke.getKeyStroke(KeyEvent.VK_E, 0, false);
        btnSurrender.registerKeyboardAction(actionListener, keystroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        keystroke = KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false);
        btnDeal.registerKeyboardAction(actionListener, keystroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        keystroke = KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, false);
        btnQuit.registerKeyboardAction(actionListener, keystroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
    
    public class MyWindowListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            int question = JOptionPane.showConfirmDialog(null, "Save changes before exit?", "Save", JOptionPane.YES_NO_CANCEL_OPTION);
            if (question == JOptionPane.NO_OPTION) { // if user clicked NO, exit
                System.exit(0);
            }
            if (question == JOptionPane.YES_OPTION) {
                view.executeSysExit(false);
            }
            if (question == JOptionPane.CANCEL_OPTION) {
                //do nothing
            }
        }
    }
    
    /**
     * The method resets the in-game UI components
     */
    private void clearGUI(){
        pnlShowWinner.setVisible(false);
        for(JLabel l : playerHand){
            l.setVisible(false);
            l = null;
        }
        playerHand = new ArrayList<>();
        for(JLabel l : dealerHand){
            l.setVisible(false);
            l = null;
        }
        dealerHand = new ArrayList<>();
        for(JLabel l : deckOfCards){
            l.setVisible(false);
            l = null;
        }
        deckOfCards = new ArrayList<>();
    }
    
    /**
     * The method creates a deck of card animation and presents it to the UI
     */
    private void animateDeck(){
        clearGUI(); //clear earlier events of in-game animation
        JLabel label;
        int i = 0, x = 0;
        
        for (Card c : currentGame.getDeck().getCardDeck()){
            if (i%5==0){
                label = new JLabel(new ImageIcon(getClass().getResource("/resources/cards/HiddenCard.png")));
                label.setBounds(Constants.DeckX+x, -200, 117, 170);
                deckOfCards.add(label);
                mainDesktopPane.add(label, -1);
                ac.jLabelYDown(-200, Constants.DeckY+x-2, 1, 2, label);
                x+=4;
            }
            i+=1;
        }
        //animateShuffle();
    }
    
    /**
     * The method creates a deck of card shuffle animation and presents it to the UI
     * NOTICE: NOT YET FUNCTION
     */
    private void animateShuffle(){
        final int[] x = new int[deckOfCards.size()];
        
        animateDivide(x);
        System.out.println("Spreading complete, now gathering...");
        animateJoin(x);
    }
    
    /**
     * NOTICE: NOT YET FUNCTIONAL
     * @param x 
     */
    private void animateDivide(int[] x){
        AnimationClass ac1 = new AnimationClass();
        int i = 0;

        for(JLabel l : deckOfCards){
            x[i] = l.getLocation().x;
            if (i%2==0){ //is going left
                ac1.jLabelXLeft(l.getLocation().x, l.getLocation().x-100, 1, 1, l);
                System.out.println("currently at x: " + l.getLocation() + " ia going left to x: " + (l.getLocation().x-100));
            } else { //is going right
                ac1.jLabelXRight(l.getLocation().x, l.getLocation().x+100, 1, 1, l);
                System.out.println("currently at x: " + l.getLocation() + " is going right to x: " + (l.getLocation().x+100));
            }
            ac1.jLabelYDown(-100, Constants.DeckY, 1, 1, l);
            i++;
            try {
                Thread.sleep(120);
            } catch (InterruptedException ex) {
                Logger.getLogger(TableFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * NOTICE: NOT YET FUNCTIONAL
     * @param x 
     */
    private void animateJoin(int[] x){
        AnimationClass ac1 = new AnimationClass();
        int i = 0;
            
        for(JLabel l : deckOfCards){
            if (i%2==0){ //is at left
                System.out.println("Is left at x: " + (x[i]-150) + " going right to " + x[i]);
                ac1.jLabelXRight(l.getLocation().x-150, x[i], 1, 1, l);
            } else { //is at right
                System.out.println("Is right at x: " + (x[i]+150) + " going left to " + x[i]);
                ac1.jLabelXLeft(l.getLocation().x+150, x[i], 1, 1, l);
            }
            ac1.jLabelYDown(-100, Constants.DeckY, 1, 1, l);
            i++;
        }
    }
    
    /**
     * Deals cards to current Hand
     * @param hand (Player's Hand / Dealer)
     * @param hit (true if it's a Hit, false if it's initial cards)
     * @param player (String to represent the user)
     */
    private void dealCards(Hand hand, boolean hit, String player){
        JLabel label;
        switch (player) {
            case "Player":
                if (hit){
                    label = dealCard(playerHand.get(hand.getNextIndex()-2), hand.getCards()[hand.getNextIndex()-1], "Player");
                } else {
                    JLabel firstCard = dealCard(null, hand.getCards()[hand.getNextIndex()-2], "Player");
                    playerHand.add(firstCard);
                    mainDesktopPane.add(firstCard, 1);
                    //animateDealCard(firstCard, Constants.PlayerX, Constants.PlayerY);
                    label = dealCard(playerHand.get(hand.getNextIndex()-2), hand.getCards()[hand.getNextIndex()-1], "Player");
                }   playerHand.add(label);
                mainDesktopPane.add(label, 1);
                //animateDealCard(label, Constants.PlayerX + 25, Constants.PlayerY);
                break;
            case "Dealer":
                if (hit){
                    label = dealCard(dealerHand.get(hand.getNextIndex()-2), hand.getCards()[hand.getNextIndex()-1], "Dealer");
                } else {
                    JLabel firstCard = dealCard(null, hand.getCards()[hand.getNextIndex()-2], "Dealer");
                    dealerHand.add(firstCard);
                    mainDesktopPane.add(firstCard, 1);
                    //animateDealCard(firstCard, Constants.DealerX, Constants.DealerY);
                    label = dealCard(dealerHand.get(hand.getNextIndex()-2), hand.getCards()[hand.getNextIndex()-1], "Dealer");         
                }   dealerHand.add(label);
                mainDesktopPane.add(label, 1);
                //animateDealCard(label, dealerHand.get(hand.getNextIndex()-1).getLocation().x + 25, Constants.DealerY);
                break;
        }
    }
    
    /**
     * Creates an animation of a JLabel card from x to y.
     * @param cardToDeal
     * @param x
     * @param y 
     */
    private void animateDealCard(JLabel cardToDeal, int x, int y){
        //System.out.println(cardToDeal.getIcon().toString() + " x: " + x  + " y: " + y);
        AnimationClass acDealer = new AnimationClass();
        if (x > y){ //dealt to Player
            acDealer.jLabelYDown(Constants.DeckY, y, 1, 1, cardToDeal);
            acDealer.jLabelXLeft(Constants.DeckX, x, 1, 1, cardToDeal);
        } else { //dealt to Dealer
            acDealer.jLabelXLeft(Constants.DeckX, x, 1, 1, cardToDeal);
            acDealer.jLabelYDown(Constants.DeckY, y, 1, 1, cardToDeal);
        }
    }
    
    /**
     * The method is a utility method for dealCards(), it creates a new JLabel and animates movement from Deck of Cards to the current player.
     * @param lastCard
     * @param cardToDeal
     * @param player
     * @return the moved JLabel card.
     */
    private JLabel dealCard(JLabel lastCard, Card cardToDeal, String player){
        JLabel dealtCard = new JLabel(new ImageIcon(getClass().getResource(view.getCardImage(cardToDeal))));
        
        switch (player) {
            case "Player":
                if (lastCard == null){ //first card
                    dealtCard.setBounds(Constants.DeckX, Constants.DeckY, 117, 170);
                    animateDealCard(dealtCard, Constants.PlayerX, Constants.PlayerY);
                } else { //any other card
                    dealtCard.setBounds(Constants.DeckX, Constants.DeckY, 117, 170);
                    animateDealCard(dealtCard, Constants.PlayerX + (playerHand.size()* 25), Constants.PlayerY);
                }   break;
            case "Dealer":
                if (lastCard == null){ //first card
                    dealtCard.setIcon(new ImageIcon(getClass().getResource("/resources/cards/HiddenCard.png")));
                    dealtCard.setBounds(Constants.DeckX, Constants.DeckY, 117, 170);
                    animateDealCard(dealtCard, Constants.DealerX, Constants.DealerY);
                } else { //any other card
                    dealtCard.setBounds(Constants.DeckX, Constants.DeckY, 117, 170);
                    animateDealCard(dealtCard, Constants.DealerX + (dealerHand.size()* 25), Constants.DealerY);
                }   break;
        }
        return dealtCard;
    }
    
    /**
     * The method represents the Stand process of the Player and imitates Dealer\'s actions.
     */
    private void stand(){
        while(currentGame.getDealer().dealerHandValue() <= Constants.DEALER_STAND){
            if(currentGame.getDealer().dealerHandValue() < Constants.DEALER_STAND 
                    || (currentGame.getDealer().dealerHandValue() == Constants.DEALER_STAND && currentGame.getDealer().isSoft())){
                Card card = view.stand(currentGame);
                currentGame.getDealer().getCards()[currentGame.getDealer().getNextIndex()] = card;
                currentGame.getDealer().setNextIndex(currentGame.getDealer().getNextIndex() + 1);
                dealCards(currentGame.getDealer(), true, "Dealer");
                System.out.println("Dealer next card is: " + card);
            } else {
                return;
            }
        }  
    }
    
    /**
     * The method is a utility method to update GUI about the game\'s victor.
     * @param playerWon (true if Player won, false if Dealer did).
     * @return 
     */
    private String gameWon(boolean playerWon){
        if (playerWon){
            pnlMenuInGame.setVisible(false);
            return "WON";
        } else { //Dealer won
            pnlMenuInGame.setVisible(false);
            return "LOST";
        }
    }
    
    /**
     * The method retrieves the most updated scores from the current Game and updates the ScoreBoard accordingly
     */
    private void updateScoreBoard(){
        if (currentGame != null){
            lblPlayerWins.setText("" + currentGame.getWins());
            lblPlayerLoses.setText("" + currentGame.getLoses());
        }
        lblPlayerBank.setText("" + currentGame.getScore());
    }
    
    /**
     * The method deals with what happens in the UI when a Game is won.
     */
    private void showWinner(){
        /** TODO **/
        pnlMenuInGame.setVisible(false);
        pnlMenu.setVisible(true);
        
        lblPlayerResult.setText("You " + gameWon(currentGame.whoWon()) + "!");
        updateScoreBoard();
        pnlShowWinner.setVisible(true);
        /*
        hide pnlInGame (Hit/Stand/... buttons)
        show pnlMenu (Deal/Quit buttons)
        run updateScoreBoard();
            //create a new Panel (opaque=false) with a JLabel of the same size to be used as background (resources/showWinner.png).
            //make the Panel layout Absolute to be able to put labels upon eachother.
            //add labels to the panel to represent "You win/Lose" messages, make use of gameWon(boolean playerWon).
        show that panel to the user
        
        check logic for btnStandActionPerformed() and btnHitActionPerformed(). both are checking for win/loss and updating scoreboard, maybe no need for this way anymore.
            *** if you have time, try to add sounds to the application, you have the sound class you've used in the past (package: Sounds).
        */
    }
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainDesktopPane = new javax.swing.JDesktopPane(){
            @Override
            protected void paintComponent(Graphics g) {
                try {
                    image = new javax.swing.ImageIcon(getClass().getResource("/resources/tableFrame.png")).getImage();

                    if (g != null) {
                        g.drawImage(image,
                            (this.getSize().width - image.getWidth(null)) / 2,       // set the frame at the center of the screen
                            (this.getSize().height - image.getHeight(null)) / 2,
                            null);
                    }
                } catch (NullPointerException npe) {
                    if (Constants.DEBUG)
                    System.out.println("Can't find file!! Continue without the original background");
                }
            }
        };
        pnlShowWinner = new javax.swing.JPanel();
        lblPlayerResult = new javax.swing.JLabel();
        lblShowWinner = new javax.swing.JLabel();
        pnlScoreBoard = new javax.swing.JPanel();
        lblPlayerWins = new javax.swing.JLabel();
        lblPlayerLoses = new javax.swing.JLabel();
        lblPlayerBank = new javax.swing.JLabel();
        lblScoreBoard = new javax.swing.JLabel();
        lblPlayerName = new javax.swing.JLabel();
        pnlMenu = new javax.swing.JPanel();
        btnDeal = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();
        pnlMenuInGame = new javax.swing.JPanel();
        btnHit = new javax.swing.JButton();
        btnStand = new javax.swing.JButton();
        btnSurrender = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        mainDesktopPane.setPreferredSize(new java.awt.Dimension(1280, 850));

        pnlShowWinner.setOpaque(false);

        lblPlayerResult.setFont(new java.awt.Font("GadMFW", 0, 24)); // NOI18N
        lblPlayerResult.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerResult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblShowWinner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/showWinner.png"))); // NOI18N

        javax.swing.GroupLayout pnlShowWinnerLayout = new javax.swing.GroupLayout(pnlShowWinner);
        pnlShowWinner.setLayout(pnlShowWinnerLayout);
        pnlShowWinnerLayout.setHorizontalGroup(
            pnlShowWinnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlShowWinnerLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(lblPlayerResult, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblShowWinner)
        );
        pnlShowWinnerLayout.setVerticalGroup(
            pnlShowWinnerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlShowWinnerLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(lblPlayerResult, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblShowWinner)
        );

        pnlScoreBoard.setOpaque(false);

        lblPlayerWins.setFont(new java.awt.Font("GadMFW", 1, 15)); // NOI18N
        lblPlayerWins.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerWins.setText("0");

        lblPlayerLoses.setFont(new java.awt.Font("GadMFW", 1, 15)); // NOI18N
        lblPlayerLoses.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerLoses.setText("0");

        lblPlayerBank.setFont(new java.awt.Font("GadMFW", 1, 15)); // NOI18N
        lblPlayerBank.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerBank.setText("" + Constants.STARTING_AMOUNT);

        lblScoreBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ScoreBoard.png"))); // NOI18N

        lblPlayerName.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        lblPlayerName.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayerName.setText(view.getCurrentPlayer().getName());
        lblPlayerName.setToolTipText("");

        javax.swing.GroupLayout pnlScoreBoardLayout = new javax.swing.GroupLayout(pnlScoreBoard);
        pnlScoreBoard.setLayout(pnlScoreBoardLayout);
        pnlScoreBoardLayout.setHorizontalGroup(
            pnlScoreBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(lblPlayerBank, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(lblPlayerLoses, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(lblPlayerWins, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblScoreBoard)
        );
        pnlScoreBoardLayout.setVerticalGroup(
            pnlScoreBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(270, 270, 270)
                .addComponent(lblPlayerBank, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addComponent(lblPlayerLoses, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addComponent(lblPlayerWins, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblScoreBoard))
        );

        pnlMenu.setOpaque(false);
        pnlMenu.setPreferredSize(new java.awt.Dimension(1116, 150));

        btnDeal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Deal.png"))); // NOI18N
        btnDeal.setContentAreaFilled(false);
        btnDeal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDealMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDealMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDealMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDealMouseReleased(evt);
            }
        });
        btnDeal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDealActionPerformed(evt);
            }
        });

        btnQuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Quit.png"))); // NOI18N
        btnQuit.setContentAreaFilled(false);
        btnQuit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnQuitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnQuitMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnQuitMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnQuitMouseReleased(evt);
            }
        });
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                .addContainerGap(423, Short.MAX_VALUE)
                .addComponent(btnDeal, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(375, 375, 375))
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMenuLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeal, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlMenuInGame.setOpaque(false);
        pnlMenuInGame.setPreferredSize(new java.awt.Dimension(1116, 150));

        btnHit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Hit.png"))); // NOI18N
        btnHit.setContentAreaFilled(false);
        btnHit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHitMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHitMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnHitMouseReleased(evt);
            }
        });
        btnHit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitActionPerformed(evt);
            }
        });

        btnStand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Stand.png"))); // NOI18N
        btnStand.setContentAreaFilled(false);
        btnStand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnStandMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnStandMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnStandMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnStandMouseReleased(evt);
            }
        });
        btnStand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStandActionPerformed(evt);
            }
        });

        btnSurrender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Surrender.png"))); // NOI18N
        btnSurrender.setContentAreaFilled(false);
        btnSurrender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSurrenderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSurrenderMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSurrenderMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSurrenderMouseReleased(evt);
            }
        });
        btnSurrender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSurrenderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuInGameLayout = new javax.swing.GroupLayout(pnlMenuInGame);
        pnlMenuInGame.setLayout(pnlMenuInGameLayout);
        pnlMenuInGameLayout.setHorizontalGroup(
            pnlMenuInGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuInGameLayout.createSequentialGroup()
                .addContainerGap(418, Short.MAX_VALUE)
                .addComponent(btnHit, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStand, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSurrender, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(220, 220, 220))
        );
        pnlMenuInGameLayout.setVerticalGroup(
            pnlMenuInGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuInGameLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlMenuInGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSurrender, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStand, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainDesktopPaneLayout = new javax.swing.GroupLayout(mainDesktopPane);
        mainDesktopPane.setLayout(mainDesktopPaneLayout);
        mainDesktopPaneLayout.setHorizontalGroup(
            mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 1112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
            .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlShowWinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148)
                .addComponent(pnlScoreBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlMenuInGame, javax.swing.GroupLayout.PREFERRED_SIZE, 1112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(160, Short.MAX_VALUE)))
        );
        mainDesktopPaneLayout.setVerticalGroup(
            mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                        .addComponent(pnlScoreBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDesktopPaneLayout.createSequentialGroup()
                        .addGap(0, 220, Short.MAX_VALUE)
                        .addComponent(pnlShowWinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)))
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDesktopPaneLayout.createSequentialGroup()
                    .addContainerGap(654, Short.MAX_VALUE)
                    .addComponent(pnlMenuInGame, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        mainDesktopPane.setLayer(pnlShowWinner, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(pnlScoreBoard, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(pnlMenu, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(pnlMenuInGame, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainDesktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainDesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        //are you sure? if yes, open MainFrame; if Not, do nothing
        int question = JOptionPane.showConfirmDialog(null, "Are you sure you want to stand-up and quit the table?", "Stand-Up", JOptionPane.YES_NO_OPTION);
        if (question == JOptionPane.YES_OPTION) { // if clicked YES
            System.out.println("Bye.");
            view.executeSysExit(false);
        }
    }//GEN-LAST:event_btnQuitActionPerformed

    private void btnQuitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuitMouseExited
        btnQuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Quit.png")));
    }//GEN-LAST:event_btnQuitMouseExited

    private void btnQuitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuitMouseEntered
        btnQuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonHover_Quit.png")));
    }//GEN-LAST:event_btnQuitMouseEntered

    private void btnDealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDealActionPerformed
        clearGUI();
        pnlMenu.setVisible(false);
        view.deal(currentGame);
        pnlMenuInGame.setVisible(true);
        animateDeck();
        dealCards(currentGame.getPlayer().getCurrentHand(), false, "Player");
        dealCards(currentGame.getDealer(), false, "Dealer");
    }//GEN-LAST:event_btnDealActionPerformed

    private void btnDealMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDealMouseExited
        btnDeal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Deal.png")));
    }//GEN-LAST:event_btnDealMouseExited

    private void btnDealMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDealMouseEntered
        btnDeal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonHover_Deal.png")));
    }//GEN-LAST:event_btnDealMouseEntered

    private void btnSurrenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSurrenderActionPerformed
        System.out.println("Never surrender!!!");
    }//GEN-LAST:event_btnSurrenderActionPerformed

    private void btnSurrenderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSurrenderMouseExited
        btnSurrender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Surrender.png")));
    }//GEN-LAST:event_btnSurrenderMouseExited

    private void btnSurrenderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSurrenderMouseEntered
        btnSurrender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonHover_Surrender.png")));
    }//GEN-LAST:event_btnSurrenderMouseEntered

    private void btnStandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStandActionPerformed
        System.out.println("I'm still standing you just fade away.");
        if(!view.isBusted(currentGame)){
           stand();
           //gameWon(view.whoWon(currentGame));
           //updateScoreBoard();
           showWinner();
        }
    }//GEN-LAST:event_btnStandActionPerformed

    private void btnStandMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStandMouseExited
        btnStand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Stand.png")));
    }//GEN-LAST:event_btnStandMouseExited

    private void btnStandMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStandMouseEntered
        btnStand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonHover_Stand.png")));
    }//GEN-LAST:event_btnStandMouseEntered

    private void btnHitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitActionPerformed
        System.out.println("Hit me baby, one more time!");
        currentGame.hit();
        dealCards(currentGame.getPlayer().getCurrentHand(), true, "Player");
        if (currentGame.isBusted()){
            //gameWon(currentGame.whoWon());
            //updateScoreBoard();
            showWinner();
        }
    }//GEN-LAST:event_btnHitActionPerformed

    private void btnHitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHitMouseExited
        btnHit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Hit.png")));
    }//GEN-LAST:event_btnHitMouseExited

    private void btnHitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHitMouseEntered
        btnHit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonHover_Hit.png")));
    }//GEN-LAST:event_btnHitMouseEntered

    private void btnHitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHitMousePressed
        btnHit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonActive_Hit.png")));
    }//GEN-LAST:event_btnHitMousePressed

    private void btnHitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHitMouseReleased
        btnHit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Hit.png")));
    }//GEN-LAST:event_btnHitMouseReleased

    private void btnStandMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStandMousePressed
        btnStand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonActive_Stand.png")));
    }//GEN-LAST:event_btnStandMousePressed

    private void btnStandMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStandMouseReleased
        btnStand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Stand.png")));
    }//GEN-LAST:event_btnStandMouseReleased

    private void btnSurrenderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSurrenderMousePressed
        btnSurrender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonActive_Surrender.png")));
    }//GEN-LAST:event_btnSurrenderMousePressed

    private void btnSurrenderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSurrenderMouseReleased
        btnSurrender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Surrender.png")));
    }//GEN-LAST:event_btnSurrenderMouseReleased

    private void btnDealMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDealMousePressed
        btnDeal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonActive_Deal.png")));
    }//GEN-LAST:event_btnDealMousePressed

    private void btnDealMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDealMouseReleased
        btnDeal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Deal.png")));
    }//GEN-LAST:event_btnDealMouseReleased

    private void btnQuitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuitMousePressed
        btnQuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonActive_Quit.png")));
    }//GEN-LAST:event_btnQuitMousePressed

    private void btnQuitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuitMouseReleased
        btnQuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Quit.png")));
    }//GEN-LAST:event_btnQuitMouseReleased
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeal;
    private javax.swing.JButton btnHit;
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnStand;
    private javax.swing.JButton btnSurrender;
    private javax.swing.JLabel lblPlayerBank;
    private javax.swing.JLabel lblPlayerLoses;
    private javax.swing.JLabel lblPlayerName;
    private javax.swing.JLabel lblPlayerResult;
    private javax.swing.JLabel lblPlayerWins;
    private javax.swing.JLabel lblScoreBoard;
    private javax.swing.JLabel lblShowWinner;
    private javax.swing.JDesktopPane mainDesktopPane;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlMenuInGame;
    private javax.swing.JPanel pnlScoreBoard;
    private javax.swing.JPanel pnlShowWinner;
    // End of variables declaration//GEN-END:variables
}
