package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.modelo.Produto;

public class CadastroDeProduto {

	public static void main(String[] args) {
		
		Produto celular = new Produto();
		celular.setNome("Xiaomi Redmi");
		celular.setDescricao("Muito legal");
		celular.setPreco(new BigDecimal("800"));
		
		//Aqui a JPA se conectara com o banco de dados chamado "loja"
		EntityManagerFactory factory = Persistence.
				createEntityManagerFactory("loja");//nome do persistenceUnit que se encontra lá no XML, imaginemos que persistence-unit é um banco de dados
		
		//Criando um entityManager
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin(); //para o EntityManager iniciar a transação
		em.persist(celular);//persist -> método para persistir, inserir um registro dentro do banco de dados, e será inserido na tabela "Produto"
		//o entityManager que transformara o objeto produto em um linha lá na tabela do banco de dados
		em.getTransaction().commit();//para comitar a transação no banco de dados
		em.close();//terminou de utilizar o entityManager, precisa dar um close(), fechando o banco de dados depois do uso
	}
	
}
