package com.evaluate;

import com.evaluate.model.Admin;
import com.evaluate.model.Client;
import com.evaluate.model.Comment;
import com.evaluate.model.Evaluation;
import com.evaluate.model.Permit;
import com.evaluate.repository.AdminRepository;
import com.evaluate.repository.ClientRepository;
import com.evaluate.repository.CommentRepository;
import com.evaluate.repository.EvaluationRepository;
import com.evaluate.repository.PermitRepository;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EvaluateApplication  implements CommandLineRunner{
    
    @Autowired
    private ClientRepository clientRepo;
    @Autowired
    private AdminRepository adminRepo;
    @Autowired
    private EvaluationRepository evaluationRepo;
    @Autowired
    private CommentRepository commentRepo;
    @Autowired
    private PermitRepository permitRepo;
    
    
    public static void main(String[] args) {
	SpringApplication.run(EvaluateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       Permit p1 = new Permit();
       p1.setName("ADMIN");
       
       Permit p2 = new Permit();
       p2.setName("CLIENT");
       
       permitRepo.saveAll(List.of(p1,p2));
        
        
       Admin admin1 = new Admin();
       admin1.setLogin("Maria");
       admin1.setPassword(new BCryptPasswordEncoder().encode("123"));
       admin1.setEmail("Maria44@gmail.com");
       admin1.setPermits(List.of(p1));
       admin1.setToken(23l);
       
       adminRepo.save(admin1);
       
       Client client1 = new Client();
       client1.setLogin("Carlos123");
       client1.setPassword(new BCryptPasswordEncoder().encode("12345"));
       client1.setEmail("Carlos@gmail.com");
       client1.setName("Carlos");
       client1.setCity("Campos");
       client1.setPermits(List.of(p2));
       
       clientRepo.save(client1);
       
       Client client2 = new Client();
       client2.setLogin("monicaGS");
       client2.setPassword(new BCryptPasswordEncoder().encode("1234578"));
       client2.setEmail("MGS@gmail.com");
       client2.setName("Monica");
       client2.setCity("Marataizes");
       client2.setPermits(List.of(p2));
       
       clientRepo.save(client2);
       
       Evaluation evaluation1 = new Evaluation();
       evaluation1.setRestaurantName("carne aqui");
       evaluation1.setRestaurantCategory("carne");
       evaluation1.setRestaurantLocation("Campos");
       evaluation1.setNote(5);
       evaluation1.setDescription("otimo atendimento");
       Calendar date = Calendar.getInstance();
       date.set(2020,10,10);
       evaluation1.setDate(date);
       evaluation1.setClient(client1);
       
       evaluationRepo.save(evaluation1);
       
       client1.setEvaluations(List.of(evaluation1));
       clientRepo.save(client1);
       
       Comment comment1 = new Comment();
       comment1.setEvaluation(evaluation1);
       comment1.setClient(client2);
       comment1.setDescription("otimo comentario");
       
       commentRepo.save(comment1);
       
       evaluation1.setComments(List.of(comment1));
       evaluationRepo.save(evaluation1);
       
       client2.setComments(List.of(comment1));
       clientRepo.save(client2);
       
    }

}
