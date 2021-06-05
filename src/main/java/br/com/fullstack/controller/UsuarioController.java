package br.com.fullstack.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fullstack.dao.UsuarioDao;
import br.com.fullstack.model.Usuario;

@RestController //para que a classe responda o http
@CrossOrigin("*") //permite que o controller receba requisições externas(fora do server)
public class UsuarioController {
	
	@Autowired //transfere p SpringBoot a responsabilidade sobre este objeto
	private UsuarioDao dao;
	
	@PostMapping("/nome")
	public ResponseEntity<List<Usuario>> getUserName(@RequestBody Usuario usuario){
		/*//List<Usuario> nom = dao.findByNome(usuario.getNome());
		List<Usuario> resposta = new ArrayList<Usuario>();
		for (Usuario u : lista) {
			resposta.add(u);
		}
		*/
		List<Usuario> lista = dao.findByNomeLike("%" + usuario.getNome() + "%");
		
		
		if (lista.size()==0) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok( lista );
	}
	
	@GetMapping("/loginget/{e}/{s}")
	public ResponseEntity<Usuario> logar (@PathVariable String e, @PathVariable String s){
		Usuario resposta = dao.findByEmailAndSenha(e, s);
		if (resposta==null) {
			return ResponseEntity.status(404).build();
		}
		resposta.setSenha("");
		return ResponseEntity.ok( resposta );
		
	}
	
	//GET = método para ações simples retorno para grandes conjuntos de dados @PathVariable
	//Post == método para ações onde os dados enviados dentro do pacote
	//@RequestBody
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> logar(@RequestBody Usuario usuario){
		Usuario resposta = dao.findByEmailAndSenha(usuario.getEmail(),usuario.getSenha());
		if (resposta==null) {
			return ResponseEntity.status(404).build();
		}
		resposta.setSenha("");
		return ResponseEntity.ok( resposta );		
		
	}
	
	
	@PostMapping("/novousuario")
	public ResponseEntity<Usuario> add(@RequestBody Usuario usuario){
		try {
			dao.save(usuario);
			return ResponseEntity.ok(usuario);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(403).build();
		}
		
	}
	
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> getUser(@PathVariable int id){
		Usuario resposta = dao.findById(id).orElse(null);
		if (resposta==null) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(resposta);
	}
	
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getAll(){
		List<Usuario> lista = (List<Usuario>) dao.findAll();
		if(lista.size()==0) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(lista);
		
	}
	
	
	
	
	
	
	
	
	

}
