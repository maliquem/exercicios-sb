package br.com.jael.exerciciossb.model.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.jael.exerciciossb.model.entities.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

}
