package io.codyn.sqldb.core;

import io.codyn.types.Transactions;

import java.util.function.Supplier;

public class SqlTransactions implements Transactions {

    private final DSLContextProvider contextProvider;


    public SqlTransactions(DSLContextProvider contextProvider) {
        this.contextProvider = contextProvider;
    }

    @Override
    public void execute(Runnable transaction) {
        contextProvider.transaction(transaction);
    }

    @Override
    public <T> T executeAndReturn(Supplier<T> transaction) {
        return contextProvider.transactionResult(transaction);
    }
}
