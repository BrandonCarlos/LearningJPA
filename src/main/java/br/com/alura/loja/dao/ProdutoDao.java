package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {

	private EntityManager em;// Todo m�todo vamos precisar deste entityManager j� transformamos em atributo

	public ProdutoDao(EntityManager em) {//quem for instanciar a class ProdutoDao vai ter que passar o EntityManager aqui
		this.em = em;
	}
	
	public void cadastrar(Produto produto) { 
		this.em.persist(produto);
	} 

}
