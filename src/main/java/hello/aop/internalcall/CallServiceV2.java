package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {

    //ObjectProvider 사용
    private final ObjectProvider<CallServiceV2> callServiceProvider;
    private CallServiceV2 callServiceV2;

    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceProvider) {
        this.callServiceProvider = callServiceProvider;
    }

    public void external() {
        log.info("call external");
        callServiceV2 = callServiceProvider.getObject();//ObjectProvider.getObject()
        callServiceV2.internal();
    }

    public void internal(){
        log.info("call internal");
    }
}
