package com.bancosimplificado.service;

import com.bancosimplificado.domain.user.User;
import com.bancosimplificado.domain.user.UserType;
import com.bancosimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void ValidateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuário do Tipo Lojista não esta autorizado a realizar transação");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário Não Encontrado"));
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }
}
