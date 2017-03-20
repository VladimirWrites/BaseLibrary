package com.vlad1m1r.baselibrary.screens.language;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.ContextThemeWrapper;

import com.vlad1m1r.baselibrary.R;

/**
 * Created by vladimirjovanovic on 3/14/17.
 */

public class LanguageDialogFragment extends DialogFragment {

    public static final String KEY_LANGUAGE = "language";

    public String mLanguage;

    private ILanguageChanged mListener;

    public static LanguageDialogFragment newInstance(String language) {
        LanguageDialogFragment f = new LanguageDialogFragment();

        Bundle args = new Bundle();
        args.putString(KEY_LANGUAGE, language);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLanguage = getArguments().getString(KEY_LANGUAGE);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String[] languages = getResources().getStringArray(R.array.languages);
        final String[] codes = getResources().getStringArray(R.array.language_codes);

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.BaseAlertDialog));
        builder.setTitle(R.string.language_dialog__title);
        builder.setSingleChoiceItems(languages, getPositionInArray(codes, mLanguage), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mLanguage = codes[which];
            }
        });

        builder.setPositiveButton(R.string.language_dialog__ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mListener != null) mListener.onLanguageChanged(mLanguage);
            }
        });

        builder.setNegativeButton(R.string.language_dialog__cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }

    private static int getPositionInArray(String[] array, String string) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(string)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (ILanguageChanged) context;
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }
}