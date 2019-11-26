/**
 *
 */
package org.javabase.apps.mapper;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SuppressWarnings("unchecked")
public class CommonMapperImpl implements CommonMapper {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    private static final Logger log = LoggerFactory.getLogger(CommonMapperImpl.class);

    @Override
    @Transactional(readOnly = true)
    public Object getObjectById(Object obj, int id) {
        try {
            return hibernateTemplate.get(obj.getClass(), id);
        } catch (Exception e) {
            System.out.println("Error in CommonMapperImpl.getObjectById : "+e.getMessage());
            log.error(e.getMessage(), e);
            return false;
        }
    }
    
    @Override
    @Transactional
    public Boolean saveObject(Object obj) {
        try {
            hibernateTemplate.save(obj);
            return true;
        } catch (Exception e) {
            System.out.println("Error in CommonMapperImpl.saveObject : "+e.getMessage());
            log.error(e.getMessage(), e);
            return false;
        }
    }
    
    @Override
    @Transactional
    public Boolean saveOrUpdateObject(Object obj) {
        try {
            hibernateTemplate.saveOrUpdate(obj);
            return true;
        } catch (Exception e) {
            System.out.println("Error in CommonMapperImpl.saveOrUpdateObject : "+e.getMessage());
            log.error(e.getMessage(), e);
            return false;
        }
    }

    
    @Override
    @Transactional
    public Boolean deleteObject(Object obj) {
        try {
            hibernateTemplate.delete(obj);
            return true;
        } catch (Exception e) {
            System.out.println("Error in CommonMapperImpl.deleteObject : "+e.getMessage());
            log.error(e.getMessage(), e);
            return false;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Map<String,Long> getDashBoardDataForAdmin() {
        Map<String,Long> map=new HashMap<>();
        try {
            String hql1 = "SELECT count(id) FROM Student";
            Long studentcount=(Long)hibernateTemplate.find(hql1).get(0);
            
            String hql2 = "SELECT count(id) FROM Course";
            Long coursecount=(Long)hibernateTemplate.find(hql2).get(0);
            
            String hql3 = "SELECT count(id) FROM Batch";
            Long batchcount=(Long)hibernateTemplate.find(hql3).get(0);
            
            map.put("student", studentcount);
            map.put("course", coursecount);
            map.put("batch", batchcount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return map;
    }

}
