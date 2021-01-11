

package kondratov.spring_feign.feign;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static kondratov.spring_feign.feign.CurrencyCourse.*;
@Controller
public class Currency {





    @RequestMapping("/showCourses")
    public String welcome(Model model) {
        // String gif_url = appController.getData(giFservice, cashCourse, apIcash);

            model.addAttribute("attrCoursTD", getCoursTD());
            model.addAttribute("attrCoursYTD", getCouursYTD());
            model.addAttribute("attrCurrency", getCurrency());


        //    <p>Course to day,    <text th:text="*{attrСurrency}" />: <text th:text="*{attrCoursTD}" /> </p>
            //<p>Course yesterday, <text th:text="*{attrСurrency}" />: <text th:text="*{attrCoursYTD}" /> </p>
        return "currency";
    }



}
