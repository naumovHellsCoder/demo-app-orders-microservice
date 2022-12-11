package gmarmari.demo.microservices.orders.api;

import java.util.function.Supplier;

public enum Result {

    OK,
    ERROR,
    ;

    boolean isOk() {
        return this == OK;
    }

    public <X extends Throwable> void throwIfNotOk(Supplier<? extends X> exceptionSupplier) throws X {
        if (!isOk()) {
            throw exceptionSupplier.get();
        }
    }

}
