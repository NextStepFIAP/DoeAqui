package br.com.fiap.doeaqui.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_DOE_USER")
@SequenceGenerator(name="user", sequenceName = "SQ_TB_DOE_USUARIO", allocationSize = 1)
public class User implements UserDetails {

    //geral
    @Id
    @GeneratedValue(generator = "user", strategy = GenerationType.SEQUENCE)
    @Column(name = "CD_USUARIO", nullable = false)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "NM_USUARIO", nullable = false, length = 64)
    private String name;

    @NotBlank(message = "O email é obrigatório")
    @Column(name = "DS_EMAIL", nullable = false, length = 128, unique = true)
    private String email;

    @Size(min = 8, message = "A senha deve conter pelo menos 8 caracteres")
    @Column(name = "NM_SENHA", nullable = false, length = 100)
    private String password;

    //instituição
    @Column(name = "DS_USER")
    private String description; //informações da instituição e informações sobre como doar para ela

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.email;
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

    public User(Long id, String name, String email, String password, String description) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.description = description;
    }

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, String description) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.description = description;
    }

    public User(Long id) {
        this.id = id;
    }
}
