package br.com.alura.loja.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // <- falando que Produto é uma entidade lá do banco de dados, mapeando a tabela
		// Produto aqui
@Table(name = "Produtos") // <- para dizer que a classe Produto e a tabela Produtos é a mesma coisa
public class Produto {// Esta class representa a "tabela de Produtos", e precisamos indicar para a JPA
	// e indicamos para a JPA via Annotations

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Definindo a strategy que vai gerar a Chave primaria dentro do banco de dados
	//IDENTITY e para banco de dados que não tem o tipo SEQUENCE, más vai gerar em SEQUENCE aqui
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
}
