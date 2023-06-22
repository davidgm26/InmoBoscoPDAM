package com.salesianostriana.pdam.inmoboscoapi.security.jwt.refresh;

import com.salesianostriana.pdam.inmoboscoapi.user.model.User;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class RefreshToken {

    @Id
    private UUID id;

    @MapsId
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", columnDefinition = "uuid")
    private User user;

    @NaturalId
    @Column(nullable = false,unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    @CreatedDate
    private Instant createdAt;


}
