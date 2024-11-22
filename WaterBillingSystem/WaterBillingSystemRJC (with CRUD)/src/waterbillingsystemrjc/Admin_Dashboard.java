package waterbillingsystemrjc;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat; 
import java.util.Date; 
import java.util.Vector;


public class Admin_Dashboard extends javax.swing.JFrame {

    public Admin_Dashboard() {
        initComponents();
        Connect();
        fetchClientInfo();
       
    }
    
    Connection con;
    PreparedStatement pst;
     ResultSet rs = null;
    
    public String generateNextAccountNo(Connection con) throws SQLException {
    String lastAccountNo = null;

    String query = "SELECT Account_No FROM clientinfo ORDER BY Account_No DESC LIMIT 1";
    try (PreparedStatement pst = con.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
        if (rs.next()) {
            lastAccountNo = rs.getString("Account_No");
        }
    }


    String newAccountNo;
    if (lastAccountNo == null || lastAccountNo.isEmpty()) {
        newAccountNo = "RJCWB001"; 
    } else {
        
        int lastNumber = Integer.parseInt(lastAccountNo.substring(5)); 
        if (lastNumber >= 100) {
            throw new IllegalStateException("Account numbers have reached the maximum limit (RJCWB100).");
        }
        newAccountNo = "RJCWB" + String.format("%03d", lastNumber + 1); 
    }

    return newAccountNo;
}

    
    
    public void Connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:MySQL://localhost:3306/java_user_database", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Admin_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Admin_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
   private void fetchClientInfo() {
    try {
        if (con == null || con.isClosed()) {
            JOptionPane.showMessageDialog(this, "Database connection is not established.");
            return;
        }

        pst = con.prepareStatement("SELECT * FROM clientinfo");
        rs = pst.executeQuery();

        ResultSetMetaData rss = rs.getMetaData();
        int columnCount = rss.getColumnCount();

        DefaultTableModel df = (DefaultTableModel) CustomerInfoTable.getModel();
        df.setRowCount(0);

        while (rs.next()) {
            Vector<Object> v2 = new Vector<>();

            v2.add(rs.getString("Account_No"));  
            v2.add(rs.getString("Account_Name"));  
            v2.add(rs.getString("Service_Address"));   
            v2.add(rs.getInt("Contact_Number"));    
            v2.add(rs.getString("Property"));     
            v2.add(rs.getString("Meter"));       
            v2.add(rs.getString("Account_Status"));    
            v2.add(rs.getBigDecimal("Previous_Consumption")); 
            v2.add(rs.getBigDecimal("Current_Consumption"));  
            v2.add(rs.getBigDecimal("Current_Charge"));       
            v2.add(rs.getDate("Billing_Period_Start"));        
            v2.add(rs.getDate("Billing_Period_End"));          
            v2.add(rs.getDate("Reading_Date"));     
            v2.add(rs.getDate("Due_Date"));               
            v2.add(rs.getBigDecimal("Total_Amount_Due"));      
            
            df.addRow(v2);
        }

    } catch (SQLException ex) {
        
        Logger.getLogger(Admin_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Error fetching data: " + ex.getMessage());
    } catch (Exception ex) {

        Logger.getLogger(Admin_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Unexpected error: " + ex.getMessage());
    }
}

   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        AccountNoTF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        CreateBT = new javax.swing.JButton();
        AccountNameTF = new javax.swing.JTextField();
        PreviousConsumptionTF = new javax.swing.JTextField();
        CurrentConsumptionTF = new javax.swing.JTextField();
        BillingPeriodStartTF = new javax.swing.JTextField();
        CurrentChargeTF = new javax.swing.JTextField();
        DueDateTF = new javax.swing.JTextField();
        TotalAmountDueTF = new javax.swing.JTextField();
        SearchBT = new javax.swing.JButton();
        UpdateBT = new javax.swing.JButton();
        DeleteBT = new javax.swing.JButton();
        MeterCB = new javax.swing.JComboBox<>();
        PropertyCB = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        ServiceAddressTF = new javax.swing.JTextArea();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        AccountStatusCB = new javax.swing.JComboBox<>();
        ReadingDateTF = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        CustomerInfoTable = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        Contact_NumberTF = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        BillingPeriodEndTF = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        meterreading = new javax.swing.JTextField();
        ratestructure = new javax.swing.JTextField();
        usagealerts = new javax.swing.JTextField();
        datavalidation = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        readingdate = new javax.swing.JTextField();
        accountname = new javax.swing.JTextField();
        CurrentConsumptionTextField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        PreviousConsumptionTextField = new javax.swing.JTextField();
        CurrentChargeTextField = new javax.swing.JTextField();
        TotalAmountTextField = new javax.swing.JTextField();
        DueDateTextField = new javax.swing.JTextField();
        StatusTextField = new javax.swing.JTextField();
        FinishPaymentbtn = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        area1 = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        ApprovedByTF = new javax.swing.JTextField();
        TotalAmountTF = new javax.swing.JTextField();
        ClientTF = new javax.swing.JTextField();
        FinalApprovalTF = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        StatusTF = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        meterreading1 = new javax.swing.JTextField();
        ratestructure1 = new javax.swing.JTextField();
        usagealerts1 = new javax.swing.JTextField();
        datavalidation1 = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        area2 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        readingdate1 = new javax.swing.JTextField();
        accountname1 = new javax.swing.JTextField();
        CurrentConsumptionTextField1 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        PreviousConsumptionTextField1 = new javax.swing.JTextField();
        CurrentChargeTextField1 = new javax.swing.JTextField();
        TotalAmountTextField1 = new javax.swing.JTextField();
        DueDateTextField1 = new javax.swing.JTextField();
        StatusTextField1 = new javax.swing.JTextField();
        FinishPaymentbtn1 = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        area3 = new javax.swing.JTextArea();
        jPanel16 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel56 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        ApprovedByTF1 = new javax.swing.JTextField();
        TotalAmountTF1 = new javax.swing.JTextField();
        ClientTF1 = new javax.swing.JTextField();
        FinalApprovalTF1 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        StatusTF1 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel19 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        meterreading2 = new javax.swing.JTextField();
        ratestructure2 = new javax.swing.JTextField();
        usagealerts2 = new javax.swing.JTextField();
        datavalidation2 = new javax.swing.JTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        area4 = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        readingdate2 = new javax.swing.JTextField();
        accountname2 = new javax.swing.JTextField();
        CurrentConsumptionTextField2 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        PreviousConsumptionTextField2 = new javax.swing.JTextField();
        CurrentChargeTextField2 = new javax.swing.JTextField();
        TotalAmountTextField2 = new javax.swing.JTextField();
        DueDateTextField2 = new javax.swing.JTextField();
        StatusTextField2 = new javax.swing.JTextField();
        FinishPaymentbtn2 = new javax.swing.JButton();
        jLabel75 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jScrollPane15 = new javax.swing.JScrollPane();
        area5 = new javax.swing.JTextArea();
        jPanel22 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel77 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        ApprovedByTF2 = new javax.swing.JTextField();
        TotalAmountTF2 = new javax.swing.JTextField();
        ClientTF2 = new javax.swing.JTextField();
        FinalApprovalTF2 = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        StatusTF2 = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel25 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        meterreading3 = new javax.swing.JTextField();
        ratestructure3 = new javax.swing.JTextField();
        usagealerts3 = new javax.swing.JTextField();
        datavalidation3 = new javax.swing.JTextField();
        jScrollPane18 = new javax.swing.JScrollPane();
        area6 = new javax.swing.JTextArea();
        jButton6 = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        readingdate3 = new javax.swing.JTextField();
        accountname3 = new javax.swing.JTextField();
        CurrentConsumptionTextField3 = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        PreviousConsumptionTextField3 = new javax.swing.JTextField();
        CurrentChargeTextField3 = new javax.swing.JTextField();
        TotalAmountTextField3 = new javax.swing.JTextField();
        DueDateTextField3 = new javax.swing.JTextField();
        StatusTextField3 = new javax.swing.JTextField();
        FinishPaymentbtn3 = new javax.swing.JButton();
        jLabel96 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jScrollPane19 = new javax.swing.JScrollPane();
        area7 = new javax.swing.JTextArea();
        jPanel28 = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel98 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        ApprovedByTF3 = new javax.swing.JTextField();
        TotalAmountTF3 = new javax.swing.JTextField();
        ClientTF3 = new javax.swing.JTextField();
        FinalApprovalTF3 = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        StatusTF3 = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane21 = new javax.swing.JScrollPane();
        area8 = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton4.setText("Log out");

        jTabbedPane1.setBackground(new java.awt.Color(0, 204, 204));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 0, 12)); // NOI18N

