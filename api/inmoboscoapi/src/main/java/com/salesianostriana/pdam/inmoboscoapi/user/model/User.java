package com.salesianostriana.pdam.inmoboscoapi.user.model;

import com.salesianostriana.pdam.inmoboscoapi.user.UserRole;
import com.salesianostriana.pdam.inmoboscoapi.others.RoleConverterAttribute;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Entity
@Table(name = "USER_ENTITY")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(columnDefinition = "uuid")
    protected UUID id;

    @Convert(converter = RoleConverterAttribute.class)
    protected EnumSet<UserRole> rol;


    protected String firstname;

    protected String lastname;

    protected String username;
    protected String password;
    protected String dni;
    protected String avatar;
    protected LocalDate birthdate;
    protected String phoneNumber;
    protected String email;

    @CreatedDate
    protected LocalDateTime createdAt;

    @Builder.Default
    protected LocalDateTime lastPasswordChangeAt = LocalDateTime.now();

    @Builder.Default
    protected boolean accountNonExpired = true;
    @Builder.Default
    protected boolean accountNonLocked = true;
    @Builder.Default
    protected boolean credentialsNonExpired = true;
    @Builder.Default
    protected boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rol.stream()
                .map(role -> "ROLE " + role)
                .map(SimpleGrantedAuthority::new)
                //.toList();
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
