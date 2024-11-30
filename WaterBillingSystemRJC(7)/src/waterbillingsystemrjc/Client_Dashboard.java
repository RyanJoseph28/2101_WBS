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
    initComponents();
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Profile = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        SeemybillsBT = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        SeePaymentReceipt = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        CDReceipts = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CPReceipt = new javax.swing.JTextArea();
        jLabel28 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Downloads\\011.png")); // NOI18N
        jLabel1.setText("   Client Dashboard");

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Profile.setBackground(new java.awt.Color(0, 0, 0));
        Profile.setForeground(new java.awt.Color(0, 0, 51));
        Profile.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        Profile.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 0, 12)); // NOI18N
        Profile.setPreferredSize(new java.awt.Dimension(1290, 768));

        jPanel8.setBackground(new java.awt.Color(0, 51, 51));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Cooper Black", 0, 40)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("WATER BILLING");
        jPanel8.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        jLabel31.setFont(new java.awt.Font("Showcard Gothic", 0, 70)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("RJC");
        jPanel8.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel26.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/water014 (1).jpg"))); // NOI18N
        jPanel8.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -50, 1240, -1));

        Profile.addTab("", jPanel8);

        jPanel7.setBackground(new java.awt.Color(0, 51, 51));
        jPanel7.setMinimumSize(new java.awt.Dimension(1190, 660));
        jPanel7.setPreferredSize(new java.awt.Dimension(1190, 660));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jLabel2.setText("PAYMENT RECEIPT");
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 90, 120, 20));

        SeemybillsBT.setBackground(new java.awt.Color(11, 43, 55));
        SeemybillsBT.setFont(new java.awt.Font("Perpetua Titling MT", 0, 14)); // NOI18N
        SeemybillsBT.setForeground(new java.awt.Color(255, 255, 255));
        SeemybillsBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/bill (3) (1).png"))); // NOI18N
        SeemybillsBT.setText("SEE RECEIPT");
        SeemybillsBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeemybillsBTActionPerformed(evt);
            }
        });
        jPanel7.add(SeemybillsBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel3.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jLabel3.setText("BILL RECEIPT");
        jPanel7.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 90, -1));

        SeePaymentReceipt.setBackground(new java.awt.Color(11, 43, 55));
        SeePaymentReceipt.setFont(new java.awt.Font("Perpetua Titling MT", 0, 14)); // NOI18N
        SeePaymentReceipt.setForeground(new java.awt.Color(255, 255, 255));
        SeePaymentReceipt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/bill (3) (1).png"))); // NOI18N
        SeePaymentReceipt.setText("SEE RECEIPT");
        SeePaymentReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeePaymentReceiptActionPerformed(evt);
            }
        });
        jPanel7.add(SeePaymentReceipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, -1, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        CDReceipts.setColumns(20);
        CDReceipts.setRows(5);
        jScrollPane2.setViewportView(CDReceipts);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 320, 480));

        jPanel3.setBackground(new java.awt.Color(0, 0, 51));

        CPReceipt.setColumns(20);
        CPReceipt.setRows(5);
        jScrollPane1.setViewportView(CPReceipt);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 110, 320, 300));

        jLabel28.setFont(new java.awt.Font("Palatino Linotype", 0, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/water014 (1).jpg"))); // NOI18N
        jPanel7.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -20, 1250, 820));

        Profile.addTab("RECEIPT", jPanel7);

        getContentPane().add(Profile, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, -39, 1250, 810));

        jPanel1.setBackground(new java.awt.Color(11, 43, 55));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton9.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/receipt (1).png"))); // NOI18N
        jButton9.setText("Receipt");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jButton4.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        jButton4.setText("About us");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 100, -1));

        jButton3.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/logout (1).png"))); // NOI18N
        jButton3.setText("Log out");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 100, -1));

        jButton1.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/home (2).png"))); // NOI18N
        jButton1.setText("Home");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 790));

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
    String query = "SELECT Account_No, Account_Name, Service_Address, Property, Meter_No, " +
               "Previous_Reading, Current_Reading, Consumption, Rate, Tax_Amount, " +
               "Base_Charge, Penalty_Charge, Billing_Period_Start, Billing_Period_End, " +
               "Due_Date, Total_Amount_Due " +
               "FROM generatedreceipt " +
               "WHERE receiptID = (SELECT MAX(receiptID) FROM generatedreceipt)";

    try {
    pst = con.prepareStatement(query);
    rs = pst.executeQuery();

    // Step 3: Clear previous data
    // Step 3: Clear previous data
CDReceipts.setText(""); // Clear the content only once before the loop

// Step 4: Display data
boolean hasResults = false;
while (rs.next()) {
    hasResults = true;

    // Append each record to CDReceipts with styled format
    CDReceipts.append(
        "==================== RECEIPT ====================\n" +
        "                  RJC WATER BILLING              \n" +
        "          Bucana, Nasugbu, Batangas, Philippines \n" +
        "-------------------------------------------------\n" +
        "Account No:       " + rs.getString("Account_No") + "\n" +
        "Account Name:     " + rs.getString("Account_Name") + "\n" +
        "Service Address:  " + rs.getString("Service_Address") + "\n" +
        "Property:         " + rs.getString("Property") + "\n" +
        "Meter No:         " + rs.getString("Meter_No") + "\n" +
        "-------------------------------------------------\n" +
        "Previous Reading: " + rs.getBigDecimal("Previous_Reading") + "\n" +
        "Current Reading:  " + rs.getBigDecimal("Current_Reading") + "\n" +
        "Consumption:      " + rs.getBigDecimal("Consumption") + " m³\n" +
        "Rate:             " + rs.getBigDecimal("Rate") + " per m³\n" +
        "Tax Amount:       " + rs.getBigDecimal("Tax_Amount") + "\n" +
        "Base Charge:      " + rs.getBigDecimal("Base_Charge") + "\n" +
        "Penalty Charge:   " + rs.getBigDecimal("Penalty_Charge") + "\n" +
        "-------------------------------------------------\n" +
        "Billing Period:   " + rs.getString("Billing_Period_Start") + " to " +
                            rs.getString("Billing_Period_End") + "\n" +
        "Due Date:         " + rs.getDate("Due_Date") + "\n" +
        "-------------------------------------------------\n" +
        "TOTAL AMOUNT DUE: " + rs.getBigDecimal("Total_Amount_Due") + "\n" +
        "=================================================\n" +
        "                RJC WATER BILLING                \n" +
        "Manage water wisely with RJC-- Billing made Better\n\n"
    );
}

// Step 5: Handle no results
if (!hasResults) {
    CDReceipts.setText(
        "==================== RECEIPT ====================\n" +
        "                  RJC WATER BILLING              \n" +
        "          Bucana, Nasugbu, Batangas, Philippines \n" +
        "-------------------------------------------------\n" +
        "              No bills found in the system.       \n" +
        "=================================================\n" +
        "                RJC WATER BILLING                \n" +
        "Manage water wisely with RJC-- Billing made Better\n"
    );
}

} catch (SQLException ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error retrieving bill data.");
}



        
    }//GEN-LAST:event_SeemybillsBTActionPerformed

    private void SeePaymentReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeePaymentReceiptActionPerformed
        if (con == null) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_user_database", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection failed.");
            return;
        }
    }

    // Step 2: Query the database to get the most recent payment record
    String query = "SELECT Account_No, Account_Name, Total_Amount_Due, Payment_Method, Payment_Date " +
                   "FROM clientpayment " +
                   "WHERE id = (SELECT MAX(id) FROM clientpayment)";

    try {
        pst = con.prepareStatement(query);
        rs = pst.executeQuery();

        // Step 3: Clear previous data
        CPReceipt.setText(""); // Clear the text area before displaying new data

// Step 4: Display the most recent record
if (rs.next()) {
    CPReceipt.append(
        "==================== PAYMENT RECEIPT ====================\n" +
        "                  RJC WATER BILLING                      \n" +
        "          Bucana, Nasugbu, Batangas, Philippines         \n" +
        "---------------------------------------------------------\n" +
        " Account No:        " + rs.getString("Account_No") + "\n" +
        " Account Name:      " + rs.getString("Account_Name") + "\n" +
        "---------------------------------------------------------\n" +
        " TOTAL AMOUNT DUE:  " + rs.getBigDecimal("Total_Amount_Due") + "\n" +
        " Payment Method:    " + rs.getString("Payment_Method") + "\n" +
        " Payment Date:      " + rs.getDate("Payment_Date") + "\n" +
        "=========================================================\n" +
        "                 RJC WATER BILLING                        \n" +
        " Manage water wisely with RJC-- Billing made Better       \n" +
        "               Thank you for trusting!                  \n"
    );
} else {
    // Step 5: Handle no results
    CPReceipt.setText(
        "==================== PAYMENT RECEIPT ====================\n" +
        "                  RJC WATER BILLING                      \n" +
        "          Bucana, Nasugbu, Batangas, Philippines         \n" +
        "---------------------------------------------------------\n" +
        "                  No payment records found.              \n" +
        "=========================================================\n" +
        "                    RJC WATER BILLING                        \n" +
        "Manage water wisely with RJC-- Billing made Better       \n" +
        "               Thank you for trusting!                  \n"
    );
}

    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error retrieving payment data.");
    }


    }//GEN-LAST:event_SeePaymentReceiptActionPerformed

   public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new Login().setVisible(true); // Launch login screen first
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea CDReceipts;
    private javax.swing.JTextArea CPReceipt;
    private javax.swing.JTabbedPane Profile;
    private javax.swing.JButton SeePaymentReceipt;
    private javax.swing.JButton SeemybillsBT;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    private void syncMRDataToOtherTabs() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private Admin_Dashboard getClient_DashboardInstance() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
