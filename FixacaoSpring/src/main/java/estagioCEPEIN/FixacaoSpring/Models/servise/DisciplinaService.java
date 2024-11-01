package estagioCEPEIN.FixacaoSpring.Models.servise;

import estagioCEPEIN.FixacaoSpring.Models.dto.disciplina.DisciplinasConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.disciplina.DisciplinasDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Disciplina;
import estagioCEPEIN.FixacaoSpring.Models.exceptions.DataNotFoundException;
import estagioCEPEIN.FixacaoSpring.Models.repositorio.DisciplinaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;


    public DisciplinasConsultaDTO getById(Long id) {
        List<Disciplina> DisciList = disciplinaRepository.findAll();
        Disciplina disciFound = new Disciplina();
        DisciplinasConsultaDTO disci = null;

        for(Disciplina buscar : DisciList){
           if(id == buscar.getIds().getId()){
               disciFound = buscar;
               disci = new DisciplinasConsultaDTO(
                       disciFound.getIds(),
                       disciFound.getNome(),
                       disciFound.getSimbolo(),
                       disciFound.getEstagio(),
                       disciFound.getDataRegistro());
           }
        }
        if(disci != null){
            return disci;
        }
        throw new DataNotFoundException("Disciplina");
    }

    public List<DisciplinasConsultaDTO> getAll() {
        List<DisciplinasConsultaDTO> disciplinas = VerListaDisciplinas(disciplinaRepository.findAll());
        return disciplinas;
    }

    @Transactional
    public DisciplinasConsultaDTO save(DisciplinasDTO disciplina) {
        return VerDisciplinasAoCriar(disciplinaRepository.save(new Disciplina(disciplina)));
    }

    @Transactional
    public Disciplina update(Long id, DisciplinasDTO disciplinaNova) {
        List<Disciplina> acharDisci = disciplinaRepository.findAll();
        Disciplina disciFound = null;

        for(Disciplina buscar : acharDisci){
            if(id == buscar.getIds().getId()){
                disciFound = buscar;
                disciFound.setNome(disciplinaNova.nome() != null ? disciplinaNova.nome() : disciFound.getNome());
                disciFound.setSimbolo(disciplinaNova.simbolo() != null ? disciplinaNova.simbolo() : disciFound.getSimbolo());
                disciFound.setEstagio(disciplinaNova.estagio() != null ? disciplinaNova.estagio() : disciFound.getEstagio());
            }
        }
        if(disciFound != null){
            return disciplinaRepository.save(disciFound);
        }
         throw new DataNotFoundException("Disciplina");

    }

    @Transactional
    public String deleteId(Long id) {
        List<Disciplina> ListDisci = disciplinaRepository.findAll();

        for(Disciplina buscar : ListDisci){
           if(id == buscar.getIds().getId()){
               disciplinaRepository.delete(buscar);
                return "Disciplina removida com sucesso";
           }
        }
        throw new DataNotFoundException("Disciplina");
    }

    public DisciplinasConsultaDTO VerDisciplinasAoCriar(Disciplina disciplinaEntidade) {

        DisciplinasConsultaDTO disciNova = new DisciplinasConsultaDTO(
                disciplinaEntidade.getIds(),
                disciplinaEntidade.getNome(),
                disciplinaEntidade.getSimbolo(),
                disciplinaEntidade.getEstagio(),
                disciplinaEntidade.getDataRegistro()
        );
        return disciNova;
    }


            public List<DisciplinasConsultaDTO> VerListaDisciplinas(List<Disciplina> disciplinaEntidade) {
        List<DisciplinasConsultaDTO> disciDTO = new ArrayList<>();

        for (Disciplina disciplina : disciplinaEntidade) {
            DisciplinasConsultaDTO disciNova = new DisciplinasConsultaDTO(
                    disciplina.getIds(),
                    disciplina.getNome(),
                    disciplina.getSimbolo(),
                    disciplina.getEstagio(),
                    disciplina.getDataRegistro()
            );
            disciDTO.add(disciNova);
        }
        if (disciDTO.isEmpty()) {
            throw new DataNotFoundException("Disciplina");
        } else {
            return disciDTO;
        }
    }
}
