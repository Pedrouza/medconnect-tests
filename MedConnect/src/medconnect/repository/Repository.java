package medconnect.repository;

import java.util.List;

/**
 * Interface genérica para repositórios
 */
public interface Repository<T> {
    
    void salvar(T entity);
    
    void atualizar(T entity);
    
    void excluir(Integer id);
    
    T buscarPorId(Integer id);
    
    List<T> listarTodos();
    
    List<T> buscarPorNome(String nome);
}