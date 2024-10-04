package tech.chillo.avis;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AvisService {

    private UtilisateurRepository utilisateurRepository;
    private AvisRepository avisRepository;

    public void save(AvisDTO avisDTO) {
        Utilisateur utilisateur = Utilisateur.builder().nom(avisDTO.nom()).build();
        utilisateur =utilisateurRepository.save(utilisateur);

        Avis avis = Avis.builder().message(avisDTO.message()).utilisateur(utilisateur).creation(LocalDateTime.now()).build();
        this.avisRepository.save(avis);

    }
    public List<AvisDTO> search() {
        return this.avisRepository.findAll().stream().map(avis -> new AvisDTO(avis.getMessage(), avis.getUtilisateur().getNom()))
                .collect(Collectors.toList());
    }
}
