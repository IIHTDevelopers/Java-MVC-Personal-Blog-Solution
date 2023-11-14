package com.yaksha.training.blog.functional;

import com.yaksha.training.blog.controller.BlogController;
import com.yaksha.training.blog.entity.Blog;
import com.yaksha.training.blog.service.BlogService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.yaksha.training.blog.utils.MasterData.getBlog;
import static com.yaksha.training.blog.utils.MasterData.getBlogList;
import static com.yaksha.training.blog.utils.TestUtils.businessTestFile;
import static com.yaksha.training.blog.utils.TestUtils.currentTest;
import static com.yaksha.training.blog.utils.TestUtils.testReport;
import static com.yaksha.training.blog.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class BlogControllerTest {

    @Mock
    private BlogService blogService;

    @InjectMocks
    private BlogController blogController;

    private MockMvc mockMvc;

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
    public void testControllerGetForm() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("form"), businessTestFile);
    }

    @Test
    public void testControllerGetFormById() throws Exception {
        Blog blog = getBlog();
        when(blogService.getBlogById(blog.getId())).thenReturn(blog);
        MvcResult result = this.mockMvc.perform(get("/")
                .param("id", blog.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("form"), businessTestFile);
    }

    @Test
    public void testControllerGetBlogs() throws Exception {
        List<Blog> blogs = getBlogList(5);
        when(blogService.getBlogs()).thenReturn(blogs);
        MvcResult result = this.mockMvc.perform(get("/blogs")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("blog_list"), businessTestFile);

    }

    @Test
    public void testControllerSubmitForm() throws Exception {
        Blog blog = getBlog();
        MvcResult result = this.mockMvc.perform(post("/handleSubmit")
                .flashAttr("blog", blog)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/blogs"), businessTestFile);

    }

    @Test
    public void testControllerDeleteBlogById() throws Exception {
        Blog blog = getBlog();
        MvcResult result = this.mockMvc.perform(get("/blogs/" + blog.getId())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("blog_list"), businessTestFile);

    }

}
