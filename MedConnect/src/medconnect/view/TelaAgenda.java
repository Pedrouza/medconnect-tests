package medconnect.view;

import medconnect.dao.PacienteDAO;
import medconnect.dao.MedicoDAO;
import medconnect.exception.BancoException;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Tela Agenda - Gerenciamento de consultas
 */
public class TelaAgenda extends javax.swing.JFrame {
    
    private PacienteDAO pacienteDAO;
    private MedicoDAO medicoDAO;
    
    public TelaAgenda() {
        initComponents();
        personalizarTela();
        pacienteDAO = new PacienteDAO();
        medicoDAO = new MedicoDAO();
        carregarAgenda();
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
            lblTitulo.setText("Agenda");
        }
        if (btnAgendarConsulta != null) {
            btnAgendarConsulta.setBackground(new Color(19, 123, 208));
            btnAgendarConsulta.setFont(new Font("Tahoma", Font.BOLD, 32));
            btnAgendarConsulta.setForeground(Color.WHITE);
        }
        if (tblAgenda != null) {
            tblAgenda.setRowHeight(60);
            tblAgenda.setFont(new Font("Tahoma", Font.PLAIN, 24));
        }
    }
    
    private void carregarAgenda() {
        if (tblAgenda == null) return;
        
        DefaultTableModel modelo = (DefaultTableModel) tblAgenda.getModel();
        modelo.setRowCount(0);
        
        // Dados simulados para demonstração
        modelo.addRow(new Object[]{"Ana", "08:00/09:00", "Cardiograma", null, null, null});
        modelo.addRow(new Object[]{null, "10:00/11:00", "Colonoscopia", null, null, null});
        modelo.addRow(new Object[]{null, "10:00/10:45", "Raio-X", null, null, null});
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
        btnAgendarConsulta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAgenda = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MedConnect - Agenda");
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
        lblTitulo.setText("Agenda");

        btnAgendarConsulta.setFont(new java.awt.Font("Tahoma", 1, 32));
        btnAgendarConsulta.setText("Agendar Consulta");
        btnAgendarConsulta.setPreferredSize(new java.awt.Dimension(331, 86));
        btnAgendarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendarConsultaActionPerformed(evt);
            }
        });

        tblAgenda.setFont(new java.awt.Font("Tahoma", 0, 24));
        tblAgenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Ana", "08:00/09:00", "Cardiograma", null, null, null},
                {null, "10:00/11:00", "Colonoscopia", null, null, null},
                {null, "10:00/10:45", "Raio-X", null, null, null}
            },
            new String [] {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00"}
        ));
        tblAgenda.setRowHeight(60);
        jScrollPane1.setViewportView(tblAgenda);

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(300, 300, 300)
                        .addComponent(btnAgendarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addComponent(btnAgendarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnProntuariosMouseClicked(java.awt.event.MouseEvent evt) {
        new TelaProntuario().setVisible(true);
        this.dispose();
    }

    private void btnConsultasMouseClicked(java.awt.event.MouseEvent evt) {
        // Já está na tela de consultas/agenda
    }

    private void btnAgendarConsultaActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this, 
            "Funcionalidade de agendamento em desenvolvimento", 
            "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JButton btnAgendarConsulta;
    private javax.swing.JTable tblAgenda;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel btnDashboard;
    private javax.swing.JLabel btnPacientes;
    private javax.swing.JLabel btnMedicos;
    private javax.swing.JLabel btnConsultas;
    private javax.swing.JLabel btnProntuarios;
    // End of variables declaration
}