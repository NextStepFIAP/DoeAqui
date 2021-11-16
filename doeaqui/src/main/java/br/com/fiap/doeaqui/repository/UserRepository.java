package br.com.fiap.doeaqui.repository;

import br.com.fiap.doeaqui.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

	Page<User> findByNameLike(String name, Pageable pageable);

	Optional<User> findByEmail(String username); //Pode existir o email ou não

	List<User> findByDescriptionIsNull(); //Usuário

	List<User> findByDescriptionIsNotNull(); //Instituição

}
