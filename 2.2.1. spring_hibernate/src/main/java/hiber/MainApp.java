package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      User usr;
      usr = new User("UserA", "LastnameA", "userA@mail.ru");
      usr.setCar(new Car("A", 123));
      userService.add(usr);
      usr = new User("UserB", "LastnameB", "userB@mail.ru");
      usr.setCar(new Car("B", 456));
      userService.add(usr);
      usr = new User("UserC", "LastnameC", "userC@mail.ru");
      usr.setCar(new Car("C", 789));
      userService.add(usr);

      List<User> users = userService.listUsers();
      for (User user : users) {
/*         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if(user.getCar() != null) {
            System.out.println("Car model = "+user.getCar().getModel());
            System.out.println("Car series = "+user.getCar().getSeries());
         }
         System.out.println();*/
         System.out.println(user);
      }

      User userByQuery = userService.getUserByModelAndSeries("A", 123);
      if(userByQuery != null) {
         System.out.println(userByQuery);
      }

      context.close();
   }
}
