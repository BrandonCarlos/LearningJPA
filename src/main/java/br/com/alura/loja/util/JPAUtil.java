package br.com.alura.loja.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	//Faremos um m�todo que cria o entityManager, vamos garantir  criar uma �nica factory somente uma vez
	
	//Conectando com o banco de dados "loja"
	private static final EntityManagerFactory FACTORY = Persistence.
			createEntityManagerFactory("loja");//nome do persistenceUnit que se encontra l� no XML, imaginemos que persistence-unit � um banco de dados
	
	//m�todo p/ retornar um EntityManager
	public static EntityManager getEntityManager() {//static pois � um m�todo da class, e chamamos assim:
		//JPAUtil.getEntityManager();  
		return FACTORY.createEntityManager();
	}
}
