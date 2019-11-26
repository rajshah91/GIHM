package org.javabase.apps.controller.setup;


import org.javabase.apps.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "dashboard/temp")
public class TempController {

    @Autowired
    BatchService batchService;

    @RequestMapping(method = RequestMethod.GET)
    public String getTempPage() {
        return "student/temp";
    }

    

}
