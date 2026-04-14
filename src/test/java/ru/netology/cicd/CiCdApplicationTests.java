package ru.netology.cicd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.netology.cicd.repository.TaskRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CiCdApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    void setUp() throws Exception{
        taskRepository.clear();

        String task1 = """
                {
                    "id": 0,
                    "title": "test",
                    "createdAt": "2026-04-13",
                    "done": false
                }""";

        String task2 = """
                {
                    "id": 1,
                    "title": "test",
                    "createdAt": "2026-04-14",
                    "done": false
                }""";

        mockMvc.perform(post("/tasks")
                        .content(task1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/tasks")
                        .content(task2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void saveTask() throws Exception {
        String task = """
                {
                    "id": 3,
                    "title": "test",
                    "createdAt": "2026-04-15",
                    "done": false
                }""";

        mockMvc.perform(post("/tasks")
                        .content(task)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void getAllTasks() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void updateTask() throws Exception {
        String task = """
                {
                    "id": 0,
                    "title": "test",
                    "createdAt": "2026-04-13",
                    "done": true
                }""";

        mockMvc.perform(put("/tasks/{0}", "0")
                        .content(task)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deleteTask() throws Exception {
        mockMvc.perform(delete("/tasks/{0}", "0"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getAllTaskTest() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("test"))
                .andExpect(jsonPath("$[1].title").value("test"))
                .andDo(print());
    }
}
