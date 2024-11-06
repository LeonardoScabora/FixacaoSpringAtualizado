package estagioCEPEIN.FixacaoSpring.Models.servise;

import estagioCEPEIN.FixacaoSpring.Models.dto.endereco.EnderecoDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Endereco;
import estagioCEPEIN.FixacaoSpring.Models.exceptions.DataNotFoundException;
import estagioCEPEIN.FixacaoSpring.Models.repositorio.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco getById(Long id){
        if(enderecoRepository.existsById(id)){
            return enderecoRepository.findById(id).get();
        }
        throw new DataNotFoundException("Endereço");
    }

    public List<Endereco> getAll(){
        return enderecoRepository.findAll();
    }

    @Transactional
    public Endereco save(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public Endereco update(Long id, EnderecoDTO endereco){
            Endereco enderecoAtual = getById(id);

            enderecoAtual.setRua(endereco.rua() != null ?  endereco.rua() : enderecoAtual.getRua());
            enderecoAtual.setBairro(endereco.bairro() != null ? endereco.bairro() : enderecoAtual.getBairro());
            enderecoAtual.setMoradia(endereco.moradia() != null ? endereco.moradia() : enderecoAtual.getMoradia());
            return enderecoRepository.save(enderecoAtual);
    }
}
