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
import waterbillingsystemrjc.Admin_Dashboard;
import waterbillingsystemrjc.Admin_Dashboard;
import static waterbillingsystemrjc.Admin_Dashboard.getConnection;
import waterbillingsystemrjc.Login;
import waterbillingsystemrjc.Login;


    public class AdminDashboard extends javax.swing.JFrame {

     
        public AdminDashboard() {
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
    
    @SuppressWarnings("unchecked")


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel73 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        SIAccountNoTF = new javax.swing.JTextField();
        CreateBT = new javax.swing.JButton();
        SearchBT = new javax.swing.JButton();
        UpdateBT = new javax.swing.JButton();
        DeleteBT = new javax.swing.JButton();
        SIPropertyCB = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        SIServiceAddressTF = new javax.swing.JTextArea();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        AccountStatusCB = new javax.swing.JComboBox<>();
        SIMeterNoTF = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        ClientInfoTable = new javax.swing.JTable();
        jLabel36 = new javax.swing.JLabel();
        ContactNumberTF = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        SIAccountNameTF = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        MRRateTF = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        MRAccountNameTF = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        MRMeterNoTF = new javax.swing.JTextField();
        MRConsumptionTF = new javax.swing.JTextField();
        MRPreviousReadingTF = new javax.swing.JTextField();
        MRCurrentReadingTF = new javax.swing.JTextField();
        MRAddToReceipt = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        MRAccountNoTF = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jLabel92 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        BDAccountNameTF = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        BDPreviousReadingTF = new javax.swing.JTextField();
        BDCurrentReadingTF = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        BDConsumptionTF = new javax.swing.JTextField();
        BDRateTF = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        BDBaseChargeTF = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        BDPenaltyChargeTF = new javax.swing.JTextField();
        BDTaxAmountTF = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        BDAddToReceipt1 = new javax.swing.JButton();
        jLabel84 = new javax.swing.JLabel();
        BDAccountNoTF = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel94 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        BSAccountNoTF = new javax.swing.JTextField();
        BSAccountNameTF = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        BSBillingPeriodStartTF = new javax.swing.JTextField();
        BSBillingPeriodEndTF = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        BSTotalAmountDueTF = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        BSDueDateTF = new javax.swing.JTextField();
        BSAddToReceipt = new javax.swing.JButton();
        jLabel91 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel95 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        GRAccountNoTF = new javax.swing.JTextField();
        GRAccountNameTF = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        GRPreviousReadingTF = new javax.swing.JTextField();
        GRCurrentReadingTF = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        GRConsumptionTF = new javax.swing.JTextField();
        GRRateTF = new javax.swing.JTextField();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        GRBaseChargeTF = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        GRPenaltyChargeTF = new javax.swing.JTextField();
        GRDueDateTF = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        GRTaxAmountTF = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        GRPropertyTF = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        GRBillingPeriodStartTF = new javax.swing.JTextField();
        GRBillingPeriodEndTF = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        GRTotalAmountDueTF = new javax.swing.JTextField();
        GRBT = new javax.swing.JButton();
        jLabel110 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        GRServiceAddressTF = new javax.swing.JTextArea();
        jLabel111 = new javax.swing.JLabel();
        GRMeterNoTF = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        GRReceipt = new javax.swing.JTextArea();
        jLabel112 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jLabel113 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jLabel132 = new javax.swing.JLabel();
        PAccountNoTF = new javax.swing.JTextField();
        PAccountNameTF = new javax.swing.JTextField();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        PPaymentDateTF = new javax.swing.JTextField();
        PaymentMethodCB = new javax.swing.JComboBox<>();
        jButton10 = new javax.swing.JButton();
        jLabel135 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        PArea = new javax.swing.JTextArea();
        PTotalAmountDueTF1 = new javax.swing.JTextField();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        CLTable = new javax.swing.JTable();
        DisplayClientsBT = new javax.swing.JButton();
        CLSearchTF = new javax.swing.JTextField();
        CLDeleteBT = new javax.swing.JButton();
        CLDeleteAllBT = new javax.swing.JButton();
        jLabel114 = new javax.swing.JLabel();
        CLSearchBT = new javax.swing.JButton();
        jLabel139 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        SCalculateViewSalesBT = new javax.swing.JButton();
        TotalSalesTF = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        SalesTable = new javax.swing.JTable();
        jLabel115 = new javax.swing.JLabel();
        SearchSales = new javax.swing.JTextField();
        jLabel141 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        RateMaintenanceTable = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();

        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/BG (1).png"))); // NOI18N
        jLabel73.setText("jLabel73");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1190, 680));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setMinimumSize(new java.awt.Dimension(2000, 2200));
        jPanel10.setPreferredSize(new java.awt.Dimension(1190, 3000));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setText("Responsibilities of RJC Water Billing ");
        jPanel10.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 439, 410, -1));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("A water supplier is responsible for sourcing, treating, and distributing clean and safe water to meet");
        jPanel10.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 495, -1, 25));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/facebook (1).png"))); // NOI18N
        jPanel10.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 1690, 30, 30));

        jLabel5.setBackground(new java.awt.Color(144, 190, 222));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/LOGO (4).jpg"))); // NOI18N
        jPanel10.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 1930, 80, 60));
        jPanel10.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));
        jPanel10.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));

        jLabel6.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setText("R J C");
        jPanel10.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 2140, -1, 30));

        jLabel9.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jLabel9.setText("Reliable Justice Charges");
        jPanel10.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 349, 279, 19));

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel11.setText("the needs of residential, commercial, industrial and Institutional users. They ensure the sustainability");
        jPanel10.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 526, -1, 25));

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel12.setText(" of water sources, maintain infrastructure like pipelines and treatment facilities, and regularly monitor");
        jPanel10.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 557, -1, 25));

        jLabel14.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel14.setText(" water quality to comply with health and safety standards. Additionally, they address customer concerns,");
        jPanel10.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 588, -1, 25));

        jLabel15.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel15.setText("manage billing, and respond to emergencies such as service disruptions or contamination issues. ");
        jPanel10.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 619, -1, 25));

        jLabel16.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel16.setText("By promoting water conservation and adhering to regulatory requirements, water suppliers play a ");
        jPanel10.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 656, -1, 25));

        jLabel17.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel17.setText("vital role in protecting public health and ensuring reliable access to water.");
        jPanel10.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 687, -1, 25));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/pipelines (1).jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(676, 432, 495, -1));

        jPanel4.setBackground(new java.awt.Color(0, 102, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setBackground(new java.awt.Color(0, 102, 153));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/user-headset (3).png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, 60));

        jLabel13.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("SERVICE INFORMATION");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, 20));

        jPanel10.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 900, 180, 200));

        jPanel5.setBackground(new java.awt.Color(0, 102, 153));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton5.setBackground(new java.awt.Color(0, 102, 153));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/meter-droplet (1).png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLabel18.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("METER READING");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jPanel10.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 900, 180, 200));

        jPanel6.setBackground(new java.awt.Color(0, 102, 153));
        jPanel6.setPreferredSize(new java.awt.Dimension(164, 202));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setBackground(new java.awt.Color(0, 102, 153));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/file-invoice-dollar (1).png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLabel19.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("MAINTENANCE");
        jPanel6.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        jLabel47.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("RATE ");
        jPanel6.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, -1));

        jPanel10.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 900, 180, -1));

        jPanel7.setBackground(new java.awt.Color(0, 102, 153));
        jPanel7.setPreferredSize(new java.awt.Dimension(164, 202));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton7.setBackground(new java.awt.Color(0, 102, 153));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/wallet-arrow (1).png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLabel20.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("PAYMENT");
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jPanel10.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(694, 900, 180, -1));

        jPanel8.setBackground(new java.awt.Color(0, 102, 153));
        jPanel8.setPreferredSize(new java.awt.Dimension(164, 202));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton8.setBackground(new java.awt.Color(0, 102, 153));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/file-circle-info (1).png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLabel21.setFont(new java.awt.Font("Rockwell", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("CLIENT LEDGER");
        jPanel8.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 140, -1, -1));

        jPanel10.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 900, 180, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/CONTACT1 (1).jpg"))); // NOI18N
        jPanel10.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 1470, 400, 400));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/CONTACT3 (1).jpg"))); // NOI18N
        jPanel10.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 1390, -1, -1));

        jLabel25.setFont(new java.awt.Font("Freestyle Script", 0, 36)); // NOI18N
        jLabel25.setText("-Billing made better");
        jPanel10.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 2190, -1, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 51, 204));
        jLabel26.setText("Stay informed by following us through this channel ");
        jPanel10.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 1660, -1, -1));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/instagram (1).png"))); // NOI18N
        jPanel10.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 1690, 30, -1));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/twitter (1).png"))); // NOI18N
        jPanel10.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 1690, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/waterbottle (1).jpg"))); // NOI18N
        jPanel10.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 275));

        jLabel56.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel56.setText("0965-356-7688");
        jPanel10.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 2060, -1, -1));

        jLabel57.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 102, 153));
        jLabel57.setText("RJC Water Billing System");
        jPanel10.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 1930, -1, -1));

        jLabel58.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 102, 153));
        jLabel58.setText("RJC Water Billing System, Inc. 2024");
        jPanel10.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 2010, -1, -1));

        jLabel59.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(0, 102, 153));
        jLabel59.setText("Calatagan, Batangas");
        jPanel10.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 2030, -1, -1));

        jLabel60.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 102, 153));
        jLabel60.setText("Customer Hotline : 28-200-7");
        jPanel10.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 2060, -1, -1));

        jLabel61.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 102, 153));
        jLabel61.setText("Customer Support");
        jPanel10.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 1950, -1, -1));

        jLabel62.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 102, 153));
        jLabel62.setText("Service Advisory");
        jPanel10.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 1980, -1, -1));

        jLabel63.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(0, 102, 153));
        jLabel63.setText("FAQs");
        jPanel10.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 2000, -1, -1));

        jLabel64.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 102, 153));
        jLabel64.setText("Contact Us:");
        jPanel10.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 2020, -1, -1));

        jLabel65.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel65.setText("0909-541-7972");
        jPanel10.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 2020, -1, -1));

        jLabel66.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel66.setText("0987-564-9872");
        jPanel10.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 2040, -1, -1));

        jLabel67.setFont(new java.awt.Font("Nirmala UI", 1, 36)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 51, 204));
        jLabel67.setText("CONNECT WITH US");
        jPanel10.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 1550, -1, -1));

        jLabel68.setFont(new java.awt.Font("Freestyle Script", 0, 36)); // NOI18N
        jLabel68.setText("Manage water wisely with ");
        jPanel10.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 2140, 260, -1));

        jLabel69.setFont(new java.awt.Font("Nirmala UI", 1, 48)); // NOI18N
        jLabel69.setText("R J C");
        jPanel10.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 299, -1, 44));

        jLabel70.setText("@RJCWBS2024");
        jPanel10.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 1700, -1, -1));

        jLabel71.setText("RJCWaterBilling");
        jPanel10.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 1700, 90, -1));

        jLabel72.setText("@RJCWBS");
        jPanel10.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 1700, 80, -1));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/BG (1).png"))); // NOI18N
        jLabel29.setText("jLabel29");
        jPanel10.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 740, -1, -1));

        jScrollPane1.setViewportView(jPanel10);

        jPanel9.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 1190, 969));

        jTabbedPane1.addTab("HOME", jPanel9);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Account No. :");
        jLabel30.setAutoscrolls(true);
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, -1, 27));

        jLabel31.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Account Name :");
        jLabel31.setAutoscrolls(true);
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, -1, -1));

        jLabel32.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Service Address :");
        jLabel32.setAutoscrolls(true);
        jPanel3.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, -1, -1));

        jLabel33.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Property:");
        jLabel33.setAutoscrolls(true);
        jPanel3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 90, -1, -1));

        SIAccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SIAccountNoTFActionPerformed(evt);
            }
        });
        jPanel3.add(SIAccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 312, 40));

        CreateBT.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        CreateBT.setText("CREATE");
        CreateBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateBTActionPerformed(evt);
            }
        });
        jPanel3.add(CreateBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 330, 110, 44));

        SearchBT.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        SearchBT.setText("SEARCH");
        SearchBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBTActionPerformed(evt);
            }
        });
        jPanel3.add(SearchBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, 110, 44));

        UpdateBT.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        UpdateBT.setText("UPDATE");
        UpdateBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBTActionPerformed(evt);
            }
        });
        jPanel3.add(UpdateBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, 110, 44));

        DeleteBT.setFont(new java.awt.Font("Perpetua Titling MT", 0, 12)); // NOI18N
        DeleteBT.setText("DELETE");
        DeleteBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBTActionPerformed(evt);
            }
        });
        jPanel3.add(DeleteBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 330, 100, 44));

        SIPropertyCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Residential", "Commercial", "Industrial", "Institutional" }));
        SIPropertyCB.setAutoscrolls(true);
        SIPropertyCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SIPropertyCBActionPerformed(evt);
            }
        });
        jPanel3.add(SIPropertyCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 70, 200, 40));

        jScrollPane2.setAutoscrolls(true);

        SIServiceAddressTF.setColumns(20);
        SIServiceAddressTF.setRows(5);
        jScrollPane2.setViewportView(SIServiceAddressTF);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 312, 40));

        jLabel34.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Meter No. :");
        jLabel34.setAutoscrolls(true);
        jPanel3.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 150, -1, -1));

        jLabel35.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Account Status :");
        jLabel35.setAutoscrolls(true);
        jPanel3.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 210, -1, -1));

        AccountStatusCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));
        AccountStatusCB.setAutoscrolls(true);
        AccountStatusCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccountStatusCBActionPerformed(evt);
            }
        });
        jPanel3.add(AccountStatusCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 190, 200, 40));

        SIMeterNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SIMeterNoTFActionPerformed(evt);
            }
        });
        jPanel3.add(SIMeterNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 130, 200, 40));

        ClientInfoTable.setBackground(new java.awt.Color(0, 51, 102));
        ClientInfoTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 102, 102), null, null));
        ClientInfoTable.setForeground(new java.awt.Color(255, 255, 255));
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
        jScrollPane5.setViewportView(ClientInfoTable);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1079, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 1090, 250));

        jLabel36.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Contact Number :");
        jLabel36.setAutoscrolls(true);
        jPanel3.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 170, -1));

        ContactNumberTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContactNumberTFActionPerformed(evt);
            }
        });
        jPanel3.add(ContactNumberTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, 310, 40));

        jLabel37.setFont(new java.awt.Font("MingLiU_HKSCS-ExtB", 1, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("SERVICE INFORMATION");
        jLabel37.setAutoscrolls(true);
        jPanel3.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 240, -1));

        SIAccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SIAccountNameTFActionPerformed(evt);
            }
        });
        jPanel3.add(SIAccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 312, 40));

        jLabel38.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/ADMINGB (1).jpg"))); // NOI18N
        jPanel3.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, -1));

        jTabbedPane1.addTab("Sales", jPanel3);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Account No. :");
        jPanel2.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, -1, 20));

        MRRateTF.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        MRRateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRRateTFActionPerformed(evt);
            }
        });
        jPanel2.add(MRRateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, 310, 30));

        jLabel40.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Meter No. :");
        jPanel2.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, -1, 20));

        MRAccountNameTF.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        MRAccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRAccountNameTFActionPerformed(evt);
            }
        });
        jPanel2.add(MRAccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 312, 30));

        jLabel41.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Account Name :");
        jPanel2.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, -1, 20));

        jLabel42.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Consumption :");
        jPanel2.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 340, -1, 20));

        jLabel43.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Previous Reading :");
        jPanel2.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 220, -1, 20));

        jLabel44.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Current Reading :");
        jPanel2.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 280, -1, 20));

        jLabel45.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Rate :");
        jPanel2.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, -1, 20));

        MRMeterNoTF.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        MRMeterNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRMeterNoTFActionPerformed(evt);
            }
        });
        jPanel2.add(MRMeterNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, 310, 30));

        MRConsumptionTF.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        MRConsumptionTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRConsumptionTFActionPerformed(evt);
            }
        });
        jPanel2.add(MRConsumptionTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 330, 310, 30));

        MRPreviousReadingTF.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        MRPreviousReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRPreviousReadingTFActionPerformed(evt);
            }
        });
        jPanel2.add(MRPreviousReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 210, 310, 30));

        MRCurrentReadingTF.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        MRCurrentReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRCurrentReadingTFActionPerformed(evt);
            }
        });
        jPanel2.add(MRCurrentReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 270, 310, 30));

        MRAddToReceipt.setFont(new java.awt.Font("Perpetua", 1, 14)); // NOI18N
        MRAddToReceipt.setText("ADD TO RECEIPT");
        MRAddToReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRAddToReceiptActionPerformed(evt);
            }
        });
        jPanel2.add(MRAddToReceipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 410, -1, 20));

        jLabel46.setFont(new java.awt.Font("Palatino Linotype", 1, 16)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("METER READING");
        jPanel2.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 240, -1));

        MRAccountNoTF.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        MRAccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MRAccountNoTFActionPerformed(evt);
            }
        });
        jPanel2.add(MRAccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 312, 30));

        jButton13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton13.setText("Next");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 450, -1, -1));

        jLabel92.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/ADMINGB (1).jpg"))); // NOI18N
        jPanel2.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, -1));

        jTabbedPane1.addTab("METER", jPanel2);

        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel75.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel75.setForeground(java.awt.Color.white);
        jLabel75.setText("Account No. :");
        jPanel13.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, 27));

        BDAccountNameTF.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        BDAccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDAccountNameTFActionPerformed(evt);
            }
        });
        jPanel13.add(BDAccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 312, 30));

        jLabel76.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel76.setForeground(java.awt.Color.white);
        jLabel76.setText("Account Name :");
        jPanel13.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, -1, -1));

        jLabel77.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel77.setForeground(java.awt.Color.white);
        jLabel77.setText("Previous Reading :");
        jPanel13.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, -1, 27));

        BDPreviousReadingTF.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        BDPreviousReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDPreviousReadingTFActionPerformed(evt);
            }
        });
        jPanel13.add(BDPreviousReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 290, 312, 30));

        BDCurrentReadingTF.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        BDCurrentReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDCurrentReadingTFActionPerformed(evt);
            }
        });
        jPanel13.add(BDCurrentReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 350, 312, 30));

        jLabel78.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel78.setForeground(java.awt.Color.white);
        jLabel78.setText("Current Reading :");
        jPanel13.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, -1, -1));

        jLabel79.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel79.setForeground(java.awt.Color.white);
        jLabel79.setText("Consumption :");
        jPanel13.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 410, -1, 27));

        BDConsumptionTF.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        BDConsumptionTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDConsumptionTFActionPerformed(evt);
            }
        });
        jPanel13.add(BDConsumptionTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 312, 30));

        BDRateTF.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        BDRateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDRateTFActionPerformed(evt);
            }
        });
        jPanel13.add(BDRateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 190, 312, 30));

        jLabel80.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel80.setForeground(java.awt.Color.white);
        jLabel80.setText("Rate :");
        jPanel13.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 200, -1, -1));

        jLabel81.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel81.setForeground(java.awt.Color.white);
        jLabel81.setText("Base Charge :");
        jPanel13.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 310, -1, 27));

        BDBaseChargeTF.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        BDBaseChargeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDBaseChargeTFActionPerformed(evt);
            }
        });
        jPanel13.add(BDBaseChargeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 310, 312, 30));

        jLabel82.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel82.setForeground(java.awt.Color.white);
        jLabel82.setText("Penalty Charge :");
        jPanel13.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 370, -1, 27));

        BDPenaltyChargeTF.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        BDPenaltyChargeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDPenaltyChargeTFActionPerformed(evt);
            }
        });
        jPanel13.add(BDPenaltyChargeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 370, 312, 30));

        BDTaxAmountTF.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        BDTaxAmountTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDTaxAmountTFActionPerformed(evt);
            }
        });
        jPanel13.add(BDTaxAmountTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 250, 312, 30));

        jLabel83.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel83.setForeground(java.awt.Color.white);
        jLabel83.setText("Tax :");
        jPanel13.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 260, -1, -1));

        BDAddToReceipt1.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        BDAddToReceipt1.setText("ADD TO RECEIPT");
        BDAddToReceipt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDAddToReceipt1ActionPerformed(evt);
            }
        });
        jPanel13.add(BDAddToReceipt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 460, -1, -1));

        jLabel84.setFont(new java.awt.Font("Palatino Linotype", 1, 16)); // NOI18N
        jLabel84.setForeground(java.awt.Color.white);
        jLabel84.setText("BILLING DETAILS");
        jPanel13.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 240, -1));

        BDAccountNoTF.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        BDAccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDAccountNoTFActionPerformed(evt);
            }
        });
        jPanel13.add(BDAccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 312, 30));

        jButton14.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jButton14.setText("Next");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 500, -1, -1));

        jButton15.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jButton15.setText("Back");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        jLabel94.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel94.setForeground(java.awt.Color.white);
        jLabel94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/ADMINGB (1).jpg"))); // NOI18N
        jPanel13.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 1220, -1));

        jTabbedPane1.addTab("DETAILS", jPanel13);

        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel85.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(255, 255, 255));
        jLabel85.setText("Account No. :");
        jPanel14.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, -1, 27));

        BSAccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSAccountNoTFActionPerformed(evt);
            }
        });
        jPanel14.add(BSAccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 160, 312, 30));

        BSAccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSAccountNameTFActionPerformed(evt);
            }
        });
        jPanel14.add(BSAccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 220, 312, 30));

        jLabel86.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setText("Account Name :");
        jPanel14.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, -1, -1));

        jLabel87.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("Billing Period Start :");
        jPanel14.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 280, -1, 27));

        BSBillingPeriodStartTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSBillingPeriodStartTFActionPerformed(evt);
            }
        });
        jPanel14.add(BSBillingPeriodStartTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 280, 312, 30));

        BSBillingPeriodEndTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSBillingPeriodEndTFActionPerformed(evt);
            }
        });
        jPanel14.add(BSBillingPeriodEndTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 340, 312, 30));

        jLabel88.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setText("Billing Period End :");
        jPanel14.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 350, -1, -1));

        jLabel89.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setText("Total Amount Due :");
        jPanel14.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 470, -1, 27));

        BSTotalAmountDueTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSTotalAmountDueTFActionPerformed(evt);
            }
        });
        jPanel14.add(BSTotalAmountDueTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 470, 312, 30));

        jLabel90.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("Due Date :");
        jPanel14.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 400, -1, 27));

        BSDueDateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSDueDateTFActionPerformed(evt);
            }
        });
        jPanel14.add(BSDueDateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 400, 312, 30));

        BSAddToReceipt.setText("ADD TO RECEIPT");
        BSAddToReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSAddToReceiptActionPerformed(evt);
            }
        });
        jPanel14.add(BSAddToReceipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 530, -1, 20));

        jLabel91.setFont(new java.awt.Font("Palatino Linotype", 1, 16)); // NOI18N
        jLabel91.setForeground(java.awt.Color.white);
        jLabel91.setText("BILLING SUMMARY");
        jPanel14.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 240, -1));

        jButton16.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jButton16.setText("Back");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        jButton17.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jButton17.setText("Next");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 570, -1, -1));

        jLabel95.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel95.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/ADMINGB (1).jpg"))); // NOI18N
        jPanel14.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, -1));

        jTabbedPane1.addTab("SUM", jPanel14);

        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel96.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel96.setForeground(java.awt.Color.white);
        jLabel96.setText("Account No. :");
        jPanel16.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, -1, 27));

        GRAccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRAccountNoTFActionPerformed(evt);
            }
        });
        jPanel16.add(GRAccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 312, 30));

        GRAccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRAccountNameTFActionPerformed(evt);
            }
        });
        jPanel16.add(GRAccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 312, 30));

        jLabel97.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel97.setForeground(java.awt.Color.white);
        jLabel97.setText("Account Name :");
        jPanel16.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, -1, -1));

        jLabel98.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel98.setForeground(java.awt.Color.white);
        jLabel98.setText("Previous Reading :");
        jPanel16.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, 27));

        GRPreviousReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRPreviousReadingTFActionPerformed(evt);
            }
        });
        jPanel16.add(GRPreviousReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 240, 312, 30));

        GRCurrentReadingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRCurrentReadingTFActionPerformed(evt);
            }
        });
        jPanel16.add(GRCurrentReadingTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, 312, 30));

        jLabel99.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel99.setForeground(java.awt.Color.white);
        jLabel99.setText("Current Reading :");
        jPanel16.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, -1, -1));

        jLabel100.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel100.setForeground(java.awt.Color.white);
        jLabel100.setText("Consumption :");
        jPanel16.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, -1, 27));

        GRConsumptionTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRConsumptionTFActionPerformed(evt);
            }
        });
        jPanel16.add(GRConsumptionTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, 312, 30));

        GRRateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRRateTFActionPerformed(evt);
            }
        });
        jPanel16.add(GRRateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 360, 312, 30));

        jLabel101.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel101.setForeground(java.awt.Color.white);
        jLabel101.setText("Rate :");
        jPanel16.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, -1, -1));

        jLabel102.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel102.setForeground(java.awt.Color.white);
        jLabel102.setText("Base Charge :");
        jPanel16.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 440, -1, 27));

        GRBaseChargeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRBaseChargeTFActionPerformed(evt);
            }
        });
        jPanel16.add(GRBaseChargeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 440, 312, 30));

        jLabel103.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel103.setForeground(java.awt.Color.white);
        jLabel103.setText("Penalty Charge :");
        jPanel16.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 480, -1, 27));

        GRPenaltyChargeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRPenaltyChargeTFActionPerformed(evt);
            }
        });
        jPanel16.add(GRPenaltyChargeTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 480, 312, 30));

        GRDueDateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRDueDateTFActionPerformed(evt);
            }
        });
        jPanel16.add(GRDueDateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 600, 312, 30));

        jLabel104.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel104.setForeground(java.awt.Color.white);
        jLabel104.setText("Due Date:");
        jPanel16.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 610, -1, -1));

        GRTaxAmountTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRTaxAmountTFActionPerformed(evt);
            }
        });
        jPanel16.add(GRTaxAmountTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, 312, 30));

        jLabel105.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel105.setForeground(java.awt.Color.white);
        jLabel105.setText("Tax :");
        jPanel16.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, -1, -1));

        GRPropertyTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRPropertyTFActionPerformed(evt);
            }
        });
        jPanel16.add(GRPropertyTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 312, 30));

        jLabel106.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel106.setForeground(java.awt.Color.white);
        jLabel106.setText("Meter No. :");
        jPanel16.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 210, -1, -1));

        jLabel107.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel107.setForeground(java.awt.Color.white);
        jLabel107.setText("Billing Period Start :");
        jPanel16.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 520, -1, 27));

        GRBillingPeriodStartTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRBillingPeriodStartTFActionPerformed(evt);
            }
        });
        jPanel16.add(GRBillingPeriodStartTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 520, 312, 30));

        GRBillingPeriodEndTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRBillingPeriodEndTFActionPerformed(evt);
            }
        });
        jPanel16.add(GRBillingPeriodEndTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 560, 312, 30));

        jLabel108.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel108.setForeground(java.awt.Color.white);
        jLabel108.setText("Billing Period End :");
        jPanel16.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 570, -1, -1));

        jLabel109.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel109.setForeground(java.awt.Color.white);
        jLabel109.setText("Total Amount Due :");
        jPanel16.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 640, -1, 27));

        GRTotalAmountDueTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRTotalAmountDueTFActionPerformed(evt);
            }
        });
        jPanel16.add(GRTotalAmountDueTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 640, 312, 30));

        GRBT.setFont(new java.awt.Font("Rockwell", 0, 12)); // NOI18N
        GRBT.setText("GENERATE RECEIPT");
        GRBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRBTActionPerformed(evt);
            }
        });
        jPanel16.add(GRBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 610, -1, 20));

        jLabel110.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel110.setForeground(java.awt.Color.white);
        jLabel110.setText("Service Area :");
        jPanel16.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, -1, -1));

        GRServiceAddressTF.setColumns(20);
        GRServiceAddressTF.setRows(5);
        jScrollPane3.setViewportView(GRServiceAddressTF);

        jPanel16.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 310, 30));

        jLabel111.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel111.setForeground(java.awt.Color.white);
        jLabel111.setText("Property :");
        jPanel16.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, -1, -1));

        GRMeterNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GRMeterNoTFActionPerformed(evt);
            }
        });
        jPanel16.add(GRMeterNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 312, 30));

        jPanel17.setBackground(new java.awt.Color(0, 102, 153));

        GRReceipt.setColumns(20);
        GRReceipt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        GRReceipt.setRows(5);
        jScrollPane7.setViewportView(GRReceipt);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel16.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 80, 360, -1));

        jLabel112.setFont(new java.awt.Font("Palatino Linotype", 1, 16)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(255, 255, 255));
        jLabel112.setText("GENERATE RECEIPT");
        jPanel16.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 240, -1));

        jButton18.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jButton18.setText("Back");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        jButton19.setFont(new java.awt.Font("SimSun", 1, 12)); // NOI18N
        jButton19.setText("Next");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 570, -1, -1));

        jLabel113.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel113.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/ADMINGB (1).jpg"))); // NOI18N
        jPanel16.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, -1));

        jTabbedPane1.addTab("REC", jPanel16);

        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton9.setFont(new java.awt.Font("Sitka Text", 0, 13)); // NOI18N
        jButton9.setText("SEND");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 420, -1, -1));

        jLabel132.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel132.setForeground(java.awt.Color.white);
        jLabel132.setText("Account No. :");
        jPanel18.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, -1, 27));

        PAccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PAccountNoTFActionPerformed(evt);
            }
        });
        jPanel18.add(PAccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 312, 30));

        PAccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PAccountNameTFActionPerformed(evt);
            }
        });
        jPanel18.add(PAccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 312, 30));

        jLabel133.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel133.setForeground(java.awt.Color.white);
        jLabel133.setText("Account Name :");
        jPanel18.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, -1, -1));

        jLabel134.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel134.setForeground(java.awt.Color.white);
        jLabel134.setText("Payment Date :");
        jPanel18.add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, -1, 27));

        PPaymentDateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PPaymentDateTFActionPerformed(evt);
            }
        });
        jPanel18.add(PPaymentDateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 312, 30));

        PaymentMethodCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "E-Payment" }));
        PaymentMethodCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentMethodCBActionPerformed(evt);
            }
        });
        jPanel18.add(PaymentMethodCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, 310, 30));

        jButton10.setFont(new java.awt.Font("Sitka Text", 0, 13)); // NOI18N
        jButton10.setText("Add to Receipt");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 350, -1, -1));

        jLabel135.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel135.setForeground(java.awt.Color.white);
        jLabel135.setText("Payment Method :");
        jPanel18.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, -1, -1));

        jPanel24.setBackground(new java.awt.Color(0, 102, 153));

        PArea.setColumns(20);
        PArea.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PArea.setRows(5);
        jScrollPane8.setViewportView(PArea);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel18.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 90, -1, 320));

        PTotalAmountDueTF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PTotalAmountDueTF1ActionPerformed(evt);
            }
        });
        jPanel18.add(PTotalAmountDueTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 312, 30));

        jLabel136.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel136.setForeground(java.awt.Color.white);
        jLabel136.setText("Total Amount Due :");
        jPanel18.add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, -1, 27));

        jLabel137.setFont(new java.awt.Font("Palatino Linotype", 1, 16)); // NOI18N
        jLabel137.setForeground(new java.awt.Color(255, 255, 255));
        jLabel137.setText("PAYMENT");
        jPanel18.add(jLabel137, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 240, 20));

        jLabel138.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel138.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/ADMINGB (1).jpg"))); // NOI18N
        jPanel18.add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 1200, -1));

        jTabbedPane1.addTab("PAY", jPanel18);

        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CLTable.setBackground(new java.awt.Color(0, 102, 153));
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
        jScrollPane9.setViewportView(CLTable);

        jPanel19.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 1110, 410));

        DisplayClientsBT.setText("DISPLAY CLIENTS");
        DisplayClientsBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisplayClientsBTActionPerformed(evt);
            }
        });
        jPanel19.add(DisplayClientsBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, -1, -1));

        CLSearchTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLSearchTFActionPerformed(evt);
            }
        });
        jPanel19.add(CLSearchTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 380, -1));

        CLDeleteBT.setText("DELETE");
        CLDeleteBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLDeleteBTActionPerformed(evt);
            }
        });
        jPanel19.add(CLDeleteBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 120, -1, -1));

        CLDeleteAllBT.setText("DELETE ALL");
        CLDeleteAllBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLDeleteAllBTActionPerformed(evt);
            }
        });
        jPanel19.add(CLDeleteAllBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 120, -1, -1));

        jLabel114.setFont(new java.awt.Font("Palatino Linotype", 1, 16)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(255, 255, 255));
        jLabel114.setText("CLIENTS LEDGER");
        jPanel19.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 240, -1));

        CLSearchBT.setText("SEARCH");
        CLSearchBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLSearchBTActionPerformed(evt);
            }
        });
        jPanel19.add(CLSearchBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, -1, -1));

        jLabel139.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel139.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/ADMINGB (1).jpg"))); // NOI18N
        jPanel19.add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 1200, -1));

        jTabbedPane1.addTab("LEDG", jPanel19);

        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SCalculateViewSalesBT.setText("VIEW SALES");
        SCalculateViewSalesBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SCalculateViewSalesBTActionPerformed(evt);
            }
        });
        jPanel22.add(SCalculateViewSalesBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, -1, -1));

        TotalSalesTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalSalesTFActionPerformed(evt);
            }
        });
        jPanel22.add(TotalSalesTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 540, 100, -1));

        jScrollPane10.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N

        SalesTable.setBackground(new java.awt.Color(0, 102, 153));
        SalesTable.setForeground(new java.awt.Color(255, 255, 255));
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
                "Total Amount Due", "Payment Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(SalesTable);

        jPanel22.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 713, 316));

        jLabel115.setFont(new java.awt.Font("Palatino Linotype", 1, 16)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(255, 255, 255));
        jLabel115.setText("SALES");
        jPanel22.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 240, -1));

        SearchSales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchSalesActionPerformed(evt);
            }
        });
        jPanel22.add(SearchSales, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 180, -1));

        jLabel141.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel141.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/ADMINGB (1).jpg"))); // NOI18N
        jPanel22.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 1200, -1));

        jTabbedPane1.addTab("SALES", jPanel22);

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RateMaintenanceTable.setBackground(new java.awt.Color(0, 102, 153));
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

        jPanel11.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 800, 180));

        jLabel48.setFont(new java.awt.Font("Palatino Linotype", 1, 16)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("RATE MAINTENANCE");
        jPanel11.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

        jLabel142.setFont(new java.awt.Font("Palatino Linotype", 1, 14)); // NOI18N
        jLabel142.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/ADMINGB (1).jpg"))); // NOI18N
        jPanel11.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 1200, -1));

        jTabbedPane1.addTab("RATE", jPanel11);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-140, 35, 1330, -1));

        jButton1.setBackground(new java.awt.Color(139, 174, 189));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DESIGN/LOGO.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 40, 30));

        jButton2.setText("About Us");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, -1, 20));

        jButton3.setText("Sales");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, -1, 20));

        jLabel22.setText("HOME");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jButton11.setText("Log out");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 10, -1, 20));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(7);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(6);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(9);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton4ActionPerformed

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

            DefaultTableModel model = (DefaultTableModel) CLTable.getModel();
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

    private void DeleteBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBTActionPerformed
        try {
    String accountNo = SIAccountNoTF.getText().trim(); 
    if (accountNo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a valid Account No.", "Error", JOptionPane.ERROR_MESSAGE);
        return; 
    }

    // Confirmation prompt before deletion
    int confirmation = JOptionPane.showConfirmDialog(
        this, 
        "Are you sure you want to delete the client with Account No: " + accountNo + "?\nThis action cannot be undone.", 
        "Confirm Deletion", 
        JOptionPane.YES_NO_OPTION, 
        JOptionPane.WARNING_MESSAGE
    );

    if (confirmation != JOptionPane.YES_OPTION) {
        return; // Exit if the user chooses "No"
    }

    // Proceed with deletion
    String query = "DELETE FROM clientinfo WHERE Account_No = ?";
    pst = con.prepareStatement(query);
    pst.setString(1, accountNo); 
    int rowsDeleted = pst.executeUpdate();

    if (rowsDeleted > 0) {
        JOptionPane.showMessageDialog(this, "Client information deleted successfully!");

        // Remove the deleted record from the table
        DefaultTableModel model = (DefaultTableModel) ClientInfoTable.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(accountNo)) {  
                model.removeRow(i);  
                break;
            }
        }

        // Clear input fields
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

    private void AccountStatusCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccountStatusCBActionPerformed

    }//GEN-LAST:event_AccountStatusCBActionPerformed

    private void SIMeterNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SIMeterNoTFActionPerformed

    }//GEN-LAST:event_SIMeterNoTFActionPerformed

    private void ContactNumberTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContactNumberTFActionPerformed

    }//GEN-LAST:event_ContactNumberTFActionPerformed

    private void SIAccountNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SIAccountNameTFActionPerformed
   
    }//GEN-LAST:event_SIAccountNameTFActionPerformed

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

    private void MRAddToReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRAddToReceiptActionPerformed
        syncMRDataToOtherTabs();  
        JOptionPane.showMessageDialog(this, 
        "Data successfully added to your receipt.", 
        "Success", 
        JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_MRAddToReceiptActionPerformed

    private void MRAccountNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MRAccountNoTFActionPerformed

    }//GEN-LAST:event_MRAccountNoTFActionPerformed

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

    private void BDTaxAmountTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDTaxAmountTFActionPerformed
         BDBaseChargeTF.requestFocus();
    }//GEN-LAST:event_BDTaxAmountTFActionPerformed

    private void BDAddToReceipt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDAddToReceipt1ActionPerformed
        
        syncBDDataToBSandGR();
        JOptionPane.showMessageDialog(this, 
        "Data successfully added to your receipt.", 
        "Success", 
        JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_BDAddToReceipt1ActionPerformed

    private void BDAccountNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDAccountNoTFActionPerformed

    }//GEN-LAST:event_BDAccountNoTFActionPerformed

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

    private void BSAddToReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSAddToReceiptActionPerformed
        syncBSDataToGR();
        JOptionPane.showMessageDialog(this, 
        "Data successfully added to your receipt.", 
        "Success", 
        JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_BSAddToReceiptActionPerformed

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

    try (Connection conn1 = Admin_Dashboard.DBConnection.getConnection(); 
         Connection conn2 = Admin_Dashboard.DBConnection2.getConnection()) { // Assuming DBConnection2 is the second DB connection

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

    private void GRMeterNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GRMeterNoTFActionPerformed

    }//GEN-LAST:event_GRMeterNoTFActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        JOptionPane.showMessageDialog(this, 
        "You successfully sent it to the client.", 
        "Message Sent", 
        JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void PAccountNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PAccountNoTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PAccountNoTFActionPerformed

    private void PAccountNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PAccountNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PAccountNameTFActionPerformed

    private void PPaymentDateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PPaymentDateTFActionPerformed
        // TODO add your handling code here:
        String paymentDateInput = PPaymentDateTF.getText().trim();
SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
dateFormat.setLenient(false);

try {
    // Parse the payment date
    java.util.Date utilPaymentDate = dateFormat.parse(paymentDateInput);
    java.sql.Date sqlPaymentDate = new java.sql.Date(utilPaymentDate.getTime());

    // Retrieve account number and other data
    String accountNo = PAccountNoTF.getText().trim();
    String query = "SELECT Billing_Period_End, Total_Amount_Due FROM generatedreceipt WHERE Account_No = ?";
    double penaltyCharge = 0.0;

    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_user_database", "root", "")) {
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, accountNo);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            // Retrieve the billing period end date and total amount due
            java.sql.Date billingPeriodEnd = rs.getDate("Billing_Period_End");
            double totalAmountDue = rs.getDouble("Total_Amount_Due");

            // Debugging: Print retrieved data
            System.out.println("Retrieved Total Amount Due: " + totalAmountDue);  // Debug log
            System.out.println("Retrieved Billing Period End: " + billingPeriodEnd);  // Debug log

            // Calculate due date (5 days after billing period end)
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(billingPeriodEnd);
            calendar.add(Calendar.DAY_OF_MONTH, 5);  // Adding 5 days to billing period end
            java.sql.Date dueDate = new java.sql.Date(calendar.getTimeInMillis());

            // Debugging: Print due date and payment date
            System.out.println("Calculated Due Date: " + dueDate);
            System.out.println("Payment Date: " + sqlPaymentDate);

            // Check if payment is late
            if (sqlPaymentDate.after(dueDate)) {
                // Calculate the days late
                long daysLate = (sqlPaymentDate.getTime() - dueDate.getTime()) / (24 * 60 * 60 * 1000);
                System.out.println("Days Late: " + daysLate);  // Debug log

                // If payment is late, apply a 10% penalty
                if (daysLate > 0) {
                    penaltyCharge = totalAmountDue * 0.10;
                    System.out.println("Penalty Charge Calculated: " + penaltyCharge);  // Debug log
                }
            }

            // Final penalty charge value
            System.out.println("Final Penalty Charge: " + penaltyCharge);

            // Set the calculated penalty in the text field
            BDPenaltyChargeTF.setText(String.format("%.2f", penaltyCharge));
        } else {
            JOptionPane.showMessageDialog(this, "Account not found in the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
} catch (ParseException e) {
    JOptionPane.showMessageDialog(this, "Invalid date format. Please use MM/dd/yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
}


    }//GEN-LAST:event_PPaymentDateTFActionPerformed

    private void PaymentMethodCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaymentMethodCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PaymentMethodCBActionPerformed
    private boolean isReceiptAdded = false;
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
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
        String penaltyChargeText = BDPenaltyChargeTF.getText().trim();  // Penalty charge from text field
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

    }//GEN-LAST:event_jButton10ActionPerformed

    private void PTotalAmountDueTF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PTotalAmountDueTF1ActionPerformed
        // TODO add your handling code here:
         syncTotalAmountDue();
    }//GEN-LAST:event_PTotalAmountDueTF1ActionPerformed

    private void DisplayClientsBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisplayClientsBTActionPerformed
        DefaultTableModel model = (DefaultTableModel) CLTable.getModel();
    model.setRowCount(0);

    String query = "SELECT * FROM clientledger";

    try (Connection conn = Admin_Dashboard.DBConnection2.getConnection(); 
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

        try (Connection conn = Admin_Dashboard.DBConnection2.getConnection(); PreparedStatement pst = conn.prepareStatement(deleteQuery)) {
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
            try (Connection con = Admin_Dashboard.DBConnection.getConnection()) {
               
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

    private void CLSearchBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLSearchBTActionPerformed
        String searchKeyword = CLSearchTF.getText().trim();
        
        if (searchKeyword.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter a search term.", "Error", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    DefaultTableModel model = (DefaultTableModel) CLTable.getModel();
    model.setRowCount(0);

    String query = "SELECT * FROM clientledger WHERE Account_No LIKE ? OR Account_Name LIKE ? OR Property LIKE ? OR Meter_No LIKE ? OR Billing_Period_Start LIKE ?";

    try (Connection conn = Admin_Dashboard.DBConnection2.getConnection();  
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

    private void SCalculateViewSalesBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SCalculateViewSalesBTActionPerformed
        // TODO add your handling code here:
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

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(8);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        AboutUs AboutUsFrame = new AboutUs();
        AboutUsFrame.setVisible(true);
        AboutUsFrame.pack();
        AboutUsFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
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
    }//GEN-LAST:event_jButton11ActionPerformed

    private void CLSearchTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLSearchTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CLSearchTFActionPerformed

    private void TotalSalesTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalSalesTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TotalSalesTFActionPerformed

    private void SearchSalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchSalesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchSalesActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButton15ActionPerformed

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
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> AccountStatusCB;
    private javax.swing.JTextField BDAccountNameTF;
    private javax.swing.JTextField BDAccountNoTF;
    private javax.swing.JButton BDAddToReceipt1;
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
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
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
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
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
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
