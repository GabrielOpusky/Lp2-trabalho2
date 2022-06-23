package model.dao;

import model.Movimento;

import java.util.ArrayList;
import java.util.List;

public class MovimentoDAO implements OperacoesDAO {

    private List<Movimento> movimentos;

    public MovimentoDAO() {
        this.movimentos = new ArrayList<Movimento>();
    }

    @Override
    public void inserir(Object obj) {
        movimentos.add((Movimento) obj);
    }

    @Override
    public void excluir(Object obj) {
        int id = 0;
        for (Movimento movimento : movimentos) {
            if(movimento==obj){
                movimentos.remove(id);
            }
            id++;
        }
    }

    @Override
    public void editar(Object newObj) {
        editarMovimento((Movimento) newObj);
    }

    public void editarMovimento(Movimento newObj)  {
        int id = 0;
        for (Movimento movimento : movimentos){
            if (newObj.getCorrespondencia().equals(movimento.getCorrespondencia())){
                movimentos.set(id, newObj);
                break;
            }
            id++;
        }
    }

    @Override
    public List<Movimento> pesquisar() {
        return movimentos;
    }
}
