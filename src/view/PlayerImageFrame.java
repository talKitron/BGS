package view;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import utilities.Constants;

/**
 * The class uses for the Player to choose an image for his profile.
 *
 * @author BGS Team
 */
public class PlayerImageFrame extends javax.swing.JDialog {

    ArrayList<String> playerImagePaths = new ArrayList<>();
    ArrayList<String> playerCirlceImagePaths = new ArrayList<>();
    Integer counter = 0;
    String[] nickNames = {"Sparky Geeksberg", "Miss Grouchy", "Tzvika Hadar",
        "Housewife Betty", "The Tinder Guy", "Hazel Eyes Veronica",
        "I'm Sexy", "Wonderland Alice", "Hipster Mike",
        "Adi Ashkenazi", "Freaky Bridget", "Mickey the Rat"};
    View view;
    MainFrame mainFrame;

    /**
     * Creates new dialog PlayerImageFrame
     *
     * @param view
     * @param mainFrame
     */
    public PlayerImageFrame(View view, MainFrame mainFrame) {
        initComponents();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        this.view = view;
        this.mainFrame = mainFrame;
        loadPlayers();
        lblCharacterImage.setIcon(new ImageIcon(getClass().getResource(playerImagePaths.get(counter))));
        lblCharacterName.setText(nickNames[counter]);
        btnSubmit.requestFocusInWindow();
        btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPrevious.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnPrevious) {
                    btnPreviousActionPerformed(e);
                } else if (e.getSource() == btnNext) {
                    btnNextActionPerformed(e);
                } else if (e.getSource() == btnSubmit) {
                    btnSubmitActionPerformed(e);
                }
            }
        };

        KeyStroke keystroke = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false);
        btnPrevious.registerKeyboardAction(actionListener, keystroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        keystroke = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false);
        btnNext.registerKeyboardAction(actionListener, keystroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        keystroke = KeyStroke.getKeyStroke(KeyEvent.VK_G, 0, false);
        btnSubmit.registerKeyboardAction(actionListener, keystroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        keystroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
        btnSubmit.registerKeyboardAction(actionListener, keystroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    /**
     * The method loads all the Player images into an runtime.
     */
    private void loadPlayers() {
        for (int i = 1; i <= Constants.NUMBER_OF_PLAYER_IMAGES; i++) {
            playerImagePaths.add("/resources/players/player" + i + "_s.png");
            playerCirlceImagePaths.add("/resources/players/player" + i + "_c.png");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCharacterImage = new javax.swing.JLabel();
        lblCharacterName = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(446, 315));
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });
        getContentPane().setLayout(null);
        getContentPane().add(lblCharacterImage);
        lblCharacterImage.setBounds(135, 71, 177, 177);

        lblCharacterName.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblCharacterName.setForeground(new java.awt.Color(0, 102, 102));
        lblCharacterName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblCharacterName);
        lblCharacterName.setBounds(73, 33, 300, 32);

        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/gogo.png"))); // NOI18N
        btnSubmit.setBorderPainted(false);
        btnSubmit.setContentAreaFilled(false);
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        getContentPane().add(btnSubmit);
        btnSubmit.setBounds(150, 240, 145, 49);

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/right_arrow.png"))); // NOI18N
        btnNext.setBorderPainted(false);
        btnNext.setContentAreaFilled(false);
        btnNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNextMouseExited(evt);
            }
        });
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        getContentPane().add(btnNext);
        btnNext.setBounds(326, 130, 79, 53);

        btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buttons/left_arrow.png"))); // NOI18N
        btnPrevious.setBorderPainted(false);
        btnPrevious.setContentAreaFilled(false);
        btnPrevious.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPreviousMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPreviousMouseExited(evt);
            }
        });
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });
        getContentPane().add(btnPrevious);
        btnPrevious.setBounds(41, 130, 79, 55);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        view.playSound("Button");
        view.getCurrentPlayer().setImagePath(playerCirlceImagePaths.get(counter));
        dispose();
        mainFrame.sitDownLayout();
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        view.playSound("Button");
        if (counter == 0) {
            counter = Constants.NUMBER_OF_PLAYER_IMAGES - 1;
            lblCharacterImage.setIcon(new ImageIcon(getClass().getResource(playerImagePaths.get(counter))));
            lblCharacterName.setText(nickNames[counter]);
        } else {
            lblCharacterImage.setIcon(new ImageIcon(getClass().getResource(playerImagePaths.get(--counter))));
            lblCharacterName.setText(nickNames[counter]);
        }
    }//GEN-LAST:event_btnPreviousActionPerformed

    private void btnPreviousMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreviousMouseEntered
        btnPrevious.setIcon(new ImageIcon(getClass().getResource("/resources/buttons/left_arrow_hover.png")));
    }//GEN-LAST:event_btnPreviousMouseEntered

    private void btnPreviousMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreviousMouseExited
        btnPrevious.setIcon(new ImageIcon(getClass().getResource("/resources/buttons/left_arrow.png")));
    }//GEN-LAST:event_btnPreviousMouseExited

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        view.playSound("Button");
        if (counter == Constants.NUMBER_OF_PLAYER_IMAGES - 1) {
            counter = 0;
            lblCharacterImage.setIcon(new ImageIcon(getClass().getResource(playerImagePaths.get(counter))));
            lblCharacterName.setText(nickNames[counter]);
        } else {
            lblCharacterImage.setIcon(new ImageIcon(getClass().getResource(playerImagePaths.get(++counter))));
            lblCharacterName.setText(nickNames[counter]);
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseEntered
        btnNext.setIcon(new ImageIcon(getClass().getResource("/resources/buttons/right_arrow_hover.png")));
    }//GEN-LAST:event_btnNextMouseEntered

    private void btnNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseExited
        btnNext.setIcon(new ImageIcon(getClass().getResource("/resources/buttons/right_arrow.png")));
    }//GEN-LAST:event_btnNextMouseExited

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost

    }//GEN-LAST:event_formFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel lblCharacterImage;
    private javax.swing.JLabel lblCharacterName;
    // End of variables declaration//GEN-END:variables
}
