package com.nelioalves.cursomc.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.nelioalves.cursomc.domain.Produto;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = -4784386381084766898L;

	private Integer id;

	private String nome;
	private BigDecimal preco;

	public ProdutoDTO() {

	}

	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
}
