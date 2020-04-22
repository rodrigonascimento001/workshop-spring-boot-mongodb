package com.estudos.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.estudos.domain.Post;
import com.estudos.domain.User;
import com.estudos.dto.AuthorDTO;
import com.estudos.dto.CommentDTO;
import com.estudos.repository.PostRepository;
import com.estudos.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
 
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
 
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
	
		Post post1 = new Post(null,sdf.parse("22/04/2020"),"Partiu Viagem","Vou viajar para sp abraços!",new AuthorDTO(maria));
		Post post2 = new Post(null,sdf.parse("23/04/2020"),"Bom dia","Acordei Feliz hoje!",new AuthorDTO(maria));
 
		CommentDTO c1 = new CommentDTO("Boa Viagem Mano!", sdf.parse("21/04/2020"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/04/2020"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("24/04/2020"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		postRepository.saveAll(Arrays.asList(post1,post2));
		
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
	}

}
