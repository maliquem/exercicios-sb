package br.com.jael.exerciciossb.model.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.jael.exerciciossb.model.entities.Produto;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Integer> {

    public Iterable<Produto> findByNomeContainingIgnoreCase(String parteNome);

}
