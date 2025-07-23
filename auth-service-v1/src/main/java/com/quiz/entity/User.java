package com.quiz.entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
// @Entity - Indicates that the class is an main.entity and is mapped to a database table
@Data
// @Data - Generates getter, setter, equals, hashCode, toString, and required constructors
@Table(name ="users")
// @Table - Specifies the name of the database table associated with the main.entity
@Getter
@Setter
@Builder
// @Builder - Generates a builder pattern for the main.entity
@NoArgsConstructor
@AllArgsConstructor
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue - Specifies the generation strategy for the primary key - creating an ID automatically
    private Long id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
