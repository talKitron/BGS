package view;

import java.awt.Cursor;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import utilities.SwingAnimation;

/**
 * TableFrame frame for the game.
 *
 * @author BGS Team
 */
public class TableFrame extends javax.swing.JFrame {

    private Image image;
    /**
     * ViewLogic field
     */
    private final View view;
    /**
     * Current game object
     */
    private static Game currentGame;
    /**
     * Current Dealer Hand JLabels
     */
    private static ArrayList<JLabel> dealerHand;
    /**
     * Current Player Hand JLabels
     */
    private static ArrayList<JLabel> playerHand;
    /**
     * Current Deck of Cards JLabels
     */
    private static ArrayList<JLabel> deckOfCards;
    /**
     * Animation manager object
     */
    public static SwingAnimation sa;
    /**
     * Current score watcher for score update effect
     */
    private static int currentScore = 0;

    /**
     * Creates new form TableFrame
     *
     * @param view
     * @param game
     */
    public TableFrame(View view, Game game) {
        this.view = view;
        currentGame = game;
        dealerHand = new ArrayList<>();
        playerHand = new ArrayList<>();
        deckOfCards = new ArrayList<>();
        sa = new SwingAnimation();
        initComponents();
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            if (Constants.DEBUG) {
                System.out.println(e.getMessage());
            }
        }
        setLocationRelativeTo(null);
        addWindowListener(new MyWindowListener());
        pnlMenuInGame.setVisible(false);
        pnlMenu.setOpaque(false);
        pnlShowWinner.setVisible(false);
        lblShuffling.setVisible(false);
        updateScoreBoard();
        view.setFrameIcon(this);
        btnDeal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnHit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnQuit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnStand.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSurrender.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblWhiskey.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnStand) {
                    btnStandActionPerformed(e);
                } else if (e.getSource() == btnHit) {
                    btnHitActionPerformed(e);
                } else if (e.getSource() == btnSurrender) {
                    btnSurrenderActionPerformed(e);
                } else if (e.getSource() == btnDeal) {
                    btnDealActionPerformed(e);
                } else if (e.getSource() == btnQuit) {
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
            } else if (question == JOptionPane.YES_OPTION) {
                view.executeSysExit(false);
            /*} else if (question == JOptionPane.CANCEL_OPTION) {
                //do nothing
            }*/
            }
        }
    }

    /**
     * The method resets the in-game UI components
     */
    private void clearGUI() {
        pnlShowWinner.setVisible(false);
        for (JLabel l : playerHand) {
            mainDesktopPane.remove(l);
            l = null;
        }
        playerHand = new ArrayList<>();
        for (JLabel l : dealerHand) {
            mainDesktopPane.remove(l);
            l = null;
        }
        dealerHand = new ArrayList<>();
        for (JLabel l : deckOfCards) {
            mainDesktopPane.remove(l);
            l = null;
        }
        deckOfCards = new ArrayList<>();
        System.gc();
    }

    /**
     * The method creates a deck of card animation and presents it to the UI
     */
    private void animateDeck() {
        clearGUI(); //clear earlier events of in-game animation

        final int[][] xy = new int[30][3];

        for (int k = 0; k < 11; k++) {
            if (k == 0) {
                xy[k][0] = Constants.DeckX;
                xy[k][1] = Constants.DeckY;
                xy[k][2] = xy[k][0] - 100;
            } else {
                xy[k][0] = xy[k - 1][0] + 2;
                xy[k][1] = xy[k - 1][1] + 2;
                if (k % 2 == 0) {
                    xy[k][2] = xy[k][0] + 100;
                } else {
                    xy[k][2] = xy[k][0] - 100;
                }
            }
        }

        int i = 0, y = 0;
        JLabel label;

        for (Card c : currentGame.getDeck().getCardDeck()) {
            if (i % 5 == 0) {
                label = new JLabel(new ImageIcon(getClass().getResource("/resources/cards/HiddenCard.png")));
                label.setBounds(xy[y][0], -200, 117, 170);
                deckOfCards.add(label);
                mainDesktopPane.add(label, -1);
                sa.jLabelYDown(-200, xy[y][1], Constants.DECK_DEAL_SLOW, Constants.DECK_DEAL_SLOW, label);
                y++;
            }
            i += 1;
        }

        /*new java.util.Timer().schedule( 
         new java.util.TimerTask() {
         @Override
         public void run() {
         int i = 0, y = 0;
         JLabel label;

         for (Card c : currentGame.getDeck().getCardDeck()){
         if (i%5==0){
         label = new JLabel(new ImageIcon(getClass().getResource("/resources/cards/HiddenCard.png")));
         label.setBounds(xy[y][0], -200, 117, 170);
         deckOfCards.add(label);
         mainDesktopPane.add(label, -1);
         sa.jLabelYDown(-200, xy[y][1], Constants.DECK_DEAL_SLOW, Constants.DECK_DEAL_SLOW, label);
         y++;
         }
         i+=1;
         }
         }
         }, 
         2500
         );*/
    }

    /**
     * The method creates a shuffling GIF animation and presents it to the UI while also playing a sound.
     * NOTICE: NOT IN USE, animateShuffle() instead.
     */
    private void animateGIFShuffle() {
        view.playSound("Shuffle");
        lblShuffling.setVisible(true);

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        lblShuffling.setVisible(false);
                    }
                },
                2500
        );
    }

    /**
     * The method creates a deck of card shuffle animation and presents it to the UI NOTICE: NOT YET FUNCTION
     */
    private void animateShuffle() {
        view.playSound("GameStart");
        //animateDivide();
        //animateJoin();
        final ArrayList<JLabel> left = new ArrayList<>();
        final ArrayList<JLabel> right = new ArrayList<>();
        int i = 0;
        for (JLabel jl : deckOfCards) {
            if (i % 2 == 0) {
                left.add(jl);
            } else {
                right.add(jl);
            }
            i++;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < Constants.DECK_SHUFFLE_ANIMATION) {
                    sa.jLabelXSideAndReturn(Constants.CARDS_SHUFFLE_PDISTANCE, Constants.DECK_DEAL_SLOW, Constants.DECK_DEAL_FAST, left, false);
                    sa.jLabelXSideAndReturn(Constants.CARDS_SHUFFLE_PDISTANCE, Constants.DECK_DEAL_SLOW, Constants.DECK_DEAL_FAST, right, true);
                    i++;
                    try {
                        Thread.sleep(deckOfCards.size() * 2 * Constants.CARDS_SHUFFLE_PDISTANCE / 2);
                    } catch (InterruptedException ex) {
                        if (Constants.DEBUG) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }
        }).start();
    }

    /**
     * NOTICE: NOT IN USE, better implementation at utilities/SwingAnimation
     *
     * @param x
     */
    private void animateDivide() {
        int i = 0;

        for (JLabel l : deckOfCards) {
            if (i % 2 == 0) {
                sa.jLabelXLeft(l.getLocation().x, l.getLocation().x - 100, 1, 1, l);
            } else {
                sa.jLabelXRight(l.getLocation().x, l.getLocation().x + 100, 1, 1, l);
            }
            i++;
        }
    }

    /**
     * NOTICE: NOT IN USE, better implementation at utilities/SwingAnimation
     *
     * @param x
     */
    private void animateJoin() {
        int i = 0;

        for (JLabel l : deckOfCards) {
            if (i % 2 == 0) {
                sa.jLabelXRight(l.getLocation().x, l.getLocation().x + 100, 1, 1, l);
            } else {
                sa.jLabelXLeft(l.getLocation().x, l.getLocation().x - 100, 1, 1, l);
            }
            i++;
        }
    }

    /**
     * Deals cards to current Hand
     *
     * @param hand (Player's Hand / Dealer)
     * @param hit (true if it's a Hit, false if it's initial cards)
     * @param player (String to represent the user)
     */
    private void dealCards(Hand hand, boolean hit, String player) {
        JLabel label;
        switch (player) {
            case "Player":
                if (hit) {
                    JLabel cardFromDeck = deckOfCards.get(deckOfCards.size() - 1);
                    deckOfCards.remove(cardFromDeck);
                    mainDesktopPane.remove(cardFromDeck);
                    label = dealCard(playerHand.get(hand.getNextIndex() - 2), hand.getCards()[hand.getNextIndex() - 1], "Player");
                } else {
                    JLabel firstCard = dealCard(null, hand.getCards()[hand.getNextIndex() - 2], "Player");
                    playerHand.add(firstCard);
                    mainDesktopPane.add(firstCard, 1);
                    label = dealCard(playerHand.get(hand.getNextIndex() - 2), hand.getCards()[hand.getNextIndex() - 1], "Player");
                }
                playerHand.add(label);
                mainDesktopPane.add(label, 1);
                break;
            case "Dealer":
                if (hit) {
                    JLabel cardFromDeck = deckOfCards.get(deckOfCards.size() - 1);
                    deckOfCards.remove(cardFromDeck);
                    mainDesktopPane.remove(cardFromDeck);
                    label = dealCard(dealerHand.get(hand.getNextIndex() - 2), hand.getCards()[hand.getNextIndex() - 1], "Dealer");
                } else {
                    label = dealCard(null, hand.getCards()[hand.getNextIndex() - 2], "Dealer");
                    dealerHand.add(label);
                    mainDesktopPane.add(label, 1);
                    label = dealCard(dealerHand.get(hand.getNextIndex() - 2), hand.getCards()[hand.getNextIndex() - 1], "Hidden");
                }
                dealerHand.add(label);
                mainDesktopPane.add(label, 1);
                break;
        }
    }

    /**
     * Creates an animation of a JLabel card from x to y.
     *
     * @param cardToDeal
     * @param x
     * @param y
     */
    private void animateDealCard(JLabel cardToDeal, int x, int y) {
        SwingAnimation acDealer = new SwingAnimation();
        if (x > y) { //dealt to Player
            acDealer.jLabelYDown(Constants.DeckY, y, Constants.DECK_DEAL_SLOW, Constants.DECK_DEAL_SLOW, cardToDeal);
            acDealer.jLabelXLeft(Constants.DeckX, x, Constants.DECK_DEAL_SLOW, Constants.DECK_DEAL_SLOW, cardToDeal);
        } else { //dealt to Dealer
            acDealer.jLabelXLeft(Constants.DeckX, x, Constants.DECK_DEAL_SLOW, Constants.DECK_DEAL_SLOW, cardToDeal);
            acDealer.jLabelYDown(Constants.DeckY, y, Constants.DECK_DEAL_SLOW, Constants.DECK_DEAL_SLOW, cardToDeal);
        }
    }

    /**
     * The method is a utility method for dealCards(), it creates a new JLabel and animates movement from Deck of Cards to the current player.
     *
     * @param lastCard
     * @param cardToDeal
     * @param player
     * @return the moved JLabel card.
     */
    private JLabel dealCard(JLabel lastCard, Card cardToDeal, String type) {
        JLabel dealtCard = new JLabel(new ImageIcon(getClass().getResource(view.getCardImage(cardToDeal))));

        switch (type) {
            case "Player":
                if (lastCard == null) { //first card
                    dealtCard.setBounds(Constants.DeckX, Constants.DeckY, 117, 170);
                    animateDealCard(dealtCard, Constants.PlayerX, Constants.PlayerY);
                } else { //any other card
                    dealtCard.setBounds(Constants.DeckX, Constants.DeckY, 117, 170);
                    animateDealCard(dealtCard, Constants.PlayerX + (playerHand.size() * 25), Constants.PlayerY);
                }
                break;
            case "Dealer":
                dealtCard.setBounds(Constants.DeckX, Constants.DeckY, 117, 170);
                animateDealCard(dealtCard, Constants.DealerX + (dealerHand.size() * 25), Constants.DealerY);
                break;
            case "Hidden":
                dealtCard.setIcon(new ImageIcon(getClass().getResource("/resources/cards/HiddenCard.png")));
                dealtCard.setBounds(Constants.DeckX, Constants.DeckY, 117, 170);
                animateDealCard(dealtCard, Constants.DealerX + (dealerHand.size() * 25), Constants.DealerY);
                break;
        }
        view.playSound("DealCard");
        return dealtCard;
    }

    /**
     * The method represents the Stand process of the Player and imitates Dealer\'s actions.
     */
    private void stand() {
        view.drawCard(dealerHand.get(1), currentGame.getDealer().getCards()[1]);
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (currentGame.getDealer().dealerHandValue() <= Constants.DEALER_STAND) {
                    if (currentGame.getDealer().dealerHandValue() < Constants.DEALER_STAND
                            || (currentGame.getDealer().dealerHandValue() == Constants.DEALER_STAND && currentGame.getDealer().isSoft())) {
                        Card card = view.stand(currentGame);
                        currentGame.getDealer().getCards()[currentGame.getDealer().getNextIndex()] = card;
                        currentGame.getDealer().setNextIndex(currentGame.getDealer().getNextIndex() + 1);
                        dealCards(currentGame.getDealer(), true, "Dealer");
                        if (Constants.DEBUG) {
                            System.out.println("Dealer next card is: " + card);
                        }
                        try {
                            Thread.sleep(Constants.STAND_DELAY);
                        } catch (InterruptedException ex) {
                            if (Constants.DEBUG) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    } else {
                        showWinner(false);
                        return;
                    }
                }
                showWinner(false);
            }
        }).start();
    }

    /**
     * The method represents the Surrender process for the Player.
     */
    private void surrender() {
        showWinner(true);
    }

    /**
     * The method is a utility method to update GUI about the game\'s victor.
     *
     * @param playerWon (true if Player won, false if Dealer did).
     * @return
     */
    private String gameWon(boolean playerWon) {
        if (playerWon) {
            pnlMenuInGame.setVisible(false);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            view.playSound("Win");
                        }
                    },
                    1000
            );
            return "WON";
        } else { //Dealer won
            view.playSound("Lose");
            pnlMenuInGame.setVisible(false);
            return "LOST";
        }
    }

    /**
     * The method retrieves the most updated scores from the current Game and updates the ScoreBoard accordingly
     */
    private void updateScoreBoard() {
        lblPlayerImage.setIcon(new ImageIcon(getClass().getResource(view.getCurrentPlayer().getImagePath())));
        if (currentGame != null) {
            lblPlayerWins.setText("" + currentGame.getWins());
            lblPlayerLoses.setText("" + currentGame.getLoses());
            if (currentScore != currentGame.getScore()) {
                view.playSound("Coins");
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        int newScore = currentGame.getScore();
                        System.out.println("New score: " + newScore);
                        int difference = Math.abs(currentScore - newScore);
                        for (int i = 0; i <= difference; i++) {
                            if (currentScore > newScore) {
                                lblPlayerBank.setText("" + (currentScore - i));
                            } else {
                                lblPlayerBank.setText("" + (currentScore + i));
                            }
                            try {
                                Thread.sleep(70);
                            } catch (InterruptedException ex) {
                                if (Constants.DEBUG) {
                                    System.out.println(ex.getMessage());
                                }
                            }
                        }
                        currentScore = newScore;
                    }
                }).start();
            }
            //lblPlayerBank.setText("" + currentGame.getScore());
            if (currentGame.getRound() > 0) {
                lblRoundNumber.setText("Round " + currentGame.getRound());
            }
        }
    }

    /**
     * The method deals with what happens in the UI when a Game is won.
     */
    private void showWinner(boolean surrendered) {
        final int dealersHandValue = currentGame.getDealer().dealerHandValue();
        final int playersHandValue = currentGame.getPlayer().getCurrentHand().playerHandValue();
        final String result;
        if (surrendered) {
            result = "GAVE-UP";
            currentGame.playerLose();
            view.playSound("Lose");
        } else {
            result = gameWon(currentGame.whoWon());
        }
        pnlMenuInGame.setVisible(false);
        updateScoreBoard();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep((dealerHand.size() - 2) * Constants.STAND_DELAY);
                } catch (InterruptedException ex) {
                    if (Constants.DEBUG) {
                        System.out.println(ex.getMessage());
                    }
                }
                pnlMenu.setVisible(true);
                lblPlayerResult.setText("You " + result + "!");
                lblDealersHand.setText("    Dealer's Hand: " + dealersHandValue + ".");
                lblPlayersHand.setText("    " + currentGame.getPlayer().getName() + "'s Hand: " + playersHandValue + ".");
                pnlShowWinner.setVisible(true);
            }
        }).start();
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
        lblShuffling = new javax.swing.JLabel();
        lblWhiskey = new javax.swing.JLabel();
        pnlScoreBoard = new javax.swing.JPanel();
        btnInfo = new javax.swing.JButton();
        lblPlayerWins = new javax.swing.JLabel();
        lblPlayerLoses = new javax.swing.JLabel();
        lblPlayerBank = new javax.swing.JLabel();
        lblPlayerName = new javax.swing.JLabel();
        lblPlayerImage = new javax.swing.JLabel();
        lblRoundNumber = new javax.swing.JLabel();
        lblScoreBoard = new javax.swing.JLabel();
        pnlShowWinner = new javax.swing.JPanel();
        lblPlayerResult = new javax.swing.JLabel();
        lblDealersHand = new javax.swing.JLabel();
        lblPlayersHand = new javax.swing.JLabel();
        lblShowWinner = new javax.swing.JLabel();
        pnlMenu = new javax.swing.JPanel();
        btnDeal = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();
        pnlMenuInGame = new javax.swing.JPanel();
        btnHit = new javax.swing.JButton();
        btnStand = new javax.swing.JButton();
        btnSurrender = new javax.swing.JButton();
        pnlSettings = new javax.swing.JPanel();
        chkboxSound = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        mainDesktopPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mainDesktopPane.setPreferredSize(new java.awt.Dimension(1280, 850));

        lblShuffling.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/shuffling.gif"))); // NOI18N
        mainDesktopPane.add(lblShuffling);
        lblShuffling.setBounds(700, 140, 336, 189);

        lblWhiskey.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/whiskey.png"))); // NOI18N
        lblWhiskey.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblWhiskeyMouseClicked(evt);
            }
        });
        mainDesktopPane.add(lblWhiskey);
        lblWhiskey.setBounds(240, 510, 95, 100);

        pnlScoreBoard.setOpaque(false);

        btnInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/btnInfo.png"))); // NOI18N
        btnInfo.setBorderPainted(false);
        btnInfo.setContentAreaFilled(false);
        btnInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoActionPerformed(evt);
            }
        });

        lblPlayerWins.setFont(new java.awt.Font("GadMFW", 1, 15)); // NOI18N
        lblPlayerWins.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerWins.setText("0");

        lblPlayerLoses.setFont(new java.awt.Font("GadMFW", 1, 15)); // NOI18N
        lblPlayerLoses.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerLoses.setText("0");

        lblPlayerBank.setFont(new java.awt.Font("GadMFW", 1, 15)); // NOI18N
        lblPlayerBank.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerBank.setText("" + Constants.STARTING_AMOUNT);

        lblPlayerName.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        lblPlayerName.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayerName.setText(view.getCurrentPlayer().getName());
        lblPlayerName.setToolTipText("");

        lblRoundNumber.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        lblRoundNumber.setForeground(new java.awt.Color(255, 255, 255));
        lblRoundNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRoundNumber.setPreferredSize(new java.awt.Dimension(80, 13));

        lblScoreBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ScoreBoard.png"))); // NOI18N

        javax.swing.GroupLayout pnlScoreBoardLayout = new javax.swing.GroupLayout(pnlScoreBoard);
        pnlScoreBoard.setLayout(pnlScoreBoardLayout);
        pnlScoreBoardLayout.setHorizontalGroup(
            pnlScoreBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(lblPlayerBank, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(btnInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(lblPlayerLoses, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(lblRoundNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(lblPlayerWins, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblPlayerImage, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblScoreBoard)
        );
        pnlScoreBoardLayout.setVerticalGroup(
            pnlScoreBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addComponent(lblPlayerBank, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addComponent(lblPlayerLoses, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblRoundNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addComponent(lblPlayerWins, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(lblPlayerImage, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblScoreBoard))
        );

        mainDesktopPane.add(pnlScoreBoard);
        pnlScoreBoard.setBounds(1063, 0, 217, 407);

        pnlShowWinner.setOpaque(false);
        pnlShowWinner.setPreferredSize(new java.awt.Dimension(1280, 740));
        pnlShowWinner.setLayout(null);

        lblPlayerResult.setFont(new java.awt.Font("GadMFW", 0, 48)); // NOI18N
        lblPlayerResult.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerResult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlShowWinner.add(lblPlayerResult);
        lblPlayerResult.setBounds(452, 140, 380, 100);

        lblDealersHand.setFont(new java.awt.Font("GadMFW", 0, 36)); // NOI18N
        lblDealersHand.setForeground(new java.awt.Color(255, 255, 255));
        pnlShowWinner.add(lblDealersHand);
        lblDealersHand.setBounds(452, 240, 380, 50);

        lblPlayersHand.setFont(new java.awt.Font("GadMFW", 0, 36)); // NOI18N
        lblPlayersHand.setForeground(new java.awt.Color(255, 255, 255));
        pnlShowWinner.add(lblPlayersHand);
        lblPlayersHand.setBounds(452, 290, 380, 50);

        lblShowWinner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/showWinner.png"))); // NOI18N
        pnlShowWinner.add(lblShowWinner);
        lblShowWinner.setBounds(0, 0, 1280, 740);

        mainDesktopPane.add(pnlShowWinner);
        pnlShowWinner.setBounds(0, 0, 1280, 740);

        pnlMenu.setOpaque(false);
        pnlMenu.setPreferredSize(new java.awt.Dimension(1116, 150));

        btnDeal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Deal.png"))); // NOI18N
        btnDeal.setBorderPainted(false);
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
        btnQuit.setBorderPainted(false);
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
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(475, 475, 475)
                .addComponent(btnDeal, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeal, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        mainDesktopPane.add(pnlMenu);
        pnlMenu.setBounds(0, 675, 1112, 62);

        pnlMenuInGame.setOpaque(false);
        pnlMenuInGame.setPreferredSize(new java.awt.Dimension(1116, 150));

        btnHit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Hit.png"))); // NOI18N
        btnHit.setBorderPainted(false);
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
        btnStand.setBorderPainted(false);
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
        btnSurrender.setBorderPainted(false);
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
            .addGroup(pnlMenuInGameLayout.createSequentialGroup()
                .addGap(395, 395, 395)
                .addComponent(btnHit, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnStand, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnSurrender, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlMenuInGameLayout.setVerticalGroup(
            pnlMenuInGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuInGameLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlMenuInGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStand, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSurrender, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        mainDesktopPane.add(pnlMenuInGame);
        pnlMenuInGame.setBounds(0, 675, 1112, 62);

        pnlSettings.setOpaque(false);

        chkboxSound.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chkboxSound.setForeground(new java.awt.Color(255, 255, 255));
        chkboxSound.setSelected(true);
        chkboxSound.setText("Sound");
        chkboxSound.setContentAreaFilled(false);
        chkboxSound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkboxSoundActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSettingsLayout = new javax.swing.GroupLayout(pnlSettings);
        pnlSettings.setLayout(pnlSettingsLayout);
        pnlSettingsLayout.setHorizontalGroup(
            pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSettingsLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(chkboxSound))
        );
        pnlSettingsLayout.setVerticalGroup(
            pnlSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSettingsLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(chkboxSound))
        );

        mainDesktopPane.add(pnlSettings);
        pnlSettings.setBounds(1140, 590, 120, 71);

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
        int question = JOptionPane.showConfirmDialog(null, "Are you sure you want to stand-up the table and quit?", "Stand-Up", JOptionPane.YES_NO_OPTION);
        if (question == JOptionPane.YES_OPTION) { // if clicked YES
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
        view.playSound("Button");
        pnlMenu.setVisible(false);
        view.deal(currentGame);
        clearGUI();
        animateDeck();

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        animateShuffle();
                    }
                },
                1100
        );

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        dealCards(currentGame.getDealer(), false, "Dealer");
                        dealCards(currentGame.getPlayer().getCurrentHand(), false, "Player");
                        pnlMenuInGame.setVisible(true);
                    }
                },
                4000
        );
    }//GEN-LAST:event_btnDealActionPerformed

    private void btnDealMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDealMouseExited
        btnDeal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Deal.png")));
    }//GEN-LAST:event_btnDealMouseExited

    private void btnDealMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDealMouseEntered
        btnDeal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonHover_Deal.png")));
    }//GEN-LAST:event_btnDealMouseEntered

    private void btnSurrenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSurrenderActionPerformed
        view.playSound("Button");
        int question = JOptionPane.showConfirmDialog(null, "Are you sure you want to surrender and lose the round?", "Give-Up", JOptionPane.YES_NO_OPTION);
        if (question == JOptionPane.YES_OPTION) { // if clicked YES
            surrender();
        }
    }//GEN-LAST:event_btnSurrenderActionPerformed

    private void btnSurrenderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSurrenderMouseExited
        btnSurrender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Surrender.png")));
    }//GEN-LAST:event_btnSurrenderMouseExited

    private void btnSurrenderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSurrenderMouseEntered
        btnSurrender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonHover_Surrender.png")));
    }//GEN-LAST:event_btnSurrenderMouseEntered

    private void btnStandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStandActionPerformed
        view.playSound("Button");
        if (!view.isBusted(currentGame)) {
            stand();
        }
    }//GEN-LAST:event_btnStandActionPerformed

    private void btnStandMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStandMouseExited
        btnStand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Stand.png")));
    }//GEN-LAST:event_btnStandMouseExited

    private void btnStandMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStandMouseEntered
        btnStand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonHover_Stand.png")));
    }//GEN-LAST:event_btnStandMouseEntered

    private void btnHitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitActionPerformed
        view.playSound("Button");
        currentGame.hit();
        dealCards(currentGame.getPlayer().getCurrentHand(), true, "Player");
        if (currentGame.isBusted()) {
            view.drawCard(dealerHand.get(1), currentGame.getDealer().getCards()[1]);

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            showWinner(false);
                        }
                    },
                    500
            );
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

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
        view.playSound("Button");
        InfoFrame frmRules = new InfoFrame(view);
        frmRules.setVisible(true);
    }//GEN-LAST:event_btnInfoActionPerformed

    private void lblWhiskeyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblWhiskeyMouseClicked
        view.playSound("OrderDrink");
        view.orderDrink(currentGame, "Whiskey");
        updateScoreBoard();
    }//GEN-LAST:event_lblWhiskeyMouseClicked

    private void chkboxSoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkboxSoundActionPerformed
        View.soundON = chkboxSound.isSelected();
    }//GEN-LAST:event_chkboxSoundActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeal;
    private javax.swing.JButton btnHit;
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnStand;
    private javax.swing.JButton btnSurrender;
    private javax.swing.JCheckBox chkboxSound;
    private javax.swing.JLabel lblDealersHand;
    private javax.swing.JLabel lblPlayerBank;
    private javax.swing.JLabel lblPlayerImage;
    private javax.swing.JLabel lblPlayerLoses;
    private javax.swing.JLabel lblPlayerName;
    private javax.swing.JLabel lblPlayerResult;
    private javax.swing.JLabel lblPlayerWins;
    private javax.swing.JLabel lblPlayersHand;
    private javax.swing.JLabel lblRoundNumber;
    private javax.swing.JLabel lblScoreBoard;
    private javax.swing.JLabel lblShowWinner;
    private javax.swing.JLabel lblShuffling;
    private javax.swing.JLabel lblWhiskey;
    private javax.swing.JDesktopPane mainDesktopPane;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlMenuInGame;
    private javax.swing.JPanel pnlScoreBoard;
    private javax.swing.JPanel pnlSettings;
    private javax.swing.JPanel pnlShowWinner;
    // End of variables declaration//GEN-END:variables
}
