/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smsender;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.IOutboundMessageNotification;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;

/**
 *
 * @author Joel
 */
public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    String ProjectPath, contactfilepath, groupfilepath, Messagefilepath, CustomMsgCSVFilePath;
    DefaultListModel dlmContacts;
    DefaultListModel dlmGroups;
    DefaultListModel dlmRecipient;
    DefaultListModel dlmMessage;
    SerialModemGateway gateway;
    OutboundMessage msg;
    String[] a, headerrow, nextline;

    public MainForm() {
        initComponents();
        ProjectPath = new File("").getAbsolutePath();
        Messagefilepath = ProjectPath + "\\message\\message";
        contactfilepath = ProjectPath + "\\contact\\contact";
        groupfilepath = ProjectPath + "\\group";
        CreateDirectory();
        modemInitialise();

        dlmRecipient = new DefaultListModel();
        jLstRecipient.setModel(dlmRecipient);

        dlmContacts = new DefaultListModel();
        jLstContacts.setModel(dlmContacts);

        dlmGroups = new DefaultListModel();
        jLstGroup.setModel(dlmGroups);

        dlmMessage = new DefaultListModel();
        jLstMessage.setModel(dlmMessage);
        refreshcontacts();
        refreshgroups();
        refreshmessage();
    }

    private void modemInitialise() {
        try {
            OutboundNotification outboundNotification = new OutboundNotification();
            gateway = new SerialModemGateway("modem.com3", "COM3", 115200, "nokia", "C3");
            gateway.setInbound(true);
            gateway.setOutbound(true);
            gateway.setSimPin("0000");
            gateway.setSmscNumber("+919633493265");
            Service.getInstance().setOutboundMessageNotification(outboundNotification);
            Service.getInstance().addGateway(gateway);
            Service.getInstance().startService();
            jtxtManufacterInfo.setText(gateway.getManufacturer());
            jtxtModel.setText(gateway.getModel());
            jtxtSerialNo.setText(gateway.getSerialNo());
            jtxtSignalLevel.setText(gateway.getSignalLevel() + " dBm");
            jtxtBattery.setText(gateway.getBatteryLevel() + "%");
        } catch (GatewayException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TimeoutException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SMSLibException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class OutboundNotification implements IOutboundMessageNotification {

        public void process(AGateway gateway, OutboundMessage msg) {
            System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
            System.out.println(msg);
            if (Service.getInstance().getOutboundMessageCount(gateway.getGatewayId()) == 2) {
                try {
                    Service.getInstance().stopService();
                    System.exit(1);
                } catch (SMSLibException ex) {
                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLstContacts = new javax.swing.JList();
        jBtnAddContacts = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLstGroup = new javax.swing.JList();
        jBtnAddGroup = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jLstRecipient = new javax.swing.JList();
        jBtnRemoveRecipient = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jLstMessage = new javax.swing.JList();
        jBtnSelect = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTxtMessage = new javax.swing.JTextArea();
        jBtnSend = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jtxtManufacterInfo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtxtModel = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtxtSerialNo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtxtBattery = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtxtSignalLevel = new javax.swing.JTextField();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SMS Sender");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Contacts"));

        jScrollPane1.setViewportView(jLstContacts);

        jBtnAddContacts.setText(">>");
        jBtnAddContacts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAddContactsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnAddContacts)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jBtnAddContacts)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Groups"));

        jScrollPane2.setViewportView(jLstGroup);

        jBtnAddGroup.setText(">>");
        jBtnAddGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAddGroupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnAddGroup)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jBtnAddGroup)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Recipients"));

        jScrollPane3.setViewportView(jLstRecipient);

        jBtnRemoveRecipient.setText("<<");
        jBtnRemoveRecipient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRemoveRecipientActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Message"));

        jLabel1.setText("Message Templates");

        jScrollPane4.setViewportView(jLstMessage);

        jBtnSelect.setText("Select");
        jBtnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSelectActionPerformed(evt);
            }
        });

        jTxtMessage.setColumns(20);
        jTxtMessage.setRows(5);
        jScrollPane5.setViewportView(jTxtMessage);

        jBtnSend.setText("SEND");
        jBtnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnSend))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jBtnSelect))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnSelect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jBtnSend)
                .addContainerGap())
        );

        jLabel3.setText("Manfacturer");

        jtxtManufacterInfo.setEditable(false);

        jLabel4.setText("Model");

        jtxtModel.setEditable(false);

        jLabel5.setText("Serial No.");

        jtxtSerialNo.setEditable(false);

        jLabel7.setText("Battery");

        jtxtBattery.setEditable(false);
        jtxtBattery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtBatteryActionPerformed(evt);
            }
        });

        jLabel6.setText("Signal Level");

        jtxtSignalLevel.setEditable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jBtnRemoveRecipient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtxtManufacterInfo)
                                    .addComponent(jtxtModel)
                                    .addComponent(jtxtSerialNo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxtBattery)
                                    .addComponent(jtxtSignalLevel))))
                        .addGap(41, 41, 41))
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnRemoveRecipient)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtxtManufacterInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jtxtModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jtxtSerialNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jtxtSignalLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jtxtBattery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smsender/Contacts-icon.png"))); // NOI18N
        jMenu3.setToolTipText("C n G Manager");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu3);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smsender/App-Messages-icon.png"))); // NOI18N
        jMenu1.setToolTipText("Message Template Manager");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smsender/Messages-icon.png"))); // NOI18N
        jMenu2.setToolTipText("Custom Message");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenu2);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public final void refreshcontacts() {
        dlmContacts.removeAllElements();
        ArrayList list = new ArrayList();
        list = new Global().ReadFile(contactfilepath);
        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            dlmContacts.addElement(itr.next());
        }
    }

    public final void refreshgroups() {
        dlmGroups.removeAllElements();
        File file = new File(groupfilepath);
        String grouplist[] = file.list();
        for (int i = 0; i < grouplist.length; i++) {
            dlmGroups.addElement(grouplist[i]);
        }
    }

    public final void refreshmessage() {
        dlmMessage.removeAllElements();
        ArrayList list = new ArrayList();
        list = new Global().ReadFile(Messagefilepath);
        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            dlmMessage.addElement(itr.next());
        }
    }

    private void CreateDirectory() {
        File contact = new File(ProjectPath + "\\contact");
        if (!contact.exists()) {
            contact.mkdir();
        }
        File group = new File(ProjectPath + "\\group");
        if (!group.exists()) {
            group.mkdir();
        }
        File message = new File(ProjectPath + "\\message");
        if (!message.exists()) {
            message.mkdir();
        }
    }
    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        CnGManagerDiag diag = new CnGManagerDiag(this, true);
        diag.setVisible(true);
        refreshcontacts();
        refreshgroups();
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jBtnAddContactsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAddContactsActionPerformed
        if (jLstContacts.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Select a contact to add to recipients", "No Selection", JOptionPane.INFORMATION_MESSAGE);
        } else {
            dlmRecipient.addElement(jLstContacts.getSelectedValue());
        }
    }//GEN-LAST:event_jBtnAddContactsActionPerformed

    private void jBtnAddGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAddGroupActionPerformed
        if (jLstGroup.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Select a group to add to recipients", "No Selection", JOptionPane.INFORMATION_MESSAGE);
        } else {
            dlmRecipient.addElement("Group   " + jLstGroup.getSelectedValue());
        }
    }//GEN-LAST:event_jBtnAddGroupActionPerformed

    private void jBtnRemoveRecipientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRemoveRecipientActionPerformed
        if (jLstRecipient.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Select a recipients to delete", "No Selection", JOptionPane.INFORMATION_MESSAGE);
        } else {
            dlmRecipient.removeElement(jLstRecipient.getSelectedValue());
        }
    }//GEN-LAST:event_jBtnRemoveRecipientActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        MSGManagerDiag msgdiag = new MSGManagerDiag(this, true);
        msgdiag.setVisible(true);
        refreshmessage();
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jBtnSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSelectActionPerformed
        if (jLstMessage.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(this, "Select a message template to send", "No Selection", JOptionPane.INFORMATION_MESSAGE);
        } else {
            jTxtMessage.setText(jLstMessage.getSelectedValue().toString());
        }
    }//GEN-LAST:event_jBtnSelectActionPerformed

    private void jtxtBatteryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtBatteryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtBatteryActionPerformed

    private void jBtnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSendActionPerformed
        if (!jTxtMessage.getText().equals("")) {
            ArrayList list = new ArrayList();
            for (int i = 0; i < dlmRecipient.getSize(); i++) {
                a = dlmRecipient.getElementAt(i).toString().split("   ");
                if (a[0].equals("Group")) {
                    list = new Global().ReadFile(groupfilepath + "\\" + a[1]);
                    Iterator itr = list.iterator();
                    while (itr.hasNext()) {
                        a = itr.next().toString().split("   ");
                        System.out.println(a[0] + "   " + a[1]);
                        msg = new OutboundMessage("+91" + a[1], jTxtMessage.getText());
                        Service.getInstance().queueMessage(msg, gateway.getGatewayId());
                    }
                } else {
                    System.out.println(a[0] + "   " + a[1]);
//                    msg = new OutboundMessage("+91" + a[1], jTxtMessage.getText());
//                    Service.getInstance().queueMessage(msg, gateway.getGatewayId());
                }
            }
        }else{
            JOptionPane.showMessageDialog(this, "Select a message template to send", "No Selection", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jBtnSendActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        CustomMsgForm Custommsgdiag = new CustomMsgForm(this, true);
        Custommsgdiag.setVisible(true);
    }//GEN-LAST:event_jMenu2MouseClicked

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
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
            UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAddContacts;
    private javax.swing.JButton jBtnAddGroup;
    private javax.swing.JButton jBtnRemoveRecipient;
    private javax.swing.JButton jBtnSelect;
    private javax.swing.JButton jBtnSend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList jLstContacts;
    private javax.swing.JList jLstGroup;
    private javax.swing.JList jLstMessage;
    private javax.swing.JList jLstRecipient;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTxtMessage;
    private javax.swing.JTextField jtxtBattery;
    private javax.swing.JTextField jtxtManufacterInfo;
    private javax.swing.JTextField jtxtModel;
    private javax.swing.JTextField jtxtSerialNo;
    private javax.swing.JTextField jtxtSignalLevel;
    // End of variables declaration//GEN-END:variables
}
