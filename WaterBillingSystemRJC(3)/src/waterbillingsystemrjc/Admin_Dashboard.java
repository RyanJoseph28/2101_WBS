package waterbillingsystemrjc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Vector;
import java.sql.PreparedStatement;

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
   
   public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/java_user_database";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
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
   
   private java.sql.Date convertToSQLDate(java.util.Date utilDate) {
        if (utilDate == null) {
            return null; 
        }
        return new java.sql.Date(utilDate.getTime()); 
    }

   
    private void syncAccountInfo(String accountNo, String accountName, String serviceAddress, String property, String meterNo) {
  
        SIAccountNoTF.setText(accountNo);
        MRAccountNoTF.setText(accountNo);
        BDAccountNoTF.setText(accountNo);
        BSAccountNoTF.setText(accountNo);
        GRAccountNoTF.setText(accountNo);

        SIAccountNameTF.setText(accountName);
        MRAccountNameTF.setText(accountName);
        BDAccountNameTF.setText(accountName);
        BSAccountNameTF.setText(accountName);
        GRAccountNameTF.setText(accountName);

        SIServiceAddressTF.setText(serviceAddress);
        GRServiceAddressTF.setText(serviceAddress); 

        SIPropertyCB.setSelectedItem(property);
        GRPropertyTF.setText(property);

        SIMeterNoTF.setText(meterNo);
        MRMeterNoTF.setText(meterNo);
        GRMeterNoTF.setText(meterNo); 

        SIAccountNoTF.setEditable(true);
        MRAccountNoTF.setEditable(false);
        BDAccountNoTF.setEditable(false);
        BSAccountNoTF.setEditable(false);
        GRAccountNoTF.setEditable(false);

        SIAccountNameTF.setEditable(true);
        MRAccountNameTF.setEditable(false);
        BDAccountNameTF.setEditable(false);
        BSAccountNameTF.setEditable(false);
        GRAccountNameTF.setEditable(false);

        SIMeterNoTF.setEditable(true);
        MRMeterNoTF.setEditable(false);
        GRMeterNoTF.setEditable(false);

        SIServiceAddressTF.setEditable(false);
        GRServiceAddressTF.setEditable(false);

        GRPropertyTF.setEditable(false);
    }

    private void syncAccountNumber(String meterNo, String previousReading, String currentReading, 
                                String consumption, String rate, 
                                String taxAmount, String baseCharge, String totalCharge, 
                                String billingPeriodStart, String billingPeriodEnd, 
                                String totalAmountDue) {
        
    SIMeterNoTF.setText(meterNo);
    MRMeterNoTF.setText(meterNo);

    MRPreviousReadingTF.setText(previousReading);
    BDPreviousReadingTF.setText(previousReading);
    GRPreviousReadingTF.setText(previousReading);

    MRCurrentReadingTF.setText(currentReading);
    BDCurrentReadingTF.setText(currentReading);
    GRCurrentReadingTF.setText(currentReading);

    MRConsumptionTF.setText(consumption);
    BDConsumptionTF.setText(consumption);
    GRConsumptionTF.setText(consumption);

    MRRateTF.setText(rate);
    BDRateTF.setText(rate);
    GRRateTF.setText(rate);

    BDTaxAmountTF.setText(taxAmount);
    GRTaxAmountTF.setText(taxAmount);

    BDBaseChargeTF.setText(baseCharge);
    GRBaseChargeTF.setText(baseCharge);

    BDPenaltyChargeTF.setText(totalCharge);
    GRPenaltyChargeTF.setText(totalCharge);

    BSBillingPeriodStartTF.setText(billingPeriodStart);
    GRBillingPeriodStartTF.setText(billingPeriodStart);

    BSBillingPeriodEndTF.setText(billingPeriodEnd);
    GRBillingPeriodEndTF.setText(billingPeriodEnd);

    BSTotalAmountDueTF.setText(totalAmountDue);
    GRTotalAmountDueTF.setText(totalAmountDue);
    
    MRPreviousReadingTF.setEditable(false);
    BDPreviousReadingTF.setEditable(false);
    GRPreviousReadingTF.setEditable(false);

    MRCurrentReadingTF.setEditable(false);
    BDCurrentReadingTF.setEditable(false);
    GRCurrentReadingTF.setEditable(false);

    MRConsumptionTF.setEditable(false);
    BDConsumptionTF.setEditable(false);
    GRConsumptionTF.setEditable(false);

    MRRateTF.setEditable(false);
    BDRateTF.setEditable(false);
    GRRateTF.setEditable(false);

    BDTaxAmountTF.setEditable(false);
    GRTaxAmountTF.setEditable(false);

    BDBaseChargeTF.setEditable(false);
    GRBaseChargeTF.setEditable(false);

    BDPenaltyChargeTF.setEditable(false);
    GRPenaltyChargeTF.setEditable(false);

    BSBillingPeriodStartTF.setEditable(false);
    GRBillingPeriodStartTF.setEditable(false);

    BSBillingPeriodEndTF.setEditable(false);
    GRBillingPeriodEndTF.setEditable(false);

    BSTotalAmountDueTF.setEditable(false);
    GRTotalAmountDueTF.setEditable(false);

    syncPropertyData();
}

    private void syncPropertyData() {
        String selectedProperty = SIPropertyCB.getSelectedItem().toString();
        GRPropertyTF.setText(selectedProperty);
    }
   
   private void syncMRDataToOtherTabs() {
        String meterNo = MRMeterNoTF.getText().trim();
        String previousReading = MRPreviousReadingTF.getText().trim();
        String currentReading = MRCurrentReadingTF.getText().trim();
        String consumption = MRConsumptionTF.getText().trim();
        String rate = MRRateTF.getText().trim();

        BDPreviousReadingTF.setText(previousReading);
        BDCurrentReadingTF.setText(currentReading);
        BDConsumptionTF.setText(consumption);
        BDRateTF.setText(rate);

        GRPreviousReadingTF.setText(previousReading);
        GRCurrentReadingTF.setText(currentReading);
        GRConsumptionTF.setText(consumption);
        GRRateTF.setText(rate);
    }

   private void syncBDDataToBSandGR() {
        String previousReading = BDPreviousReadingTF.getText().trim();
        String currentReading = BDCurrentReadingTF.getText().trim();
        String consumption = BDConsumptionTF.getText().trim();
        String rate = BDRateTF.getText().trim();
        String taxAmount = BDTaxAmountTF.getText().trim();
        String baseCharge = BDBaseChargeTF.getText().trim();
        String totalCharge = BDPenaltyChargeTF.getText().trim();
        String billingPeriodStart = BSBillingPeriodStartTF.getText().trim();
        String billingPeriodEnd = BSBillingPeriodEndTF.getText().trim();
        String totalAmountDue = BSTotalAmountDueTF.getText().trim();
    
        GRPreviousReadingTF.setText(previousReading);
        GRCurrentReadingTF.setText(currentReading);
        GRConsumptionTF.setText(consumption);
        GRRateTF.setText(rate);

        GRTaxAmountTF.setText(taxAmount);

        BDBaseChargeTF.setText(baseCharge);
        GRBaseChargeTF.setText(baseCharge);

        BDPenaltyChargeTF.setText(totalCharge);
        GRPenaltyChargeTF.setText(totalCharge);

        BSBillingPeriodStartTF.setText(billingPeriodStart);
        GRBillingPeriodStartTF.setText(billingPeriodStart);

        BSBillingPeriodEndTF.setText(billingPeriodEnd);
        GRBillingPeriodEndTF.setText(billingPeriodEnd);

        BSTotalAmountDueTF.setText(totalAmountDue);
        GRTotalAmountDueTF.setText(totalAmountDue);
    }

   private void syncBSDataToGR() {
        String baseCharge = BDBaseChargeTF.getText().trim();
        String totalCharge = BDPenaltyChargeTF.getText().trim();
        String billingPeriodStart = BSBillingPeriodStartTF.getText().trim();
        String billingPeriodEnd = BSBillingPeriodEndTF.getText().trim();
        String dueDate = BSDueDateTF.getText().trim();
        String totalAmountDue = BSTotalAmountDueTF.getText().trim();

        GRBaseChargeTF.setText(baseCharge);
        GRPenaltyChargeTF.setText(totalCharge);
        GRBillingPeriodStartTF.setText(billingPeriodStart);
        GRBillingPeriodEndTF.setText(billingPeriodEnd);
        GRDueDateTF.setText(dueDate);
        GRTotalAmountDueTF.setText(totalAmountDue);
    }

    private void updateBillingPeriodEnd() {
        try {
            String startDateText = BSBillingPeriodStartTF.getText().trim();
            if (startDateText.isEmpty()) {
                throw new IllegalArgumentException("Billing Period Start cannot be empty.");
            }
            
            LocalDate startDate = LocalDate.parse(startDateText, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            LocalDate endDate = startDate.plusMonths(1);
            BSBillingPeriodEndTF.setText(endDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, 
                "Invalid date format for Billing Period Start. Please use MM/dd/yyyy.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, 
                e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateDueDate() {
        try {
            String endDateText = BSBillingPeriodEndTF.getText().trim();
            if (endDateText.isEmpty()) {
                throw new IllegalArgumentException("Billing Period End cannot be empty.");
            }

            LocalDate endDate = LocalDate.parse(endDateText, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            LocalDate dueDate = endDate.plusDays(5);
            BSDueDateTF.setText(dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, 
                "Invalid date format for Billing Period End. Please use MM/dd/yyyy.", 
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, 
                e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String convertToSQLDate(String dateStr) {
        try {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date = (Date) df.parse(dateStr);
            return new SimpleDateFormat("MM/dd/yyyy").format(date);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, 
                "Invalid date format. Please use MM/dd/yyyy.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LogoutBT = new javax.swing.JButton();
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
        SIPropertyCB = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        SIServiceAddressTF = new javax.swing.JTextArea();
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
        MRAddToReceipt = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        MRReceipt = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        BDAccountNoTF = new javax.swing.JTextField();
        BDAccountNameTF = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        BDPreviousReadingTF = new javax.swing.JTextField();
        BDCurrentReadingTF = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        BDConsumptionTF = new javax.swing.JTextField();
        BDRateTF = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        BDBaseChargeTF = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        BDPenaltyChargeTF = new javax.swing.JTextField();
        BDTaxAmountTF = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        BDAddToReceipt = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        BDReceipt = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        BSAccountNoTF = new javax.swing.JTextField();
        BSAccountNameTF = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        BSBillingPeriodStartTF = new javax.swing.JTextField();
        BSBillingPeriodEndTF = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        BSTotalAmountDueTF = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        BSDueDateTF = new javax.swing.JTextField();
        BSAddToReceipt = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        BSReceipt = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        GRAccountNoTF = new javax.swing.JTextField();
        GRAccountNameTF = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        GRPreviousReadingTF = new javax.swing.JTextField();
        GRCurrentReadingTF = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        GRConsumptionTF = new javax.swing.JTextField();
        GRRateTF = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        GRBaseChargeTF = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        GRPenaltyChargeTF = new javax.swing.JTextField();
        GRDueDateTF = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        GRTaxAmountTF = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        GRPropertyTF = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        GRBillingPeriodStartTF = new javax.swing.JTextField();
        GRBillingPeriodEndTF = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        GRTotalAmountDueTF = new javax.swing.JTextField();
        GRBT = new javax.swing.JButton();
        jLabel69 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        GRServiceAddressTF = new javax.swing.JTextArea();
        jLabel70 = new javax.swing.JLabel();
        GRMeterNoTF = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        GRReceipt = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        PCReceipt = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LogoutBT.setText("Log out");
        LogoutBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutBTActionPerformed(evt);
            }
        });
        getContentPane().add(LogoutBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 690, -1, -1));

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
                .addContainerGap(419, Short.MAX_VALUE))
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

        SIPropertyCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Residential", "Commercial", "Industrial", "Institutional" }));
        SIPropertyCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SIPropertyCBActionPerformed(evt);
            }
        });
        jPanel2.add(SIPropertyCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 70, 200, 40));

        SIServiceAddressTF.setColumns(20);
        SIServiceAddressTF.setRows(5);
        jScrollPane2.setViewportView(SIServiceAddressTF);

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

        CustomerInfoTable.setBackground(new java.awt.Color(112, 193, 248));
        CustomerInfoTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 102, 102), null, null));
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
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
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
        jPanel7.add(MRRateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 310, 40));

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
        jPanel7.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, -1, -1));

        jLabel2.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel2.setText("Previous Reading :");
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, -1, -1));

        jLabel7.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel7.setText("Current Reading :");
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, -1, -1));

        jLabel8.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel8.setText("Rate :");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, -1, -1));

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
        jPanel7.add(MRConsumptionTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 430, 310, 40));

        MRPreviousReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRPreviousReadingTFActionPerformed(evt);
            }
        });
        jPanel7.add(MRPreviousReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 310, 40));

        MRCurrentReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRCurrentReadingTFActionPerformed(evt);
            }
        });
        jPanel7.add(MRCurrentReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 310, 40));

        MRAddToReceipt.setText("ADD TO RECEIPT");
        MRAddToReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRAddToReceiptActionPerformed(evt);
            }
        });
        jPanel7.add(MRAddToReceipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 510, -1, -1));

        MRReceipt.setColumns(20);
        MRReceipt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        MRReceipt.setRows(5);
        jScrollPane3.setViewportView(MRReceipt);

        jPanel7.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(674, 60, 350, 470));

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

        BDPreviousReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDPreviousReadingTFActionPerformed(evt);
            }
        });
        jPanel5.add(BDPreviousReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 312, 30));

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

        BDBaseChargeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDBaseChargeTFActionPerformed(evt);
            }
        });
        jPanel5.add(BDBaseChargeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 312, 30));

        jLabel47.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel47.setText("Penalty Charge :");
        jPanel5.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, -1, 27));

        BDPenaltyChargeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDPenaltyChargeTFActionPerformed(evt);
            }
        });
        jPanel5.add(BDPenaltyChargeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 312, 30));

        BDTaxAmountTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDTaxAmountTFActionPerformed(evt);
            }
        });
        jPanel5.add(BDTaxAmountTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 312, 30));

        jLabel56.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel56.setText("Tax :");
        jPanel5.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        BDAddToReceipt.setText("ADD TO RECEIPT");
        BDAddToReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDAddToReceiptActionPerformed(evt);
            }
        });
        jPanel5.add(BDAddToReceipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 510, -1, -1));

        BDReceipt.setColumns(20);
        BDReceipt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BDReceipt.setRows(5);
        BDReceipt.setPreferredSize(new java.awt.Dimension(332, 240));
        jScrollPane5.setViewportView(BDReceipt);

        jPanel5.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, 340, 510));

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

        BSBillingPeriodStartTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSBillingPeriodStartTFActionPerformed(evt);
            }
        });
        jPanel6.add(BSBillingPeriodStartTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 312, 30));

        BSBillingPeriodEndTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSBillingPeriodEndTFActionPerformed(evt);
            }
        });
        jPanel6.add(BSBillingPeriodEndTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 312, 30));

        jLabel54.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel54.setText("Billing Period End :");
        jPanel6.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        jLabel55.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel55.setText("Total Amount Due :");
        jPanel6.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, 27));

        BSTotalAmountDueTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSTotalAmountDueTFActionPerformed(evt);
            }
        });
        jPanel6.add(BSTotalAmountDueTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 312, 30));

        jLabel68.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel68.setText("Due Date :");
        jPanel6.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, -1, 27));

        BSDueDateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSDueDateTFActionPerformed(evt);
            }
        });
        jPanel6.add(BSDueDateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 312, 30));

        BSAddToReceipt.setText("ADD TO RECEIPT");
        BSAddToReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSAddToReceiptActionPerformed(evt);
            }
        });
        jPanel6.add(BSAddToReceipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 300, -1, -1));

        BSReceipt.setColumns(20);
        BSReceipt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BSReceipt.setRows(5);
        jScrollPane6.setViewportView(BSReceipt);

        jPanel6.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, 330, 510));

        jTabbedPane1.addTab("BILLING SUMMARY", jPanel6);

        jPanel1.setBackground(new java.awt.Color(51, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel46.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel46.setText("Account No. :");
        jPanel1.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, 27));

        GRAccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRAccountNoTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRAccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 312, 30));

        GRAccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRAccountNameTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRAccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 312, 30));

        jLabel48.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel48.setText("Account Name :");
        jPanel1.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

        jLabel49.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel49.setText("Previous Reading :");
        jPanel1.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, 27));

        GRPreviousReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRPreviousReadingTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRPreviousReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 312, 30));

        GRCurrentReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRCurrentReadingTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRCurrentReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 312, 30));

        jLabel57.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel57.setText("Current Reading :");
        jPanel1.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        jLabel58.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel58.setText("Consumption :");
        jPanel1.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, -1, 27));

        GRConsumptionTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRConsumptionTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRConsumptionTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 312, 30));

        GRRateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRRateTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRRateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 312, 30));

        jLabel59.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel59.setText("Rate :");
        jPanel1.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, -1, -1));

        jLabel60.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel60.setText("Base Charge :");
        jPanel1.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, -1, 27));

        GRBaseChargeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRBaseChargeTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRBaseChargeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 430, 312, 30));

        jLabel61.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel61.setText("Penalty Charge :");
        jPanel1.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 470, -1, 27));

        GRPenaltyChargeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRPenaltyChargeTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRPenaltyChargeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 470, 312, 30));

        GRDueDateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRDueDateTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRDueDateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 590, 312, 30));

        jLabel62.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel62.setText("Due Date:");
        jPanel1.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 600, -1, -1));

        GRTaxAmountTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRTaxAmountTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRTaxAmountTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, 312, 30));

        jLabel63.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel63.setText("Tax :");
        jPanel1.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, -1, -1));

        GRPropertyTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRPropertyTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRPropertyTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 312, 30));

        jLabel64.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel64.setText("Meter No. :");
        jPanel1.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        jLabel65.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel65.setText("Billing Period Start :");
        jPanel1.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, -1, 27));

        GRBillingPeriodStartTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRBillingPeriodStartTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRBillingPeriodStartTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 510, 312, 30));

        GRBillingPeriodEndTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRBillingPeriodEndTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRBillingPeriodEndTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 550, 312, 30));

        jLabel66.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel66.setText("Billing Period End :");
        jPanel1.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 560, -1, -1));

        jLabel67.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel67.setText("Total Amount Due :");
        jPanel1.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 630, -1, 27));

        GRTotalAmountDueTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRTotalAmountDueTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRTotalAmountDueTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 630, 312, 30));

        GRBT.setText("GENERATE RECEIPT");
        GRBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRBTActionPerformed(evt);
            }
        });
        jPanel1.add(GRBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 520, -1, -1));

        jLabel69.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel69.setText("Service Area :");
        jPanel1.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        GRServiceAddressTF.setColumns(20);
        GRServiceAddressTF.setRows(5);
        jScrollPane1.setViewportView(GRServiceAddressTF);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 310, 30));

        jLabel70.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel70.setText("Property :");
        jPanel1.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        GRMeterNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRMeterNoTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRMeterNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 312, 30));

        GRReceipt.setColumns(20);
        GRReceipt.setRows(5);
        jScrollPane7.setViewportView(GRReceipt);

        jPanel1.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, 330, 460));

        jTabbedPane1.addTab("GENERATE RECEIPT", jPanel1);

        jPanel3.setBackground(new java.awt.Color(153, 255, 255));

        PCReceipt.setColumns(20);
        PCReceipt.setRows(5);
        jScrollPane8.setViewportView(PCReceipt);

        jLabel11.setText("REPORT");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel12.setText("CLIENT RECEIPT");

        jButton1.setText("SEND");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jLabel12))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(74, 74, 74)
                                        .addComponent(jLabel11)))))))
                .addContainerGap(673, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PAYMENT CONFIRMATION", jPanel3);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 12, -1, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ContactNumberTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContactNumberTFActionPerformed

    }//GEN-LAST:event_ContactNumberTFActionPerformed

    private void SIMeterNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SIMeterNoTFActionPerformed


    }//GEN-LAST:event_SIMeterNoTFActionPerformed

    private void DeleteBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBTActionPerformed
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
                SIServiceAddressTF.setText(""); 
                ContactNumberTF.setText("");  
                SIPropertyCB.setSelectedIndex(-1);  
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

        String accountNo = SIAccountNoTF.getText().trim();

    if (accountNo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter an Account Number.");
        return;
    }

    try {
        
        pst = con.prepareStatement(
            "UPDATE clientinfo SET Account_Name=?, Service_Address=?, Contact_Number=?, Property=?, " +
            "Account_Status=? WHERE Account_No=?"
        );
        pst.setString(1, SIAccountNameTF.getText());  
        pst.setString(2, SIServiceAddressTF.getText()); 
        pst.setInt(3, Integer.parseInt(ContactNumberTF.getText()));  
        pst.setString(4, (String) SIPropertyCB.getSelectedItem()); 
        pst.setString(5, (String) AccountStatusCB.getSelectedItem()); 
        pst.setString(6, accountNo);  
        int rowsUpdated = pst.executeUpdate();

        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Client details updated successfully!");

           
            DefaultTableModel model = (DefaultTableModel) CustomerInfoTable.getModel();
            int rowIndex = getRowIndexByAccountNo(accountNo); 

        if (rowIndex >= 0) {
               
                model.setValueAt(SIAccountNameTF.getText(), rowIndex, 1);  
                model.setValueAt(SIServiceAddressTF.getText(), rowIndex, 2);  
                model.setValueAt(ContactNumberTF.getText(), rowIndex, 3);  
                model.setValueAt(SIPropertyCB.getSelectedItem(), rowIndex, 4);  
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
        
         String accountNo = SIAccountNoTF.getText().trim();


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
            SIServiceAddressTF.setText(rs.getString("Service_Address")); 
            ContactNumberTF.setText(String.valueOf(rs.getInt("Contact_Number"))); 
            SIPropertyCB.setSelectedItem(rs.getString("Property"));  
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
    String serviceAddress = SIServiceAddressTF.getText().trim();

  
    if (accountName.isEmpty() || serviceAddress.isEmpty() || ContactNumberTF.getText().trim().isEmpty() ||
        SIPropertyCB.getSelectedIndex() == -1 || AccountStatusCB.getSelectedIndex() == -1) {
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

    String selectedProperty = (String) SIPropertyCB.getSelectedItem();
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
        SIServiceAddressTF.setText("");
        ContactNumberTF.setText("");
        SIPropertyCB.setSelectedIndex(-1);
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
    String accountNo = SIAccountNoTF.getText().trim();
    String accountName = SIAccountNameTF.getText().trim();  
    String serviceAddress = SIServiceAddressTF.getText().trim(); 
    String property = (String) SIPropertyCB.getSelectedItem();  
    String meterNo = SIMeterNoTF.getText().trim();  

    if (!accountNo.isEmpty() && !accountName.isEmpty()) {
        syncAccountInfo(accountNo, accountName, serviceAddress, property, meterNo);
    }
    }//GEN-LAST:event_SIAccountNoTFActionPerformed

    private void MRAccountNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRAccountNoTFActionPerformed

    }//GEN-LAST:event_MRAccountNoTFActionPerformed

    private void MRRateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRRateTFActionPerformed
        try {
        String rateText = MRRateTF.getText().trim();
        if (rateText.isEmpty()) {
            MRRateTF.setText(""); 
            return; 
        }
        
        BigDecimal rate = new BigDecimal(rateText);
        String formattedRate = rate.setScale(2, RoundingMode.HALF_UP).toString();
        MRRateTF.setText(formattedRate);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, 
            "Invalid input. Please enter a valid numeric value for the rate.", 
            "Error", JOptionPane.ERROR_MESSAGE);
        MRRateTF.setText("");
    }
    }//GEN-LAST:event_MRRateTFActionPerformed

    private void MRAccountNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRAccountNameTFActionPerformed
     
    }//GEN-LAST:event_MRAccountNameTFActionPerformed

    private void MRMeterNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRMeterNoTFActionPerformed

    }//GEN-LAST:event_MRMeterNoTFActionPerformed

    private void MRConsumptionTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRConsumptionTFActionPerformed
        try {
        String previousReadingText = MRPreviousReadingTF.getText().trim();
        String currentReadingText = MRCurrentReadingTF.getText().trim();
        if (previousReadingText.isEmpty() || currentReadingText.isEmpty()) {
            MRConsumptionTF.setText(""); 
            return;
        }
        
        BigDecimal previousReading = new BigDecimal(previousReadingText);
        BigDecimal currentReading = new BigDecimal(currentReadingText);

        BigDecimal consumption = currentReading.subtract(previousReading);

        String formattedConsumption = consumption.setScale(2, RoundingMode.HALF_UP).toString();

        MRConsumptionTF.setText(formattedConsumption);
    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(this, 
            "Invalid input. Please enter valid numeric values for previous and current readings.", 
            "Error", JOptionPane.ERROR_MESSAGE);
        
        MRConsumptionTF.setText("");
    }
    }//GEN-LAST:event_MRConsumptionTFActionPerformed

    private void MRPreviousReadingTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRPreviousReadingTFActionPerformed
       
    }//GEN-LAST:event_MRPreviousReadingTFActionPerformed

    private void MRCurrentReadingTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRCurrentReadingTFActionPerformed

    }//GEN-LAST:event_MRCurrentReadingTFActionPerformed

    private void BDAccountNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDAccountNoTFActionPerformed
    
    }//GEN-LAST:event_BDAccountNoTFActionPerformed

    private void BDAccountNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDAccountNameTFActionPerformed

    }//GEN-LAST:event_BDAccountNameTFActionPerformed

    private void BDPreviousReadingTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDPreviousReadingTFActionPerformed

    }//GEN-LAST:event_BDPreviousReadingTFActionPerformed

    private void BDCurrentReadingTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDCurrentReadingTFActionPerformed

    }//GEN-LAST:event_BDCurrentReadingTFActionPerformed

    private void BDConsumptionTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDConsumptionTFActionPerformed
       
    }//GEN-LAST:event_BDConsumptionTFActionPerformed

    private void BDRateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDRateTFActionPerformed
    
    }//GEN-LAST:event_BDRateTFActionPerformed

    private void BDBaseChargeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDBaseChargeTFActionPerformed
        try {
        String consumptionText = BDConsumptionTF.getText().trim();
        String rateText = BDRateTF.getText().trim();

        if (consumptionText.isEmpty() || rateText.isEmpty()) {
            BDBaseChargeTF.setText(""); 
            return;
        }
        
        BigDecimal consumption = new BigDecimal(consumptionText);
        BigDecimal rate = new BigDecimal(rateText);

        BigDecimal baseCharge = consumption.multiply(rate);
        
        String formattedBaseCharge = baseCharge.setScale(2, RoundingMode.HALF_UP).toString();
        
        BDBaseChargeTF.setText(formattedBaseCharge);
    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(this, 
            "Invalid input. Please enter valid numeric values for consumption and rate.", 
            "Error", JOptionPane.ERROR_MESSAGE);
        
        BDBaseChargeTF.setText("");
    }
    
    }//GEN-LAST:event_BDBaseChargeTFActionPerformed

    private void BDPenaltyChargeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDPenaltyChargeTFActionPerformed
       try {
        String previousReadingText = MRPreviousReadingTF.getText().trim();
        BigDecimal previousReading = new BigDecimal(previousReadingText);

        boolean isDueDateMissed = checkDueDateMissed(); 
        
        BigDecimal penaltyCharge;
        if (previousReading.compareTo(BigDecimal.ZERO) == 0) {
            penaltyCharge = BigDecimal.ZERO;
        } else if (isDueDateMissed) {
            penaltyCharge = new BigDecimal("200.00");
        } else {
            penaltyCharge = BigDecimal.ZERO;
        }
        
        String formattedPenaltyCharge = penaltyCharge.setScale(2, RoundingMode.HALF_UP).toString();
        BDPenaltyChargeTF.setText(formattedPenaltyCharge);
    } catch (NumberFormatException e) {
        
        JOptionPane.showMessageDialog(this,
                "Invalid input. Please ensure previous consumption is a valid numeric value.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        
        BDPenaltyChargeTF.setText("");
    }
}
    
    private boolean checkDueDateMissed() {
        return true;
    }//GEN-LAST:event_BDPenaltyChargeTFActionPerformed

    private void BSAccountNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSAccountNoTFActionPerformed

    }//GEN-LAST:event_BSAccountNoTFActionPerformed

    private void BSAccountNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSAccountNameTFActionPerformed

    }//GEN-LAST:event_BSAccountNameTFActionPerformed

    private void BSBillingPeriodStartTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSBillingPeriodStartTFActionPerformed
        updateBillingPeriodEnd();
        updateDueDate();
    }//GEN-LAST:event_BSBillingPeriodStartTFActionPerformed

    private void BSBillingPeriodEndTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSBillingPeriodEndTFActionPerformed
        updateDueDate();
    }//GEN-LAST:event_BSBillingPeriodEndTFActionPerformed

    private void BSTotalAmountDueTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSTotalAmountDueTFActionPerformed
        try {
            String taxAmountText = BDTaxAmountTF.getText().trim();
            String baseChargeText = BDBaseChargeTF.getText().trim();
            String penaltyChargeText = BDPenaltyChargeTF.getText().trim();

            if (taxAmountText.isEmpty() || baseChargeText.isEmpty() || penaltyChargeText.isEmpty()) {
                BSTotalAmountDueTF.setText(""); 
                return;
            }

            BigDecimal taxAmount = new BigDecimal(taxAmountText);
            BigDecimal baseCharge = new BigDecimal(baseChargeText);
            BigDecimal penaltyCharge = new BigDecimal(penaltyChargeText);

            BigDecimal totalAmountDue = taxAmount.add(baseCharge).add(penaltyCharge);

            String formattedTotalAmountDue = totalAmountDue.setScale(2, RoundingMode.HALF_UP).toString();

            BSTotalAmountDueTF.setText(formattedTotalAmountDue);
        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "Invalid input. Please ensure all values are valid numeric amounts.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

            BSTotalAmountDueTF.setText("");
}
    }//GEN-LAST:event_BSTotalAmountDueTFActionPerformed

    private void SIPropertyCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SIPropertyCBActionPerformed

    String selectedProperty = (String) SIPropertyCB.getSelectedItem();
    double taxAmount = 0.0;

    switch (selectedProperty) {
        case "Residential":
            taxAmount = 30.00; 
            break;
        case "Commercial":
            taxAmount = 100.00;
            break;
        case "Industrial":
            taxAmount = 300.00; 
            break;
        case "Institutional":
            taxAmount = 50.00; 
            break;
        default:
            taxAmount = 0.00; 
            break;
    }

    BDTaxAmountTF.setText(String.format("%.2f", taxAmount));
    
    try {
        if (SIPropertyCB.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, 
                "No property selected. Please select a valid property.", 
                "Error", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String selectedValue = SIPropertyCB.getSelectedItem().toString();
        System.out.println("Selected property: " + selectedValue);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "An error occurred: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SIPropertyCBActionPerformed

    private void BDTaxAmountTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDTaxAmountTFActionPerformed
       
    }//GEN-LAST:event_BDTaxAmountTFActionPerformed

    private void AccountStatusCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccountStatusCBActionPerformed
       
    }//GEN-LAST:event_AccountStatusCBActionPerformed

    private void GRAccountNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRAccountNoTFActionPerformed
      
    }//GEN-LAST:event_GRAccountNoTFActionPerformed

    private void GRAccountNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRAccountNameTFActionPerformed
    
    }//GEN-LAST:event_GRAccountNameTFActionPerformed

    private void GRPreviousReadingTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRPreviousReadingTFActionPerformed
      
    }//GEN-LAST:event_GRPreviousReadingTFActionPerformed

    private void GRCurrentReadingTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRCurrentReadingTFActionPerformed
   
    }//GEN-LAST:event_GRCurrentReadingTFActionPerformed

    private void GRConsumptionTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRConsumptionTFActionPerformed
    
    }//GEN-LAST:event_GRConsumptionTFActionPerformed

    private void GRRateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRRateTFActionPerformed
    
    }//GEN-LAST:event_GRRateTFActionPerformed

    private void GRBaseChargeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRBaseChargeTFActionPerformed
      
    }//GEN-LAST:event_GRBaseChargeTFActionPerformed

    private void GRPenaltyChargeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRPenaltyChargeTFActionPerformed
    
    }//GEN-LAST:event_GRPenaltyChargeTFActionPerformed

    private void GRDueDateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRDueDateTFActionPerformed

    }//GEN-LAST:event_GRDueDateTFActionPerformed

    private void GRTaxAmountTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRTaxAmountTFActionPerformed
       
    }//GEN-LAST:event_GRTaxAmountTFActionPerformed

    private void GRPropertyTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRPropertyTFActionPerformed
    
    }//GEN-LAST:event_GRPropertyTFActionPerformed

    private void GRBillingPeriodStartTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRBillingPeriodStartTFActionPerformed
    
    }//GEN-LAST:event_GRBillingPeriodStartTFActionPerformed

    private void GRBillingPeriodEndTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRBillingPeriodEndTFActionPerformed
    
    }//GEN-LAST:event_GRBillingPeriodEndTFActionPerformed

    private void GRTotalAmountDueTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRTotalAmountDueTFActionPerformed
     
    }//GEN-LAST:event_GRTotalAmountDueTFActionPerformed

    private void BSDueDateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSDueDateTFActionPerformed

        String endDateText = BSBillingPeriodEndTF.getText().trim();

        try {
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("MM/dd/yyyy");
            dateFormat.setLenient(false); // Ensure strict date parsing
            java.util.Date endDate = dateFormat.parse(endDateText);

            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(endDate);
            calendar.add(java.util.Calendar.DAY_OF_MONTH, 5);
            java.util.Date dueDate = calendar.getTime();

            String dueDateText = dateFormat.format(dueDate);
            BSDueDateTF.setText(dueDateText);

        } catch (java.text.ParseException e) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Please enter a valid billing period end date in MM/dd/yyyy format.", 
                "Input Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BSDueDateTFActionPerformed

    private void GRMeterNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRMeterNoTFActionPerformed
    
    }//GEN-LAST:event_GRMeterNoTFActionPerformed

    private void MRAddToReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRAddToReceiptActionPerformed
        syncMRDataToOtherTabs();   
    }//GEN-LAST:event_MRAddToReceiptActionPerformed

    private void BDAddToReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDAddToReceiptActionPerformed
        syncBDDataToBSandGR();
        
    }//GEN-LAST:event_BDAddToReceiptActionPerformed

    private void BSAddToReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSAddToReceiptActionPerformed
         syncBSDataToGR();
    }//GEN-LAST:event_BSAddToReceiptActionPerformed

    private void GRBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRBTActionPerformed
        try {
            String startDateInput = GRBillingPeriodStartTF.getText().trim();
            String endDateInput = GRBillingPeriodEndTF.getText().trim();

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            dateFormat.setLenient(false); // Ensures strict date parsing
            java.util.Date utilDateStart = dateFormat.parse(startDateInput);
            java.util.Date utilDateEnd = dateFormat.parse(endDateInput);

            java.sql.Date sqlDateStart = new java.sql.Date(utilDateStart.getTime());
            java.sql.Date sqlDateEnd = new java.sql.Date(utilDateEnd.getTime());

            String accountNo = GRAccountNoTF.getText();
        if (GRReceipt.getText().contains("Account No.  : " + accountNo)) {
            JOptionPane.showMessageDialog(null, "Account already added to the receipt area.", "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
            return; 
        }

        try (Connection conn = DBConnection.getConnection()) {
            String checkAccountQuery = "SELECT COUNT(*) FROM generatereceipt WHERE Account_No = ? AND Billing_Period_Start = ? AND Billing_Period_End = ? AND Due_Date = ?";

            try (PreparedStatement preparedStatementCheck = conn.prepareStatement(checkAccountQuery)) {
                preparedStatementCheck.setString(1, accountNo);
                preparedStatementCheck.setDate(2, sqlDateStart);
                preparedStatementCheck.setDate(3, sqlDateEnd);
                preparedStatementCheck.setDate(4, sqlDateStart);

                ResultSet rs = preparedStatementCheck.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(null, "Account with the same billing period and due date already exists in the database.", "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String sql = "INSERT INTO generatereceipt "
                           + "(Account_No, Account_Name, Service_Address, Property, Meter_No, Previous_Reading, Current_Reading, Consumption, Rate, Tax_Amount, Base_Charge, Penalty_Charge, Billing_Period_Start, Billing_Period_End, Due_Date, Total_Amount_Due) "
                           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    String accountName = GRAccountNameTF.getText();
                    String serviceAddress = GRServiceAddressTF.getText();
                    String property = GRPropertyTF.getText();
                    String meterNo = GRMeterNoTF.getText();
                    BigDecimal previousReading = new BigDecimal(GRPreviousReadingTF.getText());
                    BigDecimal currentReading = new BigDecimal(GRCurrentReadingTF.getText());
                    BigDecimal consumption = new BigDecimal(GRConsumptionTF.getText());
                    BigDecimal rate = new BigDecimal(GRRateTF.getText());
                    BigDecimal taxAmount = new BigDecimal(GRTaxAmountTF.getText());
                    BigDecimal baseCharge = new BigDecimal(GRBaseChargeTF.getText());
                    BigDecimal penaltyCharge = new BigDecimal(GRPenaltyChargeTF.getText());
                    java.sql.Date dueDate = new java.sql.Date(new java.util.Date().getTime()); 
                    BigDecimal totalAmountDue = new BigDecimal(GRTotalAmountDueTF.getText());

                    preparedStatement.setString(1, accountNo);
                    preparedStatement.setString(2, accountName);
                    preparedStatement.setString(3, serviceAddress);
                    preparedStatement.setString(4, property);
                    preparedStatement.setString(5, meterNo);
                    preparedStatement.setBigDecimal(6, previousReading);
                    preparedStatement.setBigDecimal(7, currentReading);
                    preparedStatement.setBigDecimal(8, consumption);
                    preparedStatement.setBigDecimal(9, rate);
                    preparedStatement.setBigDecimal(10, taxAmount);
                    preparedStatement.setBigDecimal(11, baseCharge);
                    preparedStatement.setBigDecimal(12, penaltyCharge);
                    preparedStatement.setDate(13, sqlDateStart);
                    preparedStatement.setDate(14, sqlDateEnd);
                    preparedStatement.setDate(15, dueDate);
                    preparedStatement.setBigDecimal(16, totalAmountDue);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    GRReceipt.setText(GRReceipt.getText() + "\n***************BILLING SUMMARY****************\n");
                    GRReceipt.append("\nAccount No.  : " + GRAccountNoTF.getText());
                    GRReceipt.append("\nAccount Name : " + GRAccountNameTF.getText());
                    GRReceipt.append("\nService Area : " + GRServiceAddressTF.getText());
                    GRReceipt.append("\nProperty     : " + GRPropertyTF.getText());
                    GRReceipt.append("\nMeter No.    : " + GRMeterNoTF.getText());
                    GRReceipt.append("\nPrevious Reading : " + GRPreviousReadingTF.getText());
                    GRReceipt.append("\nCurrent Reading  : " + GRCurrentReadingTF.getText());
                    GRReceipt.append("\nConsumption      : " + GRConsumptionTF.getText());
                    GRReceipt.append("\nRate            : " + GRRateTF.getText());
                    GRReceipt.append("\nTax             : " + GRTaxAmountTF.getText());
                    GRReceipt.append("\nBase Charge     : " + GRBaseChargeTF.getText());
                    GRReceipt.append("\nPenalty Charge  : " + GRPenaltyChargeTF.getText());
                    GRReceipt.append("\nBilling Period Start : " + GRBillingPeriodStartTF.getText());
                    GRReceipt.append("\nBilling Period End   : " + GRBillingPeriodEndTF.getText());
                    GRReceipt.append("\nDue Date            : " + GRDueDateTF.getText());
                    GRReceipt.append("\nTotal Amount Due     : " + GRTotalAmountDueTF.getText());

                    JOptionPane.showMessageDialog(null, "Receipt has been successfully sent to the client.", "Receipt Sent", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to generate receipt.");
                    }
                }
            }
        }
    } catch (ParseException ex) {
        JOptionPane.showMessageDialog(null, "Invalid date format. Please use MM/dd/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }


    }//GEN-LAST:event_GRBTActionPerformed

    private void LogoutBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutBTActionPerformed
        int response = JOptionPane.showConfirmDialog(
             null,
             "Are you sure you want to log out?",
             "Logout Confirmation",
             JOptionPane.YES_NO_OPTION,
             JOptionPane.QUESTION_MESSAGE
         );

        if (response == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(
                null,
                "Thank you for your hard work! Have a wonderful day!",
                "Goodbye",
                JOptionPane.INFORMATION_MESSAGE
            );
            
        new Login().setVisible(true); 
        this.dispose();
    }

    }//GEN-LAST:event_LogoutBTActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {

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
    private javax.swing.JButton BDAddToReceipt;
    private javax.swing.JTextField BDBaseChargeTF;
    private javax.swing.JTextField BDConsumptionTF;
    private javax.swing.JTextField BDCurrentReadingTF;
    private javax.swing.JTextField BDPenaltyChargeTF;
    private javax.swing.JTextField BDPreviousReadingTF;
    private javax.swing.JTextField BDRateTF;
    private javax.swing.JTextArea BDReceipt;
    private javax.swing.JTextField BDTaxAmountTF;
    private javax.swing.JTextField BSAccountNameTF;
    private javax.swing.JTextField BSAccountNoTF;
    private javax.swing.JButton BSAddToReceipt;
    private javax.swing.JTextField BSBillingPeriodEndTF;
    private javax.swing.JTextField BSBillingPeriodStartTF;
    private javax.swing.JTextField BSDueDateTF;
    private javax.swing.JTextArea BSReceipt;
    private javax.swing.JTextField BSTotalAmountDueTF;
    private javax.swing.JTextField ContactNumberTF;
    private javax.swing.JButton CreateBT;
    private javax.swing.JTable CustomerInfoTable;
    private javax.swing.JButton DeleteBT;
    private javax.swing.JTextField GRAccountNameTF;
    private javax.swing.JTextField GRAccountNoTF;
    private javax.swing.JButton GRBT;
    private javax.swing.JTextField GRBaseChargeTF;
    private javax.swing.JTextField GRBillingPeriodEndTF;
    private javax.swing.JTextField GRBillingPeriodStartTF;
    private javax.swing.JTextField GRConsumptionTF;
    private javax.swing.JTextField GRCurrentReadingTF;
    private javax.swing.JTextField GRDueDateTF;
    private javax.swing.JTextField GRMeterNoTF;
    private javax.swing.JTextField GRPenaltyChargeTF;
    private javax.swing.JTextField GRPreviousReadingTF;
    private javax.swing.JTextField GRPropertyTF;
    private javax.swing.JTextField GRRateTF;
    private javax.swing.JTextArea GRReceipt;
    private javax.swing.JTextArea GRServiceAddressTF;
    private javax.swing.JTextField GRTaxAmountTF;
    private javax.swing.JTextField GRTotalAmountDueTF;
    private javax.swing.JButton LogoutBT;
    private javax.swing.JTextField MRAccountNameTF;
    private javax.swing.JTextField MRAccountNoTF;
    private javax.swing.JButton MRAddToReceipt;
    private javax.swing.JTextField MRConsumptionTF;
    private javax.swing.JTextField MRCurrentReadingTF;
    private javax.swing.JTextField MRMeterNoTF;
    private javax.swing.JTextField MRPreviousReadingTF;
    private javax.swing.JTextField MRRateTF;
    private javax.swing.JTextArea MRReceipt;
    private javax.swing.JTextArea PCReceipt;
    private javax.swing.JTextField SIAccountNameTF;
    private javax.swing.JTextField SIAccountNoTF;
    private javax.swing.JTextField SIMeterNoTF;
    private javax.swing.JComboBox<String> SIPropertyCB;
    private javax.swing.JTextArea SIServiceAddressTF;
    private javax.swing.JButton SearchBT;
    private javax.swing.JButton UpdateBT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
