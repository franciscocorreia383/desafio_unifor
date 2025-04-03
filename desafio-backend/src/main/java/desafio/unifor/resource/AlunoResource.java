package desafio.unifor.resource;

import desafio.unifor.dto.aluno.AlunoCadastroDTO;
import desafio.unifor.service.AlunoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/api/v1/alunos")
@Produces(MediaType.APPLICATION_JSON)
public class AlunoResource {

    @Inject
    AlunoService alunoService;

    @GET
    @Operation(summary = "Listar Alunos", description = "Retorna todos os alunos cadastrados")
    @APIResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public Response buscarAlunos() {
        try {
            return Response.ok(alunoService.buscarAlunos()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Operation(summary = "Cadastrar Aluno", description = "Cadastra um novo aluno")
    @APIResponse(responseCode = "201", description = "Aluno cadastrado com sucesso")
    public Response criarAlunos(AlunoCadastroDTO aluno) {
        try {
            alunoService.criarAluno(aluno);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Operation(summary = "Excluir Aluno", description = "Exclui um aluno por id")
    @APIResponse(responseCode = "200", description = "Aluno excluido com sucesso")
    public Response excluirAluno(@QueryParam("id") long idAluno) {
        try {
            alunoService.excluirAluno(idAluno);
            return Response.status(Response.Status.OK).build();
        }catch (NotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        catch (Exception e) {
            return Response.serverError().build();
        }
    }


}
