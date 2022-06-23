package view;

import controle.*;
import model.Movimento;

import javax.swing.*;


public class InterfacePesquisarMovimentosDestinatario extends InterfaceBase implements Comando{
    public void executar() {
        int qnt=0;
        StringBuilder movimentosReg = new StringBuilder("");
        String numeroImovel = null;
        boolean teste;

        if (destinatarioDAO.pesquisar().size() == 0){
            JOptionPane.showMessageDialog(null,"Primeiro cadastre algum destinatário", "Não há destinatários cadastrados", JOptionPane.ERROR_MESSAGE);
            Processador.direcionar("0");
        }

        if (movimentoDAO.pesquisar().size() == 0){
            JOptionPane.showMessageDialog(null,"Primeiro cadastre algum movimento", "Não há movimentos cadastrados", JOptionPane.ERROR_MESSAGE);
            Processador.direcionar("0");
        }

        teste = true;
        do {
            try {
                numeroImovel = leDados("Informe o número do imovel do destinatário");
                if(destinatarioDAO.pesquisarPorNumero(numeroImovel) != null){teste = false;}
                else JOptionPane.showMessageDialog(null,"Destinatário não existe", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            }  catch (NullPointerException e) {
                Processador.direcionar("0");
            }
        } while (teste);

        for (Movimento movimento : movimentoDAO.pesquisar()){
            if(movimento.getCorrespondencia().getDestino().getNumeroImovel().equals(numeroImovel)){
                qnt++;
                if (movimento.getQuemRetira()==null){
                    movimentosReg.append("Recebimento: ").append(movimento).append("\n");
                } else {
                    movimentosReg.append("Retirada: ").append(movimento).append("\n");
                }

            }
        }



        if (qnt==0){JOptionPane.showMessageDialog(null, "Não há movimentos do destinatário","Movimentos do destinatario", JOptionPane.INFORMATION_MESSAGE, null);}
        else {JOptionPane.showMessageDialog(null, movimentosReg,"Movimentos do destinatario", JOptionPane.INFORMATION_MESSAGE, null);}

    }

}
