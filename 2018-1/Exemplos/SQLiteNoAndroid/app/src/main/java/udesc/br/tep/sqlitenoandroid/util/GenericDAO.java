package udesc.br.tep.sqlitenoandroid.util;

import java.util.List;


public interface GenericDAO<O> {
    long inserir(O objeto);
    int alterar(O objeto);
    int excluir(int id);
    O buscar(int id);
    List<O> lista();
}