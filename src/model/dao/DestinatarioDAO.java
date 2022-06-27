package model.dao;

import model.Destinatario;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DestinatarioDAO implements OperacoesDAO {

    private List<Destinatario> destinatarios;

    public DestinatarioDAO() {
        this.destinatarios = new ArrayList<Destinatario>();
    }

    @Override
    public void inserir(Object obj) {
            destinatarios.add((Destinatario) obj);
    }
    @Override
    public void excluir(Object obj) {
        int id = 0;
        for (Destinatario destinatario : destinatarios) {
            if(Objects.equals(destinatario.toString(), obj.toString())){
                destinatarios.remove(id);
                break;
            }
            id++;
        }
    }

    @Override
    public void editar(Object newObj)  {
        editarDestinatario((Destinatario) newObj);
    }

    public void editarDestinatario(Destinatario newObj)  {
        int id = 0;
        for (Destinatario destinatario : destinatarios){
            if (newObj.getNumeroImovel().equals(destinatario.getNumeroImovel())){
                destinatarios.set(id, newObj);
                break;
            }
            id++;
        }
    }

    @Override
    public List<Destinatario> pesquisar() {
        return destinatarios;
    }

    public Destinatario pesquisarPorNumero(String numero){
        for (Destinatario destinatario : destinatarios) {
            if (Objects.equals(destinatario.getNumeroImovel(), numero)) {
                return destinatario;
            }
        }
        return null;
    }
    public boolean checaExistencia(Destinatario newDestinatario) {
        for (Destinatario destinatario : destinatarios) {
            if (Objects.equals(destinatario.toString(), newDestinatario.toString())) {
                return true;
            }
        }
        return false;
    }
    public boolean checaExistenciaImovel(Destinatario newDestinatario) {
        for (Destinatario destinatario : destinatarios) {
            if (Objects.equals(destinatario.getNumeroImovel(), newDestinatario.getNumeroImovel())) {
                return true;
            }
        }
        return false;
    }
}
