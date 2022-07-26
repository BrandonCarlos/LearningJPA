package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		
		Categoria celulares = new Categoria("CELULARES");
//		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal(800), celulares);
		//Criando um entityManager
		EntityManager em = JPAUtil.getEntityManager();
//		ProdutoDao produtoDao = new ProdutoDao(em);
//		CategoriaDao categoriaDao = new CategoriaDao(em);
		em.getTransaction().begin(); //para o EntityManager iniciar a transa��o
//		categoriaDao.cadastrar(celulares);//ou seja primeiro vai inserir a categoria no banco de dados, dizemos isso pq o Produto est� vinculado com est� Categoria
		em.persist(celulares);//inserindo dados dentro do banco de dados, apartir daqui a JPA est� vigiando qualquer mudan�a antes de fazer o COMMIT
		celulares.setNome("Samsung");//aqui j� temos uma mudan�a, JPA ta vigiando e far� UPDATE
		//E assim temos a chave estrangeira em nossa tabela produto
//		produtoDao.cadastrar(celular);//persist -> m�todo para persistir, inserir um registro dentro do banco de dados, e ser� inserido na tabela "Produto"
		//o entityManager que transformara o objeto produto em um linha l� na tabela do banco de dados
		em.getTransaction().commit();//para comitar a transa��o no banco de dados
		em.close();//terminou de utilizar o entityManager, precisa dar um close(), fechando o banco de dados depois do uso
		celulares.setNome("Samsung");//j� fechamos conex�o com a JPA, JPA n�o vigia mais, portanto n�o far� o UPDATE
	}
	
}
