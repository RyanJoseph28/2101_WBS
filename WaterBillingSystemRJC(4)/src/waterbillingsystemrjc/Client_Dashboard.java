package waterbillingsystemrjc;

import java.math.BigDecimal;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;



public class Client_Dashboard extends javax.swing.JFrame {
    

     public Client_Dashboard(String username) {
        initComponents();
        initializeDatabaseConnection();
       
    }
     
    Connection con;
    PreparedStatement pst;
    ResultSet rs = null;
    
    private Connection initializeDatabaseConnection() {
    try {
        String url = "jdbc:mysql://localhost:3306/java_user_database";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    } catch (SQLException ex) {
        ex.printStackTrace();
        return null;
    }
}

public Client_Dashboard() {
    con = initializeDatabaseConnection();
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Profile = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        CDReceipts = new javax.swing.JTextArea();
        SeemybillsBT = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Downloads\\011.png")); // NOI18N
        jLabel1.setText("   Client Dashboard");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1190, 660));

        Profile.setBackground(new java.awt.Color(0, 204, 204));
        Profile.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        Profile.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 0, 12)); // NOI18N

        jPanel8.setBackground(new java.awt.Color(0, 204, 204));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel30.setText("WATER BILLING");
        jPanel8.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, -1, -1));

        jLabel31.setFont(new java.awt.Font("Pristina", 0, 36)); // NOI18N
        jLabel31.setText("RJC");
        jPanel8.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 160, -1, -1));

        Profile.addTab("", jPanel8);

        jPanel7.setBackground(new java.awt.Color(153, 255, 255));
        jPanel7.setMinimumSize(new java.awt.Dimension(1190, 660));
        jPanel7.setPreferredSize(new java.awt.Dimension(1190, 660));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("PAYMENT RECEIPT");
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 90, -1, -1));

        CDReceipts.setColumns(20);
        CDReceipts.setRows(5);
        jScrollPane2.setViewportView(CDReceipts);

        jPanel7.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 270, 500));

        SeemybillsBT.setText("SEE RECEIPT");
        SeemybillsBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeemybillsBTActionPerformed(evt);
            }
        });
        jPanel7.add(SeemybillsBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, -1, 250));

        jLabel3.setText("BILL RECEIPT");
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        jButton2.setText("SEE RECEIPT");
        jPanel7.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 130, -1, -1));

        Profile.addTab("RECEIPT", jPanel7);

        jButton1.setText("Home");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Log out");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("About us");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton9.setText("Receipt");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton9))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(Profile, javax.swing.GroupLayout.PREFERRED_SIZE, 1132, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jButton1)
                .addGap(77, 77, 77)
                .addComponent(jButton9)
                .addGap(118, 118, 118)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton3))
            .addComponent(Profile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        AboutUs AboutUsFrame = new AboutUs();
        AboutUsFrame.setVisible(true);
        AboutUsFrame.pack();
        AboutUsFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Profile.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        Profile.setSelectedIndex(1);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int response = JOptionPane.showConfirmDialog(
        null,
        "Do you want to log out?",
        "Log out Confirmation",
        JOptionPane.YES_NO_OPTION
        );
        if (response == JOptionPane.YES_OPTION) {
            Login LoginFrame = new Login();
            LoginFrame.setVisible(true);
            LoginFrame.pack();
            LoginFrame.setLocationRelativeTo(null);
            this.dispose();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void SeemybillsBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeemybillsBTActionPerformed
       
    
    // Step 1: Establish Database Connection
    if (con == null) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_user_database", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection failed.");
            return;
        }
    }

    // Step 2: Query the database
    String query = "SELECT Account_No, Account_Name, Service_Address, Property, Meter_No, Previous_Reading, Current_Reading, " +
                   "Consumption, Rate, Tax_Amount, Base_Charge, Penalty_Charge, Billing_Period_Start, Billing_Period_End, " +
                   "Due_Date, Total_Amount_Due FROM generatedreceipt";

    try {
        pst = con.prepareStatement(query);
        rs = pst.executeQuery();

        // Step 3: Clear previous data
        CDReceipts.setText("");

        // Step 4: Display data
        boolean hasResults = false;
        while (rs.next()) {
            hasResults = true;
            CDReceipts.append(
                "Account No: " + rs.getString("Account_No") + "\n" +
                "Account Name: " + rs.getString("Account_Name") + "\n" +
                "Service Address: " + rs.getString("Service_Address") + "\n" +
                "Property: " + rs.getString("Property") + "\n" +
                "Meter No: " + rs.getString("Meter_No") + "\n" +
                "Previous Reading: " + rs.getBigDecimal("Previous_Reading") + "\n" +
                "Current Reading: " + rs.getBigDecimal("Current_Reading") + "\n" +
                "Consumption: " + rs.getBigDecimal("Consumption") + "\n" +
                "Rate: " + rs.getBigDecimal("Rate") + "\n" +
                "Tax: " + rs.getBigDecimal("Tax_Amount") + "\n" +
                "Base Charge: " + rs.getBigDecimal("Base_Charge") + "\n" +
                "Penalty Charge: " + rs.getBigDecimal("Penalty_Charge") + "\n" +
                "Billing Period Start: " + rs.getString("Billing_Period_Start") + "\n" +
                "Billing Period End: " + rs.getString("Billing_Period_End") + "\n" +
                "Due Date: " + rs.getDate("Due_Date") + "\n" +
                "Total Amount Due: " + rs.getBigDecimal("Total_Amount_Due") + "\n\n"
            );
        }

        // Step 5: Handle no results
        if (!hasResults) {
            CDReceipts.setText("No bills found.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error retrieving bill data.");
    }


        
    }//GEN-LAST:event_SeemybillsBTActionPerformed

   public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new Login().setVisible(true); // Launch login screen first
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea CDReceipts;
    private javax.swing.JTabbedPane Profile;
    private javax.swing.JButton SeemybillsBT;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    private void syncMRDataToOtherTabs() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private Admin_Dashboard getClient_DashboardInstance() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
