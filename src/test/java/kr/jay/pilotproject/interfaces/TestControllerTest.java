package kr.jay.pilotproject.interfaces;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.FileInputStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.config.RestDocumentationConfigurer;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * TestControllerTest
 *
 * @author jaypark
 * @version 1.0.0
 * @since 12/12/23
 */

@SpringBootTest
@AutoConfigureRestDocs
@AutoConfigureMockMvc
class TestControllerTest {
	@Autowired
	protected MockMvc mockMvc;


	@Test
	void adminLogin() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/model")
				.param("localDate", "2021-12-12")
				.param("time", "12:12:12")
				.param("localDateTime", "2021-12-12T12:12:12")
				.header("datasource", "BUILDER")
				)
			.andDo(print())
			.andDo(document("test",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
				formParameters(
					parameterWithName("localDate").description(""),
					parameterWithName("time").description(""),
					parameterWithName("localDateTime").description("")
				),

					responseFields(
						fieldWithPath("data.localDate").description("yyyy-MM-dd"),
						fieldWithPath("data.time").description(""),
						fieldWithPath("data.localDateTime").description(""),
						fieldWithPath("data.title").description("")

					)
				)
			)
			.andExpect(status().isOk());
	}



}