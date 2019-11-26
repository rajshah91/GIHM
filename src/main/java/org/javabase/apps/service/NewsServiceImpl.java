/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabase.apps.service;

import java.util.List;
import org.javabase.apps.entity.News;
import org.javabase.apps.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author raj.shah
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsMapper newsMapper;

    @Override
    public List<News> getAllNews() {
        return newsMapper.getAllNews();
    }

    @Override
    public List<News> getAllActiveNews() {
        return newsMapper.getAllActiveNews();
    }

}
