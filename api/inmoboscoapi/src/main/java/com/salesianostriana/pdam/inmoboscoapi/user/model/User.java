package com.salesianostriana.pdam.inmoboscoapi.user.model;

import com.salesianostriana.pdam.inmoboscoapi.others.RoleConverterAttribute;
import com.salesianostriana.pdam.inmoboscoapi.property.model.Property;
import com.salesianostriana.pdam.inmoboscoapi.user.UserRole;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
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
    private UUID id;


    @Convert(converter = RoleConverterAttribute.class)
    private EnumSet<UserRole> rol;


    private String firstname;

    private String lastname;

    private String username;
    private String password;
    private String dni;
    private String avatar;
    private LocalDate birthdate;
    private String phoneNumber;
    private String email;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_favorite_properties",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id")
    )
    private List<Property> favoriteProperties = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder.Default
    private LocalDateTime lastPasswordChangeAt = LocalDateTime.now();

    @Builder.Default
    private boolean accountNonExpired = true;
    @Builder.Default
    private boolean accountNonLocked = true;
    @Builder.Default
    private boolean credentialsNonExpired = true;
    @Builder.Default
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rol.stream()
                .map(role -> "ROLE_" + role)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void addUserRole(UserRole role){
        rol.add(role);
    }
    public void removeRole(UserRole role){
        rol.remove(role);
    }

    public void addFavouriteProperty(Property p){
        this.favoriteProperties.add(p);
    }
    public void removeFavouriteProperty(Property p) {
        favoriteProperties.removeIf(property -> property.getId() == p.getId());
    }
}
