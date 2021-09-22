/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import classes.Pessoa;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fábio Pereira Reis
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Proncipal
     *
     * @throws java.text.ParseException
     */
    public Principal() throws ParseException {
        initComponents();
        clearInputs(); // método para limpar campos
    }
    
    // lista de objetos do tipo Pessoa (lista pessoas)
    ArrayList<Pessoa> pessoas = new ArrayList<>();
   
    // método para limpar campos
    public void clearInputs() {
        txtName.setText("");
        txtLastName.setText("");
        txtCPF.setText("");
        txtBirthData.setText("");
    }

    // método que gera janelas de informação dinâmicas
    public void messageView(String title, String message) {
        JFrame frame = new JFrame(title);
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);

    }

    // método para verificar se os campos estão preenchidos
    public Boolean validateFields() {
        if (txtName.getText().isBlank()) {
            System.out.println("nome");
            messageView("Campo Obrigatório", "Nome");
            return false;
        }
        if (txtLastName.getText().isBlank()) {
            System.out.println("sobrenome");
            messageView("Campo Obrigatório", "Sobrenome");
            return false;
        }
        if (txtCPF.getText().equals("   .   .   -  ")) {
            System.out.println("cpf");
            messageView("Campo Obrigatório", "CPF");
            return false;
        }
        if (txtBirthData.getText().equals("  /  /    ")) {
            System.out.println("data");
            messageView("Campo Obrigatório", "Data de nascimento");
            return false;
        }
        return true;
    }

    // método que valida se a data de nascimento é válida
    public Boolean validateBirthDate() {
        String dateFormat = "dd/MM/yyyy";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        try {
//            LocalDate birthDate = LocalDate.parse(txtBirthData.getText(), dateTimeFormatter);
            LocalDate.parse(txtBirthData.getText(), dateTimeFormatter);
            System.out.println("Aceito");
            return true;
        } catch (DateTimeParseException e) {
            messageView("Revise os dados", "Data de nascimento inválida");
            System.out.println("Não Aceito");
            return false;
        }
    }

    // método que verifica se a pessoa tem idade igual ou maior que 18 anos
    public String isAdult(String year) {
        if (Integer.parseInt(year) >= 18) {
            return "Sim";
        }
        return "Não";
    }

    // método que verificar se o CPF já foi cadastrado
    public boolean findByCPF(Pessoa p) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (p.getCPF().equals(pessoas.get(i).getCPF())) {
                return false;
            }
        }
        return true;
    }

    //  método que constroe um objeto do tipo pessoa
    public Pessoa buildObjectPessoa() {
        String name = txtName.getText();
        String lastName = txtLastName.getText();
        String strCPF = txtCPF.getText();
        String birthDate = txtBirthData.getText();
        Pessoa p = new Pessoa(name, lastName, strCPF, birthDate);

        return p;
    }

    // método que adiciona um objeto do tipo pessoa na lista de pessoas
    public void createPessoa(Pessoa p) {
        if (pessoas.size() < 10) {
            if (findByCPF(p)) {
                pessoas.add(p);
                System.out.println("Adicionado");
                messageView("Sucesso !", "Candidato adicionado com sucesso!");
            } else {
                System.out.println("Ja existe");
                messageView("Atenção ", "Candidato já adicionado no sistema");
            }

        } else {
            System.out.println("Cheio");
                messageView("Atenção ", "Limite máximo de cadastros atingido");
        }
    }

    // método que calcula a idade de uma pessoa
    public Integer yearsOld(String ageYears) {
        String dateFormat = "dd/MM/yyyy";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDate date = LocalDate.parse(ageYears, dateTimeFormatter);
            LocalDate dateNow = LocalDate.now();
            int diff = (int) ChronoUnit.YEARS.between(date, dateNow);
            return diff;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    // método que alimenta a tabela com a lista de pessoas
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
                yearsOld(pessoas.get(i).getBirthDate()).toString(),
                isAdult(yearsOld(pessoas.get(i).getBirthDate()).toString())

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
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();

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
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "Sobrenome", "CPF", "Data de nascimento", "Idade", "Maior de idade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton3.setText("Salvar");
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

        jCheckBox1.setText("Trainee 1");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("Trainee 2");

        jCheckBox3.setText("Trainee 3");

        jCheckBox4.setText("Trainee 4");

        jCheckBox5.setText("Trainee 5");

        jLabel5.setText("Vagas disponiveis");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBirthData, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtLastName)
                                    .addComponent(txtCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 210, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addComponent(jLabel5)
                            .addComponent(jCheckBox2)
                            .addComponent(jCheckBox3)
                            .addComponent(jCheckBox4)
                            .addComponent(jCheckBox5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(176, 176, 176))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel5)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox1)
                            .addComponent(jLabel1)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jCheckBox3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtBirthData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jButton3)
                .addGap(4, 4, 4)
                .addComponent(jButton4)
                .addGap(12, 12, 12)
                .addComponent(jButton2)
                .addGap(230, 230, 230))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (validateFields() && validateBirthDate()) {
            createPessoa(buildObjectPessoa());
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
        } catch (DateTimeParseException e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
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
                yearsOld(pessoas.get(i).getBirthDate()).toString(),});
        }
        jTable1.setModel(model);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String teste = txtBirthData.getText();
        System.out.println(teste);
        if (teste.equals("  /  /    ")) {
            System.out.println("Vazio");
        } else {
            System.out.println("n Vazio");

        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

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
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JFormattedTextField txtBirthData;
    private javax.swing.JFormattedTextField txtCPF;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
