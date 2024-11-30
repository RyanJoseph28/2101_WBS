
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        WBSLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 204, 255, 40));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LoginBT.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        LoginBT.setText("Login");
        LoginBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBTActionPerformed(evt);
            }
        });
        jPanel1.add(LoginBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, -1, -1));

        SignUpBT.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        SignUpBT.setText("Create Account");
        SignUpBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpBTActionPerformed(evt);
            }
        });
        jPanel1.add(SignUpBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 370, -1, -1));

        Full_NameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Full_NameTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(Full_NameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 200, -1));

        AddressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddressTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(AddressTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 200, -1));

        Email_AddressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Email_AddressTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(Email_AddressTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 200, -1));

        Contact_NumberTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Contact_NumberTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(Contact_NumberTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 200, -1));

        UsernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameFieldActionPerformed(evt);
            }
        });
        jPanel1.add(UsernameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, 200, -1));

        CreatePasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreatePasswordFieldActionPerformed(evt);
            }
        });
        jPanel1.add(CreatePasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, 200, -1));

        ConfirmPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmPasswordFieldActionPerformed(evt);
            }
        });
        jPanel1.add(ConfirmPasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 330, 200, -1));

        ConfirmPasswordBT.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        ConfirmPasswordBT.setText("Confirm Password :");
        jPanel1.add(ConfirmPasswordBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, -1, -1));

        CreatePasswordBT.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        CreatePasswordBT.setText("Create Password :");
        jPanel1.add(CreatePasswordBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, -1, -1));

        UserNameBT.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        UserNameBT.setText("Username :");
        jPanel1.add(UserNameBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, -1, -1));

        ContactNoBT.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        ContactNoBT.setText("Contact No :");
        jPanel1.add(ContactNoBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, -1, -1));

        EmailAddressBT.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        EmailAddressBT.setText("Email Address :");
        jPanel1.add(EmailAddressBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, -1, -1));

        AddressBT.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        AddressBT.setText("Address :");
        jPanel1.add(AddressBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));

        FullNameBT.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        FullNameBT.setText("Full Name :");
        jPanel1.add(FullNameBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, -1));

        CreateAccountLabel.setFont(new java.awt.Font("Showcard Gothic", 1, 24)); // NOI18N
        CreateAccountLabel.setText("Create Account");
        jPanel1.add(CreateAccountLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, -1, -1));

        jLabel5.setFont(new java.awt.Font("Lucida Calligraphy", 1, 30)); // NOI18N
        jLabel5.setText("RJC");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 12, -1, -1));

        jLabel6.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        jLabel6.setText("Water Billing");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/signup (1).jpg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

    String confirmationMessage = String.format(
            "Please confirm your details:\n\nFull Name: %s\nAddress: %s\nEmail: %s\nContact No: %d\nUsername: %s\n\nIs this information correct? (yes/no)",
            Full_Name, Address, Email_Address, Contact_No, Username
        );
    
       
    
        int response = JOptionPane.showConfirmDialog(
            null, 
            confirmationMessage, 
            "Confirm Details", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );
         if (response == JOptionPane.YES_OPTION) {
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

        query = "INSERT INTO user(Full_Name, Address, Email_Address, Contact_Number, Username, Create_Password, Confirm_Password) "
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
      } else { // If the user clicks 'No' (response == NO_OPTION), prompt to check information
            JOptionPane.showMessageDialog(new JFrame(), "Please check your information and try again.", "Check Information", JOptionPane.WARNING_MESSAGE);
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
