package waterbillingsystemrjc;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.Connection;



public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        AdminBT = new javax.swing.JButton();
        UserNameTextField = new javax.swing.JTextField();
        PasswordTextField = new javax.swing.JPasswordField();
        UsernameBT = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        SignUpBT = new javax.swing.JButton();
        ExitBT = new javax.swing.JButton();
        LoginBT = new javax.swing.JButton();
        ShowPasswordCheckBox = new javax.swing.JCheckBox();
        PasswordBT = new javax.swing.JLabel();
        LoginBG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 11)); // NOI18N
        jLabel1.setText("Water Billing");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, -1, -1));

        AdminBT.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        AdminBT.setText("RJC");
        AdminBT.setBorder(null);
        AdminBT.setContentAreaFilled(false);
        AdminBT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        AdminBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminBTActionPerformed(evt);
            }
        });
        getContentPane().add(AdminBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, -1, -1));

        UserNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserNameTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(UserNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 170, -1));

        PasswordTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(PasswordTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, 170, -1));

        UsernameBT.setText("Username :");
        getContentPane().add(UsernameBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Downloads\\1111.png")); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, -1, 110));

        jPanel1.setBackground(new java.awt.Color(153, 204, 255, 50));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SignUpBT.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Downloads\\000.png")); // NOI18N
        SignUpBT.setText("SignUp");
        SignUpBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpBTActionPerformed(evt);
            }
        });
        jPanel1.add(SignUpBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 222, -1, -1));

        ExitBT.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Downloads\\0000.png")); // NOI18N
        ExitBT.setText("Exit");
        ExitBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitBTActionPerformed(evt);
            }
        });
        jPanel1.add(ExitBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 222, -1, -1));

        LoginBT.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Downloads\\00.png")); // NOI18N
        LoginBT.setText("Login");
        LoginBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBTActionPerformed(evt);
            }
        });
        jPanel1.add(LoginBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 222, -1, -1));

        ShowPasswordCheckBox.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        ShowPasswordCheckBox.setText("Show Password");
        ShowPasswordCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowPasswordCheckBoxActionPerformed(evt);
            }
        });
        jPanel1.add(ShowPasswordCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 173, -1, -1));

        PasswordBT.setText("Password :");
        jPanel1.add(PasswordBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 131, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 360, 270));

        LoginBG.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Downloads\\water-picsart-editing-blue-background-hd-images-free-download-gngnl9zzmm.png")); // NOI18N
        getContentPane().add(LoginBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UserNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserNameTextFieldActionPerformed

        PasswordTextField.requestFocus();
    }//GEN-LAST:event_UserNameTextFieldActionPerformed

    private void PasswordTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordTextFieldActionPerformed
       
    }//GEN-LAST:event_PasswordTextFieldActionPerformed

    private void SignUpBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpBTActionPerformed
           
        SignUp SignUpFrame = new SignUp();
        SignUpFrame.setVisible(true);
        SignUpFrame.pack();
        SignUpFrame.setLocationRelativeTo(null);
        this.dispose();
            
    }//GEN-LAST:event_SignUpBTActionPerformed

    private void ShowPasswordCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowPasswordCheckBoxActionPerformed
      
        if (ShowPasswordCheckBox.isSelected()) {
            PasswordTextField.setEchoChar((char) 0);
        } else {
            PasswordTextField.setEchoChar('*');
       }
    }//GEN-LAST:event_ShowPasswordCheckBoxActionPerformed

    private void LoginBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginBTActionPerformed
       
        String username = UserNameTextField.getText();
String password = new String(PasswordTextField.getPassword());

String SUrl = "jdbc:MySQL://localhost:3306/java_user_database";
String SUser = "root"; 
String SPass = "";   

try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection(SUrl, SUser, SPass);

    String query = "SELECT * FROM user WHERE Username = ? AND Create_Password = ?";
    java.sql.PreparedStatement pst = con.prepareStatement(query);
    pst.setString(1, username);
    pst.setString(2, password);
    java.sql.ResultSet rs = pst.executeQuery();

    if (rs.next()) {
        JOptionPane.showMessageDialog(new JFrame(), "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        this.dispose(); 
       
        new Client_Dashboard(username).setVisible(true);
    } else {
        JOptionPane.showMessageDialog(new JFrame(), "No account found with this username. Please sign up first.", "Error", JOptionPane.ERROR_MESSAGE);
    }

} catch (Exception e) {
    JOptionPane.showMessageDialog(new JFrame(), "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}


    }//GEN-LAST:event_LoginBTActionPerformed

    private void ExitBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitBTActionPerformed

         int a = JOptionPane.showConfirmDialog(null, "Do you really want to Close Application", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_ExitBTActionPerformed
    private int adminClickCount = 0;
    private void AdminBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminBTActionPerformed

        adminClickCount++; 

    if (adminClickCount == 5) {
        adminClickCount = 0;
        AdminLogin adminLogin = new AdminLogin(); 
        adminLogin.setVisible(true);
        this.dispose();
    }
        
    }//GEN-LAST:event_AdminBTActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdminBT;
    private javax.swing.JButton ExitBT;
    private javax.swing.JLabel LoginBG;
    private javax.swing.JButton LoginBT;
    private javax.swing.JLabel PasswordBT;
    private javax.swing.JPasswordField PasswordTextField;
    private javax.swing.JCheckBox ShowPasswordCheckBox;
    private javax.swing.JButton SignUpBT;
    private javax.swing.JTextField UserNameTextField;
    private javax.swing.JLabel UsernameBT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
