package com.yaksha.training.blog.service;

import com.yaksha.training.blog.entity.Blog;
import com.yaksha.training.blog.repository.BlogRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.yaksha.training.blog.utils.MasterData.asJsonString;
import static com.yaksha.training.blog.utils.MasterData.getBlog;
import static com.yaksha.training.blog.utils.MasterData.getBlogList;
import static com.yaksha.training.blog.utils.TestUtils.businessTestFile;
import static com.yaksha.training.blog.utils.TestUtils.currentTest;
import static com.yaksha.training.blog.utils.TestUtils.testReport;
import static com.yaksha.training.blog.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

public class BlogServiceTest {

    @Mock
    private BlogRepository blogRepository;

    @InjectMocks
    private BlogService blogService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testAddBlog() throws Exception {
        Blog actual = getBlog();
        when(blogRepository.save(actual)).thenReturn(actual);
        Blog expected = blogService.addBlog(actual);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testUpdateBlog() throws Exception {
        Blog actual = getBlog();
        Blog existing = getBlog();
        when(blogRepository.save(actual)).thenReturn(actual);
        Blog expected = blogService.updateBlog(actual, existing);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testGetBlogs() throws Exception {
        List<Blog> actual = getBlogList(5);
        when(blogRepository.findAll()).thenReturn(actual);
        List<Blog> expected = blogService.getBlogs();
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testGetBlogById() throws Exception {
        Blog actual = getBlog();
        when(blogRepository.findById(actual.getId())).thenReturn(Optional.of(actual));
        Blog expected = blogService.getBlogById(actual.getId());
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testSubmitBlogIfIdIsNull() throws Exception {
        Blog actual = getBlog();
        actual.setId(null);
        when(blogRepository.save(actual)).thenReturn(actual);
        Blog expected = blogService.submitBlog(actual);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testSubmitBlogIfIdIsNotNull() throws Exception {
        Blog actual = getBlog();
        Blog existing = getBlog();
        when(blogRepository.findById(existing.getId())).thenReturn(Optional.of(existing));
        when(blogRepository.save(actual)).thenReturn(actual);
        Blog expected = blogService.submitBlog(actual);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testDeleteBlogById() throws Exception {
        Blog actual = getBlog();
        boolean expected = blogService.deleteBlogById(actual.getId());
        yakshaAssert(currentTest(),
                (expected ? true : false),
                businessTestFile);
    }
}
