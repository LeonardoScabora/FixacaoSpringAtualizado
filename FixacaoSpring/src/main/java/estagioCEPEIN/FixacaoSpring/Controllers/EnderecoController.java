package estagioCEPEIN.FixacaoSpring.Controllers;

import estagioCEPEIN.FixacaoSpring.Models.dto.EnderecoDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Enderecos;
import estagioCEPEIN.FixacaoSpring.Models.servise.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Enderecos>> listarEndereco(){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.getAll());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Enderecos> AlterarEndereco(@PathVariable Long id, @RequestBody EnderecoDTO endereco){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(enderecoService.update(id, endereco));
    }
}
