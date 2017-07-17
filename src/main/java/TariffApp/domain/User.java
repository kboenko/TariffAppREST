package TariffApp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Пользователь
 */

@Entity
@Table(name="users")
public class User {

    /**
     * ID
     */
    private Long id;

    /**
     * Имя пользователя
     */
    private String name;

    /**
     * Тариф, к которому подключен данный пользователь
     */
    private Tariff tariff;

    /**
     * СМС-сообщения об изменении тарифа, полученные пользователем
     */
    private Set<Message> messageSet;

    /**
     * Дата, в которую в последний раз менялся тариф
     */
    private Date lastModified;

    /**
     * Стоимость минуты разговора для данного пользователя с учётом всех изменений его тарифа
     */
    private Float minuteCost;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonBackReference
    public Set<Message> getMessageSet() {
        return messageSet;
    }

    public void setMessageSet(Set<Message> messageSet) {
        this.messageSet = messageSet;
    }

    @ManyToOne
    @JoinColumn(name = "tarif_id")
    @JsonManagedReference
    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    @Column(name="last_modified")
    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Column(name="minute_cost")
    public Float getMinuteCost() {
        return minuteCost;
    }

    public void setMinuteCost(Float minuteCost) {
        this.minuteCost = minuteCost;
    }
}
