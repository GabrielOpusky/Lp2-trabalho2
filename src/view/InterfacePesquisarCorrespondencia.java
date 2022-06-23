package view;

import controle.Comando;
import controle.Processador;
import model.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class InterfacePesquisarCorrespondencia extends InterfaceBase implements Comando{

    @Override
    public void executar() {
        String numeroImovel = null;
        int pacote= 0;
        int carta = 0;
        boolean teste;

        if (correspondenciaDAO.pesquisar().size()==0){
            JOptionPane.showMessageDialog(null,"Não há correspondencias registradas");
            Processador.direcionar("0");
        }

        teste = true;
        do {
            try {
                numeroImovel = leDados("Informe o número do imovel do destinatário");
                if(destinatarioDAO.pesquisarPorNumero(numeroImovel) != null){teste = false;}
                else JOptionPane.showMessageDialog(null,"Destinatário não existe", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, "Digite algum valor");
            }  catch (NullPointerException e) {
                Processador.direcionar("0");
            }
        } while (teste);

        if(!correspondenciaDAO.pesquisarNaoEntregues(numeroImovel)){
            JOptionPane.showMessageDialog(null,"Não há correspondencias a serem entregas para este destinatário");
            Processador.direcionar("0");
        }

        for (Correspondencia correspondencia : correspondenciaDAO.pesquisar()){
            if ((!correspondencia.getStatus()) && correspondencia.getDestino().getNumeroImovel().equals(numeroImovel)){
                if (correspondencia instanceof Carta){carta++;}
                if (correspondencia instanceof Pacote){pacote++;}
            }
        }

        JOptionPane.showMessageDialog(null,"Pacotes: " + pacote +"\nCartas: " + carta,"Correspondencias do destinatário" , JOptionPane.INFORMATION_MESSAGE);

    }

}
