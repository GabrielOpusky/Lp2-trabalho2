/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controle.Comando;
import controle.Processador;

import javax.swing.*;

/**
 *
 * @author Karen
 */
public class InterfacePrincipal extends InterfaceBase implements Comando{

    public void executar() {
        String opcao = null;
        do {
            try{
                opcao = leDados("Escolha a opcao:"
                        + "\n1 - Sair"
                        + "\n2 - Registrar Movimento de Entrada de Correspondencia"
                        + "\n3 - Registrar Movimento de Saida de Correspondencia"
                        + "\n4 - Pesquisar Se Existem Correspondencias para Serem Entregues para um Determinado Destinatario"
                        + "\n5 - Listar todos os Movimentos de um Determinado Destinatario"
                        + "\n6 - Listar todos os Movimentos de um Determinada Data"
                        + "\n7 - Listar todos os Movimentos Registrados no Sistema"
                        + "\n8 - Inserir um Determinado Destinatario"
                        + "\n9 - Inserir Pessoas Autorizadas por um Destinatario"
                        + "\n10 - Pesquisar Dados de um Determinado Destinatario"
                        + "\n11 - Excluir um Determinado Destinatario");

                Processador.direcionar(opcao);
            } catch(CampoVazioException x){
                JOptionPane.showMessageDialog(null,x.getMessage() + " Digite novamente");
            }
        } while (opcao==null || !opcao.equals("0"));
    }
    
}
