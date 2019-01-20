package fr.bdf.exa.poc.rest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.opentracing.Traced;

import com.google.gson.Gson;

import fr.bdf.exa.poc.domain.PocEntiry;
import fr.bdf.exa.poc.service.PostgresqlService;
import fr.bdf.exa.poc.service.ServiceBean;
import io.opentracing.Tracer;

@Transactional
@Path("/postgresThorntail")
@Schema(name = "/mysqlThorntail")
public class PocEndpoint {

    @Inject
    private Tracer tracer;

    @Inject
    private ServiceBean serviceBean;

    @Inject
    PostgresqlService myService;

    @GET
    @Path("/service")
    @Traced(operationName = "Service_invocation")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response service() {
        Gson g = new Gson();
        String str = g.toJson(serviceBean.method());
        return Response.status(200).entity(str).build();

    }

    @GET
    @Traced(false)
    @Path("/health")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Health
    public Response firstHealthCheckMethod() {
        Gson g = new Gson();
        String str = g.toJson("Healthy");
        return Response.status(201).entity(str).build();

    }

    @GET
    @Traced
    @Path("/home")
    @Counted(monotonic = true, name = "poc-count", absolute = true)
    @Timed(name = "poc-time", absolute = true)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doGet() {
        Gson g = new Gson();
        String str = g.toJson("Hello from BDF!");
        return Response.status(201).entity(str).build();
 
    }

    @GET
    @Traced(operationName = "all")
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPocsCount() {
        return Response.ok(new Gson().toJson(myService.getCount())).build();
    }

    @GET
    @Traced(operationName = "getById")
    @Counted(monotonic = true, name = "poc-count-get", absolute = true)
    @Timed(name = "poc-time-get", absolute = true)
    @Path("/{id}")
    @Operation(summary = "Get poc", description = "This will  return a poc")
    @APIResponse(responseCode = "200", description = "A poc's data",
            content = @Content(
                    schema = @Schema(implementation = PocEntiry.class)))
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPocById(@PathParam("id") Integer id){

        return Response.ok(new Gson().toJson(myService.getPoc(id))).build();
    }

    @POST
    @Traced(operationName = "createPoc")
    @Path("/post")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPocEntry(PocEntiry poc) {

        myService.createPoc(poc);
        Gson g = new Gson();
        String str = g.toJson("OK");
        return Response.status(201).entity(str).build();

    }

    @DELETE
    //@Traced(operationName = "deletePoc")
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removePocEntry(@PathParam("id") Integer id) {
        myService.deletePoc(id);
        Gson g = new Gson();
        String str = g.toJson("OK");
        return Response.status(201).entity(str).build();
    }

    @PUT
    @Traced(operationName = "updatePoc")
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePocEntry(@PathParam("id") Long id, PocEntiry poc) {

        myService.updatePoc(poc);
        Gson g = new Gson();
        String str = g.toJson("OK");
        return Response.status(200).entity(str).build();
    }

    @GET
    @Traced(operationName = "fallback")
    @Path("/fallback")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doFallback() {

        String ret = serviceBean.doFallback();
        Gson g = new Gson();
        String str = g.toJson(ret);
        return Response.status(200).entity(str).build();
    }

    @GET
    @Traced(operationName = "retry")
    @Path("/retry")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response retry() throws IOException {
        String ret = serviceBean.retry();
        Gson g = new Gson();
        String str = g.toJson(ret);
        return Response.status(200).entity(str).build();
    }

   
    @GET
    @Traced(operationName = "circuitBreaker")
    @Path("/circuitBreaker")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response circuitBreaker() throws IOException {

        String ret = serviceBean.circuitBreaker();
        Gson g = new Gson();
        String str = g.toJson(ret);
        return Response.status(200).entity(str).build();

    }

    @GET
    @Traced(operationName = "bulkhead")
    @Path("/bulkhead")
    @Bulkhead(2)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response bulkhead() {
        String ret = serviceBean.bulkhead();
        Gson g = new Gson();
        String str = g.toJson(ret);
        return Response.status(200).entity(str).build();
    }

}
