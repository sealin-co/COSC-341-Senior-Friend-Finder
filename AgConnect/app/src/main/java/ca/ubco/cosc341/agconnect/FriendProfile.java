package ca.ubco.cosc341.agconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

public class FriendProfile extends AppCompatActivity {
    User user;
    private String name;
    private String currentData = new Date().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
        AppGlobals.saveUserData(this);

        user = (User) getIntent().getSerializableExtra("user");

        name = getIntent().getStringExtra("name");
        //name = this.getString(R.string.name_harold);
        TextView nameText = findViewById(R.id.profile_name2);
        nameText.setText(name);
        setProfileImage(name);
        setBio(name);


    }

    private void setProfileImage(String name){
        ImageView profileImage = findViewById(R.id.profile_image3);
        if (name.equals(this.getResources().getString(R.string.name_AgConnectHelp))){
            profileImage.setImageDrawable(getDrawable(R.drawable.ic_user));
        }
        if (name.equals(this.getResources().getString(R.string.name_harold))){
            profileImage.setImageDrawable(getDrawable(R.drawable.profile_harold));
        }
    }

    private void setBio(String name){
        TextView objective = findViewById(R.id.objective);
        String[] objectiveArray = this.getResources().getStringArray(R.array.objective_array);
        String format;

        TextView age = findViewById(R.id.age);
        TextView pronoun = findViewById(R.id.pronoun);

        TextView interests = findViewById(R.id.interests);
        String[] interestsArray = this.getResources().getStringArray(R.array.interest_array);
        TextView more = findViewById(R.id.more);
        String moreString;

        if (name.equals(this.getString(R.string.name_AgConnectHelp))){
            format = this.getString(R.string.user_objectives, objectiveArray[0]);
            objective.setText(format);

            age.setText(this.getString(R.string.age_AgConnectHelp));
            pronoun.setText(this.getString(R.string.pro_they));

            format = this.getString(R.string.user_interests, interestsArray[4]);
            interests.setText(format);

            moreString = this.getString(R.string.more_AgConnectHelp);
            format = this.getString(R.string.user_bio, moreString);
            more.setText(format);

            Button unfriend = findViewById(R.id.unfriend);
            unfriend.setVisibility(View.INVISIBLE);
        }
        if(AppGlobals.friendsWithHarold){
            if (name.equals(this.getResources().getString(R.string.name_harold))) {
                format = this.getString(R.string.user_objectives, objectiveArray[1]);
                objective.setText(format);

                age.setText(this.getString(R.string.age_harold));
                pronoun.setText(this.getString(R.string.pro_he));

                format = this.getString(R.string.user_interests, interestsArray[2]);
                interests.setText(format);
                moreString = this.getString(R.string.more_harold);
                format = this.getString(R.string.user_bio, moreString);
                more.setText(format);
            }

            TextView date = findViewById(R.id.friend_date);
            format = this.getString(R.string.request_accepted, currentData);
            date.setText(format);
        }

    }

    public void onChatClick(View v){
        Intent i = new Intent(FriendProfile.this, Chat.class);
        i.putExtra("name", name);
        startActivity(i);
    }

    public void onBackClick(View v){
        finish();
    }

    public void toEvents(View view){
        Intent intent = new Intent(FriendProfile.this, Events.class);
        startActivity(intent);
    }

    public void onUnfriendClick(View v){
        Intent intent = new Intent(FriendProfile.this, Dialog_DeleteWarning.class);
        intent.putExtra("origin", "unfriend");
        intent.putExtra("title","Unfriend");
        intent.putExtra("message", "Are you sure you want to unfriend " + name +"?");
        intent.putExtra("friendName", name);
        startActivity(intent);
    }

    public void toMyConnections(View view){
        Intent intent = new Intent(FriendProfile.this, MyConnections_MyFriends.class);
        startActivity(intent);
    }

    public void toConnect(View view){
        Intent intent = new Intent(FriendProfile.this, Connect.class);
        startActivity(intent);
    }

    public void toSettings(View view){
        Intent intent = new Intent(FriendProfile.this, SettingsMenu.class);
        startActivity(intent);
    }
}
