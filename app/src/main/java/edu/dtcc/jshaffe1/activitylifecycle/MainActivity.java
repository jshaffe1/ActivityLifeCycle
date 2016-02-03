package edu.dtcc.jshaffe1.activitylifecycle;

// Imports
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Create variables to be used
    private static final String TAG = "Jeff";   // Used for tagging logCat lines
    private TextView txtStatus;     // The text view that will display recent statuses
    private String sNoticeType;     // Stores which type of notice user wants (snackbar or toast)
    private String sNewStatus;      // Holds and manipulates the values in the txtStatus

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the notice type (by default it Snackbar messages will be shown)
        sNoticeType = "Snackbar";

        // Initialize txtStatus to refer to the status text view
        txtStatus = (TextView) findViewById(R.id.tvStatus);

        // Create button objects and link them to the buttons in the layout
        Button btnToasts = (Button) findViewById(R.id.btnToasts);       // Used to show only toasts
        Button btnSnackbars = (Button) findViewById(R.id.btnSnackBars); // Used to show only snackbars

        // Create the onClickListener for the buttons
        View.OnClickListener setTypeOfNotification = new View.OnClickListener() {
            @Override
            public void onClick(View v){
                // Set the notice type depending on which button is clicked
                if (v.getId() == R.id.btnSnackBars)
                    sNoticeType = "Snackbar";           // User wants snackbar notifications
                else
                    sNoticeType = "Toast";              // User wants toasts
            }
        };

        // Set onClickListeners for the buttons in the layout
        btnToasts.setOnClickListener(setTypeOfNotification);
        btnSnackbars.setOnClickListener(setTypeOfNotification);

        // Call the method that will display the Snackbar or Toast
        sendNotice("onCreate() has run!");

        // Add onCreate to the text view to show that onCreate has just run
        txtStatus.setText("onCreate");

        // Add logCat line showing that onCreate has run
        Log.d(TAG, "onCreate has run");
    } // end onCreate()


    protected void sendNotice(String sMessage){
        // This is the method called to display either a snackbar message or a toast message_
        // _depending on which type the user has chosen (which button they tapped)

        // Check if the type of message they want is a snackbar, if it is, send that type_
        // _if it isn't, then send a toast message
        if ("Snackbar".equals(sNoticeType))
            Snackbar.make(findViewById(R.id.myView), sMessage, Snackbar.LENGTH_SHORT).show();
         else
            Toast.makeText(this, sMessage, Toast.LENGTH_SHORT).show();
    }  // end sendNotice

    protected void setStatusField(String sMessage){
        // This is the method called that will update the text view containing the ten most recent_
        // _activity status methods called

        // Check the line count of the status message text view, ensure it is only 10 lines long
        if(txtStatus.getLineCount() == 10) {
             // If there are 10 lines, the next line would not be shown so cut out the first line_
             // _then add the new line
            sNewStatus = txtStatus.getText().toString();  // Get the current text displayed
            // Cut the first line out up to the end of the line return
            sNewStatus = sNewStatus.substring(sNewStatus.indexOf("\n") + 1, sNewStatus.length())
                    +  "\n" + sMessage;
        } else {
            sNewStatus = txtStatus.getText() + "\n" + sMessage;
        }
        txtStatus.setText(sNewStatus);  // Display newly created string to the text view
    }


    // The follow are all stops along the way of the Android app lifecycle.
    // Each method will call sendNotice to display the desired type of message/notification.
    // Then the method will call setStatusField and pass it's name to it to be added to the_
    // _displayed information to the user.
    // Then a message will be sent to the logCat with a tag of "Jeff" and a short description_
    // _of the event.

    @Override
    protected void onStart(){
        // Called when the activity is becoming visible to the user.
        // Followed by onResume() if the activity comes to the foreground,_
        // _or onStop() if it becomes hidden.
        super.onStart();
        sendNotice("onStart() has run!");
        setStatusField("onStart");
        Log.d(TAG, "onStart has run");
    }

    @Override
    protected void onRestart(){
        // Called after the activity has been stopped and before it is started again.
        // Always followed by onStart()
        super.onRestart();
        sendNotice("onRestart() has run!");
        setStatusField("onRestart");
        Log.d(TAG, "onRestart has run");
    }

    @Override
    protected void onResume() {
        // Called when the activity will start interacting with the user.
        // At this point your activity is at the top of the activity stack,_
        // _with user input going to it.
        // Always followed by onPause().
        super.onResume();
        sendNotice("onResume() has run!");
        setStatusField("onResume");
        Log.d(TAG, "onResume has run");
    }

    @Override
    protected void onPause(){
        // Called when the system is about to start resuming a previous activity.
        // Usually used to stop animations and store unsaved changes.
        // Followed by either onResume() if the activity returns back to the front,_
        // _or onStop() if it becomes invisible to the user.
        super.onPause();
        sendNotice("onPause() has run!");
        setStatusField("onPause");
        Log.d(TAG, "onPause has run");
    }

    @Override
    protected void onStop(){
        // Called when the activity is no longer visible to the user because another_
        // _activity has been resumed and is covering this one.
        // Followed by either onRestart() if this activity is coming back to interact_
        // _with the user, or onDestroy() if this activity is going away.

        super.onStop();
        sendNotice("onStop() has run!");
        setStatusField("onStop");
        Log.d(TAG, "onStop has run");
    }

    @Override
    protected void onDestroy(){
        // Called when exiting the app entirely
        super.onDestroy();
        sendNotice("onDestroy() has run!");
        setStatusField("onDestroy");
        Log.d(TAG, "onDestroy has run");
    }
}
