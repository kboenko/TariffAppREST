package TariffApp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
    Тариф
 */

@Entity
@Table(name="tariffs")
public class Tariff {
    /**
     * ID
     */
    private Long id;

    /**
     * Название тарифа
     */
    private String name;

    /**
     * Стоимость минуты разговора
     */
    private Float minuteCost;

    /**
     * Число, на которое уменьшается стоимость минуты разговора через заданный интервал времени
     */
    private Float deltaMinuteCost;

    /**
     * Интервал времени, через который происходит уменьшение стоимости минуты разговора
     */
    private Integer changePeriod;

    /**
     * Пользователи, подключенные к данному тарифу
     */
    private Set<User> userSet;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="tariff_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="minute_cost")
    public Float getMinuteCost() {
        return minuteCost;
    }

    public void setMinuteCost(Float minuteCost) {
        this.minuteCost = minuteCost;
    }

    @Column(name="delta_minute")
    public Float getDeltaMinuteCost() {
        return deltaMinuteCost;
    }

    public void setDeltaMinuteCost(Float deltaMinuteCost) {
        this.deltaMinuteCost = deltaMinuteCost;
    }

    @Column(name="change_period_days")
    public Integer getChangePeriod() {
        return changePeriod;
    }

    public void setChangePeriod(Integer changePeriod) {
        this.changePeriod = changePeriod;
    }

    @OneToMany(mappedBy = "tariff", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonBackReference
    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

}
