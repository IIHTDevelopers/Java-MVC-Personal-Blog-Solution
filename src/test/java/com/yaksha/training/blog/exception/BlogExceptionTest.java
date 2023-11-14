package com.yaksha.training.blog.exception;

import com.yaksha.training.blog.controller.BlogController;
import com.yaksha.training.blog.entity.Blog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static com.yaksha.training.blog.utils.MasterData.getBlog;
import static com.yaksha.training.blog.utils.TestUtils.currentTest;
import static com.yaksha.training.blog.utils.TestUtils.exceptionTestFile;
import static com.yaksha.training.blog.utils.TestUtils.testReport;
import static com.yaksha.training.blog.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class BlogExceptionTest {

    @InjectMocks
    private BlogController blogController;

    private MockMvc mockMvc;

    BindingResult bindingResult = mock(BindingResult.class);


    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testExceptionSubmitFormDescriptionNull() throws Exception {
        Blog blog = getBlog();
        blog.setDescription(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/handleSubmit")
                .flashAttr("blog", blog)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSubmitFormAmountNull() throws Exception {
        Blog blog = getBlog();
        blog.setTitle(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/handleSubmit")
                .flashAttr("blog", blog)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSubmitFormMonthNull() throws Exception {
        Blog blog = getBlog();
        blog.setName(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/handleSubmit")
                .flashAttr("blog", blog)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("form")), exceptionTestFile);
    }

}
