package com.example.controllerTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.RestApiDemo.RestApiDemoApplication;
import com.example.bean.Student;
import com.example.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApiDemoApplication.class)
@AutoConfigureMockMvc
public class RestApiControllerTest {
	
	@MockBean
	StudentService studentservice;
	
	@Autowired
	private MockMvc mockMvc;
		
	@Test
	public void testGetStudentById() throws Exception {
		Student st = new Student();
		st.setId(100);
		st.setName("Amit");
		st.setCountry("france");
		Mockito.when(studentservice.findById(Mockito.anyInt())).thenReturn(st);
		String URI = "/stu/14221";
		RequestBuilder req = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(req).andReturn();
		String expectedjson = this.maptoJson(st);
		String outputJson = result.getResponse().getContentAsString();
		assertThat(outputJson).isEqualTo(expectedjson);

	}
	
	private String maptoJson(Object object) throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
		
	}
	

}
