package main;

import user.User;
import user.UserDao;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        UserDao create = new UserDao();
        UserDao update = new UserDao();
        UserDao read = new UserDao();
        UserDao delete = new UserDao();

        // 4.1. Inclusão dos objetos

        LocalDateTime dateTime = LocalDateTime.now().minusDays(1).withHour(17).withMinute(0).withSecond(0).withNano(0);

        User user1 = new User("Ana Zaira", "a.zaira@mail.com", "123", dateTime, true);
        user1.setId(create.saveOrUpdate(user1));

        dateTime = LocalDateTime.now().withHour(3).withMinute(0).withSecond(0).withNano(0);

        User user2 = new User("Beatriz Yana", "b.yana@mail.com", "456", dateTime, true);
        user2.setId(create.saveOrUpdate(user2));

        dateTime = LocalDateTime.now().minusDays(2).withHour(12).withMinute(0).withSecond(0).withNano(0);

        User user3 = new User("Cecília Xerxes", "c.xerxes@mail.com", "789", dateTime, true);
        user3.setId(create.saveOrUpdate(user3));

        User user4 = new User("Débora Wendel", "debora.w@mail.com", "147", null,false);
        user4.setId(create.saveOrUpdate(user4));

        dateTime = LocalDateTime.now().withHour(6).withMinute(0).withSecond(0).withNano(0);

        User user5 = new User("Eugênia Vale", "e.vale@mail.com", "258", dateTime, true);
        user5.setId(create.saveOrUpdate(user5));

        dateTime = LocalDateTime.now().minusDays(1).withHour(23).withMinute(59).withSecond(0).withNano(0);

        User user6 = new User("Fernanda Uchoa", "f.vale@mail.com", "369", dateTime, false);
        user6.setId(create.saveOrUpdate(user6));

        // 4.2. Atualização registros

        user1.setLastAccess(LocalDateTime.now());
        update.saveOrUpdate(user1);

        user3.setActive(false);
        update.saveOrUpdate(user3);

        user4.setEmail("d.wendel@mail.com");
        user4.setPassword("&Yh4$Wu9");
        update.saveOrUpdate(user4);

        user5.setPassword("asdfqwerty");
        user5.setLastAccess(LocalDateTime.now());
        update.saveOrUpdate(user5);

        // 4.3. Impressão do 3º objeto recuperado do banco de dados

        System.out.println(read.findById(3L).toString());

        // 4.4. Impressão de todos os objetos recuperados do banco de dados

        System.out.println(read.findAll().toString());

        // 4.5. Impressão de todos os objetos com estado ativo no banco de dados

        System.out.println(read.findAllActive(true).toString());

        // 4.6. Remoção do 4º objeto inserido no banco de dados

        delete.deleteById(4L);

    }
}