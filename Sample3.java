import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class Sample3 {

    public String accessKeyId="AERWERWRSDFASDFER3SFP";
    public String secretAccessKey="asdffkl4rfdvgf+3xcfa23sdfdsfDASFNSFSDDF";

    public boolean getS3BucketExists() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();
        return s3client.doesBucketExist("mytestBucket");
    }
    public void restoreS3Pbject() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();
        String key="testfile";
        String bucketName="mytestbucket";
        s3client.restoreObject( bucketName,  key, 20);
    }
}
