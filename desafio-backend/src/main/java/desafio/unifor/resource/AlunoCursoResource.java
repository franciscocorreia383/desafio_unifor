package desafio.unifor.resource;

import desafio.unifor.dto.aluno.AlunoCadastroDTO;
import desafio.unifor.dto.alunoCurso.AlunoCursoDTO;
import desafio.unifor.service.AlunoCursoService;
import desafio.unifor.service.AlunoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/api/v1/alunos-cursos")
@Produces(MediaType.APPLICATION_JSON)
public class AlunoCursoResource {

    @Inject
    AlunoCursoService alunoCursoService;

    @POST
    @Operation(summary = "Cadastrar Aluno ao Curso", description = "Cadastra um aluno a um curso")
    @APIResponse(responseCode = "201", description = "Aluno cadastrado com sucesso")
    public Response adicionaAluno(AlunoCursoDTO alunoCursoDTO) {
        try {
            alunoCursoService.adicionaAlunoAoCurso(alunoCursoDTO);
            return Response.status(Response.Status.CREATED).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Operation(summary = "Listar Alunos Cadastrado no Curso", description = "Retorna todos os alunos cadastrados no curso")
    @APIResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public Response buscarAlunos(@QueryParam("cursoId") long alunoId) {
        try {
            return Response.ok(alunoCursoService.buscarAlunosPorCurso(alunoId)).build();
        } catch (Exception e) {
            System.out.println("Erro ao buscar " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
