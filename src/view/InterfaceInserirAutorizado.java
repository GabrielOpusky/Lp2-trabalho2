package view;

import controle.Comando;
import controle.Processador;
import model.Destinatario;

import javax.swing.*;
import java.util.Objects;

public class InterfaceInserirAutorizado extends InterfaceBase implements Comando {
    @Override
    public void executar() {
        String nome = null;
        String numeroImovel = null;
        boolean ch = true;

        if (destinatarioDAO.pesquisar().size() == 0){
            JOptionPane.showMessageDialog(null,"Primeiro cadastre algum destinatário", "Não há destinatários cadastrados", JOptionPane.ERROR_MESSAGE);
            Processador.direcionar("0");
        }

        do {try {
            nome = leDados("Digite o nome:");
            numeroImovel = leDados("Digite o número do imóvel:");
        } catch (CampoVazioException e) {
            JOptionPane.showMessageDialog(null, "Você deve digitar algo",  "Erro" ,JOptionPane.ERROR_MESSAGE);
        } } while ((nome==null || numeroImovel==null));

        Destinatario searchDestinatario = new Destinatario(nome, numeroImovel);

        for (Destinatario destinatario : destinatarioDAO.pesquisar()){
            if(Objects.equals(destinatario.toString(), searchDestinatario.toString())){
                while (0==JOptionPane.showConfirmDialog(null, "Deseja inserir novo autorizado?")){
                    try {
                        String autorizado = leDados("Digite o nome do autorizado");
                        destinatario.addAutorizado(autorizado);
                    } catch (CampoVazioException e) {
                        JOptionPane.showMessageDialog(null, "Você deve digitar algo",  "Erro" ,JOptionPane.ERROR_MESSAGE);
                        continue;
                    }
                }
            }
        }


    }
}
