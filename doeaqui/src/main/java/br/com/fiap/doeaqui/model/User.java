package br.com.fiap.doeaqui.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "TB_DOE_USER")
@SequenceGenerator(name="user", sequenceName = "SQ_TB_DOE_USUARIO", allocationSize = 1)
public class User {

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
    @Column(name = "NM_SENHA", nullable = false, length = 20)
    private String password;

    //instituição
    @Column(name = "DS_USER")
    private String description; //informações da instituição e informações sobre como doar para ela

}
