package com.linuxtips.challengejavaweek.controller;


import com.linuxtips.challengejavaweek.dto.ProdutoDto;
import com.linuxtips.challengejavaweek.model.Produto;
import com.linuxtips.challengejavaweek.service.ProdutoService;

import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Object> createProduto(@RequestBody @Valid ProdutoDto produtoDto) {	
	if (this.produtoService.existsByNome(produtoDto.getNome()))
	    return ResponseEntity.status(HttpStatus.CONFLICT).body("Dados repetidos: O Produto já existe!");			
	
	var produto = new Produto();		
	BeanUtils.copyProperties(produtoDto, produto);
	
	return ResponseEntity.status(HttpStatus.CREATED).body(this.produtoService.criarProduto(produto));
    }
    
    @GetMapping
    public ResponseEntity<List<Produto>> readProdutos() {
	return ResponseEntity.status(HttpStatus.OK).body(this.produtoService.listarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPais(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.produtoService.buscarProdutoPeloId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePais(@PathVariable(value = "id") Long id, 
	  				@RequestBody @Valid Produto produto) {
	return ResponseEntity.status(HttpStatus.OK).body(this.produtoService.atualizarProdutoPeloId(produto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePais(@PathVariable(value = "id") Long id) {
 	return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.produtoService.apagarPeloId(id));
    }

}
