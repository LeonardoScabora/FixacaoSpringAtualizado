package estagioCEPEIN.FixacaoSpring.Controllers;

import estagioCEPEIN.FixacaoSpring.Models.dto.endereco.EnderecoDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Endereco;
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
    public ResponseEntity<List<Endereco>> listarEndereco(){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.getAll());
    }

    @PutMapping(path = "/id/{id}")
    public ResponseEntity<Endereco> AlterarEndereco(@PathVariable Long id, @RequestBody EnderecoDTO endereco){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.update(id, endereco));
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<Endereco> VerEnderecoID(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.getById(id));
    }
}
