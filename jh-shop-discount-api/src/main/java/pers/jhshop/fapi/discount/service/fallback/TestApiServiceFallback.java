package pers.jhshop.fapi.discount.service.fallback;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pers.jhshop.fapi.discount.dto.TestDTO;
import pers.jhshop.fapi.discount.dto.req.TestReq;
import pers.jhshop.fapi.discount.service.TestApiService;

/**
 * @author ChenJiahao(五条)
 * @date 2024/12/10 21:56:58
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class TestApiServiceFallback implements TestApiService {

    private Throwable throwable;

    @Override
    public TestDTO testSome(TestReq testReq) {
        log.error("TestApiServiceFallbackFactory fallback, error msg[{}]", throwable.getMessage());
        return new TestDTO();
    }
}
