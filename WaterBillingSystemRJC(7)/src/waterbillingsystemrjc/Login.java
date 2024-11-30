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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        AdminBT = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        UserNameTextField = new javax.swing.JTextField();
        PasswordTextField = new javax.swing.JPasswordField();
        UsernameBT = new javax.swing.JLabel();
        SignUpBT = new javax.swing.JButton();
        LoginBT = new javax.swing.JButton();
        ShowPasswordCheckBox = new javax.swing.JCheckBox();
        PasswordBT = new javax.swing.JLabel();
        ExitBT = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1439, 2000));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Downloads\\1111.png")); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 1600, -1, 110));

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
        jPanel2.add(AdminBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 1650, -1, -1));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 11)); // NOI18N
        jLabel1.setText("Water Billing");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 1670, -1, -1));

        UserNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserNameTextFieldActionPerformed(evt);
            }
        });
        jPanel2.add(UserNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 1720, 210, -1));

        PasswordTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordTextFieldActionPerformed(evt);
            }
        });
        jPanel2.add(PasswordTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 1770, 210, -1));

        UsernameBT.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        UsernameBT.setText("Username :");
        jPanel2.add(UsernameBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 1720, -1, -1));

        SignUpBT.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Downloads\\000.png")); // NOI18N
        SignUpBT.setText("SignUp");
        SignUpBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpBTActionPerformed(evt);
            }
        });
        jPanel2.add(SignUpBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 1840, -1, -1));

        LoginBT.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Downloads\\00.png")); // NOI18N
        LoginBT.setText("Login");
        LoginBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBTActionPerformed(evt);
            }
        });
        jPanel2.add(LoginBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 1840, -1, -1));

        ShowPasswordCheckBox.setFont(new java.awt.Font("OCR A Extended", 1, 11)); // NOI18N
        ShowPasswordCheckBox.setText("Show Password");
        ShowPasswordCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowPasswordCheckBoxActionPerformed(evt);
            }
        });
        jPanel2.add(ShowPasswordCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1800, -1, -1));

        PasswordBT.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        PasswordBT.setText("Password :");
        jPanel2.add(PasswordBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 1770, -1, -1));

        ExitBT.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Downloads\\0000.png")); // NOI18N
        ExitBT.setText("Exit");
        ExitBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitBTActionPerformed(evt);
            }
        });
        jPanel2.add(ExitBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Lucida Calligraphy", 1, 90)); // NOI18N
        jLabel3.setText("RJC");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(189, 276, -1, 92));

        jLabel4.setFont(new java.awt.Font("OCR A Extended", 1, 65)); // NOI18N
        jLabel4.setText("WATER BILLING SYSTEM");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(404, 298, -1, -1));

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Lucida Calligraphy", 1, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("RJC ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 112, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("\"Reliable Justified Charges\"");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 184, -1, -1));

        jLabel9.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("emphasizing accurate, fair, and trustworthy ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 195, -1, -1));

        jLabel10.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("billing for water usage");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(295, 216, -1, -1));

        jLabel11.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("With its robust features, including secure data handling, comprehensive reporting tools, and user-");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        jLabel12.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("friendly dashboards, RJC not only simplifies billing operations but also upholds the highest standards");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, -1));

        jLabel13.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("of transparency. By emphasizing reliability and fairness, the RJC Water Billing System builds enduring");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        jLabel14.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("relationships between service providers and their clients, ensuring that every charge is not just accurate");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        jLabel15.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("but also justifiable.");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, -1));

        jLabel16.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("The RJC Water Billing System is more than just software; it is a transformative solution aimed at");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, -1));

        jLabel17.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("redefining how utility providers and their clients interact. It creates a bridge of trust, ensuring that");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, -1, -1));

        jLabel18.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("every aspect of the billing process, from data collection to payment tracking, is handled with utmost");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, -1, -1));

        jLabel19.setFont(new java.awt.Font("Nirmala UI Semilight", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("clarity and fairness.");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, -1, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 770, 670, 770));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/login part (1).jpg"))); // NOI18N
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1530, 670, 550));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/WBSBG (1).jpg"))); // NOI18N
        jLabel7.setText("jLabel7");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 760, 710, 790));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/waterdrop (1).jpg"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1390, 780));

        jPanel3.setBackground(new java.awt.Color(0, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Monospaced", 0, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("\"Manage water wisely with RJC ");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, -1, -1));

        jLabel22.setFont(new java.awt.Font("Monospaced", 0, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("- Billing made better \"");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, -1, -1));

        jLabel23.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 204, 204));
        jLabel23.setText("~Acbang, Bascoguin, Diño ");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, -1, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 1530, 710, 740));

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1366, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
                .addContainerGap())
        );

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
    private javax.swing.JButton LoginBT;
    private javax.swing.JLabel PasswordBT;
    private javax.swing.JPasswordField PasswordTextField;
    private javax.swing.JCheckBox ShowPasswordCheckBox;
    private javax.swing.JButton SignUpBT;
    private javax.swing.JTextField UserNameTextField;
    private javax.swing.JLabel UsernameBT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
