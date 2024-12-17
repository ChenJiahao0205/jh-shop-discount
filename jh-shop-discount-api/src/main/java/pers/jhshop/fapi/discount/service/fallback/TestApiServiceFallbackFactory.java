package pers.jhshop.fapi.discount.service.fallback;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import pers.jhshop.fapi.discount.service.TestApiService;

/**
 * @author ChenJiahao(五条)
 * @date 2024/12/10 21:56:51
 */
@Component
public class TestApiServiceFallbackFactory implements FallbackFactory<TestApiService> {

    @Override
    public TestApiService create(Throwable cause) {
        return new TestApiServiceFallback(cause);
    }
}
