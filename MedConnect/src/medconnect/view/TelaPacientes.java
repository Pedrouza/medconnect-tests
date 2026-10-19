package medconnect.view;

import medconnect.model.Paciente;
import medconnect.service.PacienteService;
import medconnect.exception.NegocioException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Tela de Pacientes - CRUD completo
 */
public class TelaPacientes extends javax.swing.JFrame {
    
    private final PacienteService service;
    private Paciente pacienteSelecionado;
    private boolean modoEdicao = false;
    
    private static final Color COR_MENU_LATERAL = new Color(30, 64, 175);
    private static final Color COR_FUNDO_PRINCIPAL = new Color(223, 240, 246);
    private static final Color COR_BOTAO_EDITAR = new Color(19, 123, 208);
    private static final Color COR_BOTAO_EXCLUIR = new Color(208, 47, 19);
    private static final Color COR_BOTAO_CADASTRAR = new Color(16, 185, 129);
    private static final Color COR_BOTAO_SALVAR = new Color(19, 123, 208);
    private static final Color COR_BOTAO_BUSCAR = new Color(240, 240, 240);
    
    public TelaPacientes() {
        this.service = new PacienteService();
        initComponents();
        personalizarTela();
        aplicarMascaras();
        carregarTabela();
    }
    
    private void personalizarTela() {
        jPanelMenu.setBackground(COR_MENU_LATERAL);
        lblMenuDashboard.setForeground(Color.WHITE);
        lblMenuPacientes.setForeground(Color.WHITE);
        lblMenuMedicos.setForeground(Color.WHITE);
        lblMenuConsultas.setForeground(Color.WHITE);
        lblMenuProntuarios.setForeground(Color.WHITE);
        
        Font menuFont = new Font("Tahoma", Font.PLAIN, 36);
        lblMenuDashboard.setFont(menuFont);
        lblMenuPacientes.setFont(menuFont);
        lblMenuMedicos.setFont(menuFont);
        lblMenuConsultas.setFont(menuFont);
        lblMenuProntuarios.setFont(menuFont);
        
        jPanelPrincipal.setBackground(COR_FUNDO_PRINCIPAL);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 100));
        lblTitulo.setText("Pacientes");
        
        btnEditar.setBackground(COR_BOTAO_EDITAR);
        btnEditar.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnEditar.setForeground(Color.WHITE);
        
        btnExcluir.setBackground(COR_BOTAO_EXCLUIR);
        btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnExcluir.setForeground(Color.WHITE);
        
        btnCadastrar.setBackground(COR_BOTAO_CADASTRAR);
        btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnCadastrar.setForeground(Color.WHITE);
        
        btnSalvar.setBackground(COR_BOTAO_SALVAR);
        btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setEnabled(false);
        
        btnBuscar.setBackground(COR_BOTAO_BUSCAR);
        btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 24));
        
        Font labelFont = new Font("Tahoma", Font.BOLD, 32);
        lblNome.setFont(labelFont);
        lblCPF.setFont(labelFont);
        lblDataNasc.setFont(labelFont);
        lblTelefone.setFont(labelFont);
        lblEmail.setFont(labelFont);
        lblEndereco.setFont(labelFont);
        
        Font inputFont = new Font("Tahoma", Font.PLAIN, 24);
        txtNome.setFont(inputFont);
        txtCPF.setFont(inputFont);
        txtDataNascimento.setFont(inputFont);
        txtTelefone.setFont(inputFont);
        txtEmail.setFont(inputFont);
        txtEndereco.setFont(inputFont);
    }
    
    private void aplicarMascaras() {
        try {
            javax.swing.text.MaskFormatter mascaraCPF = 
                new javax.swing.text.MaskFormatter("###.###.###-##");
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mascaraCPF));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        
        try {
            javax.swing.text.MaskFormatter mascaraTelefone = 
                new javax.swing.text.MaskFormatter("(##) #####-####");
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mascaraTelefone));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        
        try {
            javax.swing.text.MaskFormatter mascaraData = 
                new javax.swing.text.MaskFormatter("##/##/####");
            txtDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(mascaraData));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
    }
    
    private void carregarTabela() {
        try {
            List<Paciente> pacientes = service.listarTodos();
            DefaultTableModel modelo = (DefaultTableModel) tblPacientes.getModel();
            modelo.setRowCount(0);
            
            for (Paciente p : pacientes) {
                modelo.addRow(new Object[]{
                    p.getId(),
                    p.getNome(),
                    p.getCpf(),
                    p.getTelefone(),
                    p.getEmail()
                });
            }
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao carregar pacientes: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Paciente coletarDadosDaTela() {
        if (txtNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome é obrigatório!", 
                "Erro", JOptionPane.ERROR_MESSAGE);
            txtNome.requestFocus();
            return null;
        }
        
        if (txtCPF.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "CPF é obrigatório!", 
                "Erro", JOptionPane.ERROR_MESSAGE);
            txtCPF.requestFocus();
            return null;
        }
        
        if (txtDataNascimento.getText().trim().isEmpty() || 
            txtDataNascimento.getText().contains("_")) {
            JOptionPane.showMessageDialog(this, "Data de nascimento é obrigatória!", 
                "Erro", JOptionPane.ERROR_MESSAGE);
            txtDataNascimento.requestFocus();
            return null;
        }
        
        Date dataNascimento = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            dataNascimento = sdf.parse(txtDataNascimento.getText());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, 
                "Data inválida! Use DD/MM/AAAA", 
                "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        if (txtTelefone.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Telefone é obrigatório!", 
                "Erro", JOptionPane.ERROR_MESSAGE);
            txtTelefone.requestFocus();
            return null;
        }
        
        if (txtEmail.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "E-mail é obrigatório!", 
                "Erro", JOptionPane.ERROR_MESSAGE);
            txtEmail.requestFocus();
            return null;
        }
        
        Paciente paciente = new Paciente();
        if (pacienteSelecionado != null && modoEdicao) {
            paciente.setId(pacienteSelecionado.getId());
        }
        
        paciente.setNome(txtNome.getText().trim());
        paciente.setCpf(txtCPF.getText().trim());
        paciente.setDataNascimento(dataNascimento);
        paciente.setTelefone(txtTelefone.getText().trim());
        paciente.setEmail(txtEmail.getText().trim());
        paciente.setEndereco(txtEndereco.getText().trim());
        
        return paciente;
    }
    
    private void preencherCampos(Paciente paciente) {
        txtNome.setText(paciente.getNome());
        txtCPF.setText(paciente.getCpf());
        
        if (paciente.getDataNascimento() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            txtDataNascimento.setText(sdf.format(paciente.getDataNascimento()));
        }
        
        txtTelefone.setText(paciente.getTelefone());
        txtEmail.setText(paciente.getEmail());
        txtEndereco.setText(paciente.getEndereco());
    }
    
    private void limparCampos() {
        txtNome.setText("");
        txtCPF.setText("");
        txtDataNascimento.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtEndereco.setText("");
        pacienteSelecionado = null;
        modoEdicao = false;
        btnCadastrar.setEnabled(true);
        btnSalvar.setEnabled(false);
        btnEditar.setEnabled(true);
        btnExcluir.setEnabled(true);
        txtNome.requestFocus();
    }
    
    private void navegarParaDashboard() {
        new Dashboard().setVisible(true);
        this.dispose();
    }
    
    private void navegarParaMedicos() {
        new TelaMedicos().setVisible(true);
        this.dispose();
    }
    
    private void navegarParaConsultas() {
        new TelaAgenda().setVisible(true);
        this.dispose();
    }
    
    private void navegarParaProntuarios() {
        new TelaProntuario().setVisible(true);
        this.dispose();
    }

    // ========================================================================
    // MÉTODO initComponents() - GERADO PELO NETBEANS GUI BUILDER
    // NÃO EDITAR MANUALMENTE!
    // ========================================================================
    @SuppressWarnings("unchecked")
    private void initComponents() {
        
        jPanelMenu = new javax.swing.JPanel();
        lblMenuDashboard = new javax.swing.JLabel();
        lblMenuPacientes = new javax.swing.JLabel();
        lblMenuMedicos = new javax.swing.JLabel();
        lblMenuConsultas = new javax.swing.JLabel();
        lblMenuProntuarios = new javax.swing.JLabel();
        jPanelPrincipal = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        jPanelFormulario = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        lblCPF = new javax.swing.JLabel();
        txtCPF = new javax.swing.JFormattedTextField();
        lblDataNasc = new javax.swing.JLabel();
        txtDataNascimento = new javax.swing.JFormattedTextField();
        lblTelefone = new javax.swing.JLabel();
        txtTelefone = new javax.swing.JFormattedTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblEndereco = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        jPanelBotoes = new javax.swing.JPanel();
        btnExcluir = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPacientes = new javax.swing.JTable();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MedConnect - Pacientes");
        
        jPanelMenu.setPreferredSize(new java.awt.Dimension(457, 0));
        
        lblMenuDashboard.setFont(new java.awt.Font("Tahoma", 0, 36));
        lblMenuDashboard.setForeground(new java.awt.Color(255, 255, 255));
        lblMenuDashboard.setText("Dashboard");
        lblMenuDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMenuDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuDashboardMouseClicked(evt);
            }
        });
        
        lblMenuPacientes.setFont(new java.awt.Font("Tahoma", 0, 36));
        lblMenuPacientes.setForeground(new java.awt.Color(255, 255, 255));
        lblMenuPacientes.setText("Pacientes");
        lblMenuPacientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMenuPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuPacientesMouseClicked(evt);
            }
        });
        
        lblMenuMedicos.setFont(new java.awt.Font("Tahoma", 0, 36));
        lblMenuMedicos.setForeground(new java.awt.Color(255, 255, 255));
        lblMenuMedicos.setText("Médicos");
        lblMenuMedicos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMenuMedicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuMedicosMouseClicked(evt);
            }
        });
        
        lblMenuConsultas.setFont(new java.awt.Font("Tahoma", 0, 36));
        lblMenuConsultas.setForeground(new java.awt.Color(255, 255, 255));
        lblMenuConsultas.setText("Consultas");
        lblMenuConsultas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMenuConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuConsultasMouseClicked(evt);
            }
        });
        
        lblMenuProntuarios.setFont(new java.awt.Font("Tahoma", 0, 36));
        lblMenuProntuarios.setForeground(new java.awt.Color(255, 255, 255));
        lblMenuProntuarios.setText("Prontuários");
        lblMenuProntuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMenuProntuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuProntuariosMouseClicked(evt);
            }
        });
        
        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMenuProntuarios)
                    .addComponent(lblMenuConsultas)
                    .addComponent(lblMenuMedicos)
                    .addComponent(lblMenuPacientes)
                    .addComponent(lblMenuDashboard))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(lblMenuDashboard)
                .addGap(40, 40, 40)
                .addComponent(lblMenuPacientes)
                .addGap(40, 40, 40)
                .addComponent(lblMenuMedicos)
                .addGap(40, 40, 40)
                .addComponent(lblMenuConsultas)
                .addGap(40, 40, 40)
                .addComponent(lblMenuProntuarios)
                .addContainerGap(300, Short.MAX_VALUE))
        );
        
        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 100));
        lblTitulo.setText("Pacientes");
        
        btnEditar.setFont(new java.awt.Font("Tahoma", 1, 24));
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        
        jPanelFormulario.setBackground(new java.awt.Color(223, 240, 246));
        
        lblNome.setFont(new java.awt.Font("Tahoma", 1, 32));
        lblNome.setText("Nome Completo");
        
        txtNome.setFont(new java.awt.Font("Tahoma", 0, 24));
        
        lblCPF.setFont(new java.awt.Font("Tahoma", 1, 32));
        lblCPF.setText("CPF");
        
        txtCPF.setFont(new java.awt.Font("Tahoma", 0, 24));
        
        lblDataNasc.setFont(new java.awt.Font("Tahoma", 1, 32));
        lblDataNasc.setText("Data de nascimento");
        
        txtDataNascimento.setFont(new java.awt.Font("Tahoma", 0, 24));
        
        lblTelefone.setFont(new java.awt.Font("Tahoma", 1, 32));
        lblTelefone.setText("Telefone");
        
        txtTelefone.setFont(new java.awt.Font("Tahoma", 0, 24));
        
        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 32));
        lblEmail.setText("E-mail");
        
        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 24));
        
        lblEndereco.setFont(new java.awt.Font("Tahoma", 1, 32));
        lblEndereco.setText("Endereço");
        
        txtEndereco.setFont(new java.awt.Font("Tahoma", 0, 24));
        
        javax.swing.GroupLayout jPanelFormularioLayout = new javax.swing.GroupLayout(jPanelFormulario);
        jPanelFormulario.setLayout(jPanelFormularioLayout);
        jPanelFormularioLayout.setHorizontalGroup(
            jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEndereco)
                    .addComponent(lblEmail)
                    .addComponent(lblTelefone)
                    .addComponent(lblDataNasc)
                    .addComponent(lblCPF)
                    .addComponent(lblNome)
                    .addGroup(jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
                        .addComponent(txtCPF)
                        .addComponent(txtDataNascimento)
                        .addComponent(txtTelefone)
                        .addComponent(txtEmail)
                        .addComponent(txtEndereco)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanelFormularioLayout.setVerticalGroup(
            jPanelFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormularioLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblNome)
                .addGap(10, 10, 10)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblCPF)
                .addGap(10, 10, 10)
                .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblDataNasc)
                .addGap(10, 10, 10)
                .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblTelefone)
                .addGap(10, 10, 10)
                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblEmail)
                .addGap(10, 10, 10)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblEndereco)
                .addGap(10, 10, 10)
                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        
        btnExcluir.setFont(new java.awt.Font("Tahoma", 1, 24));
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        
        btnCadastrar.setFont(new java.awt.Font("Tahoma", 1, 24));
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });
        
        btnSalvar.setFont(new java.awt.Font("Tahoma", 1, 24));
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        
        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 24));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        
        javax.swing.GroupLayout jPanelBotoesLayout = new javax.swing.GroupLayout(jPanelBotoes);
        jPanelBotoes.setLayout(jPanelBotoesLayout);
        jPanelBotoesLayout.setHorizontalGroup(
            jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotoesLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanelBotoesLayout.setVerticalGroup(
            jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotoesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelBotoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        
        tblPacientes.setFont(new java.awt.Font("Tahoma", 0, 24));
        tblPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Nome", "CPF", "Telefone", "E-mail"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPacientes.setRowHeight(50);
        tblPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPacientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPacientes);
        
        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBotoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanelBotoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        pack();
    }
    
    private void lblMenuDashboardMouseClicked(java.awt.event.MouseEvent evt) {
        navegarParaDashboard();
    }
    
    private void lblMenuPacientesMouseClicked(java.awt.event.MouseEvent evt) {}
    
    private void lblMenuMedicosMouseClicked(java.awt.event.MouseEvent evt) {
        navegarParaMedicos();
    }
    
    private void lblMenuConsultasMouseClicked(java.awt.event.MouseEvent evt) {
        navegarParaConsultas();
    }
    
    private void lblMenuProntuariosMouseClicked(java.awt.event.MouseEvent evt) {
        navegarParaProntuarios();
    }
    
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {
        if (pacienteSelecionado == null) {
            JOptionPane.showMessageDialog(this, 
                "Selecione um paciente na tabela para editar!", 
                "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        modoEdicao = true;
        btnCadastrar.setEnabled(false);
        btnSalvar.setEnabled(true);
        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);
        txtNome.requestFocus();
    }
    
    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
        Paciente paciente = coletarDadosDaTela();
        if (paciente == null) return;
        
        try {
            service.cadastrar(paciente);
            JOptionPane.showMessageDialog(this, 
                "Paciente cadastrado com sucesso!", 
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
            carregarTabela();
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, 
                e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        if (pacienteSelecionado == null) {
            JOptionPane.showMessageDialog(this, 
                "Selecione um paciente para editar!", 
                "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Paciente paciente = coletarDadosDaTela();
        if (paciente == null) return;
        
        try {
            service.atualizar(paciente);
            JOptionPane.showMessageDialog(this, 
                "Paciente atualizado com sucesso!", 
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
            carregarTabela();
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, 
                e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        if (pacienteSelecionado == null) {
            JOptionPane.showMessageDialog(this, 
                "Selecione um paciente para excluir!", 
                "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacao = JOptionPane.showConfirmDialog(this, 
            "Deseja realmente excluir " + pacienteSelecionado.getNome() + "?", 
            "Confirmação", JOptionPane.YES_NO_OPTION);
        
        if (confirmacao == JOptionPane.YES_OPTION) {
            try {
                service.excluir(pacienteSelecionado.getId());
                JOptionPane.showMessageDialog(this, 
                    "Paciente excluído com sucesso!", 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
                carregarTabela();
            } catch (NegocioException e) {
                JOptionPane.showMessageDialog(this, 
                    e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        String busca = JOptionPane.showInputDialog(this, 
            "Digite o nome do paciente:", "Buscar", JOptionPane.QUESTION_MESSAGE);
        
        if (busca != null && !busca.trim().isEmpty()) {
            try {
                List<Paciente> resultados = service.buscarPorNome(busca);
                DefaultTableModel modelo = (DefaultTableModel) tblPacientes.getModel();
                modelo.setRowCount(0);
                
                for (Paciente p : resultados) {
                    modelo.addRow(new Object[]{
                        p.getId(), p.getNome(), p.getCpf(), 
                        p.getTelefone(), p.getEmail()
                    });
                }
            } catch (NegocioException e) {
                JOptionPane.showMessageDialog(this, 
                    e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void tblPacientesMouseClicked(java.awt.event.MouseEvent evt) {
        int linha = tblPacientes.getSelectedRow();
        if (linha >= 0) {
            try {
                Integer id = (Integer) tblPacientes.getValueAt(linha, 0);
                pacienteSelecionado = service.buscarPorId(id);
                if (pacienteSelecionado != null) {
                    preencherCampos(pacienteSelecionado);
                }
            } catch (NegocioException e) {
                JOptionPane.showMessageDialog(this, 
                    e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Variables declaration - do not modify
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JLabel lblMenuDashboard;
    private javax.swing.JLabel lblMenuPacientes;
    private javax.swing.JLabel lblMenuMedicos;
    private javax.swing.JLabel lblMenuConsultas;
    private javax.swing.JLabel lblMenuProntuarios;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JButton btnEditar;
    private javax.swing.JPanel jPanelFormulario;
    private javax.swing.JLabel lblNome;
    private javax.swing.JTextField txtNome;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JFormattedTextField txtCPF;
    private javax.swing.JLabel lblDataNasc;
    private javax.swing.JFormattedTextField txtDataNascimento;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JFormattedTextField txtTelefone;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JPanel jPanelBotoes;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPacientes;
    // End of variables declaration
}