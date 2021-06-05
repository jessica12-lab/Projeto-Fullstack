package br.com.fullstack.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.fullstack.model.Usuario;

public interface UsuarioDao extends CrudRepository <Usuario, Integer> {
	
	//DAO é um pattern
	//gravar - consultar - excluir - alterar
	//save(objeto): void
	//findById(int): objeto 
	//findAll(): iterable
	//deleteById(int): void
	//deleteAll()
	List<Usuario> findByNomeLike(String nome);
	Usuario findByEmailAndSenha(String email, String senha);
	

}
