package org.alv.unit;

import org.alv.AlvController;
import org.alv.data.AlvMessageRepository;
import org.alv.data.LoadDatabase;
import org.alv.entity.AlvRequest;
import org.alv.entity.DiffResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(AlvController.class)
public class AlvControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AlvMessageRepository alvMessageRepository;

    @Mock
    private LoadDatabase dbLoader;

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
                .contentType(MediaType.APPLICATION_JSON).content(inputJson))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void compareEqualMessages() throws Exception {
        // Perform creation in left and right first
        AlvRequest request = new AlvRequest();
        request.setMessage("Hola");

        ObjectMapper objectMapper = new ObjectMapper();
        String inputJson = objectMapper.writeValueAsString(request);
        Long id = new Long(1);

        // Send request to left
         this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/diff/{id}/left", id)
         .contentType(MediaType.APPLICATION_JSON).content(inputJson));

        // Send request to right
        this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/diff/{id}/right", id)
                .contentType(MediaType.APPLICATION_JSON).content(inputJson));

        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/diff/{id}", id)).andReturn();

        // Check if the web response is correct
        int status = result.getResponse().getStatus();
        assertEquals(200, status);

        // Check if the response of the service is correct -- It Didn't work in the tests :( it can be tested with CURL
        DiffResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), DiffResponse.class);
        assertEquals(200, response.getStatus());
    }
}