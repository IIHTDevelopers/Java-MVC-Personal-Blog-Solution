package com.yaksha.training.blog.controller;

import com.yaksha.training.blog.entity.Blog;
import com.yaksha.training.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) Long id) {
        if (id == null) {
            model.addAttribute("blog", new Blog());
        } else {
            model.addAttribute("blog", blogService.getBlogById(id));
        }
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(@Valid Blog blog, BindingResult result) {
        if (result.hasErrors()) return "form";
        blogService.submitBlog(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/blogs")
    public String getBlogs(Model model) {
        model.addAttribute("blogs", blogService.getBlogs());
        return "blog_list";
    }

    @GetMapping("/blogs/{id}")
    public String deleteBlog(@PathVariable("id") Long id, Model model) {
        blogService.deleteBlogById(id);
        model.addAttribute("blogs", blogService.getBlogs());
        return "blog_list";
    }
}
