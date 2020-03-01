package moaad.klaib.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import moaad.klaib.com.data.MyChild;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addChild extends AppCompatActivity {
    private EditText fatherphone,childphone,fathername;
    private Button save2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_child );
       fatherphone=findViewById( R.id.fatherphone);
        childphone=findViewById( R.id.childphone );
        fathername=findViewById( R.id.fatherphone );
        save2=findViewById( R.id.save2 );

        save2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                dataHandler();
            }

            private void dataHandler() {
                boolean isok = true;
                String Title = fatherphone.getText().toString();
                String Subject = childphone.getText().toString();
                String fathernamest = fathername.getText().toString();
                if (Title.length() == 0) {
                    fatherphone.setError( "Enter Title" );
                    isok = false;
                }
                if (Subject.length() == 0) {
                    childphone.setError( "Enter Subject" );
                    isok = false;
                }
                if (isok) {

                    MyChild t=new MyChild();
                    t.setFatherName( Title );
                    t.setFatherPhone( Subject );
                    t.setChildPhone(fathernamest );
                    createtask( t );
                }
            }
        });

    }
    private void createtask(MyChild t) {
        //.1
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //.2
        DatabaseReference reference = database.getReference();
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();
        t.setChildPhone(uid);
        String key = reference.child( "tasks" ).push().getKey();
        t.setFatherName(key);
        reference.child( "tasks" ).child(uid).child( key ).setValue( t ).addOnCompleteListener( addChild.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull

                                           com.google.android.gms.tasks.Task task) {
                if (task.isSuccessful()) {

                    Toast.makeText( addChild.this, "Add Successful", Toast.LENGTH_SHORT ).show();
                    finish();
                } else {


                    Toast.makeText( addChild.this, "Add Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT ).show();
                    task.getException().printStackTrace();
                }

            }


        } );

///
    }

}
