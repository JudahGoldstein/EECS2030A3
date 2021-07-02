package ca.navid.a2;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Error handler for the web application
 */
@Controller
public class AppErrorController implements ErrorController
{
    /**
     * Error handler for the web application
     * @param request HttpServletRequest
     * @return the string corresponding to the error code:
     *  HTTP return code 404 for page not found and 500 if our web application faces
     *  any IO or internal server errors
     */
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request)
    {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (null != status)
        {
            Integer statusCode = Integer.valueOf(status.toString());
            if(statusCode == HttpStatus.NOT_FOUND.value())  return "404";
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) return "500";
        }
        return "error";
    }
}