package br.com.alura.loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	//Faremos um método que cria o entityManager, vamos garantir  criar uma única factory somente uma vez
	
	//Conectando com o banco de dados "loja"
	private static final EntityManagerFactory FACTORY = Persistence.
			createEntityManagerFactory("loja");//nome do persistenceUnit que se encontra lá no XML, imaginemos que persistence-unit é um banco de dados
	
	//método p/ retornar um EntityManager
	public static EntityManager getEntityManager() {//static pois é um método da class, e chamamos assim:
		//JPAUtil.getEntityManager();  
		return FACTORY.createEntityManager();
	}
}
