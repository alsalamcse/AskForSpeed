package moaad.klaib.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class signin extends AppCompatActivity {
    private EditText emsign,pssign;
    private Button signIn1,signUp1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signin );

        emsign = findViewById( R.id.emSign );
        pssign = findViewById( R.id.psSign );
        signIn1 = findViewById( R.id.signIn1 );
        signUp1 = findViewById( R.id.signUp1 );



        signIn1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( getApplicationContext(),signup.class);
                startActivity( intent );
            }


        });
        signIn1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataHandler();
            }
        } );
    }
    private void dataHandler() {
        String email=emsign.getText().toString();
        String password=pssign.getText().toString();
        boolean isok=true;

        if(!isValidEmailAddress( email )) {
            emsign.setError( "email" );
            isok=false;
        }
        if (password.length() < 8) {
            pssign.setError( "password length eror" );
            isok=false;
        }
        if(isok)
            signIn( email, password );

    }
    private void signIn(String email,String pass)
    {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            //todo go to ,main screen(all Task activity)
                            Intent i=new Intent( getApplicationContext(),TempAllTaskActivity.class );
                            startActivity( i );
                        }
                        else
                        {
                            emsign.setError( "email or password is wrong"+task.getException().getMessage());
                            task.getException().printStackTrace();
                        }
                    }
                }
        );
    }



    public boolean isValidEmailAddress (String email)
    {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();

    }
}
