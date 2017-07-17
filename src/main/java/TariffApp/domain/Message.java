package TariffApp.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;

/**
 * СМС-сообщение
 */

@Entity
@Table(name="messages")
public class Message {

    /**
     * ID
     */
    private Long id;

    /**
     * Дата прихода сообщения
     */
    private Date date;

    /**
     * Текст сообщения
     */
    private String message;

    /**
     * Пользователь-получатель сообщения
     */
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="msg_date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name="message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
