package community.baribari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BaribariApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaribariApplication.class, args);
    }

}
