package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;

@Controller
public class ControladorError{


//        @RequestMapping(value = "/error", method = RequestMethod.GET)
//        public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
//            ModelAndView errorPage = new ModelAndView("error");
//            String errorMsg = "";
//            int httpErrorCode = getErrorCode(httpRequest);
//
//            switch (httpErrorCode) {
//                case 400: {
//                    errorMsg = "Http Error Code: 400. Bad Request";
//                    break;
//                }
//                case 401: {
//                    errorMsg = "Http Error Code: 401. Unauthorized";
//                    break;
//                }
//                case 404: {
//                    errorMsg = "Http Error Code: 404. Resource not found";
//                    break;
//                }
//                case 500: {
//                    errorMsg = "Http Error Code: 500. Internal Server Error";
//                    break;
//                }
//            }
//            errorPage.addObject("errorMsg", errorMsg);
//            return errorPage;
//        }
//
//        private int getErrorCode(HttpServletRequest httpRequest) {
//            return (Integer) httpRequest
//                    .getAttribute("javax.servlet.error.status_code");
//        }

}