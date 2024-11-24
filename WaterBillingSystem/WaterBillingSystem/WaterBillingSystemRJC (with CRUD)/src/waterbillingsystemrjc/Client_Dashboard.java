package waterbillingsystemrjc;

import waterbillingsystemrjc.ClientsDashboardProfile;

public class Client_Dashboard extends javax.swing.JFrame {

    public Client_Dashboard() {
        initComponents();
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        ClientBT = new javax.swing.JButton();
        MeterBT = new javax.swing.JButton();
        BillBT = new javax.swing.JButton();
        ReceiptBT = new javax.swing.JButton();
        PaymentBT = new javax.swing.JButton();
        LedgerBT = new javax.swing.JButton();
        NotificationBT = new javax.swing.JButton();
        TabbedPane = new javax.swing.JTabbedPane();
        ClientTP = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ClientsTable = new javax.swing.JTable();
        MeterTP = new javax.swing.JPanel();
        BillTP = new javax.swing.JPanel();
        ReceiptTP = new javax.swing.JPanel();
        PaymentTP = new javax.swing.JPanel();
        LedgerTP = new javax.swing.JPanel();
        NotificationTP = new javax.swing.JPanel();
        ProfileBT = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255,0));

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Downloads\\011.png")); // NOI18N
        jLabel1.setText("   Client Dashboard");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 110));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255,0));

        ClientBT.setText("CLIENT");
        ClientBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClientBTActionPerformed(evt);
            }
        });

        MeterBT.setText("METER");
        MeterBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MeterBTActionPerformed(evt);
            }
        });

        BillBT.setText("BILL");
        BillBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BillBTActionPerformed(evt);
            }
        });

        ReceiptBT.setText("RECEIPT");
        ReceiptBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReceiptBTActionPerformed(evt);
            }
        });

        PaymentBT.setText("PAYMENT");
        PaymentBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentBTActionPerformed(evt);
            }
        });

        LedgerBT.setText("LEDGER");
        LedgerBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LedgerBTActionPerformed(evt);
            }
        });

        NotificationBT.setText("NOTIFICATION");
        NotificationBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NotificationBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ClientBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MeterBT, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BillBT, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ReceiptBT, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PaymentBT, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LedgerBT, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NotificationBT, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(ClientBT, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MeterBT, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BillBT, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ReceiptBT, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PaymentBT, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LedgerBT, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NotificationBT, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 290, 750));

        ClientTP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ClientTP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Code :");
        ClientTP.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 58, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Full Name :");
        ClientTP.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 107, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Address :");
        ClientTP.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 156, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Contact Number :");
        ClientTP.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 205, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Email Address :");
        ClientTP.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 247, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Rate Class :");
        ClientTP.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 303, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Property :");
        ClientTP.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 352, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Meter Count :");
        ClientTP.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 401, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Account Date Created :");
        ClientTP.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 450, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Account Status :");
        ClientTP.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 499, -1, -1));

        ClientsTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ClientsTable.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        ClientsTable.setRowHeight(45);
        ClientsTable.setSelectionBackground(new java.awt.Color(204, 204, 204));
        ClientsTable.setShowGrid(false);
        ClientsTable.setShowHorizontalLines(true);
        ClientsTable.setShowVerticalLines(true);
        jScrollPane1.setViewportView(ClientsTable);

        ClientTP.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 510, 540));

        TabbedPane.addTab("CLIENT", ClientTP);

        javax.swing.GroupLayout MeterTPLayout = new javax.swing.GroupLayout(MeterTP);
        MeterTP.setLayout(MeterTPLayout);
        MeterTPLayout.setHorizontalGroup(
            MeterTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        MeterTPLayout.setVerticalGroup(
            MeterTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
        );

        TabbedPane.addTab("METER", MeterTP);

        javax.swing.GroupLayout BillTPLayout = new javax.swing.GroupLayout(BillTP);
        BillTP.setLayout(BillTPLayout);
        BillTPLayout.setHorizontalGroup(
            BillTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        BillTPLayout.setVerticalGroup(
            BillTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
        );

        TabbedPane.addTab("BILL", BillTP);

        javax.swing.GroupLayout ReceiptTPLayout = new javax.swing.GroupLayout(ReceiptTP);
        ReceiptTP.setLayout(ReceiptTPLayout);
        ReceiptTPLayout.setHorizontalGroup(
            ReceiptTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        ReceiptTPLayout.setVerticalGroup(
            ReceiptTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
        );

        TabbedPane.addTab("RECEIPT", ReceiptTP);

        javax.swing.GroupLayout PaymentTPLayout = new javax.swing.GroupLayout(PaymentTP);
        PaymentTP.setLayout(PaymentTPLayout);
        PaymentTPLayout.setHorizontalGroup(
            PaymentTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        PaymentTPLayout.setVerticalGroup(
            PaymentTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
        );

        TabbedPane.addTab("PAYMENT", PaymentTP);

        javax.swing.GroupLayout LedgerTPLayout = new javax.swing.GroupLayout(LedgerTP);
        LedgerTP.setLayout(LedgerTPLayout);
        LedgerTPLayout.setHorizontalGroup(
            LedgerTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        LedgerTPLayout.setVerticalGroup(
            LedgerTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
        );

        TabbedPane.addTab("LEDGER", LedgerTP);

        javax.swing.GroupLayout NotificationTPLayout = new javax.swing.GroupLayout(NotificationTP);
        NotificationTP.setLayout(NotificationTPLayout);
        NotificationTPLayout.setHorizontalGroup(
            NotificationTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        NotificationTPLayout.setVerticalGroup(
            NotificationTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
        );

        TabbedPane.addTab("NOTIFICATION", NotificationTP);

        getContentPane().add(TabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 930, 610));

        ProfileBT.setText("PROFILE");
        ProfileBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProfileBTActionPerformed(evt);
            }
        });
        getContentPane().add(ProfileBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 20, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NotificationBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NotificationBTActionPerformed

        TabbedPane.setSelectedIndex(6);
    }//GEN-LAST:event_NotificationBTActionPerformed

    private void LedgerBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LedgerBTActionPerformed

        TabbedPane.setSelectedIndex(5);
    }//GEN-LAST:event_LedgerBTActionPerformed

    private void PaymentBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaymentBTActionPerformed

        TabbedPane.setSelectedIndex(4);
    }//GEN-LAST:event_PaymentBTActionPerformed

    private void ReceiptBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReceiptBTActionPerformed
 
        TabbedPane.setSelectedIndex(3);
    }//GEN-LAST:event_ReceiptBTActionPerformed

    private void BillBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BillBTActionPerformed
    
        TabbedPane.setSelectedIndex(2);
    }//GEN-LAST:event_BillBTActionPerformed

    private void MeterBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MeterBTActionPerformed
  
        TabbedPane.setSelectedIndex(1);
    }//GEN-LAST:event_MeterBTActionPerformed

    private void ClientBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClientBTActionPerformed
    
        TabbedPane.setSelectedIndex(0);
        
    }//GEN-LAST:event_ClientBTActionPerformed

    private void ProfileBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfileBTActionPerformed
    
        ClientsDashboardProfile profileFrame = new ClientsDashboardProfile();
        profileFrame.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_ProfileBTActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client_Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BillBT;
    private javax.swing.JPanel BillTP;
    private javax.swing.JButton ClientBT;
    private javax.swing.JPanel ClientTP;
    private javax.swing.JTable ClientsTable;
    private javax.swing.JButton LedgerBT;
    private javax.swing.JPanel LedgerTP;
    private javax.swing.JButton MeterBT;
    private javax.swing.JPanel MeterTP;
    private javax.swing.JButton NotificationBT;
    private javax.swing.JPanel NotificationTP;
    private javax.swing.JButton PaymentBT;
    private javax.swing.JPanel PaymentTP;
    private javax.swing.JButton ProfileBT;
    private javax.swing.JButton ReceiptBT;
    private javax.swing.JPanel ReceiptTP;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
