package kamianowski.maciej.demo.rest;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/github")
@Produces(MediaType.APPLICATION_JSON)
public class GithubResource {

    private final GithubService githubService;

    public GithubResource(GithubService githubService) {
        this.githubService = githubService;
    }

    @GET
    @Path("/repos/{username}")
    public Uni<Response> getRepositories(@PathParam("username") String username) {
        return githubService.getRepositories(username)
                .onItem().transform(repos -> repos.stream()
                        .filter(repo -> !repo.isFork())
                        .map(repo -> new RepositoryDto(repo.getRepository(), repo.getOwner().getOwnerName(), repo.getBranches()))
                        .toList())
                .onItem().transform(Response::ok)
                .onItem().transform(Response.ResponseBuilder::build)
                .onFailure().recoverWithItem(error -> Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse(Response.Status.NOT_FOUND.getStatusCode(), "User not found"))
                        .build());
    }
}
