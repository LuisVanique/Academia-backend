package com.br.luisvanique.academia.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.luisvanique.academia.domain.instrutor.Instrutor;
import com.br.luisvanique.academia.repository.InstrutorRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	
    private InstrutorRepository repository;

    public AuthenticationService(InstrutorRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }

    public static Instrutor getLoggedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean usuarioLogado = authentication != null && !"anonymousUser".equals(authentication.getPrincipal());
        return usuarioLogado ? (Instrutor) authentication.getPrincipal() : null;
    }

}