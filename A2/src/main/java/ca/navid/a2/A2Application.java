package ca.navid.a2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * The main entrypoint of the assignment.
 */
@Controller
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class A2Application {

    public static void main(String[] args)
    {
        SpringApplication.run(A2Application.class, args);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public @ResponseBody String sayHello(@RequestParam(value = "name", required = false, defaultValue = "Stranger") String name)
    {
        return "Hello " + name + "!";
    }//http://localhost:9000/hello?name=navid
}
