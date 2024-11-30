package waterbillingsystemrjc;

import javax.swing.JOptionPane;

public class AdminLogin extends javax.swing.JFrame {

    public AdminLogin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        UsernameTF = new javax.swing.JTextField();
        PasswordTF = new javax.swing.JPasswordField();
        RetypeTF = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jLabel1.setText("Username :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 58, -1, -1));

        jLabel2.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jLabel2.setText("Password :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 98, -1, -1));

        UsernameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameTFActionPerformed(evt);
            }
        });
        getContentPane().add(UsernameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 55, 132, -1));

        PasswordTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordTFActionPerformed(evt);
            }
        });
        getContentPane().add(PasswordTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 95, 132, -1));

        RetypeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RetypeTFActionPerformed(evt);
            }
        });
        getContentPane().add(RetypeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 135, 132, -1));

        jLabel3.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jLabel3.setText("Re-type Password :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 138, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/LOGINADMIN (1).jpg"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
private static final String FIXED_USERNAME = "RJCWB2024";
private static final String FIXED_PASSWORD = "RJCWB"; 

private int failedAttempts = 0; 
    private void UsernameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameTFActionPerformed
    
    }//GEN-LAST:event_UsernameTFActionPerformed

    private void PasswordTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordTFActionPerformed

        validateLogin();
    }//GEN-LAST:event_PasswordTFActionPerformed

    private void RetypeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RetypeTFActionPerformed

        validateLogin();
    }//GEN-LAST:event_RetypeTFActionPerformed
     
private void validateLogin() {
    String enteredUsername = UsernameTF.getText();
    String enteredPassword = new String(PasswordTF.getPassword());
    String retypedPassword = new String(RetypeTF.getPassword());
    
    if (!enteredUsername.equals(FIXED_USERNAME)) {
        failedAttempts++;
        JOptionPane.showMessageDialog(null, "Incorrect username!");
    } else if (!enteredPassword.equals(FIXED_PASSWORD)) {
        failedAttempts++;
        JOptionPane.showMessageDialog(null, "Incorrect password!");
    } else if (!enteredPassword.equals(retypedPassword)) {
        failedAttempts++;
        JOptionPane.showMessageDialog(null, "Passwords do not match!");
    } else {
        JOptionPane.showMessageDialog(null, "Login successful!");

        openAdminDashboard();
        return;
    } if (failedAttempts >= 2) {
        failedAttempts = 0; 
        JOptionPane.showMessageDialog(null, "Too many failed attempts. Returning to login screen.");
        openLoginFrame(); 
    }
}
    private void openAdminDashboard() {
        Admin_Dashboard Admin_Dashboard = new Admin_Dashboard();
        Admin_Dashboard.setVisible(true);
        this.dispose();
}
    private void openLoginFrame() {
    Login loginFrame = new Login(); 
    loginFrame.setVisible(true);
    this.dispose(); 
}

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField PasswordTF;
    private javax.swing.JPasswordField RetypeTF;
    private javax.swing.JTextField UsernameTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
