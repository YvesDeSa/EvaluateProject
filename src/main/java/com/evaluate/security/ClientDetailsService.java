package com.evaluate.security;

import com.evaluate.model.Client;
import com.evaluate.model.Permit;
import com.evaluate.repository.ClientRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailsService implements UserDetailsService {
    
    @Autowired
    private ClientRepository repo;
    
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Client client = repo.findByLogin(login);

        if(client == null){
            throw new UsernameNotFoundException("Cliente não encontrado com esse login: " + login);
        }
        return new User(client.getLogin(), client.getPassword(), getAuthoritis(client.getPermits()));
    }
    
    private List<GrantedAuthority> getAuthoritis(List<Permit> permitsList){
        List<GrantedAuthority> list = new ArrayList<>();
        
        permitsList.forEach(permits -> {
            list.add(new SimpleGrantedAuthority("ROLE_" + permits.getName()));
        });
        
        return list;
    }
    
}
