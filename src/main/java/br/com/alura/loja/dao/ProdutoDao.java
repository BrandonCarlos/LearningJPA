package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {

	private EntityManager em;// Todo m�todo vamos precisar deste entityManager j� transformamos em atributo

	public ProdutoDao(EntityManager em) {//quem for instanciar a class ProdutoDao vai ter que passar o EntityManager aqui
		this.em = em;
	}
	
	public void cadastrar(Produto produto) { 
		this.em.persist(produto);
	} 
	
	public void cadastrarNoBancoDeDados(Produto produto) { 
		this.em.persist(produto);//inserindo dados do tipo Produto no BD 
	} 
	
	//-----------------------------------------------------------------------------------------------
	//Aqui mesmo neste arquivo podemos fazer consultas no banco de d ados
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);//Busca na entity Produto atrav�s desse ID que foi fornecido
	}
	
	//Para buscarmos todos os registros l� da entidade Produto, utilizaremos o JPQL = SQL orientado a objetos, JPQL recebe uma String
	public List<Produto> buscarTodosProdutos() {
		String jpql = "SELECT p FROM Produto p"; //� o mesmo que fazer SELECT * FROM PRODUTO, Produto <- entity, e o "p" e como se fosse o ALIAS
		return this.em.createQuery(jpql, Produto.class).getResultList();//Produto.class -> falando que realmente o retorno e do tipo List de Produto
	}
	
	public List<Produto> BuscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";//:nome <- indicando que � um par�metro din�mico
		return em.createQuery(jpql, Produto.class)//retornando a QUERY 
				.setParameter("nome", nome)//setando par�metro dizendo para substituir o ":nome" pelo par�metro "nome"
				.getResultList();//pedindo para retornar a Lista
	}
	
	public List<Produto> BuscarPorNomeDaCategoria(String nome) {//a tabela Produto se relaciona com a tabela Categoria, portanto podemos fazer consultar na Tabela Categoria
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";//p.categoria.nome j� far� o JOIN autom�ticamente pq aqui estamos usando JPQL que � OO
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}

}
