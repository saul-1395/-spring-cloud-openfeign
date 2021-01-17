package kondratov.spring_feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "course-cash", url = "https://openexchangerates.org")
public interface CourseService {


    @GetMapping("/api/{type_request}")
    Object getData(
            @PathVariable  String type_request,
                   @RequestParam("app_id") String app_id,
                   @RequestParam("symbols") String symbols);

}

//https://openexchangerates.org/ api/ latest                .json  ?app_id=185161acba574512b653852e440401b8&symbols=RUB
//https://openexchangerates.org/ api/ historical/2021-01-07 .json  ?app_id=185161acba574512b653852e440401b8&symbols=RUB


