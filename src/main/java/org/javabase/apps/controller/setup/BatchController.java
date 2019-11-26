package org.javabase.apps.controller.setup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.javabase.apps.entity.Batch;

import org.javabase.apps.service.BatchService;
import org.javabase.apps.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "dashboard/batch")
public class BatchController {

    @Autowired
    BatchService batchService;
    
    @Autowired
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAddBatchPage() {
        return "institution/batch";
    }

    @ResponseBody
    @RequestMapping(value = "load", method = RequestMethod.GET)
    public Map<String, Object> getAllBatch(@RequestParam(value = "active" ,required = false ,defaultValue = "false") String active) {
        Map<String, Object> response = new HashMap<>();
        List<Batch> batchList = null;
        if(active != null && "true".equalsIgnoreCase(active)){
            batchList=batchService.getAllActiveBatch();
        }else{
            batchList=batchService.getAllBatch();
        }
        response.put("success", true);
        response.put("data", batchList);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Map<String, Object> registration(@RequestBody Batch batch) {
        Map<String, Object> response = new HashMap<>();
        Boolean save = batchService.addBatch(batch);

        if (save) {
            response.put("success", true);
            response.put("message", "Batch Added.");
            return response;
        } else {
            response.put("success", false);
            response.put("error", true);
            response.put("message", "Add Batch Failed");
            return response;
        }

    }
    
    @ResponseBody
    @RequestMapping(value = "changestatus", method = RequestMethod.POST)
    public Map<String, Object> changesStatus(@RequestBody Batch batch) {
        Map<String, Object> response = new HashMap<>();
        
        Batch bt = batchService.getBatchById(batch.getId());
        bt.setActive(batch.getActive());
        Boolean save=commonService.saveOrUpdateObject(bt);

        if (save) {
            response.put("success", true);
            response.put("message", "Batch Status Changed.");
            return response;
        } else {
            response.put("success", false);
            response.put("error", true);
            response.put("message", "Batch Status change Failed");
            return response;
        }

    }

}
