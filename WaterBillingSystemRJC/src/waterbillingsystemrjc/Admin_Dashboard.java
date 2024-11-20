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
import java.text.SimpleDateFormat;  // For parsing date strings
import java.util.Date; 


public class Admin_Dashboard extends javax.swing.JFrame {

    public Admin_Dashboard() {
        initComponents();
        Connect();
    }
    
    Connection con;
    PreparedStatement pst;
    
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
        ReadBT = new javax.swing.JButton();
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
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ReadingDateTextField = new javax.swing.JTextField();
        AccountNameTextField = new javax.swing.JTextField();
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
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();

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
                .addContainerGap(546, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(jLabel30)
                .addContainerGap(527, Short.MAX_VALUE))
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

        ReadBT.setText("READ");
        ReadBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReadBTActionPerformed(evt);
            }
        });
        jPanel2.add(ReadBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 92, 44));

        UpdateBT.setText("UPDATE");
        UpdateBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBTActionPerformed(evt);
            }
        });
        jPanel2.add(UpdateBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 92, 44));

        DeleteBT.setText("DELETE");
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
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 1100, 290));

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
                .addContainerGap(250, Short.MAX_VALUE))
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

        jLabel25.setText("Meter Reading & Calculation");

        jLabel26.setText("Rate Structure");

        jLabel27.setText("Usage Alerts");

        jLabel28.setText("Data Validation & Error Handling");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField5)
                    .addComponent(jTextField6)
                    .addComponent(jTextField7)
                    .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Usage Information", jPanel4);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setText("Total Amount:");

        jLabel2.setText("Due Date:");

        ReadingDateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReadingDateTextFieldActionPerformed(evt);
            }
        });

        AccountNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccountNameTextFieldActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(228, 228, 228)
                                .addComponent(FinishPaymentbtn)))
                        .addContainerGap(624, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
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
                                    .addComponent(AccountNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(ReadingDateTextField))))
                        .addGap(643, 643, 643))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(ReadingDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(AccountNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(270, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Billing Details", jPanel1);

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
                .addContainerGap(430, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("", jPanel10);

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane1.addTab("Billing Management", jPanel5);

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

    private void ReadBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReadBTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ReadBTActionPerformed

    private void UpdateBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBTActionPerformed
        // TODO add your handling code here:
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

    // Parse dates from the input fields
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

    private void CurrentConsumptionTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurrentConsumptionTextFieldActionPerformed
        // TODO add your handling code here:
        PreviousConsumptionTextField.requestFocus();
    }//GEN-LAST:event_CurrentConsumptionTextFieldActionPerformed

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
    }//GEN-LAST:event_FinishPaymentbtnActionPerformed

    private void ReadingDateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReadingDateTextFieldActionPerformed
        // TODO add your handling code here:
        AccountNameTextField.requestFocus();
    }//GEN-LAST:event_ReadingDateTextFieldActionPerformed

    private void AccountNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccountNameTextFieldActionPerformed
        // TODO add your handling code here:
        CurrentConsumptionTextField.requestFocus();
    }//GEN-LAST:event_AccountNameTextFieldActionPerformed

    private void PreviousConsumptionTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviousConsumptionTextFieldActionPerformed
        // TODO add your handling code here:
        CurrentChargeTextField.requestFocus();
    }//GEN-LAST:event_PreviousConsumptionTextFieldActionPerformed

    private void CurrentChargeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurrentChargeTextFieldActionPerformed
        // TODO add your handling code here:
       TotalAmountTextField.requestFocus();
    }//GEN-LAST:event_CurrentChargeTextFieldActionPerformed

    private void TotalAmountTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalAmountTextFieldActionPerformed
        // TODO add your handling code here:
        DueDateTextField.requestFocus();
    }//GEN-LAST:event_TotalAmountTextFieldActionPerformed

    private void DueDateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DueDateTextFieldActionPerformed
        // TODO add your handling code here:
        StatusTextField.requestFocus();
    }//GEN-LAST:event_DueDateTextFieldActionPerformed

    private void ReadingDateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReadingDateTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ReadingDateTFActionPerformed

    private void BillingPeriodEndTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BillingPeriodEndTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BillingPeriodEndTFActionPerformed

    private void Contact_NumberTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Contact_NumberTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Contact_NumberTFActionPerformed

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
    private javax.swing.JTextField AccountNameTextField;
    private javax.swing.JTextField AccountNoTF;
    private javax.swing.JComboBox<String> AccountStatusCB;
    private javax.swing.JTextField ApprovedByTF;
    private javax.swing.JTextField BillingPeriodEndTF;
    private javax.swing.JTextField BillingPeriodStartTF;
    private javax.swing.JTextField ClientTF;
    private javax.swing.JTextField Contact_NumberTF;
    private javax.swing.JButton CreateBT;
    private javax.swing.JTextField CurrentChargeTF;
    private javax.swing.JTextField CurrentChargeTextField;
    private javax.swing.JTextField CurrentConsumptionTF;
    private javax.swing.JTextField CurrentConsumptionTextField;
    private javax.swing.JTable CustomerInfoTable;
    private javax.swing.JButton DeleteBT;
    private javax.swing.JTextField DueDateTF;
    private javax.swing.JTextField DueDateTextField;
    private javax.swing.JTextField FinalApprovalTF;
    private javax.swing.JButton FinishPaymentbtn;
    private javax.swing.JComboBox<String> MeterCB;
    private javax.swing.JTextField PreviousConsumptionTF;
    private javax.swing.JTextField PreviousConsumptionTextField;
    private javax.swing.JComboBox<String> PropertyCB;
    private javax.swing.JButton ReadBT;
    private javax.swing.JTextField ReadingDateTF;
    private javax.swing.JTextField ReadingDateTextField;
    private javax.swing.JTextArea ServiceAddressTF;
    private javax.swing.JTextField StatusTF;
    private javax.swing.JTextField StatusTextField;
    private javax.swing.JTextField TotalAmountDueTF;
    private javax.swing.JTextField TotalAmountTF;
    private javax.swing.JTextField TotalAmountTextField;
    private javax.swing.JButton UpdateBT;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
