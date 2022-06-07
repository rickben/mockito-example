package ThreadLocals;

public class CurrentStoreState {

    private static final ThreadLocal<StoreState> STORE_STATE_THREAD_LOCAL = new ThreadLocal<>();

    public static StoreState getCurrentState() {
        if (isInitialized()) {
            return STORE_STATE_THREAD_LOCAL.get();
        }
        throw new IllegalStateException("Current store state was not initialized");
    }

    public static boolean isInitialized() {
        return STORE_STATE_THREAD_LOCAL.get() != null;
    }

    public static void setCurrentState(StoreState state) {
        STORE_STATE_THREAD_LOCAL.set(state);
    }

    public static void clear() {
        STORE_STATE_THREAD_LOCAL.remove();
    }
}
