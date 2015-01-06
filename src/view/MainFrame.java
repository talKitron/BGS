package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import model.Game;
import utilities.Constants;

/**
 * MainFrame frame for the game.
 *
 * @author BGS Team
 */
public class MainFrame extends javax.swing.JFrame {

    //***************************************** Variables *********************************************
    /**
     * ViewLogic field
     */
    private static View view;
    boolean firstTimeNameType = true;
    boolean firstTimePassType = true;

    /**
     * Creates new form MainFrame
     *
     * @param view
     */
    public MainFrame(View view) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            if (Constants.DEBUG) {
                System.out.println(e.getMessage());
            }
        }
        getContentPane().setBackground(new Color(182, 184, 192));
        this.view = view;
        initComponents();
        view.setFrameIcon(this);
        btnSubmit.setContentAreaFilled(false);
        btnSitDown.setVisible(false);
        pnlFacts.setVisible(false);
        pnlHighscore.setVisible(false);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        addWindowListener(new MyWindowListener());
        pnlLogin.setOpaque(false);
        btnInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSitDown.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ActionListener actionListener = new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (e.getSource() == btnSubmit) {
                    btnSubmitActionPerformed(e);
                } else if (e.getSource() == btnSitDown) {
                    btnSitDownActionPerformed(e);
                }
            }
        };
        KeyStroke keystroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
        btnSubmit.registerKeyboardAction(actionListener, keystroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        btnSitDown.registerKeyboardAction(actionListener, keystroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        keystroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false);
        btnSitDown.registerKeyboardAction(actionListener, keystroke, JComponent.WHEN_IN_FOCUSED_WINDOW);

    }

    public class MyWindowListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            int question = JOptionPane.showConfirmDialog(null, "Save changes before exit?", "Save", JOptionPane.YES_NO_CANCEL_OPTION);
            if (question == JOptionPane.NO_OPTION) { // if user clicked NO, exit
                System.exit(0);
            } else if (question == JOptionPane.YES_OPTION) {
                view.executeSysExit(false);
            }
        }
    }

    protected void sitDownLayout() {
        ImageIcon newBackground = new ImageIcon(getClass().getResource("/resources/bgStandUp.png")); //change
        pnlBackground.setIcon(newBackground);                                                        //background
        pnlLogin.setVisible(false);
        btnSitDown.setVisible(true);
        lblPlayerImage.setIcon(new ImageIcon(getClass().getResource(view.getCurrentPlayer().getImagePath())));
        pnlScoreBoard.setVisible(true);
        lblFactsText.setText("<html>" + view.getFact() + "</html>");
        lblPlayerName.setText("<html>" + txtfName.getText() + "</html>");
        pnlFacts.setVisible(true);
        pnlHighscore.setVisible(true);
        try {
            lblHighscoresText.setText("<html>Most rounds won: " + view.getHighWinsGame().getPlayer().getName() + " (" + view.getHighWinsGame().getWins() + ")"
                    + "<br>Most rounds lost: " + view.getHighLosesGame().getPlayer().getName() + " (" + view.getHighLosesGame().getLoses() + ")"
                    + "<br>Highest score: " + view.getHighScoreGame().getPlayer().getName() + " (" + view.getHighScoreGame().getScore() + ")</html>");
        } catch (NullPointerException e) {
            lblHighscoresText.setText("<html>Currently empty.<br>You need to play more games to have Highscores. :)</html>");
            if (Constants.DEBUG) {
                System.out.println(e.getMessage());
            }
        }
        btnSitDown.requestFocusInWindow();
        view.getCurrentPlayer();
        view.playSound("CasinoAtmosphere");
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHighscore = new javax.swing.JPanel();
        lblHighscoresTitle = new javax.swing.JLabel();
        lblHighscoresText = new javax.swing.JLabel();
        lblBgHighscores = new javax.swing.JLabel();
        pnlFacts = new javax.swing.JPanel();
        lblFactsTitle = new javax.swing.JLabel();
        lblFactsText = new javax.swing.JLabel();
        lblBgFacts = new javax.swing.JLabel();
        pnlLogin = new javax.swing.JPanel();
        lblLogin = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtfName = new javax.swing.JTextField();
        txtfPassword = new javax.swing.JPasswordField();
        btnSubmit = new javax.swing.JButton();
        lblError = new javax.swing.JLabel("<html>** You may login your last used name or just register a new one.</html>");
        btnSitDown = new javax.swing.JButton();
        pnlScoreBoard = new javax.swing.JPanel();
        btnInfo = new javax.swing.JButton();
        lblPlayerWins = new javax.swing.JLabel();
        lblPlayerLoses = new javax.swing.JLabel();
        lblPlayerBank = new javax.swing.JLabel();
        lblPlayerName = new javax.swing.JLabel();
        lblPlayerImage = new javax.swing.JLabel();
        lblScoreBoard = new javax.swing.JLabel();
        pnlBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1280, 720));

        pnlHighscore.setOpaque(false);
        pnlHighscore.setLayout(null);

        lblHighscoresTitle.setBackground(new java.awt.Color(255, 255, 255));
        lblHighscoresTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblHighscoresTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblHighscoresTitle.setText("Highscores:");
        pnlHighscore.add(lblHighscoresTitle);
        lblHighscoresTitle.setBounds(20, 11, 149, 22);

        lblHighscoresText.setBackground(new java.awt.Color(255, 255, 255));
        lblHighscoresText.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblHighscoresText.setForeground(new java.awt.Color(255, 255, 255));
        lblHighscoresText.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        pnlHighscore.add(lblHighscoresText);
        lblHighscoresText.setBounds(20, 50, 350, 46);

        lblBgHighscores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bgFacts.png"))); // NOI18N
        pnlHighscore.add(lblBgHighscores);
        lblBgHighscores.setBounds(0, 0, 396, 108);

        pnlFacts.setOpaque(false);
        pnlFacts.setLayout(null);

        lblFactsTitle.setBackground(new java.awt.Color(255, 255, 255));
        lblFactsTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblFactsTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblFactsTitle.setText("Did you know?");
        pnlFacts.add(lblFactsTitle);
        lblFactsTitle.setBounds(20, 11, 149, 22);

        lblFactsText.setBackground(new java.awt.Color(255, 255, 255));
        lblFactsText.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        lblFactsText.setForeground(new java.awt.Color(255, 255, 255));
        lblFactsText.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        pnlFacts.add(lblFactsText);
        lblFactsText.setBounds(20, 39, 350, 46);

        lblBgFacts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bgFacts.png"))); // NOI18N
        pnlFacts.add(lblBgFacts);
        lblBgFacts.setBounds(0, 0, 396, 108);

        pnlLogin.setOpaque(false);

        lblLogin.setForeground(new java.awt.Color(255, 255, 255));
        lblLogin.setText("Login:");

        lblName.setForeground(new java.awt.Color(255, 255, 255));
        lblName.setText("Player Name:");

        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setText("Password:");

        txtfName.setForeground(new java.awt.Color(102, 102, 102));
        txtfName.setText("Your name");
        txtfName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfNameKeyPressed(evt);
            }
        });

        txtfPassword.setForeground(new java.awt.Color(102, 102, 102));
        txtfPassword.setText("Password");
        txtfPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfPasswordKeyPressed(evt);
            }
        });

        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Submit.png"))); // NOI18N
        btnSubmit.setBorderPainted(false);
        btnSubmit.setContentAreaFilled(false);
        btnSubmit.setMaximumSize(new java.awt.Dimension(154, 39));
        btnSubmit.setMinimumSize(new java.awt.Dimension(154, 39));
        btnSubmit.setPreferredSize(new java.awt.Dimension(154, 39));
        btnSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSubmitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSubmitMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSubmitMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSubmitMouseReleased(evt);
            }
        });
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        lblError.setForeground(new java.awt.Color(153, 255, 255));
        lblError.setPreferredSize(new java.awt.Dimension(185, 60));

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogin)
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addComponent(lblName)
                        .addGap(18, 18, 18)
                        .addComponent(txtfName, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addComponent(lblPassword)
                        .addGap(32, 32, 32)
                        .addComponent(txtfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblLogin)
                .addGap(18, 18, 18)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblName))
                    .addComponent(txtfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblPassword))
                    .addComponent(txtfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnSitDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_SitDown.png"))); // NOI18N
        btnSitDown.setBorderPainted(false);
        btnSitDown.setContentAreaFilled(false);
        btnSitDown.setMaximumSize(new java.awt.Dimension(154, 39));
        btnSitDown.setMinimumSize(new java.awt.Dimension(154, 39));
        btnSitDown.setPreferredSize(new java.awt.Dimension(154, 39));
        btnSitDown.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSitDownMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSitDownMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSitDownMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSitDownMouseReleased(evt);
            }
        });
        btnSitDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSitDownActionPerformed(evt);
            }
        });

        pnlScoreBoard.setOpaque(false);
        pnlScoreBoard.setVisible(false);

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
        lblPlayerName.setText("Player");
        lblPlayerName.setToolTipText("");

        lblScoreBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ScoreBoard.png"))); // NOI18N

        javax.swing.GroupLayout pnlScoreBoardLayout = new javax.swing.GroupLayout(pnlScoreBoard);
        pnlScoreBoard.setLayout(pnlScoreBoardLayout);
        pnlScoreBoardLayout.setHorizontalGroup(
            pnlScoreBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(lblPlayerWins, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(lblPlayerLoses, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(lblPlayerBank, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblPlayerImage, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblScoreBoard)
        );
        pnlScoreBoardLayout.setVerticalGroup(
            pnlScoreBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(195, 195, 195)
                .addComponent(lblPlayerWins, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPlayerLoses, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(pnlScoreBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPlayerBank, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(lblPlayerImage, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlScoreBoardLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblScoreBoard))
        );

        pnlBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/login.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(pnlHighscore, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(560, 560, 560)
                .addComponent(btnSitDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(1060, 1060, 1060)
                .addComponent(pnlScoreBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(pnlFacts, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(pnlBackground)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(pnlHighscore, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(490, 490, 490)
                .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(600, 600, 600)
                .addComponent(btnSitDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(pnlScoreBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(pnlFacts, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(pnlBackground)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        view.playSound("Button");
        if ((txtfName.getText().equals("Your name") && String.valueOf(txtfPassword.getPassword()).equals("Password")) || (txtfName.getText().isEmpty() || Arrays.toString(txtfPassword.getPassword()).isEmpty())) {
            lblError.setText("Please fill in all the fields.");
            return;
        }
        switch (view.loginProcess(txtfName.getText(), String.valueOf(txtfPassword.getPassword()))) {
            case 0: //general error
                lblError.setText("General error.");
                break;
            case 1: //login succeeded
                if (view.getCurrentPlayer().getImagePath().isEmpty()) { //Player doesn't have a Player image yet (Creation)
                    PlayerImageFrame playerImagePicker = new PlayerImageFrame(view, this);
                    playerImagePicker.setModal(true);
                    playerImagePicker.setVisible(true);
                } else { //player already have a Player image
                    sitDownLayout();
                }
                break;
            case 2: //user already exist & password is incorrect for login
                lblError.setText("Incorrect password.");
                break;
            case 3: //entered name is illegal
                lblError.setText("Illegal name.");
                break;
            default:
                break;
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void txtfNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfNameKeyPressed
        if (firstTimeNameType) {
            txtfName.setText("");
            txtfName.setForeground(Color.BLACK);
            firstTimeNameType = false;
        }
    }//GEN-LAST:event_txtfNameKeyPressed

    private void txtfPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfPasswordKeyPressed
        if (firstTimePassType) {
            txtfPassword.setText("");
            txtfPassword.setForeground(Color.BLACK);
            firstTimePassType = false;
        }
    }//GEN-LAST:event_txtfPasswordKeyPressed

    private void btnSitDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSitDownActionPerformed
        view.playSound("Button");
        Game game = view.addGame(view.getCurrentPlayer());
        TableFrame tableFrame = new TableFrame(view, game);
        tableFrame.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnSitDownActionPerformed

    private void btnSitDownMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSitDownMouseEntered
        btnSitDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonHover_SitDown.png")));
    }//GEN-LAST:event_btnSitDownMouseEntered

    private void btnSitDownMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSitDownMouseExited
        btnSitDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_SitDown.png")));
    }//GEN-LAST:event_btnSitDownMouseExited

    private void btnSubmitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubmitMouseEntered
        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonHover_Submit.png")));
    }//GEN-LAST:event_btnSubmitMouseEntered

    private void btnSubmitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubmitMouseExited
        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonHover_Submit.png")));
    }//GEN-LAST:event_btnSubmitMouseExited

    private void btnSubmitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubmitMousePressed
        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonActive_Submit.png")));
    }//GEN-LAST:event_btnSubmitMousePressed

    private void btnSubmitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubmitMouseReleased
        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_Submit.png")));
    }//GEN-LAST:event_btnSubmitMouseReleased

    private void btnSitDownMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSitDownMousePressed
        btnSitDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButtonActive_SitDown.png")));
    }//GEN-LAST:event_btnSitDownMousePressed

    private void btnSitDownMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSitDownMouseReleased
        btnSitDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gameButton_SitDown.png")));
    }//GEN-LAST:event_btnSitDownMouseReleased

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
        view.playSound("Button");
        InfoFrame frmRules = new InfoFrame(view);
        frmRules.setVisible(true);
    }//GEN-LAST:event_btnInfoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnSitDown;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel lblBgFacts;
    private javax.swing.JLabel lblBgHighscores;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblFactsText;
    private javax.swing.JLabel lblFactsTitle;
    private javax.swing.JLabel lblHighscoresText;
    private javax.swing.JLabel lblHighscoresTitle;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPlayerBank;
    private javax.swing.JLabel lblPlayerImage;
    private javax.swing.JLabel lblPlayerLoses;
    private javax.swing.JLabel lblPlayerName;
    private javax.swing.JLabel lblPlayerWins;
    private javax.swing.JLabel lblScoreBoard;
    private javax.swing.JLabel pnlBackground;
    private javax.swing.JPanel pnlFacts;
    private javax.swing.JPanel pnlHighscore;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPanel pnlScoreBoard;
    private javax.swing.JTextField txtfName;
    private javax.swing.JPasswordField txtfPassword;
    // End of variables declaration//GEN-END:variables
}
