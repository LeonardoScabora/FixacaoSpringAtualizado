package estagioCEPEIN.FixacaoSpring.Models.servise;

import estagioCEPEIN.FixacaoSpring.Models.dto.turma.TurmasConsultaDTO;
import estagioCEPEIN.FixacaoSpring.Models.dto.turma.TurmasDTO;
import estagioCEPEIN.FixacaoSpring.Models.entidades.Turma;
import estagioCEPEIN.FixacaoSpring.Models.exceptions.DataNotFoundException;
import estagioCEPEIN.FixacaoSpring.Models.repositorio.TurmaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TurmasService {

    @Autowired
    TurmaRepository turmaRepository;

    public Turma getById(Long id){
        if(turmaRepository.existsById(id)){
            return turmaRepository.findById(id).get();
        }
        throw new DataNotFoundException("Turma");
    }

    public List<TurmasConsultaDTO> getAll(){
        List<TurmasConsultaDTO> turmas = VerTurmas(turmaRepository.findAll());
        return turmas;
    }

    @Transactional
    public TurmasConsultaDTO save(TurmasDTO turma) {
        return VerTurma(turmaRepository.save(new Turma(turma)));}

    @Transactional
    public TurmasConsultaDTO update(Long id, TurmasDTO turma) {
        Turma turmaFound = getById(id);

        turmaFound.setSerie(turma.serie() != null ? turma.serie() : turmaFound.getSerie());

        return VerTurma(turmaRepository.save(turmaFound));
    }

    @Transactional
    public String delete(Long id) {
        if(turmaRepository.existsById(id)){
            turmaRepository.deleteById(id);
            return "Turma removida com sucesso!";
        }else{
            throw new DataNotFoundException("Turma");
        }
    }

    ///findByIn
    public List<Turma> buscarTurmasPorSerie(List<String> serie) {
        return turmaRepository.findBySerieIn(serie);
    }

    private TurmasConsultaDTO VerTurma(Turma turmaEntidade) {
            TurmasConsultaDTO turmaNova = new TurmasConsultaDTO(
                    turmaEntidade.getId(),
                    turmaEntidade.getSerie(),
                    turmaEntidade.getDataRegistro()
            );
            if(turmaNova != null){
                return turmaNova;
            }
            throw new DataNotFoundException("Turma");
    }

    private List<TurmasConsultaDTO> VerTurmas(List<Turma> turmaEntidades) {
        List<TurmasConsultaDTO> turmaDTO = new ArrayList<TurmasConsultaDTO>();

        for(Turma turma: turmaEntidades) {
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
