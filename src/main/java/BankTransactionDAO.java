package main.java;

import java.time.LocalDate;

/*
    DAO(data access object): 데이터 접근 객체
    - 객체를 식별하는 일종의 ID가 필요하다.
*/
public class BankTransactionDAO {

    public BankTransaction create(final LocalDate date, final double amount, final String description) {
        throw new UnsupportedOperationException();
    }

    public BankTransaction read(final long id) {
        throw new UnsupportedOperationException();
    }

    public BankTransaction update(final long id) {
        throw new UnsupportedOperationException();
    }

    public void delete(final BankTransaction bankTransaction) {
        throw new UnsupportedOperationException();
    }
}
