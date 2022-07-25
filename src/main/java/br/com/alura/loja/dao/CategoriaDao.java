package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;

public class CategoriaDao {

	private EntityManager em;// Todo método vamos precisar deste entityManager já transformamos em atributo

	public CategoriaDao(EntityManager em) {//quem for instanciar a class ProdutoDao vai ter que passar o EntityManager aqui
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria) { 
		this.em.persist(categoria);//estamos inserindo a categoria dentro do banco de dados
	}

}
