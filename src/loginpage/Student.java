/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package loginpage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.rendering.PDFRenderer;
/**
 *
 * @author Brandon
 */
public class Student extends javax.swing.JFrame {

    private DefaultTableModel materiModel;
    private DefaultTableModel bookmarkModel;
    private DefaultTableModel diskusiModel;
    private DefaultTableModel diskusiSayaModel;
    
    private FileInputStream fis;
    private File selectedFile;
    
    Login login = new Login();
    /**
     * Creates new form Lecturer
     */
    public Student() {
        initComponents();
        
        materiModel = new DefaultTableModel();
        MateriTable.setModel(materiModel);
        materiModel.addColumn("Id Materi");
        materiModel.addColumn("Nama Materi");
        
       loadMateriData();
       
       bookmarkModel = new DefaultTableModel();
        BookmarkTable.setModel(bookmarkModel);
        bookmarkModel.addColumn("Id Materi");
        bookmarkModel.addColumn("Nama Materi");
        
        loadBookmarkData();
        
        diskusiModel = new DefaultTableModel();
        DiskusiTable.setModel(diskusiModel);
        diskusiModel.addColumn("Id");
        diskusiModel.addColumn("Nama Diskusi");
        diskusiModel.addColumn("Topik Diskusi");
        
        TableColumnModel columnModel2 = DiskusiTable.getColumnModel();
        columnModel2.getColumn(0).setPreferredWidth(5);
        columnModel2.getColumn(1).setPreferredWidth(75);
        
        loadDiskusiData();
        
        diskusiSayaModel = new DefaultTableModel();
        DiskusiSayaTable.setModel(diskusiSayaModel);
        diskusiSayaModel.addColumn("Id");
        diskusiSayaModel.addColumn("Nama Diskusi");
        diskusiSayaModel.addColumn("Topik Diskusi");
        
        TableColumnModel columnModel3 = DiskusiSayaTable.getColumnModel();
        columnModel3.getColumn(0).setPreferredWidth(5);
        columnModel3.getColumn(1).setPreferredWidth(75);
        
        loadDiskusiSayaData();
       
        usernameLabel.setText(login.currentUser.getUsername());
        jButton1.setVisible(false);
        
        profileNameField.setText(login.currentUser.getUsername());
        profileEmailField.setText(login.currentUser.getEmail());
    }
    
    private void loadMateriData() {
        materiModel.getDataVector().removeAllElements();
        materiModel.fireTableDataChanged();
        
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM materi";
            // TODO : query semua materi 
            PreparedStatement statement = connection.prepareStatement(query);
            
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Object[] o = new Object[3];
                o[0] = resultSet.getString("id_materi");
                o[1] = resultSet.getString("judul_materi");
                
                materiModel.addRow(o);
            }
            
