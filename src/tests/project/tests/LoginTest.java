package project.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import project.controller.UserController;
import project.entity.BreekEmoji;
import project.entity.BreeksLine;
import project.entity.TimetableElement;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Test
    public void test() throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    public void registrationValidMailTest() throws Exception {

        this.mockMvc.perform(post("/user/registration")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"userName\": \"test@test.ua\", \"password\": \"0000\"}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void registrationInvalidMailTest() throws Exception {

        this.mockMvc.perform(post("/user/registration")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"userName\": \"fakemail\", \"password\": \"0000\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void registrationActivationCodeTest() throws Exception {

        MvcResult res = this.mockMvc.perform(post("/user/registration")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"userName\": \"test@test.us\", \"password\": \"0000\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        JSONObject json = new JSONObject(res.getResponse().getContentAsString());
        String activationCode = json.getString("activationCode");

        assertThat(activationCode).isNotNull();

        this.mockMvc.perform(get("/user/activate/{code}", activationCode))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void registrationInvalidActivationCodeTest() throws Exception {

        this.mockMvc.perform(get("/user/activate/{code}", "1234567890"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void loginTest() throws Exception {
        login("test");
    }

    String login(String name) throws Exception {
        MvcResult res = this.mockMvc.perform(post("/user/registration")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"userName\": \"" + name + "@test.ru\", \"password\": \"0000\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        JSONObject json = new JSONObject(res.getResponse().getContentAsString());
        String activationCode = json.getString("activationCode");

        assertThat(activationCode).isNotNull();

        this.mockMvc.perform(get("/user/activate/{code}", activationCode))
                .andDo(print())
                .andExpect(status().isOk());

        MvcResult res2 = this.mockMvc.perform(post("/auth/signin")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"username\": \"test@test.ru\", \"password\": \"0000\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        return new JSONObject(res2.getResponse().getContentAsString()).getString("token");
    }

    @Test
    public void testAddingImage() throws Exception {
        addImage(login("image"));
    }

    public void addImage(String token) throws Exception {

        MvcResult res1 = this.mockMvc.perform(post("/image/addImage")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"linkToImage\": \"test_path\", \"date\": " + getDate() + "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        MvcResult res2 = this.mockMvc.perform(post("/image/addImage")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"linkToImage\": \"test_path2\", \"date\": " + getDate() + "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertThat(new JSONObject(res1.getResponse().getContentAsString()).getString("id")).isEqualTo(new JSONObject(res2.getResponse().getContentAsString()).getString("id"));

        MvcResult res3 = this.mockMvc.perform(post("/image/addImage")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"linkToImage\": \"test_path3\", \"date\": " + (getDate() + getDate()) + "}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertThat(new JSONObject(res2.getResponse().getContentAsString()).getString("id")).isNotEqualTo(new JSONObject(res3.getResponse().getContentAsString()).getString("id"));
    }

    @Test
    public void testAddAndEditTtElement() throws Exception {
        String token = login("element");
        String sId = addTtElement(token);
        editTtElement(token, sId);
    }

    public String addTtElement(String token) throws Exception {

        TimetableElement element = new TimetableElement((short) 1, "text", "effects", "12:00", "13:00", new Date(getDate()));
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(element);

        MvcResult res = this.mockMvc.perform(post("/timetableElement/addTimetableElement")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        return new JSONObject(res.getResponse().getContentAsString()).getString("elementId");
    }

    public void editTtElement(String token, String id) throws Exception {

        TimetableElement element = new TimetableElement((short) 1, "text2", "effects", "12:00", "13:00", new Date(getDate()));
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(element);

        MvcResult res = this.mockMvc.perform(put("/timetableElement/editTimetableElement/{id}", id)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertThat(new JSONObject(res.getResponse().getContentAsString()).getString("elementId")).isEqualTo(id);
    }

    @Test
    public void testAddingAndEditingBreeksLine() throws Exception {
        String token = login("breeksLine");
        String sId = addBreeksLine(token);
        editBreeksLine(token, sId);
    }

    public String addBreeksLine(String token) throws Exception {

        List<BreekEmoji> emojis = new ArrayList<>();
        emojis.add(new BreekEmoji(1));
        BreeksLine breeksLine = new BreeksLine("text", "effects", (short) 21, (short) 1054, emojis, new Date(getDate()));
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(breeksLine);

        MvcResult res = this.mockMvc.perform(post("/breeks/addLine")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        return new JSONObject(res.getResponse().getContentAsString()).getString("lineId");
    }

    public void editBreeksLine(String token, String id) throws Exception {

        List<BreekEmoji> emojis = new ArrayList<>();
        emojis.add(new BreekEmoji(1));
        BreeksLine breeksLine = new BreeksLine("text2", "effects", (short) 21, (short) 1054, emojis, new Date(getDate()));
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(breeksLine);

        MvcResult res = this.mockMvc.perform(put("/breeks/editLine/{id}", id)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertThat(new JSONObject(res.getResponse().getContentAsString()).getString("lineId")).isEqualTo(id);
    }

    private long getDate() {
        LocalDate localDate = LocalDate.now();

        long millisLocalDate = localDate
                .atStartOfDay()
                .toInstant(OffsetDateTime
                        .now()
                        .getOffset())
                .toEpochMilli();

        return millisLocalDate;
    }
}
