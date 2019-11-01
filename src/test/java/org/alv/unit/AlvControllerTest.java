package org.alv.unit;

import org.alv.AlvController;
import org.alv.entity.AlvRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(AlvController.class)
public class AlvControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlvController alvController;

    @Test
    public void createLeft() throws Exception {
        AlvRequest request = new AlvRequest();
        request.setMessage("Hola");

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(request);

        Long id = new Long(1);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/diff/{id}/left", id)
                .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void createRight() throws Exception {
        AlvRequest request = new AlvRequest();
        request.setMessage("Hola");

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(request);

        Long id = new Long(1);

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/diff/{id}/right", id)
                .contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void diff() throws Exception {
        Long id = new Long(1);
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/diff/{id}", id)).andReturn();

        int status = result.getResponse().getStatus();
        System.out.println(result.getResponse().getContentAsString());
        assertEquals(200, status);
    }
}