package com.example.diary.restDocs.content;

import com.example.diary.content.controller.ContentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ContentController.class) // Controller만 가볍게 Test하기 위한 애너테이션
@MockBean(JpaMetamodelMappingContext.class) // JPA에서 사용하는 Bean들을 Mock 객체로 주입하는 설정 (DB와의 연결을 끊음)
@AutoConfigureRestDocs //Spring Rest Docs 자동 구성을 위한 애너테이션
public class contentControllerTest {
    @Autowired
    private MockMvc mockMvc; // Mock 객체를 주입 받음. Mock 기능을 사용하기 위해
}
