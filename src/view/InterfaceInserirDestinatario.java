package view;

import controle.Comando;
import model.Destinatario;

import javax.swing.*;
import java.util.Objects;

public class InterfaceInserirDestinatario extends InterfaceBase implements Comando {


    @Override
    public void executar() {
        String nome = null;
        String numeroImovel = null;
        boolean ch = true;

        do {try {
            nome = leDados("Digite o nome:");
            numeroImovel = leDados("Digite o número do imóvel:");
        } catch (CampoVazioException e) {
            JOptionPane.showMessageDialog(null, "Você deve digitar algo",  "Erro" ,JOptionPane.ERROR_MESSAGE);
        } } while ((nome==null || numeroImovel==null));



        Destinatario newDestinatario = new Destinatario(nome, numeroImovel);

        if (destinatarioDAO.checaExistenciaImovel(newDestinatario)) {
            JOptionPane.showMessageDialog(null, "Destinatário já cadastrado", "Erro" ,JOptionPane.ERROR_MESSAGE);
            ch = false;
        }

        if (ch) {destinatarioDAO.inserir(newDestinatario);}
        System.out.println(destinatarioDAO.pesquisar());
    }
}
