package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;

public class CategoriaDao {

	private EntityManager em;// Todo método vamos precisar deste entityManager já transformamos em atributo 

	private static Categoria categoria = new Categoria(); 

	//alguém vai instanciar está classe e para instânciar devemos pedir o 
	//objeto EntityManager  
	public CategoriaDao(EntityManager em) {//Objeto EntityManager é obrigatório para podermos inserir dados no banco de dados, digamos que é o 1° passo 
		this.em = em; 
	} 
	
	//então o usuário vai estar com uma instância desta classe, portanto 
	//e pode invocar um método que vai inserir dados dentro do BD 
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
