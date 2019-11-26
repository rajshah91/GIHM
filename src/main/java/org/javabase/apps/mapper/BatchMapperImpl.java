/**
 *
 */
package org.javabase.apps.mapper;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.javabase.apps.entity.Batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@SuppressWarnings("unchecked")
public class BatchMapperImpl implements BatchMapper {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    private static final Logger log = LoggerFactory.getLogger(BatchMapperImpl.class);

    @Override
    @Transactional(readOnly = true)
    public Batch getBatchById(int batchId) {
        return hibernateTemplate.get(Batch.class, batchId);
    }

    /**
     *
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Batch> getAllBatch() {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try {
            Query q=session.createQuery("FROM Batch");
            List<Batch> batchList = q.list();
            session.close();
            return batchList;
        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            log.error(e.getMessage(), e);
            return null;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Batch> getAllActiveBatch() {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try {
            Query q=session.createQuery("FROM Batch WHERE active='true'");
            List<Batch> batchList = q.list();
            session.close();
            return batchList;
        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    @Transactional
    public Boolean addBatch(Batch batch) {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        try {
            session.save(batch);
//            hibernateTemplate.save(batch);
            return true;
        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Batch getBatchByName(String batchName) {
        try {
            String hql = "FROM Batch b WHERE b.batch = '" + batchName + "'";
            return (Batch) hibernateTemplate.find(hql).get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
