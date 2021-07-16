/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.POJO_HOADON;
import java.util.ArrayList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author ngock
 */
public class DAO_HOADON {
    public ArrayList<POJO_HOADON> LayDSHD(){
        ArrayList<POJO_HOADON> ds=new ArrayList<POJO_HOADON>();
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("QLCHXM");
            MongoCollection<Document> collection =database.getCollection("HoaDon");
            for (Document doc : collection.find()){
            try {
                JSONParser parser = new JSONParser();
                JSONObject json;
                POJO_HOADON ch =  new POJO_HOADON();
                json = (JSONObject) parser.parse(doc.toJson());
                String value = json.get("MaHD").toString();
                String value1 = json.get("ThanhTien").toString();
                ch.setMahd(value);
                ch.setThanhtien(value1);
                ds.add(ch);          
            } catch (ParseException ex) {
                Logger.getLogger(DAO_KHACHHANG.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ds;
    }
    public POJO_HOADON inforeHD(String MaHD) throws ParseException
    {
        POJO_HOADON ts= new POJO_HOADON();
        Bson filter = eq("MaHD", MaHD);
        Bson project = and(eq("MaKH", 1L),eq("MaNV", 1L),eq("ThanhTien", 1L));
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("HoaDon");
        FindIterable<Document> result = collection.find(filter).projection(project);

        JSONParser parser = new JSONParser();
        JSONObject json;
        for (Document document : result)
        {
           POJO_HOADON ch =  new POJO_HOADON();
           json = (JSONObject) parser.parse(document.toJson());
           String value = MaHD;
           String value1 = json.get("MaKH").toString();
           String value2 = json.get("MaNV").toString();
           String value3 = json.get("ThanhTien").toString();
           ch.setMahd(value);
           ch.setMakh(value1);
           ch.setManv(value2);
           ch.setThanhtien(value3);
           ts = ch;
        }
        return ts;
    }
    public String tHD(String MaHD) throws ParseException
    {
        Bson filter = eq("MaHD", MaHD);
        Bson project =eq("ThanhTien", 1L);
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("HoaDon");
        FindIterable<Document> result = collection.find(filter).projection(project);
        String kqhd =null;
        JSONParser parser = new JSONParser();
        JSONObject json;
        for (Document document : result)
        {
           
           json = (JSONObject) parser.parse(document.toJson());
           String value3 = json.get("ThanhTien").toString();
           kqhd = value3;
        }
        return kqhd;
    }
    public void addHD(String mahd,String makh,String manv,String tt)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("HoaDon");
        BasicDBObject document = new BasicDBObject();
        document.put("MaHD", mahd);
        document.put("MaKH", makh);
        document.put("MaNV", manv);
        document.put("ThanhTien", tt);
        collection.insert(document);
    }
    public void deleteKH(String makh)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("HoaDon");
        BasicDBObject document = new BasicDBObject();
        document.put("MaHD", makh);
        collection.remove(document);
    }
    public void updateHD(String ma,String tt)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("HoaDon");
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("ThanhTien", tt));
        BasicDBObject searchQuery = new BasicDBObject().append("MaHD",ma);
        collection.update(searchQuery, newDocument);
    }
}
