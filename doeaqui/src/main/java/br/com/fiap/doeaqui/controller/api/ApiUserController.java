package br.com.fiap.doeaqui.controller.api;

import br.com.fiap.doeaqui.model.User;
import br.com.fiap.doeaqui.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    @Cacheable("login")
    public List<User> findUsuario() {
        return repository.findByDescriptionIsNull();
    }

    @GetMapping("/instituicao")
    @Cacheable("login")
    public List<User> findInstituicao() {
        return repository.findByDescriptionIsNotNull();
    }


    @PostMapping
    @CacheEvict(value="login", allEntries = true)
    public ResponseEntity<User> create(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {

        repository.save(user);

        URI uri = uriBuilder.path("/api/user/1").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> show(@PathVariable Long id){
        return ResponseEntity.of(repository.findById(id));
    }

    @DeleteMapping("{id}")
    @CacheEvict(value="login", allEntries = true)
    public ResponseEntity<User> destroy(@PathVariable Long id){
        Optional<User> user = repository.findById(id);

        if(user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    @CacheEvict(value="login", allEntries = true)
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Valid User newUser){
        Optional<User> optional = repository.findById(id);

        if(optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = optional.get();
        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setDescription(newUser.getDescription());

        repository.save(user);

        return ResponseEntity.ok(user);

    }

}
