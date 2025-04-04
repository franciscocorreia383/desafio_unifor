package desafio.unifor.resource;

import desafio.unifor.dto.curso.CursoDTO;
import desafio.unifor.service.CursoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/api/v1/cursos")
@Produces(MediaType.APPLICATION_JSON)
public class CursoResource {

    @Inject
    CursoService cursoService;

    @GET
    @Operation(summary = "Listar Cursos", description = "Retorna todos os cursos cadastrados")
    @APIResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public Response listarCursos(){
        try {
            return Response.ok(cursoService.listarCursos()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Buscar Curso Por ID", description = "Retorna curso cadastrado")
    @APIResponse(responseCode = "200", description = "Curso encontrado com sucesso")
    public Response buscarCurso(@PathParam("id") long id){
        try{
            return Response.ok(cursoService.buscarCursoPorId(id)).build();
        }catch (NotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Operation(summary = "Cadastrar Curso", description = "Cadastra um novo curso")
    @APIResponse(responseCode = "201", description = "Curso cadastrado com sucesso")
    public Response criarCurso(CursoDTO curso) {
        try {
            cursoService.criarCurso(curso);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }


}
