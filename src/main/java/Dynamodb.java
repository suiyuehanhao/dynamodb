import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wangpei
 * @date 2020/6/15 22:31
 * @description
 */
@ComponentScan("com.dynamodb.repository")
@SpringBootApplication
public class Dynamodb {
    public static void main(String[] args) {
        SpringApplication.run(Dynamodb.class,args);

    }
}
