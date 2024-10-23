package estagioCEPEIN.FixacaoSpring.Models.servise;

import estagioCEPEIN.FixacaoSpring.Models.dto.EnderecoDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Enderecos;
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

    public Enderecos getById(Long id){
        return enderecoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não Encontrado"));
    }

    public List<Enderecos> getAll(){
        return enderecoRepository.findAll();
    }

    @Transactional
    public Enderecos save(Enderecos endereco){
        endereco.setDataRegistro(LocalDateTime.now());
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public Enderecos update(Long id, EnderecoDTO endereco){
        Enderecos enderecoAtual = getById(id);

        enderecoAtual.setRua(endereco.rua() != null ?  endereco.rua() : enderecoAtual.getRua());
        enderecoAtual.setBairro(endereco.bairro() != null ? endereco.bairro() : enderecoAtual.getBairro());
        enderecoAtual.setMoradia(endereco.moradia() != null ? endereco.moradia() : enderecoAtual.getMoradia());

        return enderecoRepository.save(enderecoAtual);
    }
}
