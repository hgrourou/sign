package com.signin.demo.secruity;

import com.signin.demo.dao.secruity.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountDao accountDAO;

    /**
     * This will be called from
     * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider#retrieveUser(java.lang.String, org.springframework.security.authentication.UsernamePasswordAuthenticationToken)}
     * when
     * . Easy.
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.signin.demo.entity.User user = accountDAO.getAccountByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }

        String[] roles = user.getRole().split(",");
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (roles != null && roles.length > 0) {
            for(String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }

        boolean accountNonLocked = true;

//        if(user.getLocked() == 1){
//            accountNonLocked = false;
//        }

        return new User(username, user.getPassword(), true, true, true, accountNonLocked,
                authorities);
    }
}
