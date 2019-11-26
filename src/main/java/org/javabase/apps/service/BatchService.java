package org.javabase.apps.service;

import java.util.List;
import org.javabase.apps.entity.Batch;


/**
 * @author raj.shah rajshah131291@gmail.com>
 * @version	1.0.0
 * @since	1.0.0
 */
public interface BatchService {

    public Batch getBatchById(int batchId);

    public List<Batch> getAllBatch();
    
    public List<Batch> getAllActiveBatch();

    public Boolean addBatch(Batch batch);
    
    public Batch getBatchByName(String batchName);

}
