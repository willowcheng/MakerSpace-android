package me.willowcheng.makerspace;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.gc.materialdesign.widgets.ColorSelector;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.TimePickerDialog;
import com.rey.material.widget.Button;
import com.rey.material.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RemoteFragment extends Fragment {

    public RemoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_remote, container, false);

        Spinner makerspaceSpinner = (Spinner) rootView.findViewById(R.id.spinner_makerspace);
        ArrayList<String> makerspaceScenarios = new ArrayList<>();
        makerspaceScenarios.add("Open");
        makerspaceScenarios.add("Close");
        makerspaceScenarios.add("Energy");
        makerspaceScenarios.add("Demo");
        ArrayAdapter<String> makerspaceAdapter = new ArrayAdapter<>(getActivity(), R.layout.row_spinner, makerspaceScenarios);
        makerspaceSpinner.setAdapter(makerspaceAdapter);

        Spinner ariseSpinner = (Spinner) rootView.findViewById(R.id.spinner_arise);
        ArrayList<String> ariseScenarios = new ArrayList<>();
        ariseScenarios.add("Open");
        ariseScenarios.add("Close");
        ariseScenarios.add("Romantic");
        ariseScenarios.add("Wild");
        ArrayAdapter<String> ariseAdapter = new ArrayAdapter<>(getActivity(), R.layout.row_spinner, ariseScenarios);
        ariseSpinner.setAdapter(ariseAdapter);

        Button timerButton = (Button) rootView.findViewById(R.id.button_timer);
        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog.Builder builder = new TimePickerDialog.Builder(6, 00) {
                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        TimePickerDialog dialog = (TimePickerDialog) fragment.getDialog();
                        Toast.makeText(getActivity(), "Time is " + dialog.getFormattedTime(SimpleDateFormat.getTimeInstance()), Toast.LENGTH_SHORT).show();
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
                        super.onNegativeActionClicked(fragment);
                    }
                };

                builder.positiveAction("OK")
                        .negativeAction("CANCEL");
                DialogFragment fragment = DialogFragment.newInstance(builder);
                fragment.show(getFragmentManager(), null);
            }
        });

        Button colorSelectButton = (Button) rootView.findViewById(R.id.button_color_select);
        colorSelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorSelector colorSelector = new ColorSelector(getActivity(), Color.RED, null);
                colorSelector.show();
            }
        });

        return rootView;
    }


}
