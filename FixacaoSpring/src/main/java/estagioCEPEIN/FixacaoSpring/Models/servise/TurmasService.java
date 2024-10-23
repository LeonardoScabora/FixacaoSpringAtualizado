package estagioCEPEIN.FixacaoSpring.Models.servise;

import estagioCEPEIN.FixacaoSpring.Models.dto.TurmasConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.TurmasDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Turmas;
import estagioCEPEIN.FixacaoSpring.Models.repositorio.TurmaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TurmasService {

    @Autowired
    TurmaRepository turmaRepository;

    public Turmas getById(Long id){
        return turmaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não Encontrado"));
    }

    public List<TurmasConsultaDTO> getAll(){
        List<TurmasConsultaDTO> turmas = preencherTurmasDTO(turmaRepository.findAll());
        return turmas;
    }

    @Transactional
    public Turmas save(TurmasDTO turma) {
        return turmaRepository.save(new Turmas(turma));}

    @Transactional
    public Turmas update(Long id, TurmasDTO turma) {
        Turmas turmaFound = getById(id);

        turmaFound.setSerie(turma.serie() != null ? turma.serie() : turmaFound.getSerie());

        return turmaRepository.save(turmaFound);
    }

    @Transactional
    public String delete(Long id) {
        turmaRepository.deleteById(id);
        return "Turma removida com sucesso!";
    }

    public List<Turmas> buscarTurmasPorSerie(List<String> serie) {
        return turmaRepository.findBySerieIn(serie);
    }

    private List<TurmasConsultaDTO> preencherTurmasDTO(List<Turmas> TurmasEntidades) {
        List<TurmasConsultaDTO> turmaDTO = new ArrayList<TurmasConsultaDTO>();

        for(Turmas turma: TurmasEntidades) {
            TurmasConsultaDTO turmaNova = new TurmasConsultaDTO(
                    turma.getId(),
                    turma.getSerie(),
                    turma.getDataRegistro()
            );

            turmaDTO.add(turmaNova);
        }
        return turmaDTO;
    }

}
