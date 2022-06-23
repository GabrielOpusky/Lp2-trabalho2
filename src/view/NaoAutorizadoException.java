package view;

public class NaoAutorizadoException extends Exception{
    /** Exception referente a retirada de correspondencia por pessoal não autorizado pelo destinatário
     *
     */


    public NaoAutorizadoException(){}

    public NaoAutorizadoException(String msg){super(msg);}
}