            resultSet.close();            
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
    }
    
    private void loadBookmarkData() {
     bookmarkModel.getDataVector().removeAllElements();
     bookmarkModel.fireTableDataChanged();
        
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT bookmark.id_materi, materi.judul_materi FROM bookmark JOIN materi ON bookmark.id_materi = materi.id_materi WHERE id_user = ? ";
            // TODO : query bookmark user ini saja
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, login.currentUser.getId());
            
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Object[] o = new Object[3];
                o[0] = resultSet.getString("id_materi");
                o[1] = resultSet.getString("judul_materi");
                
                bookmarkModel.addRow(o);
            }
            
            resultSet.close();            
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } 
    }
    
    private void loadDiskusiData() {
        diskusiModel.getDataVector().removeAllElements();
        diskusiModel.fireTableDataChanged();
        
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM diskusi WHERE replies_id IS NULL");
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Object[] o = new Object[3];
                o[0] = resultSet.getString("id_diskusi");
                o[1] = resultSet.getString("nama_diskusi");
                o[2] = resultSet.getString("topik_diskusi");
                
                diskusiModel.addRow(o);
            }
            
            resultSet.close();            
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    private void loadDiskusiSayaData() {
        diskusiSayaModel.getDataVector().removeAllElements();
        diskusiSayaModel.fireTableDataChanged();
        
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM diskusi WHERE replies_id IS NULL && user_id= ?");
            statement.setInt(1, login.currentUser.getId());
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Object[] o = new Object[3];
                o[0] = resultSet.getString("id_diskusi");
                o[1] = resultSet.getString("nama_diskusi");
                o[2] = resultSet.getString("topik_diskusi");
                
                diskusiSayaModel.addRow(o);
            }
            
            resultSet.close();            
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    private void CleanUpDiskusi() {
        NamaDiskusiField.setText("");
        TopikDiskusiField.setText("");
        IdDiskusiHapusField.setText("");
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TabPane = new javax.swing.JTabbedPane();
        MateriPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MateriTable = new javax.swing.JTable();
        materiSayaLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        pdfScrollPane = new javax.swing.JScrollPane();
        pdfPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        BookmarkPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        BookmarkTable = new javax.swing.JTable();
        DiskusiLabel = new javax.swing.JLabel();
        pdfScrollPane1 = new javax.swing.JScrollPane();
        pdfPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        ProfilePanel = new javax.swing.JPanel();
        profileEmailLabel = new javax.swing.JLabel();
        profileEmailField = new javax.swing.JTextField();
        profileNameLabel = new javax.swing.JLabel();
        profileNameField = new javax.swing.JTextField();
        profileUpdateButton = new javax.swing.JButton();
        profileLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        AddDiskusiPanel = new javax.swing.JPanel();
        TopikDiskusiLabel = new javax.swing.JLabel();
        TopikDiskusiField = new javax.swing.JTextField();
        NamaDiskusiLabel = new javax.swing.JLabel();
        NamaDiskusiField = new javax.swing.JTextField();
        AddDiskusiButton = new javax.swing.JButton();
        penambahanDiskusiLabel = new javax.swing.JLabel();
        DiskusiSayaLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        DiskusiTable = new javax.swing.JTable();
        penambahanDiskusiLabel2 = new javax.swing.JLabel();
        TopikDiskusiLabel3 = new javax.swing.JLabel();
        IdDiskusiHapusField = new javax.swing.JTextField();
        HapusDiskusiButton = new javax.swing.JButton();
        NamaDiskusiTerpilih = new javax.swing.JLabel();
        DiskusiScrollPane = new javax.swing.JScrollPane();
        DiskusiPanel = new javax.swing.JPanel();
        TopikDiskusiTerpilih = new javax.swing.JLabel();
        JawabDiskusiField = new javax.swing.JTextField();
        JawabDiskusiButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        DiskusiSayaTable = new javax.swing.JTable();
        DiskusiSayaLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        MateriNavigator = new javax.swing.JButton();
        BookmarkNavigator = new javax.swing.JButton();
        DiskusiNavigator = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        ProfileNavigator = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1442, 870));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1450, 70));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DISTUDY");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/loginpage/Rectangle 77.png"))); // NOI18N

        usernameLabel.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setText("Username");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Group.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 943, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameLabel)
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 7, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameLabel)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1460, -1));

        TabPane.setBackground(new java.awt.Color(255, 255, 255));

        MateriPanel.setBackground(new java.awt.Color(255, 255, 255));
        MateriPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MateriTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        MateriTable.setForeground(new java.awt.Color(51, 153, 255));
        MateriTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        MateriTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MateriTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(MateriTable);

        MateriPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 67, 181, 435));

        materiSayaLabel.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        materiSayaLabel.setForeground(new java.awt.Color(51, 153, 255));
        materiSayaLabel.setText("Materi Saya");
        MateriPanel.add(materiSayaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 23, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Rectangle 79.png"))); // NOI18N
        jLabel4.setText("BG");
        MateriPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 1037, 37, -1));
        MateriPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 605, 37, 426));

        javax.swing.GroupLayout pdfPanelLayout = new javax.swing.GroupLayout(pdfPanel);
        pdfPanel.setLayout(pdfPanelLayout);
        pdfPanelLayout.setHorizontalGroup(
            pdfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1312, Short.MAX_VALUE)
        );
        pdfPanelLayout.setVerticalGroup(
            pdfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 843, Short.MAX_VALUE)
        );

        pdfScrollPane.setViewportView(pdfPanel);

        MateriPanel.add(pdfScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 67, 860, 680));

        jButton1.setBackground(new java.awt.Color(51, 153, 255));
        jButton1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Bookmark");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        MateriPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 70, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Rectangle 79.png"))); // NOI18N
        MateriPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 780));

        TabPane.addTab("tab1", MateriPanel);

        BookmarkPanel.setBackground(new java.awt.Color(255, 255, 255));
        BookmarkPanel.setLayout(null);

        BookmarkTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BookmarkTable.setForeground(new java.awt.Color(51, 153, 255));
        BookmarkTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        BookmarkTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BookmarkTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(BookmarkTable);

        BookmarkPanel.add(jScrollPane2);
        jScrollPane2.setBounds(20, 70, 280, 402);

        DiskusiLabel.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        DiskusiLabel.setForeground(new java.awt.Color(51, 153, 255));
        DiskusiLabel.setText("Bookmark Saya");
        BookmarkPanel.add(DiskusiLabel);
        DiskusiLabel.setBounds(20, 30, 200, 32);

        javax.swing.GroupLayout pdfPanel1Layout = new javax.swing.GroupLayout(pdfPanel1);
        pdfPanel1.setLayout(pdfPanel1Layout);
        pdfPanel1Layout.setHorizontalGroup(
            pdfPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 849, Short.MAX_VALUE)
        );
        pdfPanel1Layout.setVerticalGroup(
            pdfPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );

        pdfScrollPane1.setViewportView(pdfPanel1);

        BookmarkPanel.add(pdfScrollPane1);
        pdfScrollPane1.setBounds(310, 70, 851, 640);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Rectangle 79.png"))); // NOI18N
        jLabel8.setText("jLabel8");
        BookmarkPanel.add(jLabel8);
        jLabel8.setBounds(-3, -15, 1300, 840);

        TabPane.addTab("tab1", BookmarkPanel);

        ProfilePanel.setBackground(new java.awt.Color(255, 255, 255));
        ProfilePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        profileEmailLabel.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        profileEmailLabel.setForeground(new java.awt.Color(51, 153, 255));
        profileEmailLabel.setText("Email");
        ProfilePanel.add(profileEmailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 364, -1, -1));

        profileEmailField.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        profileEmailField.setForeground(new java.awt.Color(51, 153, 255));
        profileEmailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileEmailFieldActionPerformed(evt);
            }
        });
        ProfilePanel.add(profileEmailField, new org.netbeans.lib.awtextra.AbsoluteConstraints(544, 361, 304, -1));

        profileNameLabel.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        profileNameLabel.setForeground(new java.awt.Color(51, 153, 255));
        profileNameLabel.setText("Nama");
        ProfilePanel.add(profileNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 308, -1, -1));

        profileNameField.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        profileNameField.setForeground(new java.awt.Color(51, 153, 255));
        profileNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileNameFieldActionPerformed(evt);
            }
        });
        ProfilePanel.add(profileNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(544, 305, 304, -1));

        profileUpdateButton.setBackground(new java.awt.Color(51, 153, 255));
        profileUpdateButton.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        profileUpdateButton.setForeground(new java.awt.Color(255, 255, 255));
        profileUpdateButton.setText("Update Profile");
        profileUpdateButton.setBorder(null);
        profileUpdateButton.setBorderPainted(false);
        profileUpdateButton.setFocusPainted(false);
        profileUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileUpdateButtonActionPerformed(evt);
            }
        });
        ProfilePanel.add(profileUpdateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(669, 417, 179, 41));

        profileLabel.setFont(new java.awt.Font("Roboto", 1, 36)); // NOI18N
        profileLabel.setForeground(new java.awt.Color(51, 153, 255));
        profileLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profileLabel.setText("PROFILE");
        ProfilePanel.add(profileLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(552, 64, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/2.png"))); // NOI18N
        ProfilePanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, -1, 180));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Rectangle 79.png"))); // NOI18N
        ProfilePanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-6, -5, 1300, 780));

        TabPane.addTab("tab1", ProfilePanel);

        AddDiskusiPanel.setBackground(new java.awt.Color(255, 255, 255));
        AddDiskusiPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TopikDiskusiLabel.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        TopikDiskusiLabel.setForeground(new java.awt.Color(51, 153, 255));
        TopikDiskusiLabel.setText("Topik Diskusi");
        AddDiskusiPanel.add(TopikDiskusiLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 159, -1));

        TopikDiskusiField.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        TopikDiskusiField.setForeground(new java.awt.Color(51, 153, 255));
        TopikDiskusiField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TopikDiskusiFieldActionPerformed(evt);
            }
        });
        AddDiskusiPanel.add(TopikDiskusiField, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 118, 304, -1));

        NamaDiskusiLabel.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        NamaDiskusiLabel.setForeground(new java.awt.Color(51, 153, 255));
        NamaDiskusiLabel.setText("Nama Diskusi");
        AddDiskusiPanel.add(NamaDiskusiLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 159, -1));

        NamaDiskusiField.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        NamaDiskusiField.setForeground(new java.awt.Color(51, 153, 255));
        NamaDiskusiField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaDiskusiFieldActionPerformed(evt);
            }
        });
        AddDiskusiPanel.add(NamaDiskusiField, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 59, 304, -1));

        AddDiskusiButton.setBackground(new java.awt.Color(51, 153, 255));
        AddDiskusiButton.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        AddDiskusiButton.setForeground(new java.awt.Color(255, 255, 255));
        AddDiskusiButton.setText("Add Diskusi");
        AddDiskusiButton.setBorder(null);
        AddDiskusiButton.setBorderPainted(false);
        AddDiskusiButton.setFocusPainted(false);
        AddDiskusiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddDiskusiButtonActionPerformed(evt);
            }
        });
        AddDiskusiPanel.add(AddDiskusiButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 177, 141, 41));

        penambahanDiskusiLabel.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        penambahanDiskusiLabel.setForeground(new java.awt.Color(51, 153, 255));
        penambahanDiskusiLabel.setText("Penambahan Diskusi");
        AddDiskusiPanel.add(penambahanDiskusiLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 246, -1));

        DiskusiSayaLabel1.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        DiskusiSayaLabel1.setForeground(new java.awt.Color(51, 153, 255));
        DiskusiSayaLabel1.setText("Diskusi");
        AddDiskusiPanel.add(DiskusiSayaLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 15, 196, -1));

        DiskusiTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        DiskusiTable.setForeground(new java.awt.Color(51, 153, 255));
        DiskusiTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        DiskusiTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DiskusiTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(DiskusiTable);

        AddDiskusiPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 59, 196, 339));

        penambahanDiskusiLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        penambahanDiskusiLabel2.setForeground(new java.awt.Color(51, 153, 255));
        penambahanDiskusiLabel2.setText("Hapus Diskusi");
        AddDiskusiPanel.add(penambahanDiskusiLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 179, -1));

        TopikDiskusiLabel3.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        TopikDiskusiLabel3.setForeground(new java.awt.Color(51, 153, 255));
        TopikDiskusiLabel3.setText("Id Diskusi");
        AddDiskusiPanel.add(TopikDiskusiLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 165, -1));

        IdDiskusiHapusField.setEditable(false);
        IdDiskusiHapusField.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        IdDiskusiHapusField.setForeground(new java.awt.Color(51, 153, 255));
        IdDiskusiHapusField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdDiskusiHapusFieldActionPerformed(evt);
            }
        });
        AddDiskusiPanel.add(IdDiskusiHapusField, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 285, 304, -1));

        HapusDiskusiButton.setBackground(new java.awt.Color(51, 153, 255));
        HapusDiskusiButton.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        HapusDiskusiButton.setForeground(new java.awt.Color(255, 255, 255));
        HapusDiskusiButton.setText("Hapus Diskusi");
        HapusDiskusiButton.setBorder(null);
        HapusDiskusiButton.setBorderPainted(false);
        HapusDiskusiButton.setFocusPainted(false);
        HapusDiskusiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapusDiskusiButtonActionPerformed(evt);
            }
        });
        AddDiskusiPanel.add(HapusDiskusiButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(332, 341, 170, 40));

        NamaDiskusiTerpilih.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        NamaDiskusiTerpilih.setForeground(new java.awt.Color(51, 153, 255));
        NamaDiskusiTerpilih.setText("Nama Diskusi");
        AddDiskusiPanel.add(NamaDiskusiTerpilih, new org.netbeans.lib.awtextra.AbsoluteConstraints(722, 15, 543, -1));

        DiskusiPanel.setPreferredSize(new java.awt.Dimension(200, 300));
        DiskusiPanel.setLayout(new javax.swing.BoxLayout(DiskusiPanel, javax.swing.BoxLayout.PAGE_AXIS));

        TopikDiskusiTerpilih.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        TopikDiskusiTerpilih.setForeground(new java.awt.Color(51, 153, 255));
        TopikDiskusiTerpilih.setText("Topik Diskusi");
        DiskusiPanel.add(TopikDiskusiTerpilih);

        DiskusiScrollPane.setViewportView(DiskusiPanel);

        AddDiskusiPanel.add(DiskusiScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(722, 56, 543, 620));

        JawabDiskusiField.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        JawabDiskusiField.setForeground(new java.awt.Color(51, 153, 255));
        JawabDiskusiField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JawabDiskusiFieldActionPerformed(evt);
            }
        });
        AddDiskusiPanel.add(JawabDiskusiField, new org.netbeans.lib.awtextra.AbsoluteConstraints(722, 691, 456, -1));

        JawabDiskusiButton.setBackground(new java.awt.Color(51, 153, 255));
        JawabDiskusiButton.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        JawabDiskusiButton.setForeground(new java.awt.Color(255, 255, 255));
        JawabDiskusiButton.setText("Jawab");
        JawabDiskusiButton.setBorder(null);
        JawabDiskusiButton.setBorderPainted(false);
        JawabDiskusiButton.setFocusPainted(false);
        JawabDiskusiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JawabDiskusiButtonActionPerformed(evt);
            }
        });
        AddDiskusiPanel.add(JawabDiskusiButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1184, 691, 82, 34));

        DiskusiSayaTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        DiskusiSayaTable.setForeground(new java.awt.Color(51, 153, 255));
        DiskusiSayaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        DiskusiSayaTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DiskusiSayaTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(DiskusiSayaTable);

        AddDiskusiPanel.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 448, 196, 305));

        DiskusiSayaLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        DiskusiSayaLabel2.setForeground(new java.awt.Color(51, 153, 255));
        DiskusiSayaLabel2.setText("Diskusi Saya");
        AddDiskusiPanel.add(DiskusiSayaLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 410, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Rectangle 79.png"))); // NOI18N
        AddDiskusiPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 6, 1300, 770));

        TabPane.addTab("tab1", AddDiskusiPanel);

        getContentPane().add(TabPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, 840));

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));

        MateriNavigator.setBackground(new java.awt.Color(51, 153, 255));
        MateriNavigator.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        MateriNavigator.setForeground(new java.awt.Color(255, 255, 255));
        MateriNavigator.setText("Materi");
        MateriNavigator.setBorder(null);
        MateriNavigator.setBorderPainted(false);
        MateriNavigator.setFocusPainted(false);
        MateriNavigator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MateriNavigatorActionPerformed(evt);
            }
        });

        BookmarkNavigator.setBackground(new java.awt.Color(0, 0, 51));
        BookmarkNavigator.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BookmarkNavigator.setForeground(new java.awt.Color(255, 255, 255));
        BookmarkNavigator.setText("Bookmarks");
        BookmarkNavigator.setBorder(null);
        BookmarkNavigator.setBorderPainted(false);
        BookmarkNavigator.setContentAreaFilled(false);
        BookmarkNavigator.setFocusPainted(false);
        BookmarkNavigator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookmarkNavigatorActionPerformed(evt);
            }
        });

        DiskusiNavigator.setBackground(new java.awt.Color(0, 0, 51));
        DiskusiNavigator.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DiskusiNavigator.setForeground(new java.awt.Color(255, 255, 255));
        DiskusiNavigator.setText("Diskusi");
        DiskusiNavigator.setBorder(null);
        DiskusiNavigator.setBorderPainted(false);
        DiskusiNavigator.setContentAreaFilled(false);
        DiskusiNavigator.setFocusPainted(false);
        DiskusiNavigator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiskusiNavigatorActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("MENU");

        ProfileNavigator.setBackground(new java.awt.Color(0, 0, 51));
        ProfileNavigator.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ProfileNavigator.setForeground(new java.awt.Color(255, 255, 255));
        ProfileNavigator.setText("Profile");
        ProfileNavigator.setBorder(null);
        ProfileNavigator.setBorderPainted(false);
        ProfileNavigator.setContentAreaFilled(false);
        ProfileNavigator.setFocusPainted(false);
        ProfileNavigator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProfileNavigatorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MateriNavigator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BookmarkNavigator, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addContainerGap(22, Short.MAX_VALUE))
            .addComponent(DiskusiNavigator, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ProfileNavigator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ProfileNavigator, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MateriNavigator, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BookmarkNavigator, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DiskusiNavigator, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(516, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BookmarkTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BookmarkTableMouseClicked
      int i = BookmarkTable.getSelectedRow();
        TableModel model = BookmarkTable.getModel();
        
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM materi WHERE id_materi = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setInt(1, Integer.parseInt(model.getValueAt(i, 0).toString()));

            
            ResultSet resultSet = statement.executeQuery();
            

            if (resultSet.next()) {
                Blob blob = resultSet.getBlob("data_materi");
                InputStream inputStream = blob.getBinaryStream();

                PDDocument document = PDDocument.load(inputStream);
                PDFRenderer pdfRenderer = new PDFRenderer(document);

                int numPages = document.getNumberOfPages();
                pdfPanel1.removeAll();
                pdfPanel1.setLayout(new GridLayout(numPages, 1));

                for (int j = 0; j < numPages; j++) {
                    BufferedImage image = pdfRenderer.renderImageWithDPI(j, 100);
                    JLabel label = new JLabel(new ImageIcon(image));
                    pdfPanel1.add(label);
                }

                pdfScrollPane1.revalidate();
                pdfScrollPane1.repaint();
            } else {
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_BookmarkTableMouseClicked

    private void MateriNavigatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MateriNavigatorActionPerformed
        TabPane.setSelectedIndex(0);
        
        BookmarkNavigator.setContentAreaFilled(false);
        BookmarkNavigator.setBackground(new Color(0, 0, 51));
        
        MateriNavigator.setContentAreaFilled(true);
        MateriNavigator.setBackground(new Color(51, 153, 255));
        
        ProfileNavigator.setContentAreaFilled(false);
        ProfileNavigator.setBackground(new Color(0, 0, 51));
        
        DiskusiNavigator.setContentAreaFilled(false);
        DiskusiNavigator.setBackground(new Color(0, 0, 51));
    }//GEN-LAST:event_MateriNavigatorActionPerformed

    private void BookmarkNavigatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookmarkNavigatorActionPerformed
        TabPane.setSelectedIndex(1);
        
        BookmarkNavigator.setContentAreaFilled(true);
        BookmarkNavigator.setBackground(new Color(51, 153, 255));
        
        MateriNavigator.setContentAreaFilled(false);
        MateriNavigator.setBackground(new Color(0, 0, 51));
        
        ProfileNavigator.setContentAreaFilled(false);
        ProfileNavigator.setBackground(new Color(0, 0, 51));
        
        DiskusiNavigator.setContentAreaFilled(false);
        DiskusiNavigator.setBackground(new Color(0, 0, 51));
    }//GEN-LAST:event_BookmarkNavigatorActionPerformed

    private void MateriTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MateriTableMouseClicked
        int i = MateriTable.getSelectedRow();
        TableModel model = MateriTable.getModel();

        jButton1.setVisible(true);
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM materi WHERE id_materi = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, Integer.parseInt(model.getValueAt(i, 0).toString()));

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Blob blob = resultSet.getBlob("data_materi");
                InputStream inputStream = blob.getBinaryStream();

                PDDocument document = PDDocument.load(inputStream);
                PDFRenderer pdfRenderer = new PDFRenderer(document);

                int numPages = document.getNumberOfPages();
                pdfPanel.removeAll();
                pdfPanel.setLayout(new GridLayout(numPages, 1));

                for (int j = 0; j < numPages; j++) {
                    BufferedImage image = pdfRenderer.renderImageWithDPI(j, 100);
                    JLabel label = new JLabel(new ImageIcon(image));
                    pdfPanel.add(label);
                }

                pdfScrollPane.revalidate();
                pdfScrollPane.repaint();
            } else {
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_MateriTableMouseClicked

    private void DiskusiNavigatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiskusiNavigatorActionPerformed
        TabPane.setSelectedIndex(3);
        
        BookmarkNavigator.setContentAreaFilled(false);
        BookmarkNavigator.setBackground(new Color(0, 0, 51));
        
        MateriNavigator.setContentAreaFilled(false);
        MateriNavigator.setBackground(new Color(0, 0, 51));
        
        ProfileNavigator.setContentAreaFilled(false);
        ProfileNavigator.setBackground(new Color(0, 0, 51));
        
        DiskusiNavigator.setContentAreaFilled(true);
        DiskusiNavigator.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_DiskusiNavigatorActionPerformed

    private void ProfileNavigatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfileNavigatorActionPerformed
        TabPane.setSelectedIndex(2);
        
        BookmarkNavigator.setContentAreaFilled(false);
        BookmarkNavigator.setBackground(new Color(0, 0, 51));
        
        MateriNavigator.setContentAreaFilled(false);
        MateriNavigator.setBackground(new Color(0, 0, 51));
        
        ProfileNavigator.setContentAreaFilled(true);
        ProfileNavigator.setBackground(new Color(51, 153, 255));
        
        DiskusiNavigator.setContentAreaFilled(false);
        DiskusiNavigator.setBackground(new Color(0, 0, 51));
    }//GEN-LAST:event_ProfileNavigatorActionPerformed

    private void profileEmailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileEmailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profileEmailFieldActionPerformed

    private void profileNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profileNameFieldActionPerformed

    private void profileUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileUpdateButtonActionPerformed
        if(profileNameField.getText().isEmpty() || profileEmailField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Isi semua field!");
            return;
        }

        if(!profileEmailField.getText().contains("@")) {
            JOptionPane.showMessageDialog(null, "Email harus beriri '@'!");
            return;
        }

        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "UPDATE pengguna set name = ?, email = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, profileNameField.getText());
            statement.setString(2, profileEmailField.getText());
            statement.setString(3, String.valueOf(login.currentUser.getId()));

            statement.execute();

            JOptionPane.showMessageDialog(null, "Profile berhasil di-update.");
            usernameLabel.setText(profileNameField.getText());
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_profileUpdateButtonActionPerformed

    private void TopikDiskusiFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TopikDiskusiFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TopikDiskusiFieldActionPerformed

    private void NamaDiskusiFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaDiskusiFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaDiskusiFieldActionPerformed

    private void AddDiskusiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddDiskusiButtonActionPerformed
        if(NamaDiskusiField.getText().isEmpty() || TopikDiskusiField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Fill all fields");
            return;
        }
        // TODO add foreign key to lecturer. Perlu yang buat login 😊.
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "INSERT INTO diskusi(nama_diskusi, topik_diskusi, user_id) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, NamaDiskusiField.getText());
            statement.setString(2, TopikDiskusiField.getText());
            statement.setInt(3, login.currentUser.getId());

            statement.execute();

            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "Berhasil menambahkan diskusi");
            loadDiskusiData();
            CleanUpDiskusi();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_AddDiskusiButtonActionPerformed

    private void DiskusiTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DiskusiTableMouseClicked
        int i = DiskusiTable.getSelectedRow();
        TableModel model = DiskusiTable.getModel();
        String textContent = "";

        try {
            DiskusiPanel.removeAll();
            JTextPane TopikDiskusi = new JTextPane();
            TopikDiskusi.setText(model.getValueAt(i, 2).toString());
            TopikDiskusi.setFont(new java.awt.Font("Roboto", 1, 36));
            TopikDiskusi.setForeground(new java.awt.Color(51, 153, 255));
            TopikDiskusi.setEditable(false);
            TopikDiskusi.setMaximumSize(new Dimension(700, 100));
            DiskusiPanel.add(TopikDiskusi);

            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT * FROM diskusi WHERE replies_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, model.getValueAt(i, 0).toString());

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Connection connection2 = DatabaseConnection.getConnection();
                String query2 = "SELECT name FROM pengguna WHERE id = ?";
                PreparedStatement statement2 = connection2.prepareStatement(query2);

                statement2.setString(1, resultSet.getString("user_id"));

                ResultSet resultSet2 = statement2.executeQuery();

                while(resultSet2.next()) {
                    textContent += resultSet2.getString("name");
                }

                textContent += " - " + resultSet.getString("topik_diskusi");
                JTextPane reply = new JTextPane();
                reply.setText(textContent);
                reply.setPreferredSize(new Dimension(100, 20));
                reply.setMaximumSize(new Dimension(700, 50));
                reply.setFont(new java.awt.Font("Roboto", 1, 20));
                reply.setForeground(new java.awt.Color(51, 153, 255));
                reply.setBackground(new java.awt.Color(255,255,255));
                reply.setEditable(false);

                DiskusiPanel.add(reply);
                textContent = "";

                statement2.close();
                connection2.close();
            }

            statement.close();
            connection.close();

            DiskusiPanel.revalidate();
            DiskusiPanel.repaint();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        NamaDiskusiTerpilih.setText(model.getValueAt(i, 1).toString());
    }//GEN-LAST:event_DiskusiTableMouseClicked

    private void IdDiskusiHapusFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdDiskusiHapusFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdDiskusiHapusFieldActionPerformed

    private void HapusDiskusiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusDiskusiButtonActionPerformed
        int pilihan = JOptionPane.showConfirmDialog(null, "Apakah anda yakin akan menghapus diskusi?");

        if(pilihan != 0) return;

        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "DELETE FROM diskusi WHERE id_diskusi = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, IdDiskusiHapusField.getText());

            statement.execute();

            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "Berhasil menghapus diskusi");
            loadDiskusiData();
            CleanUpDiskusi();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_HapusDiskusiButtonActionPerformed

    private void JawabDiskusiFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JawabDiskusiFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JawabDiskusiFieldActionPerformed

    private void JawabDiskusiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JawabDiskusiButtonActionPerformed
        int i = DiskusiTable.getSelectedRow();
        TableModel model = DiskusiTable.getModel();

        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "INSERT INTO diskusi(topik_diskusi, user_id, replies_id) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, JawabDiskusiField.getText());
            statement.setString(2, String.valueOf(login.currentUser.getId()));
            statement.setString(3, model.getValueAt(i, 0).toString());

            statement.execute();

            JOptionPane.showMessageDialog(null, "Berhasil menjawab diskusi");
            JTextPane newReply = new JTextPane();
            newReply.setText(login.currentUser.getUsername() + " - " + JawabDiskusiField.getText());
            newReply.setFont(new java.awt.Font("Roboto", 1, 20));
            newReply.setForeground(new java.awt.Color(51, 153, 255));
            newReply.setEditable(false);
            newReply.setMaximumSize(new Dimension(700, 50));
            DiskusiPanel.add(newReply);

            JawabDiskusiField.setText("");
            DiskusiPanel.revalidate();
            DiskusiPanel.repaint();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_JawabDiskusiButtonActionPerformed

    private void DiskusiSayaTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DiskusiSayaTableMouseClicked
        int i = DiskusiSayaTable.getSelectedRow();
        TableModel model = DiskusiSayaTable.getModel();

        IdDiskusiHapusField.setText(model.getValueAt(i, 0).toString());
    }//GEN-LAST:event_DiskusiSayaTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Student().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddDiskusiButton;
    private javax.swing.JPanel AddDiskusiPanel;
    private javax.swing.JButton BookmarkNavigator;
    private javax.swing.JPanel BookmarkPanel;
    private javax.swing.JTable BookmarkTable;
    private javax.swing.JLabel DiskusiLabel;
    private javax.swing.JButton DiskusiNavigator;
    private javax.swing.JPanel DiskusiPanel;
    private javax.swing.JLabel DiskusiSayaLabel1;
    private javax.swing.JLabel DiskusiSayaLabel2;
    private javax.swing.JTable DiskusiSayaTable;
    private javax.swing.JScrollPane DiskusiScrollPane;
    private javax.swing.JTable DiskusiTable;
    private javax.swing.JButton HapusDiskusiButton;
    private javax.swing.JTextField IdDiskusiHapusField;
    private javax.swing.JButton JawabDiskusiButton;
    private javax.swing.JTextField JawabDiskusiField;
    private javax.swing.JButton MateriNavigator;
    private javax.swing.JPanel MateriPanel;
    private javax.swing.JTable MateriTable;
    private javax.swing.JTextField NamaDiskusiField;
    private javax.swing.JLabel NamaDiskusiLabel;
    private javax.swing.JLabel NamaDiskusiTerpilih;
    private javax.swing.JButton ProfileNavigator;
    private javax.swing.JPanel ProfilePanel;
    private javax.swing.JTabbedPane TabPane;
    private javax.swing.JTextField TopikDiskusiField;
    private javax.swing.JLabel TopikDiskusiLabel;
    private javax.swing.JLabel TopikDiskusiLabel3;
    private javax.swing.JLabel TopikDiskusiTerpilih;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel materiSayaLabel;
    private javax.swing.JPanel pdfPanel;
    private javax.swing.JPanel pdfPanel1;
    private javax.swing.JScrollPane pdfScrollPane;
    private javax.swing.JScrollPane pdfScrollPane1;
    private javax.swing.JLabel penambahanDiskusiLabel;
    private javax.swing.JLabel penambahanDiskusiLabel2;
    private javax.swing.JTextField profileEmailField;
    private javax.swing.JLabel profileEmailLabel;
    private javax.swing.JLabel profileLabel;
    private javax.swing.JTextField profileNameField;
    private javax.swing.JLabel profileNameLabel;
    private javax.swing.JButton profileUpdateButton;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
