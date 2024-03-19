package dev.guedes.hibernatetest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * @author João Guedes
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name="Items")
@Table(name="Items")
@NamedQueries({
        @NamedQuery(name="Item.findAllByOrderId", query="SELECT i FROM Items i WHERE i.order.id = :orderId")
})
public class Item {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;
    @Column(nullable=false)
    private int quantity;
    @Column(name="unit_price", precision=10, scale=2, nullable = false)
    private BigDecimal unitPrice;
    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name="order_id", nullable = false, foreignKey=@ForeignKey(name="order_id",
            foreignKeyDefinition="FOREIGN KEY (order_id) REFERENCES Orders(id) ON UPDATE CASCADE ON DELETE CASCADE"))
    private Order order;

}
