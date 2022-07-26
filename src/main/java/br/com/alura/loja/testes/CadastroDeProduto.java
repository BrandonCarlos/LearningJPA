package br.com.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		
		Categoria celulares = new Categoria("CELULARES"); 
//		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal(800), celulares); 
		
		//EntityManager que consegue cadastrar dados dentro do Banco de dados 
		EntityManager em = JPAUtil.getEntityManager();//utilizamos nossa fábria de EntityManager para nós retorna uma EntityManager 
//		ProdutoDao produtoDao = new ProdutoDao(em);//enviar EM e criando objeto que tem método para cadastrarDadosNoBD 
		CategoriaDao categoriaDao = new CategoriaDao(em); 
		em.getTransaction().begin(); 
//		categoriaDao.cadastrarNoBancoDeDados(celulares);
//		produtoDao.cadastrarNoBancoDeDados(celular);//aqui já foi inserido no BD 
		
		em.persist(celulares);//Apartir deste momento para baixo a JPA está de olho na entidade, ex: se for alterar algum campo, deletar algo, etc.. 
		celulares.setNome("SAMSUNG");//JPA vigiando que eu mudei algo na entidade Categoria e fará o UPDATE 
		
		em.flush();//Praticamente o mesmo que o commit, sincronizara com o banco de dados 
		em.clear();//Aqui joga a entidade para o estado DETACHED que no caso a JPA não vigia mais as atualizações que eu fizer 

//		celulares = em.merge(celulares); 
//		celulares = categoriaDao.voltarParaEstadoManaged(celulares); 
		celulares = em.merge(celulares);//Qual entidade quer voltar para o estado de MANAGED? celulares, então vai poderemos alterar, inserir a tabela "celulares" 
		celulares.setNome("Motorola"); 
		em.remove(celulares);//Removendo a entidade(entityManager) 
		categoriaDao.mudarNome("Samsung"); 
		em.clear(); 
		categoriaDao.deletarEntidade(celulares); 
		categoriaDao.deletarEntidade(celulares); 
		em.flush(); 
		 
//		em.getTransaction().commit();//Apartir daqui p/ baixo pega todas as entidades que estão em estado MANAGED e sincroniza com o banco de dados, inseri, atualiza e deleta tudo que tem que ser feito dentro do banco de dados  
//		em.close();//Apartir daqui para baixo a JPA já não vigia mais a Entidade Categoria, famoso estado DETACHED 
	}
	
}
