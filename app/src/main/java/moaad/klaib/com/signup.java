package moaad.klaib.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

        String email=Userem.getText().toString();
        String phone1=phone.getText().toString();
        String save=save1.getText().toString();
        String password=pass.getText().toString();
        if(Userem.length()<4 || email.indexOf('@')<0 || email.indexOf('.')<0)
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
            createAcount(password,email,phone1);

        }

    }

    private void createAcount(String email, String passw1, String fname1) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        Task<AuthResult> authResultTask = auth.createUserWithEmailAndPassword( email, passw1 ).addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText( signup.this, "sign up Successful", Toast.LENGTH_SHORT ).show();
                    finish();
                } else {
                    Userem.setError( "sign up failed" );
                }

            }
        } );
    }
}
