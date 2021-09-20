/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import classes.Pessoa;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fabinho
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Proncipal
     *
     * @throws java.text.ParseException
     */
    public Principal() throws ParseException {
        initComponents();
        clearInputs();
    }

    ArrayList<Pessoa> pessoas = new ArrayList<>();
    
    public void clearInputs(){
        txtName.setText("");
        txtLastName.setText("");
        txtCPF.setText("");
        txtBirthData.setText("");
    }

    public Boolean validateFields() {
        if (txtName.getText().isBlank()) {
            System.out.println("nome");
            return false;
        }
        if (txtLastName.getText().isBlank()) {
            System.out.println("sobrenome");
            return false;
        }
        if (txtCPF.getText().equals("   .   .   -  ")) {
            System.out.println("cpf");
            return false;
        }
        if (txtBirthData.getText().equals("  /  /    ")) {
            System.out.println("data");
            return false;
        }
        return true;
    }

    public Boolean validateBirthDate() {
        String dateFormat = "dd/MM/yyyy";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDate birthDate = LocalDate.parse(txtBirthData.getText(), dateTimeFormatter);
            System.out.println("Aceito");
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Não Aceito");
            return false;
        }
    }

    public String isAdult(String year) {
        if (Integer.parseInt(year) >= 18) {
            return "Sim";
        }
        return "Não";
    }

    public Pessoa createObjectPessoa() {
        String name = txtName.getText();
        String lastName = txtLastName.getText();
        String strCPF = txtCPF.getText();
        String birthDate = txtBirthData.getText();
        Pessoa p = new Pessoa(name, lastName, strCPF, birthDate);

        return p;
    }

    public void createPessoa(Pessoa p) {
        if (pessoas.size() < 10) {
//        if (cont < 10) {
            if (findByCPF(p)) {
                pessoas.add(p);
                System.out.println("Adicionado");
            } else {
                System.out.println("Ja existe");
            }

        } else {
            System.out.println("Cheio");
        }
    }

    public boolean findByCPF(Pessoa p) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (p.getCPF().equals(pessoas.get(i).getCPF())) {
                return false;
            }
        }
        return true;
    }

    public Integer ageYears(String ageYears) {
//        Calendar birthDate = Calendar.getInstance();
//        try {
//            birthDate.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(ageYears));
//            Calendar dateNow = Calendar.getInstance();
//            int years = dateNow.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
//            birthDate.add(Calendar.YEAR, years);
//            if (dateNow.before(birthDate)) {
//                years--;
//            }
////            System.out.println(years + "anos");
//            return years;
//
//        } catch (ParseException ex) {
//            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
// ==========================================
        String dateFormat = "dd/MM/yyyy";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDate date = LocalDate.parse(ageYears, dateTimeFormatter);
            LocalDate dateNow = LocalDate.now();
            int diff = (int) ChronoUnit.YEARS.between(date, dateNow);
            System.out.println("Int" + diff);
            return diff;
        } catch (DateTimeParseException e) {
            return null;
        }

    }

    public void tableContent() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Nome");
        model.addColumn("Sobrenome");
        model.addColumn("CPF");
        model.addColumn("Data de Nascimento");
        model.addColumn("Idade");
        model.addColumn("Maior de idade ?");
        for (int i = 0; i < pessoas.size(); i++) {
            model.addRow(new String[]{
                pessoas.get(i).getName(),
                pessoas.get(i).getLastName(),
                pessoas.get(i).getCPF(),
                pessoas.get(i).getBirthDate(),
                ageYears(pessoas.get(i).getBirthDate()).toString(),
                isAdult(ageYears(pessoas.get(i).getBirthDate()).toString())

            });
        }
        jTable1.setModel(model);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtBirthData = new javax.swing.JFormattedTextField();
        txtCPF = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome:");

        txtName.setName(""); // NOI18N

        jLabel2.setText("Sobrenome:");

        jLabel3.setText("CPF:");

        jLabel4.setText("Data de Nascimento:");

        jButton1.setLabel("Adicionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        try {
            txtBirthData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                            .addComponent(txtLastName)
                                            .addComponent(txtBirthData, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCPF))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                                        .addComponent(jButton4))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(227, 227, 227)
                                .addComponent(jButton1)
                                .addGap(46, 46, 46)
                                .addComponent(jButton2)))
                        .addGap(74, 74, 74))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(156, 156, 156))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtBirthData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jButton3)
                .addContainerGap(208, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (validateFields() && validateBirthDate()) {
            createPessoa(createObjectPessoa());
            tableContent();
            clearInputs();

            
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

//        Calendar birthDate = Calendar.getInstance();
//        try {
//            birthDate.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(txtBirthData.getText()));
//            Calendar dateNow = Calendar.getInstance();
//            int years = dateNow.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
//            birthDate.add(Calendar.YEAR, years);
//            if (dateNow.before(birthDate)) {
//                years--;
//            }
//            System.out.println(years + "anos");
//
//        } catch (ParseException ex) {
//            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
//        }
// =========================================================================
        
        String year;
        String dateFormat = "dd/MM/yyyy";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDate date = LocalDate.parse(txtBirthData.getText(), dateTimeFormatter);
            LocalDate dateNow = LocalDate.now();
//            long diff = ChronoUnit.YEARS.between(date, dateNow);
            int diff = (int) ChronoUnit.YEARS.between(date, dateNow);
            System.out.println("Long" + diff);
            year = Long.toString(diff);
            System.out.println("String" + year);
           
        } catch (DateTimeParseException e) {
            System.out.println(e);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String adult;
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Sobrenome");
        model.addColumn("CPF");
        model.addColumn("Data de Nascimento");
        model.addColumn("Idade");
        for (int i = 0; i < pessoas.size(); i++) {
            model.addRow(new String[]{
                pessoas.get(i).getName(),
                pessoas.get(i).getLastName(),
                pessoas.get(i).getCPF(),
                pessoas.get(i).getBirthDate(),
                ageYears(pessoas.get(i).getBirthDate()).toString(),});
        }
        jTable1.setModel(model);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String teste = txtBirthData.getText();
        System.out.println(teste);
        if(teste.equals("  /  /    ")){
            System.out.println("Vazio");
        }else{
            System.out.println("n Vazio");
            
        }
            
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Principal().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JFormattedTextField txtBirthData;
    private javax.swing.JFormattedTextField txtCPF;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
