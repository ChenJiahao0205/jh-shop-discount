package pers.jhshop.discount;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MapperScan(basePackages = "pers.jhshop.discount.mapper")
@SpringBootApplication
public class JhShopDiscountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JhShopDiscountServiceApplication.class, args);
    }

}
