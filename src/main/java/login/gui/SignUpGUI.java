package login.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 27.03.2023
 * @author 
 */

public class SignUpGUI extends JFrame {
  // Anfang Attribute
  private JLabel lUsername = new JLabel();
  private JLabel lPassword = new JLabel();
  private JLabel lSignUp = new JLabel();
  private JTextField tfUsername = new JTextField();
  private JTextField tfPassword = new JTextField();
  private JButton bContinue = new JButton();
  private JLabel lConfirmpassword = new JLabel();
  private JTextField tfConfirmpassword = new JTextField();
  // Ende Attribute
  
  public SignUpGUI() {
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 332; 
    int frameHeight = 208;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("UserdataGUI");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    bContinue.setBounds(120, 136, 80, 24);
    bContinue.setText("Continue");
    bContinue.setMargin(new Insets(2, 2, 2, 2));
    bContinue.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bContinue_ActionPerformed(evt);
      }
    });
    bContinue.setBackground(Color.WHITE);
    cp.add(bContinue);
    tfUsername.setBounds(120, 40, 184, 24);
    tfUsername.setToolTipText("Enter a username");
    tfUsername.setText("Enter a username");
    cp.add(tfUsername);
    lSignUp.setBounds(0, 8, 320, 24);
    lSignUp.setText("Sign Up");
    lSignUp.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(lSignUp);
    lUsername.setBounds(8, 40, 80, 24);
    lUsername.setText("Username:");
    lUsername.setBackground(Color.WHITE);
    lUsername.setOpaque(true);
    cp.add(lUsername);
    
    cp.setBackground(Color.WHITE);
    lPassword.setBounds(8, 72, 80, 24);
    lPassword.setText("Password:");
    lPassword.setBackground(Color.WHITE);
    lPassword.setOpaque(true);
    cp.add(lPassword);
    tfPassword.setBounds(120, 72, 184, 24);
    tfPassword.setToolTipText("Enter a password");
    tfPassword.setText("Enter a password");
    cp.add(tfPassword);
    lConfirmpassword.setBounds(8, 104, 112, 24);
    lConfirmpassword.setText("Confirm password:");
    cp.add(lConfirmpassword);
    tfConfirmpassword.setBounds(120, 104, 184, 24);
    tfConfirmpassword.setText("Confirm password");
    tfConfirmpassword.setToolTipText("Confirm password");
    cp.add(tfConfirmpassword);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public UserdataGUI
  
  // Anfang Methoden
  
  public void bContinue_ActionPerformed(ActionEvent evt) {
    if (tfPassword != null && tfUsername != null){
      if (tfPassword.getText().equals(tfConfirmpassword.getText())) {
        UserData.saveUserData(new User(tfUsername.getText(), tfPassword.getText()));
      } else {
        JOptionPane.showMessageDialog(null, "Passwords don't match", "Error", JOptionPane.INFORMATION_MESSAGE);
      }
    }else {
      JOptionPane.showMessageDialog(null, "Please fillout all fields", "Error", JOptionPane.INFORMATION_MESSAGE);
    }
    
  } // end of bContinue_ActionPerformed

  // Ende Methoden
} // end of class UserdataGUI
