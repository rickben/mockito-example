package ThreadLocals;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StoreService {

    MockedService mockedService;

    public void setStoreState() {
        CurrentStoreState.setCurrentState(StoreState.OPEN);
        mockedService.operationAfterSetStoreState();
        CurrentStoreState.setCurrentState(StoreState.CLOSED);
        mockedService.operationAfterSetStoreState();
    }
}
