package com.example.diary.restDocs.content;

import com.example.diary.content.controller.ContentController;
import com.example.diary.content.dto.ContentDto;
import com.example.diary.content.entity.Content;
import com.example.diary.content.mapper.ContentMapper;
import com.example.diary.content.service.ContentService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.example.diary.utils.ApiDocumentUtils.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContentController.class) // Controller만 가볍게 Test하기 위한 애너테이션
@MockBean(JpaMetamodelMappingContext.class) // JPA에서 사용하는 Bean들을 Mock 객체로 주입하는 설정 (DB와의 연결을 끊음)
@AutoConfigureRestDocs //Spring Rest Docs 자동 구성을 위한 애너테이션
public class contentControllerTest {
    //TODO MockMvc 사용
    @Autowired
    private MockMvc mockMvc; // Mock 객체를 주입 받음. Mock 기능을 사용하기 위해
    //TODO Gson
    @Autowired
    private Gson gson;
    //TODO mapper Mocking
    @MockBean
    private ContentMapper mapper;
    //TODO Service Mocking
    @MockBean
    private ContentService contentService;

    @Test
    public void postContentTest() throws Exception{
        //GIVEN

        //TODO RequestBody, Dto -> JSON : Request Setting
        ContentDto.Post post = new ContentDto.Post("Jackson","no");
        String content = gson.toJson(post);
        //TODO ResponseBody, Dto : Response Setting
        ContentDto.Response response = new ContentDto.Response(
                1L,
                "Jackson",
                "no"
        );
        //TODO Logic : Mocking Setting (수정 1. Hamcrest 써보기)
        given(mapper.contentPostToContent(Mockito.any(ContentDto.Post.class))).willReturn(new Content());
        given(contentService.createContent(Mockito.any(Content.class))).willReturn(new Content());
        given(mapper.contentToContentResponse(Mockito.any(Content.class))).willReturn(response);

        //WHEN
        ResultActions actions =
                mockMvc.perform(
                        post("/v1/contents")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );

        //THEN
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.writer").value(post.getWriter()))
                .andDo(document(
                        "post-content",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                            List.of(
                                    fieldWithPath("writer").type(JsonFieldType.STRING).description("글쓴이"),
                                    fieldWithPath("text").type(JsonFieldType.STRING).description("글 내용")
                            )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.contentId").type(JsonFieldType.NUMBER).description("글 식별자"),
                                        fieldWithPath("data.writer").type(JsonFieldType.STRING).description("글쓴이"),
                                        fieldWithPath("data.text").type(JsonFieldType.STRING).description("글 내용"),
                                        fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("작성 일시"),
                                        fieldWithPath("data.modifiedAt").type(JsonFieldType.STRING).description("수정 일시")
                                )
                        )
                ));
    }
}
