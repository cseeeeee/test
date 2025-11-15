package my.testproj.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * 트랜잭션 설정 클래스
 * */
@Slf4j
@Configuration
public class TransactionConfig {

    /**
     * 트랜잭션 포인트컷
     */
    private static final String TX_POINTCUT_EXPRESSION = "execution(* my.testproj.module..*Service.*(..))";


}
