package com.evaluate;

import com.evaluate.model.Admin;
import com.evaluate.model.Client;
import com.evaluate.model.Comment;
import com.evaluate.model.Evaluation;
import com.evaluate.repository.AdminRepository;
import com.evaluate.repository.ClientRepository;
import com.evaluate.repository.CommentRepository;
import com.evaluate.repository.EvaluationRepository;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
    
    
    public static void main(String[] args) {
	SpringApplication.run(EvaluateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
       Admin admin1 = new Admin();
       admin1.setLogin("Maria");
       admin1.setPassword("abc12");
       admin1.setEmail("Maria44@gmail.com");
       admin1.setToken(23l);
       
       adminRepo.save(admin1);
       
       Client client1 = new Client();
       client1.setLogin("Carlos123");
       client1.setPassword("12345");
       client1.setEmail("Carlos@gmail.com");
       client1.setName("Carlos");
       client1.setCity("Campos");
       
       clientRepo.save(client1);
       
       Client client2 = new Client();
       client2.setLogin("monicaGS");
       client2.setPassword("ffdrs");
       client2.setEmail("MGS@gmail.com");
       client2.setName("Monica");
       client2.setCity("Marataizes");
       
       clientRepo.save(client2);
       
       Evaluation evaluation1 = new Evaluation();
       evaluation1.setRestaurantName("carne aqui");
       evaluation1.setRestaurantCategory("carne");
       evaluation1.setRestaurantLocation("Campos");
       evaluation1.setNote("bom");
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
