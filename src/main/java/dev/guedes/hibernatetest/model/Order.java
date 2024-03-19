package dev.guedes.hibernatetest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Date;

/**
 * @author João Guedes
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name="Orders")
@Table(name="Orders")
@NamedQueries({
        @NamedQuery(name="Order.findAllByAccountId", query="SELECT o FROM Orders o WHERE o.account.id = :accountId")
})
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;
    @Column(name="order_date", nullable=false)
    @CreationTimestamp
    private Date orderDate;
    @ManyToOne
    @JoinColumn(name="account_id", nullable = false, foreignKey=@ForeignKey(name="account_id",
            foreignKeyDefinition="FOREIGN KEY (account_id) REFERENCES Accounts(id) ON UPDATE CASCADE ON DELETE CASCADE"))
    private Account account;

}
