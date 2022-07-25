package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		
		Produto celular = new Produto();
		celular.setNome("Xiaomi Redmi");
		celular.setDescricao("Muito legal");
		celular.setPreco(new BigDecimal("800"));
		//Criando um entityManager
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao dao = new ProdutoDao(em);
		em.getTransaction().begin(); //para o EntityManager iniciar a transa��o
		dao.cadastrar(celular);//persist -> m�todo para persistir, inserir um registro dentro do banco de dados, e ser� inserido na tabela "Produto"
		//o entityManager que transformara o objeto produto em um linha l� na tabela do banco de dados
		em.getTransaction().commit();//para comitar a transa��o no banco de dados
		em.close();//terminou de utilizar o entityManager, precisa dar um close(), fechando o banco de dados depois do uso
	}
	
}
