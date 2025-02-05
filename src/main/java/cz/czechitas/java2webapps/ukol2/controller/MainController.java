package cz.czechitas.java2webapps.ukol2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class MainController {

   private final Random random = new Random();

   @GetMapping("/")
    public ModelAndView nahodnyCitatAObrazek () throws IOException {
      int nahodneCislo = random.nextInt(6) + 1;

       ModelAndView vysledek = new ModelAndView("index");
       vysledek.addObject("obrazek",String.format("/images/obr-%d.jpg", nahodneCislo));

       List<String> citaty = readAllLines("citaty.txt");
       vysledek.addObject("citat", citaty.get(nahodneCislo));


       return vysledek;
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
}