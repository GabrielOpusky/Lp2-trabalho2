package view;

import controle.Comando;
import controle.Processador;
import model.Movimento;

import javax.swing.*;
import java.util.Calendar;

import static java.lang.Integer.parseInt;

public class InterfacePesquisarMovimentosData extends InterfaceBase implements Comando {
    @Override
    public void executar() {int qnt=0;
        StringBuilder movimentosReg = new StringBuilder("");
        String data = null;
        Calendar sCalendar = null;
        String sData = null;
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
                data = leDados("Informe a data (dd/m/yyyy) Exemplo: '20/7/2019'");
                String[] dataP = data.split("/");
                if (dataP.length() != 3) {throw new ValorInvalidoException();}
                if((dataP[0].length() != 2) || (dataP[1].length() != 2) || (dataP[2].length() != 4)) {throw new ValorInvalidoException();} 
                int a = parseInt(dataP[0]);
                int b = parseInt(dataP[1]);
                int c = parseInt(dataP[2]);   
                data = Integer.toString(a) + "/" + Integer.toString(b) + "/" + Integer.toString(c);
                if(!checaData(b, a, c)){throw new DataInvalidaException();}
                teste = false;
            } catch (CampoVazioException ex) {
                JOptionPane.showMessageDialog(null, "Digite algum valor");
            }  catch (NullPointerException e) {
                Processador.direcionar("0");
            }  catch (ValorInvalidoException ei) {
                JOptionPane.showMessageDialog(null, "Digite um valor válido");
            } catch (NumberFormatException en) {
                JOptionPane.showMessageDialog(null, "Digite um valor válido em numeros");
            } catch (DataInvalidaException ed) {
                JOptionPane.showMessageDialog(null, "Data inválida");
            }
        } while (teste);

        for (Movimento movimento : movimentoDAO.pesquisar()){
            sCalendar = movimento.getData();
            sData= (sCalendar.get(Calendar.DAY_OF_MONTH) +"/"+ (sCalendar.get(Calendar.MONTH)+1) + "/" + sCalendar.get(Calendar.YEAR));
            if(sData.equals(data)){
                qnt++;
                if (movimento.getQuemRetira()==null){
                    movimentosReg.append("Recebimento: ").append(movimento).append("\n");
                } else {
                    movimentosReg.append("Retirada: ").append(movimento).append("\n");
                }

            }
        }



        if (qnt==0){JOptionPane.showMessageDialog(null, "Não há movimentos na data","Movimentos na data", JOptionPane.INFORMATION_MESSAGE, null);}
        else {JOptionPane.showMessageDialog(null, movimentosReg,"Movimentos na data", JOptionPane.INFORMATION_MESSAGE, null);}

    }

    //Checa se a data é válida de acordo com quantos dias tem cada mes, e quantos meses tem o ano
    public boolean checaData(int mes, int dia, int ano){
        if (ano < 0) {return false;}
        if ((mes<0) || (mes>12)){return false;}
        else if(mes==2){
            if((dia<0) || (dia>29)) {return false;}
        } else if ((mes==4) || (mes==6) || (mes==9) || (mes==11)){
            if((dia<0) || (dia>30)) {return false;}
        } else {
            if((dia<0) || (dia>31)) {return false;}
        }
        return true;
    }

}
