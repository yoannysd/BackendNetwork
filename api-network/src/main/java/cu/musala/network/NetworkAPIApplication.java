package cu.musala.network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NetworkAPIApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetworkAPIApplication.class, args);
        System.out.println("Configured parameters:");
        for (int i = 0; i < args.length; i++) {
            System.out.println("Argument " + (i + 1) + ": " + args[i]);
        }
    }

}
