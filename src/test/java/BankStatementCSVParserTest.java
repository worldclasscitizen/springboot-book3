package test.java;

import main.java.BankStatementCSVParser;
import main.java.BankStatementParser;
import main.java.BankTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class BankStatementCSVParserTest {

    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void 한_줄_파싱하기() throws Exception {
        final String line = "30/01/2017,-50,Tesla";

        final BankTransaction result = statementParser.parseFrom(line);

        final BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesla");
        final double tolerance = 0.0d;

        Assertions.assertEquals(expected.getDate(), result.getDate());
        Assertions.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
        Assertions.assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void 여러_줄_파싱하기() throws Exception {
        final List<String> lines = List.of(
                "01/01/2017,6000,Tesla",
                "08/01/2017,-33000,Lamborghini",
                "15/01/2017,11000,Benz",
                "22/01/2017,1500,Hyundai",
                "29/01/2017,-70000,Rolls Royce"
        );

        final List<BankTransaction> result = statementParser.parseLinesFrom(lines);

        final List<BankTransaction> expected = List.of(
                new BankTransaction(LocalDate.of(2017, Month.JANUARY, 1), 6000, "Tesla"),
                new BankTransaction(LocalDate.of(2017, Month.JANUARY, 8), -33000, "Lamborghini"),
                new BankTransaction(LocalDate.of(2017, Month.JANUARY, 15), 11000, "Benz"),
                new BankTransaction(LocalDate.of(2017, Month.JANUARY, 22), 1500, "Hyundai"),
                new BankTransaction(LocalDate.of(2017, Month.JANUARY, 29), -70000, "Rolls Royce")
        );

        final double tolerance = 0.0d;

        Assertions.assertEquals(expected.size(), result.size(), "결과 크기가 일치하지 않습니다.");
        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertEquals(expected.get(i).getDate(), result.get(i).getDate(), "날짜가 일치하지 않습니다.");
            Assertions.assertEquals(expected.get(i).getAmount(), result.get(i).getAmount(), tolerance, "금액이 일치하지 않습니다.");
            Assertions.assertEquals(expected.get(i).getDescription(), result.get(i).getDescription(), "설명이 일치하지 않습니다.");
        }
    }

    @Test
    public void 날짜_구분자가_잘못되면_오류_발생() throws Exception {
        final String line = "30-01-2017,-50,Tesla";

        Assertions.assertThrows(
                java.time.format.DateTimeParseException.class,
                () -> statementParser.parseFrom(line)
        );
    }
}
