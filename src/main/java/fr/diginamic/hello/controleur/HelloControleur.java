package fr.diginamic.hello.controleur;

import fr.diginamic.hello.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/hello")
public class HelloControleur {

    @GetMapping
    public String direHello(){
        return HelloService.salutations();
    }

}
