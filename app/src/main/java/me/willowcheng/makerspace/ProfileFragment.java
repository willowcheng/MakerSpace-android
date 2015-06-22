package me.willowcheng.makerspace;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(me.willowcheng.makerspace.R.layout.fragment_profile, container, false);
        Button signUpButton = (Button) rootView.findViewById(me.willowcheng.makerspace.R.id.sign_up);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MaterialNavigationDrawer) getActivity()).setFragmentChild(new SignupFragment(), "Sign up");
            }
        });
        return rootView;
    }


}
