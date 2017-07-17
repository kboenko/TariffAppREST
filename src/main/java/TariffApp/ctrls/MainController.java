package TariffApp.ctrls;

import TariffApp.domain.*;
import TariffApp.services.BusinessDelegate;
import TariffApp.services.CRUDServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/tariffApp")
public class MainController {

    @Autowired
    private BusinessDelegate businessDelegate;

    @Autowired
    private CRUDServices crudServices;

    @RequestMapping("/users")
    public List<User> getAllUsers() {
        return crudServices.getAllUsers();
    }

    @RequestMapping("/tariffs")
    public List<Tariff> getAllTariffs() {
        return crudServices.getAllTariffs();
    }

    @RequestMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return crudServices.getUser(id);
    }

    @RequestMapping("/messages/{id}")
    public Message getLastMessageForUser(@PathVariable Long id) {
        return crudServices.getLastMessageForUser(id);
    }

    @ManagedOperation
    @Scheduled(cron = "${cron.check.update}")
    public void checkTariff(){
        checkAndUpdate();
    }

    public void checkAndUpdate() {
        businessDelegate.checkAndUpdateTariffs();
    }

}
