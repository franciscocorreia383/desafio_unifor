package desafio.unifor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class AlunoDTO {
    private String nome;

    private String matricula;

}
