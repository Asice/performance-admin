package com.qurich.external.lucene;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.hankcs.lucene.HanLPAnalyzer;
import com.qurich.external.model.Message;

public class LuceneUtil {
	
	private static Logger log = Logger.getLogger(LuceneUtil.class.getClass());
	private static final String Luceneindex="/home/lucene";
	
	private final static long ONEDAY=24*60*60*1000;
	public final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	//private static final String Luceneindex="D:\\spark\\lucene";
	
	private static final Analyzer analyzer = new HanLPAnalyzer();
	private static final String FieldContent = "content";
	private static final int HitsPer = 10;
	
	public static void indexFile(List<Message> messages) {
		try{
			Directory dir = FSDirectory.open(Paths.get(Luceneindex));
			
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
			/*if (create) {
				iwc.setOpenMode(OpenMode.CREATE);
			} else {
				iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
			}*/
			IndexWriter writer = new IndexWriter(dir, iwc);
		    indexDoc(writer, messages);
		    writer.close();
		}catch(Exception e){
			log.error("创建索引失败"+e);
		}
		
	}
	//好像没有自带增量索引，只能每次重建索引
	private static void indexDoc(IndexWriter writer,List<Message> messages) throws IOException{
		 for(Message msg:messages){
			 Document doc = new Document();
			 doc.add(new StringField("time",String.valueOf(msg.getTime().getTime()),Field.Store.YES));
			 doc.add(new TextField("content",String.valueOf(msg.getContent()),Field.Store.YES));//包括标题，都索引
			 writer.addDocument(doc);
		 }
	}	
	
	//返回时间数组
	public static List<String> SearchFile(String line,String filed) {
		List<String> list=new ArrayList<String>();
		try{
			if(StringUtils.isBlank(line))return list;
			IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(Luceneindex)));
		    IndexSearcher searcher = new IndexSearcher(reader);
		    QueryParser parser = new QueryParser(FieldContent, analyzer);
		    Query query = parser.parse(QueryParser.escape(line));
		    
		    //查找
		    //取1000个
		    TopDocs results = searcher.search(query, 100* HitsPer);
		    ScoreDoc[] hits = results.scoreDocs;
		    int numTotalHits = results.totalHits;
		    
		    for(int i=0;i<10;i++){
		    	if(i==numTotalHits)break;
		    	Document doc = searcher.doc(hits[i].doc);
		    	list.add(doc.get(filed));
		    	//TODO 以后需要对结果分数回归，做第二次筛选
		    }
		}catch(Exception e){
			log.error("索引失败"+e);
		}
		return list;
	}
	//传入文章，返回message
	public static List<Message> SearchFile(String line) {
		List<Message> list=new ArrayList<Message>();
		try{
			if(StringUtils.isBlank(line))return list;
			IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(Luceneindex)));
		    IndexSearcher searcher = new IndexSearcher(reader);
		    QueryParser parser = new QueryParser(FieldContent, analyzer);
		    Query query = parser.parse(QueryParser.escape(line));
		    
		    //查找
		    //取1000个
		    TopDocs results = searcher.search(query, 100* HitsPer);
		    ScoreDoc[] hits = results.scoreDocs;
		    int numTotalHits = results.totalHits;
		    
		    for(int i=0;i<10;i++){
		    	if(i==numTotalHits)break;
		    	Document doc = searcher.doc(hits[i].doc);
		    	Message message=new Message();
		    	message.setContent(doc.get("content"));
		    	message.setTime(new Date(Long.valueOf(doc.get("time"))));
		    	list.add(message);
		    	//TODO 以后需要对结果分数回归，做第二次筛选
		    }
		}catch(Exception e){
			log.error("索引失败"+e);
		}
		return list;
	}

	public static Date getWorkDay(String date){
		try{
			//Date time=new Date(Long.valueOf(date));
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date(Long.valueOf(date)));
			int dayWeek=cal.get(Calendar.DAY_OF_WEEK);
			int hour=cal.get(Calendar.HOUR_OF_DAY);
			long time=0l;
			//收盘
			if(dayWeek==1){//星期天
				time=cal.getTimeInMillis()+ONEDAY;
			}else if(dayWeek==7){//星期6
				time=cal.getTimeInMillis()+ONEDAY*2;
			}else if(dayWeek==6){//星期5
				if(hour>=15){//已收盘
					time=cal.getTimeInMillis()+ONEDAY*3;
				}
			}else{
				if(hour>=15){//已收盘
					time=cal.getTimeInMillis()+ONEDAY;
				}
			}
			return new Date(time);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Date();
	}
	 
}
