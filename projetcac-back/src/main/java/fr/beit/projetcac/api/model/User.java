package fr.beit.projetcac.api.model;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name ="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int idAddress;

    private String username;

    private String password;

    private String mail;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="profile_photo")
    private String profilePhoto;
}
