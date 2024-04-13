package faddy.backend.global.config.Thread;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CheckThreadCount {

    @Test
    public void countActiveThreads() {
        // 현재 실행 중인 스레드 그룹을 가져옵니다.
        ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup parentGroup;
        while ((parentGroup = rootGroup.getParent()) != null) {
            rootGroup = parentGroup;
        }

        // 활성 스레드의 추정 수를 가져옵니다.
        int threadCount = rootGroup.activeCount();
        System.out.println("현재 실행 중인 스레드 수: " + threadCount);

        // 스레드 배열을 생성하고 실제 활성 스레드를 배열에 복사합니다.
        Thread[] threads = new Thread[threadCount];
        int actualThreadCount = rootGroup.enumerate(threads, true);

        // 실제 스레드 수를 출력합니다.
        System.out.println("실제 스레드 수: " + actualThreadCount);

        // 테스트를 위해 스레드 수를 검증하는 부분도 추가할 수 있습니다.
        // 예를 들어, 스레드 수가 0이 아니어야 한다는 것을 확인합니다.
        assert(actualThreadCount > 0);
    }
}
