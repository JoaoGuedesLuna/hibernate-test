package dev.guedes.hibernatetest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author João Guedes
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name="Accounts")
@Table(name="Accounts")
@NamedQueries({
        @NamedQuery(name="Account.findByEmail", query="SELECT a FROM Accounts a WHERE a.email = :email")
})
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;
    @Column(length=256, unique=true, nullable = false)
    private String email;
    @Column(length=50, nullable = false)
    private String password;

}
