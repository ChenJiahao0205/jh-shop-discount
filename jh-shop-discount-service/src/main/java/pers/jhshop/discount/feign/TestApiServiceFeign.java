package pers.jhshop.discount.feign;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.web.bind.annotation.RestController;
import pers.jhshop.fapi.discount.dto.TestDTO;
import pers.jhshop.fapi.discount.dto.req.TestReq;
import pers.jhshop.fapi.discount.service.TestApiService;

/**
 * @author ChenJiahao(五条)
 * @date 2024/12/10 22:07:21
 */
@RestController
@AllArgsConstructor
public class TestApiServiceFeign implements TestApiService {

    @Override
    public TestDTO testSome(TestReq testReq) {
        TestDTO testDTO = new TestDTO();
        if (BooleanUtils.isTrue(testReq.getFlag())){
            testDTO.setResult(true);
        }else {
            testDTO.setResult(false);
        }

        return testDTO;
    }
}
