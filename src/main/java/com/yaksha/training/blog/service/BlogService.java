package com.yaksha.training.blog.service;

import com.yaksha.training.blog.entity.Blog;
import com.yaksha.training.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blog addBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public Blog updateBlog(Blog blog, Blog existing) {
        blog.setId(existing.getId());
        return blogRepository.save(blog);
    }

    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).get();
    }

    public Blog submitBlog(Blog blog) {
        if (blog.getId() == null) {
            return addBlog(blog);
        } else {
            Optional<Blog> existingBlog = blogRepository.findById(blog.getId());
            return updateBlog(blog, existingBlog.get());
        }
    }

    public boolean deleteBlogById(Long id) {
        blogRepository.deleteById(id);
        return true;
    }
}
