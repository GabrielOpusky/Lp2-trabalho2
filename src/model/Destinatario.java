package model;

import java.util.*;

/**
 * Write a description of class model.Destinatario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Destinatario
{
    private String nome; 
    private String numeroImovel;
    private List<String> autorizados; //lista de pessoas autorizadas a retirar correspondencias

    public Destinatario(String nome, String numeroImovel)
    {
            this.nome = nome;
            this.numeroImovel = numeroImovel;
            this.autorizados = new ArrayList<String>();
    }
    
    public String getNome(){
        return nome;
    }
        
    public String getNumeroImovel(){
        return numeroImovel;
    }
    
    public String toString(){
        return ("Nome = "+ nome + "\nNumero do Imóvel = "+ numeroImovel);
    }

    public void addAutorizado(String nome){ autorizados.add(nome); }

    public List<String> getAutorizados() { return autorizados; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destinatario that = (Destinatario) o;
        return nome.equals(that.nome) && numeroImovel.equals(that.numeroImovel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, numeroImovel);
    }
}