        jPanel8.setBackground(new java.awt.Color(0, 204, 204));

        jLabel30.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel30.setText("ADMIN DASHBOARD");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(jLabel30)
                .addContainerGap(551, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(jLabel30)
                .addContainerGap(2749, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("", jPanel8);

        jPanel2.setBackground(new java.awt.Color(153, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel3.setText("Account No.");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, 27));

        jLabel4.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel4.setText("Account Name:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        jLabel9.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel9.setText("Service Address:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        jLabel10.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel10.setText("Property:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        jLabel11.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel11.setText("Meter:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, -1));

        jLabel12.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel12.setText("Previous Consumption:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, -1, -1));

        jLabel13.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel13.setText("Current Consumption;");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, -1, -1));

        jLabel14.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel14.setText("Due Date:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 260, -1, 13));

        jLabel15.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel15.setText("Billing Period Start:");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 170, -1, -1));

        jLabel17.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel17.setText("Current Charge:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, -1, -1));

        AccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccountNoTFActionPerformed(evt);
            }
        });
        jPanel2.add(AccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 312, 33));

        jLabel16.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel16.setText("Total Amount Due:");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 310, -1, -1));

        CreateBT.setText("CREATE");
        CreateBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateBTActionPerformed(evt);
            }
        });
        jPanel2.add(CreateBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 341, 87, 44));

        AccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccountNameTFActionPerformed(evt);
            }
        });
        jPanel2.add(AccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 312, 33));

        PreviousConsumptionTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviousConsumptionTFActionPerformed(evt);
            }
        });
        jPanel2.add(PreviousConsumptionTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 30, 300, 33));

        CurrentConsumptionTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurrentConsumptionTFActionPerformed(evt);
            }
        });
        jPanel2.add(CurrentConsumptionTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 70, 300, 33));

        BillingPeriodStartTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BillingPeriodStartTFActionPerformed(evt);
            }
        });
        jPanel2.add(BillingPeriodStartTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, 300, 30));

        CurrentChargeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurrentChargeTFActionPerformed(evt);
            }
        });
        jPanel2.add(CurrentChargeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, 300, 33));

        DueDateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DueDateTFActionPerformed(evt);
            }
        });
        jPanel2.add(DueDateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 260, 300, 33));

        TotalAmountDueTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalAmountDueTFActionPerformed(evt);
            }
        });
        jPanel2.add(TotalAmountDueTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, 300, 33));

        SearchBT.setText("SEARCH");
        SearchBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBTActionPerformed(evt);
            }
        });
        jPanel2.add(SearchBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 92, 44));

        UpdateBT.setText("UPDATE");
        UpdateBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBTActionPerformed(evt);
            }
        });
        jPanel2.add(UpdateBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 92, 44));

        DeleteBT.setText("DELETE");
        DeleteBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBTActionPerformed(evt);
            }
        });
        jPanel2.add(DeleteBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 340, 92, 44));

        MeterCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
        jPanel2.add(MeterCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 111, 32));

        PropertyCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Residential", "Commercial", "Industrial", "Institutional" }));
        jPanel2.add(PropertyCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 111, 32));

        ServiceAddressTF.setColumns(20);
        ServiceAddressTF.setRows(5);
        jScrollPane2.setViewportView(ServiceAddressTF);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 312, 33));

        jLabel32.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel32.setText("Reading Date :");
        jPanel2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, -1, -1));

        jLabel33.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel33.setText("Account Status :");
        jPanel2.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, -1, -1));

        AccountStatusCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));
        jPanel2.add(AccountStatusCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, 100, 36));

        ReadingDateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReadingDateTFActionPerformed(evt);
            }
        });
        jPanel2.add(ReadingDateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 99, 31));

        CustomerInfoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Account Number", "Account Name", "Service Address", "Contact Number", "Property", "Meter", "Account Status", "Previous Consumption", "resent Consumption", "Current Change", "Billing Period_Start", "Billing Period End", "Due Date", "Reading Date", "Total Amount Due"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(CustomerInfoTable);
        if (CustomerInfoTable.getColumnModel().getColumnCount() > 0) {
            CustomerInfoTable.getColumnModel().getColumn(0).setResizable(false);
            CustomerInfoTable.getColumnModel().getColumn(1).setResizable(false);
            CustomerInfoTable.getColumnModel().getColumn(2).setResizable(false);
            CustomerInfoTable.getColumnModel().getColumn(3).setResizable(false);
            CustomerInfoTable.getColumnModel().getColumn(4).setResizable(false);
            CustomerInfoTable.getColumnModel().getColumn(5).setResizable(false);
            CustomerInfoTable.getColumnModel().getColumn(6).setResizable(false);
            CustomerInfoTable.getColumnModel().getColumn(7).setResizable(false);
            CustomerInfoTable.getColumnModel().getColumn(8).setResizable(false);
            CustomerInfoTable.getColumnModel().getColumn(9).setResizable(false);
            CustomerInfoTable.getColumnModel().getColumn(10).setResizable(false);
            CustomerInfoTable.getColumnModel().getColumn(11).setResizable(false);
            CustomerInfoTable.getColumnModel().getColumn(12).setResizable(false);
            CustomerInfoTable.getColumnModel().getColumn(13).setResizable(false);
            CustomerInfoTable.getColumnModel().getColumn(14).setResizable(false);
        }

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 1100, 250));

        jLabel34.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel34.setText("Contact Number:");
        jPanel2.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 130, -1));

        Contact_NumberTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Contact_NumberTFActionPerformed(evt);
            }
        });
        jPanel2.add(Contact_NumberTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 310, 40));

        jLabel35.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel35.setText("Billing Period End:");
        jPanel2.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 220, -1, -1));

        BillingPeriodEndTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BillingPeriodEndTFActionPerformed(evt);
            }
        });
        jPanel2.add(BillingPeriodEndTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 220, 300, 30));

        jTabbedPane1.addTab("Customer Management", jPanel2);

        jPanel7.setBackground(new java.awt.Color(153, 255, 255));

        jLabel7.setText("Service Interruption Reports");

        jLabel39.setText("Alert and Notification");

        jLabel41.setText("Address");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Address", "Service Interruption Report", "Alerts and Notification", "Clients Feedbacks"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setResizable(false);
            jTable3.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addGap(63, 63, 63)))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel39)))))
                .addGap(501, 501, 501))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel39)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(2472, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Customer Water Usage Reports", jPanel7);

        jPanel5.setBackground(new java.awt.Color(153, 255, 255));

        jTabbedPane2.setBackground(new java.awt.Color(204, 255, 255));
        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane2.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 12)); // NOI18N

        jPanel11.setBackground(new java.awt.Color(204, 255, 255));

        jLabel29.setFont(new java.awt.Font("Cooper Black", 0, 36)); // NOI18N
        jLabel29.setText("BILLING MANAGEMENT");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addComponent(jLabel29)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("", jPanel11);

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setText("Meter Reading & Calculation");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 93, -1, -1));

        jLabel26.setText("Rate Structure");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 168, -1, -1));

        jLabel27.setText("Usage Alerts");
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 241, -1, -1));

        jLabel28.setText("Data Validation & Error Handling");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 309, -1, -1));
        jPanel4.add(meterreading, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 223, -1));
        jPanel4.add(ratestructure, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 165, 223, -1));
        jPanel4.add(usagealerts, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 238, 223, -1));
        jPanel4.add(datavalidation, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 306, 223, -1));

        area.setColumns(20);
        area.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        area.setRows(5);
        jScrollPane6.setViewportView(area);

        jPanel4.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 350, 700));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, 130, 40));

        jTabbedPane2.addTab("Usage Information", jPanel4);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setText("Total Amount:");

        jLabel2.setText("Due Date:");

        readingdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readingdateActionPerformed(evt);
            }
        });

        accountname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountnameActionPerformed(evt);
            }
        });

        CurrentConsumptionTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurrentConsumptionTextFieldActionPerformed(evt);
            }
        });

        jLabel19.setText("Reading Date:");

        jLabel20.setText("Current Consumption:");

        jLabel21.setText("Previous Consumption:");

        jLabel22.setText("Current Charge:");

        jLabel23.setText("Account Name:");

        jLabel5.setText("Status:");

        PreviousConsumptionTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviousConsumptionTextFieldActionPerformed(evt);
            }
        });

        CurrentChargeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurrentChargeTextFieldActionPerformed(evt);
            }
        });

        TotalAmountTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalAmountTextFieldActionPerformed(evt);
            }
        });

        DueDateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DueDateTextFieldActionPerformed(evt);
            }
        });

        FinishPaymentbtn.setText("FINISH PAYMENT");
        FinishPaymentbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinishPaymentbtnActionPerformed(evt);
            }
        });

        jLabel36.setText("APPROVER:");

        area1.setColumns(20);
        area1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        area1.setRows(5);
        jScrollPane9.setViewportView(area1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(228, 228, 228)
                                .addComponent(FinishPaymentbtn)))
                        .addGap(206, 206, 206))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21))
                                .addGap(58, 58, 58)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CurrentConsumptionTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                    .addComponent(PreviousConsumptionTextField)
                                    .addComponent(CurrentChargeTextField)
                                    .addComponent(TotalAmountTextField)
                                    .addComponent(DueDateTextField)
                                    .addComponent(StatusTextField)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel19))
                                .addGap(99, 99, 99)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(accountname, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(readingdate))))
                        .addGap(207, 207, 207)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(readingdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(accountname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(CurrentConsumptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(PreviousConsumptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(CurrentChargeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TotalAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(DueDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(StatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(FinishPaymentbtn)
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jTabbedPane2.addTab("Billing Details", jPanel1);

        jPanel9.setBackground(new java.awt.Color(204, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setText("Admin:");
        jPanel9.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 45, -1, -1));
        jPanel9.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 42, 294, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Reading Date", "Account Name", "Current Consumption", "Previous Consumption", "Current Charges", "Total Amount", "Due Date", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable2.getColumnModel().getColumn(7).setMaxWidth(180);
            jTable2.getColumnModel().getColumn(8).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 144, 948, -1));

        jLabel31.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        jLabel31.setText("MONTHLY BILLING REPORTS");
        jPanel9.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jTabbedPane2.addTab("Billing Reports", jPanel9);

        jPanel10.setBackground(new java.awt.Color(204, 255, 255));

        jLabel6.setText("TOTAL AMOUNT:");

        jLabel8.setText("FINAL APPROVAL:");

        jLabel24.setText("APPROVED BY:");

        jLabel37.setText("CLIENT:");

        jLabel38.setText("STATUS:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel24)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38))
                .addGap(85, 85, 85)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TotalAmountTF)
                    .addComponent(ApprovedByTF)
                    .addComponent(ClientTF)
                    .addComponent(FinalApprovalTF)
                    .addComponent(StatusTF, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(421, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addComponent(ApprovedByTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TotalAmountTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ClientTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(FinalApprovalTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StatusTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addContainerGap(402, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("", jPanel10);

        jTextArea2.setBackground(new java.awt.Color(204, 255, 255));
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane7.setViewportView(jTextArea2);

        jTabbedPane2.addTab("Reciept", jScrollPane7);

        jTabbedPane3.setBackground(new java.awt.Color(204, 255, 255));
        jTabbedPane3.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane3.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 12)); // NOI18N

        jPanel13.setBackground(new java.awt.Color(204, 255, 255));

        jLabel40.setFont(new java.awt.Font("Cooper Black", 0, 36)); // NOI18N
        jLabel40.setText("BILLING MANAGEMENT");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addComponent(jLabel40)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("", jPanel13);

        jPanel14.setBackground(new java.awt.Color(204, 255, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel42.setText("Meter Reading & Calculation");
        jPanel14.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 93, -1, -1));

        jLabel43.setText("Rate Structure");
        jPanel14.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 168, -1, -1));

        jLabel44.setText("Usage Alerts");
        jPanel14.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 241, -1, -1));

        jLabel45.setText("Data Validation & Error Handling");
        jPanel14.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 309, -1, -1));
        jPanel14.add(meterreading1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 223, -1));
        jPanel14.add(ratestructure1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 165, 223, -1));
        jPanel14.add(usagealerts1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 238, 223, -1));
        jPanel14.add(datavalidation1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 306, 223, -1));

        area2.setColumns(20);
        area2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        area2.setRows(5);
        jScrollPane10.setViewportView(area2);

        jPanel14.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 350, 700));

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("jButton1");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, 130, 40));

        jTabbedPane3.addTab("Usage Information", jPanel14);

        jPanel15.setBackground(new java.awt.Color(204, 255, 255));

        jLabel46.setText("Total Amount:");

        jLabel47.setText("Due Date:");

        readingdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readingdate1ActionPerformed(evt);
            }
        });

        accountname1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountname1ActionPerformed(evt);
            }
        });

        CurrentConsumptionTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurrentConsumptionTextField1ActionPerformed(evt);
            }
        });

        jLabel48.setText("Reading Date:");

        jLabel49.setText("Current Consumption:");

        jLabel50.setText("Previous Consumption:");

        jLabel51.setText("Current Charge:");

        jLabel52.setText("Account Name:");

        jLabel53.setText("Status:");

        PreviousConsumptionTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviousConsumptionTextField1ActionPerformed(evt);
            }
        });

        CurrentChargeTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurrentChargeTextField1ActionPerformed(evt);
            }
        });

        TotalAmountTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalAmountTextField1ActionPerformed(evt);
            }
        });

        DueDateTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DueDateTextField1ActionPerformed(evt);
            }
        });

        FinishPaymentbtn1.setText("FINISH PAYMENT");
        FinishPaymentbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinishPaymentbtn1ActionPerformed(evt);
            }
        });

        jLabel54.setText("APPROVER:");

        area3.setColumns(20);
        area3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        area3.setRows(5);
        jScrollPane11.setViewportView(area3);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                                .addGap(228, 228, 228)
                                .addComponent(FinishPaymentbtn1)))
                        .addGap(206, 206, 206))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel46)
                                    .addComponent(jLabel47)
                                    .addComponent(jLabel53)
                                    .addComponent(jLabel51)
                                    .addComponent(jLabel49)
                                    .addComponent(jLabel50))
                                .addGap(58, 58, 58)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CurrentConsumptionTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                    .addComponent(PreviousConsumptionTextField1)
                                    .addComponent(CurrentChargeTextField1)
                                    .addComponent(TotalAmountTextField1)
                                    .addComponent(DueDateTextField1)
                                    .addComponent(StatusTextField1)))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel52)
                                    .addComponent(jLabel48))
                                .addGap(99, 99, 99)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(accountname1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(readingdate1))))
                        .addGap(207, 207, 207)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(readingdate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(accountname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(CurrentConsumptionTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(PreviousConsumptionTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(CurrentChargeTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(TotalAmountTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(DueDateTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(StatusTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(FinishPaymentbtn1)
                .addGap(39, 39, 39)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jTabbedPane3.addTab("Billing Details", jPanel15);

        jPanel16.setBackground(new java.awt.Color(204, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel55.setText("Admin:");
        jPanel16.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 45, -1, -1));
        jPanel16.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 42, 294, -1));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Reading Date", "Account Name", "Current Consumption", "Previous Consumption", "Current Charges", "Total Amount", "Due Date", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane12.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable4.getColumnModel().getColumn(7).setMaxWidth(180);
            jTable4.getColumnModel().getColumn(8).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
        );

        jPanel16.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 144, 948, -1));

        jLabel56.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        jLabel56.setText("MONTHLY BILLING REPORTS");
        jPanel16.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jTabbedPane3.addTab("Billing Reports", jPanel16);

        jPanel18.setBackground(new java.awt.Color(204, 255, 255));

        jLabel57.setText("TOTAL AMOUNT:");

        jLabel58.setText("FINAL APPROVAL:");

        jLabel59.setText("APPROVED BY:");

        jLabel60.setText("CLIENT:");

        jLabel61.setText("STATUS:");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel57)
                    .addComponent(jLabel58)
                    .addComponent(jLabel59)
                    .addComponent(jLabel60)
                    .addComponent(jLabel61))
                .addGap(85, 85, 85)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TotalAmountTF1)
                    .addComponent(ApprovedByTF1)
                    .addComponent(ClientTF1)
                    .addComponent(FinalApprovalTF1)
                    .addComponent(StatusTF1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(421, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel59)
                    .addComponent(ApprovedByTF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(TotalAmountTF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ClientTF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(FinalApprovalTF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StatusTF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61))
                .addContainerGap(402, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("", jPanel18);

        jTextArea4.setBackground(new java.awt.Color(204, 255, 255));
        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane13.setViewportView(jTextArea4);

        jTabbedPane3.addTab("Reciept", jScrollPane13);

        jTabbedPane4.setBackground(new java.awt.Color(204, 255, 255));
        jTabbedPane4.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane4.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 12)); // NOI18N

        jPanel19.setBackground(new java.awt.Color(204, 255, 255));

        jLabel62.setFont(new java.awt.Font("Cooper Black", 0, 36)); // NOI18N
        jLabel62.setText("BILLING MANAGEMENT");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addComponent(jLabel62)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("", jPanel19);

        jPanel20.setBackground(new java.awt.Color(204, 255, 255));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel63.setText("Meter Reading & Calculation");
        jPanel20.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 93, -1, -1));

        jLabel64.setText("Rate Structure");
        jPanel20.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 168, -1, -1));

        jLabel65.setText("Usage Alerts");
        jPanel20.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 241, -1, -1));

        jLabel66.setText("Data Validation & Error Handling");
        jPanel20.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 309, -1, -1));
        jPanel20.add(meterreading2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 223, -1));
        jPanel20.add(ratestructure2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 165, 223, -1));
        jPanel20.add(usagealerts2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 238, 223, -1));
        jPanel20.add(datavalidation2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 306, 223, -1));

        area4.setColumns(20);
        area4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        area4.setRows(5);
        jScrollPane14.setViewportView(area4);

        jPanel20.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 350, 700));

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("jButton1");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel20.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, 130, 40));

        jTabbedPane4.addTab("Usage Information", jPanel20);

        jPanel21.setBackground(new java.awt.Color(204, 255, 255));

        jLabel67.setText("Total Amount:");

        jLabel68.setText("Due Date:");

        readingdate2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readingdate2ActionPerformed(evt);
            }
        });

        accountname2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountname2ActionPerformed(evt);
            }
        });

        CurrentConsumptionTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurrentConsumptionTextField2ActionPerformed(evt);
            }
        });

        jLabel69.setText("Reading Date:");

        jLabel70.setText("Current Consumption:");

        jLabel71.setText("Previous Consumption:");

        jLabel72.setText("Current Charge:");

        jLabel73.setText("Account Name:");

        jLabel74.setText("Status:");

        PreviousConsumptionTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviousConsumptionTextField2ActionPerformed(evt);
            }
        });

        CurrentChargeTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurrentChargeTextField2ActionPerformed(evt);
            }
        });

        TotalAmountTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalAmountTextField2ActionPerformed(evt);
            }
        });

        DueDateTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DueDateTextField2ActionPerformed(evt);
            }
        });

        FinishPaymentbtn2.setText("FINISH PAYMENT");
        FinishPaymentbtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinishPaymentbtn2ActionPerformed(evt);
            }
        });

        jLabel75.setText("APPROVER:");

        area5.setColumns(20);
        area5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        area5.setRows(5);
        jScrollPane15.setViewportView(area5);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel21Layout.createSequentialGroup()
                                .addComponent(jLabel75)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel21Layout.createSequentialGroup()
                                .addGap(228, 228, 228)
                                .addComponent(FinishPaymentbtn2)))
                        .addGap(206, 206, 206))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel67)
                                    .addComponent(jLabel68)
                                    .addComponent(jLabel74)
                                    .addComponent(jLabel72)
                                    .addComponent(jLabel70)
                                    .addComponent(jLabel71))
                                .addGap(58, 58, 58)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CurrentConsumptionTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                    .addComponent(PreviousConsumptionTextField2)
                                    .addComponent(CurrentChargeTextField2)
                                    .addComponent(TotalAmountTextField2)
                                    .addComponent(DueDateTextField2)
                                    .addComponent(StatusTextField2)))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel73)
                                    .addComponent(jLabel69))
                                .addGap(99, 99, 99)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(accountname2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(readingdate2))))
                        .addGap(207, 207, 207)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(readingdate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(accountname2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(CurrentConsumptionTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(PreviousConsumptionTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(CurrentChargeTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(TotalAmountTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(DueDateTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(StatusTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(FinishPaymentbtn2)
                .addGap(39, 39, 39)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jTabbedPane4.addTab("Billing Details", jPanel21);

        jPanel22.setBackground(new java.awt.Color(204, 255, 255));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel76.setText("Admin:");
        jPanel22.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 45, -1, -1));
        jPanel22.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 42, 294, -1));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Reading Date", "Account Name", "Current Consumption", "Previous Consumption", "Current Charges", "Total Amount", "Due Date", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane16.setViewportView(jTable5);
        if (jTable5.getColumnModel().getColumnCount() > 0) {
            jTable5.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable5.getColumnModel().getColumn(7).setMaxWidth(180);
            jTable5.getColumnModel().getColumn(8).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
        );

        jPanel22.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 144, 948, -1));

        jLabel77.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        jLabel77.setText("MONTHLY BILLING REPORTS");
        jPanel22.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jTabbedPane4.addTab("Billing Reports", jPanel22);

        jPanel24.setBackground(new java.awt.Color(204, 255, 255));

        jLabel78.setText("TOTAL AMOUNT:");

        jLabel79.setText("FINAL APPROVAL:");

        jLabel80.setText("APPROVED BY:");

        jLabel81.setText("CLIENT:");

        jLabel82.setText("STATUS:");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel78)
                    .addComponent(jLabel79)
                    .addComponent(jLabel80)
                    .addComponent(jLabel81)
                    .addComponent(jLabel82))
                .addGap(85, 85, 85)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TotalAmountTF2)
                    .addComponent(ApprovedByTF2)
                    .addComponent(ClientTF2)
                    .addComponent(FinalApprovalTF2)
                    .addComponent(StatusTF2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(421, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel80)
                    .addComponent(ApprovedByTF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(TotalAmountTF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ClientTF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(FinalApprovalTF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StatusTF2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel82))
                .addContainerGap(402, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("", jPanel24);

        jTextArea5.setBackground(new java.awt.Color(204, 255, 255));
        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane17.setViewportView(jTextArea5);

        jTabbedPane4.addTab("Reciept", jScrollPane17);

        jTabbedPane5.setBackground(new java.awt.Color(204, 255, 255));
        jTabbedPane5.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane5.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 12)); // NOI18N

        jPanel25.setBackground(new java.awt.Color(204, 255, 255));

        jLabel83.setFont(new java.awt.Font("Cooper Black", 0, 36)); // NOI18N
        jLabel83.setText("BILLING MANAGEMENT");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addComponent(jLabel83)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("", jPanel25);

        jPanel26.setBackground(new java.awt.Color(204, 255, 255));
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel84.setText("Meter Reading & Calculation");
        jPanel26.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 93, -1, -1));

        jLabel85.setText("Rate Structure");
        jPanel26.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 168, -1, -1));

        jLabel86.setText("Usage Alerts");
        jPanel26.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 241, -1, -1));

        jLabel87.setText("Data Validation & Error Handling");
        jPanel26.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 309, -1, -1));
        jPanel26.add(meterreading3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 223, -1));
        jPanel26.add(ratestructure3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 165, 223, -1));
        jPanel26.add(usagealerts3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 238, 223, -1));
        jPanel26.add(datavalidation3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 306, 223, -1));

        area6.setColumns(20);
        area6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        area6.setRows(5);
        jScrollPane18.setViewportView(area6);

        jPanel26.add(jScrollPane18, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 350, 700));

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setText("jButton1");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel26.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, 130, 40));

        jTabbedPane5.addTab("Usage Information", jPanel26);

        jPanel27.setBackground(new java.awt.Color(204, 255, 255));

        jLabel88.setText("Total Amount:");

        jLabel89.setText("Due Date:");

        readingdate3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readingdate3ActionPerformed(evt);
            }
        });

        accountname3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountname3ActionPerformed(evt);
            }
        });

        CurrentConsumptionTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurrentConsumptionTextField3ActionPerformed(evt);
            }
        });

        jLabel90.setText("Reading Date:");

        jLabel91.setText("Current Consumption:");

        jLabel92.setText("Previous Consumption:");

        jLabel93.setText("Current Charge:");

        jLabel94.setText("Account Name:");

        jLabel95.setText("Status:");

        PreviousConsumptionTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviousConsumptionTextField3ActionPerformed(evt);
            }
        });

        CurrentChargeTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurrentChargeTextField3ActionPerformed(evt);
            }
        });

        TotalAmountTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalAmountTextField3ActionPerformed(evt);
            }
        });

        DueDateTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DueDateTextField3ActionPerformed(evt);
            }
        });

        FinishPaymentbtn3.setText("FINISH PAYMENT");
        FinishPaymentbtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinishPaymentbtn3ActionPerformed(evt);
            }
        });

        jLabel96.setText("APPROVER:");

        area7.setColumns(20);
        area7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        area7.setRows(5);
        jScrollPane19.setViewportView(area7);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel27Layout.createSequentialGroup()
                                .addComponent(jLabel96)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel27Layout.createSequentialGroup()
                                .addGap(228, 228, 228)
                                .addComponent(FinishPaymentbtn3)))
                        .addGap(206, 206, 206))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel88)
                                    .addComponent(jLabel89)
                                    .addComponent(jLabel95)
                                    .addComponent(jLabel93)
                                    .addComponent(jLabel91)
                                    .addComponent(jLabel92))
                                .addGap(58, 58, 58)
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CurrentConsumptionTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                    .addComponent(PreviousConsumptionTextField3)
                                    .addComponent(CurrentChargeTextField3)
                                    .addComponent(TotalAmountTextField3)
                                    .addComponent(DueDateTextField3)
                                    .addComponent(StatusTextField3)))
                            .addGroup(jPanel27Layout.createSequentialGroup()
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel94)
                                    .addComponent(jLabel90))
                                .addGap(99, 99, 99)
                                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(accountname3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(readingdate3))))
                        .addGap(207, 207, 207)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel90)
                    .addComponent(readingdate3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94)
                    .addComponent(accountname3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(CurrentConsumptionTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel92)
                    .addComponent(PreviousConsumptionTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel93)
                    .addComponent(CurrentChargeTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88)
                    .addComponent(TotalAmountTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(DueDateTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(StatusTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(FinishPaymentbtn3)
                .addGap(39, 39, 39)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel96)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jTabbedPane5.addTab("Billing Details", jPanel27);

        jPanel28.setBackground(new java.awt.Color(204, 255, 255));
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel97.setText("Admin:");
        jPanel28.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 45, -1, -1));
        jPanel28.add(jTextField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 42, 294, -1));

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Reading Date", "Account Name", "Current Consumption", "Previous Consumption", "Current Charges", "Total Amount", "Due Date", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane20.setViewportView(jTable6);
        if (jTable6.getColumnModel().getColumnCount() > 0) {
            jTable6.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable6.getColumnModel().getColumn(7).setMaxWidth(180);
            jTable6.getColumnModel().getColumn(8).setMaxWidth(150);
        }

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
        );

        jPanel28.add(jPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 144, 948, -1));

        jLabel98.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        jLabel98.setText("MONTHLY BILLING REPORTS");
        jPanel28.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jTabbedPane5.addTab("Billing Reports", jPanel28);

        jPanel30.setBackground(new java.awt.Color(204, 255, 255));

        jLabel99.setText("TOTAL AMOUNT:");

        jLabel100.setText("FINAL APPROVAL:");

        jLabel101.setText("APPROVED BY:");

        jLabel102.setText("CLIENT:");

        jLabel103.setText("STATUS:");

        jButton2.setText("APPROVED");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel99)
                    .addComponent(jLabel100)
                    .addComponent(jLabel101)
                    .addComponent(jLabel102)
                    .addComponent(jLabel103))
                .addGap(85, 85, 85)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TotalAmountTF3)
                    .addComponent(ApprovedByTF3)
                    .addComponent(ClientTF3)
                    .addComponent(FinalApprovalTF3)
                    .addComponent(StatusTF3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(421, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(359, 359, 359))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel101)
                    .addComponent(ApprovedByTF3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel99)
                    .addComponent(TotalAmountTF3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ClientTF3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel102))
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel100)
                    .addComponent(FinalApprovalTF3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StatusTF3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel103))
                .addGap(90, 90, 90)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(280, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("", jPanel30);

        area8.setColumns(20);
        area8.setRows(5);
        jScrollPane21.setViewportView(area8);

        jTabbedPane5.addTab("Reciept", jScrollPane21);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Billing Management", jPanel5);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane8.setViewportView(jTextArea3);

        jTabbedPane1.addTab("tab5", jScrollPane8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AccountNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccountNoTFActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_AccountNoTFActionPerformed

    private void AccountNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccountNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AccountNameTFActionPerformed

    private void PreviousConsumptionTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviousConsumptionTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PreviousConsumptionTFActionPerformed

    private void CurrentConsumptionTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurrentConsumptionTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CurrentConsumptionTFActionPerformed

    private void BillingPeriodStartTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BillingPeriodStartTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BillingPeriodStartTFActionPerformed

    private void CurrentChargeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurrentChargeTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CurrentChargeTFActionPerformed

    private void DueDateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DueDateTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DueDateTFActionPerformed

    private void TotalAmountDueTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalAmountDueTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalAmountDueTFActionPerformed

    private void SearchBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBTActionPerformed
        // TODO add your handling code here:
     String accountNo = AccountNoTF.getText().trim();

    if (accountNo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter an Account Number.");
        return;
    }

    try {
      
        pst = con.prepareStatement("SELECT * FROM clientinfo WHERE Account_No = ?");
        pst.setString(1, accountNo); 

        rs = pst.executeQuery();
        
        if (rs.next()) {
            AccountNameTF.setText(rs.getString("Account_Name"));
            ServiceAddressTF.setText(rs.getString("Service_Address"));
            Contact_NumberTF.setText(String.valueOf(rs.getInt("Contact_Number")));
            PropertyCB.setSelectedItem(rs.getString("Property"));
            MeterCB.setSelectedItem(rs.getString("Meter"));
            AccountStatusCB.setSelectedItem(rs.getString("Account_Status"));
            PreviousConsumptionTF.setText(String.valueOf(rs.getBigDecimal("Previous_Consumption")));
            CurrentConsumptionTF.setText(String.valueOf(rs.getBigDecimal("Current_Consumption")));
            CurrentChargeTF.setText(String.valueOf(rs.getBigDecimal("Current_Charge")));
            BillingPeriodStartTF.setText(new SimpleDateFormat("MM/dd/yyyy").format(rs.getDate("Billing_Period_Start")));
            BillingPeriodEndTF.setText(new SimpleDateFormat("MM/dd/yyyy").format(rs.getDate("Billing_Period_End")));
            ReadingDateTF.setText(new SimpleDateFormat("MM/dd/yyyy").format(rs.getDate("Reading_Date")));
            DueDateTF.setText(new SimpleDateFormat("MM/dd/yyyy").format(rs.getDate("Due_Date")));
            TotalAmountDueTF.setText(String.valueOf(rs.getBigDecimal("Total_Amount_Due")));
        } else {
            JOptionPane.showMessageDialog(this, "No customer found with Account Number: " + accountNo);
        }

    } catch (SQLException ex) {
        Logger.getLogger(Admin_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Error during search: " + ex.getMessage());
    }

    }//GEN-LAST:event_SearchBTActionPerformed

    private void UpdateBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBTActionPerformed
        // TODO add your handling code here:
        try {
        String accountNo = AccountNoTF.getText().trim();

        if (accountNo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Account No.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String accountName = AccountNameTF.getText().trim();
        String serviceAddress = ServiceAddressTF.getText().trim();
        int contactNumber = Integer.parseInt(Contact_NumberTF.getText().trim());
        String property = PropertyCB.getSelectedItem().toString();
        String meter = MeterCB.getSelectedItem().toString();
        String accountStatus = AccountStatusCB.getSelectedItem().toString();
        BigDecimal previousConsumption = new BigDecimal(PreviousConsumptionTF.getText().trim()).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal currentConsumption = new BigDecimal(CurrentConsumptionTF.getText().trim()).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal currentCharge = new BigDecimal(CurrentChargeTF.getText().trim()).setScale(2, BigDecimal.ROUND_HALF_UP);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        java.sql.Date billingPeriodStart = new java.sql.Date(sdf.parse(BillingPeriodStartTF.getText().trim()).getTime());
        java.sql.Date billingPeriodEnd = new java.sql.Date(sdf.parse(BillingPeriodEndTF.getText().trim()).getTime());
        java.sql.Date readingDate = new java.sql.Date(sdf.parse(ReadingDateTF.getText().trim()).getTime());
        java.sql.Date dueDate = new java.sql.Date(sdf.parse(DueDateTF.getText().trim()).getTime());

        BigDecimal totalAmountDue = currentCharge.multiply(currentConsumption);

        String query = "UPDATE clientinfo SET Account_Name = ?, Service_Address = ?, Contact_Number = ?, Property = ?, Meter = ?, "
                     + "Account_Status = ?, Previous_Consumption = ?, Current_Consumption = ?, Current_Charge = ?, "
                     + "Billing_Period_Start = ?, Billing_Period_End = ?, Reading_Date = ?, Due_Date = ?, Total_Amount_Due = ? "
                     + "WHERE Account_No = ?";
        pst = con.prepareStatement(query);

        pst.setString(1, accountName);
        pst.setString(2, serviceAddress);
        pst.setInt(3, contactNumber);
        pst.setString(4, property);
        pst.setString(5, meter);
        pst.setString(6, accountStatus);
        pst.setBigDecimal(7, previousConsumption);
        pst.setBigDecimal(8, currentConsumption);
        pst.setBigDecimal(9, currentCharge);
        pst.setDate(10, billingPeriodStart);
        pst.setDate(11, billingPeriodEnd);
        pst.setDate(12, readingDate);
        pst.setDate(13, dueDate);
        pst.setBigDecimal(14, totalAmountDue);
        pst.setString(15, accountNo);

        int rowsUpdated = pst.executeUpdate();

        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Client information updated successfully!");

            DefaultTableModel model = (DefaultTableModel) CustomerInfoTable.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                if (model.getValueAt(i, 0).equals(accountNo)) { 
                    model.setValueAt(accountName, i, 1);
                    model.setValueAt(serviceAddress, i, 2);
                    model.setValueAt(contactNumber, i, 3);
                    model.setValueAt(property, i, 4);
                    model.setValueAt(meter, i, 5);
                    model.setValueAt(accountStatus, i, 6);
                    model.setValueAt(previousConsumption, i, 7);
                    model.setValueAt(currentConsumption, i, 8);
                    model.setValueAt(currentCharge, i, 9);
                    model.setValueAt(billingPeriodStart, i, 10);
                    model.setValueAt(billingPeriodEnd, i, 11);
                    model.setValueAt(readingDate, i, 12);
                    model.setValueAt(dueDate, i, 13);
                    model.setValueAt(totalAmountDue, i, 14);
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No matching record found to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid input in numeric fields. Please check your data.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "Invalid date format. Please use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException ex) {
        Logger.getLogger(Admin_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_UpdateBTActionPerformed

    private void CreateBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateBTActionPerformed
        // TODO add your handling code here:
        
      try {
    
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

    String Account_Name = AccountNameTF.getText();
    String Service_Address = ServiceAddressTF.getText();
    int Contact_Number = Integer.parseInt(Contact_NumberTF.getText());
    String selectedItemProperty = (String) PropertyCB.getSelectedItem();
    String selectedItemMeter = (String) MeterCB.getSelectedItem();
    String selectedItemAccount_Status = (String) AccountStatusCB.getSelectedItem();
    BigDecimal Previous_Consumption = new BigDecimal(PreviousConsumptionTF.getText()).setScale(2, RoundingMode.HALF_UP);
    BigDecimal Current_Consumption = new BigDecimal(CurrentConsumptionTF.getText()).setScale(2, RoundingMode.HALF_UP);
    BigDecimal Current_Charge = new BigDecimal(CurrentChargeTF.getText()).setScale(2, RoundingMode.HALF_UP);

    Date startDate = sdf.parse(BillingPeriodStartTF.getText());  
    Date endDate = sdf.parse(BillingPeriodEndTF.getText());      
    Date readingDate = sdf.parse(ReadingDateTF.getText());     
    Date dueDate = sdf.parse(DueDateTF.getText());             

    java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
    java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
    java.sql.Date sqlReadingDate = new java.sql.Date(readingDate.getTime());
    java.sql.Date sqlDueDate = new java.sql.Date(dueDate.getTime());

    String newAccountNo = generateNextAccountNo(con);

    
    BigDecimal Total_Amount_Due = Current_Charge.multiply(Current_Consumption);

   
    pst = con.prepareStatement(
        "INSERT INTO clientinfo (Account_No, Account_Name, Service_Address, Contact_Number, Property, Meter, Account_Status, " +
        "Previous_Consumption, Current_Consumption, Current_Charge, Billing_Period_Start, Billing_Period_End, Reading_Date, Due_Date, Total_Amount_Due) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
    );

   
    pst.setString(1, newAccountNo); 
    pst.setString(2, Account_Name);
    pst.setString(3, Service_Address);
    pst.setInt(4, Contact_Number);
    pst.setString(5, selectedItemProperty);
    pst.setString(6, selectedItemMeter);
    pst.setString(7, selectedItemAccount_Status);
    pst.setBigDecimal(8, Previous_Consumption);
    pst.setBigDecimal(9, Current_Consumption);
    pst.setBigDecimal(10, Current_Charge);
    pst.setDate(11, sqlStartDate);
    pst.setDate(12, sqlEndDate);
    pst.setDate(13, sqlReadingDate);
    pst.setDate(14, sqlDueDate);
    pst.setBigDecimal(15, Total_Amount_Due);

 
    int rowsInserted = pst.executeUpdate();


    if (rowsInserted > 0) {
        DefaultTableModel model = (DefaultTableModel) CustomerInfoTable.getModel();
        model.addRow(new Object[]{
            newAccountNo, Account_Name, Service_Address, Contact_Number, selectedItemProperty,
            selectedItemMeter, selectedItemAccount_Status, Previous_Consumption, Current_Consumption,
            Current_Charge, new SimpleDateFormat("MM/dd/yyyy").format(sqlStartDate),
            new SimpleDateFormat("MM/dd/yyyy").format(sqlEndDate),
            new SimpleDateFormat("MM/dd/yyyy").format(sqlReadingDate),
            new SimpleDateFormat("MM/dd/yyyy").format(sqlDueDate),
            Total_Amount_Due
        });

        JOptionPane.showMessageDialog(this, "Client Info Added Successfully! Account_No: " + newAccountNo);
    } else {
        JOptionPane.showMessageDialog(this, "Record Failed to Save!");
    }

} catch (ParseException e) {
    JOptionPane.showMessageDialog(this, "Invalid date format. Please use MM/dd/yyyy.");
} catch (SQLException ex) {
    Logger.getLogger(Admin_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
    JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Invalid input for numeric fields: " + e.getMessage());
}
            
            
            
    }//GEN-LAST:event_CreateBTActionPerformed

    private void ReadingDateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReadingDateTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ReadingDateTFActionPerformed

    private void BillingPeriodEndTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BillingPeriodEndTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BillingPeriodEndTFActionPerformed

    private void Contact_NumberTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Contact_NumberTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Contact_NumberTFActionPerformed

    private void DeleteBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBTActionPerformed
        // TODO add your handling code here:
        try {
        String accountNo = AccountNoTF.getText().trim();

        if (accountNo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Account No.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the client with Account No: " + accountNo + "?", 
                                                          "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        String query = "DELETE FROM clientinfo WHERE Account_No = ?";
        pst = con.prepareStatement(query);
        pst.setString(1, accountNo);

        int rowsDeleted = pst.executeUpdate();

        if (rowsDeleted > 0) {
            JOptionPane.showMessageDialog(this, "Client information deleted successfully!");

            DefaultTableModel model = (DefaultTableModel) CustomerInfoTable.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                if (model.getValueAt(i, 0).equals(accountNo)) { 
                    model.removeRow(i); 
                    break;
                }
            }

            AccountNoTF.setText("");
            AccountNameTF.setText("");
            ServiceAddressTF.setText("");
            Contact_NumberTF.setText("");
            PropertyCB.setSelectedIndex(-1);
            MeterCB.setSelectedIndex(-1);
            AccountStatusCB.setSelectedIndex(-1);
            PreviousConsumptionTF.setText("");
            CurrentConsumptionTF.setText("");
            CurrentChargeTF.setText("");
            BillingPeriodStartTF.setText("");
            BillingPeriodEndTF.setText("");
            ReadingDateTF.setText("");
            DueDateTF.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "No record found with Account No: " + accountNo, "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException ex) {
        Logger.getLogger(Admin_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_DeleteBTActionPerformed

    private void FinishPaymentbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinishPaymentbtnActionPerformed

        int a = javax.swing.JOptionPane.showConfirmDialog(
            null,
            "Do you really want to proceed?",
            "Select",
            javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (a == javax.swing.JOptionPane.YES_OPTION) {

            int currentIndex = jTabbedPane2.getSelectedIndex();

            int nextIndex = currentIndex + 1;

            if (nextIndex < jTabbedPane2.getTabCount()) {
                jTabbedPane2.setSelectedIndex(nextIndex);
            } else {

                jTabbedPane2.setSelectedIndex(2);
            }
        }
        area1.setText("********************************************\n");
        area1.setText(area1.getText()+"*       WATER BILLING SYSTEM                  *\n");
        area1.setText(area1.getText()+"********************************************\n");

        area1.setText(area1.getText()+"************Usage Information************\n");

        area1.append("\nMeter Reading & Calculation : " + meterreading.getText());
        area1.append("\nRate Structure : " + ratestructure.getText());
        area1.append("\nUsage Alerts : " + usagealerts.getText());
        area1.append("\nData Validation & Error Handling : " + datavalidation.getText());

        area1.setText(area1.getText()+"\n**************Billing Details**************\n");
        area1.append("\nReading Date  : " + readingdate.getText());
        area1.append("\nAccount Name : " + accountname.getText());
        area1.append("\nCurrent Consumption : " + CurrentConsumptionTextField.getText());
        area1.append("\nPrevious Consumption : " + PreviousConsumptionTextField.getText());
        area1.append("\nCurrent Charge  : " + CurrentChargeTextField.getText());
        area1.append("\nTotal Amount : " + TotalAmountTextField.getText());
        area1.append("\nDueDate : " + DueDateTextField.getText());
        area1.append("\nStatus : " + StatusTextField.getText());
    }//GEN-LAST:event_FinishPaymentbtnActionPerformed

    private void DueDateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DueDateTextFieldActionPerformed
        // TODO add your handling code here:
        StatusTextField.requestFocus();
    }//GEN-LAST:event_DueDateTextFieldActionPerformed

    private void TotalAmountTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalAmountTextFieldActionPerformed
        // TODO add your handling code here:
        DueDateTextField.requestFocus();
    }//GEN-LAST:event_TotalAmountTextFieldActionPerformed

    private void CurrentChargeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurrentChargeTextFieldActionPerformed
        // TODO add your handling code here:
        TotalAmountTextField.requestFocus();
    }//GEN-LAST:event_CurrentChargeTextFieldActionPerformed

    private void PreviousConsumptionTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviousConsumptionTextFieldActionPerformed
        // TODO add your handling code here:
        CurrentChargeTextField.requestFocus();
    }//GEN-LAST:event_PreviousConsumptionTextFieldActionPerformed

    private void CurrentConsumptionTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurrentConsumptionTextFieldActionPerformed
        // TODO add your handling code here:
        PreviousConsumptionTextField.requestFocus();
    }//GEN-LAST:event_CurrentConsumptionTextFieldActionPerformed

    private void accountnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountnameActionPerformed
        // TODO add your handling code here:
        CurrentConsumptionTextField.requestFocus();
    }//GEN-LAST:event_accountnameActionPerformed

    private void readingdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readingdateActionPerformed
        // TODO add your handling code here:
        accountname.requestFocus();
    }//GEN-LAST:event_readingdateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        area.setText("********************************************\n");
        area.setText(area.getText()+"*       WATER BILLING SYSTEM                  *\n");
        area.setText(area.getText()+"********************************************\n");

        area.setText(area.getText()+"************Usage Information************\n");

        area.append("\nMeter Reading & Calculation : " + meterreading.getText());
        area.append("\nRate Structure : " + ratestructure.getText());
        area.append("\nUsage Alerts : " + usagealerts.getText());
        area.append("\nData Validation & Error Handling : " + datavalidation.getText());

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void readingdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readingdate1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_readingdate1ActionPerformed

    private void accountname1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountname1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accountname1ActionPerformed

    private void CurrentConsumptionTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurrentConsumptionTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CurrentConsumptionTextField1ActionPerformed

    private void PreviousConsumptionTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviousConsumptionTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PreviousConsumptionTextField1ActionPerformed

    private void CurrentChargeTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurrentChargeTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CurrentChargeTextField1ActionPerformed

    private void TotalAmountTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalAmountTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalAmountTextField1ActionPerformed

    private void DueDateTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DueDateTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DueDateTextField1ActionPerformed

    private void FinishPaymentbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinishPaymentbtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FinishPaymentbtn1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void readingdate2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readingdate2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_readingdate2ActionPerformed

    private void accountname2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountname2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accountname2ActionPerformed

    private void CurrentConsumptionTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurrentConsumptionTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CurrentConsumptionTextField2ActionPerformed

    private void PreviousConsumptionTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviousConsumptionTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PreviousConsumptionTextField2ActionPerformed

    private void CurrentChargeTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurrentChargeTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CurrentChargeTextField2ActionPerformed

    private void TotalAmountTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalAmountTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalAmountTextField2ActionPerformed

    private void DueDateTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DueDateTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DueDateTextField2ActionPerformed

    private void FinishPaymentbtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinishPaymentbtn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FinishPaymentbtn2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void readingdate3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readingdate3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_readingdate3ActionPerformed

    private void accountname3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountname3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accountname3ActionPerformed

    private void CurrentConsumptionTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurrentConsumptionTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CurrentConsumptionTextField3ActionPerformed

    private void PreviousConsumptionTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviousConsumptionTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PreviousConsumptionTextField3ActionPerformed

    private void CurrentChargeTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurrentChargeTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CurrentChargeTextField3ActionPerformed

    private void TotalAmountTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalAmountTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalAmountTextField3ActionPerformed

    private void DueDateTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DueDateTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DueDateTextField3ActionPerformed

    private void FinishPaymentbtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinishPaymentbtn3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FinishPaymentbtn3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Admin_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AccountNameTF;
    private javax.swing.JTextField AccountNoTF;
    private javax.swing.JComboBox<String> AccountStatusCB;
    private javax.swing.JTextField ApprovedByTF;
    private javax.swing.JTextField ApprovedByTF1;
    private javax.swing.JTextField ApprovedByTF2;
    private javax.swing.JTextField ApprovedByTF3;
    private javax.swing.JTextField BillingPeriodEndTF;
    private javax.swing.JTextField BillingPeriodStartTF;
    private javax.swing.JTextField ClientTF;
    private javax.swing.JTextField ClientTF1;
    private javax.swing.JTextField ClientTF2;
    private javax.swing.JTextField ClientTF3;
    private javax.swing.JTextField Contact_NumberTF;
    private javax.swing.JButton CreateBT;
    private javax.swing.JTextField CurrentChargeTF;
    private javax.swing.JTextField CurrentChargeTextField;
    private javax.swing.JTextField CurrentChargeTextField1;
    private javax.swing.JTextField CurrentChargeTextField2;
    private javax.swing.JTextField CurrentChargeTextField3;
    private javax.swing.JTextField CurrentConsumptionTF;
    private javax.swing.JTextField CurrentConsumptionTextField;
    private javax.swing.JTextField CurrentConsumptionTextField1;
    private javax.swing.JTextField CurrentConsumptionTextField2;
    private javax.swing.JTextField CurrentConsumptionTextField3;
    private javax.swing.JTable CustomerInfoTable;
    private javax.swing.JButton DeleteBT;
    private javax.swing.JTextField DueDateTF;
    private javax.swing.JTextField DueDateTextField;
    private javax.swing.JTextField DueDateTextField1;
    private javax.swing.JTextField DueDateTextField2;
    private javax.swing.JTextField DueDateTextField3;
    private javax.swing.JTextField FinalApprovalTF;
    private javax.swing.JTextField FinalApprovalTF1;
    private javax.swing.JTextField FinalApprovalTF2;
    private javax.swing.JTextField FinalApprovalTF3;
    private javax.swing.JButton FinishPaymentbtn;
    private javax.swing.JButton FinishPaymentbtn1;
    private javax.swing.JButton FinishPaymentbtn2;
    private javax.swing.JButton FinishPaymentbtn3;
    private javax.swing.JComboBox<String> MeterCB;
    private javax.swing.JTextField PreviousConsumptionTF;
    private javax.swing.JTextField PreviousConsumptionTextField;
    private javax.swing.JTextField PreviousConsumptionTextField1;
    private javax.swing.JTextField PreviousConsumptionTextField2;
    private javax.swing.JTextField PreviousConsumptionTextField3;
    private javax.swing.JComboBox<String> PropertyCB;
    private javax.swing.JTextField ReadingDateTF;
    private javax.swing.JButton SearchBT;
    private javax.swing.JTextArea ServiceAddressTF;
    private javax.swing.JTextField StatusTF;
    private javax.swing.JTextField StatusTF1;
    private javax.swing.JTextField StatusTF2;
    private javax.swing.JTextField StatusTF3;
    private javax.swing.JTextField StatusTextField;
    private javax.swing.JTextField StatusTextField1;
    private javax.swing.JTextField StatusTextField2;
    private javax.swing.JTextField StatusTextField3;
    private javax.swing.JTextField TotalAmountDueTF;
    private javax.swing.JTextField TotalAmountTF;
    private javax.swing.JTextField TotalAmountTF1;
    private javax.swing.JTextField TotalAmountTF2;
    private javax.swing.JTextField TotalAmountTF3;
    private javax.swing.JTextField TotalAmountTextField;
    private javax.swing.JTextField TotalAmountTextField1;
    private javax.swing.JTextField TotalAmountTextField2;
    private javax.swing.JTextField TotalAmountTextField3;
    private javax.swing.JButton UpdateBT;
    private javax.swing.JTextField accountname;
    private javax.swing.JTextField accountname1;
    private javax.swing.JTextField accountname2;
    private javax.swing.JTextField accountname3;
    private javax.swing.JTextArea area;
    private javax.swing.JTextArea area1;
    private javax.swing.JTextArea area2;
    private javax.swing.JTextArea area3;
    private javax.swing.JTextArea area4;
    private javax.swing.JTextArea area5;
    private javax.swing.JTextArea area6;
    private javax.swing.JTextArea area7;
    private javax.swing.JTextArea area8;
    private javax.swing.JTextField datavalidation;
    private javax.swing.JTextField datavalidation1;
    private javax.swing.JTextField datavalidation2;
    private javax.swing.JTextField datavalidation3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField meterreading;
    private javax.swing.JTextField meterreading1;
    private javax.swing.JTextField meterreading2;
    private javax.swing.JTextField meterreading3;
    private javax.swing.JTextField ratestructure;
    private javax.swing.JTextField ratestructure1;
    private javax.swing.JTextField ratestructure2;
    private javax.swing.JTextField ratestructure3;
    private javax.swing.JTextField readingdate;
    private javax.swing.JTextField readingdate1;
    private javax.swing.JTextField readingdate2;
    private javax.swing.JTextField readingdate3;
    private javax.swing.JTextField usagealerts;
    private javax.swing.JTextField usagealerts1;
    private javax.swing.JTextField usagealerts2;
    private javax.swing.JTextField usagealerts3;
    // End of variables declaration//GEN-END:variables
}
