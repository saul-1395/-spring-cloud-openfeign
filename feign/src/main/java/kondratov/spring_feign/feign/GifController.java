package kondratov.spring_feign.feign;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GifController {

    public GifController(AppController appController, GIFservice giFservice, CourseService courseService, APIcash apIcash) {
        this.appController = appController;
        this.giFservice = giFservice;
        this.courseService = courseService;
        this.apIcash = apIcash;
    }

    private final AppController appController;

    private final GIFservice giFservice;
    private final CourseService courseService;
    private APIcash apIcash;


    @RequestMapping("/formhtml")
    public String welcome(Model model) {
        String gif_url = appController.getData(giFservice, courseService, apIcash);

        model.addAttribute("attrGif", gif_url);
        return "form";
    }



}
