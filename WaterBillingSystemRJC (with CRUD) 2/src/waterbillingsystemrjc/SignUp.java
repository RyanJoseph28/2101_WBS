
package waterbillingsystemrjc;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SignUp extends javax.swing.JFrame {

    public SignUp() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        LoginBT = new javax.swing.JButton();
        SignUpBT = new javax.swing.JButton();
        Full_NameTextField = new javax.swing.JTextField();
        AddressTextField = new javax.swing.JTextField();
        Email_AddressTextField = new javax.swing.JTextField();
        Contact_NumberTextField = new javax.swing.JTextField();
        UsernameField = new javax.swing.JTextField();
        CreatePasswordField = new javax.swing.JPasswordField();
        ConfirmPasswordField = new javax.swing.JPasswordField();
        ConfirmPasswordBT = new javax.swing.JLabel();
        CreatePasswordBT = new javax.swing.JLabel();
        UserNameBT = new javax.swing.JLabel();
        ContactNoBT = new javax.swing.JLabel();
        EmailAddressBT = new javax.swing.JLabel();
        AddressBT = new javax.swing.JLabel();
        FullNameBT = new javax.swing.JLabel();
        CreateAccountLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        WBSLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 204, 255, 40));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LoginBT.setText("Login");
        LoginBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBTActionPerformed(evt);
            }
        });
        jPanel1.add(LoginBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 380, -1, -1));

        SignUpBT.setText("Create Account");
        SignUpBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpBTActionPerformed(evt);
            }
        });
        jPanel1.add(SignUpBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 380, -1, -1));

        Full_NameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Full_NameTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(Full_NameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 200, -1));

        AddressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddressTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(AddressTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, 200, -1));

        Email_AddressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Email_AddressTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(Email_AddressTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 200, -1));

        Contact_NumberTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Contact_NumberTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(Contact_NumberTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, 200, -1));

        UsernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameFieldActionPerformed(evt);
            }
        });
        jPanel1.add(UsernameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 200, -1));

        CreatePasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreatePasswordFieldActionPerformed(evt);
            }
        });
        jPanel1.add(CreatePasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, 200, -1));

        ConfirmPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmPasswordFieldActionPerformed(evt);
            }
        });
        jPanel1.add(ConfirmPasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 340, 200, -1));

        ConfirmPasswordBT.setText("Confirm Password :");
        jPanel1.add(ConfirmPasswordBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, -1, -1));

        CreatePasswordBT.setText("Create Password :");
        jPanel1.add(CreatePasswordBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, -1, -1));

        UserNameBT.setText("Username :");
        jPanel1.add(UserNameBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, -1, -1));

        ContactNoBT.setText("Contact No :");
        jPanel1.add(ContactNoBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, -1, -1));

        EmailAddressBT.setText("Email Address :");
        jPanel1.add(EmailAddressBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, -1, -1));

        AddressBT.setText("Address :");
        jPanel1.add(AddressBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, -1, -1));

        FullNameBT.setText("Full Name :");
        jPanel1.add(FullNameBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, -1, -1));

        CreateAccountLabel.setFont(new java.awt.Font("Segoe UI Historic", 1, 24)); // NOI18N
        CreateAccountLabel.setText("Create Account");
        jPanel1.add(CreateAccountLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 450));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 29, -1, -1));

        WBSLabel.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        WBSLabel.setText("RJC");
        getContentPane().add(WBSLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 40, 20));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 11)); // NOI18N
        jLabel2.setText("Water Billing");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Downloads\\111112.jpg")); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddressTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddressTextFieldActionPerformed
    
        Email_AddressTextField.requestFocus();
    }//GEN-LAST:event_AddressTextFieldActionPerformed

    private void Email_AddressTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Email_AddressTextFieldActionPerformed
     
        Contact_NumberTextField.requestFocus();
    }//GEN-LAST:event_Email_AddressTextFieldActionPerformed

    private void LoginBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginBTActionPerformed
  
        Login LoginFrame = new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_LoginBTActionPerformed

    private void Full_NameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Full_NameTextFieldActionPerformed
      
        AddressTextField.requestFocus();
    }//GEN-LAST:event_Full_NameTextFieldActionPerformed

    private void Contact_NumberTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Contact_NumberTextFieldActionPerformed

        UsernameField.requestFocus();
    }//GEN-LAST:event_Contact_NumberTextFieldActionPerformed

    private void UsernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameFieldActionPerformed
   
        CreatePasswordField.requestFocus();
    }//GEN-LAST:event_UsernameFieldActionPerformed

    private void CreatePasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreatePasswordFieldActionPerformed
    
        ConfirmPasswordField.requestFocus();
    }//GEN-LAST:event_CreatePasswordFieldActionPerformed

    private void ConfirmPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmPasswordFieldActionPerformed
     
        SignUpBT.doClick();
    }//GEN-LAST:event_ConfirmPasswordFieldActionPerformed

    private void SignUpBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpBTActionPerformed
     
       System.out.println("Sign up btn clicked!");

    String Full_Name, Address, Email_Address, Username, Create_Password, Confirm_Password, query;
    int Contact_No;
    String SUrl, SUser, SPass;
    SUrl = "jdbc:MySQL://localhost:3306/java_user_database";
    SUser = "root";
    SPass = "";    


    if ("".equals(Full_NameTextField.getText())) {
        JOptionPane.showMessageDialog(new JFrame(), "Full Name is required", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    } else if ("".equals(AddressTextField.getText())) {
        JOptionPane.showMessageDialog(new JFrame(), "Address is required", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    } else if ("".equals(Email_AddressTextField.getText())) {
        JOptionPane.showMessageDialog(new JFrame(), "Email Address is required", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    } else if ("".equals(Contact_NumberTextField.getText())) {
        JOptionPane.showMessageDialog(new JFrame(), "Contact Number is required", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    } else if (!Contact_NumberTextField.getText().matches("\\d+")) {
        JOptionPane.showMessageDialog(new JFrame(), "Contact Number must contain only digits", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    } else if ("".equals(CreatePasswordField.getText())) {
        JOptionPane.showMessageDialog(new JFrame(), "Password is required", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    } else if (CreatePasswordField.getPassword().length < 6) {
        JOptionPane.showMessageDialog(new JFrame(), "Password must be at least 6 characters", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    } else if ("".equals(ConfirmPasswordField.getText())) {
        JOptionPane.showMessageDialog(new JFrame(), "Confirm Password is required", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    } else if (!new String(CreatePasswordField.getPassword()).equals(new String(ConfirmPasswordField.getPassword()))) {
        JOptionPane.showMessageDialog(new JFrame(), "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Full_Name = Full_NameTextField.getText();
    Address = AddressTextField.getText();
    Email_Address = Email_AddressTextField.getText();
    Contact_No = Integer.parseInt(Contact_NumberTextField.getText());
    Username = UsernameField.getText();
    Create_Password = new String(CreatePasswordField.getPassword());
    Confirm_Password = new String(ConfirmPasswordField.getPassword());

    try {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(SUrl, SUser, SPass);

        query = "SELECT * FROM user WHERE Username = ?";
        java.sql.PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, Username);
        java.sql.ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            JOptionPane.showMessageDialog(new JFrame(), "Username is already taken, please choose another one", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        query = "INSERT INTO user(Full_Name, Address, Email_Address, Contact_No, Username, Create_Password, Confirm_Password) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        pst = con.prepareStatement(query);
        pst.setString(1, Full_Name);
        pst.setString(2, Address);
        pst.setString(3, Email_Address);
        pst.setInt(4, Contact_No);
        pst.setString(5, Username);
        pst.setString(6, Create_Password);
        pst.setString(7, Confirm_Password);

        pst.executeUpdate();

        JOptionPane.showMessageDialog(new JFrame(), "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        
        Full_NameTextField.setText("");
        AddressTextField.setText("");
        Email_AddressTextField.setText("");
        Contact_NumberTextField.setText("");
        UsernameField.setText("");
        CreatePasswordField.setText("");
        ConfirmPasswordField.setText("");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(new JFrame(), "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
        
    }//GEN-LAST:event_SignUpBTActionPerformed
  

    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddressBT;
    private javax.swing.JTextField AddressTextField;
    private javax.swing.JLabel ConfirmPasswordBT;
    private javax.swing.JPasswordField ConfirmPasswordField;
    private javax.swing.JLabel ContactNoBT;
    private javax.swing.JTextField Contact_NumberTextField;
    private javax.swing.JLabel CreateAccountLabel;
    private javax.swing.JLabel CreatePasswordBT;
    private javax.swing.JPasswordField CreatePasswordField;
    private javax.swing.JLabel EmailAddressBT;
    private javax.swing.JTextField Email_AddressTextField;
    private javax.swing.JLabel FullNameBT;
    private javax.swing.JTextField Full_NameTextField;
    private javax.swing.JButton LoginBT;
    private javax.swing.JButton SignUpBT;
    private javax.swing.JLabel UserNameBT;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JLabel WBSLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
