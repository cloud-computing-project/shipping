package rso.projects.shippings;
import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;

@Entity(name = "shippings")
@NamedQueries(value =
        {
                @NamedQuery(name = "Shippings.getAll", query = "SELECT s FROM shippings s")
        })
@UuidGenerator(name = "idGenerator")
public class Shipping {

        @Id
        @GeneratedValue(generator = "idGenerator")
        private String id;

        @Column(name = "product_id")
        private String productId;

        @Column(name = "shipping_to")
        private String shippingTo;

        private String service;

        private String delivery;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getProductId() {
                return productId;
        }

        public void setProductId(String productId) {
                this.productId = productId;
        }

        public String getShippingTo() {
                return shippingTo;
        }

        public void setShippingTo(String shippingTo) {
                this.shippingTo = shippingTo;
        }

        public String getService() {
                return service;
        }

        public void setService(String service) {
                this.service = service;
        }

        public String getDelivery() {
                return delivery;
        }

        public void setDelivery(String delivery) {
                this.delivery = delivery;
        }
}
