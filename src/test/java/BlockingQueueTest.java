import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.example.spring.BlockingQueue;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BlockingQueueTest {

    private BlockingQueue blockingQueue;

    @BeforeEach
    void setUp() { blockingQueue = new BlockingQueue();}

    @Test
    void shouldAddElement() throws InterruptedException {
        blockingQueue.enqueue(10);
        assertEquals(1, blockingQueue.size());
    }

    @Test
    void shouldReturnElement() throws InterruptedException {
        blockingQueue.enqueue(10);
        assertEquals(10, blockingQueue.dequeue());
    }

    @Test
    void shouldPreserveFifoOrder() throws InterruptedException {
        List<Integer> result = new ArrayList<>();
        List<Integer> correctResult = List.of(10,20);
        blockingQueue.enqueue(10);
        blockingQueue.enqueue(20);
        result.add(blockingQueue.dequeue());
        result.add(blockingQueue.dequeue());
        assertEquals(correctResult, result);
    }
}

