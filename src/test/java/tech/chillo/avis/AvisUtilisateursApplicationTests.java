package tech.chillo.avis;

import com.google.gson.Gson;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AvisUtilisateursApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
			"postgres:16.0-bullseye"
	);

	@BeforeAll
	static void beforeAll() {
		postgres.start();
	}

	@AfterAll
	static void afterAll() {
		postgres.stop();
	}

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
	}

	@DisplayName("[Controller] Test liste des avis")
	@Test
	void shouldReturnListOfCustomers() throws Exception {
		// Arrange

		// Act & Assert
		this.mockMvc
				.perform(get("/avis"))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(content().string(containsString("MBOUGUENG")));

	}

	@Test
	public void createAvis() throws Exception
	{

		Gson gson = new Gson();
		String json = gson.toJson(new AvisDTO("Un Nouveau message dans test container", "Achille"));
		this.mockMvc.perform(post("/avis")
						.contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
	}


}
