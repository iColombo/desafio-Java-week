package com.linuxtips.challengejavaweek.service;


import com.linuxtips.challengejavaweek.model.Produto;
import com.linuxtips.challengejavaweek.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto criarProduto (Produto produto){
        return produtoRepository.save(produto);
    }

	public boolean existsByNome(String nome) {
		return this.produtoRepository.existsByNome(nome);
	}

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    public ResponseEntity<Produto> buscarProdutoPeloId(Long id){
        return  produtoRepository.findById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Produto> atualizarProdutoPeloId(Produto produto, Long id){
        return produtoRepository.findById(id)
                .map(produtoToUpdate ->{
                    produtoToUpdate.setNome(produto.getNome());
                    produtoToUpdate.setPreco(produto.getPreco());
                    Produto updated = produtoRepository.save(produtoToUpdate);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> apagarPeloId (Long id){
        return produtoRepository.findById(id)
                .map(produtoToDelete ->{
                    produtoRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());

    }
}