/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.POJO_HANG;
import POJO.POJO_KHACHHANG;
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
public class DAO_KHACHHANG {
    public ArrayList<POJO_KHACHHANG> LayDSKH(){
        ArrayList<POJO_KHACHHANG> ds=new ArrayList<POJO_KHACHHANG>();
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("QLCHXM");
            MongoCollection<Document> collection =database.getCollection("KhachHang");
            for (Document doc : collection.find()){
            try {
                JSONParser parser = new JSONParser();
                JSONObject json;
                POJO_KHACHHANG ch =  new POJO_KHACHHANG();
                json = (JSONObject) parser.parse(doc.toJson());
                String value = json.get("MaKH").toString();
                String value1 = json.get("TenKH").toString();
                ch.setMakh(value);
                ch.setTenkh(value1);
                ds.add(ch);          
            } catch (ParseException ex) {
                Logger.getLogger(DAO_KHACHHANG.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ds;
    }
    public POJO_KHACHHANG inforeKhachHang(String MaKH) throws ParseException
    {
        POJO_KHACHHANG ts= new POJO_KHACHHANG();
        Bson filter = eq("MaKH", MaKH);
        Bson project = and(eq("TenKH", 1L),eq("Diachi", 1L),eq("sdt", 1L));
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("KhachHang");
        FindIterable<Document> result = collection.find(filter).projection(project);

        JSONParser parser = new JSONParser();
        JSONObject json;
        for (Document document : result)
        {
           POJO_KHACHHANG ch =  new POJO_KHACHHANG();
           json = (JSONObject) parser.parse(document.toJson());
           String value = MaKH;
           String value1 = json.get("TenKH").toString();
           String value2 = json.get("Diachi").toString();
           String value3 = json.get("sdt").toString();
           ch.setMakh(value);
           ch.setTenkh(value1);
           ch.setDiachi(value2);
           ch.setSdt(value3);
           ts = ch;
        }
        return ts;
    }
    public void addKhachHang(String ma,String ten,String dc,String sdt)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("KhachHang");
        BasicDBObject document = new BasicDBObject();
        document.put("MaKH", ma);
        document.put("TenKH", ten);
        document.put("Diachi", dc);
        document.put("sdt", sdt);
        collection.insert(document);
    }
    public void deleteKH(String makh)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("KhachHang");
        BasicDBObject document = new BasicDBObject();
        document.put("MaKH", makh);
        collection.remove(document);
    }
    public void updateKH(String ma,String ten,String dc,String sdt)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("KhachHang");
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("TenKH", ten).append("Diachi",dc).append("sdt",sdt));
        BasicDBObject searchQuery = new BasicDBObject().append("MaKH",ma);
        collection.update(searchQuery, newDocument);
    }
}
