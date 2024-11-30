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
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;


    public class Admin_Dashboard extends javax.swing.JFrame {

     
        public Admin_Dashboard() {
            initComponents();
            Connect();
            fetchClientInfo();
            fetchDataAndPopulateTable();
            
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
   
   public class DBConnection2 {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/java_user_database"; // Replace with your second DB URL
    private static final String DB_USER = "root"; // Replace with your second DB username if different
    private static final String DB_PASSWORD = ""; // Replace with your second DB password if different

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

        DefaultTableModel df = (DefaultTableModel) ClientInfoTable.getModel();
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
        PAccountNoTF.setText(accountNo);

        SIAccountNameTF.setText(accountName);
        MRAccountNameTF.setText(accountName);
        BDAccountNameTF.setText(accountName);
        BSAccountNameTF.setText(accountName);
        GRAccountNameTF.setText(accountName);
        PAccountNameTF.setText(accountName);

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
        PAccountNoTF.setEditable(false);

        SIAccountNameTF.setEditable(true);
        MRAccountNameTF.setEditable(false);
        BDAccountNameTF.setEditable(false);
        BSAccountNameTF.setEditable(false);
        GRAccountNameTF.setEditable(false);
        PAccountNameTF.setEditable(false);

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
    PTotalAmountDueTF1.setText(totalAmountDue);
    
    
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
    PTotalAmountDueTF1.setEditable(false);

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
        PPaymentDateTF.setText(totalAmountDue);
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
   

   private void syncTotalAmountDue() {

    String totalAmount = GRTotalAmountDueTF.getText();

    if (totalAmount != null && !totalAmount.isEmpty()) {
        PTotalAmountDueTF1.setText(totalAmount);
    } else {
        PTotalAmountDueTF1.setText("");
    }
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

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/java_user_database"; // Replace with your DB details
            String user = "root";  // Replace with your DB username
            String password = "";  // Replace with your DB password

            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }

    // Method to fetch data from the database and populate the JTable
    private void fetchDataAndPopulateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Rate Name");
        model.addColumn("Base Charge");
        model.addColumn("Rate Per Cubic Meter");
        model.addColumn("Penalty Charge");
        model.addColumn("Effective Date");
        model.addColumn("Expiration Date");

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ratemaintenance")) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("Rate_Name"),
                    rs.getDouble("Base_Charge"),
                    rs.getDouble("Rate_Per_Cubic_Meter"),
                    rs.getInt("Penalty_Charge"),
                    rs.getDate("Effective_Date"),
                    rs.getDate("Expiration_Date")
                });
            }

            // Set the model to the table to display data
            RateMaintenanceTable.setModel(model);

        } catch (SQLException e) {
            System.out.println("Error while fetching data: " + e.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
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
        ClientInfoTable = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        ContactNumberTF = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
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
        jLabel26 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
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
        jLabel27 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
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
        jLabel28 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
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
        jPanel11 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        GRReceipt = new javax.swing.JTextArea();
        jLabel29 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel71 = new javax.swing.JLabel();
        PAccountNoTF = new javax.swing.JTextField();
        PAccountNameTF = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        PPaymentDateTF = new javax.swing.JTextField();
        PaymentMethodCB = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        PArea = new javax.swing.JTextArea();
        PTotalAmountDueTF1 = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        PPenaltyChargeTF = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        CLTable = new javax.swing.JTable();
        DisplayClientsBT = new javax.swing.JButton();
        CLSearchTF = new javax.swing.JTextField();
        CLSearchBT = new javax.swing.JButton();
        CLDeleteBT = new javax.swing.JButton();
        CLDeleteAllBT = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        SalesTable = new javax.swing.JTable();
        SCalculateViewSalesBT = new javax.swing.JButton();
        SearchSales = new javax.swing.JTextField();
        TotalSalesTF = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        RateMaintenanceTable = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        LogoutBT = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(102, 157, 210));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 0, 12)); // NOI18N

        jPanel8.setBackground(new java.awt.Color(0, 51, 51));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Cooper Black", 0, 60)); // NOI18N
        jLabel30.setText("ADMIN DASHBOARD");
        jPanel8.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 730, 100));

        jLabel14.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 18)); // NOI18N
        jLabel14.setText("Welcome to your Dashboard");
        jPanel8.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, -1, 20));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/WATER001 (1).jpg"))); // NOI18N
        jPanel8.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 700));

        jTabbedPane1.addTab("", jPanel8);

        jPanel2.setBackground(new java.awt.Color(0, 51, 51));
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

        SIAccountNoTF.setBackground(new java.awt.Color(23, 34, 71));
        SIAccountNoTF.setForeground(new java.awt.Color(255, 255, 255));
        SIAccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SIAccountNoTFActionPerformed(evt);
            }
        });
        jPanel2.add(SIAccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 312, 40));

        CreateBT.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        CreateBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/add-user (1).png"))); // NOI18N
        CreateBT.setText("CREATE");
        CreateBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateBTActionPerformed(evt);
            }
        });
        jPanel2.add(CreateBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, 110, 44));

        SIAccountNameTF.setBackground(new java.awt.Color(23, 34, 71));
        SIAccountNameTF.setForeground(new java.awt.Color(255, 255, 255));
        SIAccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SIAccountNameTFActionPerformed(evt);
            }
        });
        jPanel2.add(SIAccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 312, 40));

        SearchBT.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        SearchBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/focus (1).png"))); // NOI18N
        SearchBT.setText("SEARCH");
        SearchBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBTActionPerformed(evt);
            }
        });
        jPanel2.add(SearchBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 330, 110, 44));

        UpdateBT.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        UpdateBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/update (1).png"))); // NOI18N
        UpdateBT.setText("UPDATE");
        UpdateBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBTActionPerformed(evt);
            }
        });
        jPanel2.add(UpdateBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 330, 110, 44));

        DeleteBT.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        DeleteBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/delete (1).png"))); // NOI18N
        DeleteBT.setText("DELETE");
        DeleteBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBTActionPerformed(evt);
            }
        });
        jPanel2.add(DeleteBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 330, 100, 44));

        SIPropertyCB.setBackground(new java.awt.Color(23, 34, 71));
        SIPropertyCB.setForeground(new java.awt.Color(255, 255, 255));
        SIPropertyCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Residential", "Commercial", "Industrial", "Institutional" }));
        SIPropertyCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SIPropertyCBActionPerformed(evt);
            }
        });
        jPanel2.add(SIPropertyCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 70, 200, 40));

        SIServiceAddressTF.setBackground(new java.awt.Color(23, 34, 71));
        SIServiceAddressTF.setColumns(20);
        SIServiceAddressTF.setForeground(new java.awt.Color(255, 255, 255));
        SIServiceAddressTF.setRows(5);
        jScrollPane2.setViewportView(SIServiceAddressTF);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 312, 40));

        jLabel32.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel32.setText("Meter No. :");
        jPanel2.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 140, -1, -1));

        jLabel33.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel33.setText("Account Status :");
        jPanel2.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 200, -1, -1));

        AccountStatusCB.setBackground(new java.awt.Color(23, 34, 71));
        AccountStatusCB.setForeground(new java.awt.Color(255, 255, 255));
        AccountStatusCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));
        AccountStatusCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccountStatusCBActionPerformed(evt);
            }
        });
        jPanel2.add(AccountStatusCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 190, 200, 40));

        SIMeterNoTF.setBackground(new java.awt.Color(23, 34, 71));
        SIMeterNoTF.setForeground(new java.awt.Color(255, 255, 255));
        SIMeterNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SIMeterNoTFActionPerformed(evt);
            }
        });
        jPanel2.add(SIMeterNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, 200, 40));

        ClientInfoTable.setBackground(new java.awt.Color(102, 157, 210));
        ClientInfoTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 102, 102), null, null));
        ClientInfoTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(ClientInfoTable);
        if (ClientInfoTable.getColumnModel().getColumnCount() > 0) {
            ClientInfoTable.getColumnModel().getColumn(0).setResizable(false);
            ClientInfoTable.getColumnModel().getColumn(1).setResizable(false);
            ClientInfoTable.getColumnModel().getColumn(2).setResizable(false);
            ClientInfoTable.getColumnModel().getColumn(3).setResizable(false);
            ClientInfoTable.getColumnModel().getColumn(4).setResizable(false);
            ClientInfoTable.getColumnModel().getColumn(5).setResizable(false);
            ClientInfoTable.getColumnModel().getColumn(6).setResizable(false);
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

        ContactNumberTF.setBackground(new java.awt.Color(23, 34, 71));
        ContactNumberTF.setForeground(new java.awt.Color(255, 255, 255));
        ContactNumberTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContactNumberTFActionPerformed(evt);
            }
        });
        jPanel2.add(ContactNumberTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 310, 40));

        jLabel25.setFont(new java.awt.Font("MingLiU_HKSCS-ExtB", 1, 16)); // NOI18N
        jLabel25.setText("SERVICE INFORMATION");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 240, -1));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/WATER001 (1).jpg"))); // NOI18N
        jPanel2.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 700));

        jTabbedPane1.addTab("SERVICE INFORMATION", jPanel2);

        jPanel7.setBackground(new java.awt.Color(0, 51, 51));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel5.setText("Account No. :");
        jPanel7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, -1, 20));

        MRAccountNoTF.setBackground(new java.awt.Color(23, 34, 71));
        MRAccountNoTF.setForeground(new java.awt.Color(255, 255, 255));
        MRAccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRAccountNoTFActionPerformed(evt);
            }
        });
        jPanel7.add(MRAccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 312, 30));

        MRRateTF.setBackground(new java.awt.Color(23, 34, 71));
        MRRateTF.setForeground(new java.awt.Color(255, 255, 255));
        MRRateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRRateTFActionPerformed(evt);
            }
        });
        jPanel7.add(MRRateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 310, 30));

        jLabel36.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel36.setText("Meter No. :");
        jPanel7.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, -1, 30));

        MRAccountNameTF.setBackground(new java.awt.Color(23, 34, 71));
        MRAccountNameTF.setForeground(new java.awt.Color(255, 255, 255));
        MRAccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRAccountNameTFActionPerformed(evt);
            }
        });
        jPanel7.add(MRAccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 200, 312, 30));

        jLabel6.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel6.setText("Account Name :");
        jPanel7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, -1, 30));

        jLabel1.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel1.setText("Consumption :");
        jPanel7.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 500, -1, 20));

        jLabel2.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel2.setText("Previous Reading :");
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, -1, 20));

        jLabel7.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel7.setText("Current Reading :");
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 450, -1, 20));

        jLabel8.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel8.setText("Rate :");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, -1, 20));

        MRMeterNoTF.setBackground(new java.awt.Color(23, 34, 71));
        MRMeterNoTF.setForeground(new java.awt.Color(255, 255, 255));
        MRMeterNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRMeterNoTFActionPerformed(evt);
            }
        });
        jPanel7.add(MRMeterNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, 310, 30));

        MRConsumptionTF.setBackground(new java.awt.Color(23, 34, 71));
        MRConsumptionTF.setForeground(new java.awt.Color(255, 255, 255));
        MRConsumptionTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRConsumptionTFActionPerformed(evt);
            }
        });
        jPanel7.add(MRConsumptionTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 500, 310, 30));

        MRPreviousReadingTF.setBackground(new java.awt.Color(23, 34, 71));
        MRPreviousReadingTF.setForeground(new java.awt.Color(255, 255, 255));
        MRPreviousReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRPreviousReadingTFActionPerformed(evt);
            }
        });
        jPanel7.add(MRPreviousReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 310, 30));

        MRCurrentReadingTF.setBackground(new java.awt.Color(23, 34, 71));
        MRCurrentReadingTF.setForeground(new java.awt.Color(255, 255, 255));
        MRCurrentReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRCurrentReadingTFActionPerformed(evt);
            }
        });
        jPanel7.add(MRCurrentReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, 310, 30));

        MRAddToReceipt.setBackground(new java.awt.Color(23, 34, 71));
        MRAddToReceipt.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        MRAddToReceipt.setForeground(new java.awt.Color(255, 255, 255));
        MRAddToReceipt.setText("ADD TO RECEIPT");
        MRAddToReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRAddToReceiptActionPerformed(evt);
            }
        });
        jPanel7.add(MRAddToReceipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 550, -1, 40));

        jLabel26.setFont(new java.awt.Font("MingLiU_HKSCS-ExtB", 1, 16)); // NOI18N
        jLabel26.setText("METER READING");
        jPanel7.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 240, -1));

        jLabel50.setBackground(new java.awt.Color(0, 37, 58));
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/WATER001 (1).jpg"))); // NOI18N
        jPanel7.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 700));

        jTabbedPane1.addTab("METER READING", jPanel7);

        jPanel5.setBackground(new java.awt.Color(0, 51, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel39.setText("Account No. :");
        jPanel5.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, -1, 27));

        BDAccountNoTF.setBackground(new java.awt.Color(23, 34, 71));
        BDAccountNoTF.setForeground(new java.awt.Color(255, 255, 255));
        BDAccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDAccountNoTFActionPerformed(evt);
            }
        });
        jPanel5.add(BDAccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 312, 30));

        BDAccountNameTF.setBackground(new java.awt.Color(23, 34, 71));
        BDAccountNameTF.setForeground(new java.awt.Color(255, 255, 255));
        BDAccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDAccountNameTFActionPerformed(evt);
            }
        });
        jPanel5.add(BDAccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 312, 30));

        jLabel40.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel40.setText("Account Name :");
        jPanel5.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, -1, -1));

        jLabel41.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel41.setText("Previous Reading :");
        jPanel5.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, -1, 27));

        BDPreviousReadingTF.setBackground(new java.awt.Color(23, 34, 71));
        BDPreviousReadingTF.setForeground(new java.awt.Color(255, 255, 255));
        BDPreviousReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDPreviousReadingTFActionPerformed(evt);
            }
        });
        jPanel5.add(BDPreviousReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 190, 312, 30));

        BDCurrentReadingTF.setBackground(new java.awt.Color(23, 34, 71));
        BDCurrentReadingTF.setForeground(new java.awt.Color(255, 255, 255));
        BDCurrentReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDCurrentReadingTFActionPerformed(evt);
            }
        });
        jPanel5.add(BDCurrentReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 240, 312, 30));

        jLabel42.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel42.setText("Current Reading :");
        jPanel5.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, -1, -1));

        jLabel43.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel43.setText("Consumption :");
        jPanel5.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, -1, 27));

        BDConsumptionTF.setBackground(new java.awt.Color(23, 34, 71));
        BDConsumptionTF.setForeground(new java.awt.Color(255, 255, 255));
        BDConsumptionTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDConsumptionTFActionPerformed(evt);
            }
        });
        jPanel5.add(BDConsumptionTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, 312, 30));

        BDRateTF.setBackground(new java.awt.Color(23, 34, 71));
        BDRateTF.setForeground(new java.awt.Color(255, 255, 255));
        BDRateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDRateTFActionPerformed(evt);
            }
        });
        jPanel5.add(BDRateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 360, 312, 30));

        jLabel44.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel44.setText("Rate :");
        jPanel5.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 370, -1, -1));

        jLabel45.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel45.setText("Base Charge :");
        jPanel5.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 470, -1, 27));

        BDBaseChargeTF.setBackground(new java.awt.Color(23, 34, 71));
        BDBaseChargeTF.setForeground(new java.awt.Color(255, 255, 255));
        BDBaseChargeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDBaseChargeTFActionPerformed(evt);
            }
        });
        jPanel5.add(BDBaseChargeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 470, 312, 30));

        jLabel47.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel47.setText("Penalty Charge :");
        jPanel5.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 520, -1, 27));

        BDPenaltyChargeTF.setBackground(new java.awt.Color(23, 34, 71));
        BDPenaltyChargeTF.setForeground(new java.awt.Color(255, 255, 255));
        BDPenaltyChargeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDPenaltyChargeTFActionPerformed(evt);
            }
        });
        jPanel5.add(BDPenaltyChargeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 520, 312, 30));

        BDTaxAmountTF.setBackground(new java.awt.Color(23, 34, 71));
        BDTaxAmountTF.setForeground(new java.awt.Color(255, 255, 255));
        BDTaxAmountTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDTaxAmountTFActionPerformed(evt);
            }
        });
        jPanel5.add(BDTaxAmountTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 420, 312, 30));

        jLabel56.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel56.setText("Tax :");
        jPanel5.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, -1, -1));

        BDAddToReceipt.setBackground(new java.awt.Color(23, 34, 71));
        BDAddToReceipt.setFont(new java.awt.Font("Lucida Sans Typewriter", 1, 12)); // NOI18N
        BDAddToReceipt.setForeground(new java.awt.Color(255, 255, 255));
        BDAddToReceipt.setText("ADD TO RECEIPT");
        BDAddToReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDAddToReceiptActionPerformed(evt);
            }
        });
        jPanel5.add(BDAddToReceipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 560, -1, 30));

        jLabel27.setFont(new java.awt.Font("MingLiU_HKSCS-ExtB", 1, 14)); // NOI18N
        jLabel27.setText("BILLING DETAILS");
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 240, -1));

        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/WATER001 (1).jpg"))); // NOI18N
        jPanel5.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 700));

        jTabbedPane1.addTab("BILLING DETAILS", jPanel5);

        jPanel6.setBackground(new java.awt.Color(0, 51, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel51.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel51.setText("Account No. :");
        jPanel6.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, 27));

        BSAccountNoTF.setBackground(new java.awt.Color(23, 34, 71));
        BSAccountNoTF.setForeground(new java.awt.Color(255, 255, 255));
        BSAccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSAccountNoTFActionPerformed(evt);
            }
        });
        jPanel6.add(BSAccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 312, 30));

        BSAccountNameTF.setBackground(new java.awt.Color(23, 34, 71));
        BSAccountNameTF.setForeground(new java.awt.Color(255, 255, 255));
        BSAccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSAccountNameTFActionPerformed(evt);
            }
        });
        jPanel6.add(BSAccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, 312, 30));

        jLabel52.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel52.setText("Account Name :");
        jPanel6.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, -1, -1));

        jLabel53.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel53.setText("Billing Period Start :");
        jPanel6.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, -1, 27));

        BSBillingPeriodStartTF.setBackground(new java.awt.Color(23, 34, 71));
        BSBillingPeriodStartTF.setForeground(new java.awt.Color(255, 255, 255));
        BSBillingPeriodStartTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSBillingPeriodStartTFActionPerformed(evt);
            }
        });
        jPanel6.add(BSBillingPeriodStartTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 260, 312, 30));

        BSBillingPeriodEndTF.setBackground(new java.awt.Color(23, 34, 71));
        BSBillingPeriodEndTF.setForeground(new java.awt.Color(255, 255, 255));
        BSBillingPeriodEndTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSBillingPeriodEndTFActionPerformed(evt);
            }
        });
        jPanel6.add(BSBillingPeriodEndTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 320, 312, 30));

        jLabel54.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel54.setText("Billing Period End :");
        jPanel6.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, -1, -1));

        jLabel55.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel55.setText("Total Amount Due :");
        jPanel6.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, -1, 27));

        BSTotalAmountDueTF.setBackground(new java.awt.Color(23, 34, 71));
        BSTotalAmountDueTF.setForeground(new java.awt.Color(255, 255, 255));
        BSTotalAmountDueTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSTotalAmountDueTFActionPerformed(evt);
            }
        });
        jPanel6.add(BSTotalAmountDueTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 450, 312, 30));

        jLabel68.setFont(new java.awt.Font("OCR A Extended", 0, 18)); // NOI18N
        jLabel68.setText("Due Date :");
        jPanel6.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, -1, 27));

        BSDueDateTF.setBackground(new java.awt.Color(23, 34, 71));
        BSDueDateTF.setForeground(new java.awt.Color(255, 255, 255));
        BSDueDateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSDueDateTFActionPerformed(evt);
            }
        });
        jPanel6.add(BSDueDateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, 312, 30));

        BSAddToReceipt.setBackground(new java.awt.Color(23, 34, 71));
        BSAddToReceipt.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        BSAddToReceipt.setForeground(new java.awt.Color(255, 255, 255));
        BSAddToReceipt.setText("ADD TO RECEIPT");
        BSAddToReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSAddToReceiptActionPerformed(evt);
            }
        });
        jPanel6.add(BSAddToReceipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 500, -1, 30));

        jLabel28.setFont(new java.awt.Font("MingLiU_HKSCS-ExtB", 1, 16)); // NOI18N
        jLabel28.setText("BILLING SUMMARY");
        jPanel6.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 240, -1));

        jLabel77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/WATER001 (1).jpg"))); // NOI18N
        jPanel6.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 700));

        jTabbedPane1.addTab("BILLING SUMMARY", jPanel6);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel46.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel46.setText("Account No. :");
        jPanel1.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, 27));

        GRAccountNoTF.setBackground(new java.awt.Color(23, 34, 71));
        GRAccountNoTF.setForeground(new java.awt.Color(255, 255, 255));
        GRAccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRAccountNoTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRAccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 312, 30));

        GRAccountNameTF.setBackground(new java.awt.Color(23, 34, 71));
        GRAccountNameTF.setForeground(new java.awt.Color(255, 255, 255));
        GRAccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRAccountNameTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRAccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 312, 30));

        jLabel48.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel48.setText("Account Name :");
        jPanel1.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        jLabel49.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel49.setText("Previous Reading :");
        jPanel1.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, -1, 27));

        GRPreviousReadingTF.setBackground(new java.awt.Color(23, 34, 71));
        GRPreviousReadingTF.setForeground(new java.awt.Color(255, 255, 255));
        GRPreviousReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRPreviousReadingTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRPreviousReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 312, 30));

        GRCurrentReadingTF.setBackground(new java.awt.Color(23, 34, 71));
        GRCurrentReadingTF.setForeground(new java.awt.Color(255, 255, 255));
        GRCurrentReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRCurrentReadingTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRCurrentReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, 312, 30));

        jLabel57.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel57.setText("Current Reading :");
        jPanel1.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, -1, -1));

        jLabel58.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel58.setText("Consumption :");
        jPanel1.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, -1, 27));

        GRConsumptionTF.setBackground(new java.awt.Color(23, 34, 71));
        GRConsumptionTF.setForeground(new java.awt.Color(255, 255, 255));
        GRConsumptionTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRConsumptionTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRConsumptionTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, 312, 30));

        GRRateTF.setBackground(new java.awt.Color(23, 34, 71));
        GRRateTF.setForeground(new java.awt.Color(255, 255, 255));
        GRRateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRRateTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRRateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 312, 30));

        jLabel59.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel59.setText("Rate :");
        jPanel1.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        jLabel60.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel60.setText("Base Charge :");
        jPanel1.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, -1, 27));

        GRBaseChargeTF.setBackground(new java.awt.Color(23, 34, 71));
        GRBaseChargeTF.setForeground(new java.awt.Color(255, 255, 255));
        GRBaseChargeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRBaseChargeTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRBaseChargeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 440, 312, 30));

        jLabel61.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel61.setText("Penalty Charge :");
        jPanel1.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 480, -1, 27));

        GRPenaltyChargeTF.setBackground(new java.awt.Color(23, 34, 71));
        GRPenaltyChargeTF.setForeground(new java.awt.Color(255, 255, 255));
        GRPenaltyChargeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRPenaltyChargeTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRPenaltyChargeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 480, 312, 30));

        GRDueDateTF.setBackground(new java.awt.Color(23, 34, 71));
        GRDueDateTF.setForeground(new java.awt.Color(255, 255, 255));
        GRDueDateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRDueDateTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRDueDateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 600, 312, 30));

        jLabel62.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel62.setText("Due Date:");
        jPanel1.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 610, -1, -1));

        GRTaxAmountTF.setBackground(new java.awt.Color(23, 34, 71));
        GRTaxAmountTF.setForeground(new java.awt.Color(255, 255, 255));
        GRTaxAmountTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRTaxAmountTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRTaxAmountTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, 312, 30));

        jLabel63.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel63.setText("Tax :");
        jPanel1.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, -1, -1));

        GRPropertyTF.setBackground(new java.awt.Color(23, 34, 71));
        GRPropertyTF.setForeground(new java.awt.Color(255, 255, 255));
        GRPropertyTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRPropertyTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRPropertyTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 312, 30));

        jLabel64.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel64.setText("Meter No. :");
        jPanel1.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, -1));

        jLabel65.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel65.setText("Billing Period Start :");
        jPanel1.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, -1, 27));

        GRBillingPeriodStartTF.setBackground(new java.awt.Color(23, 34, 71));
        GRBillingPeriodStartTF.setForeground(new java.awt.Color(255, 255, 255));
        GRBillingPeriodStartTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRBillingPeriodStartTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRBillingPeriodStartTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 520, 312, 30));

        GRBillingPeriodEndTF.setBackground(new java.awt.Color(23, 34, 71));
        GRBillingPeriodEndTF.setForeground(new java.awt.Color(255, 255, 255));
        GRBillingPeriodEndTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRBillingPeriodEndTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRBillingPeriodEndTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 560, 312, 30));

        jLabel66.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel66.setText("Billing Period End :");
        jPanel1.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, -1, -1));

        jLabel67.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel67.setText("Total Amount Due :");
        jPanel1.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 640, -1, 27));

        GRTotalAmountDueTF.setBackground(new java.awt.Color(23, 34, 71));
        GRTotalAmountDueTF.setForeground(new java.awt.Color(255, 255, 255));
        GRTotalAmountDueTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRTotalAmountDueTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRTotalAmountDueTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 640, 312, 30));

        GRBT.setBackground(new java.awt.Color(23, 34, 71));
        GRBT.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        GRBT.setForeground(new java.awt.Color(255, 255, 255));
        GRBT.setText("GENERATE RECEIPT");
        GRBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRBTActionPerformed(evt);
            }
        });
        jPanel1.add(GRBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 620, -1, 30));

        jLabel69.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel69.setText("Service Area :");
        jPanel1.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, -1, -1));

        GRServiceAddressTF.setBackground(new java.awt.Color(23, 34, 71));
        GRServiceAddressTF.setColumns(20);
        GRServiceAddressTF.setForeground(new java.awt.Color(255, 255, 255));
        GRServiceAddressTF.setRows(5);
        jScrollPane1.setViewportView(GRServiceAddressTF);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 310, 30));

        jLabel70.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel70.setText("Property :");
        jPanel1.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, -1));

        GRMeterNoTF.setBackground(new java.awt.Color(23, 34, 71));
        GRMeterNoTF.setForeground(new java.awt.Color(255, 255, 255));
        GRMeterNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRMeterNoTFActionPerformed(evt);
            }
        });
        jPanel1.add(GRMeterNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, 312, 30));

        jPanel11.setBackground(new java.awt.Color(23, 34, 71));

        GRReceipt.setColumns(20);
        GRReceipt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        GRReceipt.setRows(5);
        jScrollPane7.setViewportView(GRReceipt);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, 360, -1));

        jLabel29.setFont(new java.awt.Font("MingLiU_HKSCS-ExtB", 1, 16)); // NOI18N
        jLabel29.setText("GENERATE RECEIPT");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 240, -1));

        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/WATER001 (1).jpg"))); // NOI18N
        jPanel1.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 700));

        jTabbedPane1.addTab("GENERATE RECEIPT", jPanel1);

        jPanel3.setBackground(new java.awt.Color(0, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(23, 34, 71));
        jButton1.setFont(new java.awt.Font("Sitka Text", 0, 13)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SEND");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 420, -1, 30));

        jLabel71.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel71.setText("Account No. :");
        jPanel3.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, 27));

        PAccountNoTF.setBackground(new java.awt.Color(23, 34, 71));
        PAccountNoTF.setForeground(new java.awt.Color(255, 255, 255));
        PAccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PAccountNoTFActionPerformed(evt);
            }
        });
        jPanel3.add(PAccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 312, 30));

        PAccountNameTF.setBackground(new java.awt.Color(23, 34, 71));
        PAccountNameTF.setForeground(new java.awt.Color(255, 255, 255));
        PAccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PAccountNameTFActionPerformed(evt);
            }
        });
        jPanel3.add(PAccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 312, 30));

        jLabel72.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel72.setText("Account Name :");
        jPanel3.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jLabel73.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel73.setText("Penalty Charge :");
        jPanel3.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, 27));

        PPaymentDateTF.setBackground(new java.awt.Color(23, 34, 71));
        PPaymentDateTF.setForeground(new java.awt.Color(255, 255, 255));
        PPaymentDateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PPaymentDateTFActionPerformed(evt);
            }
        });
        jPanel3.add(PPaymentDateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 312, 30));

        PaymentMethodCB.setBackground(new java.awt.Color(23, 34, 71));
        PaymentMethodCB.setForeground(new java.awt.Color(255, 255, 255));
        PaymentMethodCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "E-Payment" }));
        PaymentMethodCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentMethodCBActionPerformed(evt);
            }
        });
        jPanel3.add(PaymentMethodCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 310, 30));

        jButton2.setBackground(new java.awt.Color(23, 34, 71));
        jButton2.setFont(new java.awt.Font("Sitka Text", 0, 13)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Add to Receipt");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 380, -1, 30));

        jLabel76.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel76.setText("Payment Method :");
        jPanel3.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        jPanel10.setBackground(new java.awt.Color(23, 34, 71));

        PArea.setColumns(20);
        PArea.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PArea.setRows(5);
        jScrollPane8.setViewportView(PArea);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, -1, 320));

        PTotalAmountDueTF1.setBackground(new java.awt.Color(23, 34, 71));
        PTotalAmountDueTF1.setForeground(new java.awt.Color(255, 255, 255));
        PTotalAmountDueTF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PTotalAmountDueTF1ActionPerformed(evt);
            }
        });
        jPanel3.add(PTotalAmountDueTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 312, 30));

        jLabel74.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel74.setText("Total Amount Due :");
        jPanel3.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, 27));

        jLabel82.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel82.setText("Payment Date :");
        jPanel3.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, -1, 27));

        PPenaltyChargeTF.setBackground(new java.awt.Color(23, 34, 71));
        PPenaltyChargeTF.setForeground(new java.awt.Color(255, 255, 255));
        PPenaltyChargeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PPenaltyChargeTFActionPerformed(evt);
            }
        });
        jPanel3.add(PPenaltyChargeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 312, 30));

        jLabel31.setFont(new java.awt.Font("MingLiU_HKSCS-ExtB", 1, 16)); // NOI18N
        jLabel31.setText("PAYMENT");
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 240, 20));

        jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/WATER001 (1).jpg"))); // NOI18N
        jPanel3.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 700));

        jTabbedPane1.addTab("PAYMENT ", jPanel3);

        jPanel9.setBackground(new java.awt.Color(0, 51, 51));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CLTable.setBackground(new java.awt.Color(102, 157, 210));
        CLTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Account No", "Account Name", "Service Area", "Property", "Meter No", "Previous Reading", "Current Reading", "Consumption", "Rate", "Tax", "Base Charge", "Penalty Charge", "Billing Period Start", "Billing Period End", "Due Date", "Total Amount Due"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        CLTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        CLTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CLTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(CLTable);
        if (CLTable.getColumnModel().getColumnCount() > 0) {
            CLTable.getColumnModel().getColumn(0).setResizable(false);
            CLTable.getColumnModel().getColumn(1).setResizable(false);
            CLTable.getColumnModel().getColumn(2).setResizable(false);
            CLTable.getColumnModel().getColumn(3).setResizable(false);
            CLTable.getColumnModel().getColumn(4).setResizable(false);
            CLTable.getColumnModel().getColumn(5).setResizable(false);
            CLTable.getColumnModel().getColumn(6).setResizable(false);
            CLTable.getColumnModel().getColumn(7).setResizable(false);
            CLTable.getColumnModel().getColumn(8).setResizable(false);
            CLTable.getColumnModel().getColumn(9).setResizable(false);
            CLTable.getColumnModel().getColumn(10).setResizable(false);
            CLTable.getColumnModel().getColumn(11).setResizable(false);
            CLTable.getColumnModel().getColumn(12).setResizable(false);
            CLTable.getColumnModel().getColumn(13).setResizable(false);
            CLTable.getColumnModel().getColumn(14).setResizable(false);
            CLTable.getColumnModel().getColumn(15).setResizable(false);
        }

        jPanel9.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 1110, 410));

        DisplayClientsBT.setBackground(new java.awt.Color(23, 34, 71));
        DisplayClientsBT.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        DisplayClientsBT.setForeground(new java.awt.Color(255, 255, 255));
        DisplayClientsBT.setText("DISPLAY CLIENTS");
        DisplayClientsBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisplayClientsBTActionPerformed(evt);
            }
        });
        jPanel9.add(DisplayClientsBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, 40));

        CLSearchTF.setBackground(new java.awt.Color(23, 34, 71));
        CLSearchTF.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        CLSearchTF.setForeground(new java.awt.Color(255, 255, 255));
        CLSearchTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLSearchTFActionPerformed(evt);
            }
        });
        jPanel9.add(CLSearchTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 160, 30));

        CLSearchBT.setBackground(new java.awt.Color(23, 34, 71));
        CLSearchBT.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        CLSearchBT.setForeground(new java.awt.Color(255, 255, 255));
        CLSearchBT.setText("SEARCH");
        CLSearchBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLSearchBTActionPerformed(evt);
            }
        });
        jPanel9.add(CLSearchBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, -1, 40));

        CLDeleteBT.setBackground(new java.awt.Color(23, 34, 71));
        CLDeleteBT.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        CLDeleteBT.setForeground(new java.awt.Color(255, 255, 255));
        CLDeleteBT.setText("DELETE");
        CLDeleteBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLDeleteBTActionPerformed(evt);
            }
        });
        jPanel9.add(CLDeleteBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, -1, 40));

        CLDeleteAllBT.setBackground(new java.awt.Color(23, 34, 71));
        CLDeleteAllBT.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        CLDeleteAllBT.setForeground(new java.awt.Color(255, 255, 255));
        CLDeleteAllBT.setText("DELETE ALL");
        CLDeleteAllBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLDeleteAllBTActionPerformed(evt);
            }
        });
        jPanel9.add(CLDeleteAllBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, -1, 40));

        jLabel35.setFont(new java.awt.Font("MingLiU_HKSCS-ExtB", 1, 16)); // NOI18N
        jLabel35.setText("CLIENTS LEDGER");
        jPanel9.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 240, -1));

        jLabel80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/WATER001 (1).jpg"))); // NOI18N
        jPanel9.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 700));

        jTabbedPane1.addTab("CLIENTS LEDGER", jPanel9);

        jPanel4.setBackground(new java.awt.Color(0, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N

        SalesTable.setBackground(new java.awt.Color(102, 157, 210));
        SalesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Payment Date", "Total Amount Due"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(SalesTable);
        if (SalesTable.getColumnModel().getColumnCount() > 0) {
            SalesTable.getColumnModel().getColumn(0).setResizable(false);
            SalesTable.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 172, 713, 316));

        SCalculateViewSalesBT.setBackground(new java.awt.Color(23, 34, 71));
        SCalculateViewSalesBT.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        SCalculateViewSalesBT.setForeground(new java.awt.Color(255, 255, 255));
        SCalculateViewSalesBT.setText("VIEW SALES");
        SCalculateViewSalesBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SCalculateViewSalesBTActionPerformed(evt);
            }
        });
        jPanel4.add(SCalculateViewSalesBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, -1, -1));

        SearchSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchSalesActionPerformed(evt);
            }
        });
        jPanel4.add(SearchSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 130, 160, -1));

        TotalSalesTF.setBackground(new java.awt.Color(23, 34, 71));
        TotalSalesTF.setForeground(new java.awt.Color(255, 255, 255));
        TotalSalesTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalSalesTFActionPerformed(evt);
            }
        });
        jPanel4.add(TotalSalesTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 520, 170, 40));

        jLabel37.setFont(new java.awt.Font("MingLiU_HKSCS-ExtB", 1, 16)); // NOI18N
        jLabel37.setText("SALES");
        jPanel4.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, 240, -1));

        jLabel81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/WATER001 (1).jpg"))); // NOI18N
        jPanel4.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 700));

        jTabbedPane1.addTab("SALES", jPanel4);

        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RateMaintenanceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Rate Name", "Base Charge", "Rate per m³", "Penalty Charge", "Effective Date", "Expiration Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(RateMaintenanceTable);
        if (RateMaintenanceTable.getColumnModel().getColumnCount() > 0) {
            RateMaintenanceTable.getColumnModel().getColumn(0).setResizable(false);
            RateMaintenanceTable.getColumnModel().getColumn(1).setResizable(false);
            RateMaintenanceTable.getColumnModel().getColumn(2).setResizable(false);
            RateMaintenanceTable.getColumnModel().getColumn(3).setResizable(false);
            RateMaintenanceTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            RateMaintenanceTable.getColumnModel().getColumn(4).setResizable(false);
            RateMaintenanceTable.getColumnModel().getColumn(4).setPreferredWidth(35);
            RateMaintenanceTable.getColumnModel().getColumn(5).setResizable(false);
            RateMaintenanceTable.getColumnModel().getColumn(5).setPreferredWidth(35);
        }

        jPanel14.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 800, 180));

        jTabbedPane1.addTab("RATE MAINTENANCE", jPanel14);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, -40, 1190, 730));

        jPanel13.setBackground(new java.awt.Color(23, 34, 71));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setBackground(new java.awt.Color(102, 157, 210));
        jButton3.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/home (2).png"))); // NOI18N
        jButton3.setText("DASHBOARD");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jButton4.setBackground(new java.awt.Color(102, 157, 210));
        jButton4.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/information (1).png"))); // NOI18N
        jButton4.setText("SERVICE INFO");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 160, -1));

        jButton5.setBackground(new java.awt.Color(102, 157, 210));
        jButton5.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/magnifying-glass (1).png"))); // NOI18N
        jButton5.setText("METER READING");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 160, -1));

        jButton6.setBackground(new java.awt.Color(102, 157, 210));
        jButton6.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/detail (1).png"))); // NOI18N
        jButton6.setText("BILLING DETAILS");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 160, -1));

        jButton7.setBackground(new java.awt.Color(102, 157, 210));
        jButton7.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Downloads\\bill (1).png")); // NOI18N
        jButton7.setText("BILLING SUMMARY");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 160, 30));

        jButton8.setBackground(new java.awt.Color(102, 157, 210));
        jButton8.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/receipt (1).png"))); // NOI18N
        jButton8.setText("GENERATE RECEIPT");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 160, -1));

        jButton9.setBackground(new java.awt.Color(102, 157, 210));
        jButton9.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/operation (1).png"))); // NOI18N
        jButton9.setText("PAYMENT");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 392, 160, 30));

        jButton10.setBackground(new java.awt.Color(102, 157, 210));
        jButton10.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/ledgers (1).png"))); // NOI18N
        jButton10.setText("CLIENT LEDGER");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 160, 30));

        jButton11.setBackground(new java.awt.Color(102, 157, 210));
        jButton11.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/sales (1).png"))); // NOI18N
        jButton11.setText("SALES");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 160, 30));

        LogoutBT.setBackground(new java.awt.Color(102, 157, 210));
        LogoutBT.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        LogoutBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/logout (1).png"))); // NOI18N
        LogoutBT.setText("Log out");
        LogoutBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutBTActionPerformed(evt);
            }
        });
        jPanel13.add(LogoutBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 630, -1, -1));

        jButton12.setBackground(new java.awt.Color(102, 157, 210));
        jButton12.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/waterbillingsystemrjc/sales (1).png"))); // NOI18N
        jButton12.setText("Rate maintenance");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 160, 30));

        getContentPane().add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 690));

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
            
        DefaultTableModel model = (DefaultTableModel) ClientInfoTable.getModel();
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
        int response = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to update the client details for Account No: " + accountNo + "?",
        "Confirm Update",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
    );

         if (response == JOptionPane.YES_OPTION) {
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

           
            DefaultTableModel model = (DefaultTableModel) ClientInfoTable.getModel();
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

         } else {
        
        JOptionPane.showMessageDialog(this, "Client details update canceled.");
    }
    } catch (SQLException | NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Error updating client details: " + ex.getMessage());
    }
}

    private int getRowIndexByAccountNo(String accountNo) {
    DefaultTableModel model = (DefaultTableModel) ClientInfoTable.getModel();
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
        pst.setString(1, accountNo); 
     
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

        int response = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to create the account?", 
        "Confirm Account Creation",
        JOptionPane.YES_NO_OPTION, 
        JOptionPane.QUESTION_MESSAGE 
    );

    
    if (response == JOptionPane.YES_OPTION) {
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

            DefaultTableModel model = (DefaultTableModel) ClientInfoTable.getModel();
            model.addRow(new Object[] {
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
        } else {
            JOptionPane.showMessageDialog(this, "Record Failed to Save!", "Error", JOptionPane.ERROR_MESSAGE);
        }
         } else {
        
        JOptionPane.showMessageDialog(this, "Account creation cancelled.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
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
        MRPreviousReadingTF.requestFocus();
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
        BDTaxAmountTF.requestFocus();
    }//GEN-LAST:event_MRConsumptionTFActionPerformed

    private void MRPreviousReadingTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRPreviousReadingTFActionPerformed
        MRCurrentReadingTF.requestFocus();
    }//GEN-LAST:event_MRPreviousReadingTFActionPerformed

    private void MRCurrentReadingTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRCurrentReadingTFActionPerformed
        MRConsumptionTF.requestFocus();
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
       BSBillingPeriodStartTF.requestFocus();
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
        
        BSTotalAmountDueTF.requestFocus();
    }//GEN-LAST:event_BSBillingPeriodStartTFActionPerformed

    private void BSBillingPeriodEndTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSBillingPeriodEndTFActionPerformed
        updateDueDate();
    }//GEN-LAST:event_BSBillingPeriodEndTFActionPerformed

    private void BSTotalAmountDueTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSTotalAmountDueTFActionPerformed
        try {
        String consumptionText = BDConsumptionTF.getText().trim();
        String rateText = BDRateTF.getText().trim();
        String baseChargeText = BDBaseChargeTF.getText().trim();
        String taxAmountText = BDTaxAmountTF.getText().trim();
        String penaltyChargeText = BDPenaltyChargeTF.getText().trim();

        if (consumptionText.isEmpty() || rateText.isEmpty() || baseChargeText.isEmpty() || taxAmountText.isEmpty() || penaltyChargeText.isEmpty()) {
            BSTotalAmountDueTF.setText(""); 
            return;
        }

        BigDecimal consumption = new BigDecimal(consumptionText);
        BigDecimal rate = new BigDecimal(rateText);
        BigDecimal baseCharge = new BigDecimal(baseChargeText);
        BigDecimal taxAmount = new BigDecimal(taxAmountText);
        BigDecimal penaltyCharge = new BigDecimal(penaltyChargeText);

        BigDecimal consumptionAmount = consumption.multiply(rate);
        BigDecimal totalAmountDue = consumptionAmount.add(baseCharge)
                                                  .add(taxAmount)
                                                  .add(penaltyCharge);

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
    double ratePerCubicMeter = 0.0;
    double baseCharge = 0.0;

    try {
        if (selectedProperty == null || selectedProperty.isEmpty()) {
            return;
        }

        try (Connection conn = getConnection()) {
            String query = "SELECT Rate_Per_Cubic_Meter, Base_Charge FROM ratemaintenance WHERE Rate_Name = ?";

            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, selectedProperty);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    ratePerCubicMeter = rs.getDouble("Rate_Per_Cubic_Meter");
                    baseCharge = rs.getDouble("Base_Charge");
                    MRRateTF.setText(String.valueOf(ratePerCubicMeter));
                    BDBaseChargeTF.setText(String.valueOf(baseCharge));
                } else {
                    MRRateTF.setText("0.00");
                    BDBaseChargeTF.setText("0.00");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database connection or query error: " + e.getMessage());
        }

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

    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }

    }//GEN-LAST:event_SIPropertyCBActionPerformed

    private void BDTaxAmountTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDTaxAmountTFActionPerformed
        BDBaseChargeTF.requestFocus();
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
        JOptionPane.showMessageDialog(this, 
        "Data successfully added to your receipt.", 
        "Success", 
        JOptionPane.INFORMATION_MESSAGE);
   
    }//GEN-LAST:event_MRAddToReceiptActionPerformed
   

    private void BDAddToReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDAddToReceiptActionPerformed
    
        syncBDDataToBSandGR();
            JOptionPane.showMessageDialog(this, 
        "Data successfully added to your receipt.", 
        "Success", 
        JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_BDAddToReceiptActionPerformed

    private void BSAddToReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSAddToReceiptActionPerformed
         syncBSDataToGR();
              JOptionPane.showMessageDialog(this, 
        "Data successfully added to your receipt.", 
        "Success", 
        JOptionPane.INFORMATION_MESSAGE);
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
     int confirmation = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to generate the receipt for Account No: " + accountNo + "?", 
            "Confirm Receipt Generation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

    if (confirmation != JOptionPane.YES_OPTION) {
        return;
    }

    try (Connection conn1 = DBConnection.getConnection(); 
         Connection conn2 = DBConnection2.getConnection()) { // Assuming DBConnection2 is the second DB connection

        String checkAccountQuery = "SELECT COUNT(*) FROM generatedreceipt WHERE Account_No = ? AND Billing_Period_Start = ? AND Billing_Period_End = ? AND Due_Date = ?";

        try (PreparedStatement preparedStatementCheck = conn1.prepareStatement(checkAccountQuery)) {
            preparedStatementCheck.setString(1, accountNo);
            preparedStatementCheck.setDate(2, sqlDateStart);
            preparedStatementCheck.setDate(3, sqlDateEnd);
            preparedStatementCheck.setDate(4, sqlDateStart);

            ResultSet rs = preparedStatementCheck.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "Account with the same billing period and due date already exists in the database.", "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Prepare SQL for inserting into both databases
            String sqlGeneratedReceipt = "INSERT INTO generatedreceipt "
                                       + "(Account_No, Account_Name, Service_Address, Property, Meter_No, Previous_Reading, Current_Reading, Consumption, Rate, Tax_Amount, Base_Charge, Penalty_Charge, Billing_Period_Start, Billing_Period_End, Due_Date, Total_Amount_Due) "
                                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            String sqlClientLedger = "INSERT INTO clientledger "
                                   + "(Account_No, Account_Name, Service_Address, Property, Meter_No, Previous_Reading, Current_Reading, Consumption, Rate, Tax_Amount, Base_Charge, Penalty_Charge, Billing_Period_Start, Billing_Period_End, Due_Date, Total_Amount_Due) "
                                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement1 = conn1.prepareStatement(sqlGeneratedReceipt);
                 PreparedStatement preparedStatement2 = conn2.prepareStatement(sqlClientLedger)) {

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

                // Set parameters for the first database connection (conn1 - for generatedreceipt)
                preparedStatement1.setString(1, accountNo);
                preparedStatement1.setString(2, accountName);
                preparedStatement1.setString(3, serviceAddress);
                preparedStatement1.setString(4, property);
                preparedStatement1.setString(5, meterNo);
                preparedStatement1.setBigDecimal(6, previousReading);
                preparedStatement1.setBigDecimal(7, currentReading);
                preparedStatement1.setBigDecimal(8, consumption);
                preparedStatement1.setBigDecimal(9, rate);
                preparedStatement1.setBigDecimal(10, taxAmount);
                preparedStatement1.setBigDecimal(11, baseCharge);
                preparedStatement1.setBigDecimal(12, penaltyCharge);
                preparedStatement1.setDate(13, sqlDateStart);
                preparedStatement1.setDate(14, sqlDateEnd);
                preparedStatement1.setDate(15, dueDate);
                preparedStatement1.setBigDecimal(16, totalAmountDue);

                // Set parameters for the second database connection (conn2 - for clientledger)
                preparedStatement2.setString(1, accountNo);
                preparedStatement2.setString(2, accountName);
                preparedStatement2.setString(3, serviceAddress);
                preparedStatement2.setString(4, property);
                preparedStatement2.setString(5, meterNo);
                preparedStatement2.setBigDecimal(6, previousReading);
                preparedStatement2.setBigDecimal(7, currentReading);
                preparedStatement2.setBigDecimal(8, consumption);
                preparedStatement2.setBigDecimal(9, rate);
                preparedStatement2.setBigDecimal(10, taxAmount);
                preparedStatement2.setBigDecimal(11, baseCharge);
                preparedStatement2.setBigDecimal(12, penaltyCharge);
                preparedStatement2.setDate(13, sqlDateStart);
                preparedStatement2.setDate(14, sqlDateEnd);
                preparedStatement2.setDate(15, dueDate);
                preparedStatement2.setBigDecimal(16, totalAmountDue);

                // Execute the insertion for both databases
                int rowsInserted1 = preparedStatement1.executeUpdate();
                int rowsInserted2 = preparedStatement2.executeUpdate();

                // Check if data was inserted into both databases
                if (rowsInserted1 > 0 && rowsInserted2 > 0) {
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
                    
                    JOptionPane.showMessageDialog(null, "Receipt has been successfully sent to the client!", "Receipt Sent", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to generate receipt.");
                }
            }
        }
    }
} catch (ParseException ex) {
    JOptionPane.showMessageDialog(null, "The text fields should be filled all.", "Error", JOptionPane.ERROR_MESSAGE);
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  JOptionPane.showMessageDialog(this, 
        "You successfully sent it to the client.", 
        "Message Sent", 
        JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void PAccountNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PAccountNoTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PAccountNoTFActionPerformed

    private void PAccountNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PAccountNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PAccountNameTFActionPerformed

    private void PPaymentDateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PPaymentDateTFActionPerformed
                                                
    String paymentDateInput = PPaymentDateTF.getText().trim();

// Date format to parse the input
SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
dateFormat.setLenient(false);

try {
    // Parse the payment date entered by the user
    java.util.Date utilPaymentDate = dateFormat.parse(paymentDateInput);
    java.sql.Date sqlPaymentDate = new java.sql.Date(utilPaymentDate.getTime());

    // Retrieve the Billing Period End and Total Amount Due from the database
    String accountNo = PAccountNoTF.getText(); // Assuming Account No is provided
    String query = "SELECT Billing_Period_End, Total_Amount_Due FROM generatedreceipt WHERE Account_No = ?";

    // Initialize variables
    double penaltyCharge = 0.0;

    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_user_database", "root", "")) {
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, accountNo);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            java.sql.Date billingPeriodEnd = rs.getDate("Billing_Period_End");
            double totalAmountDue = rs.getDouble("Total_Amount_Due"); // Get Total Amount Due from database

            // Calculate Due Date (Billing Period End + 5 days)
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(billingPeriodEnd);
            calendar.add(Calendar.DAY_OF_MONTH, 5);
            java.sql.Date dueDate = new java.sql.Date(calendar.getTimeInMillis());

            // Compare Payment Date with Due Date
            if (sqlPaymentDate.after(dueDate)) { // Payment is after due date
                // Calculate penalty as 10% of Total Amount Due (fixed 10% penalty)
                penaltyCharge = totalAmountDue * 0.10;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Account not found in the database.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Set the calculated penalty charge in the penalty charge text field
        PPenaltyChargeTF.setText(String.format("%.2f", penaltyCharge));
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
} catch (ParseException e) {
    JOptionPane.showMessageDialog(this, "Invalid date format. Please use MM/dd/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
}




    }//GEN-LAST:event_PPaymentDateTFActionPerformed
    private boolean isReceiptAdded = false;
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    if (isReceiptAdded) {
        JOptionPane.showMessageDialog(this, "Receipt has already been added.", "Info", JOptionPane.INFORMATION_MESSAGE);
        return; 
    }

    String paymentDateInput = PPaymentDateTF.getText().trim(); 
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    dateFormat.setLenient(false); 

    try {
        java.util.Date utilDate = dateFormat.parse(paymentDateInput);
        java.sql.Date sqlPaymentDate = new java.sql.Date(utilDate.getTime());

        String accountNo = PAccountNoTF.getText();
        String accountName = PAccountNameTF.getText();
        String penaltyChargeText = PPenaltyChargeTF.getText().trim();  // Penalty charge from text field
        String totalAmountDue = PTotalAmountDueTF1.getText().trim();
        String paymentMethod = PaymentMethodCB.getSelectedItem().toString();

        // Ensure penalty charge is a valid number
        double penaltyCharge = 0.0;
        if (!penaltyChargeText.isEmpty()) {
            try {
                penaltyCharge = Double.parseDouble(penaltyChargeText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid penalty charge value.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        int confirmation = JOptionPane.showConfirmDialog(this, 
            "Are you sure the data is correct?\nAccount No: " + accountNo + 
            "\nAccount Name: " + accountName + 
            "\nPenalty Charge: " + penaltyCharge +
            "\nTotal Amount Due: " + totalAmountDue + 
            "\nPayment Method: " + paymentMethod + 
            "\nPayment Date: " + paymentDateInput, 
            "Confirm Payment Data", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        String dbUrl = "jdbc:mysql://localhost:3306/java_user_database";
        String dbUser = "root";
        String dbPassword = "";

        String query1 = "INSERT INTO clientpayment (Account_No, Account_Name, Penalty_Charge, Total_Amount_Due, Payment_Method, Payment_Date) VALUES (?, ?, ?, ?, ?, ?)";
        String query2 = "INSERT INTO clientpaymentsales (Account_No, Account_Name, Total_Amount_Due, Payment_Method, Payment_Date) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            
            PreparedStatement pst1 = con.prepareStatement(query1);
            pst1.setString(1, accountNo);
            pst1.setString(2, accountName);
            pst1.setDouble(3, penaltyCharge);  // Set penalty charge in database
            pst1.setDouble(4, Double.parseDouble(totalAmountDue));  // Convert Total Amount Due to Double
            pst1.setString(5, paymentMethod);
            pst1.setDate(6, sqlPaymentDate);

            PreparedStatement pst2 = con.prepareStatement(query2);
            pst2.setString(1, accountNo);
            pst2.setString(2, accountName);
            pst2.setDouble(3, Double.parseDouble(totalAmountDue));  // Set total amount due
            pst2.setString(4, paymentMethod);
            pst2.setDate(5, sqlPaymentDate);

            int rowsAffected1 = pst1.executeUpdate();
            int rowsAffected2 = pst2.executeUpdate();

            if (rowsAffected1 > 0 && rowsAffected2 > 0) {
                String receipt = "\n==================== RECEIPT ====================\n";
                receipt += "Account No: " + accountNo + "\n";
                receipt += "Account Name: " + accountName + "\n";
                receipt += "Payment Method: " + paymentMethod + "\n";
                receipt += "Payment Date: " + paymentDateInput + "\n";
                receipt += "---------------------------------------------\n";
                receipt += "Penalty Charge: " + penaltyCharge + "\n";
                receipt += "Total Amount Due: " + totalAmountDue + "\n";
                receipt += "---------------------------------------------\n";
                receipt += "Thank you for your payment!\n";
                receipt += "===============================================\n\n";

                PArea.append(receipt); 

                isReceiptAdded = true;
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save payment details.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (ParseException e) {
        JOptionPane.showMessageDialog(this, "Invalid date format. Please use MM/dd/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
    } 


    }//GEN-LAST:event_jButton2ActionPerformed

    private void PaymentMethodCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaymentMethodCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PaymentMethodCBActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(7);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(6);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(8);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void DisplayClientsBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisplayClientsBTActionPerformed
        DefaultTableModel model = (DefaultTableModel) CLTable.getModel();
    model.setRowCount(0);

    String query = "SELECT * FROM clientledger";

    try (Connection conn = DBConnection2.getConnection(); 
         PreparedStatement pst = conn.prepareStatement(query); 
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            String accountNo = rs.getString("Account_No");
            String accountName = rs.getString("Account_Name");
            String serviceAddress = rs.getString("Service_Address");
            String property = rs.getString("Property");
            String meterNo = rs.getString("Meter_No");
            BigDecimal previousReading = rs.getBigDecimal("Previous_Reading");
            BigDecimal currentReading = rs.getBigDecimal("Current_Reading");
            BigDecimal consumption = rs.getBigDecimal("Consumption");
            BigDecimal rate = rs.getBigDecimal("Rate");
            BigDecimal taxAmount = rs.getBigDecimal("Tax_Amount");
            BigDecimal baseCharge = rs.getBigDecimal("Base_Charge");
            BigDecimal penaltyCharge = rs.getBigDecimal("Penalty_Charge");
            Date billingPeriodStart = rs.getDate("Billing_Period_Start");
            Date billingPeriodEnd = rs.getDate("Billing_Period_End");
            Date dueDate = rs.getDate("Due_Date");
            BigDecimal totalAmountDue = rs.getBigDecimal("Total_Amount_Due");

            model.addRow(new Object[]{
                accountNo, accountName, serviceAddress, property, meterNo, previousReading, currentReading,
                consumption, rate, taxAmount, baseCharge, penaltyCharge, billingPeriodStart, billingPeriodEnd, dueDate, totalAmountDue
            });
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error loading data from the database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
    
    }//GEN-LAST:event_DisplayClientsBTActionPerformed

    private void CLSearchBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLSearchBTActionPerformed
    String searchKeyword = CLSearchTF.getText().trim();
    
    if (searchKeyword.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter a search term.", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    DefaultTableModel model = (DefaultTableModel) CLTable.getModel();
    model.setRowCount(0);

    String query = "SELECT * FROM clientledger WHERE Account_No LIKE ? OR Account_Name LIKE ? OR Property LIKE ? OR Meter_No LIKE ? OR Billing_Period_Start LIKE ?";

    try (Connection conn = DBConnection2.getConnection();  
         PreparedStatement pst = conn.prepareStatement(query)) {
        
        String searchTerm = "%" + searchKeyword + "%";
        
        pst.setString(1, searchTerm);
        pst.setString(2, searchTerm);
        pst.setString(3, searchTerm);
        pst.setString(4, searchTerm);
        pst.setString(5, searchTerm);

        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String accountNo = rs.getString("Account_No");
                String accountName = rs.getString("Account_Name");
                String serviceAddress = rs.getString("Service_Address");
                String property = rs.getString("Property");
                String meterNo = rs.getString("Meter_No");
                BigDecimal previousReading = rs.getBigDecimal("Previous_Reading");
                BigDecimal currentReading = rs.getBigDecimal("Current_Reading");
                BigDecimal consumption = rs.getBigDecimal("Consumption");
                BigDecimal rate = rs.getBigDecimal("Rate");
                BigDecimal taxAmount = rs.getBigDecimal("Tax_Amount");
                BigDecimal baseCharge = rs.getBigDecimal("Base_Charge");
                BigDecimal penaltyCharge = rs.getBigDecimal("Penalty_Charge");
                Date billingPeriodStart = rs.getDate("Billing_Period_Start");
                Date billingPeriodEnd = rs.getDate("Billing_Period_End");
                BigDecimal totalAmountDue = rs.getBigDecimal("Total_Amount_Due");

                model.addRow(new Object[]{
                    accountNo, accountName, serviceAddress, property, meterNo, previousReading, currentReading,
                    consumption, rate, taxAmount, baseCharge, penaltyCharge, billingPeriodStart, billingPeriodEnd, totalAmountDue
                });
            }
        }
        
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error searching data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
    }//GEN-LAST:event_CLSearchBTActionPerformed

    private void CLDeleteBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLDeleteBTActionPerformed
    String accountNo = CLSearchTF.getText().trim();

    if (accountNo.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter an account number to search.", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    DefaultTableModel model = (DefaultTableModel) CLTable.getModel();
    int selectedRow = -1;

    for (int i = 0; i < model.getRowCount(); i++) {
        if (model.getValueAt(i, 0).toString().equals(accountNo)) {  
            selectedRow = i;
            break;
        }
    }

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Delete will only work on Account Number.", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

    if (confirmDelete == JOptionPane.YES_OPTION) {
        String accountName = model.getValueAt(selectedRow, 1).toString();
        String property = model.getValueAt(selectedRow, 3).toString();
        String meterNo = model.getValueAt(selectedRow, 4).toString();
        Date billingPeriodStart = (Date) model.getValueAt(selectedRow, 12);

        String deleteQuery = "DELETE FROM clientledger WHERE Account_No = ? AND Account_Name = ? AND Property = ? AND Meter_No = ? AND Billing_Period_Start = ?";

        try (Connection conn = DBConnection2.getConnection(); PreparedStatement pst = conn.prepareStatement(deleteQuery)) {
            pst.setString(1, accountNo);
            pst.setString(2, accountName);
            pst.setString(3, property);
            pst.setString(4, meterNo);
            pst.setDate(5, new java.sql.Date(billingPeriodStart.getTime()));

            int rowsDeleted = pst.executeUpdate();

            if (rowsDeleted > 0) {
                model.removeRow(selectedRow);  
                JOptionPane.showMessageDialog(null, "Record deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Record not found or could not be deleted.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error deleting record: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }                                              
    
    }//GEN-LAST:event_CLDeleteBTActionPerformed

    private void CLDeleteAllBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLDeleteAllBTActionPerformed
    int confirm = JOptionPane.showConfirmDialog(null, 
        "Are you sure you want to delete all records from the client ledger?", 
        "Delete All Confirmation", JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        String adminPassword = JOptionPane.showInputDialog(null, "Enter Admin Password:");

        
        if (adminPassword != null && adminPassword.equals("RJCWB")) { 
            try (Connection con = DBConnection.getConnection()) {
               
                String sqlDelete = "DELETE FROM clientledger";
                try (PreparedStatement pst = con.prepareStatement(sqlDelete)) {
                    int rowsDeleted = pst.executeUpdate();
                    if (rowsDeleted > 0) {
                  
                        DefaultTableModel model = (DefaultTableModel) CLTable.getModel();
                        model.setRowCount(0); 

                        JOptionPane.showMessageDialog(null, "All records have been successfully deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No records found to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error deleting records: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect admin password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    }//GEN-LAST:event_CLDeleteAllBTActionPerformed

    private void PTotalAmountDueTF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PTotalAmountDueTF1ActionPerformed
        syncTotalAmountDue();
    }//GEN-LAST:event_PTotalAmountDueTF1ActionPerformed

    private void SCalculateViewSalesBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SCalculateViewSalesBTActionPerformed
        
    
    String dbUrl = "jdbc:mysql://localhost:3306/java_user_database";
    String dbUser = "root";
    String dbPassword = "";
    String query = "SELECT Payment_Date, Total_Amount_Due FROM clientpaymentsales";

    try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        DefaultTableModel tableModel = (DefaultTableModel) SalesTable.getModel();
        tableModel.setRowCount(0);

        while (rs.next()) {
            String paymentDate = rs.getString("Payment_Date");
            double totalAmountDue = rs.getDouble("Total_Amount_Due");
            tableModel.addRow(new Object[]{paymentDate, totalAmountDue});
        }

        calculateAndFilterSales();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error fetching sales data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_SCalculateViewSalesBTActionPerformed
    private void initializeSearchSalesListener() {
    SearchSales.getDocument().addDocumentListener(new DocumentListener() {
        public void insertUpdate(DocumentEvent e) {
            calculateAndFilterSales();
        }

        public void removeUpdate(DocumentEvent e) {
            calculateAndFilterSales();
        }

        public void changedUpdate(DocumentEvent e) {
            calculateAndFilterSales();
        }
    });
}

// Method to filter the table and calculate total sales
private void calculateAndFilterSales() {
    String searchText = SearchSales.getText().trim().toLowerCase();
    DefaultTableModel tableModel = (DefaultTableModel) SalesTable.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
    SalesTable.setRowSorter(sorter);

    // Apply filter
    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));

    // Calculate total sales based on filtered data
    double filteredTotalSales = 0.0;
    for (int i = 0; i < SalesTable.getRowCount(); i++) {
        filteredTotalSales += (double) SalesTable.getValueAt(i, 1);
    }

    TotalSalesTF.setText(String.format("%.2f", filteredTotalSales));
}
    private void TotalSalesTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalSalesTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalSalesTFActionPerformed

    private void SearchSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchSalesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchSalesActionPerformed

    private void CLSearchTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLSearchTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CLSearchTFActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        jTabbedPane1.setSelectedIndex(9);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void PPenaltyChargeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PPenaltyChargeTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PPenaltyChargeTFActionPerformed

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
    private javax.swing.JTextField BDTaxAmountTF;
    private javax.swing.JTextField BSAccountNameTF;
    private javax.swing.JTextField BSAccountNoTF;
    private javax.swing.JButton BSAddToReceipt;
    private javax.swing.JTextField BSBillingPeriodEndTF;
    private javax.swing.JTextField BSBillingPeriodStartTF;
    private javax.swing.JTextField BSDueDateTF;
    private javax.swing.JTextField BSTotalAmountDueTF;
    private javax.swing.JButton CLDeleteAllBT;
    private javax.swing.JButton CLDeleteBT;
    private javax.swing.JButton CLSearchBT;
    private javax.swing.JTextField CLSearchTF;
    private javax.swing.JTable CLTable;
    private javax.swing.JTable ClientInfoTable;
    private javax.swing.JTextField ContactNumberTF;
    private javax.swing.JButton CreateBT;
    private javax.swing.JButton DeleteBT;
    private javax.swing.JButton DisplayClientsBT;
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
    private javax.swing.JTextField PAccountNameTF;
    private javax.swing.JTextField PAccountNoTF;
    private javax.swing.JTextArea PArea;
    private javax.swing.JTextField PPaymentDateTF;
    private javax.swing.JTextField PPenaltyChargeTF;
    private javax.swing.JTextField PTotalAmountDueTF1;
    private javax.swing.JComboBox<String> PaymentMethodCB;
    private javax.swing.JTable RateMaintenanceTable;
    private javax.swing.JButton SCalculateViewSalesBT;
    private javax.swing.JTextField SIAccountNameTF;
    private javax.swing.JTextField SIAccountNoTF;
    private javax.swing.JTextField SIMeterNoTF;
    private javax.swing.JComboBox<String> SIPropertyCB;
    private javax.swing.JTextArea SIServiceAddressTF;
    private javax.swing.JTable SalesTable;
    private javax.swing.JButton SearchBT;
    private javax.swing.JTextField SearchSales;
    private javax.swing.JTextField TotalSalesTF;
    private javax.swing.JButton UpdateBT;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
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
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
