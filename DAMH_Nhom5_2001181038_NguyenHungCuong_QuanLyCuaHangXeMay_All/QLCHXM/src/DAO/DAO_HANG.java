/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.POJO_HANG;
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
public class DAO_HANG {
    public ArrayList<POJO_HANG> LayDSHG(){
        ArrayList<POJO_HANG> ds=new ArrayList<POJO_HANG>();
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("QLCHXM");
            MongoCollection<Document> collection =database.getCollection("HangHoa");
            for (Document doc : collection.find()){
            try {
                JSONParser parser = new JSONParser();
                JSONObject json;
                POJO_HANG ch =  new POJO_HANG();
                json = (JSONObject) parser.parse(doc.toJson());
                String value = json.get("MaHG").toString();
                String value1 = json.get("TenHG").toString();
                ch.setMahg(value);
                ch.setTenhg(value1);
                ds.add(ch);          
            } catch (ParseException ex) {
                Logger.getLogger(DAO_HANG.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ds;
    }
    public ArrayList<String> LayDSHGcbb(){
        ArrayList<String> ds=new ArrayList<String>();
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("QLCHXM");
            MongoCollection<Document> collection =database.getCollection("HangHoa");
            for (Document doc : collection.find()){
            try {
                JSONParser parser = new JSONParser();
                JSONObject json;
                String ch;
                json = (JSONObject) parser.parse(doc.toJson());
                String value = json.get("MaHG").toString();
                ch=value;
                ds.add(ch);          
            } catch (ParseException ex) {
                Logger.getLogger(DAO_HANG.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ds;
    }
    public POJO_HANG inforeHang(String MaHG) throws ParseException
    {
        POJO_HANG ts= new POJO_HANG();
        Bson filter = eq("MaHG", MaHG);
        Bson project = and(eq("TenHG", 1L),eq("Mota", 1L),eq("Hinh", 1L),eq("SoLuong", 1L),eq("Gia", 1L),eq("_id", 0L));
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("HangHoa");
        FindIterable<Document> result = collection.find(filter).projection(project);

        JSONParser parser = new JSONParser();
        JSONObject json;
        for (Document document : result)
        {
           POJO_HANG ch =  new POJO_HANG();
           json = (JSONObject) parser.parse(document.toJson());
           String value = MaHG;
           String value1 = json.get("TenHG").toString();
           String value2 = json.get("Mota").toString();
           String value3 = json.get("Hinh").toString();
           String value4 = json.get("SoLuong").toString();
           String value5 = json.get("Gia").toString();
           ch.setMahg(value);
           ch.setTenhg(value1);
           ch.setMota(value2);
           ch.setSoluong(value4);
           ch.setHinh(value3);
           ch.setGia(value5);
           ts = ch;
        }
        return ts;
    }
    public String tHang(String MaHG)
    {
        Bson filter = eq("MaHG", MaHG);
        Bson project =eq("Gia", 1L);
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("HangHoa");
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
    public void addHANG(String ma,String ten,String mota,String hinh,String sl,String gia)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("HangHoa");
        BasicDBObject document = new BasicDBObject();
        document.put("MaHG", ma);
        document.put("TenHG", ten);
        document.put("Mota", mota);
        document.put("Hinh", hinh);
        document.put("SoLuong", sl);
        document.put("Gia", gia);
        collection.insert(document);
    }
    public void deleteH(String mahg)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("HangHoa");
        BasicDBObject document = new BasicDBObject();
        document.put("MaHG", mahg);
        collection.remove(document);
    }
    public void updateH(String ma,String ten,String mota,String hinh,String sl,String gia)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("HangHoa");
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("TenHG", ten).append("Mota",mota).append("Hinh",hinh).append("SoLuong",sl).append("Gia",gia));
        BasicDBObject searchQuery = new BasicDBObject().append("MaHG",ma);
        collection.update(searchQuery, newDocument);
    }
}
