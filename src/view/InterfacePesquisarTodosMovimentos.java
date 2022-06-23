package view;

import controle.Comando;
import controle.Processador;
import model.Movimento;

import javax.swing.*;

public class InterfacePesquisarTodosMovimentos extends InterfaceBase implements Comando {
    @Override
    public void executar() {
        StringBuilder movimentosReg = new StringBuilder("");
        boolean teste;

        if (destinatarioDAO.pesquisar().size() == 0){
            JOptionPane.showMessageDialog(null,"Primeiro cadastre algum destinatário", "Não há destinatários cadastrados", JOptionPane.ERROR_MESSAGE);
            Processador.direcionar("0");
        }

        if (movimentoDAO.pesquisar().size() == 0){
            JOptionPane.showMessageDialog(null,"Primeiro cadastre algum movimento", "Não há movimentos cadastrados", JOptionPane.ERROR_MESSAGE);
            Processador.direcionar("0");
        }

        for (Movimento movimento : movimentoDAO.pesquisar()){
            if (movimento.getQuemRetira()==null){
                movimentosReg.append("Recebimento: ").append(movimento).append("\n");
            } else {
                movimentosReg.append("Retirada: ").append(movimento).append("\n");
            }

        }

        JOptionPane.showMessageDialog(null, movimentosReg,"Todos os Movimentos", JOptionPane.INFORMATION_MESSAGE, null);

    }

}
