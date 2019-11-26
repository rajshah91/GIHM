/**
 *
 */
package org.javabase.apps.service;

import java.util.Map;
import org.javabase.apps.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author raj.shah rajshah131291@gmail.com>
 * @version	1.0.0
 * @since	1.0.0
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    CommonMapper commonMapper;

    @Override
    public Object getObjectById(Object obj, int id) {
        return commonMapper.getObjectById(obj,id);
    }

    @Override
    public Boolean saveObject(Object object) {
        return commonMapper.saveObject(object);
    }

    @Override
    public Boolean saveOrUpdateObject(Object object) {
        return commonMapper.saveOrUpdateObject(object);
    }

    @Override
    public Boolean deleteObject(Object obj) {
        return commonMapper.deleteObject(obj);
    }
    
    @Override
    public Map<String,Long> getDashBoardDataForAdmin(){
        return commonMapper.getDashBoardDataForAdmin();
    }

}
