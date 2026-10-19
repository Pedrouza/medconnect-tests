package medconnect.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;

/**
 * Tela de Login - Autenticação de usuários
 */
public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        personalizarTela();
    }

    private void personalizarTela() {
        
        // Verifica se jPanel1 não é null antes de usar
        if (jPanel1 != null) {
            jPanel1.setBackground(new Color(223, 240, 246));
        }
        
        if (lblTitulo != null) {
            lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 48));
            lblTitulo.setText("MedConnect");
        }
        
        if (btnLogin != null) {
            btnLogin.setBackground(new Color(19, 123, 208));
            btnLogin.setFont(new Font("Tahoma", Font.BOLD, 36));
            btnLogin.setForeground(Color.WHITE);
        }
        
        if (lblEsqueceuSenha != null) {
            lblEsqueceuSenha.setFont(new Font("Tahoma", Font.PLAIN, 18));
            lblEsqueceuSenha.setForeground(new Color(19, 123, 208));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtEmail = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        lblEsqueceuSenha = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(223, 240, 246));

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 24));
        txtEmail.setText("seuemail@gmail.com");

        txtSenha.setFont(new java.awt.Font("Tahoma", 0, 24));
        txtSenha.setText("senha");

        btnLogin.setFont(new java.awt.Font("Tahoma", 1, 36));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 48));
        lblTitulo.setText("MedConnect");

        lblEsqueceuSenha.setFont(new java.awt.Font("Tahoma", 0, 18));
        lblEsqueceuSenha.setForeground(new java.awt.Color(19, 123, 208));
        lblEsqueceuSenha.setText("<html><u>Esqueceu a senha? clique aqui</u></html>");
        lblEsqueceuSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEsqueceuSenhaMouseClicked(evt);
            }
        });

        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 24));
        lblEmail.setText("E-mail:");

        lblSenha.setFont(new java.awt.Font("Tahoma", 0, 24));
        lblSenha.setText("Senha:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(lblTitulo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEmail)
                            .addComponent(lblSenha)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                                .addComponent(txtSenha)
                                .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(480, 480, 480)
                        .addComponent(lblEsqueceuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(350, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(lblTitulo)
                .addGap(80, 80, 80)
                .addComponent(lblEmail)
                .addGap(18, 18, 18)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblSenha)
                .addGap(18, 18, 18)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblEsqueceuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());
        
        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Preencha todos os campos!", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(this, 
                "E-mail inválido!", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (senha.length() < 6) {
            JOptionPane.showMessageDialog(this, 
                "Senha deve ter no mínimo 6 caracteres!", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Dashboard dashboard = new Dashboard();
        dashboard.setVisible(true);
        this.dispose();
    }                                        

    private void lblEsqueceuSenhaMouseClicked(java.awt.event.MouseEvent evt) {                                              
        JOptionPane.showMessageDialog(this, 
            "Funcionalidade em desenvolvimento", 
            "Info", 
            JOptionPane.INFORMATION_MESSAGE);
    }                                             

    // Variables declaration - do not modify
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblEsqueceuSenha;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration
}
