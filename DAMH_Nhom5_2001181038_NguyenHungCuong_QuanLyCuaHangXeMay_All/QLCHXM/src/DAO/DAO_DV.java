/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.POJO_DV;
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
public class DAO_DV {
    public ArrayList<POJO_DV> LayDSDV(){
        ArrayList<POJO_DV> ds=new ArrayList<POJO_DV>();
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("QLCHXM");
            MongoCollection<Document> collection =database.getCollection("DichVu");
            for (Document doc : collection.find()){
            try {
                JSONParser parser = new JSONParser();
                JSONObject json;
                POJO_DV ch =  new POJO_DV();
                json = (JSONObject) parser.parse(doc.toJson());
                String value = json.get("MaHG").toString();
                String value1 = json.get("TenDV").toString();
                ch.setMadv(value);
                ch.setTendv(value1);
                ds.add(ch);          
            } catch (ParseException ex) {
                Logger.getLogger(DAO_DV.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ds;
    }
    public ArrayList<String> LayDSDVcbb(){
        ArrayList<String> ds=new ArrayList<String>();
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("QLCHXM");
            MongoCollection<Document> collection =database.getCollection("DichVu");
            for (Document doc : collection.find()){
            try {
                JSONParser parser = new JSONParser();
                JSONObject json;
                json = (JSONObject) parser.parse(doc.toJson());
                String value = json.get("MaHG").toString();
                ds.add(value);          
            } catch (ParseException ex) {
                Logger.getLogger(DAO_DV.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ds;
    }
    public String tDV(String MaHG)
    {
        Bson filter = eq("MaHG", MaHG);
        Bson project =eq("Gia", 1L);
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("DichVu");
        FindIterable<Document> result = collection.find(filter).projection(project);

        JSONParser parser = new JSONParser();
        JSONObject json;
        String kq = null;
        for (Document document : result)
        {
            try {
                json = (JSONObject) parser.parse(document.toJson());
                String value5 = json.get("Gia").toString();
                kq=value5;
            } catch (ParseException ex) {
                Logger.getLogger(DAO_HANG.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return kq;
    }
    public POJO_DV inforeDV(String MaDV) throws ParseException
    {
        POJO_DV ts= new POJO_DV();
        Bson filter = eq("MaHG", MaDV);
        Bson project = and(eq("TenDV", 1L),eq("Gia", 1L),eq("_id", 0L));
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("DichVu");
        FindIterable<Document> result = collection.find(filter).projection(project);

        JSONParser parser = new JSONParser();
        JSONObject json;
        for (Document document : result)
        {
           POJO_DV ch =  new POJO_DV();
           json = (JSONObject) parser.parse(document.toJson());
           String value = MaDV;
           String value1 = json.get("TenDV").toString();
           String value2 = json.get("Gia").toString();
           ch.setMadv(value);
           ch.setTendv(value1);
           ch.setGia(value2);
           ts = ch;
        }
        return ts;
    }
    public void addDV(String ma,String ten,String gia)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("DichVu");
        BasicDBObject document = new BasicDBObject();
        document.put("MaHG", ma);
        document.put("TenDV", ten);
        document.put("Gia", gia);
        collection.insert(document);
    }
    public void deleteDV(String maDV)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("DichVu");
        BasicDBObject document = new BasicDBObject();
        document.put("MaHG", maDV);
        collection.remove(document);
    }
    public void updateDV(String ma,String ten,String gia)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("DichVu");
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("TenDV", ten).append("Gia",gia));
        BasicDBObject searchQuery = new BasicDBObject().append("MaHG",ma);
        collection.update(searchQuery, newDocument);
    }
}
