package org.javabase.apps.controller.setup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.javabase.apps.entity.News;
import org.javabase.apps.service.CommonService;
import org.javabase.apps.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = {"dashboard/news"})
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAddNewsPage() {
        return "institution/news";
    }

    @ResponseBody
    @RequestMapping(value = "loadallnews", method = RequestMethod.GET)
    public Map<String, Object> getAllNews(@RequestParam(value = "active" ,required = false ,defaultValue = "false") String active)  {
        Map<String, Object> response = new HashMap<>();
        List<News> newsList = null;
        if(active != null && "true".equalsIgnoreCase(active)){
            newsList=newsService.getAllActiveNews();
        }else{
            newsList=newsService.getAllNews();
        }
        response.put("success", true);
        response.put("data", newsList);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Map<String, Object> addNews(@RequestBody News nws) {
        Map<String, Object> response = new HashMap<>();
        Boolean save = false;
        nws.setActive("true");
        save = commonService.saveObject(nws);

        if (save) {
            response.put("suceess", true);
            response.put("message", "Add News Sucess");
            return response;
        } else {
            response.put("error", true);
            response.put("message", "Add News Failed");
            return response;
        }
    }

    @ResponseBody
    @RequestMapping(value = "changestatus", method = RequestMethod.POST)
    public Map<String, Object> changesStatus(@RequestBody News nws) {
        Map<String, Object> response = new HashMap<>();

        News news = (News) commonService.getObjectById(new News(), nws.getId());
        news.setActive("false");
        Boolean save = commonService.saveOrUpdateObject(news);

        if (save) {
            response.put("success", true);
            response.put("message", "News Status Changed.");
            return response;
        } else {
            response.put("success", false);
            response.put("error", true);
            response.put("message", "News Status change Failed");
            return response;
        }
    }
    
    
    @ResponseBody
    @RequestMapping(value = "dashboarddetail", method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String, Object> studentDashboardDetail() {
        Map<String, Object> response = new HashMap<>();
        Map<String, Long> dboard = commonService.getDashBoardDataForAdmin();
        if(dboard != null && dboard.size()>0){
            response.put("student", dboard.get("student"));
            response.put("course", dboard.get("course"));
            response.put("batch", dboard.get("batch"));
            response.put("success", true);
        }else{
            response.put("success", false);
        }
        return response;
    }


}
