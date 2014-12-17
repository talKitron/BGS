package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import model.Game;
import utilities.Constants;

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

    /**TweenManager for animations and transitions*/
    //private static TweenManager tweenManager;
    
    /**
     * Creates new form TableFrame
     * @param view
     */
    public TableFrame(View view) {
        this.view = view;
        //tweenManager = new TweenManager();
        //Tween.registerAccessor(null, this);
        initComponents();
        setLocationRelativeTo(null);
        pnlMenuInGame.setVisible(false);
        pnlMenu.setOpaque(false);
        //pnlPlayerCards.setOpaque(false);

    }
    
    public class MyWindowListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            int question = JOptionPane.showConfirmDialog(null, "Save changes before exit?", "Save", JOptionPane.YES_NO_CANCEL_OPTION);
            if (question == JOptionPane.NO_OPTION) { // if user clicked NO, exit
                System.exit(0);
            }
            if (question == JOptionPane.YES_OPTION) { // if clicked YES, try to save his data
                try {
                    view.executeSysExit(false);
                    
                } catch (IOException ex) {
                    
                }
            }
        }
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
        pnlScoreBoard = new javax.swing.JPanel();
        lblScoreBoard = new javax.swing.JLabel();
        pnlMenu = new javax.swing.JPanel();
        btnDeal = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();
        pnlMenuInGame = new javax.swing.JPanel();
        btnHit = new javax.swing.JButton();
        btnStand = new javax.swing.JButton();
        btnSurrender = new javax.swing.JButton();
        laypPlayerCards = new javax.swing.JLayeredPane();
        lblPlayerCard7 = new javax.swing.JLabel();
        lblPlayerCard6 = new javax.swing.JLabel();
        lblPlayerCard5 = new javax.swing.JLabel();
        lblPlayerCard4 = new javax.swing.JLabel();
        lblPlayerCard3 = new javax.swing.JLabel();
        lblPlayerCard2 = new javax.swing.JLabel();
        lblPlayerCard1 = new javax.swing.JLabel();
        laypDealerCards = new javax.swing.JLayeredPane();
        lblDealerCard7 = new javax.swing.JLabel();
        lblDealerCard6 = new javax.swing.JLabel();
        lblDealerCard5 = new javax.swing.JLabel();
        lblDealerCard4 = new javax.swing.JLabel();
        lblDealerCard3 = new javax.swing.JLabel();
        lblDealerCard2 = new javax.swing.JLabel();
        lblDealerCard1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        mainDesktopPane.setPreferredSize(new java.awt.Dimension(1280, 850));

        pnlScoreBoard.setOpaque(false);

        lblScoreBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ScoreBoard.png"))); // NOI18N

        javax.swing.GroupLayout pnlScoreBoardLayout = new javax.swing.GroupLayout(pnlScoreBoard);
        pnlScoreBoard.setLayout(pnlScoreBoardLayout);
        pnlScoreBoardLayout.setHorizontalGroup(
            pnlScoreBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblScoreBoard)
        );
        pnlScoreBoardLayout.setVerticalGroup(
            pnlScoreBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlScoreBoardLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblScoreBoard)
                .addGap(35, 35, 35))
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

        lblPlayerCard3.setPreferredSize(new java.awt.Dimension(120, 170));

        lblPlayerCard2.setPreferredSize(new java.awt.Dimension(120, 170));

        lblPlayerCard1.setMaximumSize(new java.awt.Dimension(150, 200));
        lblPlayerCard1.setMinimumSize(new java.awt.Dimension(150, 200));
        lblPlayerCard1.setPreferredSize(new java.awt.Dimension(150, 200));

        javax.swing.GroupLayout laypPlayerCardsLayout = new javax.swing.GroupLayout(laypPlayerCards);
        laypPlayerCards.setLayout(laypPlayerCardsLayout);
        laypPlayerCardsLayout.setHorizontalGroup(
            laypPlayerCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
            .addGroup(laypPlayerCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(laypPlayerCardsLayout.createSequentialGroup()
                    .addGap(117, 117, 117)
                    .addGroup(laypPlayerCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(laypPlayerCardsLayout.createSequentialGroup()
                            .addGap(100, 100, 100)
                            .addComponent(lblPlayerCard6)
                            .addGap(20, 20, 20)
                            .addComponent(lblPlayerCard7))
                        .addGroup(laypPlayerCardsLayout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(lblPlayerCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(laypPlayerCardsLayout.createSequentialGroup()
                            .addGap(80, 80, 80)
                            .addComponent(lblPlayerCard5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(laypPlayerCardsLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(lblPlayerCard2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblPlayerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(laypPlayerCardsLayout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(lblPlayerCard3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        laypPlayerCardsLayout.setVerticalGroup(
            laypPlayerCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 254, Short.MAX_VALUE)
            .addGroup(laypPlayerCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(laypPlayerCardsLayout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(laypPlayerCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblPlayerCard6)
                        .addComponent(lblPlayerCard7)
                        .addComponent(lblPlayerCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPlayerCard5)
                        .addComponent(lblPlayerCard2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPlayerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPlayerCard3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(72, Short.MAX_VALUE)))
        );
        laypPlayerCards.setLayer(lblPlayerCard7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        laypPlayerCards.setLayer(lblPlayerCard6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        laypPlayerCards.setLayer(lblPlayerCard5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        laypPlayerCards.setLayer(lblPlayerCard4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        laypPlayerCards.setLayer(lblPlayerCard3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        laypPlayerCards.setLayer(lblPlayerCard2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        laypPlayerCards.setLayer(lblPlayerCard1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        lblDealerCard3.setPreferredSize(new java.awt.Dimension(120, 170));

        lblDealerCard2.setPreferredSize(new java.awt.Dimension(120, 170));

        lblDealerCard1.setMaximumSize(new java.awt.Dimension(150, 200));
        lblDealerCard1.setMinimumSize(new java.awt.Dimension(150, 200));
        lblDealerCard1.setPreferredSize(new java.awt.Dimension(150, 200));

        javax.swing.GroupLayout laypDealerCardsLayout = new javax.swing.GroupLayout(laypDealerCards);
        laypDealerCards.setLayout(laypDealerCardsLayout);
        laypDealerCardsLayout.setHorizontalGroup(
            laypDealerCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
            .addGroup(laypDealerCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(laypDealerCardsLayout.createSequentialGroup()
                    .addGap(117, 117, 117)
                    .addGroup(laypDealerCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(laypDealerCardsLayout.createSequentialGroup()
                            .addGap(100, 100, 100)
                            .addComponent(lblDealerCard6)
                            .addGap(20, 20, 20)
                            .addComponent(lblDealerCard7))
                        .addGroup(laypDealerCardsLayout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(lblDealerCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(laypDealerCardsLayout.createSequentialGroup()
                            .addGap(80, 80, 80)
                            .addComponent(lblDealerCard5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(laypDealerCardsLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(lblDealerCard2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblDealerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(laypDealerCardsLayout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(lblDealerCard3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(44, Short.MAX_VALUE)))
        );
        laypDealerCardsLayout.setVerticalGroup(
            laypDealerCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 254, Short.MAX_VALUE)
            .addGroup(laypDealerCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(laypDealerCardsLayout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(laypDealerCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblDealerCard6)
                        .addComponent(lblDealerCard7)
                        .addComponent(lblDealerCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDealerCard5)
                        .addComponent(lblDealerCard2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDealerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDealerCard3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(72, Short.MAX_VALUE)))
        );
        laypDealerCards.setLayer(lblDealerCard7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        laypDealerCards.setLayer(lblDealerCard6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        laypDealerCards.setLayer(lblDealerCard5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        laypDealerCards.setLayer(lblDealerCard4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        laypDealerCards.setLayer(lblDealerCard3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        laypDealerCards.setLayer(lblDealerCard2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        laypDealerCards.setLayer(lblDealerCard1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout mainDesktopPaneLayout = new javax.swing.GroupLayout(mainDesktopPane);
        mainDesktopPane.setLayout(mainDesktopPaneLayout);
        mainDesktopPaneLayout.setHorizontalGroup(
            mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 1112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
            .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(laypPlayerCards, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(298, 298, 298))
                    .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(laypDealerCards, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                    .addGroup(mainDesktopPaneLayout.createSequentialGroup()
                        .addContainerGap(77, Short.MAX_VALUE)
                        .addComponent(laypDealerCards, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(laypPlayerCards, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)))
                .addComponent(pnlMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(mainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainDesktopPaneLayout.createSequentialGroup()
                    .addContainerGap(654, Short.MAX_VALUE)
                    .addComponent(pnlMenuInGame, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        mainDesktopPane.setLayer(pnlScoreBoard, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(pnlMenu, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(pnlMenuInGame, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(laypPlayerCards, javax.swing.JLayeredPane.DEFAULT_LAYER);
        mainDesktopPane.setLayer(laypDealerCards, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        //are you sure? if yes, open MainFrame; if Not, do nothing
        int question = JOptionPane.showConfirmDialog(null, "Are you sure you want to stand-up and quit the table?", "Stand-Up", JOptionPane.YES_NO_OPTION);
        if (question == JOptionPane.YES_OPTION) { // if clicked YES
            System.out.println("Bye.");
            try {
                view.executeSysExit(true);
            } catch (IOException ex) {
                if (Constants.DEBUG){
                    System.out.println(ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnQuitActionPerformed

    private void btnQuitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuitMouseExited
        btnQuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Quit.png")));
    }//GEN-LAST:event_btnQuitMouseExited

    private void btnQuitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQuitMouseEntered
        btnQuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonHover_Quit.png")));
    }//GEN-LAST:event_btnQuitMouseEntered

    private void btnDealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDealActionPerformed
        pnlMenu.setVisible(false);
        System.out.println(view.getCurrentPlayer());
        currentGame = view.deal(view.getCurrentPlayer());
        pnlMenuInGame.setVisible(true);

        view.drawCard(lblPlayerCard1, currentGame.getPlayer().getCurrentHand().getCards()[0]);
        view.drawCard(lblPlayerCard2, currentGame.getPlayer().getCurrentHand().getCards()[1]);
        
        //view.drawCard(lblDealerCard1, currentGame.getDealer().getCards()[0]);
        lblDealerCard1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cards/HiddenCard.png")));
        view.drawCard(lblDealerCard2, currentGame.getDealer().getCards()[1]);
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
    }//GEN-LAST:event_btnStandActionPerformed

    private void btnStandMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStandMouseExited
        btnStand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Stand.png")));
    }//GEN-LAST:event_btnStandMouseExited

    private void btnStandMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnStandMouseEntered
        btnStand.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonHover_Stand.png")));
    }//GEN-LAST:event_btnStandMouseEntered

    private void btnHitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitActionPerformed
        System.out.println("Hit me baby, one more time!");
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
    private javax.swing.JLayeredPane laypDealerCards;
    private javax.swing.JLayeredPane laypPlayerCards;
    private javax.swing.JLabel lblDealerCard1;
    private javax.swing.JLabel lblDealerCard2;
    private javax.swing.JLabel lblDealerCard3;
    private javax.swing.JLabel lblDealerCard4;
    private javax.swing.JLabel lblDealerCard5;
    private javax.swing.JLabel lblDealerCard6;
    private javax.swing.JLabel lblDealerCard7;
    private javax.swing.JLabel lblPlayerCard1;
    private javax.swing.JLabel lblPlayerCard2;
    private javax.swing.JLabel lblPlayerCard3;
    private javax.swing.JLabel lblPlayerCard4;
    private javax.swing.JLabel lblPlayerCard5;
    private javax.swing.JLabel lblPlayerCard6;
    private javax.swing.JLabel lblPlayerCard7;
    private javax.swing.JLabel lblScoreBoard;
    private javax.swing.JDesktopPane mainDesktopPane;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlMenuInGame;
    private javax.swing.JPanel pnlScoreBoard;
    // End of variables declaration//GEN-END:variables
}
