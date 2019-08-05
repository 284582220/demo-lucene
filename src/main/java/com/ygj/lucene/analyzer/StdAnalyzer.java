package com.ygj.lucene.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;

public class StdAnalyzer {

    private static String strCh = "中华人民共和国简称中国，是一个有13亿人口的国家";

    private static String strEn = "Dogs can not achieve a place, eyes can reach";

    public static void main(String[] args) throws IOException {
        System.out.println("");
        stdAnalyzer(strCh);
        stdAnalyzer(strEn);
    }

    public static void stdAnalyzer(String str) throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        analyzer = new StandardAnalyzer();
        StringReader reader = new StringReader(str);
        TokenStream tokenStream = analyzer.tokenStream(str, reader);
        tokenStream.reset();
        CharTermAttribute termAttribute = tokenStream.getAttribute(CharTermAttribute.class);
        while (tokenStream.incrementToken()){
            System.out.print(termAttribute.toString() + "|");
        }
        System.out.println("\n");
        analyzer.close();
    }

}
