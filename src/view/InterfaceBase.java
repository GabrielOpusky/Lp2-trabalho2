package view;

import model.Correspondencia;
import model.Movimento;
import model.Pacote;
import model.dao.*;

import javax.swing.*;

public class InterfaceBase {
    public static DestinatarioDAO destinatarioDAO = new DestinatarioDAO();
    public static MovimentoDAO movimentoDAO = new MovimentoDAO();
    public static CorrespondenciaDAO correspondenciaDAO = new CorrespondenciaDAO();
    public static String leDados(String mensagem) throws CampoVazioException {
        String opcao = JOptionPane.showInputDialog(null, mensagem);
        if (opcao.contains(" ") || opcao.length() == 0) {
            throw new CampoVazioException(mensagem);
        } else {
            return opcao;
        }
    }

}
