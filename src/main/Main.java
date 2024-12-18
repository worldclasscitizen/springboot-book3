package main;

import main.java.BankStatementAnalyzer;
import main.java.BankStatementCSVParser;
import main.java.BankStatementParser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();

        final BankStatementParser bankStatementParser = new BankStatementCSVParser();

        bankStatementAnalyzer.analyze(args[0], bankStatementParser);
    }
}
