/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.consumirapires;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kiwar
 */
public class FormStudent extends javax.swing.JFrame {

    /**
     * Creates new form FormStudent
     */
    private StudentApiConsumer apiConsumer;
    private final String[] colums = {"Cedula", "Nombre", "Apellido", "Direccion", "Telefono"};
    private DefaultTableModel modelTable;

    public FormStudent() {
        initComponents();
        apiConsumer = new StudentApiConsumer();
        modelTable = new DefaultTableModel(colums, 0);
        this.fillTable();
        this.tbStudentTable.setModel(modelTable);
        selectTable();
    }

    private void fillTable() {
        this.modelTable.setRowCount(0);
        apiConsumer.getAll().forEach(student -> {
            this.modelTable.addRow(new Object[]{student.getId(), student.getNombre(), student.getApellido(), student.getDireccion(), student.getTelefono()});
        });

    }

    private void searchStudent() {
        if (!this.txtCedula.equals("")) {
            this.errorCed.setText("");
            Student student = apiConsumer.getStudent(this.txtCedula.getText());
            if (student != null) {
                this.modelTable.setRowCount(0);
                this.modelTable.addRow(new Object[]{student.getId(), student.getNombre(), student.getApellido(), student.getDireccion(), student.getTelefono()});
            } else {
                JOptionPane.showMessageDialog(this, "Estudiante No encontrado");
            }

        } else {
            this.errorCed.setText("requerido");
        }
    }

    private void selectTable() {
        tbStudentTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int row = tbStudentTable.getSelectedRow();
            if (row != -1) {
                txtCedula.setText(tbStudentTable.getValueAt(row, 0).toString());
                txtName.setText(tbStudentTable.getValueAt(row, 1).toString());
                txtApellido.setText(tbStudentTable.getValueAt(row, 2).toString());
                txtDireccion.setText(tbStudentTable.getValueAt(row, 3).toString());
                txtphone.setText(tbStudentTable.getValueAt(row, 4).toString());
            } else {
                cleanfields();
            }
        });
    }

    private void delete() {
        int row = tbStudentTable.getSelectedColumn();
        if (row != -1) {
            String cedula = txtCedula.getText();
            if (apiConsumer.delete(cedula)) {
                JOptionPane.showMessageDialog(this, "Se elimino el estudiante: " + cedula);
                fillTable();
                cleanfields();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un Estudiante");
        }
    }

    private void editar() {
        String cedula = txtCedula.getText();
        String nombre = txtName.getText();
        String apellido = txtApellido.getText();
        String direccion = txtDireccion.getText();
        String telefono = txtphone.getText();
        if (validarCampos(cedula, nombre, apellido, direccion, telefono)) {
            Student student = new Student(cedula, nombre, apellido, direccion, telefono);
            if (apiConsumer.update(student)) {
                JOptionPane.showMessageDialog(this, "Se edito");
                fillTable();
                cleanfields();
            }
        }
    }

    private void cleanfields() {
        txtCedula.setText("");
        txtName.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
        txtphone.setText("");
    }

    private void newStudent() {
        String cedula = txtCedula.getText();
        String nombre = txtName.getText();
        String apellido = txtApellido.getText();
        String direccion = txtDireccion.getText();
        String telefono = txtphone.getText();
        if (validarCampos(cedula, nombre, apellido, direccion, telefono)) {
            Student student = new Student(cedula, nombre, apellido, direccion, telefono);
            if (apiConsumer.create(student)) {
                JOptionPane.showMessageDialog(this, "Guardo");
                fillTable();
                cleanfields();
            }
        }
    }

    private boolean validarCampos(String... campos) {
        boolean isValid = true;
        String errors = "Requerida";
        JLabel[] lb = {
            errorCed,
            errorNom,
            errorApe,
            errorDir,
            errorTel
        };

        for (int i = 0; i < campos.length; i++) {
            if (campos[i].isEmpty() || campos[i].isBlank()) {
                lb[i].setText(errors);
                isValid = false;
            } else {
                lb[i].setText("");

            }
        }
        return isValid;
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtphone = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        bntClean = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbStudentTable = new javax.swing.JTable();
        errorCed = new javax.swing.JLabel();
        errorNom = new javax.swing.JLabel();
        errorApe = new javax.swing.JLabel();
        errorDir = new javax.swing.JLabel();
        errorTel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Formulario Estudiante");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 673, -1));

        jLabel2.setText("Cedula");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 47, -1, -1));

        jLabel3.setText("Nombre");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 72, -1, -1));

        jLabel4.setText("Apellido");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 97, -1, -1));

        jLabel5.setText("Direccion");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 122, -1, -1));

        jLabel6.setText("Telefono");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 147, -1, -1));

        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });
        jPanel1.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 45, 386, -1));
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 120, 386, -1));
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 95, 386, -1));
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 70, 386, -1));
        jPanel1.add(txtphone, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 145, 386, -1));

        btnEdit.setText("Editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel1.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, -1, -1));

        btnDelete.setText("Eliminar");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, -1, -1));

        bntClean.setText("Limpiar");
        bntClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCleanActionPerformed(evt);
            }
        });
        jPanel1.add(bntClean, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 200, -1, -1));

        btnCreate.setText("Crear");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });
        jPanel1.add(btnCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, -1, -1));

        tbStudentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jScrollPane1.setViewportView(tbStudentTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 660, 250));
        jPanel1.add(errorCed, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, -1, -1));
        jPanel1.add(errorNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, -1, -1));
        jPanel1.add(errorApe, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, -1, -1));
        jPanel1.add(errorDir, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, -1, -1));
        jPanel1.add(errorTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, -1, -1));

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        newStudent();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void bntCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCleanActionPerformed
        // TODO add your handling code here:
        fillTable();
        cleanfields();
    }//GEN-LAST:event_bntCleanActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        editar();
    }//GEN-LAST:event_btnEditActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        searchStudent();
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
            java.util.logging.Logger.getLogger(FormStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        FlatMacDarkLaf.setup();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntClean;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel errorApe;
    private javax.swing.JLabel errorCed;
    private javax.swing.JLabel errorDir;
    private javax.swing.JLabel errorNom;
    private javax.swing.JLabel errorTel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbStudentTable;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtphone;
    // End of variables declaration//GEN-END:variables
}
