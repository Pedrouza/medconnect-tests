package medconnect.main;

import medconnect.view.Login;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Classe Principal do MedConnect
 * Responsável por iniciar a aplicação
 */
public class MedConnect {

    public static void main(String[] args) {
        // Configurar Look and Feel (aparência do sistema)
        configurarLookAndFeel();
        
        // Iniciar tela de Login
        iniciarSistema();
    }

    /**
     * Configura a aparência da aplicação
     */
    private static void configurarLookAndFeel() {
        try {
            // Usar Nimbus (visual moderno)
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | 
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            // Se falhar, usa o padrão do sistema
            System.err.println("Não foi possível configurar o Look and Feel: " + e.getMessage());
        }
    }

    /**
     * Inicia a tela de Login
     */
    private static void iniciarSistema() {
        // Executar na thread de despacho de eventos do Swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            login.setVisible(true);
            login.setLocationRelativeTo(null); // Centralizar na tela
        });
    }
}