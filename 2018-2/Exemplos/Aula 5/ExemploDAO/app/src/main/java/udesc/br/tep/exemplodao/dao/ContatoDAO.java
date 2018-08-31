package udesc.br.tep.exemplodao.dao;

import java.util.List;

import udesc.br.tep.exemplodao.model.Contato;
import udesc.br.tep.exemplodao.service.Arquivo;


public class ContatoDAO implements GenericDAO<Contato> {
    private Arquivo arqDataSource;

    public ContatoDAO(Arquivo arqDataSource) {
        this.arqDataSource = arqDataSource;
    }

    @Override
    public void inserir(Contato contato) {
    }

    @Override
    public void alterar(Contato contato) {
    }

    @Override
    public void excluir(int id) {
    }

    @Override
    public Contato buscar(int id) {
        return null;
    }

    @Override
    public List<Contato> lista() {
        return null;
    }
}
