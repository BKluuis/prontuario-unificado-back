package com.castelar.prontuario.service.exam;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.castelar.prontuario.model.User;
import com.castelar.prontuario.model.exam.Hemogram;
import com.castelar.prontuario.repository.IUserRepository;
import com.castelar.prontuario.repository.exam.HemogramRepository;
import com.castelar.prontuario.exception.AppException;

import lombok.RequiredArgsConstructor;

/**
 * TODO: Uma conta profissional ou admin não deve ser dona de exames
 */
@Service
@RequiredArgsConstructor
public class HemogramService implements IHemogramService {
    private final HemogramRepository hemogramRepository; 
    private final IUserRepository userRepository;
    
    /**
     * TODO: Não permitir a inserção de hemogramas exatamente iguals (com exceção do próprio id)
     */
    @Override
    public Hemogram addHemogramToUser(String login, Hemogram hemogram) {
        User loggedUser = userRepository.findByLogin(login).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        hemogram.setId(null);
        hemogram.setOwner(loggedUser);
        Hemogram savedHemogram = hemogramRepository.save(hemogram);

        return savedHemogram;
    }

    @Override
    public void update(Hemogram hemogram, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Hemogram findById(Long id) {
        Hemogram hemogram = hemogramRepository.findById(id)
                                              .orElseThrow(() -> new AppException("No hemogram found with the supplied ID", HttpStatus.NOT_FOUND));
        return hemogram;
    }


    @Override
    public Hemogram findByIdAndUser(String login, Long id) {
        User loggedUser = userRepository.findByLogin(login).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        Hemogram foundHemogram = hemogramRepository.findByOwner(loggedUser)
                        .orElseThrow(() -> new AppException("The hemogram with the given ID doesn't exists or is not associated with this user", HttpStatus.NOT_FOUND));

        return foundHemogram;
    }
}
