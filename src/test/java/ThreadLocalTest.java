import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ThreadLocals.CurrentStoreState;
import ThreadLocals.MockedService;
import ThreadLocals.StoreService;
import ThreadLocals.StoreState;

@ExtendWith(MockitoExtension.class)
public class ThreadLocalTest {

    private StoreService underTest;

    @Mock
    private MockedService mockedService;

    @BeforeEach
    void init() {
        underTest = new StoreService(mockedService);
    }

    // Notice: here we can't call verify because it's a non mocked object but a service method
    @Test
    void threadLocal_differentValuesCalled() {
        List<StoreState> storeStates = new ArrayList<>();
        doAnswer(invocation -> storeStates.add(CurrentStoreState.getCurrentState()))// saving store states we set
                .when(mockedService).operationAfterSetStoreState();

        underTest.setStoreState();

        assertThat(storeStates).containsExactlyInAnyOrder(StoreState.values());
    }
}

// mocking a static class is more complex (can be done using MockitoPower) - the reason is:
// To modify a class' byte code at runtime.