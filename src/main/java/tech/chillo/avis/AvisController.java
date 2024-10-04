package tech.chillo.avis;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@AllArgsConstructor
@RestController
@RequestMapping("avis")
public class AvisController {

    AvisService avisService;

    @GetMapping
    public List<AvisDTO> search() {
        return this.avisService.search();
    }

    @ResponseStatus(code = CREATED)
    @PostMapping
    public void save(@RequestBody AvisDTO avisDTO) {
        this.avisService.save(avisDTO);
    }
}
