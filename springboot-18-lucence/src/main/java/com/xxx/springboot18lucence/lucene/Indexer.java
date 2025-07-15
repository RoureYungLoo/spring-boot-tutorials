package com.xxx.springboot18lucence.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class Indexer {
    private static final Logger logger = LoggerFactory.getLogger(Indexer.class);
    private IndexWriter writer;

    public Indexer(String indexDir) throws Exception {
        FSDirectory directory = FSDirectory.open(Paths.get(indexDir));

        //标准分词器，会自动去掉空格啊，is a the等单词
        StandardAnalyzer analyzer = new StandardAnalyzer();
        //将标准分词器配到写索引的配置中
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        //实例化写索引对象
        IndexWriter writer = new IndexWriter(directory, config);

        this.writer = writer;
    }

    public int indexAll(String dataDir) throws IOException {
        // 获取该路径下的所有文件
        File[] files = new File(dataDir).listFiles();
        if (null != files) {
            for (File file : files) {
                indexFile(file); //调用下面的indexFile方法，对每个文件进行索引
            }
        }
        //返回索引的文件数
        return writer.numDocs();

    }

    private void indexFile(File file) throws IOException {
        logger.info("索引文件路径：{}", file.getCanonicalPath());
        Document document = getDocument(file);
        writer.addDocument(document);

    }

    private Document getDocument(File file) throws IOException {
        Document document = new Document();
        //开始添加字段
        document.add(new TextField("contents", new FileReader(file)));
        document.add(new TextField("fileName", file.getName(), Field.Store.YES));
        document.add(new TextField("fullPath", file.getCanonicalPath(), Field.Store.YES));
        return document;
    }

    public void close() throws IOException {
        writer.close();
    }

    public static void main(String[] args) {
        String indexDir = "D:\\spirngboot-tutorials\\springboot-18-lucence\\lucene";
        String dataDir = "D:\\spirngboot-tutorials\\springboot-18-lucence\\lucene\\data";

        Indexer indexer = null;
        int indexedNum = 0;
        long startTS = System.currentTimeMillis();

        try {
            indexer = new Indexer(indexDir);
            indexedNum = indexer.indexAll(dataDir);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != indexer) {
                    indexer.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long endTS = System.currentTimeMillis();
        logger.info("索引耗时{}ms", endTS - startTS);
        logger.info("索引了{}个文件", indexedNum);
    }
}
