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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Username :");

        jLabel2.setText("Password :");

        UsernameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameTFActionPerformed(evt);
            }
        });

        PasswordTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordTFActionPerformed(evt);
            }
        });

        RetypeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RetypeTFActionPerformed(evt);
            }
        });

        jLabel3.setText("Re-type Password :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(UsernameTF)
                    .addComponent(PasswordTF)
                    .addComponent(RetypeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(UsernameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(PasswordTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RetypeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private static final String FIXED_USERNAME = "123456";
private static final String FIXED_PASSWORD = "1234567"; 

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
    // End of variables declaration//GEN-END:variables
}
