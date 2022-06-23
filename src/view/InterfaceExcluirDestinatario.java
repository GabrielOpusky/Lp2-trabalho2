package view;

import controle.Comando;
import controle.Processador;
import model.Destinatario;
import model.dao.DestinatarioDAO;

import javax.swing.*;
import java.util.Objects;

public class InterfaceExcluirDestinatario extends InterfaceBase implements Comando {

    @Override
    public void executar() {
        String nome = null;
        String numeroImovel = null;

        if (destinatarioDAO.pesquisar().size() == 0){
            JOptionPane.showMessageDialog(null,"Primeiro cadastre algum destinatário", "Não há destinatários cadastrados", JOptionPane.ERROR_MESSAGE);
            Processador.direcionar("0");
        }

        do {try {
            nome = leDados("Digite o nome:");
            numeroImovel = leDados("Digite o número do imóvel:");
        } catch (CampoVazioException e) {
            JOptionPane.showMessageDialog(null, "Você deve digitar algo");
        } } while ((nome==null || numeroImovel==null));
        boolean ch = true;





        destinatarioDAO.excluir(new Destinatario(nome,numeroImovel));
        System.out.println(destinatarioDAO.getDestinatarios());
    }
}
