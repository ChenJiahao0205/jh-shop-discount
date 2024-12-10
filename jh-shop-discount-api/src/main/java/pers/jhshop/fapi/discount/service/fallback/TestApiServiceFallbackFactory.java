package pers.jhshop.fapi.discount.service.fallback;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author ChenJiahao(五条)
 * @date 2024/12/10 21:56:51
 */
@Component
public class TestApiServiceFallbackFactory implements FallbackFactory<TestApiServiceFallback> {

    @Override
    public TestApiServiceFallback create(Throwable cause) {
        return new TestApiServiceFallback(cause);
    }
}
