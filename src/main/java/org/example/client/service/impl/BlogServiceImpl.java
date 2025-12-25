package org.example.client.service.impl;

import org.example.client.service.BlogService;
import org.example.pojo.Blog;

public class BlogServiceImpl implements BlogService {
    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog
                .builder()
                .id(id)
                .title("我的博客")
                .userId(22)
                .build();
        System.out.println("客户端查询了" + id + "博客");
        return blog;
    }
}
