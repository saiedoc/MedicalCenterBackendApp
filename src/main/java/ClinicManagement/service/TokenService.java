package ClinicManagement.service;

import ClinicManagement.entity.Token;
import ClinicManagement.exception.NotFoundException;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    public Token addToken(Token token){
        try{
            return tokenRepository.save(token);
        }catch (Exception ex){
            throw new ReplicatedException();

        }
    }

    public Token updateToken(Token token){
        try{
          return    tokenRepository.save(token);
        }catch (Exception ex){
            throw new ReplicatedException();

        }
    }


    public void activeToken(int id, boolean isActive){
        try {
            Token token = tokenRepository.findById(id).get();
            token.setActive(isActive);
            tokenRepository.save(token);
        }
        catch (NoSuchElementException ex)
        {
            throw new NotFoundException("Can't active or non-active for not found object");
        }

    }
    public Token getTokenById(int token_id,boolean isActve){
        try{
            return tokenRepository.findByTokenIdAndIsActive(token_id,isActve).get();
        }catch (NoSuchElementException ex){
            throw new NotFoundException();
        }
    }

    public Token getTokenByDoctorId(int doctorId,boolean isActive)
    {
        try{
            return tokenRepository.findTokenByIdAndDoctor(doctorId,isActive).get();
        }catch (NoSuchElementException ex){
            throw new NotFoundException();
        }
    }

    public Token getTokenByPatientId(int patientId,boolean isActive)
    {
        try{
            return tokenRepository.findTokenByIdAndPatient(patientId,isActive).get();
        }catch (NoSuchElementException ex){
            throw new NotFoundException();
        }
    }
}
