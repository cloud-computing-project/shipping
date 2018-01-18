package rso.projects.shippings.services;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import rso.projects.shippings.Shipping;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
public class ShippingsBean {

    @Inject
    private EntityManager em;

    public List<Shipping> getShippings(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery())
                .defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, Shipping.class, queryParameters);

    }

    public Shipping getShipping(String shippingId) {

        Shipping shipping = em.find(Shipping.class, shippingId);

        if (shipping == null) {
            throw new NotFoundException();
        }

        return shipping;
    }

    public Shipping createShipping(Shipping shipping) {

        try {
            beginTx();
            em.persist(shipping);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return shipping;
    }

    public Shipping putShipping(String shippingId, Shipping shipping) {

        Shipping s = em.find(Shipping.class, shippingId);

        if (s == null) {
            return null;
        }

        try {
            beginTx();
            shipping.setId(s.getId());
            shipping = em.merge(shipping);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return shipping;
    }

    public boolean deleteShipping(String shippingId) {

        Shipping shipping = em.find(Shipping.class, shippingId);

        if (shipping != null) {
            try {
                beginTx();
                em.remove(shipping);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
            }
        } else
            return false;

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }
}
