package com.jorgegiance.chess_timer.ui;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.jorgegiance.chess_timer.R;
import com.jorgegiance.chess_timer.util.TimerPickerDialogCallback;
import com.jorgegiance.chess_timer.util.Utils;

import org.w3c.dom.Text;

public class TimerPickerDialog extends DialogFragment implements NumberPicker.OnValueChangeListener {

    AlertDialog.Builder builder;
    TimerPickerDialogCallback timerCallback;
    String defaultTime;
    int timerState;

    NumberPicker hourPicker;
    NumberPicker minutePicker;
    NumberPicker secondPicker;
    TextView hourLabel;

    public TimerPickerDialog(TimerPickerDialogCallback timerCallback, String defaultTime, int timerState) {
        this.defaultTime = defaultTime;
        this.timerCallback = timerCallback;
        this.timerState = timerState;
    }


    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View mdialog = inflater.inflate(R.layout.dialog_timer_picker, null);

        builder.setView(mdialog)
                .setTitle(defaultTime)
                // Add action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick( DialogInterface dialog, int id ) {
                        // User clicked OK button ...
                        onTimeSaved();


                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick( DialogInterface dialog, int id ) {
                        TimerPickerDialog.this.getDialog().cancel();
                    }
                });



        hourPicker = mdialog.findViewById(R.id.hours);
        minutePicker = mdialog.findViewById(R.id.minutes);
        secondPicker = mdialog.findViewById(R.id.seconds);
        hourLabel = mdialog.findViewById(R.id.hour_label);
        setNumberPickers();

        return builder.create();
    }

    private void onTimeSaved() {
        if (timerState == 3){
            timerCallback.timeSaved(Utils.time2String(minutePicker.getValue(), secondPicker.getValue()));
        }else{
            timerCallback.timeSaved(Utils.time2String(hourPicker.getValue(), minutePicker.getValue(), secondPicker.getValue()));
        }
    }

    private void setNumberPickers() {

        if (timerState == 3){
            hourPicker.setVisibility(View.GONE);
            hourLabel.setVisibility(View.GONE);

            minutePicker.setMaxValue(59);
            secondPicker.setMaxValue(59);

            minutePicker.setMinValue(0);
            secondPicker.setMinValue(0);

            minutePicker.setValue(Utils.string2Minutes(defaultTime));
            secondPicker.setValue(Utils.string2Seconds(defaultTime));

            minutePicker.setOnValueChangedListener(this);
            secondPicker.setOnValueChangedListener(this);

        }else{
            hourPicker.setMaxValue(10);
            minutePicker.setMaxValue(59);
            secondPicker.setMaxValue(59);

            hourPicker.setMinValue(0);
            minutePicker.setMinValue(0);
            secondPicker.setMinValue(0);

            hourPicker.setValue(Utils.string2Hours(defaultTime));
            minutePicker.setValue(Utils.string2Minutes(defaultTime));
            secondPicker.setValue(Utils.string2Seconds(defaultTime));

            hourPicker.setOnValueChangedListener(this);
            minutePicker.setOnValueChangedListener(this);
            secondPicker.setOnValueChangedListener(this);

        }

    }


    @Override
    public void onValueChange( NumberPicker picker, int oldVal, int newVal ) {
        getDialog().setTitle(Utils.time2String(hourPicker.getValue(), minutePicker.getValue(), secondPicker.getValue()));

        if (timerState == 3){
            getDialog().setTitle(Utils.time2String( minutePicker.getValue(), secondPicker.getValue()));

        }else{
            getDialog().setTitle(Utils.time2String(hourPicker.getValue(), minutePicker.getValue(), secondPicker.getValue()));

        }
    }


}
