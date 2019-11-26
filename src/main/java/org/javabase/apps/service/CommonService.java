package org.javabase.apps.service;

import java.util.Map;

/**
 * @author raj.shah rajshah131291@gmail.com>
 * @version	1.0.0
 * @since	1.0.0
 */
public interface CommonService {

    public Object getObjectById(Object obj, int id);

    public Boolean saveObject(Object obj);
    
    public Boolean saveOrUpdateObject(Object obj);
    
    public Boolean deleteObject(Object obj);
    
    public Map<String,Long> getDashBoardDataForAdmin();
}
