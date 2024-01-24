package com.castelar.prontuario.service.exam;

import com.castelar.prontuario.exception.AppException;
import com.castelar.prontuario.model.exam.Hemogram;

import java.util.List;

public interface IHemogramService {
    /**
     * Função preferida para a associação de um hemograma à um usuário e salvamento
     * do mesmo no banco de dados
     * 
     * @param patientLogin      Login do paciente ao qual o hemograma será associado
     * @param ProfessionalLogin Login do profissional que cadastrou o hemograma
     * @param hemogram          Hemograma a ser salvo, sem ID
     * @return Hemograma salvo no Banco de dados
     * @throws AppException Caso não encontrar um usuário com o login fornecido
     */
    public Hemogram addHemogramToUser(String patientLogin, String ProfessionalLogin, Hemogram hemogram)
            throws AppException;

    /**
     * Atualiza o campo de observação do hemograma e garante que não têm mais de 512
     * caracteres
     * 
     * @param login      Login do profissional responsável pelo hemograma
     * @param newComment Novo comentário
     * @param hemogramId Id do hemograma
     * @return Hemograma salvo
     */
    public Hemogram updateComment(String login, String newComment, Long hemogramId);

    public void delete(Long id);

    public Hemogram findById(Long hemogramID);

    /**
     * Procura o hemograma que contém o usuário fornecido, tanto como dono do
     * hemograma quando como profissional responsável
     * 
     * @param login      Login do usuário atualmente conectado
     * @param hemogramID Id do hemograma
     * @return Hemograma associado com este usuário
     * @throws AppException Caso não encontrar um usuário com o login fornecido ou
     *                      não encontrar um hemograma associado ao usuário
     */
    public Hemogram findByIdAndUser(String login, Long hemogramID) throws AppException;

    public List<Hemogram> findUserHemograms(String patientLogin) throws AppException;
}
