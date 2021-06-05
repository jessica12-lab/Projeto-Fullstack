package br.com.fullstack.dao;

import java.util.List; 

import org.springframework.data.repository.CrudRepository;

import br.com.fullstack.model.Artista;

public interface ArtistaDao extends CrudRepository <Artista, Integer>{

	//DAO é um pattern
		//gravar - consultar - excluir - alterar
		//save(objeto): void
		//findById(int): objeto 
		//findAll(): iterable
		//deleteById(int): void
		//deleteAll()
	public List<Artista> findByNacionalidade(String nacionalidade);
	/*List<Artista> findBynomeArtistaLike (String nome);
	List<Artista> findBynacionalidade(String nacionalidade);
	Artista save();
	Artista deleteById(int id);*/
	
	
}
