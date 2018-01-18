package rso.projects.shippings.api.v1.resources;

import rso.projects.shippings.Shipping;
import rso.projects.shippings.services.ShippingsBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import org.eclipse.microprofile.metrics.annotation.Metered;

@ApplicationScoped
@Path("/shippings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShippingsResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private ShippingsBean shippingsBean;

    @GET
    @Metered
    public Response getShippings() {

        List<Shipping> shippings = shippingsBean.getShippings(uriInfo);

        return Response.ok(shippings).build();
    }

    @GET
    @Path("/{shippingId}")
    public Response getShipping(@PathParam("shippingId") String shippingId) {

        Shipping shipping = shippingsBean.getShipping(shippingId);

        if (shipping == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(shipping).build();
    }

    @POST
    public Response createShipping(Shipping shipping) {

        if (shipping.getProductId() == null || shipping.getProductId().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            shipping = shippingsBean.createShipping(shipping);
        }

        if (shipping.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(shipping).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(shipping).build();
        }
    }

    @PUT
    @Path("{shippingId}")
    public Response putShipping(@PathParam("shippingId") String shippingId, Shipping shipping) {

        shipping = shippingsBean.putShipping(shippingId, shipping);

        if (shipping == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            if (shipping.getId() != null)
                return Response.status(Response.Status.OK).entity(shipping).build();
            else
                return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("{shippingId}")
    public Response deleteShipping(@PathParam("shippingId") String shippingId) {

        boolean deleted = shippingsBean.deleteShipping(shippingId);

        if (deleted) {
            return Response.status(Response.Status.GONE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
