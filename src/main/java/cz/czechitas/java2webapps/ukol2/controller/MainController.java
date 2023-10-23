package cz.czechitas.java2webapps.ukol2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
public class MainController {

   private final Random random = new Random();

   @GetMapping("/")
    public ModelAndView nahodnyCitat () {
      int nahodneCislo = random.nextInt();

       ModelAndView obrazek = new ModelAndView("index");
       obrazek.addObject("obrazek",String.format("/images/obr-%d.jpg"));
       return obrazek;
   }

    private static List<String> readAllLines(String resource)throws IOException{
           ClassLoader classLoader=Thread.currentThread().getContextClassLoader();

       try(InputStream inputStream=classLoader.getResourceAsStream(resource);
           BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8))){

       return reader
                 .lines()
                 .collect(Collectors.toList());
   }
}
