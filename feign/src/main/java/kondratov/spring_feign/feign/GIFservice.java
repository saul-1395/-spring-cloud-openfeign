package kondratov.spring_feign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gif-service", url = "https://api.giphy.com")
public interface GIFservice {


    @GetMapping("/v1/gifs/random")
    Object getData(@RequestParam("api_key") String apiKey,
                   @RequestParam("tag") String tag,
                   @RequestParam("rating") String rating);

}

//https://api.giphy.com
// /v1/gifs/random
//
// ?
// api_key=YjMBpwuaouTnbKjk18LH9rM2uOrNivuk
// &
// tag=rich
// &
// rating=g