package com.mrl.mongodb;

import java.util.Date;

import org.bson.Document;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class testMongodb {
	public static void main(String[] args) {
		String ip = "127.0.0.1";
		int port = 27017;
		String dbname = "epointtest";
		
		MongoClient mC = new MongoClient(ip, port);
		MongoDatabase db = mC.getDatabase(dbname);
		MongoCollection<Document> colletion = db.getCollection("frame_user");
		
		
		//插入数据
		JSONObject user = new JSONObject();
		user.put("userguid", "110");
		user.put("username", "测试人员1");
		user.put("birthday", new Date());
		user.put("updateTime", new Date());
		user.put("gender", "男");
		JSONObject addres = new JSONObject();
		addres.put("city", "LosAngelos");
		addres.put("contory", "US");
		user.put("address", addres);
		
		Document record = Document.parse(user.toJSONString());
		
		colletion.insertOne(record);
		
		for(Document item : colletion.find().sort(new BasicDBObject("userguid",1))){
			System.out.println("----"+item.getString("username"));
		}
		
	}
}
