/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabase.apps.mapper;

import java.util.List;
import org.javabase.apps.entity.News;

/**
 *
 * @author raj.shah
 */
public interface NewsMapper {
    
    public List<News> getAllNews();

    public List<News> getAllActiveNews();
}
