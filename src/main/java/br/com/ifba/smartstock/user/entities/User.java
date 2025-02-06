package br.com.ifba.smartstock.user.entities;

import br.com.ifba.smartstock.infrastructure.entities.PersistenceEntity; // Classe base para persistência, com atributos e configurações comuns.
import jakarta.persistence.*; // Importação das anotações do JPA para mapeamento da entidade.
import lombok.*;

@Entity // Indica que esta classe é uma entidade JPA, representando uma tabela no banco de dados.
@Table(name = "users") // Especifica o nome da tabela associada a esta entidade.
@Data // Lombok: Gera automaticamente getters, setters, toString, equals e hashCode.
@AllArgsConstructor // Lombok: Gera um construtor com todos os atributos da classe.
@NoArgsConstructor // Lombok: Gera um construtor vazio.
@EqualsAndHashCode(callSuper = false) // Lombok: Configura o método equals e hashCode, ignorando atributos da classe pai.
@Builder
public class User extends PersistenceEntity { // A classe User herda atributos e comportamentos de PersistenceEntity.

    @Column(name = "name", nullable = false) // Indica que a coluna "name" não pode ser nula no banco de dados.
    private String name;

    @Column(name = "login",nullable = false, unique = true) // Define que a coluna "login" é única e não pode ser nula.
    private String login;

    @Column(name = "password",nullable = false) // Indica que a coluna "password" não pode ser nula.
    private String password;

    @Column(name = "email",nullable = false, unique = true) // Indica que a coluna "email" não pode ser nula.
    private String email;
}
