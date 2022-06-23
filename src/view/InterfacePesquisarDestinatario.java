package view;

import controle.Comando;
import controle.Processador;
import model.Destinatario;

import javax.swing.*;
import java.util.Objects;

public class InterfacePesquisarDestinatario extends InterfaceBase implements Comando{

    @Override
    public void executar() {
        String search = null;
        String opc = null;

        if (movimentoDAO.pesquisar().size()==0){
            JOptionPane.showMessageDialog(null,"Não há movimentos registradas");
            Processador.direcionar("0");
        }

        do {
            try {
                opc = leDados("Pesquisar por nome(1) ou número do Imovel(2)");
                if (opc.equals("1")){
                    search = leDados("Digite o nome:");
                } else if (opc.equals("2")){
                    search = leDados("Digite o número do imóvel:");
                } else {
                    JOptionPane.showMessageDialog(null, "Digite uma opção válida", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (CampoVazioException e) {
                JOptionPane.showMessageDialog(null, "Você deve digitar algo",  "Erro" ,JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException e) {
                Processador.direcionar("0");
            }
        } while (search == null);

        if (opc.equals("1")){
            for (Destinatario destinatario : destinatarioDAO.getDestinatarios()) {
                if(Objects.equals(destinatario.getNome(), search)){
                    String sdestinatario = destinatario.toString() + " \nAutorizados = " + destinatario.getAutorizados();
                    JOptionPane.showMessageDialog(null, sdestinatario, "Dados", 0, null);
                }
            }
        } else if (opc.equals("2")){
            for (Destinatario destinatario : destinatarioDAO.getDestinatarios()) {
                if(Objects.equals(destinatario.getNumeroImovel(), search)){
                    JOptionPane.showMessageDialog(null, destinatario.toString() + " \nAutorizados = " + destinatario.getAutorizados());
                }
            }
        }
    }
}
