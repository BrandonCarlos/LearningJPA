package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;

public class CategoriaDao {

	private EntityManager em;// Todo m�todo vamos precisar deste entityManager j� transformamos em atributo 

	private static Categoria categoria = new Categoria(); 

	//algu�m vai instanciar est� classe e para inst�nciar devemos pedir o 
	//objeto EntityManager  
	public CategoriaDao(EntityManager em) {//Objeto EntityManager � obrigat�rio para podermos inserir dados no banco de dados, digamos que � o 1� passo 
		this.em = em; 
	} 
	
	//ent�o o usu�rio vai estar com uma inst�ncia desta classe, portanto 
	//e pode invocar um m�todo que vai inserir dados dentro do BD 
	public void cadastrarNoBancoDeDados(Categoria categoria) { 
		this.em.persist(categoria);//inserindo dados do tipo Produto no BD 
	} 
	
	public Categoria voltarParaEstadoManaged(Categoria categoria) { 
		return this.em.merge(categoria);  
	} 
	
	public void deletarEntidade(Categoria categoria) { 
		categoria = voltarParaEstadoManaged(categoria); 
		this.em.remove(categoria); 
	}
	
	public void mudarNome(String nome) { 
		categoria.setNome(nome); 
		cadastrarNoBancoDeDados(categoria); 
	}

}
