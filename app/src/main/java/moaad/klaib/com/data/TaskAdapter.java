package moaad.klaib.com.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import moaad.klaib.com.R;

public class TaskAdapter extends ArrayAdapter<MyChild>
{


    public TaskAdapter(@NonNull Context context) {
        super( context, R.layout.childitem );
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View vitem = LayoutInflater.from( getContext() ).inflate( R.layout.taskitem, parent, false );
        TextView tvTitle = vitem.findViewById( R.id.itmTvTitle );
        TextView tvSubject = vitem.findViewById( R.id.itmTvSubject );
        RatingBar rbPrio = vitem.findViewById( R.id.itmRatingPrio );
        CheckBox cbIsCompleted = vitem.findViewById( R.id.itemChbxIsCompleted );
        ImageView itmImgInfo = vitem.findViewById( R.id.itmImgInfo );

        final MyChild mychild = getItem( position );//getting data source
}
