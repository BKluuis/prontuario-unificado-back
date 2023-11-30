package com.castelar.prontuario.service.exam;

import com.castelar.prontuario.model.User;
import com.castelar.prontuario.model.exam.Hemogram;

public interface IHemogramService {
    /**
     * Função preferida para a associação de um hemograma à um usuário e salvamento do mesmo no banco de dados
     * @param login Login do usuário
     * @param hemogram Hemograma a ser salvo, sem ID 
     * @return Hemograma salvo no Banco de dados
     * @throws UserNotFoundException Caso não encontrar um usuário com o login fornecido
     */
    public Hemogram addHemogramToUser(String login, Hemogram hemogram);

    public void update(Hemogram hemogram, Long hemogramID);

    public void delete(Long id);
    
    public Hemogram findById(Long hemogramID);

    /**
     * Função preferida para a procura de hemogramas
     * @param login Login do usuário atualmente conectado
     * @param id Id do hemograma 
     * @return Hemograma associado com este usuário
     * @throws UserNotFoundException Caso não encontrar um usuário com o login fornecido
     * @throws AppException Caso não encontrar um hemograma associado ao usuário
     */
    public Hemogram findByIdAndUser(String login, Long hemogramID);
}
