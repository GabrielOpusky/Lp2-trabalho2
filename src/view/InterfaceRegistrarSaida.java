package view;

import controle.Comando;
import controle.Processador;
import model.*;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InterfaceRegistrarSaida extends InterfaceBase implements Comando {

    public void executar() {
        List<Movimento> movimentosSaida = new ArrayList<Movimento>();
        Movimento newMovimento = null;
        Destinatario searchDestinatario;
        String quemRegistra = null;
        String numeroImovel = null;
        String quemRetira = null;
        boolean teste;

        if (destinatarioDAO.pesquisar().size() == 0){
            JOptionPane.showMessageDialog(null,"Primeiro cadastre algum destinatário", "Não há destinatários cadastrados", JOptionPane.ERROR_MESSAGE);
            Processador.direcionar("0");
        }

        if (correspondenciaDAO.pesquisar().size()==0){
            JOptionPane.showMessageDialog(null,"Não há correspondencias registradas");
            Processador.direcionar("0");
        }

        for (Correspondencia correspondencia : correspondenciaDAO.pesquisar()){
            if(!correspondencia.getStatus()) {break;}
            else {
                JOptionPane.showMessageDialog(null,"Não há correspondencias não entregues");
                Processador.direcionar("0");
            }
        }

        teste = true;
        do {
            try {
                numeroImovel = leDados("Informe o número do imóvel do destinatário");
                teste =false;
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, "Digite algum valor", "Erro", JOptionPane.ERROR_MESSAGE);
            }  catch (NullPointerException e) {
                Processador.direcionar("0");
            }
        } while (teste);

        if(correspondenciaDAO.pesquisarNaoEntregues(numeroImovel)) {
            JOptionPane.showMessageDialog(null, "Existem correspondecias a serem entregues para o número");
        } else {
            JOptionPane.showMessageDialog(null, "Não há correspondencias a serem entregues para o número");
            Processador.direcionar("0");
        }

        teste = true;
        do {
            try {
                if (0==JOptionPane.showConfirmDialog(null, "O próprio destinatário está recebendo a correspondencia?")){
                    quemRetira = destinatarioDAO.pesquisarPorNumero(numeroImovel).getNome();
                } else {
                    quemRetira = leDados("Digite o nome de um autorizado");
                    searchDestinatario = destinatarioDAO.pesquisarPorNumero(numeroImovel);
                    if(!searchDestinatario.getAutorizados().contains(quemRetira)){throw new NaoAutorizadoException();}
                }
                teste = false;
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, "Digite algum valor", "Erro", JOptionPane.ERROR_MESSAGE);
            }  catch (NullPointerException e) {
                Processador.direcionar("0");
            } catch (NaoAutorizadoException en) {
                JOptionPane.showMessageDialog(null, "Pessoa não autorizada pelo individuo", "Não autorizado",JOptionPane.ERROR_MESSAGE);
            }
        } while (teste);

        teste = true;
        do {
            try {
                quemRegistra = leDados("Quem está registrando a retirada da correspondencia");
                teste = false;
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, "Digite algum valor", "Erro", JOptionPane.ERROR_MESSAGE);
            }  catch (NullPointerException e) {
                Processador.direcionar("0");
            }
        } while (teste);

        for (Movimento movimento : movimentoDAO.pesquisar()){
            if((numeroImovel.equals(movimento.getCorrespondencia().getDestino().getNumeroImovel())) && (!movimento.getCorrespondencia().getStatus()));{
                movimento.getCorrespondencia().setStatus(true);
                newMovimento = new Movimento(movimento.getCorrespondencia(), quemRetira, quemRegistra);
                System.out.println(newMovimento.getCorrespondencia());
                newMovimento.setCorrespondencia(movimento.getCorrespondencia());
                movimentosSaida.add(newMovimento);
            }
        }

        for (Movimento movimento : movimentosSaida){
            movimentoDAO.inserir(movimento);
        }
        System.out.println(movimentoDAO.pesquisar());
    }
}
