package br.com.fiap.doeaqui;

import br.com.fiap.doeaqui.controller.api.ApiUserController;
import br.com.fiap.doeaqui.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DoeaquiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ApiUserController controller;

	@Test
	void createUser() throws Exception{
		//Sempre mudar o email a cada teste (Pois ele é Unique Key)
		//Criar usuário
		User user = new User("nome", "email5@hotmail.com", "Senha123", "Descrição do usuário");

		//POST
		MvcResult result = mockMvc.perform(post("/api/user")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isCreated()).andReturn();

		//Comparar resultados
		Assertions.assertEquals(user.getName(), "nome");
		//Sempre mudar o email a cada teste
		Assertions.assertEquals(user.getEmail(), "email5@hotmail.com");
		Assertions.assertEquals(user.getPassword(), "Senha123");
		Assertions.assertEquals(user.getDescription(), "Descrição do usuário");

		System.out.println("\n\n Resultado da requisição: " + result.getResponse().getContentAsString() + "\n\n");

	}

	@Test
	void getAllUsers() throws Exception{
		//GET All
		MvcResult result = mockMvc.perform(get("/api/user/instituicao")
						.contentType("application/json"))
				.andExpect(status().isOk()).andReturn();
		System.out.println("\n\n Resultado da requisição: " + result.getResponse().getContentAsString() + "\n\n");
	}

	@Test
	void getSingleUser() throws Exception{
		//GET USER
		User user = new User(1L, "Guilherme", "gui.rodriguero@hotmail.com", "123qwe");

		MvcResult result = mockMvc.perform(get("/api/user/1")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk()).andReturn();

		//Comparar resultados
		Assertions.assertEquals(user.getId(), 1L);
		Assertions.assertEquals(user.getName(), "Guilherme");
		Assertions.assertEquals(user.getEmail(), "gui.rodriguero@hotmail.com");
		Assertions.assertEquals(user.getPassword(), "123qwe");
		//Print do body do request
		System.out.println("\n\n Resultado da requisição: " + result.getResponse().getContentAsString() + "\n\n");
	}

	@Test
	void deleteUser() throws Exception{

		//Este Objeto deve estar criado no BD
		User user = new User(2L);

		//DELETE
		MvcResult result = mockMvc.perform(delete("/api/user/44")
						.contentType("application/json")
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk()).andReturn();

		System.out.println("\n\nUsuário deletado");

	}


}
