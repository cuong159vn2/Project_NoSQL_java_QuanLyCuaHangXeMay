/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Form.DangNhap;
import com.mongodb.client.MongoDatabase; 
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential; 
/**
 *
 * @author ngock
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DangNhap dangnhap = new DangNhap();
        dangnhap.show();
    }
    
}
