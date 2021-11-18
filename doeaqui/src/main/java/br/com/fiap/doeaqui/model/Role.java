package br.com.fiap.doeaqui.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role implements GrantedAuthority {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "user", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
