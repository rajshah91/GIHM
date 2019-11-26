/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.javabase.apps.mapper;

import java.util.List;
import org.javabase.apps.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author raj.shah
 */
@Repository
@SuppressWarnings("unchecked")
public class NewsMapperImpl implements NewsMapper{
    
    @Autowired
    private HibernateTemplate hibernateTemplate;
    
    @Override
    public List<News> getAllNews() {
        try {
            String hql = "FROM News n ORDER BY n.dataCreateTime DESC";
            return (List<News>) hibernateTemplate.find(hql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<News> getAllActiveNews() {
        try {
            String hql = "FROM News n WHERE n.active ='true' ORDER BY n.dataCreateTime DESC";
            return (List<News>) hibernateTemplate.find(hql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
