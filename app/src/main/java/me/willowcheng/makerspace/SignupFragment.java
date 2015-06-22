package me.willowcheng.makerspace;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.mrengineer13.snackbar.SnackBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import me.willowcheng.makerspace.util.AppController;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    private static final String TAG = "SignupFragment";
    private static final String SIGNUP_URL = "http://makerspacerepo.herokuapp.com/users";


    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(me.willowcheng.makerspace.R.layout.fragment_signup, container, false);
        Button mSignUpButton = (Button) rootView.findViewById(me.willowcheng.makerspace.R.id.button_sign_up);
        final TextView mFirstNameText = (TextView) rootView.findViewById(me.willowcheng.makerspace.R.id.text_first_name);
        final TextView mLastNameText = (TextView) rootView.findViewById(me.willowcheng.makerspace.R.id.text_last_name);
        final TextView mEmailText = (TextView) rootView.findViewById(me.willowcheng.makerspace.R.id.text_email);
        final TextView mUsernameText = (TextView) rootView.findViewById(me.willowcheng.makerspace.R.id.text_username);
        final TextView mPasswordText = (TextView) rootView.findViewById(me.willowcheng.makerspace.R.id.text_password);
        final TextView mConfirmaionPasswordText = (TextView) rootView.findViewById(me.willowcheng.makerspace.R.id.text_confirm_password);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MaterialDialog pDialog = new MaterialDialog.Builder(getActivity())
                        .content(me.willowcheng.makerspace.R.string.loading)
                        .progress(true, 0)
                        .show();
                try {
                    final JSONObject jsonRequest = new JSONObject(String.format("{\"user\": {\"first_name\": \"%s\",\"last_name\": \"%s\",\"username\": \"%s\",\"email\": \"%s\",\"password\": \"%s\",\"password_confirmation\": \"%s\"}}",
                            mFirstNameText.getText(),
                            mLastNameText.getText(),
                            mUsernameText.getText(),
                            mEmailText.getText(),
                            mPasswordText.getText(),
                            mConfirmaionPasswordText.getText()));

                    Log.i(TAG, jsonRequest.toString());
                    CustomJsonObjectRequest signReq = new CustomJsonObjectRequest(Request.Method.POST, SIGNUP_URL, jsonRequest, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.i(TAG, response.getString("success"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            new SnackBar.Builder(getActivity())
                                    .withMessage(mUsernameText.getText().toString() + ", " + "Register successful!")
                                    .withDuration(SnackBar.SHORT_SNACK)
                                    .show();
                            pDialog.dismiss();

                            getActivity().onBackPressed();
                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pDialog.dismiss();
                            Log.i(TAG, "Failed");
                            Log.i(TAG, error.networkResponse.toString());
                        }
                    });
                    AppController.getInstance().addToRequestQueue(signReq);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
        return rootView;
    }

    public class CustomJsonObjectRequest extends JsonObjectRequest {
        public CustomJsonObjectRequest(int method, String url, JSONObject jsonRequest, Response.Listener listener, Response.ErrorListener errorListener) {
            super(method, url, jsonRequest, listener, errorListener);
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("Accept", "application/json");
            Log.d(TAG, headers.toString());
            return headers;
        }

    }

}
