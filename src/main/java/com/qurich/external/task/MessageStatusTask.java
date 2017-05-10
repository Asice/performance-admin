package com.qurich.external.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.qurich.external.mapper.MessageMapper;
import com.qurich.external.mapper.StockInfoMapper;
import com.qurich.external.model.Message;
import com.qurich.external.model.StockInfo;
import com.qurich.external.utils.QuarterReportDayUtil;

/**
 * 业绩定时器
 * @author Asice
 *
 */
@Service
@EnableAsync
public class MessageStatusTask {

		private static Logger log = Logger.getLogger(MessageStatusTask.class.getClass());
		@Autowired
		private StockInfoMapper stockInfoMapper;
		
		@Autowired
		private MessageMapper messageMapper;
		
		//@Scheduled(cron ="0 15 23 * * ?")
		@Scheduled(fixedRate=3*60*1000)
		@Async
		protected void spilerReport() throws ParseException{
			log.info("start");
			List<StockInfo> list=stockInfoMapper.getListAll();
			long time=new Date().getTime()-60*60*1000;
			List<Message> messages=messageMapper.getBeanListTypeStatusTime(new Date(time), 0, 3);
			for(Message msg:messages){
				String title=msg.getTitle();
				boolean  exit=false;
				for(StockInfo stock:list){
					if(title.contains(stock.getName())){
						messageMapper.updateStatusById(msg.getId(), 1);//1为A股
						exit=true;
						break;
					}
				}
				if(!exit){
					messageMapper.updateStatusById(msg.getId(), 2);//未知，标示已处理
				}
			}
			log.info("end");
		}
}
