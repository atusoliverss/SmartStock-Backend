package br.com.ifba.smartstock.user.entities;

import br.com.ifba.smartstock.infrastructure.PersistenceEntity; // Classe base para persistência, com atributos e configurações comuns.
import jakarta.persistence.*; // Importação das anotações do JPA para mapeamento da entidade.
import lombok.AllArgsConstructor; // Lombok: Gera um construtor com todos os atributos.
import lombok.Data; // Lombok: Gera automaticamente os métodos getter, setter, toString, equals e hashCode.
import lombok.EqualsAndHashCode; // Lombok: Gera o método equals e hashCode, com configurações específicas.
import lombok.NoArgsConstructor; // Lombok: Gera um construtor sem argumentos.

@Entity // Indica que esta classe é uma entidade JPA, representando uma tabela no banco de dados.
@Table(name = "users") // Especifica o nome da tabela associada a esta entidade.
@Data // Lombok: Gera automaticamente getters, setters, toString, equals e hashCode.
@AllArgsConstructor // Lombok: Gera um construtor com todos os atributos da classe.
@NoArgsConstructor // Lombok: Gera um construtor vazio.
@EqualsAndHashCode(callSuper = false) // Lombok: Configura o método equals e hashCode, ignorando atributos da classe pai.
public class User extends PersistenceEntity { // A classe User herda atributos e comportamentos de PersistenceEntity.

    @Column(nullable = false) // Indica que a coluna "name" não pode ser nula no banco de dados.
    private String name;

    @Column(unique = true, nullable = false) // Define que a coluna "login" é única e não pode ser nula.
    private String login;

    @Column(nullable = false) // Indica que a coluna "password" não pode ser nula.
    private String password;

    @Column(nullable = false) // Indica que a coluna "email" não pode ser nula.
    private String email;
}
