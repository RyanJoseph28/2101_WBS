package waterbillingsystemrjc;

import javax.swing.JOptionPane;


public class Client_Dashboard extends javax.swing.JFrame {

    public Client_Dashboard() {
        initComponents();
        CDReceipt.setEditable(false);
    }
    
    public void setReceiptContent(String receiptContent) {
        CDReceipt.setText(receiptContent);
    
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ProfileBT = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        CDReceipt = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        CDReceipt2 = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        CDAccountNoTF = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        CDAccountNameTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        CDPaymentMethod = new javax.swing.JTextField();
        CDAmountPaid = new javax.swing.JTextField();
        CDAmountDate = new javax.swing.JTextField();
        FPBT = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Downloads\\011.png")); // NOI18N
        jLabel1.setText("   Client Dashboard");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ProfileBT.setText("PROFILE");
        ProfileBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProfileBTActionPerformed(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(0, 204, 204));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 0, 12)); // NOI18N

        jPanel8.setBackground(new java.awt.Color(0, 204, 204));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabel30.setText("WATER BILLING");
        jPanel8.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 68, -1, -1));

        jLabel31.setFont(new java.awt.Font("Pristina", 0, 36)); // NOI18N
        jLabel31.setText("RJC");
        jPanel8.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(384, 80, -1, -1));

        jButton2.setText("Notification");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 476, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Message Request!!");
        jScrollPane1.setViewportView(jTextArea1);

        jPanel8.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 434, 125, 36));

        jTabbedPane1.addTab("", jPanel8);

        jPanel6.setBackground(new java.awt.Color(153, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane3.setViewportView(jTextArea2);

        jPanel6.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, -1, 290));

        jTabbedPane1.addTab("NOTIFICATION", jPanel6);

        jPanel7.setBackground(new java.awt.Color(153, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("PAYMENT REMINDERS");
        jPanel7.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, -1, -1));

        jLabel11.setText("From Admin");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, -1, -1));

        jLabel12.setText("Pampasa sa Admin");
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 70, -1, -1));

        jButton5.setText("Payment");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 500, -1, -1));

        jButton6.setText("Proceed");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 500, -1, -1));

        jButton7.setText("EDIT");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 500, -1, -1));

        CDReceipt.setColumns(20);
        CDReceipt.setRows(5);
        jScrollPane2.setViewportView(CDReceipt);

        jPanel7.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 270, 390));

        CDReceipt2.setColumns(20);
        CDReceipt2.setRows(5);
        jScrollPane4.setViewportView(CDReceipt2);

        jPanel7.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, 260, 390));

        jTabbedPane1.addTab("RECEIPT", jPanel7);

        jPanel5.setBackground(new java.awt.Color(153, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel5.setText("Account No. :");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, 27));

        CDAccountNoTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CDAccountNoTFActionPerformed(evt);
            }
        });
        jPanel5.add(CDAccountNoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 312, 40));

        jLabel36.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel36.setText("Payment Method :");
        jPanel5.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        CDAccountNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CDAccountNameTFActionPerformed(evt);
            }
        });
        jPanel5.add(CDAccountNameTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 312, 40));

        jLabel6.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel6.setText("Account Name :");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jLabel7.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel7.setText("Amount Paid :");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        jLabel8.setFont(new java.awt.Font("OCR A Extended", 0, 14)); // NOI18N
        jLabel8.setText("Payment Date :");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, -1, -1));

        CDPaymentMethod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CDPaymentMethodActionPerformed(evt);
            }
        });
        jPanel5.add(CDPaymentMethod, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 310, 40));

        CDAmountPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CDAmountPaidActionPerformed(evt);
            }
        });
        jPanel5.add(CDAmountPaid, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 310, 40));

        CDAmountDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CDAmountDateActionPerformed(evt);
            }
        });
        jPanel5.add(CDAmountDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 310, 40));

        FPBT.setText("Review");
        FPBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FPBTActionPerformed(evt);
            }
        });
        jPanel5.add(FPBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, -1, -1));

        jButton8.setText("OK");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 370, -1, -1));

        jTabbedPane1.addTab("PAYMENT", jPanel5);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));

        jLabel13.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 18)); // NOI18N
        jLabel13.setText("Payment History");

        jLabel14.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jLabel14.setText("Payment Date :");

        jLabel15.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jLabel15.setText("Approved by :");

        jLabel16.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jLabel16.setText("Amount Paid :");

        jLabel17.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jLabel17.setText("Balance Remaining :");

        jLabel18.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jLabel18.setText("Payment Method :");

        jLabel19.setFont(new java.awt.Font("OCR A Extended", 0, 12)); // NOI18N
        jLabel19.setText("Status :");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(81, 81, 81)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(652, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel13)
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(196, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("LEDGER", jPanel1);

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

        jButton10.setText("Ledger");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton9))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton10))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(ProfileBT))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jButton1)
                .addGap(77, 77, 77)
                .addComponent(jButton9)
                .addGap(37, 37, 37)
                .addComponent(jButton10)
                .addGap(297, 297, 297)
                .addComponent(jButton4)
                .addGap(27, 27, 27)
                .addComponent(jButton3))
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(ProfileBT))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ProfileBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfileBTActionPerformed
         ClientsDashboardProfile profileFrame = new ClientsDashboardProfile();
         profileFrame.setVisible(true);
         this.dispose();
    }//GEN-LAST:event_ProfileBTActionPerformed

    private void CDAccountNoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CDAccountNoTFActionPerformed
        String accountNo = CDAccountNoTF.getText().trim();
        String accountName = CDAccountNameTF.getText().trim();
        String meterNo = CDPaymentMethod.getText().trim();

        if (!accountNo.isEmpty() && !accountName.isEmpty() && !meterNo.isEmpty()) {
            syncAccountNumbers(accountNo, accountName, meterNo);
        }

    }//GEN-LAST:event_CDAccountNoTFActionPerformed

    private void CDAccountNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CDAccountNameTFActionPerformed

    }//GEN-LAST:event_CDAccountNameTFActionPerformed

    private void CDPaymentMethodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CDPaymentMethodActionPerformed

        String accountNo = CDAccountNoTF.getText().trim();
        String accountName = CDAccountNameTF.getText().trim();
        String meterNo = CDPaymentMethod.getText().trim();

        if (!accountNo.isEmpty() && !accountName.isEmpty() && !meterNo.isEmpty()) {
            syncAccountNumbers(accountNo, accountName, meterNo);
        }

    }//GEN-LAST:event_CDPaymentMethodActionPerformed

    private void CDAmountPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CDAmountPaidActionPerformed
       
    }//GEN-LAST:event_CDAmountPaidActionPerformed

    private void CDAmountDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CDAmountDateActionPerformed
 
    }//GEN-LAST:event_CDAmountDateActionPerformed

    private void FPBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FPBTActionPerformed
        jTabbedPane1.setSelectedIndex(2);
        
    }//GEN-LAST:event_FPBTActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        AboutUs AboutUsFrame = new AboutUs();
        AboutUsFrame.setVisible(true);
        AboutUsFrame.pack();
        AboutUsFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
   
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        jTabbedPane1.setSelectedIndex(2);
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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
            int response = JOptionPane.showConfirmDialog(
            null,
            "Do you want to pay?", 
            "Proceed to payment",
            JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.YES_OPTION) {
            jTabbedPane1.setSelectedIndex(3);
        } else {
            JOptionPane.showMessageDialog(null, "You chose not to proceed with payment.", "Payment Cancelled", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
            int response = JOptionPane.showConfirmDialog(
            null,
            "Do you want to edit?", 
            "Proceed to payment",
            JOptionPane.YES_NO_OPTION 
        );
            if (response == JOptionPane.YES_OPTION) {
                jTabbedPane1.setSelectedIndex(3);
            } else {
                JOptionPane.showMessageDialog(null, "You chose not to proceed with payment.", "Edit Cancelled", JOptionPane.INFORMATION_MESSAGE);
            }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
            jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
       
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
       
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
       
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
       
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
            int reponse = JOptionPane.showConfirmDialog(
                null,
                "Do want to add to receipt",
                "Receipt Added",
                JOptionPane.YES_NO_OPTION);
        
                CDReceipt2.setText(CDReceipt2.getText()+"\n***************READING PAYMENT****************\n");

                CDReceipt2.append("\nAccount No.  : "+ CDAccountNoTF.getText());
                CDReceipt2.append("\nAccount Name : "+ CDAccountNameTF.getText());
                CDReceipt2.append("\nPayment Method : "+ CDPaymentMethod.getText());
                CDReceipt2.append("\nAmount Paid : ₱"+ CDAmountPaid.getText());
                CDReceipt2.append("\nPayment Reading : "+ CDAmountDate.getText());
        
        syncMRDataToOtherTabs();
    }//GEN-LAST:event_jButton8ActionPerformed

    public static void main(String args[]) {
            java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new Client_Dashboard().setVisible(true);
                }
            });
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CDAccountNameTF;
    private javax.swing.JTextField CDAccountNoTF;
    private javax.swing.JTextField CDAmountDate;
    private javax.swing.JTextField CDAmountPaid;
    private javax.swing.JTextField CDPaymentMethod;
    private javax.swing.JTextArea CDReceipt;
    private javax.swing.JTextArea CDReceipt2;
    private javax.swing.JButton FPBT;
    private javax.swing.JButton ProfileBT;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables

    private void syncMRDataToOtherTabs() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
