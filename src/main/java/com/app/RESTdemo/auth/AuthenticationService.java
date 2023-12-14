package com.app.RESTdemo.auth;

import com.app.RESTdemo.config.JWTService;
import com.app.RESTdemo.student.UserNotFoundException;
import com.app.RESTdemo.user.User;
import com.app.RESTdemo.user.UserRepository;
import com.app.RESTdemo.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthenticationService {

    private final UserRepository _repository;
    private final PasswordEncoder _passwordEncoder;
    private final JWTService _jwtService;

    private final AuthenticationManager _authManager;

    @Autowired
    public AuthenticationService(UserRepository repository,PasswordEncoder passwordEncoder,JWTService jwtservice,AuthenticationManager authenticationManager){
        this._repository = repository;
        this._passwordEncoder = passwordEncoder;
        this._jwtService = jwtservice;
        this._authManager = authenticationManager;

    }
    public AuthenticationResponse register(RegisterRequest request){
        User user = new User(request.getUsername(), this._passwordEncoder.encode(request.getPassword()), UserRole.USER);
        this._repository.save(user);
        String jwt = this._jwtService.generateJWT(user);
        return new AuthenticationResponse(jwt);

    }

    public AuthenticationResponse authenticate(RegisterRequest request){
        this._authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );
        var user = this._repository.findByUsername(request.getUsername()).orElseThrow();
        String jwt = this._jwtService.generateJWT(user);
        return new AuthenticationResponse(jwt);

    }

}
