package hello.aop.exam;

import hello.aop.exam.annotation.Trace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExamService {

    private final ExamRepository repository;

    @Trace
    public void save(String itemId){
        repository.save(itemId);
    }
}
