/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import classes.Pessoa;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
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
        jobs1.setSelected(false);
        jobs2.setSelected(false);
        jobs3.setSelected(false);
        jobs4.setSelected(false);
        jobs5.setSelected(false);

    }
    // método para resetar alguns parametros depois de salvar a lista
    public void resetFormInputs(){
        pessoas.clear();
        tableContent();
        jobs1.setEnabled(true);
        jobs2.setEnabled(true);
        jobs3.setEnabled(true);
        jobs4.setEnabled(true);
        jobs5.setEnabled(true);
        countJobs1 = 0;
        countJobs2 = 0;
        countJobs3 = 0;
        countJobs4 = 0;
        countJobs5 = 0;
        
    }

    // método que gera janelas de informação dinâmicas
    public void messageView(String title, String message) {
        JFrame frame = new JFrame(title);
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);

    }

    // método para verificar se os campos estão preenchidos
    public Boolean validateFields() {
        if (txtName.getText().isBlank()) {
            messageView("Campo Obrigatório", "Por favor preencher o campo nome");
            return false;
        }
        if (txtLastName.getText().isBlank()) {
            messageView("Campo Obrigatório", "Por favor preencher o campo sobrenome");
            return false;
        }
        if (txtCPF.getText().equals("   .   .   -  ")) {
            messageView("Campo Obrigatório", "Por favor preencher o campo CPF");
            return false;
        }
        if (txtBirthData.getText().equals("  /  /    ")) {
            messageView("Campo Obrigatório", "Por favor preencher o campo data de nascimento");
            return false;
        }
        return true;
    }

    // método que valida se a data de nascimento é válida
    public Boolean validateBirthDate() {
        String dateFormat = "dd/MM/yyyy";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDate.parse(txtBirthData.getText(), dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            messageView("Revise os dados", "Data de nascimento inválida");
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

    // método  que verificar se o CPF já foi cadastrado
    public boolean validateCPF(String cpf) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (cpf.equals(pessoas.get(i).getCPF())) {
                messageView("Atenção ", "Candidato já adicionado no sistema, CPF duplicado");
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
            pessoas.add(p);
            countJobs();
            messageView("Sucesso !", "Candidato adicionado com sucesso!");
            clearInputs();
        } else {
            messageView("Atenção ", "Limite máximo de candidatos atingido");
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
    // variáveis de contagem das vagas
    private int countJobs1 = 0;
    private int countJobs2 = 0;
    private int countJobs3 = 0;
    private int countJobs4 = 0;
    private int countJobs5 = 0;

    // método para contar as vagas e desabilitar os checkBox
    public void countJobs() {

        if (jobs1.isSelected()) {
            countJobs1++;
            if (countJobs1 >= 3) {
                messageView("Limite atingido para vagas 1", "Quantidade máxima de candidatos atingida");
                jobs1.setSelected(false);
                jobs1.setEnabled(false);
            }
        }
        if (jobs2.isSelected()) {
            countJobs2++;
            if (countJobs2 >= 3) {
                messageView("Limite atingido para vagas 2", "Quantidade máxima de candidatos atingida");
                jobs2.setSelected(false);
                jobs2.setEnabled(false);
            }
        }
        if (jobs3.isSelected()) {
            countJobs3++;
            if (countJobs3 >= 3) {
                messageView("Limite atingido para vagas 3", "Quantidade máxima de candidatos atingida");
                jobs3.setSelected(false);
                jobs3.setEnabled(false);
            }
        }
        if (jobs4.isSelected()) {
            countJobs4++;
            if (countJobs4 >= 3) {
                messageView("Limite atingido para vagas 4", "Quantidade máxima de candidatos atingida");
                jobs4.setSelected(false);
                jobs4.setEnabled(false);
            }
        }
        if (jobs5.isSelected()) {
            countJobs5++;
            if (countJobs5 >= 3) {
                messageView("Limite atingido para vagas 5", "Quantidade máxima de candidatos atingida");
                jobs5.setSelected(false);
                jobs5.setEnabled(false);
            }
        }

    }

    // método de validação das vagas
    public boolean validateJobs() {
        boolean isValid = true;

        if (jobs1.isSelected() && countJobs1 == 3) {
            isValid = false;
        }
        if (jobs2.isSelected() && countJobs2 == 3) {
            isValid = false;
        }
        if (jobs3.isSelected() && countJobs3 == 3) {
            isValid = false;
        }
        if (jobs4.isSelected() && countJobs4 == 3) {
            isValid = false;
        }
        if (jobs5.isSelected() && countJobs5 == 3) {
            isValid = false;
        }
        return isValid;
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
        txtBirthData = new javax.swing.JFormattedTextField();
        txtCPF = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jobs1 = new javax.swing.JCheckBox();
        jobs2 = new javax.swing.JCheckBox();
        jobs3 = new javax.swing.JCheckBox();
        jobs4 = new javax.swing.JCheckBox();
        jobs5 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

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

        jobs1.setText("Trainee 1");
        jobs1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jobs1ActionPerformed(evt);
            }
        });

        jobs2.setText("Trainee 2");

        jobs3.setText("Trainee 3");

        jobs4.setText("Trainee 4");

        jobs5.setText("Trainee 5");

        jLabel5.setText("Vagas disponiveis");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Processo Trainee 2021");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/telas/logo.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 932, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel2))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtLastName)
                                                    .addComponent(txtCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel1)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jobs2)
                                    .addComponent(jobs3)
                                    .addComponent(jLabel5)
                                    .addComponent(jobs1)
                                    .addComponent(jobs4)
                                    .addComponent(jobs5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(222, 222, 222)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3)
                                    .addComponent(jButton1))))
                        .addGap(77, 77, 77)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(41, 41, 41))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(98, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jobs1)
                                    .addComponent(jLabel1)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jobs3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jobs4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jobs2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtBirthData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)))
                        .addComponent(jobs5)
                        .addGap(87, 87, 87)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jButton3)
                .addGap(47, 47, 47))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (validateFields() && validateBirthDate() && validateCPF(txtCPF.getText()) && validateJobs()) {
            createPessoa(buildObjectPessoa());
            tableContent();
            clearInputs();
        }
        System.out.println(countJobs1);
        System.out.println(countJobs2);
        System.out.println(countJobs3);
        System.out.println(countJobs4);
        System.out.println(countJobs5);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        // função para selecionar o diretório para salvar o arquivo
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Escolha um diretório para salvar o arquivo: ");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (jfc.getSelectedFile().isDirectory()) {
                System.out.println("Diretório selecionado: " + jfc.getSelectedFile());
            }
        }
        // =====================================================================
        // criação do arquivo de texto com a lista dos candidatos
        StringBuilder saida = new StringBuilder();
        saida.append("Nome;Sobrenome;CPF;Data nascimento;Idade")
                .append("\n");
        for (int i = 0; i < pessoas.size(); i++) {
            saida.append(pessoas.get(i).getName())
                    .append(";")
                    .append(pessoas.get(i).getLastName())
                    .append(";")
                    .append(pessoas.get(i).getCPF())
                    .append(";")
                    .append(pessoas.get(i).getBirthDate())
                    .append(";")
                    .append(yearsOld(pessoas.get(i).getBirthDate()))
                    .append(";")
                    .append("\n");
        }
        // =====================================================================
        // função que exporta a lista com os dados dos candidatos para um arquivo csv
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileOutputStream(jfc.getSelectedFile() + File.separator + "saida.csv"));
            pw.write(saida.toString());
            pw.flush();
            pw.close();
            messageView("Lista enviada", "Candidatos cadastrados com sucesso !");
            resetFormInputs();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jobs1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jobs1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jobs1ActionPerformed

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
                    Logger.getLogger(Principal.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JCheckBox jobs1;
    private javax.swing.JCheckBox jobs2;
    private javax.swing.JCheckBox jobs3;
    private javax.swing.JCheckBox jobs4;
    private javax.swing.JCheckBox jobs5;
    private javax.swing.JFormattedTextField txtBirthData;
    private javax.swing.JFormattedTextField txtCPF;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
