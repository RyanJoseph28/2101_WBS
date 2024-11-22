package waterbillingsystemrjc;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ClientsDashboardProfile extends javax.swing.JFrame {
   private Client_Dashboard clientDashboard;

    public ClientsDashboardProfile() {
        initComponents();
        Connect();
    }
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public void Connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:MySQL://localhost:3306/java_user_database", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientsDashboardProfile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClientsDashboardProfile.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    private String generateUniqueCode() {
        String prefix = "WBSRJC"; // The fixed prefix
        String newCode = null;

        try {
            // Query the database for the highest code
            String query = "SELECT code FROM clients_profile WHERE code LIKE 'WBSRJC%' ORDER BY code DESC LIMIT 1";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            if (rs.next()) {
                String lastCode = rs.getString("code");
                // Extract the numeric part of the last code (the suffix)
                String numericPart = lastCode.substring(prefix.length());
                int nextNumber = Integer.parseInt(numericPart) + 1;
                // Format the new code with leading zeros if necessary
                newCode = prefix + String.format("%03d", nextNumber);
            } else {
                // If no previous codes exist, start from "001"
                newCode = prefix + "001";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientsDashboardProfile.class.getName()).log(Level.SEVERE, null, ex);
        }

        return newCode;
    }
    
     

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Full_NameTextField = new javax.swing.JTextField();
        Contact_NumberTextField = new javax.swing.JTextField();
        AddressTextField = new javax.swing.JTextField();
        Email_AddressTextField = new javax.swing.JTextField();
        UsernameTextField = new javax.swing.JTextField();
        Account_Date_CreatedTextField = new javax.swing.JTextField();
        PropertyComboBox = new javax.swing.JComboBox<>();
        Meter_CountComboBox = new javax.swing.JComboBox<>();
        Account_StatusComboBox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        PasswordTextField = new javax.swing.JTextField();
        CreateBT = new javax.swing.JButton();
        UpdateBT = new javax.swing.JButton();
        DeleteBT = new javax.swing.JButton();
        BackBT = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel11.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Downloads\\011.png")); // NOI18N
        jLabel11.setText("   Client Profile");

        jLabel10.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jLabel10.setText("Account Status:");

        jLabel9.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jLabel9.setText("Account Date Created:");

        jLabel8.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jLabel8.setText("Meter Count:");

        jLabel1.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jLabel1.setText("Code:");

        jLabel2.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jLabel2.setText("Full Name:");

        jLabel4.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jLabel4.setText("Contact Number: ");

        jLabel5.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jLabel5.setText("Email Address:");

        jLabel7.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jLabel7.setText("Property:");

        jLabel3.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jLabel3.setText("Address:");

        jLabel6.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jLabel6.setText("Username : ");

        Full_NameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Full_NameTextFieldActionPerformed(evt);
            }
        });

        Contact_NumberTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Contact_NumberTextFieldActionPerformed(evt);
            }
        });

        AddressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddressTextFieldActionPerformed(evt);
            }
        });

        Email_AddressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Email_AddressTextFieldActionPerformed(evt);
            }
        });

        UsernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameTextFieldActionPerformed(evt);
            }
        });

        Account_Date_CreatedTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Account_Date_CreatedTextFieldActionPerformed(evt);
            }
        });

        PropertyComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Residential", "Commercial", "Industrial", "Governement/Institutional" }));
        PropertyComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PropertyComboBoxActionPerformed(evt);
            }
        });

        Meter_CountComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
        Meter_CountComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Meter_CountComboBoxActionPerformed(evt);
            }
        });

        Account_StatusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive", "Closed" }));

        jLabel12.setText("Password :");

        PasswordTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordTextFieldActionPerformed(evt);
            }
        });

        CreateBT.setText("CREATE");
        CreateBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateBTActionPerformed(evt);
            }
        });

        UpdateBT.setText("UPDATE");

        DeleteBT.setText("DELETE");

        BackBT.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Downloads\\000001.png")); // NOI18N
        BackBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10)
                            .addComponent(jLabel4)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8))
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(UsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(PasswordTextField))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(Account_Date_CreatedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Email_AddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(AddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Contact_NumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Full_NameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(PropertyComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(Meter_CountComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Account_StatusComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(CreateBT)
                                .addComponent(DeleteBT, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(UpdateBT))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(468, 468, 468)
                        .addComponent(BackBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(23, 23, 23))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(BackBT)))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Full_NameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Contact_NumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(CreateBT))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Email_AddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(UsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(PasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UpdateBT))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(PropertyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Meter_CountComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteBT))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Account_Date_CreatedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(Account_StatusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 770, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void Full_NameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Full_NameTextFieldActionPerformed

        Contact_NumberTextField.requestFocus();
    }//GEN-LAST:event_Full_NameTextFieldActionPerformed

    private void Contact_NumberTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Contact_NumberTextFieldActionPerformed

        AddressTextField.requestFocus();
    }//GEN-LAST:event_Contact_NumberTextFieldActionPerformed

    private void AddressTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddressTextFieldActionPerformed
      
        Email_AddressTextField.requestFocus();
    }//GEN-LAST:event_AddressTextFieldActionPerformed

    private void Email_AddressTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Email_AddressTextFieldActionPerformed
      
        UsernameTextField.requestFocus();
    }//GEN-LAST:event_Email_AddressTextFieldActionPerformed

    private void PasswordTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordTextFieldActionPerformed
     
        PropertyComboBox.requestFocus();
    }//GEN-LAST:event_PasswordTextFieldActionPerformed

    private void Meter_CountComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Meter_CountComboBoxActionPerformed
    
        Account_Date_CreatedTextField.requestFocus();
    }//GEN-LAST:event_Meter_CountComboBoxActionPerformed

    private void UsernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameTextFieldActionPerformed
     
        PasswordTextField.requestFocus();
    }//GEN-LAST:event_UsernameTextFieldActionPerformed

    private void PropertyComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PropertyComboBoxActionPerformed
    
        Meter_CountComboBox.requestFocus();
    }//GEN-LAST:event_PropertyComboBoxActionPerformed

    private void Account_Date_CreatedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Account_Date_CreatedTextFieldActionPerformed
    
        Account_StatusComboBox.requestFocus();
    }//GEN-LAST:event_Account_Date_CreatedTextFieldActionPerformed

    
    
    private void CreateBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateBTActionPerformed
    
        try{
        String Full_Name = Full_NameTextField.getText();
        int Contact_Number = Integer.parseInt(Contact_NumberTextField.getText());
        String Address = AddressTextField.getText();
        String Email_Address = Email_AddressTextField.getText();
        String Username = UsernameTextField.getText();
        String Password = PasswordTextField.getText();
        String Meter_Count = (String) Meter_CountComboBox.getSelectedItem();
        String selectedItemProperty = (String) PropertyComboBox.getSelectedItem();
        String Account_Date_Created = Account_Date_CreatedTextField.getText();
        String selectedItemAccount_Status = (String) Account_StatusComboBox.getSelectedItem();
        
       
        String code = generateUniqueCode();

    
        pst = con.prepareStatement("INSERT INTO clients_profile (id,code, Full_Name, Contact_Number, Address, Email_Address, Username, Password, Meter_Count, Property, Account_Date_Created, Account_Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        
        
        
        pst.setString(1, code);  
        pst.setString(2, Full_Name);
        pst.setInt(3, Contact_Number);
        pst.setString(4, Address);
        pst.setString(5, Email_Address);
        pst.setString(6, Username);
        pst.setString(7, Password);
        pst.setString(8, Meter_Count);
        pst.setString(9, selectedItemProperty);
        pst.setString(10, Account_Date_Created);
        pst.setString(11, selectedItemAccount_Status);
       
        int r = pst.executeUpdate();
        
        if (r == 1){
            JOptionPane.showMessageDialog(this, "Clients Info Added Successfully!");
     
       } else {
            JOptionPane.showMessageDialog(this, "Record Failed to save!");
       }
        
       } catch (SQLException ex) {
        Logger.getLogger(ClientsDashboardProfile.class.getName()).log(Level.SEVERE, null, ex);
    }
            
    }//GEN-LAST:event_CreateBTActionPerformed

    private void BackBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackBTActionPerformed

    this.dispose();

    new Client_Dashboard().setVisible(true);
    }//GEN-LAST:event_BackBTActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientsDashboardProfile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Account_Date_CreatedTextField;
    private javax.swing.JComboBox<String> Account_StatusComboBox;
    private javax.swing.JTextField AddressTextField;
    private javax.swing.JButton BackBT;
    private javax.swing.JTextField Contact_NumberTextField;
    private javax.swing.JButton CreateBT;
    private javax.swing.JButton DeleteBT;
    private javax.swing.JTextField Email_AddressTextField;
    private javax.swing.JTextField Full_NameTextField;
    private javax.swing.JComboBox<String> Meter_CountComboBox;
    private javax.swing.JTextField PasswordTextField;
    private javax.swing.JComboBox<String> PropertyComboBox;
    private javax.swing.JButton UpdateBT;
    private javax.swing.JTextField UsernameTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
