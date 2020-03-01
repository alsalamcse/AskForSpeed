package moaad.klaib.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.FirebaseDatabase;

public class signin extends AppCompatActivity {
    private EditText emSing,psSign;
    private Button signUp1,login;
    private CheckBox checkfather,checkson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signin );

        emSing = findViewById( R.id.emSign );
        psSign = findViewById( R.id.psSign );
        login = findViewById( R.id.login );
         signUp1= findViewById( R.id.signUp1 );



        signUp1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent( getApplicationContext(),signup.class);
                startActivity( intent );
            }


        });
        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataHandler();
            }
        } );
    }
    private void dataHandler() {
        String email=emSing.getText().toString();
        String password=psSign.getText().toString();
        boolean isok=true;

        if(!isValidEmailAddress( email )) {
            emSing.setError( "email" );
            isok=false;
        }
        if (password.length() < 8) {
            psSign.setError( "password length eror" );
            isok=false;
        }
        if(isok)
            signIn( email, password );

    }
    private void signIn(String email,String pass)
    {
        FirebaseAuth auth= FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            //todo go to ,main screen(all Task activity)
//                            Intent i=new Intent( getApplicationContext(),TempAllTaskActivity.class );
//                            startActivity( i );
                        }
                        else
                        {
                            emSing.setError( "email or password is wrong"+task.getException().getMessage());
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


