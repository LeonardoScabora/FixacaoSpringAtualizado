package estagioCEPEIN.FixacaoSpring.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @GetMapping(path = "/teste")
    public String ola () {
        return "Hello World";
    }
}
