package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;import org.hibernate.internal.build.AllowSysOut;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();//Aqui dentro da classe eu posso chamar assim mesmo, ou assim: CadastroDeProduto.cadastrarProduto();, pronto tudo guardado dentro do banco de dados
		
		//Para fazer uma busca no banco de dados, precisaremos novamente do EntityManager, abaixo já começamos a fazer consultas no banco de dados 
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em); 
		Produto buscarProduto = produtoDao.buscarPorId(1l);//buscando lá dentro da class CategoriaDao, pois é lá que tem a categoria do produto
		System.out.println(buscarProduto.getPreco());
		
		List<Produto> todosProdutos = produtoDao.buscarTodosProdutos();//retornando uma Lista com todos os Produtos e abaixo vamos percorrer com ForEach
		todosProdutos.forEach(produto -> System.out.println(produto.getNome()));//percorrendo de dentro de cada Produto e pegando o nome
		
		List<Produto> produtosComMesmoNome = produtoDao.BuscarPorNome("Samsung");//se retornou Lista, devemos percorrer com forEach
		produtosComMesmoNome.forEach(produtoNome -> System.out.println(produtoNome.getNome() + " Legal"));
		
		List<Produto> produtosComMesmoNomeCategoria = produtoDao.BuscarPorNomeDaCategoria("CELULARES");
		produtosComMesmoNomeCategoria.forEach(produtoNome -> System.out.println(produtoNome.getNome()));
	
		BigDecimal produtoComPreco = produtoDao.BuscarPrecoDoProdutoComNome("Samsung");
		System.out.println("Preço do produto: " + produtoComPreco);
		
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES"); 
		Produto celularOne = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal(800), celulares); 
		Produto celularTwo = new Produto("Samsung", "Muito legal", new BigDecimal(1200), celulares); 
		Produto celularThree = new Produto("Samsung", "Muito legal", new BigDecimal(1200), celulares); 
		Produto celularFour = new Produto("Motorola", "Muito legal", new BigDecimal(1100), celulares); 
		
		//EntityManager que consegue cadastrar dados dentro do Banco de dados 
		EntityManager em = JPAUtil.getEntityManager();//utilizamos nossa fábria de EntityManager para nós retorna uma EntityManager 
//		ProdutoDao produtoDao = new ProdutoDao(em);//enviar EM e criando objeto que tem método para cadastrarDadosNoBD 
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);
		em.getTransaction().begin(); 
		categoriaDao.cadastrarNoBancoDeDados(celulares);
		produtoDao.cadastrarNoBancoDeDados(celularOne);//aqui j++á foi inserido no BD 
		produtoDao.cadastrarNoBancoDeDados(celularTwo);//aqui já foi inserido no BD 
		produtoDao.cadastrarNoBancoDeDados(celularThree);//aqui já foi inserido no BD 
		produtoDao.cadastrarNoBancoDeDados(celularFour);//aqui já foi inserido no BD 
		celulares.setNome("SAMSUNG");//JPA vigiando que eu mudei algo na entidade Categoria e fará o UPDATE 
		em.flush();//Praticamente o mesmo que o commit, sincronizara com o banco de dados 
		em.clear();//Aqui joga a entidade para o estado DETACHED que no caso a JPA não vigia mais as atualizações que eu fizer 
		celulares = categoriaDao.voltarParaEstadoManaged(celulares); 
		celulares.setNome("Motorola"); 
		categoriaDao.mudarNome("Samsung"); 
		em.clear(); 
//		categoriaDao.deletarEntidade(celulares); //Não podemos deletar está tabela pois tem relacionamento com a tabela Produto
		em.flush(); 		 
		em.getTransaction().commit();//Apartir daqui p/ baixo pega todas as entidades que estão em estado MANAGED e sincroniza com o banco de dados, inseri, atualiza e deleta tudo que tem que ser feito dentro do banco de dados  
		em.close();//Apartir daqui para baixo a JPA já não vigia mais a Entidade Categoria, famoso estado DETACHED 
	}
	
}
