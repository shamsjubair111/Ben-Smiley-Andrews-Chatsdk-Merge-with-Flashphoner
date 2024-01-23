package sdk.chat.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ContactListViewAdapter extends ArrayAdapter<Contact> {

    Context context;
    ArrayList<Contact> list;



    public ContactListViewAdapter(Context context, ArrayList<Contact> items){
        super(context, R.layout.user_row_test,items);
        this.context = context;
        list = items;
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Log.e("GetView", "GetView Called");
        if(convertView == null) {


            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.user_row_test, null);
        }


        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        nameTextView.setText(list.get(position).getContactName());

        ImageButton videoCall = convertView.findViewById(R.id.videoCall);

        ImageButton audioCall = convertView.findViewById(R.id.audioCall);

        ImageButton sipCallButton = convertView.findViewById(R.id.sipCallButton);

        videoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),VideoActivity.class);
                intent.putExtra("type","video");
                getContext().startActivity(intent);

            }
        });


        audioCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),VideoActivity.class);
                intent.putExtra("type","audio");
                getContext().startActivity(intent);

            }
        });


        sipCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),AudioActivity.class);
                intent.putExtra("callee", validPhoneNumber(list.get(position).getContactNumber()));

                getContext().startActivity(intent);
            }
        });



        return convertView;

    }

    public static String validPhoneNumber(String mobileNumber) {
        mobileNumber = mobileNumber.replaceAll("[\\s-]+", "");
        mobileNumber = mobileNumber.substring(mobileNumber.length() - 11);
        mobileNumber = "88" + mobileNumber;

        return mobileNumber;
    }
}
