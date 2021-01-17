

package kondratov.spring_feign.feign;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static kondratov.spring_feign.feign.CurrencyCourse.*;
@Controller
public class Currency {

    @RequestMapping("/showCourses")
    public String welcome(Model model) {

            model.addAttribute("attrCoursTD", getCoursTD());
            model.addAttribute("attrCoursYTD", getCouursYTD());
            model.addAttribute("attrCurrency", getCurrency());
        return "currency";
    }



}
