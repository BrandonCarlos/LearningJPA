package br.com.alura.loja.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Categorias")
public class Categoria {// Esta class � uma Entity l� do banco de dados

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;// N�o precisamos gerar GET e SET do "id" pq � gerado l� no banco de dados
	private String nome;
	
	public Categoria() {
		//constructor default, para podermos voltar para o estado de MANAGED com o m�todo MERGE()
	}
	
	public Categoria(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
