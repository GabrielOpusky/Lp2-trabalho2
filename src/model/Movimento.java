package model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * Write a description of class model.Movimento here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Movimento
{
    private Correspondencia correspondencia;
    private String quemRegistra; //Deve estar na lista de autorizados do Destinatario
    private Calendar data;
    private String quemRetira; //Só para movimento de saída

    //model.Movimento de recebimento da correspondencia pela portaria
    public Movimento(Correspondencia correspondencia, String quemRegistra) {
            this.setCorrespondencia(correspondencia);
            this.setQuemRegistra(quemRegistra);
            setData(new GregorianCalendar());

    }

    //model.Movimento de entrega da correspondencia para o destinatário
    public Movimento(Correspondencia correspondencia, String quemRetira, String quemRegistra) {
            correspondencia.setStatus(true);
            this.setQuemRetira(quemRetira);
            this.setQuemRegistra(quemRegistra);
            setData(new GregorianCalendar());

    }
    
    private String verData(){
        return (data.get(Calendar.DAY_OF_MONTH) +"/"+ (data.get(Calendar.MONTH)+1) + "/" + data.get(Calendar.YEAR));
    }

    public Calendar getData(){
        return this.data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Correspondencia getCorrespondencia() {
        return correspondencia;
    }

    public void setCorrespondencia(Correspondencia correspondencia) {
        this.correspondencia = correspondencia;
    }

    public String getQuemRegistra() {
        return quemRegistra;
    }

    public void setQuemRegistra(String quemRegistra) {
        this.quemRegistra = quemRegistra;
    }

    public String getQuemRetira() {
        return quemRetira;
    }

    // Só pode setar quemRetira se antes tiver passado na verificação
    // para saber se o nome está na lista de autorizados do Destinatário
    public void setQuemRetira(String quemRetira) {
        this.quemRetira = quemRetira;
    }


    @Override
    public String toString() {
        return "Movimento{" +
                "correspondencia=" + correspondencia +
                ", quemRegistra='" + quemRegistra + '\'' +
                ", data=" + verData() +
                ", quemRetira='" + quemRetira + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movimento movimento = (Movimento) o;
        return correspondencia.equals(movimento.correspondencia) && quemRegistra.equals(movimento.quemRegistra) && data.equals(movimento.data) && quemRetira.equals(movimento.quemRetira);
    }

    @Override
    public int hashCode() {
        return Objects.hash(correspondencia, quemRegistra, data, quemRetira);
    }
}
