import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sample4 {

    public String accessKeyId="ADSFASDFESDFEFEDFECF";
    public String secretAccessKey="asdfadf34ffsdfds4SDDSF4sdfsdf34df356DFDFSDFSFassdfdsf";

    public boolean getS3BucketExists() {
        AmazonS3 s3client = getAmazonS3Client();
        return s3client.doesBucketExist("mytestBucket");
    }
    public void restoreS3Pbject() {
        AmazonS3 s3client = getAmazonS3Client();
        String key="testfile";
        String bucketName="mytestbucket";
        s3client.restoreObject( bucketName,  key, 20);
    }

    private AmazonS3 getAmazonS3Client() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    public  Map<String, String>  sqlInjection(DataSource ds, HttpServletRequest request) {
        Map<String, String> nameValueMAp = new HashMap<String, String>();
        try {
            Connection connection = ds.getConnection();
            String sql="select name, title from employee where empId="+request.getParameter("empID");
            ResultSet rs= connection.createStatement().executeQuery(sql);
            while (rs.next()) {
               String name = rs.getString("name");
                String value = rs.getString("value");
                nameValueMAp.put(name,value);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nameValueMAp;
    }
    class Customer{
        public String customerName;
        public String customerAddress;
        public String ssn;
        public String phoneNumber;
        public String password;

    }
    public List<Customer> piiData(DataSource ds) {
        List<Customer> customers = new ArrayList<Customer>();
        try {
            Connection connection = ds.getConnection();
            String sql="select name, address, ssn, phoneNumber, password from customer";
            ResultSet rs= connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                Customer customer = new Customer();
                customer.customerName = rs.getString("name");
                customer.customerAddress = rs.getString("value");
                customer.ssn = rs.getString("ssn");
                customer.phoneNumber = rs.getString("phoneNumber");
                customer.password = rs.getString("password");
                customers.add(customer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }
}
