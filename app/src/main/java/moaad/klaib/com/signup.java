package moaad.klaib.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class signup extends AppCompatActivity {

    private EditText Userem,phone,pass;
    private Button save1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup );

        Userem=findViewById( R.id.Userem);
        pass=findViewById( R.id.pass );
        phone=findViewById( R.id.phone );

    }

    public void dattaHandler()


    {
        boolean isok=true;//if all the fields filled well
        String Userem=Userem.getText().toString();
        String pass=pass.getText().toString();
        int phone=phone.getText().toString();
        String btnsave1=save1.getText().toString();
        if(Userem.length()<4 || Userem.indexOf('@')<0 || Userem.indexOf('.')<0)
        {
            Userem.setError( "Wrong Email" );
            isok = false;
        }
        if (pass.length()<8 ||  pass.equals(pass )==false){
            pass.setError( "have to at least 8 char and the same password" );
            pass.setError( "have to be at least 8 char and the same password" );
            isok=false;
        }
        if (phone.length()==0)
        {
            phone.setError( "enter name" );
            isok=false;

        }
        if (isok)
        {
            createAcount(pass,Userem,phone);

        }

    }

    private void createAcount(String email, String passw1, String fname1, String lname1, EditText phone) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        Task<AuthResult> authResultTask = auth.createUserWithEmailAndPassword( email, passw1 ).addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText( signup.this, "sign up Successful", Toast.LENGTH_SHORT ).show();
                    finish();
                } else {
                    em2.setError( "sign up failed" );
                }

            }
        } );
    }
}
