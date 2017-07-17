package TariffApp.services;

import TariffApp.dao.MessageRepository;
import TariffApp.dao.TariffRepository;
import TariffApp.dao.UserRepository;
import TariffApp.domain.Message;
import TariffApp.domain.Tariff;
import TariffApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CRUDServices {

    @Autowired
    private TariffRepository tariffRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Tariff> getAllTariffs() {
        return tariffRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findOne(id);
    }

    public Message getLastMessageForUser(Long id) {
        List<Message> messages = messageRepository.findByUser(id);
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String today = dateFormat.format(new Date());

        for(Message msg : messages) {
            String msgDate = dateFormat.format(msg.getDate());
            if(msgDate.equals(today)) {
                return msg;
            }
        }
        return null;
    }

}
