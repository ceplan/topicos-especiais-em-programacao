package udesc.br.tep.exemplodao.dao;

import java.util.List;

import udesc.br.tep.exemplodao.service.Arquivo;

public interface GenericDAO<O> {
    void inserir(O objeto);
    void alterar(O objeto);
    void excluir(int id);
    O buscar(int id);
    List<O> lista();
}
