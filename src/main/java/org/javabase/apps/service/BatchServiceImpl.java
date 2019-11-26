/**
 *
 */
package org.javabase.apps.service;

import java.util.List;
import org.javabase.apps.entity.Batch;

import org.javabase.apps.mapper.BatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author raj.shah rajshah131291@gmail.com>
 * @version	1.0.0
 * @since	1.0.0
 */
@Service
public class BatchServiceImpl implements BatchService {

    @Autowired
    BatchMapper batchMapper;

    @Override
    public Batch getBatchById(int batchId) {
        return batchMapper.getBatchById(batchId);
    }

    @Override
    public List<Batch> getAllBatch() {
        return batchMapper.getAllBatch();
    }
    
    @Override
    public List<Batch>  getAllActiveBatch() {
        return batchMapper.getAllActiveBatch();
    }

    @Override
    public Boolean addBatch(Batch batch) {
        return batchMapper.addBatch(batch);
    }
    
    @Override
    public Batch getBatchByName(String batchName) {
        return batchMapper.getBatchByName(batchName);
    }

}
