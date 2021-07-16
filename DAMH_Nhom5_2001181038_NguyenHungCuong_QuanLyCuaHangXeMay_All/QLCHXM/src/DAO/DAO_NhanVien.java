/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import POJO.POJO_NHANVIEN;
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
public class DAO_NhanVien {
    public ArrayList<POJO_NHANVIEN> LayDSNV(){
        ArrayList<POJO_NHANVIEN> ds=new ArrayList<POJO_NHANVIEN>();
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("QLCHXM");
            MongoCollection<Document> collection =database.getCollection("NhanVien");
            for (Document doc : collection.find()){
            try {
                JSONParser parser = new JSONParser();
                JSONObject json;
                POJO_NHANVIEN ch =  new POJO_NHANVIEN();
                
                json = (JSONObject) parser.parse(doc.toJson());
                String value = json.get("MaNV").toString();
                String value1 = json.get("MaCH").toString();
                ch.setManv(value);
                ch.setMach(value1);
                ds.add(ch);          
            } catch (ParseException ex) {
                Logger.getLogger(DAO_NhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ds;
    }
    public String LayMK(String ma) throws ParseException
    {
        Bson filter = eq("MaNV", ma);
        Bson project = eq("ThongTin.MatKhau", 1L);
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("NhanVien");
        FindIterable<Document> result = collection.find(filter).projection(project);
        JSONParser parser = new JSONParser();
        JSONObject json;
        String mk = null;
        for (Document document : result)
        {
           json = (JSONObject) parser.parse(document.toJson());
           mk = json.get("ThongTin").toString();
           String ss = xlc(mk);
           return ss;
        }
        return mk;
    }
    public String LayTenNV(String ma) throws ParseException
    {
        Bson filter = eq("MaNV", ma);
        Bson project = eq("ThongTin.TenNV", 1L);
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("NhanVien");
        FindIterable<Document> result = collection.find(filter).projection(project);
        JSONParser parser = new JSONParser();
        JSONObject json;
        String cv = null;
        for (Document document : result)
        {
           json = (JSONObject) parser.parse(document.toJson());
           String vl = json.get("ThongTin").toString();
           cv = xlc3(vl);
           return cv;
        }
        return cv;
    }
    public String LayMT() throws ParseException
    {
        Bson filter = new Document();
        Bson project = eq("MaNV", 1L);
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("MaNVDN");
        FindIterable<Document> result = collection.find(filter).projection(project);
        JSONParser parser = new JSONParser();
        JSONObject json;
       
        for (Document document : result)
        {
           json = (JSONObject) parser.parse(document.toJson());
           String vl = json.get("MaNV").toString();
           return vl;
        }
        return "Lỗi";
    }
    public int LayCV(String ma) throws ParseException
    {
        Bson filter = eq("MaNV", ma);
        Bson project = eq("ThongTin.ChucVu", 1L);
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("NhanVien");
        FindIterable<Document> result = collection.find(filter).projection(project);
        JSONParser parser = new JSONParser();
        JSONObject json;
        String cv = null;
        for (Document document : result)
        {
           json = (JSONObject) parser.parse(document.toJson());
           cv = json.get("ThongTin").toString();
           String a =cv.substring(11);
           int sl = a.length()-2;
           String b = a.substring(0,sl);
           if(b.compareTo("Boss")==0)
               return 1;
        }
        return 0;
    }
    public String LayCV1(String ma) throws ParseException
    {
        Bson filter = eq("MaNV", ma);
        Bson project = eq("ThongTin.ChucVu", 1L);
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("NhanVien");
        FindIterable<Document> result = collection.find(filter).projection(project);
        JSONParser parser = new JSONParser();
        JSONObject json;
        String cv = null;
        for (Document document : result)
        {
           json = (JSONObject) parser.parse(document.toJson());
           cv = json.get("ThongTin").toString();
           String a =cv.substring(11);
           int sl = a.length()-2;
           String b = a.substring(0,sl);
            cv = b;
           return b; 
        }
        return cv;
    }
     public String LayLuong(String ma) throws ParseException
    {
        Bson filter = eq("MaNV", ma);
        Bson project = eq("ThongTin.Luong", 1L);
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("NhanVien");
        FindIterable<Document> result = collection.find(filter).projection(project);
        JSONParser parser = new JSONParser();
        JSONObject json;
        String mk = null;
        for (Document document : result)
        {
           json = (JSONObject) parser.parse(document.toJson());
           mk = json.get("ThongTin").toString();
           String a =mk.substring(10);
           int sl = a.length()-2;
           String b = a.substring(0,sl);
           mk = b;
           return b;
        }
        return mk;
    }
     public String LayTK(String ma) throws ParseException
    {
        Bson filter = eq("MaNV", ma);
        Bson project = eq("ThongTin.TaiKhoan", 1L);
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("NhanVien");
        FindIterable<Document> result = collection.find(filter).projection(project);
        JSONParser parser = new JSONParser();
        JSONObject json;
        String mk = null;
        for (Document document : result)
        {
           json = (JSONObject) parser.parse(document.toJson());
           mk = json.get("ThongTin").toString();
           String a =mk.substring(13);
           int sl = a.length()-2;
           String b = a.substring(0,sl);
           mk = b;
           return b;
        }
        return mk;
    }
     public String LayDC(String ma) throws ParseException
    {
        Bson filter = eq("MaNV", ma);
        Bson project = eq("ThongTin.DiachiNV", 1L);
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("NhanVien");
        FindIterable<Document> result = collection.find(filter).projection(project);
        JSONParser parser = new JSONParser();
        JSONObject json;
        String mk = null;
        for (Document document : result)
        {
           json = (JSONObject) parser.parse(document.toJson());
           mk = json.get("ThongTin").toString();
           String a =mk.substring(13);
           int sl = a.length()-2;
           String b = a.substring(0,sl);
           mk = b;
           return b;
        }
        return mk;
    }
     public String LayGT(String ma) throws ParseException
    {
        Bson filter = eq("MaNV", ma);
        Bson project = eq("ThongTin.GioiTinh", 1L);
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("NhanVien");
        FindIterable<Document> result = collection.find(filter).projection(project);
        JSONParser parser = new JSONParser();
        JSONObject json;
        String mk = null;
        for (Document document : result)
        {
           json = (JSONObject) parser.parse(document.toJson());
           mk = json.get("ThongTin").toString();
           String a =mk.substring(13);
           int sl = a.length()-2;
           String b = a.substring(0,sl);
           mk = b;
           return b;
        }
        return mk;
    }
    public void isMT(String ma)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("MaNVDN");
        BasicDBObject document = new BasicDBObject();
        document.put("MaNV", ma);
        collection.insert(document);
    }
    public void dltMT()
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("MaNVDN");
        BasicDBObject document = new BasicDBObject();
        collection.remove(document);
    }
    public void addNhanVien(String manv,String mach,String taikhoan, String matkhau, String tennhanvien, String diachi, String gioitinh, String chucvu, String luong)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("NhanVien");
        BasicDBObject document = new BasicDBObject();
        document.put("MaNV", manv);
        document.put("MaCH", mach);
        BasicDBObject documentDetail = new BasicDBObject();
        documentDetail.put("TaiKhoan", taikhoan);
        documentDetail.put("MatKhau", matkhau);
        documentDetail.put("TenNV", tennhanvien);
        documentDetail.put("DiachiNV", diachi);
        documentDetail.put("GioiTinh", gioitinh);
        documentDetail.put("ChucVu", chucvu);
        documentDetail.put("Luong", luong);
        document.put("ThongTin", documentDetail);
        collection.insert(document);
    }
    public void deleteNV(String manv)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("NhanVien");
        BasicDBObject document = new BasicDBObject();
        document.put("MaNV", manv);
        collection.remove(document);
    }
    public void updateNV(String manv,String mach,String taikhoan, String matkhau, String tennhanvien, String diachi, String gioitinh, String chucvu, String luong)
    {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("QLCHXM");
        DBCollection collection = db.getCollection("NhanVien");
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("MaCH", mach).append("ThongTin.TaiKhaoan", taikhoan).append("ThongTin.MatKhau", matkhau).append("ThongTin.TenNV", tennhanvien).append("ThongTin.DiaChiNV", diachi).append("ThongTin.GioiTinh", gioitinh).append("ThongTin.ChucVu", chucvu).append("ThongTin.Luong", luong));
        BasicDBObject searchQuery = new BasicDBObject().append("MaNV",manv);
        collection.update(searchQuery, newDocument);
    }
    public POJO_NHANVIEN inforeNhanVien(String MaCH) throws ParseException
    {
        POJO_NHANVIEN ts= new POJO_NHANVIEN();
        Bson filter = eq("MaNV", MaCH);
        Bson project = and(eq("MaCH", 1L),eq("MaNV", 1L),eq("_id", 0L));
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
        MongoCollection<Document> collection = database.getCollection("NhanVien");
        FindIterable<Document> result = collection.find(filter).projection(project);

        JSONParser parser = new JSONParser();
        JSONObject json;
        for (Document document : result)
        {
           POJO_NHANVIEN ch =  new POJO_NHANVIEN();
           json = (JSONObject) parser.parse(document.toJson());
           String mch = json.get("MaCH").toString();
           String mnv = json.get("MaNV").toString();
           String tttk = LayTK(mnv);
           String ttmk = LayMK(mnv);
           String tttnv = LayTenNV(mnv);
           String ttdcnv = LayDC(mnv);
           String ttgt = LayGT(mnv);
           String cv = LayCV1(mnv);
           String l=LayLuong(mnv);
           ch.setMach(mch);
           ch.setManv(mnv);
           ch.setTaikhoan(tttk);
           ch.setMatkhau(ttmk);
           ch.setTennv(tttnv);
           ch.setDiachinv(ttdcnv);
           ch.setGioitinh(ttgt);
           ch.setChucvu(cv);
           ch.setLuong(l);
           ts = ch;
        }
        return ts;
    }
