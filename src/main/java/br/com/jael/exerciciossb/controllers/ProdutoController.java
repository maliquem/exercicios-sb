package br.com.jael.exerciciossb.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jael.exerciciossb.model.entities.Produto;
import br.com.jael.exerciciossb.model.repositories.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    public @ResponseBody Produto novoProduto(@Valid Produto produto) {
        produto.setPrecoFinal(produto.getPreco(), produto.getDesconto());
        produtoRepository.save(produto);
        return produto;
    }

    @GetMapping
    public Iterable<Produto> obterProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping(path = "/nome/{parteNome}")
    public Iterable<Produto> obterProdutosPorNome(@PathVariable String parteNome) {
        return produtoRepository.findByNomeContainingIgnoreCase(parteNome);
    }

    @GetMapping(path = "/pagina/{pagina}/{qntdProdutos}")
    public Iterable<Produto> obterProdutosPorPagina(@PathVariable int pagina, @PathVariable int qntdProdutos) {
        if (qntdProdutos >= 5) {
            qntdProdutos = 5;
        }
        Pageable pageable = PageRequest.of(pagina, qntdProdutos);
        return produtoRepository.findAll(pageable);
    }

    @GetMapping(path = "/{id}")
    public Optional<Produto> obterProdutosPorId(@PathVariable int id) {
        return produtoRepository.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void excluirProduto(@PathVariable int id) {
        produtoRepository.deleteById(id);
    }

}
