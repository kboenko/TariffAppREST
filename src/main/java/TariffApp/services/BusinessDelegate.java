package TariffApp.services;

import TariffApp.dao.MessageRepository;
import TariffApp.dao.UserRepository;
import TariffApp.domain.Message;
import TariffApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BusinessDelegate {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Value("#{'${user.message}'}")
    private String msg;

    public void checkAndUpdateTariffs() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        List<User> users = userRepository.findAll();
        String todayString = format.format(new Date());
        try {
            Date today = format.parse(todayString);
            for (User user : users) {
                String lastMod = format.format(user.getLastModified());
                Date lastModified = format.parse(lastMod);
                long diffDates = today.getTime() - lastModified.getTime();
                long diffDays = diffDates / (24 * 60 * 60 * 1000);
                if (diffDays == user.getTariff().getChangePeriod()) {
                    float newMinuteCost;

                    if (user.getMinuteCost()==0) {
                        newMinuteCost = user.getTariff().getMinuteCost() - user.getTariff().getDeltaMinuteCost();
                    } else {
                        newMinuteCost = user.getMinuteCost() - user.getTariff().getDeltaMinuteCost();
                    }

                    user.getTariff().setMinuteCost(newMinuteCost);
                    userRepository.save(user);
                    sendMessage(user, newMinuteCost);
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void sendMessage(User user, float cost) {
        Message message = new Message();
        message.setDate(new Date());
        message.setMessage(msg + " " + cost);
        message.setUser(user);
        messageRepository.save(message);
    }

}
