package io.roughgears.springboot3.springbootdeveloper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class QuizControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void mockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
    }

    @DisplayName("quiz1(): GET /quiz?code=1 -> 201, Created!")
    @Test
    public void getQuiz1() throws Exception {
        // Given
        final String url = "/quiz";
        // When
        final ResultActions result = mockMvc.perform(get(url).param("code", "1"));
        // Then
        result
                .andExpect(status().isCreated())
                .andExpect(content().string("Created!"));
    }

    @DisplayName("quiz2(): GET /quiz?code=2 -> 400, Bad Request!")
    @Test
    public void getQuiz2() throws Exception {
        // Given
        final String url = "/quiz";
        // When
        final ResultActions result = mockMvc.perform(get(url).param("code", "2"));
        // Then
        result
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Bad Request!"));
    }

    @DisplayName("quiz1(): POST /quiz?code=1 --> 403, Forbidden!")
    @Test
    public void postQuiz1() throws Exception {
        // Given
        final String url = "/quiz";
        // When
        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Code(1))));
        // Then
        result
                .andExpect(status().isForbidden())
                .andExpect(content().string("Forbidden!"));
    }

    @DisplayName("quiz13(): POST /quiz?code=13 --> 200, Ok!")
    @Test
    public void postQuiz13() throws Exception {
        // Given
        final String url = "/quiz";
        // When
        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Code(13))));
        // Then
        result
                .andExpect(status().isOk())
                .andExpect(content().string("OK!"));
    }
}
