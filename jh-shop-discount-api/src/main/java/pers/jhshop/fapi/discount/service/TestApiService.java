package pers.jhshop.fapi.discount.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pers.jhshop.fapi.discount.consts.ServiceConst;
import pers.jhshop.fapi.discount.dto.TestDTO;
import pers.jhshop.fapi.discount.dto.req.TestReq;
import pers.jhshop.fapi.discount.service.fallback.TestApiServiceFallbackFactory;

/**
 * @author ChenJiahao(五条)
 * @date 2024/12/10 21:49:48
 */
@FeignClient(value = "jh-shop-discount-service", contextId = "TestApiService", fallbackFactory = TestApiServiceFallbackFactory.class)
public interface TestApiService {

    // TODO 将JhShopDiscountApiConstants依赖转移到API-Parent
    String BASE_URL = "/fapi/" + "/test";

    @PostMapping("/fapi/test/test-some")
    TestDTO testSome(@RequestBody TestReq testReq);
}
