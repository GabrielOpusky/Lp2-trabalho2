package view;

import javax.swing.*;
import controle.Comando;
import controle.Processador;
import model.*;

public class InterfaceRegistrarEntrada extends InterfaceBase implements Comando {

    public void executar() {
        Movimento newMovimento = null;
        Destinatario newDestinatario = null;
        Correspondencia newCorrespondencia = null;
        String opc = null;
        String quemRecebe = null;
        String sair = null;
        String nomeDes = null;
        String numeroImovelDes = null;
        String empresa = null;
        boolean teste = true;
        boolean recibo = false;

        if (destinatarioDAO.pesquisar().size() == 0){
            JOptionPane.showMessageDialog(null,"Primeiro cadastre algum destinatário", "Não há destinatários cadastrados", JOptionPane.ERROR_MESSAGE);
            Processador.direcionar("0");
        }

        teste = true;
        do {
            try {
                nomeDes = leDados("Informe o nome do destinatário");
                numeroImovelDes = leDados("Informe o número do imóvel do destinatário");
                newDestinatario = new Destinatario(nomeDes, numeroImovelDes);
                if(destinatarioDAO.checaExistencia(newDestinatario)){teste = false;}
                else JOptionPane.showMessageDialog(null,"Destinatário não existe", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            }  catch (NullPointerException e) {
                Processador.direcionar("0");
            }
        } while (teste);

        System.out.println(newDestinatario);
        teste = true;
        do {
            try {
                opc = leDados("Tipo de correspondencia: Carta(1),Pacote(2)");
                if (opc.equals("1")) {
                    if (0 == JOptionPane.showConfirmDialog(null, "Tem documento de registro de recebimento?")){
                        recibo = true;
                    }
                    newCorrespondencia= new Carta(newDestinatario,recibo);
                } else if (opc.equals("2")){
                    empresa = leDados("Informe a empresa responsável");
                    newCorrespondencia = new Pacote(newDestinatario, empresa);
                } else {throw new ValorInvalidoException();}
                teste = false;
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, "Digite algum valor");
            }  catch (NullPointerException e) {
                Processador.direcionar("0");
            } catch (ValorInvalidoException e) {
                JOptionPane.showMessageDialog(null, "Digite um valor válido");
            }
        } while (teste);

        teste = true;
        do {
            try {
                quemRecebe = leDados("Informe o nome de quem está recebendo essa correspondencia");
                teste = false;
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            } catch (NullPointerException e) {
                Processador.direcionar("0");
            }
        } while (teste);

            newMovimento = new Movimento(newCorrespondencia, quemRecebe);
            movimentoDAO.inserir(newMovimento);
            correspondenciaDAO.inserir(newCorrespondencia);
            System.out.println(movimentoDAO.pesquisar());

        teste = true;
        do {
            try {
                sair = leDados("Digite 0 para voltar");
                int i = Integer.parseInt(sair);
                if (i != 0) continue;
                if (i == 0) {
                    Processador.direcionar("0");
                }
                teste = false;
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage() + " novamente");
            } catch (NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, nfe.getMessage() + " Isso não é um número inteiro");
            } catch (NullPointerException e) {
                Processador.direcionar("0");
            }
        } while (teste);

    }

}
