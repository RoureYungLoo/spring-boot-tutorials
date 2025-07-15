package com.xxx.springboot18lucence.lucene;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ChineseSearcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChineseSearcher.class);

    public static List<String> search(String indexDir, String keyword) throws IOException, ParseException, InvalidTokenOffsetsException {
        LOGGER.info("searchDir:{},keyword:{}", indexDir, keyword);
        FSDirectory dir = FSDirectory.open(Paths.get(indexDir)); //获取要查询的路径
        DirectoryReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);

        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer(); //使用中文分词器
        QueryParser queryParser = new QueryParser("desc", analyzer);  //由中文分词器初始化查询解析器
        Query query = queryParser.parse(keyword); //通过解析要查询的String，获取查询对象

        long startTime = System.currentTimeMillis();

        TopDocs docs = searcher.search(query, 10);  //开始查询

        long endTime = System.currentTimeMillis();

        LOGGER.info("匹配{}耗时{}ms", keyword, endTime - startTime);
        LOGGER.info("匹配到{}记录", docs.totalHits);

        //如果不指定参数的话，默认是加粗，即<b><b/>
        SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter("<b><font color=red>", "</font></b>");
        QueryScorer scorer = new QueryScorer(query); //根据查询对象计算得分，会初始化一个查询结果最高的得分
        SimpleSpanFragmenter fragmenter = new SimpleSpanFragmenter(scorer); //根据这个得分计算出一个片段
        Highlighter highlighter = new Highlighter(htmlFormatter, scorer); //将这个片段中的关键字用上面初始化好的高亮格式高亮
        highlighter.setTextFragmenter(fragmenter); //设置一下要显示的片段

        //取出每条查询结果
        List<String> list = new ArrayList<>();
        for (ScoreDoc scoreDoc : docs.scoreDocs) {
            Document doc = searcher.doc(scoreDoc.doc);
            LOGGER.info("city:{}", doc.get("city"));
            LOGGER.info("desc:{}", doc.get("desc"));
            String desc = doc.get("desc");

            //显示高亮
            if (desc != null) {
                TokenStream tokenStream = analyzer.tokenStream("desc", new StringReader(desc));
                String summary = highlighter.getBestFragment(tokenStream, desc);
                LOGGER.info("高亮后的desc:{}", summary);
                list.add(summary);
            }
        }

        reader.close();
        return list;

    }

}
