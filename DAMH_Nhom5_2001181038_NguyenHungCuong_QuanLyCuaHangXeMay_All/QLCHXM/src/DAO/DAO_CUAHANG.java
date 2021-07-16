/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Form.CuaHang;
import POJO.POJO_CUAHANG;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
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
import java.util.regex.Pattern;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ngock
 */
public class DAO_CUAHANG {
    public ArrayList<POJO_CUAHANG> LayDS(){
        ArrayList<POJO_CUAHANG> ds=new ArrayList<POJO_CUAHANG>();
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("QLCHXM");
            MongoCollection<Document> collection =database.getCollection("CuaHang");
            for (Document doc : collection.find()){
        JSONParser parser = new JSONParser();
        JSONObject json;
        POJO_CUAHANG ch =  new POJO_CUAHANG();
            try {
                
                json = (JSONObject) parser.parse(doc.toJson());
                String value = json.get("MaCH").toString();
                String value1 = json.get("TenCH").toString();
                String value2 = json.get("DiaChi").toString();
                ch.setMach(value);
                ch.setTench(value1);
                if(value2!=null)
                {
                       ch.setDiaChi(value2);
                }
                else
                {
                    value2="null";
                }
                ds.add(ch);          
            } catch (ParseException ex) {
                Logger.getLogger(CuaHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ds;
    }
    
    public void addCUAHANG(String ma,String ten,String diachich)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("CuaHang");
        BasicDBObject document = new BasicDBObject();
        document.put("MaCH", ma);
        document.put("TenCH", ten);
        BasicDBObject documentDetail = new BasicDBObject();
        documentDetail.put("dc", diachich);
        document.put("DiaChi", documentDetail);
        collection.insert(document);
    }
    public void deleteCH(String mach)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("CuaHang");
        BasicDBObject document = new BasicDBObject();
        document.put("MaCH", mach);
        collection.remove(document);
    }
    public void updateCH(String ma,String ten,String diachi)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("CuaHang");
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("TenCH", ten).append("DiaChi.dc", diachi));
        BasicDBObject searchQuery = new BasicDBObject().append("MaCH",ma);
        collection.update(searchQuery, newDocument);
    }
    public POJO_CUAHANG inforeCuaHang2(String MaCH) throws ParseException
    {
        POJO_CUAHANG ts= new POJO_CUAHANG();
        Bson filter = eq("MaCH", MaCH);
        Bson project = and(eq("MaCH", 1L),eq("TenCH", 1L),eq("DiaChi.dc", 1L),eq("_id", 0L));
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("CuaHang");
        FindIterable<Document> result = collection.find(filter).projection(project);

        JSONParser parser = new JSONParser();
        JSONObject json;
        for (Document document : result)
        {
           POJO_CUAHANG ch =  new POJO_CUAHANG();
           json = (JSONObject) parser.parse(document.toJson());
           String value = json.get("MaCH").toString();
           String value1 = json.get("TenCH").toString();
           String value2 = json.get("DiaChi").toString();
           String chuoi = xlc(value2);
           ch.setMach(value);
           ch.setTench(value1);
           ch.setDiaChi(chuoi);
           ts = ch;
        }
        return ts;
    }
    public ArrayList<POJO_CUAHANG> FindCH(String str) throws ParseException
    {
        ArrayList<POJO_CUAHANG> ds=new ArrayList<POJO_CUAHANG>();
        Bson filter = eq("DiaChi.dc", Pattern.compile(""+str+""));
        Bson project = and(eq("MaCH", 1L),eq("TenCH", 1L),eq("DiaChi.dc", 1L),eq("_id", 0L));
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("CuaHang");
        FindIterable<Document> result = collection.find(filter).projection(project);

        JSONParser parser = new JSONParser();
        JSONObject json;
        for (Document document : result)
        {
           POJO_CUAHANG ch =  new POJO_CUAHANG();
           json = (JSONObject) parser.parse(document.toJson());
           String value = json.get("MaCH").toString();
           String value1 = json.get("TenCH").toString();
           String value2 = json.get("DiaChi").toString();
           String chuoi = xlc(value2);
           ch.setMach(value);
           ch.setTench(value1);
           ch.setDiaChi(chuoi);
           ds.add(ch);
        }
        return ds;
    }
    
    public String xlc(String str)
    {
        String a =str.substring(7);
        int sl = a.length()-2;
        String b = a.substring(0,sl);
        return b;
    }
}
