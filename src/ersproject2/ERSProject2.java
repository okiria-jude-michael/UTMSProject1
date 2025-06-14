/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ersproject2;

/**
 *
 * @author Eng.MIKE
 */
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.File;

public class ERSProject2 extends JFrame {
    private JTextField txtRegId, txtName, txtFaculty, txtProjectTitle, txtContact, txtEmail;
    private JLabel lblImage;
    private JButton btnRegister, btnSearch, btnUpdate, btnDelete, btnClear, btnExit, btnUpload;
    private String imagePath = "";
    
    // Database connection details
    private static final String DB_URL = "jdbc:ucanaccess://VUE_Exhibition.accdb";
    private Connection conn;
    
    public ERSProject2() {
        initializeUI();
        connectToDatabase();
    }
    
    private void initializeUI() {
        setTitle("Victoria University Exhibition Registration System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        
        // Form components
        formPanel.add(new JLabel("Registration ID:"));
        txtRegId = new JTextField();
        formPanel.add(txtRegId);
        
        formPanel.add(new JLabel("Student Name:"));
        txtName = new JTextField();
        formPanel.add(txtName);
        
        formPanel.add(new JLabel("Faculty:"));
        txtFaculty = new JTextField();
        formPanel.add(txtFaculty);
        
        formPanel.add(new JLabel("Project Title:"));
        txtProjectTitle = new JTextField();
        formPanel.add(txtProjectTitle);
        
        formPanel.add(new JLabel("Contact Number:"));
        txtContact = new JTextField();
        formPanel.add(txtContact);
        
        formPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        formPanel.add(txtEmail);
        
        formPanel.add(new JLabel("Project Image:"));
        btnUpload = new JButton("Upload Image");
        btnUpload.addActionListener(e -> uploadImage());
        formPanel.add(btnUpload);
        
        // Image display
        lblImage = new JLabel("No image selected", SwingConstants.CENTER);
        lblImage.setPreferredSize(new Dimension(300, 200));
        
        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 6, 5, 5));
        
        btnRegister = new JButton("Register");
        btnRegister.addActionListener(e -> registerParticipant());
        
        btnSearch = new JButton("Search");
        btnSearch.addActionListener(e -> searchParticipant());
        
        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> updateParticipant());
        
        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> deleteParticipant());
        
        btnClear = new JButton("Clear");
        btnClear.addActionListener(e -> clearForm());
        
        btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> System.exit(0));
        
        buttonPanel.add(btnRegister);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnExit);
        
        // Add components to main panel
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(lblImage, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private void connectToDatabase() {
        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connected to database successfully");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    // (c)(i) Insert/Register a participant
    private void registerParticipant() {
        if (!validateInput()) return;
        
        String sql = "INSERT INTO Participants (RegistrationID, StudentName, Faculty, " +
                     "ProjectTitle, ContactNumber, Email, ImagePath) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, txtRegId.getText());
            pstmt.setString(2, txtName.getText());
            pstmt.setString(3, txtFaculty.getText());
            pstmt.setString(4, txtProjectTitle.getText());
            pstmt.setString(5, txtContact.getText());
            pstmt.setString(6, txtEmail.getText());
            pstmt.setString(7, imagePath);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Participant registered successfully!");
                clearForm();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Registration failed: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    // (c)(ii) Search participant by Registration ID
    private void searchParticipant() {
        String regId = txtRegId.getText().trim();
        if (regId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Registration ID to search",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String sql = "SELECT * FROM Participants WHERE RegistrationID = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, regId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                txtName.setText(rs.getString("StudentName"));
                txtFaculty.setText(rs.getString("Faculty"));
                txtProjectTitle.setText(rs.getString("ProjectTitle"));
                txtContact.setText(rs.getString("ContactNumber"));
                txtEmail.setText(rs.getString("Email"));
                
                imagePath = rs.getString("ImagePath");
                if (imagePath != null && !imagePath.isEmpty()) {
                    displayImage(imagePath);
                } else {
                    lblImage.setIcon(null);
                    lblImage.setText("No image available");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No participant found with ID: " + regId,
                    "Not Found", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Search failed: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    // (c)(iii) Update participant details
    private void updateParticipant() {
        if (!validateInput()) return;
        
        String sql = "UPDATE Participants SET StudentName = ?, Faculty = ?, ProjectTitle = ?, " +
                    "ContactNumber = ?, Email = ?, ImagePath = ? WHERE RegistrationID = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, txtName.getText());
            pstmt.setString(2, txtFaculty.getText());
            pstmt.setString(3, txtProjectTitle.getText());
            pstmt.setString(4, txtContact.getText());
            pstmt.setString(5, txtEmail.getText());
            pstmt.setString(6, imagePath);
            pstmt.setString(7, txtRegId.getText());
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Participant updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No participant found with ID: " + txtRegId.getText(),
                    "Not Found", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Update failed: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    // (c)(iv) Delete participant
    private void deleteParticipant() {
        String regId = txtRegId.getText().trim();
        if (regId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Registration ID to delete",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete participant with ID: " + regId + "?",
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) return;
        
        String sql = "DELETE FROM Participants WHERE RegistrationID = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, regId);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Participant deleted successfully!");
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "No participant found with ID: " + regId,
                    "Not Found", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Delete failed: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    // (d) Upload and display image
    private void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Project Image");
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        // Filter for image files
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Image files", "jpg", "jpeg", "png", "gif");
        fileChooser.addChoosableFileFilter(filter);
        
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            imagePath = selectedFile.getAbsolutePath();
            displayImage(imagePath);
        }
    }
    
    private void displayImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        // Scale the image to fit the label
        Image img = icon.getImage().getScaledInstance(
            lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
        lblImage.setIcon(new ImageIcon(img));
        lblImage.setText("");
    }
    
    // (e) Input validation
    private boolean validateInput() {
        if (txtRegId.getText().trim().isEmpty()) {
            showError("Registration ID cannot be empty");
            return false;
        }
        if (txtName.getText().trim().isEmpty()) {
            showError("Student name cannot be empty");
            return false;
        }
        if (txtFaculty.getText().trim().isEmpty()) {
            showError("Faculty cannot be empty");
            return false;
        }
        if (txtProjectTitle.getText().trim().isEmpty()) {
            showError("Project title cannot be empty");
            return false;
        }
        if (txtContact.getText().trim().isEmpty()) {
            showError("Contact number cannot be empty");
            return false;
        }
        if (txtEmail.getText().trim().isEmpty()) {
            showError("Email cannot be empty");
            return false;
        }
        if (!txtEmail.getText().trim().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            showError("Please enter a valid email address");
            return false;
        }
        return true;
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message,
            "Validation Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void clearForm() {
        txtRegId.setText("");
        txtName.setText("");
        txtFaculty.setText("");
        txtProjectTitle.setText("");
        txtContact.setText("");
        txtEmail.setText("");
        lblImage.setIcon(null);
        lblImage.setText("No image selected");
        imagePath = "";
    }
    
    public static void main(String[] args) {
        try {
            // Load UCanAccess driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            SwingUtilities.invokeLater(() -> {
                ERSProject2 app = new ERSProject2();
                app.setVisible(true);
            });
        } catch (ClassNotFoundException e) {
            System.err.println("UCanAccess driver not found");
            e.printStackTrace();
        }
    }
}
    

