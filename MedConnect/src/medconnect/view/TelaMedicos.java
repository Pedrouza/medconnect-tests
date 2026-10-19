package medconnect.view;

import medconnect.dao.MedicoDAO;
import medconnect.model.Medico;
import medconnect.exception.BancoException;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 * Tela Médicos - CRUD de profissionais
 */
public class TelaMedicos extends javax.swing.JFrame {
    
    private MedicoDAO medicoDAO;
    private Medico medicoSelecionado;
    
    public TelaMedicos() {
        initComponents();
        personalizarTela();
        medicoDAO = new MedicoDAO();
        carregarTabela();
    }
    
    private void personalizarTela() {
        if (jPanelMenu != null) {
            jPanelMenu.setBackground(new Color(30, 64, 175));
        }
        if (jPanelPrincipal != null) {
            jPanelPrincipal.setBackground(new Color(223, 240, 246));
        }
        if (lblTitulo != null) {
            lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 100));
            lblTitulo.setText("Médicos");
        }
        if (btnExcluir != null) {
            btnExcluir.setBackground(new Color(208, 47, 19));
            btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 24));
            btnExcluir.setForeground(Color.WHITE);
        }
        if (btnCadastrar != null) {
            btnCadastrar.setBackground(new Color(16, 185, 129));
            btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 24));
            btnCadastrar.setForeground(Color.WHITE);
        }
        if (btnSalvar != null) {
            btnSalvar.setBackground(new Color(19, 123, 208));
            btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 24));
            btnSalvar.setForeground(Color.WHITE);
        }
        if (btnBuscar != null) {
            btnBuscar.setBackground(new Color(240, 240, 240));
            btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 24));
        }
    }
    
    private void carregarTabela() {
        if (tblMedicos == null) return;
        try {
            List<Medico> medicos = medicoDAO.listarTodos();
            DefaultTableModel modelo = (DefaultTableModel) tblMedicos.getModel();
            modelo.setRowCount(0);
            for (Medico m : medicos) {
                modelo.addRow(new Object[]{
                    m.getId(), 
                    m.getNome(), 
                    m.getCrm(), 
                    m.getEspecialidade()
                });
            }
        } catch (BancoException e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao carregar: " + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limparCampos() {
        if (txtNome != null) txtNome.setText("");
        if (txtCRM != null) txtCRM.setText("");
        if (txtEspecialidade != null) txtEspecialidade.setText("");
        if (txtHorarios != null) txtHorarios.setText("");
        if (txtEndereco != null) txtEndereco.setText("");
        medicoSelecionado = null;
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
        lblNome = new javax.swing.JLabel();
        lblCRM = new javax.swing.JLabel();
        lblEspecialidade = new javax.swing.JLabel();
        lblHorarios = new javax.swing.JLabel();
        lblEndereco = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtCRM = new javax.swing.JTextField();
        txtEspecialidade = new javax.swing.JTextField();
        txtHorarios = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        btnExcluir = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMedicos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MedConnect - Médicos");
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
        btnProntuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProntuariosMouseClicked(evt);
            }
        });

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

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 100));
        lblTitulo.setText("Médicos");

        lblNome.setFont(new java.awt.Font("Tahoma", 1, 32));
        lblNome.setText("Nome Completo");

        lblCRM.setFont(new java.awt.Font("Tahoma", 1, 32));
        lblCRM.setText("CRM");

        lblEspecialidade.setFont(new java.awt.Font("Tahoma", 1, 32));
        lblEspecialidade.setText("Especialidade");

        lblHorarios.setFont(new java.awt.Font("Tahoma", 1, 32));
        lblHorarios.setText("Horários");

        lblEndereco.setFont(new java.awt.Font("Tahoma", 1, 32));
        lblEndereco.setText("Endereço");

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 24));

        txtCRM.setFont(new java.awt.Font("Tahoma", 0, 24));

        txtEspecialidade.setFont(new java.awt.Font("Tahoma", 0, 24));

        txtHorarios.setFont(new java.awt.Font("Tahoma", 0, 24));

        txtEndereco.setFont(new java.awt.Font("Tahoma", 0, 24));

        btnExcluir.setFont(new java.awt.Font("Tahoma", 1, 24));
        btnExcluir.setText("Excluir");
        btnExcluir.setPreferredSize(new java.awt.Dimension(300, 65));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnCadastrar.setFont(new java.awt.Font("Tahoma", 1, 24));
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.setPreferredSize(new java.awt.Dimension(300, 65));
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnSalvar.setFont(new java.awt.Font("Tahoma", 1, 24));
        btnSalvar.setText("Salvar");
        btnSalvar.setPreferredSize(new java.awt.Dimension(300, 65));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 24));
        btnBuscar.setText("Buscar");
        btnBuscar.setPreferredSize(new java.awt.Dimension(300, 65));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblMedicos.setFont(new java.awt.Font("Tahoma", 0, 24));
        tblMedicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Nome", "CRM", "Especialidade"}
        ) {
            boolean[] canEdit = new boolean [] {false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMedicos.setRowHeight(50);
        tblMedicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMedicosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMedicos);

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblNome)
                        .addComponent(txtNome)
                        .addComponent(lblCRM)
                        .addComponent(txtCRM)
                        .addComponent(lblEspecialidade)
                        .addComponent(txtEspecialidade)
                        .addComponent(lblHorarios)
                        .addComponent(txtHorarios)
                        .addComponent(lblEndereco)
                        .addComponent(txtEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE))
                    .addComponent(lblTitulo))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitulo)
                .addGap(30, 30, 30)
                .addComponent(lblNome)
                .addGap(10, 10, 10)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblCRM)
                .addGap(10, 10, 10)
                .addComponent(txtCRM, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblEspecialidade)
                .addGap(10, 10, 10)
                .addComponent(txtEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblHorarios)
                .addGap(10, 10, 10)
                .addComponent(txtHorarios, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblEndereco)
                .addGap(10, 10, 10)
                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        // Já está na tela de médicos
    }

    private void btnConsultasMouseClicked(java.awt.event.MouseEvent evt) {
        new TelaAgenda().setVisible(true);
        this.dispose();
    }

    private void btnProntuariosMouseClicked(java.awt.event.MouseEvent evt) {
        new TelaProntuario().setVisible(true);
        this.dispose();
    }

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this, 
            "Funcionalidade de cadastro de médico em desenvolvimento", 
            "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this, 
            "Funcionalidade de atualização de médico em desenvolvimento", 
            "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this, 
            "Funcionalidade de exclusão de médico em desenvolvimento", 
            "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this, 
            "Funcionalidade de busca de médico em desenvolvimento", 
            "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void tblMedicosMouseClicked(java.awt.event.MouseEvent evt) {
        int linha = tblMedicos.getSelectedRow();
        if (linha >= 0) {
            try {
                Integer id = (Integer) tblMedicos.getValueAt(linha, 0);
                medicoSelecionado = medicoDAO.buscarPorId(id);
                if (medicoSelecionado != null) {
                    txtNome.setText(medicoSelecionado.getNome());
                    txtCRM.setText(medicoSelecionado.getCrm());
                    txtEspecialidade.setText(medicoSelecionado.getEspecialidade());
                    txtHorarios.setText(medicoSelecionado.getHorarioAtendimento());
                }
            } catch (BancoException e) {
                JOptionPane.showMessageDialog(this, 
                    "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblCRM;
    private javax.swing.JLabel lblEspecialidade;
    private javax.swing.JLabel lblHorarios;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtCRM;
    private javax.swing.JTextField txtEspecialidade;
    private javax.swing.JTextField txtHorarios;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JTable tblMedicos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel btnDashboard;
    private javax.swing.JLabel btnPacientes;
    private javax.swing.JLabel btnMedicos;
    private javax.swing.JLabel btnConsultas;
    private javax.swing.JLabel btnProntuarios;
    // End of variables declaration
}