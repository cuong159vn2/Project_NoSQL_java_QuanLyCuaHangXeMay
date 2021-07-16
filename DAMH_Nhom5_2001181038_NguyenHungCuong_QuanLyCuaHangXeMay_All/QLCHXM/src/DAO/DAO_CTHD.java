/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import POJO.POJO_CTHD;
import POJO.POJO_DV;
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
public class DAO_CTHD {
    public ArrayList<POJO_CTHD> LayDSHD(String ma){
        ArrayList<POJO_CTHD> ds=new ArrayList<POJO_CTHD>();
        Bson filter = eq("MaHD", ma);
        Bson project = and(eq("MaHG", 1L),eq("Gia", 1L));
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("CTHD");
        FindIterable<Document> result = collection.find(filter).projection(project);

        JSONParser parser = new JSONParser();
        JSONObject json;
        for (Document document : result)
        {
            try {
                POJO_CTHD ch =  new POJO_CTHD();
                json = (JSONObject) parser.parse(document.toJson());
                String value1 = json.get("MaHG").toString();
                String value2 = json.get("Gia").toString();
                ch.setMaHG(value1);
                ch.setGia(value2);
                ds.add(ch);
            } catch (ParseException ex) {
                Logger.getLogger(DAO_CTHD.class.getName()).log(Level.SEVERE, null, ex);
                POJO_CTHD ch =  new POJO_CTHD();
                ch.setMaHG(null);
                ch.setGia(null);
                ds.add(ch);
            }
        }
        return ds;
    }
    public int getgiam(String mahg)
    {
        Bson filter = eq("MaHG", mahg);
        Bson project = and(eq("Ngay", 1L),eq("Thang", 1L),eq("Nam", 1L),eq("Gia", 1L));
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("KhuyenMai");
        FindIterable<Document> result = collection.find(filter).projection(project);
        JSONParser parser = new JSONParser();
        JSONObject json;
        POJO_KhuyenMai km =  new POJO_KhuyenMai();
        for (Document document : result)
        {
            try {
                json = (JSONObject) parser.parse(document.toJson());
                String value1 = json.get("Ngay").toString();
                String value2 = json.get("Thang").toString();
                String value3 = json.get("Nam").toString();
                String value4 = json.get("Gia").toString();
                km.setNgay(value1);
                km.setThang(value2);
                km.setNam(value3);
                km.setGia(value4);
            } catch (ParseException ex) {
                Logger.getLogger(DAO_CTHD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int ngay, thang, nam;
        String z= java.time.LocalDate.now().toString();
        System.out.println(z);
        String a =z.substring(0,4);
        System.out.println(a);
        String b = z.substring(5,7);
        System.out.println(b);
        String c = z.substring(8,10);
        System.out.println(c);
        nam = Integer.valueOf(a);
        ngay= Integer.valueOf(b);
        thang= Integer.valueOf(c);
        System.out.println(ngay);
        System.out.println(thang);
        System.out.println(nam);
        if(Integer.valueOf(km.getNam())>=nam && Integer.valueOf(km.getThang())>=thang && Integer.valueOf(km.getNgay())>=ngay)
        {
            int gia = Integer.valueOf(km.getGia());
            System.out.println(gia);
            return gia;
        }
        else
        {
            return 0;
        }
}
    public int checkhanggiamgia(String mahg)
    {
        Bson filter = eq("MaHG", mahg);
        Bson project = and(eq("MaHG", 1L),eq("Gia", 1L));
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("KhuyenMai");
        FindIterable<Document> result = collection.find(filter).projection(project);
        int c = 0;
        for (Document document : result)
        {
            c++;
        }
        if(c>0)
        {
            System.err.println("Yes");
            return 1;
        }
        else
        {
            System.err.println("No");
            return 0;
        }    
    }
    
    public void addCTHD(String mahd,String mahg)
    {
        try {
            DAO_HANG a = new DAO_HANG();
            String dola = a.tHang(mahg);
            System.out.print(dola);
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("QLCHXM");
            DBCollection collection = db.getCollection("CTHD");
            BasicDBObject document = new BasicDBObject();
            document.put("MaHD", mahd);
            document.put("MaHG", mahg);
            document.put("Gia", dola);
            DAO_HOADON b = new DAO_HOADON();
            String dolaHD = b.tHD(mahd);
            int gg;
            int kq =checkhanggiamgia(mahg);
            if(kq==0)
                gg=0;
            else
            {
                gg=getgiam(mahg);
            }
            int hg = Integer.parseInt(dola);
            int hd = Integer.parseInt(dolaHD);
            int tong = hg + hd - gg;
            b.updateHD(mahd,String.valueOf(tong));
            collection.insert(document);
        } catch (ParseException ex) {
            Logger.getLogger(DAO_CTHD.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    public void deleteCTHD(String mahd,String mahg,String gt)
    {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("QLCHXM");
            DBCollection collection = db.getCollection("CTHD");
            BasicDBObject document = new BasicDBObject();
            document.put("MaHD", mahd);
            document.put("MaHG", mahg);
            collection.remove(document);
            DAO_HOADON b = new DAO_HOADON();
            String dolaHD = b.tHD(mahd);
            int hg = Integer.parseInt(gt);
            int hd = Integer.parseInt(dolaHD);
            int gg;
            int kq =checkhanggiamgia(mahg);
            if(kq==0)
                gg=0;
            else
            {
                gg=getgiam(mahg);
            }
            int hieu = hd - hg+gg;
            b.updateHD(mahd,String.valueOf(hieu));
        } catch (ParseException ex) {
            Logger.getLogger(DAO_CTHD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addCTHD2(String mahd,String mahg)
    {
        try {
            DAO_DV a = new DAO_DV();
            String dola = a.tDV(mahg);
            System.out.print(dola);
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            DB db = mongoClient.getDB("QLCHXM");
            DBCollection collection = db.getCollection("CTHD");
            BasicDBObject document = new BasicDBObject();
            document.put("MaHD", mahd);
            document.put("MaHG", mahg);
            document.put("Gia", dola);
            DAO_HOADON b = new DAO_HOADON();
            String dolaHD = b.tHD(mahd);
            int hg = Integer.parseInt(dola);
            int hd = Integer.parseInt(dolaHD);
            int tong = hg + hd;
            b.updateHD(mahd,String.valueOf(tong));
            collection.insert(document);
        } catch (ParseException ex) {
            Logger.getLogger(DAO_CTHD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
