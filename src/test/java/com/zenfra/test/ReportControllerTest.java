package com.zenfra.test;

import antlr.collections.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenfra.dto.ResponseDto;
import com.zenfra.model.ReportColumns;
import com.zenfra.repository.ReportColumnsRepository;
import com.zenfra.service.ReportServices;
import com.zenfra.service.ReportServicesImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@NoArgsConstructor
public class ReportControllerTest extends ZenfraNewApplicationTests{

    private MockMvc mockMvc;

    @Mock
    private ReportColumnsRepository reportColumnsRepository;

    @InjectMocks
    private ReportServicesImpl reportService;

    ObjectMapper objectMapper=new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void Set_up(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    ReportColumns reportColumns=ReportColumns.builder()
            .uuid(UUID.fromString("79a241e1-5e80-4fd7-996d-0238521c6885"))
            .reportName("Murugan")
            .dataType("Murugan")
            .deviceType("muru")
            .isSizeMetrics("muru")
            .seq("muru")
            .columnName("muru")
            .reportBy("mur")
            .dbFieldName("mur")
            .isPinned(true)
            .aliasName("mur")
            .devices("muru")
            .taskListCategory("muru")
            .categorySeq(1)
            .subCategorySeq(2)
            .hide(true)
            .taskListSubCategory("muru")
            .build();
    ReportColumns reportColumns1=ReportColumns.builder()
            .uuid(UUID.fromString(UUID.randomUUID().toString()))
            .reportName("Murugan")
            .dataType("Murugan")
            .deviceType("muru")
            .isSizeMetrics("muru")
            .seq("muru")
            .columnName("muru")
            .reportBy("mur")
            .dbFieldName("mur")
            .isPinned(true)
            .aliasName("mur")
            .devices("muru")
            .taskListCategory("muru")
            .categorySeq(1)
            .subCategorySeq(2)
            .hide(true)
            .taskListSubCategory("muru")
            .build();

    @Test
    public void createColumnsTest() throws Exception {
        String jsonRequest=objectMapper.writeValueAsString(reportColumns);
        MvcResult result=mockMvc.perform(post("/api/v1/report").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent=result.getResponse().getContentAsString();
        ReportColumns response=objectMapper.readValue(resultContent,ReportColumns.class);
        Assert.assertEquals(response.getReportName(),reportColumns.getReportName());
    }

    @Test
    public void getAllReportsTest() throws Exception {
        MvcResult result=mockMvc.perform(get("/api/v1/reports").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String resultContent=result.getResponse().getContentAsString();
        ResponseDto responseDto=objectMapper.readValue(resultContent,ResponseDto.class);
       Assert.assertEquals(responseDto.getMessage(),"All Reports Are: ");
       responseDto.getReportColumnsList();
    }

    @Test
    public void gertReportByIdTest() throws Exception {
        ReportColumns columns=new ReportColumns();
        columns.setUuid(UUID.fromString("79a241e1-5e80-4fd7-996d-0238521c6885"));
        MvcResult result=mockMvc.perform(get("/api/v1/report/{uuid}",columns.getUuid()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String resultContent=result.getResponse().getContentAsString();
        ResponseDto responseDto=objectMapper.readValue(resultContent,ResponseDto.class);
        Assert.assertEquals(responseDto.getMessage(),"Response Success");
    }

}
