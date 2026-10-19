package medconnect.view;

import medconnect.dao.PacienteDAO;
import medconnect.model.Paciente;
import medconnect.exception.BancoException;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Tela Prontuário - Registro clínico
 */
public class TelaProntuario extends javax.swing.JFrame {
    
    private PacienteDAO pacienteDAO;
    private Paciente pacienteSelecionado;
    
    public TelaProntuario() {
        initComponents();
        personalizarTela();
        pacienteDAO = new PacienteDAO();
    }
    
    private void personalizarTela() {
        if (jPanelMenu != null) {
            jPanelMenu.setBackground(new Color(30, 64, 175));
        }
        if (jPanelPrincipal != null) {
            jPanelPrincipal.setBackground(new Color(223, 240, 246));
        }
        if (lblTitulo != null) {
            lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 95));
            lblTitulo.setText("Prontuário");
        }
        if (btnExcluir != null) {
            btnExcluir.setBackground(new Color(208, 47, 19));
            btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 32));
            btnExcluir.setForeground(Color.WHITE);
        }
        if (btnSalvar != null) {
            btnSalvar.setBackground(new Color(19, 123, 208));
            btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 32));
            btnSalvar.setForeground(Color.WHITE);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanelMenu = new javax.swing.JPanel();
        btnDashboard = new javax.swing.JLabel();
        btnPacientes = new javax.swing.JLabel();
        btnMedicos = new javax.swing.JLabel();
        btnConsultas = new javax.swing.JLabel();
        btnProntuarios = new javax.swing.JLabel();
        jPanelPrincipal = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jPanelDadosPaciente = new javax.swing.JPanel();
        lblNomeLabel = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblCPFLabel = new javax.swing.JLabel();
        lblCPF = new javax.swing.JLabel();
        lblIdadeLabel = new javax.swing.JLabel();
        lblIdade = new javax.swing.JLabel();
        lblTelefoneLabel = new javax.swing.JLabel();
        lblTelefone = new javax.swing.JLabel();
        lblHistorico = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstHistorico = new javax.swing.JList<>();
        lblObservacoes = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObservacoes = new javax.swing.JTextArea();
        lblReceita = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtReceita = new javax.swing.JTextArea();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MedConnect - Prontuário");
        setMinimumSize(new java.awt.Dimension(1400, 900));

        jPanelMenu.setMinimumSize(new java.awt.Dimension(250, 0));
        jPanelMenu.setPreferredSize(new java.awt.Dimension(250, 0));

        btnDashboard.setFont(new java.awt.Font("Tahoma", 0, 36));
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setText("Dashboard");
        btnDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDashboardMouseClicked(evt);
            }
        });

        btnPacientes.setFont(new java.awt.Font("Tahoma", 0, 36));
        btnPacientes.setForeground(new java.awt.Color(255, 255, 255));
        btnPacientes.setText("Pacientes");
        btnPacientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPacientesMouseClicked(evt);
            }
        });

        btnMedicos.setFont(new java.awt.Font("Tahoma", 0, 36));
        btnMedicos.setForeground(new java.awt.Color(255, 255, 255));
        btnMedicos.setText("Médicos");
        btnMedicos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMedicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMedicosMouseClicked(evt);
            }
        });

        btnConsultas.setFont(new java.awt.Font("Tahoma", 0, 36));
        btnConsultas.setForeground(new java.awt.Color(255, 255, 255));
        btnConsultas.setText("Consultas");
        btnConsultas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConsultasMouseClicked(evt);
            }
        });

        btnProntuarios.setFont(new java.awt.Font("Tahoma", 0, 36));
        btnProntuarios.setForeground(new java.awt.Color(255, 255, 255));
        btnProntuarios.setText("Prontuários");
        btnProntuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnProntuarios)
                    .addComponent(btnConsultas)
                    .addComponent(btnMedicos)
                    .addComponent(btnPacientes)
                    .addComponent(btnDashboard))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(btnDashboard)
                .addGap(50, 50, 50)
                .addComponent(btnPacientes)
                .addGap(50, 50, 50)
                .addComponent(btnMedicos)
                .addGap(50, 50, 50)
                .addComponent(btnConsultas)
                .addGap(50, 50, 50)
                .addComponent(btnProntuarios)
                .addContainerGap(400, Short.MAX_VALUE))
        );

        jPanelPrincipal.setPreferredSize(new java.awt.Dimension(1150, 900));

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 95));
        lblTitulo.setText("Prontuário");

        jPanelDadosPaciente.setBackground(new java.awt.Color(158, 157, 157));
        jPanelDadosPaciente.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblNomeLabel.setFont(new java.awt.Font("Tahoma", 1, 36));
        lblNomeLabel.setText("Nome:");

        lblNome.setFont(new java.awt.Font("Tahoma", 1, 36));
        lblNome.setText("João da Silva");

        lblCPFLabel.setFont(new java.awt.Font("Tahoma", 1, 36));
        lblCPFLabel.setText("CPF:");

        lblCPF.setFont(new java.awt.Font("Tahoma", 1, 36));
        lblCPF.setText("123.456.789-00");

        lblIdadeLabel.setFont(new java.awt.Font("Tahoma", 1, 36));
        lblIdadeLabel.setText("Idade:");

        lblIdade.setFont(new java.awt.Font("Tahoma", 1, 36));
        lblIdade.setText("40 anos");

        lblTelefoneLabel.setFont(new java.awt.Font("Tahoma", 1, 36));
        lblTelefoneLabel.setText("Telefone:");

        lblTelefone.setFont(new java.awt.Font("Tahoma", 1, 36));
        lblTelefone.setText("11999999999");

        javax.swing.GroupLayout jPanelDadosPacienteLayout = new javax.swing.GroupLayout(jPanelDadosPaciente);
        jPanelDadosPaciente.setLayout(jPanelDadosPacienteLayout);
        jPanelDadosPacienteLayout.setHorizontalGroup(
            jPanelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosPacienteLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDadosPacienteLayout.createSequentialGroup()
                        .addComponent(lblNomeLabel)
                        .addGap(20, 20, 20)
                        .addComponent(lblNome))
                    .addGroup(jPanelDadosPacienteLayout.createSequentialGroup()
                        .addComponent(lblCPFLabel)
                        .addGap(20, 20, 20)
                        .addComponent(lblCPF))
                    .addGroup(jPanelDadosPacienteLayout.createSequentialGroup()
                        .addComponent(lblIdadeLabel)
                        .addGap(20, 20, 20)
                        .addComponent(lblIdade))
                    .addGroup(jPanelDadosPacienteLayout.createSequentialGroup()
                        .addComponent(lblTelefoneLabel)
                        .addGap(20, 20, 20)
                        .addComponent(lblTelefone)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanelDadosPacienteLayout.setVerticalGroup(
            jPanelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDadosPacienteLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeLabel)
                    .addComponent(lblNome))
                .addGap(20, 20, 20)
                .addGroup(jPanelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCPFLabel)
                    .addComponent(lblCPF))
                .addGap(20, 20, 20)
                .addGroup(jPanelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdadeLabel)
                    .addComponent(lblIdade))
                .addGap(20, 20, 20)
                .addGroup(jPanelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefoneLabel)
                    .addComponent(lblTelefone))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        lblHistorico.setFont(new java.awt.Font("Tahoma", 1, 24));
        lblHistorico.setText("Histórico de Consultas");

        lstHistorico.setFont(new java.awt.Font("Tahoma", 0, 24));
        lstHistorico.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Ana - 15/12/2025 - Cardiograma", "Ana - 15/12/2025 - Cardiograma", "Ana - 15/12/2025 - Cardiograma" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstHistorico);

        lblObservacoes.setFont(new java.awt.Font("Tahoma", 1, 24));
        lblObservacoes.setText("Observações:");

        txtObservacoes.setColumns(20);
        txtObservacoes.setFont(new java.awt.Font("Tahoma", 0, 24));
        txtObservacoes.setRows(5);
        jScrollPane3.setViewportView(txtObservacoes);

        lblReceita.setFont(new java.awt.Font("Tahoma", 1, 24));
        lblReceita.setText("Receita Médica:");

        txtReceita.setColumns(20);
        txtReceita.setFont(new java.awt.Font("Tahoma", 0, 24));
        txtReceita.setRows(5);
        jScrollPane4.setViewportView(txtReceita);

        btnExcluir.setFont(new java.awt.Font("Tahoma", 1, 32));
        btnExcluir.setText("Excluir");
        btnExcluir.setPreferredSize(new java.awt.Dimension(322, 67));
        // ✅ CORREÇÃO: Chamar o método separado
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSalvar.setFont(new java.awt.Font("Tahoma", 1, 32));
        btnSalvar.setText("Salvar");
        btnSalvar.setPreferredSize(new java.awt.Dimension(322, 67));
        // ✅ CORREÇÃO: Chamar o método separado
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblReceita)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObservacoes)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHistorico)
                    .addComponent(jPanelDadosPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitulo)
                .addGap(30, 30, 30)
                .addComponent(jPanelDadosPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblHistorico)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblObservacoes)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblReceita)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 1138, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );

        pack();
    }

    private void btnDashboardMouseClicked(java.awt.event.MouseEvent evt) {
        new Dashboard().setVisible(true);
        this.dispose();
    }

    private void btnPacientesMouseClicked(java.awt.event.MouseEvent evt) {
        new TelaPacientes().setVisible(true);
        this.dispose();
    }

    private void btnMedicosMouseClicked(java.awt.event.MouseEvent evt) {
        new TelaMedicos().setVisible(true);
        this.dispose();
    }

    private void btnConsultasMouseClicked(java.awt.event.MouseEvent evt) {
        new TelaAgenda().setVisible(true);
        this.dispose();
    }

    private void btnProntuariosMouseClicked(java.awt.event.MouseEvent evt) {
        // Já está na tela de prontuários
    }

    /**
     * ✅ MÉTODO HANDLER DO BOTÃO SALVAR
     */
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this, 
            "Funcionalidade de salvar prontuário em desenvolvimento", 
            "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * ✅ MÉTODO HANDLER DO BOTÃO EXCLUIR
     */
    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this, 
            "Funcionalidade de excluir prontuário em desenvolvimento", 
            "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelDadosPaciente;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblNomeLabel;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblCPFLabel;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblIdadeLabel;
    private javax.swing.JLabel lblIdade;
    private javax.swing.JLabel lblTelefoneLabel;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblHistorico;
    private javax.swing.JList<String> lstHistorico;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblObservacoes;
    private javax.swing.JTextArea txtObservacoes;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblReceita;
    private javax.swing.JTextArea txtReceita;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel btnDashboard;
    private javax.swing.JLabel btnPacientes;
    private javax.swing.JLabel btnMedicos;
    private javax.swing.JLabel btnConsultas;
    private javax.swing.JLabel btnProntuarios;
    // End of variables declaration
}