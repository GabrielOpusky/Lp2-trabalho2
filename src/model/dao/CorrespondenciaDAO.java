package model.dao;

import model.Correspondencia;

import java.util.ArrayList;
import java.util.List;

public class CorrespondenciaDAO implements OperacoesDAO{

    private List<Correspondencia> correspondencias;

    public CorrespondenciaDAO() {
        this.correspondencias = new ArrayList<Correspondencia>();
    }

    @Override
    public void inserir(Object obj) {
        correspondencias.add((Correspondencia) obj);
    }

    @Override
    public void excluir(Object obj) {
        int id = 0;
        for (Correspondencia correspondencia : correspondencias) {
            if(correspondencia==obj){
                correspondencias.remove(id);
            }
            id++;
        }
    }

    @Override
    public void editar(Object newObj) {

    }

    @Override
    public List<Correspondencia> pesquisar() {
        return correspondencias;
    }

    public boolean pesquisarNaoEntregues(String numeroImovel){
        for(Correspondencia correspondencia : correspondencias){
            if((correspondencia.getDestino().getNumeroImovel().equals(numeroImovel)) && (!correspondencia.getStatus()))
                return true;
        }
        return false;
    }
}
