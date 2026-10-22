package engtelecom.std.labrest.entities;

import lombok.*;

@Data
public class Pessoa {

    private Long id;

    @NonNull // estes atributos nao podem ser nulos
    private String nome;

    @NonNull
    private String email;

    public Pessoa() {
    }

    public Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
