/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.POJO_KHACHHANG;
import POJO.POJO_KhuyenMai;
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
public class DAO_KM {
    public ArrayList<POJO_KhuyenMai> LayDSKM(){
        ArrayList<POJO_KhuyenMai> ds=new ArrayList<POJO_KhuyenMai>();
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("QLCHXM");
            MongoCollection<Document> collection =database.getCollection("KhuyenMai");
            for (Document doc : collection.find()){
            try {
                JSONParser parser = new JSONParser();
                JSONObject json;
                POJO_KhuyenMai ch =  new POJO_KhuyenMai();
                json = (JSONObject) parser.parse(doc.toJson());
                String value = json.get("MaKM").toString();
                String value1 = json.get("MaHG").toString();
                ch.setMakm(value);
                ch.setMahg(value1);
                ds.add(ch);          
            } catch (ParseException ex) {
                Logger.getLogger(DAO_KM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ds;
    }
    public POJO_KhuyenMai inforeKM(String MaKM) throws ParseException
    {
        POJO_KhuyenMai ts= new POJO_KhuyenMai();
        Bson filter = eq("MaKM", MaKM);
        Bson project = and(eq("MaHG", 1L),eq("Gia", 1L),eq("Ngay", 1L),eq("Thang", 1L),eq("Nam", 1L));
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("KhuyenMai");
        FindIterable<Document> result = collection.find(filter).projection(project);

        JSONParser parser = new JSONParser();
        JSONObject json;
        for (Document document : result)
        {
           POJO_KhuyenMai ch =  new POJO_KhuyenMai();
           json = (JSONObject) parser.parse(document.toJson());
           String value = MaKM;
           String value1 = json.get("MaHG").toString();
           String value2 = json.get("Gia").toString();
           String value3 = json.get("Ngay").toString();
           String value4 = json.get("Thang").toString();
           String value5 = json.get("Nam").toString();
           ch.setMakm(value);
           ch.setMahg(value1);
           ch.setGia(value2);
           ch.setNgay(value3);
           ch.setThang(value4);
           ch.setNam(value5);
           ts = ch;
        }
        return ts;
    }
    public void addKM(String makm,String mahg,String gia,String ngay,String thang,String nam)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("KhuyenMai");
        BasicDBObject document = new BasicDBObject();
        document.put("MaKM", makm);
        document.put("MaHG", mahg);
        document.put("Gia", gia);
        document.put("Ngay", ngay);
        document.put("Thang", thang);
        document.put("Nam", nam);
        collection.insert(document);
    }
    public void deleteKM(String makm)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("KhuyenMai");
        BasicDBObject document = new BasicDBObject();
        document.put("MaKM", makm);
        collection.remove(document);
    }
    public void updateKM(String makm,String mahg,String gia,String ngay,String thang,String nam)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("KhuyenMai");
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("MaHG",mahg).append("Gia",gia).append("Ngay",ngay).append("Thang",thang).append("Nam",nam));
        BasicDBObject searchQuery = new BasicDBObject().append("MaKM",makm);
        collection.update(searchQuery, newDocument);
    }
}
