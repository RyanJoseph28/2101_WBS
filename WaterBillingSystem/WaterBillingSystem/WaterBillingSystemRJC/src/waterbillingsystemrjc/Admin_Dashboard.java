package waterbillingsystemrjc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.Vector;





public class Admin_Dashboard extends javax.swing.JFrame {

    public Admin_Dashboard() {
        initComponents();
        Connect();
        fetchClientInfo();
        calculateBaseCharge();
        

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
    
   private int generateNextMeterNo(Connection con) throws SQLException {
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT MAX(Meter_No) FROM clientinfo");
    if (rs.next()) {
        String maxMeterNo = rs.getString(1); 
        if (maxMeterNo != null) {
            String[] parts = maxMeterNo.split("-");
            return Integer.parseInt(parts[1]) + 1; 
        }
    }
    return 1; 
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
            v2.add(rs.getString("Contact_Number"));  
            v2.add(rs.getString("Property"));     
            v2.add(rs.getString("Meter_No"));      
            v2.add(rs.getString("Account_Status")); 

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
   private void syncAccountNumbers(String accountNo, String accountName, String meterNo) {
    
    SIAccountNoTF.setText(accountNo);  
    MRAccountNoTF.setText(accountNo); 
    BDAccountNoTF.setText(accountNo); 
    BSAccountNoTF.setText(accountNo);  

   
    SIAccountNameTF.setText(accountName); 
    MRAccountNameTF.setText(accountName); 
    BDAccountNameTF.setText(accountName);  
    BSAccountNameTF.setText(accountName); 

    SIMeterNoTF.setText(meterNo); 
    MRMeterNoTF.setText(meterNo); 

    
    SIAccountNoTF.setEditable(false);  
    MRAccountNoTF.setEditable(false); 
    BDAccountNoTF.setEditable(false); 
    BSAccountNoTF.setEditable(false);  

    
    SIAccountNameTF.setEditable(true);  
    MRAccountNameTF.setEditable(false);
    BDAccountNameTF.setEditable(false); 
    BSAccountNameTF.setEditable(false); 

    
    SIMeterNoTF.setEditable(false);  
    MRMeterNoTF.setEditable(false);  

    
    BaseChargeTF.setEditable(false);  
    BDRateTF.setEditable(true);       
    BDConsumptionTF.setEditable(true); 
}
   private void calculateBaseCharge() {
    try {
        
        if (BDConsumptionTF.getText().trim().isEmpty() || BDRateTF.getText().trim().isEmpty()) {
            return; 
        }

      
        BigDecimal consumption = new BigDecimal(BDConsumptionTF.getText().trim());
        BigDecimal rate = new BigDecimal(BDRateTF.getText().trim());

      
        BigDecimal baseCharge = consumption.multiply(rate);

        
        BaseChargeTF.setText(baseCharge.setScale(2, RoundingMode.HALF_UP).toString());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numeric values.", 
                                      "Error", JOptionPane.ERROR_MESSAGE);
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
        SIAccountNoTF = new javax.swing.JTextField();
        CreateBT = new javax.swing.JButton();
        SIAccountNameTF = new javax.swing.JTextField();
        SearchBT = new javax.swing.JButton();
        UpdateBT = new javax.swing.JButton();
        DeleteBT = new javax.swing.JButton();
        PropertyCB = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        ServiceAddressTF = new javax.swing.JTextArea();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        AccountStatusCB = new javax.swing.JComboBox<>();
        SIMeterNoTF = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        CustomerInfoTable = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        ContactNumberTF = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        MRAccountNoTF = new javax.swing.JTextField();
        MRRateTF = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        MRAccountNameTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        MRMeterNoTF = new javax.swing.JTextField();
        MRConsumptionTF = new javax.swing.JTextField();
        MRPreviousReadingTF = new javax.swing.JTextField();
        MRCurrentReadingTF = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        BDAccountNoTF = new javax.swing.JTextField();
        BDAccountNameTF = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        BDPreviousReadingTf = new javax.swing.JTextField();
        BDCurrentReadingTF = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        BDConsumptionTF = new javax.swing.JTextField();
        BDRateTF = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        BaseChargeTF = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        TotalChargeTF = new javax.swing.JTextField();
        DueDateTF = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        TaxAmountTF = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        BSAccountNoTF = new javax.swing.JTextField();
        BSAccountNameTF = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        BillingPeriodStartTF = new javax.swing.JTextField();
        BillingPeriodEndTF = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        BSAmountDue = new javax.swing.JTextField();

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
                .addContainerGap(585, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(jLabel30)
                .addContainerGap(515, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("", jPanel8);

        jPanel2.setBackground(new java.awt.Color(153, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel3.setText("Account No. :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, 27));

        jLabel4.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel4.setText("Account Name :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));

        jLabel9.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel9.setText("Service Address :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        jLabel10.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel10.setText("Property:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, -1, -1));

        SIAccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SIAccountNoTFActionPerformed(evt);
            }
        });
        jPanel2.add(SIAccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 312, 40));

        CreateBT.setText("CREATE");
        CreateBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateBTActionPerformed(evt);
            }
        });
        jPanel2.add(CreateBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 310, 87, 44));

        SIAccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SIAccountNameTFActionPerformed(evt);
            }
        });
        jPanel2.add(SIAccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 312, 40));

        SearchBT.setText("SEARCH");
        SearchBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBTActionPerformed(evt);
            }
        });
        jPanel2.add(SearchBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 310, 92, 44));

        UpdateBT.setText("UPDATE");
        UpdateBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBTActionPerformed(evt);
            }
        });
        jPanel2.add(UpdateBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 310, 92, 44));

        DeleteBT.setText("DELETE");
        DeleteBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBTActionPerformed(evt);
            }
        });
        jPanel2.add(DeleteBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 310, 92, 44));

        PropertyCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Residential", "Commercial", "Industrial", "Institutional" }));
        PropertyCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PropertyCBActionPerformed(evt);
            }
        });
        jPanel2.add(PropertyCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 70, 200, 40));

        ServiceAddressTF.setColumns(20);
        ServiceAddressTF.setRows(5);
        jScrollPane2.setViewportView(ServiceAddressTF);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 312, 40));

        jLabel32.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel32.setText("Meter No. :");
        jPanel2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 140, -1, -1));

        jLabel33.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel33.setText("Account Status :");
        jPanel2.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 200, -1, -1));

        AccountStatusCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));
        AccountStatusCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccountStatusCBActionPerformed(evt);
            }
        });
        jPanel2.add(AccountStatusCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 190, 200, 40));

        SIMeterNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SIMeterNoTFActionPerformed(evt);
            }
        });
        jPanel2.add(SIMeterNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, 200, 40));

        CustomerInfoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Account Number", "Account Name", "Service Address", "Contact Number", "Property", "Meter No", "Account Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        jLabel34.setText("Contact Number :");
        jPanel2.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 130, -1));

        ContactNumberTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContactNumberTFActionPerformed(evt);
            }
        });
        jPanel2.add(ContactNumberTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 310, 40));

        jTabbedPane1.addTab("SERVICE INFORMATION", jPanel2);

        jPanel7.setBackground(new java.awt.Color(153, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel5.setText("Account No. :");
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, 27));

        MRAccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRAccountNoTFActionPerformed(evt);
            }
        });
        jPanel7.add(MRAccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 312, 40));

        MRRateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRRateTFActionPerformed(evt);
            }
        });
        jPanel7.add(MRRateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 440, 310, 40));

        jLabel36.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel36.setText("Meter No. :");
        jPanel7.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        MRAccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRAccountNameTFActionPerformed(evt);
            }
        });
        jPanel7.add(MRAccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 312, 40));

        jLabel6.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel6.setText("Account Name :");
        jPanel7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jLabel1.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel1.setText("Consumption :");
        jPanel7.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, -1, -1));

        jLabel2.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel2.setText("Previous Reading :");
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        jLabel7.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel7.setText("Current Reading :");
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, -1, -1));

        jLabel8.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel8.setText("Rate :");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, -1, -1));

        MRMeterNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRMeterNoTFActionPerformed(evt);
            }
        });
        jPanel7.add(MRMeterNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 310, 40));

        MRConsumptionTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRConsumptionTFActionPerformed(evt);
            }
        });
        jPanel7.add(MRConsumptionTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 310, 40));

        MRPreviousReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRPreviousReadingTFActionPerformed(evt);
            }
        });
        jPanel7.add(MRPreviousReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 310, 40));

        MRCurrentReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRCurrentReadingTFActionPerformed(evt);
            }
        });
        jPanel7.add(MRCurrentReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 310, 40));

        jTabbedPane1.addTab("METER READING", jPanel7);

        jPanel5.setBackground(new java.awt.Color(153, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel39.setText("Account No. :");
        jPanel5.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, 27));

        BDAccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDAccountNoTFActionPerformed(evt);
            }
        });
        jPanel5.add(BDAccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 312, 30));

        BDAccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDAccountNameTFActionPerformed(evt);
            }
        });
        jPanel5.add(BDAccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 312, 30));

        jLabel40.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel40.setText("Account Name :");
        jPanel5.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

        jLabel41.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel41.setText("Previous Reading :");
        jPanel5.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, 27));

        BDPreviousReadingTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDPreviousReadingTfActionPerformed(evt);
            }
        });
        jPanel5.add(BDPreviousReadingTf, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 312, 30));

        BDCurrentReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDCurrentReadingTFActionPerformed(evt);
            }
        });
        jPanel5.add(BDCurrentReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 312, 30));

        jLabel42.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel42.setText("Current Reading :");
        jPanel5.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        jLabel43.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel43.setText("Consumption :");
        jPanel5.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, 27));

        BDConsumptionTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDConsumptionTFActionPerformed(evt);
            }
        });
        jPanel5.add(BDConsumptionTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 312, 30));

        BDRateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDRateTFActionPerformed(evt);
            }
        });
        jPanel5.add(BDRateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 312, 30));

        jLabel44.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel44.setText("Rate :");
        jPanel5.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        jLabel45.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel45.setText("Base Charge :");
        jPanel5.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, -1, 27));

        BaseChargeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BaseChargeTFActionPerformed(evt);
            }
        });
        jPanel5.add(BaseChargeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 312, 30));

        jLabel47.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel47.setText("Total Charge :");
        jPanel5.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, -1, 27));

        TotalChargeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalChargeTFActionPerformed(evt);
            }
        });
        jPanel5.add(TotalChargeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 312, 30));

        DueDateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DueDateTFActionPerformed(evt);
            }
        });
        jPanel5.add(DueDateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, 312, 30));

        jLabel50.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel50.setText("Due Date:");
        jPanel5.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, -1, -1));

        TaxAmountTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TaxAmountTFActionPerformed(evt);
            }
        });
        jPanel5.add(TaxAmountTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 312, 30));

        jLabel56.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel56.setText("Tax :");
        jPanel5.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        jTabbedPane1.addTab("BILLING DETAILS", jPanel5);

        jPanel6.setBackground(new java.awt.Color(153, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel51.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel51.setText("Account No. :");
        jPanel6.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, 27));

        BSAccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSAccountNoTFActionPerformed(evt);
            }
        });
        jPanel6.add(BSAccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 312, 30));

        BSAccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSAccountNameTFActionPerformed(evt);
            }
        });
        jPanel6.add(BSAccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 312, 30));

        jLabel52.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel52.setText("Account Name :");
        jPanel6.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

        jLabel53.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel53.setText("Billing Period Start :");
        jPanel6.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, 27));

        BillingPeriodStartTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BillingPeriodStartTFActionPerformed(evt);
            }
        });
        jPanel6.add(BillingPeriodStartTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 312, 30));

        BillingPeriodEndTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BillingPeriodEndTFActionPerformed(evt);
            }
        });
        jPanel6.add(BillingPeriodEndTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 312, 30));

        jLabel54.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel54.setText("Billing Period End :");
        jPanel6.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        jLabel55.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel55.setText("Total Amount Due :");
        jPanel6.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, 27));

        BSAmountDue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSAmountDueActionPerformed(evt);
            }
        });
        jPanel6.add(BSAmountDue, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 312, 30));

        jTabbedPane1.addTab("BILLING SUMMARY", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ContactNumberTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContactNumberTFActionPerformed

    }//GEN-LAST:event_ContactNumberTFActionPerformed

    private void SIMeterNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SIMeterNoTFActionPerformed
        // TODO add your handling code here:
    String accountNo = SIAccountNoTF.getText().trim();
    String accountName = SIAccountNameTF.getText().trim();
    String meterNo = SIMeterNoTF.getText().trim();

    if (!accountNo.isEmpty() && !accountName.isEmpty() && !meterNo.isEmpty()) {
        syncAccountNumbers(accountNo, accountName, meterNo);  
    }
    }//GEN-LAST:event_SIMeterNoTFActionPerformed

    private void DeleteBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBTActionPerformed
        // TODO add your handling code here:
         try {
        
        String accountNo = SIAccountNoTF.getText().trim(); 

      
        if (accountNo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Account No.", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }

       
        int confirmation = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete the client with Account No: " + accountNo + "?", 
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

           
            SIAccountNoTF.setText("");  
            SIAccountNameTF.setText(""); 
            ServiceAddressTF.setText(""); 
            ContactNumberTF.setText("");  
            PropertyCB.setSelectedIndex(-1);  
            SIMeterNoTF.setText(""); 
            AccountStatusCB.setSelectedIndex(-1);  

        } else {
            JOptionPane.showMessageDialog(this, "No record found with Account No: " + accountNo, 
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException ex) {
        Logger.getLogger(Admin_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), 
                                      "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_DeleteBTActionPerformed

    private void UpdateBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBTActionPerformed
        // TODO add your handling code here:
        String accountNo = SIAccountNoTF.getText().trim();

    if (accountNo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter an Account Number.");
        return;  // Exit if Account No is empty
    }

    try {
        
        pst = con.prepareStatement(
            "UPDATE clientinfo SET Account_Name=?, Service_Address=?, Contact_Number=?, Property=?, " +
            "Account_Status=? WHERE Account_No=?"
        );

        
        pst.setString(1, SIAccountNameTF.getText());  
        pst.setString(2, ServiceAddressTF.getText()); 
        pst.setInt(3, Integer.parseInt(ContactNumberTF.getText()));  
        pst.setString(4, (String) PropertyCB.getSelectedItem()); 
        pst.setString(5, (String) AccountStatusCB.getSelectedItem()); 
        pst.setString(6, accountNo);  

       
        int rowsUpdated = pst.executeUpdate();

        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Client details updated successfully!");

           
            DefaultTableModel model = (DefaultTableModel) CustomerInfoTable.getModel();
            int rowIndex = getRowIndexByAccountNo(accountNo); 

            if (rowIndex >= 0) {
               
                model.setValueAt(SIAccountNameTF.getText(), rowIndex, 1);  
                model.setValueAt(ServiceAddressTF.getText(), rowIndex, 2);  
                model.setValueAt(ContactNumberTF.getText(), rowIndex, 3);  
                model.setValueAt(PropertyCB.getSelectedItem(), rowIndex, 4);  
                model.setValueAt(AccountStatusCB.getSelectedItem(), rowIndex, 6);  
            }

        } else {
            JOptionPane.showMessageDialog(this, "Failed to update client details!");
        }

    } catch (SQLException | NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Error updating client details: " + ex.getMessage());
    }
}

    private int getRowIndexByAccountNo(String accountNo) {
    DefaultTableModel model = (DefaultTableModel) CustomerInfoTable.getModel();
    int rowCount = model.getRowCount();
    for (int i = 0; i < rowCount; i++) {
        if (model.getValueAt(i, 0).equals(accountNo)) {  
            return i;
        }
    }
    return -1;    
    }//GEN-LAST:event_UpdateBTActionPerformed

    private void SearchBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBTActionPerformed
        // TODO add your handling code here:
        String accountNo = SIAccountNoTF.getText().trim();  // Replace with your actual Account No field name


    if (accountNo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter an Account Number.");
        return; 
    }

    try {
        
        pst = con.prepareStatement("SELECT * FROM clientinfo WHERE Account_No = ?");
        pst.setString(1, accountNo);  // Set the Account_No parameter in the query
     
        rs = pst.executeQuery();
        
        if (rs.next()) {
           
            SIAccountNameTF.setText(rs.getString("Account_Name"));  
            ServiceAddressTF.setText(rs.getString("Service_Address")); 
            ContactNumberTF.setText(String.valueOf(rs.getInt("Contact_Number"))); 
            PropertyCB.setSelectedItem(rs.getString("Property"));  
            SIMeterNoTF.setText(rs.getString("Meter_No"));  
            AccountStatusCB.setSelectedItem(rs.getString("Account_Status"));  
        } else {
           
            JOptionPane.showMessageDialog(this, "No customer found with Account Number: " + accountNo);
        }

    } catch (SQLException ex) {
       
        Logger.getLogger(Admin_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Error during search: " + ex.getMessage());
    }

    }//GEN-LAST:event_SearchBTActionPerformed

    private void SIAccountNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SIAccountNameTFActionPerformed

    }//GEN-LAST:event_SIAccountNameTFActionPerformed

    private void CreateBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateBTActionPerformed
 

       try {
    String accountName = SIAccountNameTF.getText().trim();
    String serviceAddress = ServiceAddressTF.getText().trim();

  
    if (accountName.isEmpty() || serviceAddress.isEmpty() || ContactNumberTF.getText().trim().isEmpty() ||
        PropertyCB.getSelectedIndex() == -1 || AccountStatusCB.getSelectedIndex() == -1) {
        JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Missing Data", JOptionPane.WARNING_MESSAGE);
        return; 
    }

    int contactNumber;  
    try {
        contactNumber = Integer.parseInt(ContactNumberTF.getText().trim());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid contact number. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String selectedProperty = (String) PropertyCB.getSelectedItem();
    String selectedAccountStatus = (String) AccountStatusCB.getSelectedItem();

    String meterPrefix = "100-";
    int nextMeterNo = generateNextMeterNo(con); 
    String meterNo = meterPrefix + String.format("%03d", nextMeterNo); 
    SIMeterNoTF.setText(meterNo); 

    String newAccountNo = generateNextAccountNo(con);

    pst = con.prepareStatement(
        "INSERT INTO clientinfo (Account_No, Account_Name, Service_Address, Contact_Number, Property, Meter_No, Account_Status) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?)"
    );

    pst.setString(1, newAccountNo);
    pst.setString(2, accountName);
    pst.setString(3, serviceAddress);
    pst.setInt(4, contactNumber);
    pst.setString(5, selectedProperty);
    pst.setString(6, meterNo);
    pst.setString(7, selectedAccountStatus);

    int rowsInserted = pst.executeUpdate();

    if (rowsInserted > 0) {

        DefaultTableModel model = (DefaultTableModel) CustomerInfoTable.getModel();
        model.addRow(new Object[]{
            newAccountNo, accountName, serviceAddress, contactNumber,
            selectedProperty, meterNo, selectedAccountStatus
        });

        SIAccountNameTF.setText("");
        ServiceAddressTF.setText("");
        ContactNumberTF.setText("");
        PropertyCB.setSelectedIndex(-1);
        AccountStatusCB.setSelectedIndex(-1);
        SIMeterNoTF.setText("");
        SIAccountNameTF.requestFocus();

        JOptionPane.showMessageDialog(this, "Client Info Added Successfully! Account No: " + newAccountNo + ", Meter No: " + meterNo);
    } else {
        JOptionPane.showMessageDialog(this, "Record Failed to Save!", "Error", JOptionPane.ERROR_MESSAGE);
    }

} catch (SQLException ex) {
    Logger.getLogger(Admin_Dashboard.class.getName()).log(Level.SEVERE, null, ex);
    JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Invalid input for numeric fields: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}


    }//GEN-LAST:event_CreateBTActionPerformed

    private void SIAccountNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SIAccountNoTFActionPerformed
        // TODO add your handling code here:
    String accountNo = SIAccountNoTF.getText().trim();
    String accountName = SIAccountNameTF.getText().trim();
    String meterNo = SIMeterNoTF.getText().trim();

    if (!accountNo.isEmpty() && !accountName.isEmpty() && !meterNo.isEmpty()) {
        syncAccountNumbers(accountNo, accountName, meterNo); 
    }

    }//GEN-LAST:event_SIAccountNoTFActionPerformed

    private void MRAccountNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRAccountNoTFActionPerformed
        // TODO add your handling code here:
    String accountNo = MRAccountNoTF.getText().trim();
    String accountName = MRAccountNameTF.getText().trim();
    String meterNo = MRMeterNoTF.getText().trim();

    if (!accountNo.isEmpty() && !accountName.isEmpty() && !meterNo.isEmpty()) {
        syncAccountNumbers(accountNo, accountName, meterNo); 
    }
        
    }//GEN-LAST:event_MRAccountNoTFActionPerformed

    private void MRRateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRRateTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MRRateTFActionPerformed

    private void MRAccountNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRAccountNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MRAccountNameTFActionPerformed

    private void MRMeterNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRMeterNoTFActionPerformed
        // TODO add your handling code here:
    String accountNo = MRAccountNoTF.getText().trim();
    String accountName = MRAccountNameTF.getText().trim();
    String meterNo = MRMeterNoTF.getText().trim();

    if (!accountNo.isEmpty() && !accountName.isEmpty() && !meterNo.isEmpty()) {
        syncAccountNumbers(accountNo, accountName, meterNo); 
    }
    
    }//GEN-LAST:event_MRMeterNoTFActionPerformed

    private void MRConsumptionTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRConsumptionTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MRConsumptionTFActionPerformed

    private void MRPreviousReadingTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRPreviousReadingTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MRPreviousReadingTFActionPerformed

    private void MRCurrentReadingTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRCurrentReadingTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MRCurrentReadingTFActionPerformed

    private void BDAccountNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDAccountNoTFActionPerformed
        // TODO add your handling code here:
        String accountNo = BDAccountNoTF.getText().trim();
        String accountName = BDAccountNameTF.getText().trim();

    if (!accountNo.isEmpty() && !accountName.isEmpty()) {
        syncAccountNumbers(accountNo, accountName, ""); 
    }
    }//GEN-LAST:event_BDAccountNoTFActionPerformed

    private void BDAccountNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDAccountNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BDAccountNameTFActionPerformed

    private void BDPreviousReadingTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDPreviousReadingTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BDPreviousReadingTfActionPerformed

    private void BDCurrentReadingTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDCurrentReadingTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BDCurrentReadingTFActionPerformed

    private void BDConsumptionTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDConsumptionTFActionPerformed
        // TODO add your handling code here:
        calculateBaseCharge(); 
    }//GEN-LAST:event_BDConsumptionTFActionPerformed

    private void BDRateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDRateTFActionPerformed
        // TODO add your handling code here:
        calculateBaseCharge();
    }//GEN-LAST:event_BDRateTFActionPerformed

    private void BaseChargeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BaseChargeTFActionPerformed
        // TODO add your handling code here:
         try {
        
        BigDecimal consumption = new BigDecimal(BDConsumptionTF.getText().trim());
        BigDecimal rate = new BigDecimal(BDRateTF.getText().trim());

        
        BigDecimal baseCharge = consumption.multiply(rate);

        
        BaseChargeTF.setText(baseCharge.setScale(2, RoundingMode.HALF_UP).toString());
    } catch (NumberFormatException | ArithmeticException e) {
      
        JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numeric values for consumption and rate.", 
                                      "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_BaseChargeTFActionPerformed

    private void TotalChargeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalChargeTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalChargeTFActionPerformed

    private void DueDateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DueDateTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DueDateTFActionPerformed

    private void BSAccountNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSAccountNoTFActionPerformed
        // TODO add your handling code here:
        String accountNo = BSAccountNoTF.getText().trim();
        String accountName = BSAccountNameTF.getText().trim();

    if (!accountNo.isEmpty() && !accountName.isEmpty()) {
        syncAccountNumbers(accountNo, accountName, ""); 
    }
    }//GEN-LAST:event_BSAccountNoTFActionPerformed

    private void BSAccountNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSAccountNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BSAccountNameTFActionPerformed

    private void BillingPeriodStartTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BillingPeriodStartTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BillingPeriodStartTFActionPerformed

    private void BillingPeriodEndTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BillingPeriodEndTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BillingPeriodEndTFActionPerformed

    private void BSAmountDueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSAmountDueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BSAmountDueActionPerformed

    private void PropertyCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PropertyCBActionPerformed
        // TODO add your handling code here:
        String selectedProperty = (String) PropertyCB.getSelectedItem();
    double taxAmount = 0.0;

    // Set the tax amount based on the selected property
    switch (selectedProperty) {
        case "Residential":
            taxAmount = 30.00;  // Tax for Residential
            break;
        case "Commercial":
            taxAmount = 100.00; // Tax for Commercial
            break;
        case "Industrial":
            taxAmount = 300.00; // Tax for Industrial
            break;
        case "Institutional":
            taxAmount = 50.00;  // Tax for Institutional
            break;
        default:
            taxAmount = 0.00;  // Default tax if no valid property is selected
            break;
    }

    // Assuming you have a label or text field to display the tax
    // Set the tax value to a text field or label
    TaxAmountTF.setText(String.format("₱%.2f", taxAmount));
    }//GEN-LAST:event_PropertyCBActionPerformed

    private void TaxAmountTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TaxAmountTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TaxAmountTFActionPerformed

    private void AccountStatusCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccountStatusCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AccountStatusCBActionPerformed

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
    private javax.swing.JComboBox<String> AccountStatusCB;
    private javax.swing.JTextField BDAccountNameTF;
    private javax.swing.JTextField BDAccountNoTF;
    private javax.swing.JTextField BDConsumptionTF;
    private javax.swing.JTextField BDCurrentReadingTF;
    private javax.swing.JTextField BDPreviousReadingTf;
    private javax.swing.JTextField BDRateTF;
    private javax.swing.JTextField BSAccountNameTF;
    private javax.swing.JTextField BSAccountNoTF;
    private javax.swing.JTextField BSAmountDue;
    private javax.swing.JTextField BaseChargeTF;
    private javax.swing.JTextField BillingPeriodEndTF;
    private javax.swing.JTextField BillingPeriodStartTF;
    private javax.swing.JTextField ContactNumberTF;
    private javax.swing.JButton CreateBT;
    private javax.swing.JTable CustomerInfoTable;
    private javax.swing.JButton DeleteBT;
    private javax.swing.JTextField DueDateTF;
    private javax.swing.JTextField MRAccountNameTF;
    private javax.swing.JTextField MRAccountNoTF;
    private javax.swing.JTextField MRConsumptionTF;
    private javax.swing.JTextField MRCurrentReadingTF;
    private javax.swing.JTextField MRMeterNoTF;
    private javax.swing.JTextField MRPreviousReadingTF;
    private javax.swing.JTextField MRRateTF;
    private javax.swing.JComboBox<String> PropertyCB;
    private javax.swing.JTextField SIAccountNameTF;
    private javax.swing.JTextField SIAccountNoTF;
    private javax.swing.JTextField SIMeterNoTF;
    private javax.swing.JButton SearchBT;
    private javax.swing.JTextArea ServiceAddressTF;
    private javax.swing.JTextField TaxAmountTF;
    private javax.swing.JTextField TotalChargeTF;
    private javax.swing.JButton UpdateBT;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
