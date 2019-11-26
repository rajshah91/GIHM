package org.javabase.apps.mapper;

import java.util.List;
import org.javabase.apps.entity.Batch;

public interface BatchMapper {

   public Batch getBatchById(int batchId);

    public List<Batch> getAllBatch();
    
     public List<Batch> getAllActiveBatch();

    public Boolean addBatch(Batch batch);

    public Batch getBatchByName(String batchName);

}