//    public ArrayList<POJO_NHANVIEN> FindNV(String ma) throws ParseException
//    {
//        ArrayList<POJO_NHANVIEN> ds=new ArrayList<POJO_NHANVIEN>();
//        Bson filter = eq("MaNV", ma);
//        Bson project = eq("ThongTin.TaiKhoan", 1L);eq("ThongTin.MatKhau", 1L);
//        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false"));
//        MongoDatabase database = mongoClient.getDatabase("QLCHXM");
//        MongoCollection<Document> collection = database.getCollection("NhanVien");
//        FindIterable<Document> result = collection.find(filter).projection(project);
//
//        JSONParser parser = new JSONParser();
//        JSONObject json;
//        for (Document document : result)
//        {
//           POJO_NHANVIEN ch =  new POJO_NHANVIEN();
//           json = (JSONObject) parser.parse(document.toJson());
//           String value = json.get("ThongTin.TaiKhoan").toString();
//           String value1 = json.get("TenCH").toString();
//           String value2 = json.get("DiaChi").toString();
//           String chuoi = xlc(value2);
//           ch.setMach(value);
//           ch.setTench(value1);
//           ch.setDiaChi(chuoi);
//           ds.add(ch);
//        }
//        return ds;
//    }
    public String xlc(String str)
    {
        String a =str.substring(12);
        int sl = a.length()-2;
        String b = a.substring(0,sl);
        return b;
    }
    public String xlc2(String str)
    {
        String a =str.substring(2);
        int sl = a.length()-2;
        String b = a.substring(0,sl);
        return b;
    }
    public String xlc3(String str)
    {
        String a =str.substring(10);
        int sl = a.length()-2;
        String b = a.substring(0,sl);
        return b;
    }
    public String xlc4(String str)
    {
        String a =str.substring(2);
        int sl = a.length()-2;
        String b = a.substring(0,sl);
        return b;
    }
}
