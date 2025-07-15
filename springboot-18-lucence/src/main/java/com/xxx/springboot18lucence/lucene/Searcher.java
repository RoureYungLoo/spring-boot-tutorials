package com.xxx.springboot18lucence.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

public class Searcher {
    public static final Logger LOGGER = LoggerFactory.getLogger(Searcher.class);

    private static void search(String indexDir, String keyword) throws Exception {
        //获取要查询的路径，也就是索引所在的位置
        FSDirectory dir = FSDirectory.open(Paths.get(indexDir));
        IndexReader reader = DirectoryReader.open(dir);

        //构建IndexSearcher
        IndexSearcher searcher = new IndexSearcher(reader);
        //标准分词器
        Analyzer analyzer = new StandardAnalyzer();

        //查询解析器
        QueryParser parser = new QueryParser("contents", analyzer);
        //通过解析要查询的String，获取查询对象，keyword为传进来的待查的字符串
        Query query = parser.parse(keyword);

        long startTime = System.currentTimeMillis();

        TopDocs docs = searcher.search(query, 10);

        long endTime = System.currentTimeMillis();

        LOGGER.info("匹配{}耗时ms", endTime - startTime);
        LOGGER.info("匹配到{}条记录", docs.totalHits);

        //取出每条查询结果
        for (ScoreDoc scoreDoc : docs.scoreDocs) {
            //scoreDoc.doc相当于docID,根据这个docID来获取文档
            Document doc = searcher.doc(scoreDoc.doc);
            //fullPath是刚刚建立索引的时候我们定义的一个字段，表示路径。也可以取其他的内容，只要我们在建立索引时有定义即可
            LOGGER.info("fullPath: {}", doc.get("fullPath"));
        }

        reader.close();
    }


    public static void main(String[] args) {
        String indexDir = "D:\\spirngboot-tutorials\\springboot-18-lucence\\lucene";
        String keyword = "guo";

        try {
            search(indexDir, keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
