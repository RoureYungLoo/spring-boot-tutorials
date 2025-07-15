package com.xxx.springboot18lucence.lucene;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

public class ChineseIndexer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChineseIndexer.class);

    private Directory dir;

    //准备一下用来测试的数据
    //用来标识文档
    private Integer ids[] = {1, 2, 3};
    private String cities[] = {"上海", "南京", "青岛"};
    private String descs[] = {
            "上海是个繁华的城市。",
            "南京是一个文化的城市南京，简称宁，是江苏省会，地处中国东部地区，长江下游，濒江近海。全市下辖11个区，总面积6597平方公里，2013年建成区面积752.83平方公里，常住人口818.78万，其中城镇人口659.1万人。[1-4] “江南佳丽地，金陵帝王州”，南京拥有着6000多年文明史、近2600年建城史和近500年的建都史，是中国四大古都之一，有“六朝古都”、“十朝都会”之称，是中华文明的重要发祥地，历史上曾数次庇佑华夏之正朔，长期是中国南方的政治、经济、文化中心，拥有厚重的文化底蕴和丰富的历史遗存。[5-7] 南京是国家重要的科教中心，自古以来就是一座崇文重教的城市，有“天下文枢”、“东南第一学”的美誉。截至2013年，南京有高等院校75所，其中211高校8所，仅次于北京上海；国家重点实验室25所、国家重点学科169个、两院院士83人，均居中国第三。[8-10] 。",
            "青岛是一个美丽的城市。"
    };

    public void index(String indexDir) throws Exception {
        dir = FSDirectory.open(Paths.get(indexDir));
        // 先调用 getWriter 获取IndexWriter对象
        IndexWriter writer = getWriter();
        for (int i = 0; i < ids.length; i++) {
            Document doc = new Document();
            // 把上面的数据都生成索引，分别用id、city和desc来标识
            doc.add(new IntField("id", ids[i], Field.Store.YES));
            doc.add(new StringField("city", cities[i], Field.Store.YES));
            doc.add(new TextField("desc", descs[i], Field.Store.YES));
            //添加文档
            writer.addDocument(doc);
            LOGGER.info("正在添加文档doc：{}", doc);
        }
        //close了才真正写到文档中
        writer.close();
        LOGGER.info("文档添加完毕");

    }

    private IndexWriter getWriter() throws Exception {
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer(); //使用中文分词器
        IndexWriterConfig config = new IndexWriterConfig(analyzer); //将中文分词器配到写索引的配置中
        LOGGER.info("dir:{}, config:{}", dir, config);
        IndexWriter writer = new IndexWriter(dir, config); //实例化写索引对象
        return writer;
    }

    public static void main(String[] args) throws Exception {
        String indexDir = "D:\\spirngboot-tutorials\\springboot-18-lucence\\luceneCN";
        new ChineseIndexer().index(indexDir);
    }
}
