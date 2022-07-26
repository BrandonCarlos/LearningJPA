package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity // <- falando que Produto é uma entidade lá do banco de dados, mapeando a tabela
		// Produto aqui
@Table(name = "Produtos") // <- para dizer que a classe Produto e a tabela Produtos é a mesma coisa
public class Produto {// Esta class representa a "tabela de Produtos", e precisamos indicar para a JPA
	// e indicamos para a JPA via Annotations

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Definindo a strategy que vai gerar a Chave primaria dentro do
														// banco de dados
	// IDENTITY e para banco de dados que não tem o tipo SEQUENCE, más vai gerar em
	// SEQUENCE aqui
	//Não precisamos falar p/ JPA mapear estes atributos abaixo, pois já reconhece
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private LocalDate dataCadastro = LocalDate.now();// Ao instânciar o produto já vai vir com a data atual
	@ManyToOne //Muitos produtos podem ter 1 categoria, então a tabela "Produtos" terá a chave estrangeira Categoria_id
	private Categoria categoria;//cardinalidade de * para 1

	public Produto() {
		//constructor default para poder voltar ao estado de MANAGED com o método MERGE() que exige este constructor default
	}
	
	public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}

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

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
