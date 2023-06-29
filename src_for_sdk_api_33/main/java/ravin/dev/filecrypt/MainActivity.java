package ravin.dev.filecrypt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
    private static int pswdIterations = 100;
    private static final int keySize = 128;
    private static final String cypherInstance = "AES/CBC/PKCS5Padding";
    private static final String secretKeyInstance = "PBKDF2WithHmacSHA1";
    private static final int GALLERY_REQUEST_CODE = 21;
    private  String AESSalt = "";
    private  String initializationVector = "";
    private static String mark = File.separator;

    private static byte[] getRaw(String plainText, String salt) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(secretKeyInstance);
            KeySpec spec = new PBEKeySpec(plainText.toCharArray(), salt.getBytes(), pswdIterations, keySize);
            return factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }


    public int encryptaes(String text_key, String filename) throws Exception {
        SecretKeySpec sks = new SecretKeySpec(getRaw(text_key, AESSalt), "AES");
        Cipher cipher = null;
        cipher = Cipher.getInstance(cypherInstance);
        cipher.init(Cipher.ENCRYPT_MODE, sks, new IvParameterSpec(initializationVector.getBytes()));
        FileInputStream fis = new FileInputStream(filename);
        CipherInputStream cis = new CipherInputStream(fis, cipher);
        File source_files_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        String filename_only = "FileCrypt_"+filename.substring(filename.lastIndexOf(mark)+1);
        FileOutputStream fos = new FileOutputStream(new File(source_files_path,filename_only));
        byte[] b = new byte[1024];
        int i = cis.read(b);
        while (i != -1) {
            fos.write(b,0, i);
            i = cis.read(b);
        }
        fos.close();
        fis.close();
        cis.close();
        return 1;
    }

    public int decryptaes(String text_key, String filename) throws Exception {
        SecretKeySpec sks = new SecretKeySpec(getRaw(text_key, AESSalt), "AES");
        Cipher cipher = null;
        cipher = Cipher.getInstance(cypherInstance);
        cipher.init(Cipher.DECRYPT_MODE, sks, new IvParameterSpec(initializationVector.getBytes()));
        String filename_only = filename.substring(filename.lastIndexOf(mark)+1);
        String newfilename_only = filename_only.replace("FileCrypt_", "");
        File encrytpted_files_path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        FileOutputStream fos = new FileOutputStream(new File(encrytpted_files_path, newfilename_only));
        FileInputStream fis = new FileInputStream(filename);
        CipherOutputStream cos = new CipherOutputStream(fos,cipher);

        byte[] buf = new byte[1024];
        int read;
        while((read=fis.read(buf))!=-1){
            cos.write(buf,0,read);
        }
        cos.close();
        fos.close();
        fis.close();
        return 1;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button encrypt_button=(Button)findViewById(R.id.button_encrypt);
        Button decrypt_button=(Button)findViewById(R.id.button_decrypt);
        Button browse_button = (Button)findViewById(R.id.button_browse);
        Button about_us=(Button)findViewById(R.id.button_aboutus);
        encrypt_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText pswd = (EditText)findViewById(R.id.editeration);
                pswdIterations = Integer.parseInt(pswd.getText().toString());
                EditText edfile = (EditText)findViewById(R.id.edit_filename);
                String filename = edfile.getText().toString();
                String filename_only = filename.substring(filename.lastIndexOf("/")+1);
                if ((filename_only.startsWith("FileCrypt_")==false) && (!filename.equals("File Name"))) {
                    Toast.makeText(MainActivity.this, "Encryption started.", Toast.LENGTH_SHORT).show();
                    EditText edkey = (EditText) findViewById(R.id.text_key);
                    EditText edaessalt = (EditText) findViewById(R.id.text_aessalt);
                    EditText ediv = (EditText) findViewById(R.id.text_iv);
                    AESSalt = edaessalt.getText().toString();
                    initializationVector = ediv.getText().toString();
                    String text_key = edkey.getText().toString();
                    int res_code = 0;
                    try {
                        res_code = encryptaes(text_key, filename);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (res_code == 1) {
                        Toast.makeText(MainActivity.this, "Encryption complete, DOCUMENTS/FileCrypt_" + filename_only + " is created.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Error: Encryption supported only for: Image, Audio, and Video.", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Select correct file to encrypt !!!", Toast.LENGTH_LONG).show();
                }
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);

            }});

        decrypt_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText edfile = (EditText)findViewById(R.id.edit_filename);
                String filename = edfile.getText().toString();
                String filename_only = filename.substring(filename.lastIndexOf("/")+1);
                if (filename_only.startsWith("FileCrypt_")==true) {
                    EditText pswd = (EditText)findViewById(R.id.editeration);
                    pswdIterations = Integer.parseInt(pswd.getText().toString());
                    Toast.makeText(MainActivity.this, "Decryption started.", Toast.LENGTH_SHORT).show();
                    EditText edkey = (EditText) findViewById(R.id.text_key);
                    EditText edaessalt = (EditText) findViewById(R.id.text_aessalt);
                    EditText ediv = (EditText) findViewById(R.id.text_iv);
                    AESSalt = edaessalt.getText().toString();
                    initializationVector = ediv.getText().toString();

                    String text_key = edkey.getText().toString();
                    int res_code = -1;
                    try {
                        res_code=decryptaes(text_key, filename);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (res_code==1) {
                        filename_only = filename_only.replace("FileCrypt_", "");
                        Toast.makeText(MainActivity.this, "Decryption complete, DOCUMENTS/"+ filename_only + " is created.", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Error occurred during decryption.", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Select correct encrypted file !!!", Toast.LENGTH_LONG).show();
                }
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
            }
        });

        about_us.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
            }
        });

        browse_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("*/*");
                startActivityForResult(intent,GALLERY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode,int resultCode, Intent data){
        EditText edfile = (EditText) findViewById(R.id.edit_filename);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case GALLERY_REQUEST_CODE:
                    Uri selectedImage = data.getData();
                    RealPathUtil obj = new RealPathUtil();
                    String imgDecodableString = obj.getRealPath(this,selectedImage);
                    edfile.setText(imgDecodableString);
                    break;
            }
    }
}