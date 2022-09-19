package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		
		usuarioRepository.deleteAll();
		
		usuarioRepository.save(new Usuario(0L, "Pedro Trudesss", "pedrao.jt@gmail.com", "123456789", "IMG"));
		usuarioRepository.save(new Usuario(0L, "Pedro Martins", "martins.jt@gmail.com", "123456789", "IMG"));
		usuarioRepository.save(new Usuario(0L, "Ana Martins", "ana.martins@gmail.com", "123456789", "IMG"));
		usuarioRepository.save(new Usuario(0L, "Gustavo Martins", "gustavo.gu@gmail.com", "123456789", "IMG"));
	}
	
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("pedrao.jt@gmail.com");
		assertTrue(usuario.get().getUsuario().equals("pedrao.jt@gmail.com"));
	}
	
	
	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Martins");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Pedro Martins"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Ana Martins"));
		assertTrue(listaDeUsuarios.get(3).getNome().equals("Gustavo Martins"));
		
	}
	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}
}
